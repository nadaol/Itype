package com.example.itype;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)
public class ControladorMenuPrincipalTest {


    @Rule
    public ActivityTestRule<ControladorMenuPrincipal> reglaActMenuPrincipal =
            new ActivityTestRule<>(ControladorMenuPrincipal.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("com.example.itype", appContext.getPackageName());
    }
    @Test
    public void stringTituloExiste_vMenu() {
        onView(withId(R.id.textView_vMenuTitulo)).check(matches(withText("MENÚ PRINCIPAL")));
    }


    @Test
    public void botonIniciarJuegoExiste_vMenu(){
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText("Iniciar juego")));
    }

    @Test
    public void botonPerfilExiste_vMenu(){
        onView(withId(R.id.button_vMenuPerfil)).check(matches(withText("Perfil")));
    }

    @Test
    public void botonVolverExiste_vMenu(){
        onView(withId(R.id.button_vMenuSalir)).check(matches(withText("Salir")));
    }

    @Test
    public void botonIniciarJuegoTactil_vMenu(){
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(isClickable()));
    }

    @Test
    public void botonPerfilTactil_vMenu(){
        onView(withId(R.id.button_vMenuPerfil)).check(matches(isClickable()));
    }

    @Test
    public void botonVolverTactil_vMenu(){
        onView(withId(R.id.button_vMenuSalir)).check(matches(isClickable()));
    }

    /*@Test
    public void switchToFragment() {
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        //Probar que se inicia el menu principal
    }*/

}
