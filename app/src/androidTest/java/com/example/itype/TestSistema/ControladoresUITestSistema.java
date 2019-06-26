package com.example.itype.TestSistema;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.InstrumentationRegistry;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.IdlingPolicies;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.example.itype.ControladorActiva;
import com.example.itype.ControladorInicioSesion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;
import com.example.itype.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
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

public class ControladoresUITestSistema {

    String usuarioRegistrado = "usuario";
    String contraRegistrado = "1234";

    @Rule
    public ActivityTestRule<ControladorInicioSesion> reglaActInicioSesion = new ActivityTestRule<>(ControladorInicioSesion.class);

    @Test
    public void prueba_jugadaVacia() {           // PS2.1
        // Realizo la prueba entera, no escribo palabras, vuelvo a menu principal
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());//Ingreso credenciales
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());//Clic
        onView(withId(R.id.button_vMenuInicioJuego)).perform(click());//Selecciono opcion para jugar en menu principal y clic
        onView(withId(R.id.button_vSelNivFacil)).perform(click()); // clic en opcion Facil
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        //Espero 30 segundos
        try{Thread.sleep(TimeUnit.SECONDS.toMillis(30));}
        catch (Exception e){};
        onView(withId(R.id.siguiente_btn)).perform(click());
        onView(withId(R.id.volver_btn)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText(("Iniciar juego"))));
    }


    @Test
    public void recorridaSalir2() {          // PS2.2
        // TR2.2.2
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());//Ingreso credenciales
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());//Clic
        onView(withId(R.id.button_vMenuSalir)).perform(click());//Selecciono opcion para jugar en menu principal y clic
        onView(withId(R.id.imageView4)).check(matches(notNullValue()));
    }

    @Test
    public void recorridaInterrumpirPrueba() {         // PS2.3
        // TR2.3
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());//Ingreso credenciales
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());//Clic
        onView(withId(R.id.button_vMenuInicioJuego)).perform(click());//Selecciono opcion para jugar en menu principal y clic
        onView(withId(R.id.button_vSelNivFacil)).perform(click()); // clic en opcion Facil
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        onView(withId(R.id.menuPrincipal_btn)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText("Iniciar juego")));
    }

    @Test
    public void verificacionPerfil(){               // PS2.4
        //TR2.4
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());//Ingreso credenciales
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());//Clic
        onView(withId(R.id.button_vMenuInicioJuego)).perform(click());//Selecciono opcion para jugar en menu principal y clic
        onView(withId(R.id.button_vSelNivFacil)).perform(click()); // clic en opcion Facil
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada

        //Espero 30 segundos
        try{Thread.sleep(TimeUnit.SECONDS.toMillis(35));}
        catch (Exception e){};

        onView(withId(R.id.siguiente_btn)).perform(click());
        onView(withId(R.id.UserName)).check(matches(withText(usuarioRegistrado)));

    }

    @Test
    public void Respuesta_max3Seg(){                // PS2.5
        //TRN1.1-TRN1.3 TESTEO DE RESPUESTA MENOR A 3 SEG
        IdlingPolicies.setMasterPolicyTimeout(3000, TimeUnit.MILLISECONDS);
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());//Ingreso credenciales
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());//Clic
        onView(withId(R.id.button_vMenuPerfil)).perform(click());
        onView(withId(R.id.volver_btn)).perform(click());
        onView(withId(R.id.button_vMenuSalir)).perform(click());
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());//Ingreso credenciales
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());//Clic
        onView(withId(R.id.button_vMenuInicioJuego)).perform(click());//Selecciono opcion para jugar en menu principal y clic
        onView(withId(R.id.button_vSelNivMedio)).perform(click()); // clic en opcion medio
        onView(withId(R.id.menuPrincipal_btn)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).perform(click());//Selecciono opcion para jugar en menu principal y clic
        onView(withId(R.id.button_vSelNivDificil)).perform(click()); // clic en opcion dificil
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        onView(withId(R.id.menuPrincipal_btn)).perform(click());
        onView(withText("Si. Regresar")).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).perform(click());//Selecciono opcion para jugar en menu principal y clic
        onView(withId(R.id.button_vSelNivFacil)).perform(click()); // clic en opcion facil
        onView(withId(R.id.comenzar_btn)).perform(click());
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.siguiente_btn)).perform(click());
        onView(withId(R.id.volver_btn)).perform(click());
    }

    @Test
    public void pruebaConexion20() {     //PS2.6 -> PRUEBA RN1.2
        for(int i = 0; i < 20; i++){
            onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());//Ingreso credenciales
            onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
            onView(withId(R.id.button_vInicioIngresar)).perform(click());//Clic
            onView(withId(R.id.button_vMenuSalir)).perform(click());// Vuelo al inicio
        }
    }
}
