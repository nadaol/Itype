package com.example.itype;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ModeloInicioRegistroTest {

    private ControladorInicioSesion conInicioSesion;
    private ControladorRegistro conRegistro;

    EditText Username_et_test;
    EditText Password_et_test;

    @Before
    public void inicial() {
        conInicioSesion = new ControladorInicioSesion();
        conRegistro = new ControladorRegistro();
    }


    @Test
    public void validacionMinInicio() {
        assertTrue(ModeloInicioRegistro.validacion_entrada("usuario"));
    }

    @Test
    public void validacionMayusInicio() {
        assertTrue(ModeloInicioRegistro.validacion_entrada("USUARIO"));
    }

    @Test
    public void validacionNumeroInicio() {
        assertTrue(ModeloInicioRegistro.validacion_entrada("1234"));
    }

    @Test
    public void validacionLetraNumInicio() {
        assertTrue(ModeloInicioRegistro.validacion_entrada("UsUaRiO1234"));
    }

    @Test
    public void validacionCaracterInicio() {
        assertFalse(ModeloInicioRegistro.validacion_entrada("$usuario123."));
    }
}
