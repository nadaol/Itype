package com.example.itype;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Lector_textoTest {

    private Lector_texto lector;
    private ArrayList<String> lectura;

    @Test
    @Before
    public void Prueba1_Creacion_Lector() throws IOException { //PU6.1
        lector = new Lector_texto("words.txt");
        assertNotNull(lector);
    }

    @Test
    public void Prueba2_LecturaPalabras() { //PU6.2
            lectura =lector.getArray();
         assertNotNull(lectura);
    }

}