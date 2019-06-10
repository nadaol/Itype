package com.example.itype;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class Prueba_GeneradorPalabras {

    ArrayList<String> palabras = new ArrayList<String>() {
        {
            add("facil");
            add("medio");
            add("dificil");
        }
    };
    Comportamiento_Generador facilGen = new GeneradorPalabras_facil();
    Comportamiento_Generador interGen = new GeneradorPalabras_Intermedio();
    Comportamiento_Generador difGen = new GeneradorPalabras_dificil();

    @Test
    public void PruebaFacil_generar() {
        int count=0;
        for(int i=0;i<1000;i++){if(facilGen.generar(palabras).equals("facil")){count++;}}
        assertTrue(count>333);
    }

    @Test
    public void PruebaIntermedio_generar() {
        int count=0;
        for(int i=0;i<1000;i++){if(interGen.generar(palabras).equals("medio")){count++;}}
        assertTrue(count>333);
    }

    @Test
    public void PruebaDificil_generar() {
        int count=0;
        for(int i=0;i<1000;i++){if(difGen.generar(palabras).equals("dificil")){count++;}}
        assertTrue(count>333);
    }

}