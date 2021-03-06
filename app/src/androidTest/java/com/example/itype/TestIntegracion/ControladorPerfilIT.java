package com.example.itype.TestIntegracion;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.example.itype.ControladorPerfil;
import com.example.itype.R;

import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;


// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)
public class ControladorPerfilIT{

    @Rule
    public IntentsTestRule <ControladorPerfil> reglaActividad = new IntentsTestRule<>(ControladorPerfil.class);

    @Test
    public void despliegueMenuPrincipal_vPerfil() { //PI4.1
        //Pruebo la existencia del boton menu principal
        onView(withId(R.id.volver_btn)).check(matches(notNullValue()));
        onView(withId(R.id.volver_btn)).check(matches(withText("MENÚ PRINCIPAL")));
        //Click
        onView(withId(R.id.volver_btn)).perform(click());
        //Verifico presentacion de menu principal
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText(("Iniciar juego"))));
    }

}
