package com.example.vistaactiva;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class Prueba_GeneradorPalabras {

    ArrayList<String> palabras = new ArrayList<String>() {
        {
            add("facil");
            add("medio");
            add("dificil");
        }
    };
    GeneradorPalabras_facil facilGen = new GeneradorPalabras_facil();
    GeneradorPalabras_Intermedio interGen = new GeneradorPalabras_Intermedio();
    GeneradorPalabras_dificil difGen = new GeneradorPalabras_dificil();

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