package com.example.vistaactiva;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class Perfil_ControllerTest {

    @Rule
    public ActivityTestRule<Perfil_Controller> TestRule=new ActivityTestRule<Perfil_Controller>(Perfil_Controller.class);

    private Perfil_Controller activity = null;

    @Before
    public void setUp() throws Exception {
        activity=TestRule.getActivity();
    }

    @Test
    public void ExistenciaTextViews()
    {
        View view = activity.findViewById(R.id.txtUsuario);
        assertNotNull(view);
        view = activity.findViewById(R.id.txtVelProm);
        assertNotNull(view);
        view = activity.findViewById(R.id.txtJugadas);
        assertNotNull(view);
        view = activity.findViewById(R.id.txtVelMax);
        assertNotNull(view);
    }

    @Test
    public void ExistenciaBotones()
    {
        Button btn = activity.findViewById(R.id.volver_btn);
        assertNotNull(btn);
        btn = activity.findViewById(R.id.salir_btn);
        assertNotNull(btn);
    }

    @Test
    public void ContenidoTextViews()
    {
        TextView view = activity.findViewById(R.id.txtUsuario);
        assertEquals("Usuario :",view.getText().toString());
        view = activity.findViewById(R.id.txtVelProm);
        assertEquals("Velocidad Promedio :",view.getText().toString());
        view = activity.findViewById(R.id.txtJugadas);
        assertEquals("Jugadas :",view.getText().toString());
        view = activity.findViewById(R.id.txtVelMax);
        assertEquals("Velocidad Maxima :",view.getText().toString());
    }

    @Test
    public void ContenidoBotones()
    {
        Button btn = activity.findViewById(R.id.volver_btn);
        assertEquals("Volver a jugar",btn.getText().toString());
        btn = activity.findViewById(R.id.salir_btn);
        assertEquals("Salir",btn.getText().toString());
    }



    @After
    public void tearDown() throws Exception {
        activity=null;
    }

}