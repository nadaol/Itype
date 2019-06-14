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

import java.util.concurrent.ExecutionException;

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
public class ControladorRegistroUITest {

    String usuarioExistente = "usuario";
    String contraExistente= "1234";

    @Rule
    public ActivityTestRule<ControladorRegistro> reglaActividad =
            new ActivityTestRule<>(ControladorRegistro.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("com.example.itype", appContext.getPackageName());
    }
    @Test
    public void stringUsuarioExiste_vReg() {
        onView(withId(R.id.textView_vRegUsuario)).check(matches(withText("Usuario")));
    }

    @Test
    public void stringContrasenaExiste() {
        onView(withId(R.id.textView_vRegContra)).check(matches(withText("Contraseña")));
    }

    @Test
    public void stringBienvenidaExiste_vReg() {
        onView(withId(R.id.textView_vRegBienvenido)).check(matches(withText("¡Bienvenido a IType!")));
    }

    @Test
    public void stringInstruccionesRegExiste_vReg() {
        onView(withId(R.id.textView_vRegIngreseUC)).check(matches(withText("Ingrese un nuevo usuario y contraseña para poder acceder a la aplicación")));
    }

    @Test
    public void botonVolverExiste_vReg(){
        onView(withId(R.id.button_vRegInicio)).check(matches(withText("Volver")));
    }

    @Test
    public void botonRegistrarExiste_vReg(){
        onView(withId(R.id.button_vRegRegistrar)).check(matches(withText("Registrar")));
    }

    @Test
    public void botonVolverTactil_vReg(){
        onView(withId(R.id.button_vRegInicio)).check(matches(isClickable()));
    }

    @Test
    public void botonRegistrarTactil_vReg(){
        onView(withId(R.id.button_vRegRegistrar)).check(matches(isClickable()));
    }


    @Test
    public void cajaTextoUsuarioEditable_vReg(){
        onView(withId(R.id.editText_vRegUsuario)).check(matches(supportsInputMethods()));
    }

    @Test
    public void cajaTextoContrasenaEditable_vReg(){
        onView(withId(R.id.editText_vRegContra)).check(matches(supportsInputMethods()));

    }

    @Test
    public void contrasenaPrueba_vReg(){
        onView(withId(R.id.editText_vRegUsuario)).check(matches(supportsInputMethods()));

    }
    @Test
    public void usuarioPrueba_vReg(){
        onView(withId(R.id.editText_vRegContra)).check(matches(supportsInputMethods()));

    }

    @Test
    public void registroCorrecto_VerificacionCorrecta_vReg() throws ExecutionException, InterruptedException {
        Http_Post request = new Http_Post();//nueva conexión post para registrar usuario
        String result = request.execute("http://itype.ml/delete.php","user","prueba12341231").get();
        onView(withId(R.id.editText_vRegUsuario)).perform(clearText(),typeText("prueba12341231"),closeSoftKeyboard());
        onView(withId(R.id.editText_vRegContra)).perform(clearText(),typeText("contra123414"),closeSoftKeyboard());
        onView(withId(R.id.button_vRegRegistrar)).perform(click());
        onView(withText("Registro correcto. Inicie nuevamente")).inRoot(withDecorView(not(is(reglaActividad.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void registroSinContra_MuestraMensaje_vReg(){
        onView(withId(R.id.editText_vRegUsuario)).perform(clearText(),typeText("prueba12345990"),closeSoftKeyboard());
        onView(withId(R.id.editText_vRegContra)).perform(clearText(),typeText(""),closeSoftKeyboard());
        onView(withId(R.id.button_vRegRegistrar)).perform(click());
        onView(withText("Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros")).inRoot(withDecorView(not(is(reglaActividad.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void registroContraInvalida_MuestraMensaje_vReg(){
        onView(withId(R.id.editText_vRegUsuario)).perform(clearText(),typeText("prueba12340805"),closeSoftKeyboard());
        onView(withId(R.id.editText_vRegContra)).perform(clearText(),typeText("&"),closeSoftKeyboard());
        onView(withId(R.id.button_vRegRegistrar)).perform(click());
        onView(withText("Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros")).inRoot(withDecorView(not(is(reglaActividad.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void registroIncorrecto_UsuarioExistente_VerificacionCorrecta_vReg(){
        onView(withId(R.id.editText_vRegUsuario)).perform(clearText(),typeText(usuarioExistente),closeSoftKeyboard());
        onView(withId(R.id.editText_vRegContra)).perform(clearText(),typeText(contraExistente),closeSoftKeyboard());
        onView(withId(R.id.button_vRegRegistrar)).perform(click());
        onView(withText("El usuario - " + usuarioExistente + " - ya existe")).inRoot(withDecorView(not(is(reglaActividad.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }


    /*@Test
    public void switchToFragment() {
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        //Probar que se inicia el menu principal
    }*/

}
