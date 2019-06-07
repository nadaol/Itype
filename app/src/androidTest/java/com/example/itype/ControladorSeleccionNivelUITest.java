package com.example.itype;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

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
import static org.junit.Assert.*;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)
public class ControladorSeleccionNivelUITest {

    @Rule
    public ActivityTestRule<ControladorSeleccionNivel> reglaActividad =
            new ActivityTestRule<>(ControladorSeleccionNivel.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("com.example.itype", appContext.getPackageName());
    }
    @Test
    public void stringTituloExiste_vSelNiv() {
        onView(withId(R.id.textView_vSelNivTexto)).check(matches(withText("Seleccione la dificultad del juego:")));
    }

    @Test
    public void botonFacilExiste_vSelNiv(){
        onView(withId(R.id.button_vSelNivFacil)).check(matches(withText("Fácil")));
    }

    @Test
    public void botonMedioExiste_vSelNiv(){
        onView(withId(R.id.button_vSelNivMedio)).check(matches(withText("Medio")));
    }

    @Test
    public void botonDificilExiste_vSelNiv(){
        onView(withId(R.id.button_vSelNivDificil)).check(matches(withText("Difícil")));
    }

    @Test
    public void botonVolverExiste_vSelNiv(){
        onView(withId(R.id.button_vSelNivSalir)).check(matches(withText("Volver")));
    }

    @Test
    public void botonFacilTactil_vInicio(){
        onView(withId(R.id.button_vSelNivFacil)).check(matches(isClickable()));
    }

    @Test
    public void botonMedioTactil_vInicio(){
        onView(withId(R.id.button_vSelNivMedio)).check(matches(isClickable()));
    }
    @Test
    public void botonDificilTactil_vInicio(){
        onView(withId(R.id.button_vSelNivDificil)).check(matches(isClickable()));
    }

    @Test
    public void botonFacilVolver_vInicio(){
        onView(withId(R.id.button_vSelNivSalir)).check(matches(isClickable()));
    }

    /*@Test
    public void switchToFragment() {
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        //Probar que se inicia el menu principal
    }*/

}

