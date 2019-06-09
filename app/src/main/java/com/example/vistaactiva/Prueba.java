package com.example.vistaactiva;

import android.os.CountDownTimer;

import java.util.ArrayList;

public class Prueba {
    private Comportamiento_Generador generador;//tipo de generador para nuevaPalabra()
    private final Temporizador obs;//Observador que implementa Tick() y finalizar()
    private static CountDownTimer timer;//Timer para la prueba
    private final int TiempoPrueba;
    private int TiempoRestante;

    //clase prueba en la que se avisa al observador cada segundo la llamada de la funcion onTick y al finalizar la llamada de la funcion
    public Prueba(final Temporizador observador,int Tiempo_Seg)
    {
        TiempoPrueba=Tiempo_Seg;
        this.obs=observador;//el controlador que implementa Tick() y finalizar() para la actualización de la vista
    }

    public void setGenerador(Comportamiento_Generador generador) {
        this.generador = generador;
    }

    public String nuevaPalabra(ArrayList<String> palabras)
    {
        return generador.generar(palabras);
    }
    //devuelve velocidad en base a caracteres correctos
    public String CalcularVelocidad(int correctChar)
    {
        int Vf ,Ttranscurrido = TiempoPrueba-TiempoRestante;
        if(Ttranscurrido==0) return "0";
        Vf=(60*correctChar)/(Ttranscurrido);
        return Integer.toString(Vf);
    }
    public void pause(){timer.cancel();};
    public void resume(){empezar2(TiempoRestante);};
    //Emprieza a correr la cuenta regresiva de 'TiempoPrueba' segundos
    public void empezar()
    {
        empezar2(TiempoPrueba);
    }
    //Emprieza a correr la cuenta regresiva especificando la duracion
    private void empezar2(int Tiempo_Seg)
    {
        timer =new CountDownTimer(Tiempo_Seg*1000, 1000) {
            //Se llama cada vez que pasan 1000 milisegundos
            @Override
            public void onTick(long millisUntilFinished) {
                TiempoRestante=(int)(millisUntilFinished/1000);
                obs.Tick(TiempoRestante);
            }

            //ejecuta instrucciones luego de que termina la cuenta regresiva
            @Override
            public void onFinish() {
                obs.finalizar();
            }

        }.start();
    }
}
