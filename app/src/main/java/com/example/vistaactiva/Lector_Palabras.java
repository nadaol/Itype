package com.example.vistaactiva;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Lector_Palabras {
    ArrayList<String> palabras_array;

    public Lector_Palabras(InputStream is) throws IOException {
         palabras_array= new ArrayList<String>();
        //lee El Archivo y lo coloca en un Arreglo de Strings para luego retornarlo
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String linea;
        while ((linea=br.readLine())!=null) {
            palabras_array.add(linea.toLowerCase());
        }

    }
    public ArrayList<String> getArray()
    {
        return palabras_array;
    }
}

