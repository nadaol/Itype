package com.example.itype.TestUnitarios;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.test.rule.ActivityTestRule;

import com.example.itype.ControladorActiva;
import com.example.itype.R;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import java.util.concurrent.TimeUnit;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ControladorActivaTest {
    private final int time = 30;

    @Rule
    public ActivityTestRule<ControladorActiva> TestRule=new ActivityTestRule<ControladorActiva>(ControladorActiva.class);

    private ControladorActiva activity = null;

    @Before
    public void setUp() throws Exception {
        activity=TestRule.getActivity();
    }

    @Test
    public void Prueba1_ExistenciaBotones() { //PU5.1
        assertNotNull(activity.findViewById(R.id.comenzar_btn));
        assertNotNull(activity.findViewById(R.id.menuPrincipal_btn));
    }

    @Test
    public void Prueba2_ExistenciaTextViews() { //PU5.2
        assertNotNull(activity.findViewById(R.id.Entrada_Etext));
        assertNotNull(activity.findViewById(R.id.Tiempo_Tview));
        assertNotNull(activity.findViewById(R.id.velocidad_Tview));
        assertNotNull(activity.findViewById(R.id.Palabra_modelo));
        assertNotNull(activity.findViewById(R.id.tiempo));
        assertNotNull(activity.findViewById(R.id.tiempoTitle_Tview));
    }

    @Test
    public void Prueba3_ContenidoTextViews() { //PU5.3
        EditText entrada = (EditText) activity.findViewById(R.id.Entrada_Etext);
        TextView Tiempo = (TextView) activity.findViewById(R.id.Tiempo_Tview);
        TextView miVel = (TextView) activity.findViewById(R.id.velocidad_Tview);
        Button comenzar = (Button) activity.findViewById(R.id.comenzar_btn);
        assertEquals("", entrada.getText().toString());
        assertEquals(Integer.toString(time), Tiempo.getText().toString());
        assertEquals("0", miVel.getText().toString());
        assertEquals("Comenzar!", comenzar.getText().toString());
    }


    @Test
    public void Prueba4_Tiempo() { //PU5.4
        assertNotNull(activity.findViewById(R.id.comenzar_btn));
    }

    @Test
    public void Prueba5_finalizar() throws Exception { //PU5.5
        TextView Tiempo = (TextView) activity.findViewById(R.id.Tiempo_Tview);
        Button comenzar = (Button) activity.findViewById(R.id.comenzar_btn);
        onView(withId(comenzar.getId())).perform(click());
        TimeUnit.SECONDS.sleep(time);//espera que la prueba finalize
        assertEquals("0", Tiempo.getText().toString());
        activity = null;
    }

    @Test
    public void palabraMayusculas_vActiva() throws Exception { //PU5.6
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        boolean fin = false;
        TextView reloj = (TextView) TestRule.getActivity().findViewById(R.id.Tiempo_Tview);
        while (fin == false) {
            TextView textView = (TextView) TestRule.getActivity().findViewById(R.id.Palabra_modelo);
            String palabra = textView.getText().toString().toUpperCase();
            onView(withId(R.id.Entrada_Etext)).perform(clearText(), typeText(palabra), pressKey(66));
            if (reloj.getText().toString().equals("0")) {
                fin = true;
            }
        }
        activity = null;
    }

    @Test
    public void prueba_palabraBorrada() {          //PU5.7
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        TextView textView = (TextView) TestRule.getActivity().findViewById(R.id.Palabra_modelo);
        String palabra = textView.getText().toString();
        onView(withId(R.id.Entrada_Etext)).perform(clearText(), typeText(palabra), pressKey(66));
        onView(withId(R.id.Entrada_Etext)).check(matches(withText("")));
    }
}
