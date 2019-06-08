package com.example.vistaactiva;

import java.util.ArrayList;
import java.util.Random;

//devuelve la palabra con mas dificultad de 3 elegidas al azar
public class GeneradorPalabras_dificil implements Comportamiento_Generador {
    public String generar(ArrayList<String> palabras)
    {
        Random rand = new Random();
        int indice,indice_aux=0;
        int size=palabras.size();
        for(int i=0;i<3;i++)
        {
            indice = rand.nextInt(size) ;
            if(indice>indice_aux) indice_aux=indice;
        }
        return palabras.get(indice_aux);
    }

}
