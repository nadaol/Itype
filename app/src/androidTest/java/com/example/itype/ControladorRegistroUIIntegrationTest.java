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
public class ControladorRegistroUIIntegrationTest {

    String usuarioRegistrado = "usuario";
    String contraRegistrado = "1234";

    @Rule
    public IntentsTestRule <ControladorRegistro> reglaActividad =
            new IntentsTestRule<>(ControladorRegistro.class);

    @Test
    public void despliegueInicioSesion_vRegistro() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vRegInicio)).check(matches(notNullValue()));
        //Clic a Ingresar
        onView(withId(R.id.button_vRegInicio)).check(matches(withText("Volver")));
        onView(withId(R.id.button_vRegInicio)).perform(click());
        //Verifico que estoy en inicio de sesion
        onView(withId(R.id.imageView4)).check(matches(notNullValue()));
    }

    @Test
    public void registroVistaInicioRegistro_vRegistro() {
        onView(withId(R.id.textView_vRegBienvenido)).check(matches(withText(("¡Bienvenido a IType!"))));
        //Clic a Ingresar
        onView(withId(R.id.button_vRegInicio)).perform(click());
        //Verifico que estoy en inicio de sesion
        onView(withId(R.id.imageView4)).check(matches(notNullValue()));
        //Clic a Registrar
        onView(withId(R.id.button_vInicioRegistrar)).perform(click());
        //Verifico que volvi a Registro
        onView(withId(R.id.button_vRegRegistrar)).check(matches(notNullValue()));
    }
}
