package com.example.lmorda.websocketchat;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

public class Puestos_ControllerTest {


    @Rule
    public ActivityTestRule<Puestos_Controller> TestRule=new ActivityTestRule<Puestos_Controller>(Puestos_Controller.class);

    private Puestos_Controller activity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(Perfil_Controller.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        activity=TestRule.getActivity();
    }

    @Test
    public void ExistenciaTextViews()
    {
        View view = activity.findViewById(R.id.users);
        assertNotNull(view);
        view = activity.findViewById(R.id.puestos);
        assertNotNull(view);
        view = activity.findViewById(R.id.velocidades);
        assertNotNull(view);
    }
    @Test
    public void ExistenciaBotones()
    {
        Button btn = activity.findViewById(R.id.volver_btn);
        assertNotNull(btn);
        btn = activity.findViewById(R.id.siguiente_btn);
        assertNotNull(btn);
    }

    @Test
    public void ExistenciaTabla()
    {
        TableLayout table = (TableLayout)activity.findViewById(R.id.stadistic_table);
        assertNotNull(table);
    }

    @Test
    public void ExistenciaFilas()
    {
        TableLayout table = (TableLayout)activity.findViewById(R.id.stadistic_table);
        assertNotNull(table);
        for(int i = 0;i<11;i++)
        {
            TableRow row = (TableRow) table.getChildAt(i);
            assertNotNull(table);
        }
    }

    @Test
    public void ContenidoTextViews()
    {
        TextView view = activity.findViewById(R.id.users);
        assertEquals("Nombre de Usuario",view.getText().toString());
        view = activity.findViewById(R.id.puestos);
        assertEquals("N°",view.getText().toString());
        view = activity.findViewById(R.id.velocidades);
        assertEquals("Score",view.getText().toString());
    }

    @Test
    public void ContenidoBotones()
    {
        Button btn = activity.findViewById(R.id.volver_btn);
        assertEquals("Volver a jugar",btn.getText().toString());
        btn = activity.findViewById(R.id.siguiente_btn);
        assertEquals("Siguiente",btn.getText().toString());
    }
  /*  @Test
    public void FuncionActualizarTabla()
    {
        activity.actualizar("Uusuario1,10\nusuario2,20\nusuario3,30\nusuario4,40\nusuario5,50\nusuario6,60\nusuario7,70\nusuario8,80\nusuario9,90\nusuario10,100\n");
        try{Thread.sleep(200);}catch (Exception e){e.printStackTrace();};
        TextView view = activity.findViewById(R.id.v1);
        assertEquals("10",view.getText().toString());
        view = activity.findViewById(R.id.v2);
        assertEquals("20",view.getText().toString());
        view = activity.findViewById(R.id.v3);
        assertEquals("30",view.getText().toString());
        view = activity.findViewById(R.id.v4);
        assertEquals("40",view.getText().toString());
        view = activity.findViewById(R.id.v5);
        assertEquals("50",view.getText().toString());
        view = activity.findViewById(R.id.v6);
        assertEquals("60",view.getText().toString());
        view = activity.findViewById(R.id.v7);
        assertEquals("70",view.getText().toString());
        view = activity.findViewById(R.id.v8);
        assertEquals("80",view.getText().toString());
        view = activity.findViewById(R.id.v9);
        assertEquals("90",view.getText().toString());
        view = activity.findViewById(R.id.v10);
        assertEquals("100",view.getText().toString());
        view = activity.findViewById(R.id.u1);
        assertEquals("usuario1",view.getText().toString());
        view = activity.findViewById(R.id.u2);
        assertEquals("usuario2",view.getText().toString());
        view = activity.findViewById(R.id.u3);
        assertEquals("usuario3",view.getText().toString());
        view = activity.findViewById(R.id.u4);
        assertEquals("usuario4",view.getText().toString());
        view = activity.findViewById(R.id.u5);
        assertEquals("usuario5",view.getText().toString());
        view = activity.findViewById(R.id.u6);
        assertEquals("usuario6",view.getText().toString());
        view = activity.findViewById(R.id.u7);
        assertEquals("usuario7",view.getText().toString());
        view = activity.findViewById(R.id.u8);
        assertEquals("usuario8",view.getText().toString());
        view = activity.findViewById(R.id.u9);
        assertEquals("usuario9",view.getText().toString());
        view = activity.findViewById(R.id.u10);
        assertEquals("usuario10",view.getText().toString());
    }*/

    @Test
    public void FuncionalidadBotonSiguiente()
    {
        assertNotNull(activity.findViewById(R.id.siguiente_btn));
        onView(withId(R.id.siguiente_btn)).perform(click());
        Activity activity2 = getInstrumentation().waitForMonitorWithTimeout(monitor,3000);
        assertNotNull(activity2);
        activity2.finish();
    }


    @After
    public void tearDown() throws Exception {
        activity=null;
    }
}