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

public class ControladorMenuPrincipalIntegrationTest {

    String usuarioRegistrado = "usuario";
    String contraRegistrado = "1234";

    @Rule
    public IntentsTestRule <ControladorMenuPrincipal> reglaActividad =
            new IntentsTestRule<>(ControladorMenuPrincipal.class);

    @Test
    public void despliegueSeleccionNivel_vMenuPrincipal() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(notNullValue()));
        //Clic a Ingresar
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText("Iniciar juego")));
        onView(withId(R.id.button_vMenuInicioJuego)).perform(click());
        //Verifico que estoy en inicio de sesion
        onView(withId(R.id.textView_vSelNivTexto)).check(matches(withText("Seleccione la dificultad del juego:")));
    }

    @Test
    public void menuSeleccionMenu_vMenuPrincipal() {
        onView(withId(R.id.button_vMenuInicioJuego)).perform(click());
        onView(withId(R.id.textView_vSelNivTexto)).check(matches(withText("Seleccione la dificultad del juego:")));
        onView(withId(R.id.button_vSelNivSalir)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText("Iniciar juego")));

    }

    @Test
    public void desplieguePerfil_vMenuPrincipal() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vMenuPerfil)).check(matches(notNullValue()));
        //Clic a Ingresar
        onView(withId(R.id.button_vMenuPerfil)).check(matches(withText("Perfil")));
        onView(withId(R.id.button_vMenuPerfil)).perform(click());
        //Verifico que estoy en inicio de sesion
        onView(withId(R.id.txtVelProm)).check(matches(withText("Velocidad Promedio :")));
    }

    @Test
    public void menuPerfilMenu_vMenuPrincipal() {
        onView(withId(R.id.button_vMenuPerfil)).perform(click());
        onView(withId(R.id.txtVelProm)).check(matches(withText("Velocidad Promedio :")));
        onView(withId(R.id.volver_btn)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText("Iniciar juego")));
    }

    @Test
    public void despliegueInicioSesion_vMenuPrincipal() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vMenuSalir)).check(matches(notNullValue()));
        //Clic a Ingresar
        onView(withId(R.id.button_vMenuSalir)).check(matches(withText("Salir")));
        onView(withId(R.id.button_vMenuSalir)).perform(click());
        //Verifico que estoy en inicio de sesion
        onView(withId(R.id.imageView4)).check(matches(notNullValue()));
    }

    @Test
    public void menuInicioSesionMenu_vMenuPrincipal() {
        onView(withId(R.id.button_vMenuSalir)).perform(click());
        onView(withId(R.id.imageView4)).check(matches(notNullValue()));
        //Ingreso credenciales
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        //Clic
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        //Verifico menu principal
        onView(withId(R.id.textView_vMenuTitulo)).check(matches(withText(("MENÚ PRINCIPAL"))));    }

}
