package com.example.vistaactiva;

import java.util.ArrayList;
import java.util.Random;

public class GeneradorPalabras_Intermedio {
    //devuelve la palabra con dificultad promedio de 3 seleccionadas al azar
    public String generar(ArrayList<String> palabras)
    {
        Random rand = new Random();
        int indice,indice_acumulador=0;
        int size=palabras.size();
        for(int i=0;i<3;i++)
        {
            indice_acumulador += rand.nextInt(size+1) ;
        }
        return palabras.get((int)(indice_acumulador/4));
    }
}
