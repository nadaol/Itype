package com.example.itype.TestUnitarios;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.test.rule.ActivityTestRule;

import com.example.itype.ControladorPerfil;
import com.example.itype.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ControladorPerfilTest {

    @Rule
    public ActivityTestRule<ControladorPerfil> TestRule = new ActivityTestRule<ControladorPerfil>(ControladorPerfil.class);

    private ControladorPerfil activity = null;

    @Before
    public void setUp() throws Exception {
        activity=TestRule.getActivity();
    }

    @Test
    public void ExistenciaTextViews()   //PU9.1
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
    public void ExistenciaBotones() //PU9.2
    {
        Button btn = activity.findViewById(R.id.volver_btn);
        assertNotNull(btn);
        btn = activity.findViewById(R.id.volver_btn);
        assertNotNull(btn);
    }

    @Test
    public void ContenidoTextViews() //PU9.3
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
    public void ContenidoBotones() //PU9.4
    {
        Button btn = activity.findViewById(R.id.volver_btn);
        assertEquals("MENÚ PRINCIPAL",btn.getText().toString());
    }

    @After
    public void tearDown() throws Exception {
        activity=null;
    }

}
