package com.example.itype;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Test;
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
public class ControladorInicioSesionTest {

    String usuarioRegistrado = "usuario";
    String contraRegistrado = "1234";

    @Rule
    public ActivityTestRule<ControladorInicioSesion> reglaActividad =
            new ActivityTestRule<>(ControladorInicioSesion.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("com.example.itype", appContext.getPackageName());
    }
    @Test
    public void stringUsuarioExiste_vInicio() {
        onView(withId(R.id.textView_vInicioUsuario)).check(matches(withText("Usuario")));
    }

    @Test
    public void botonSalirExiste_vInicio() {
        onView(withId(R.id.button_vInicioSalir)).check(matches(withText("Salir")));
    }

    @Test
    public void stringContrasenaExiste_vInicio() {
        onView(withId(R.id.textView_vInicioContrasena)).check(matches(withText("Contraseña")));
    }

    @Test
    public void botonIngresarExiste_vInicio(){
        onView(withId(R.id.button_vInicioIngresar)).check(matches(withText("Ingresar")));
    }

    @Test
    public void botonRegistrarExiste_vInicio(){
        onView(withId(R.id.button_vInicioRegistrar)).check(matches(withText("Registrarse")));
    }

    @Test
    public void botonIngresarTactil_vInicio(){
        onView(withId(R.id.button_vInicioIngresar)).check(matches(isClickable()));
    }

    @Test
    public void botonRegistrarTactil_vInicio(){
        onView(withId(R.id.button_vInicioSalir)).check(matches(isClickable()));
    }

    @Test
    public void botonSalirTactil_vInicio(){
        onView(withId(R.id.button_vInicioSalir)).check(matches(isClickable()));
    }


    @Test
    public void cajaTextoUsuarioEditable_vInicio(){
        onView(withId(R.id.editText_vInicioUsuario)).check(matches(supportsInputMethods()));
    }

    @Test
    public void cajaTextoContrasenaEditable_vInicio(){
        onView(withId(R.id.editText_vInicioUsuario)).check(matches(supportsInputMethods()));

    }

    @Test
    public void contrasenaPrueba_vInicio(){
        onView(withId(R.id.editText_vInicioUsuario)).check(matches(supportsInputMethods()));

    }
    @Test
    public void usuarioPrueba_vInicio(){
        onView(withId(R.id.editText_vInicioUsuario)).check(matches(supportsInputMethods()));

    }

    @Test
    public void ingresoCorrecto_VerificacionCorrecta_vInicio(){
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        onView(withText("Inicio correcto,Bienvenido "+usuarioRegistrado)).inRoot(withDecorView(not(is(reglaActividad.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void ingresoSinContra_MuestraMensaje_vInicio(){
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText("prueba12345"),closeSoftKeyboard());
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(""),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        onView(withText("Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros")).inRoot(withDecorView(not(is(reglaActividad.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void ingresoContraInvalida_MuestraMensaje_vInicio(){
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText("prueba12345"),closeSoftKeyboard());
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText("&"),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        onView(withText("Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros")).inRoot(withDecorView(not(is(reglaActividad.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void ingresoIncorrecto_ErrorContrasena_VerificacionCorrecta_vInicio(){
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText("1"),closeSoftKeyboard());
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        onView(withText("Usuario o contraseña incorrectos")).inRoot(withDecorView(not(is(reglaActividad.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    /*@Test
    public void switchToFragment() {
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        //Probar que se inicia el menu principal
    }*/

}

