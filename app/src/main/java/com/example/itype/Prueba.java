package com.example.itype;

import android.os.CountDownTimer;

import java.util.ArrayList;

public class Prueba {
    private GeneradorComportamiento generador;//tipo de generador para nuevaPalabra()
    private final ObservadorPrueba controlador;//Observador que implementa Tick() y finalizar()
    private static CountDownTimer timer;//Timer para la prueba
    private int TiempoPrueba;
    private int TiempoRestante;

    Prueba(final ObservadorPrueba observador, int Tiempo_Seg)
    {
        TiempoPrueba=Tiempo_Seg;
        TiempoRestante=TiempoPrueba;
        this.controlador=observador;//el controlador que implementa Tick() y finalizar() para la actualización de la vista

        timer =new CountDownTimer(Tiempo_Seg*1000, 1000) {
            //Se llama cada vez que pasan 1000 milisegundos
            @Override
            public void onTick(long millisUntilFinished) {
                TiempoRestante=(int)(millisUntilFinished/1000);
                controlador.Tick(TiempoRestante);
            }

            //ejecuta instrucciones luego de que termina la cuenta regresiva
            @Override
            public void onFinish() {
                controlador.finalizar();
            }
        };
    }

    public void setGenerador(GeneradorComportamiento generador) {
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

        Vf=(60*correctChar)/(5*Ttranscurrido);

        return Integer.toString(Vf);

    }
    //Emprieza a correr la cuenta regresiva
    public void empezar()
    {
        timer.start();
    }
}
