package com.example.itype;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)

public class ControladorActivaUIIntegrationTest {

    @Rule
    public IntentsTestRule <ControladorActiva> reglaActividad =
            new IntentsTestRule<>(ControladorActiva.class);

    @Test
    public void desplieguePerfil_vSeleccionNivel() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.comenzar_btn)).check(matches(notNullValue()));
        onView(withId(R.id.comenzar_btn)).check(matches(withText("Comenzar!")));
        //Clic
        onView(withId(R.id.comenzar_btn)).perform(click());
        //Espero 30 segundos
        try{Thread.sleep(TimeUnit.SECONDS.toMillis(30));}
        catch (Exception e){};
        //Verifico cambio de boton
        onView(withId(R.id.comenzar_btn)).check(matches(withText("Siguiente")));
        //Clic
        onView(withId(R.id.comenzar_btn)).perform(click());
        //Nueva actividad
        onView(withId(R.id.stadistic_table)).check(matches(notNullValue()));
    }

    @Test
    public void despliegueMenuPrincipal_vSeleccionNivel() {
        onView(withId(R.id.salir_btn)).check(matches(notNullValue()));
        onView(withId(R.id.salir_btn)).check(matches(withText("MENÚ PRINCIPAL")));
        onView(withId(R.id.salir_btn)).perform(click());
        onView(withText("Se perderá el progreso de la partida")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button1)).check(matches(withText("Si. Regresar")));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText("Iniciar juego")));
    }

}
