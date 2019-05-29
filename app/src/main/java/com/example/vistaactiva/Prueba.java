package com.example.vistaactiva;

import android.os.CountDownTimer;

import java.util.ArrayList;

public class Prueba {
    private Comportamiento_Generador generador;
    private final ObservadorPrueba controlador;
    private static CountDownTimer timer;
    private int TiempoPrueba;
    private int TiempoRestante;

    Prueba(final ObservadorPrueba observador, int Tiempo_Seg)
    {
        TiempoPrueba=Tiempo_Seg;
        TiempoRestante=TiempoPrueba;
        this.controlador=observador;
        timer =new CountDownTimer(Tiempo_Seg*1000, 1000) {
            //Se llama cada vez que pasan 1000 milisegundos
            @Override
            public void onTick(long millisUntilFinished) {
                TiempoRestante=(int)(millisUntilFinished/1000);
                controlador.Tick(TiempoRestante);
            }

            //ejecuta instrucciones luego de que termina la cuenta atras
            @Override
            public void onFinish() {
                        controlador.finalizar();
                      }
        };
    }

    public void setGenerador(Comportamiento_Generador generador) {
        this.generador = generador;
    }

    public String nuevaPalabra(ArrayList<String> palabras)
    {
        return generador.generar(palabras);
    }

    public String CalcularVelocidad(int correctChar)
    {

        int Vf ,Ttranscurrido = TiempoPrueba-TiempoRestante;

        Vf=(60*correctChar)/(5*Ttranscurrido);

        return Integer.toString(Vf);

    }
    public void empezar()
    {
        timer.start();
    }
}
