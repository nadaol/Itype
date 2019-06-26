package com.example.itype.TestIntegracion;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import com.example.itype.ControladorActiva;
import com.example.itype.R;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;
import java.util.concurrent.TimeUnit;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)

public class ControladorActivaIT {

    @Rule
    public IntentsTestRule <ControladorActiva> reglaActividad =
            new IntentsTestRule<>(ControladorActiva.class);

    @Test
    public void desplieguePerfil_vActiva() {    // PI1.1
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.comenzar_btn)).check(matches(notNullValue()));
        onView(withId(R.id.comenzar_btn)).check(matches(withText("Comenzar!")));
        //Clic
        onView(withId(R.id.comenzar_btn)).perform(click());
        //Espero 30 segundos
        try{Thread.sleep(TimeUnit.SECONDS.toMillis(30));}
        catch (Exception e){};
        //Nueva actividad
        onView(withId(R.id.stadistic_table)).check(matches(notNullValue()));
    }

    @Test
    public void despliegueMenuPrincipal_vActiva() { //PI1.2
        onView(withId(R.id.menuPrincipal_btn)).check(matches(notNullValue()));
        onView(withId(R.id.menuPrincipal_btn)).check(matches(withText("MENÚ PRINCIPAL")));
        onView(withId(R.id.menuPrincipal_btn)).perform(click());
        onView(withText("Se perdera el progreso de la partida")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).check(matches(withText("Si. Regresar")));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText("Iniciar juego")));
    }

}
