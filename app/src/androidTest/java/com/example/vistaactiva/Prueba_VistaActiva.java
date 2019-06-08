package com.example.vistaactiva;

import android.support.test.rule.ActivityTestRule;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Prueba_VistaActiva {

    private VistaActiva_Controller activity = null;
    private final int time = 30;

    @Rule
    public ActivityTestRule<VistaActiva_Controller> TestRule;

    {
        TestRule = new ActivityTestRule<VistaActiva_Controller>(VistaActiva_Controller.class);
    }

    @Before
    @Test
public void Prueba1_ExistenciaBotones()
    {
        activity=TestRule.getActivity();
        assertNotNull(activity.findViewById(R.id.comenzar_btn));
        assertNotNull(activity.findViewById(R.id.salir_btn));
    }

    @Test
    public void Prueba2_ExistenciaTextViews()
    {
        assertNotNull(activity.findViewById(R.id.Entrada_Etext));
        assertNotNull(activity.findViewById(R.id.Tiempo_Tview));
        assertNotNull(activity.findViewById(R.id.velocidad_Tview));
        assertNotNull(activity.findViewById(R.id.Palabra_modelo));
        assertNotNull(activity.findViewById(R.id.tiempo));
        assertNotNull(activity.findViewById(R.id.tiempoTitle_Tview));
    }

    @Test
    public void Prueba3_ContenidoTextViews()
    {
        EditText entrada = (EditText) activity.findViewById(R.id.Entrada_Etext);
        TextView Tiempo = (TextView) activity.findViewById(R.id.Tiempo_Tview);
        TextView miVel = (TextView) activity.findViewById(R.id.velocidad_Tview);
        Button comenzar = (Button) activity.findViewById(R.id.comenzar_btn);
        assertEquals("",entrada.getText().toString());
        assertEquals(Integer.toString(time),Tiempo.getText().toString());
        assertEquals("0",miVel.getText().toString());
        assertEquals("Comenzar!",comenzar.getText().toString());
    }



    @Test
    public void Prueba4_Tiempo()
    {
        assertNotNull(activity.findViewById(R.id.comenzar_btn));
    }

    @Test
    public void Prueba5_finalizar() throws Exception {//completar
        TextView Tiempo = (TextView) activity.findViewById(R.id.Tiempo_Tview);
        Button comenzar = (Button) activity.findViewById(R.id.comenzar_btn);
        onView(withId(comenzar.getId())).perform(click());
        TimeUnit.SECONDS.sleep(time);//espera que la prueba finalize
        assertEquals("0",Tiempo.getText().toString());
        assertEquals("Siguiente",comenzar.getText().toString());
        activity=null;
    }
}