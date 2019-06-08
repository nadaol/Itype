package com.example.vistaactiva;

import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Prueba_VistaActiva {

    private final int TiempoPrueba_Seg = 30;
    private Prueba prueba;
    private VistaActiva_Controller activity = null;
    private final int time = 60;
    private static int count=0;

    @Rule
    public ActivityTestRule<VistaActiva_Controller> TestRule;

    {
        TestRule = new ActivityTestRule<VistaActiva_Controller>(VistaActiva_Controller.class);

    }

    @Test
    @Before
    public void CreacionPrueba() throws Exception {
        activity=TestRule.getActivity();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                prueba = new Prueba(activity, time);
                assertNotNull(prueba);
                prueba.empezar();
            }
        });
    }

    
    @After
    public void tearDown() throws Exception {
    }
}