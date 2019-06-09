package com.example.itype;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Lector_texto {
   private ArrayList<String> lectura_lineas;

    public Lector_texto(String path) throws IOException {
         lectura_lineas = new ArrayList<String>();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
        //lee El Archivo y lo coloca en un Arreglo de Strings para luego retornarlo
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String linea;
        while ((linea=br.readLine())!=null) {
            lectura_lineas.add(linea.toLowerCase());
        }
    }
    public ArrayList<String> getArray()
    {
        return lectura_lineas;
    }
}

