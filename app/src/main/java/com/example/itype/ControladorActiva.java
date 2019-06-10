package com.example.itype;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static java.lang.Thread.sleep;

public  class ControladorActiva extends AppCompatActivity implements Temporizador {

    private EditText entrada;
    private Button salir;
    private Button comenzar;
    private Lector_texto lector;//lector de texto para lectura de las palabras
    private Prueba prueba;//objeto Prueba en background el cual avisa actualizaciones de vista
    private ArrayList<String> Palabras;//lista de 10000 palabras
    private static TextView modelo,Tiempo,miVel;
    private final int TiempoPrueba_Seg = 30;
    private static int Caractateres_Correctos=0;
    private View.OnKeyListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_activa);

        try{lector =new Lector_texto("assets/words.txt");}//lectura de archivo de palabras
        catch (IOException e){e.printStackTrace();}
        Palabras = lector.getArray();//obtengo palabras
        entrada = (EditText) findViewById(R.id.Entrada_Etext);
        modelo = (TextView) findViewById(R.id.Palabra_modelo);
        Tiempo = (TextView) findViewById(R.id.Tiempo_Tview);
        miVel = (TextView) findViewById(R.id.velocidad_Tview);
        comenzar = (Button) findViewById(R.id.comenzar_btn);
        salir = (Button) findViewById(R.id.salir_btn);

        comenzar.setText("Comenzar!");
        miVel.setTextColor(Color.parseColor("#000000"));
        miVel.setText("0");
        Tiempo.setText(Integer.toString(TiempoPrueba_Seg));
        //inicializacion de la prueba con observador this
        //---pasar dificultad de vista seleccion---
        Intent intent = getIntent();
        String dificultad = intent.getStringExtra("dificultad");
        if(dificultad!=null){
            if(dificultad.equals("Facil"))prueba = Prueba.getInstance(this,TiempoPrueba_Seg,new GeneradorPalabras_facil());
            else if(dificultad.equals("Media")) prueba = Prueba.getInstance(this,TiempoPrueba_Seg,new GeneradorPalabras_Intermedio());
            else if(dificultad.equals("Dificil")) prueba = Prueba.getInstance(this,TiempoPrueba_Seg,new GeneradorPalabras_dificil());}
        else prueba = Prueba.getInstance(this,TiempoPrueba_Seg,new GeneradorPalabras_facil());

        //creo objeto listenter para manejo de deteccion de palabras
        listener = new View.OnKeyListener() {
            //Este metodo captura eventos del teclado tactil
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN)) {//si preciono cualquier caracter del teclado me da true
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {//si llega el evento del enter del teclado da true

                        String palabra_modelo=modelo.getText().toString();
                        String palabra_escrita= entrada.getText().toString();

                        if(palabra_escrita.equals(palabra_modelo)){
                            modelo.setText(prueba.nuevaPalabra(Palabras));
                            Caractateres_Correctos+= palabra_modelo.length();
                            entrada.setText(null);
                        }

                        else {
                            entrada.setText(null);
                            return false;
                        }
                    }
                    else
                        return false;
                }
                return true;}
        };



    }

    @Override
    public void Tick(int tiempoRestante) {//se llama cada segundo por el timer de prueba
        Tiempo.setText(Integer.toString(tiempoRestante));
        miVel.setText(prueba.CalcularVelocidad(Caractateres_Correctos));
    }

    @Override
    public void finalizar() {//se llama al finalizar el timer de prueba
        comenzar.setText("Siguiente");
        disableEditText(entrada);
        modelo.setText(null);
        Tiempo.setText("0");
        miVel.setText(prueba.CalcularVelocidad(Caractateres_Correctos));
        miVel.setTextColor(Color.parseColor("#DE2E13"));
    }

    private void disableEditText(EditText editText) {
        editText.setText(null);
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setOnKeyListener(null);
    }

    private void enableEditText(EditText editText, View.OnKeyListener listener) {
        editText.setFocusable(true);
        editText.setEnabled(true);
        editText.setCursorVisible(true);
        editText.setOnKeyListener(listener);
    }

    public void empezar_siguiente (View view)
    {
        if(comenzar.getText().toString().equals("Comenzar!")||comenzar.getText().toString().equals("Reintentar")) {
            entrada.setText("");
            comenzar.setText("Reintentar");
            enableEditText(entrada, listener);//asigno listener a la entrada
            //deshabilito sugerencias del teclado
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(entrada, InputMethodManager.SHOW_IMPLICIT);
            Caractateres_Correctos = 0;
            //empiezo el timer de la prueba
            modelo.setText(prueba.nuevaPalabra(Palabras));//seteo primer palabra modelo
            prueba.empezar();
        }

       else if(comenzar.getText().toString().equals("Siguiente")) {
            Intent i = new Intent(getApplicationContext(), ControladorPuestos.class);
            startActivity(i);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("ws://192.168.0.21:8080").build();
            client.newWebSocket(request,WebSocketConnection.getInstance());//creo una conexion por webSocket
            client.dispatcher().executorService().shutdown();
            try{
                sleep(400);
                String vel = miVel.getText().toString();
                if (vel!=null)WebSocketConnection.enviar(Usuario.getName()+","+vel);//requiere conexion con servidor local,debería enviar la velocidad al terminar la prueba
            }
            catch(Exception e){e.printStackTrace();}
            finish();
            return;
        }
    }



    public void MenuPrincipal (View view)
    {
        //ir a seleccion de dificultad
        prueba.pause();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¿Desea regresar a menú principal?");
        builder.setMessage("Se perderá el progreso de la partida");
        builder.setPositiveButton("Si. Regresar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                finish();
                regresoMenu();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                dialogInterface.dismiss();
                prueba.resume();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void regresoMenu () {
        Intent i = new Intent(this, ControladorMenuPrincipal.class);
        startActivity(i);
    }

}
