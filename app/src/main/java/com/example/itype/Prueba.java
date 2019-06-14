package com.example.itype;

import android.os.CountDownTimer;

import java.util.ArrayList;

public class Prueba {
    private Comportamiento_Generador generador;//tipo de generador para nuevaPalabra()
    private final Temporizador obs;//Observador que implementa tick() y finalizar()
    private static CountDownTimer timer;//Timer para la prueba
    private final int TiempoPrueba;
    private int TiempoRestante;
    private static boolean corriendo = false; // variable booleana para saber si el reloj esta corriendo o no

    //clase prueba en la que se avisa al observador cada segundo la llamada de la funcion onTick y al finalizar la llamada de la funcion
    public Prueba(final Temporizador observador, int Tiempo_Seg)
    {
        TiempoPrueba=Tiempo_Seg;
        this.obs=observador;//el controlador que implementa tick() y finalizar() para la actualización de la vista
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
                TiempoRestante = (int) (millisUntilFinished / 1000);
                obs.tick(TiempoRestante);
            }

            //ejecuta instrucciones luego de que termina la cuenta regresiva
            @Override
            public void onFinish() {
                obs.finalizar();
                timer.cancel();
            }

            //}.start();
        };
        reinicioInicio();
    }

    private void reinicioInicio() {
        if( corriendo == false ){
            corriendo = true;
            timer.start();
        }
        else{       //reinicio
            timer.cancel(); // cancel
            timer.start();  // then restart
        }
    }

    public void pausarTimer(){
        if(corriendo == true){
            timer.cancel();
            corriendo = false;
        }
    }
    public void salirTimer() {
        obs.finalizar();
    }
}
