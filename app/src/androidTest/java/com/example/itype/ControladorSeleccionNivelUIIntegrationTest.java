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

public class ControladorSeleccionNivelUIIntegrationTest {

    @Rule
    public IntentsTestRule <ControladorSeleccionNivel> reglaActividad =
            new IntentsTestRule<>(ControladorSeleccionNivel.class);

    @Test
    public void despliegueModoFacil_vSeleccionNivel() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vSelNivFacil)).check(matches(notNullValue()));
        onView(withId(R.id.button_vSelNivFacil)).check(matches(withText("Fácil")));
        //Clic
        onView(withId(R.id.button_vSelNivFacil)).perform(click());
        //Verifico menu principal
        onView(withId(R.id.VelocidadTitle_Tview)).check(matches(withText(("Velocidad:"))));
    }

    @Test
    public void despliegueModoIntermedio_vSeleccionNivel() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vSelNivMedio)).check(matches(notNullValue()));
        onView(withId(R.id.button_vSelNivMedio)).check(matches(withText("Medio")));
        //Clic
        onView(withId(R.id.button_vSelNivMedio)).perform(click());
        //Verifico menu principal
        onView(withId(R.id.VelocidadTitle_Tview)).check(matches(withText(("Velocidad:"))));
    }

    @Test
    public void despliegueModoDificil_vSeleccionNivel() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vSelNivDificil)).check(matches(notNullValue()));
        onView(withId(R.id.button_vSelNivDificil)).check(matches(withText("Difícil")));
        //Clic
        onView(withId(R.id.button_vSelNivDificil)).perform(click());
        //Verifico menu principal
        onView(withId(R.id.VelocidadTitle_Tview)).check(matches(withText(("Velocidad:"))));
    }
}
