package com.example.vistaactiva;

import android.content.Context;
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

public  class VistaActiva_Controller extends AppCompatActivity implements ObservadorPrueba {

    private EditText entrada;
    private Lector_texto lector;//lector de texto para lectura de las palabras
    private Prueba prueba;//objeto Prueba en background el cual avisa actualizaciones de vista
    private ArrayList<String> Palabras;//lista de 10000 palabras
    private static TextView modelo,Tiempo,miVel;
    private Button comenzar;
    private final int TiempoPrueba_Seg = 30;
    private static int Caractateres_Correctos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_activa);

        try{lector =new Lector_texto(getAssets().open("words.txt"));}//lectura de archivo de palabras
        catch (IOException e){e.printStackTrace();}
        Palabras = lector.getArray();//obtengo palabras
        entrada = (EditText) findViewById(R.id.Entrada_Etext);
        modelo = (TextView) findViewById(R.id.Palabra_modelo);
        Tiempo = (TextView) findViewById(R.id.Tiempo_Tview);
        miVel = (TextView) findViewById(R.id.velocidad_Tview);
        comenzar = (Button) findViewById(R.id.comenzar_btn);

        comenzar.setText("Empezar!");
        miVel.setTextColor(Color.parseColor("#000000"));
        miVel.setText("0");
        Tiempo.setText(Integer.toString(TiempoPrueba_Seg));
        //inicializacion de la prueba con observador this
        prueba = new Prueba(this,TiempoPrueba_Seg);
        //seteo la generacion de palabras del objeto prueba
        //---pasar dificultad de vista seleccion---
        prueba.setGenerador(new GeneradorPalabras_dificil());
        //creo objeto listenter para manejo de deteccion de palabras
        final View.OnKeyListener listener = new View.OnKeyListener() {
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

        comenzar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(comenzar.getText().equals("Empezar!")) {//al hacer click en Empezar!
                enableEditText(entrada,listener);//asigno listener a la entrada
                //deshabilito sugerencias del teclado
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(entrada, InputMethodManager.SHOW_IMPLICIT);
                Caractateres_Correctos = 0;
                //empiezo el timer de la prueba
                prueba.empezar();
                modelo.setText(prueba.nuevaPalabra(Palabras));//seteo primer palabra modelo
            }
            else if(comenzar.getText().equals("Siguiente")){//al hacer click en Siguiente
                //ir a estadísticas
                finish();
            }
        }
    });


    }

    @Override
    public void Tick(int tiempoRestante) {//se llama cada segundo por el timer de prueba
        Tiempo.setText(Integer.toString(tiempoRestante));
        miVel.setText(prueba.CalcularVelocidad(Caractateres_Correctos));
    }

    @Override
    public void finalizar() {//se llama al finalizar el timer de prueba
        disableEditText(entrada);
        modelo.setText(null);
        Tiempo.setText("0");
        miVel.setText(prueba.CalcularVelocidad(Caractateres_Correctos));
        miVel.setTextColor(Color.parseColor("#DE2E13"));
        comenzar.setText("Siguiente");
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

}







