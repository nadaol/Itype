package com.example.itype;

import android.os.CountDownTimer;

import java.util.ArrayList;

//clase prueba en la se asigna un temporizador y se le avisa cada segundo la llamada de la funcion onTick y al finalizar la llamada de la funcion
public class Prueba {
    private static Prueba unique;
    private static Comportamiento_Generador generador;//tipo de generador para nuevaPalabra()
    private static Temporizador tempo;//Observador que implementa Tick() y finalizar()
    private static CountDownTimer timer;//Timer para la prueba
    private static int TiempoPrueba;
    private static int TiempoRestante;
    private static int Caractateres_Correctos;
    private static int plus;

    private Prueba() { }

    public static Prueba getInstance()
    {
        if(unique==null) {
            unique = new Prueba(); }
        return unique;
    }

    public static void setPrueba(Temporizador assigned,String dificultad,int Tiempo_Seg)
    {
        tempo=assigned;
        TiempoPrueba=Tiempo_Seg;
        if (dificultad!=null){
        if(dificultad.equals("Facil")){generador=new GeneradorPalabras_facil();plus=0;}
        else if (dificultad.equals("Media")){generador=new GeneradorPalabras_Intermedio();plus=3;}
        else if (dificultad.equals("Dificil")){generador=new GeneradorPalabras_dificil();plus=6;}}
        else {generador=new GeneradorPalabras_facil();plus=0;}
    }

    public static String nuevaPalabra(ArrayList<String> palabras)
    {
        return generador.generar(palabras);
    }
    //devuelve velocidad en base a caracteres correctos
    public static String CalcularVelocidad()
    {
        int Vf ,Ttranscurrido = TiempoPrueba-TiempoRestante;
        if(Ttranscurrido==0) return "0";
        Vf=(60*Caractateres_Correctos)/(Ttranscurrido);
        return Integer.toString(Vf);
    }
    public static void pause(){timer.cancel();};
    public static void resume(){empezar2(TiempoRestante);};
    //Emprieza a correr la cuenta regresiva de 'TiempoPrueba' segundos
    public static void empezar()
    {
        Caractateres_Correctos=0;
        empezar2(TiempoPrueba);
    }
    //Emprieza a correr la cuenta regresiva especificando la duracion
    private static void empezar2(int Tiempo_Seg)
    {
        if(timer!=null) timer.cancel();
        timer =new CountDownTimer(Tiempo_Seg*1000, 1000) {
            //Se llama cada vez que pasan 1000 milisegundos
            @Override
            public void onTick(long millisUntilFinished) {
                TiempoRestante=(int)(millisUntilFinished/1000);
                tempo.Tick(TiempoRestante);
            }

            //ejecuta instrucciones luego de que termina la cuenta regresiva
            @Override
            public void onFinish() {
                Caractateres_Correctos+=plus;
                tempo.finalizar();timer.cancel();
            }

        }.start();
    }

    public static void addCorrectChars(int CorrectChars)
    {
        Caractateres_Correctos+=CorrectChars;
    }
}
