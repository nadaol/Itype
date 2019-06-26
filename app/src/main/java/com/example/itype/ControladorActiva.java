package com.example.itype;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import static java.lang.Thread.sleep;

public  class ControladorActiva extends AppCompatActivity implements Temporizador {

    private EditText entrada;
    private Lector_texto lector;//lector de texto para lectura de las palabras
    private Button comenzar;
    private Prueba prueba;//objeto Prueba en background el cual avisa actualizaciones de vista
    private static TextView modelo, Tiempo, miVel;
    private final int TiempoPrueba_Seg = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_activa);

        try {
            lector = new Lector_texto("assets/words.txt");
        }//lectura de archivo de palabras
        catch (IOException e) {
            e.printStackTrace();
        }
        entrada = (EditText) findViewById(R.id.Entrada_Etext);
        modelo = (TextView) findViewById(R.id.Palabra_modelo);
        Tiempo = (TextView) findViewById(R.id.Tiempo_Tview);
        miVel = (TextView) findViewById(R.id.velocidad_Tview);
        comenzar = (Button) findViewById(R.id.comenzar_btn);
        //deshabilito sugerencias del teclado
        comenzar.setText("Comenzar!");
        miVel.setText("0");
        Tiempo.setText(Integer.toString(TiempoPrueba_Seg));
        //inicializacion de la prueba con observador this
        //---pasar dificultad de vista seleccion---
        Intent intent = getIntent();
        String dificultad = intent.getStringExtra("dificultad");
        prueba = Prueba.getInstance();
        prueba.setPrueba(this, dificultad, TiempoPrueba_Seg);

        //listenter para manejo de deteccion de palabras
        entrada.setOnKeyListener(new View.OnKeyListener() {
            //Este metodo captura eventos del teclado tactil
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if ((event.getAction() == KeyEvent.ACTION_DOWN)) {//si preciono cualquier caracter del teclado me da true
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {//si llega el evento del enter del teclado da true

                        String palabra_modelo = modelo.getText().toString();
                        String palabra_escrita = conversionPalabra(entrada.getText().toString());

                        if (palabra_escrita.equals(palabra_modelo)) {
                            modelo.setText(prueba.nuevaPalabra(lector.getArray()));
                            prueba.addCorrectChars(palabra_modelo.length());
                            entrada.setText(null);
                        } else {
                            entrada.setText(null);
                            return false;
                        }
                    } else
                        return false;
                }
                return true;
            }
        });

        disableEditText(entrada);
    }

    @Override
    public void Tick(int tiempoRestante) {//se llama cada segundo por el timer de prueba
        Tiempo.setText(Integer.toString(tiempoRestante));
        miVel.setText(prueba.CalcularVelocidad());
    }

    @Override
    public void finalizar() {//se llama al finalizar el timer de prueba
        String velFinal = prueba.CalcularVelocidad();
        miVel.setText(velFinal);
        Intent i = new Intent(getApplicationContext(), ControladorPuestos.class);
        startActivity(i);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://192.168.0.45:8081").build();
        client.newWebSocket(request, WebSocketConnection.getInstance());//creo una conexion por webSocket
        client.dispatcher().executorService().shutdown();
        try {
            sleep(400);
            if (velFinal != null)
                WebSocketConnection.enviar(Usuario.getName() + "," + velFinal);//requiere conexion con servidor local,debería enviar la velocidad al terminar la prueba
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
        return;
    }

    public void empezar_reintentar(View view) {
        enableEditText(entrada);
        entrada.setText("");
        comenzar.setText("Reintentar");
        //empiezo el timer de la prueba
        modelo.setText(prueba.nuevaPalabra(lector.getArray()));//seteo primer palabra modelo
        prueba.empezar();

    }

    public void MenuPrincipal(View view) {
        //ir a seleccion de dificultad
        if (prueba.isActive()) {
            prueba.pause();
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("¿Desea regresar a menú principal?");
            builder.setMessage("Se perdera el progreso de la partida");
            builder.setPositiveButton("Si. Regresar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    regresoMenu();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    prueba.resume();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else regresoMenu();
    }

    public void regresoMenu() {
        Intent i = new Intent(this, ControladorMenuPrincipal.class);
        startActivity(i);
        finish();
    }

    private void disableEditText(EditText editText) {
        editText.setText(null);
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
    }

    private void enableEditText(EditText editText) {
        editText.setText(null);
        editText.setEnabled(true);
        editText.setCursorVisible(true);
        editText.setFocusableInTouchMode(true);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        editText.requestFocus();
    }

    @Override
    public void onBackPressed() {

    }


    private String conversionPalabra(String ingreso) {
        return ingreso.toLowerCase();
    }
}
