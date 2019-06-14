package com.example.itype.IntegrationTests;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.rule.IntentsTestRule;

import com.example.itype.ControladorInicioSesion;
import com.example.itype.R;

import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)
public class ControladorInicioSesionIT {

    String usuarioRegistrado = "usuario";
    String contraRegistrado = "1234";

    @Rule
    public IntentsTestRule <ControladorInicioSesion> reglaActividad =
            new IntentsTestRule<>(ControladorInicioSesion.class);

    @Test
    public void despliegueMenuPrincipal_vInicioSesion() {
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vInicioIngresar)).check(matches(notNullValue()));
        onView(withId(R.id.button_vInicioIngresar)).check(matches(withText("Ingresar")));
        //Ingreso credenciales
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText(usuarioRegistrado),closeSoftKeyboard());
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText(contraRegistrado),closeSoftKeyboard());
        //Clic
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        //Verifico menu principal
        onView(withId(R.id.textView_vMenuTitulo)).check(matches(withText(("MENÚ PRINCIPAL"))));
    }

    @Test
    public void despliegueRegistro_vInicioSesion() {
        onView(withId(R.id.button_vInicioRegistrar)).check(matches(notNullValue()));
        onView(withId(R.id.button_vInicioRegistrar)).check(matches(withText("Registrarse")));
        onView(withId(R.id.button_vInicioRegistrar)).perform(click());
        onView(withId(R.id.textView_vRegBienvenido)).check(matches(withText(("¡Bienvenido a IType!"))));
    }

    @Test
    public void registro_vInicioSesion() {
        onView(withId(R.id.button_vInicioRegistrar)).check(matches(notNullValue()));
        onView(withId(R.id.button_vInicioRegistrar)).check(matches(withText("Registrarse")));
        //Ir a registrar
        onView(withId(R.id.button_vInicioRegistrar)).perform(click());
        //Completar registro
        onView(withId(R.id.editText_vRegUsuario)).perform(clearText(),typeText("useruser123"),closeSoftKeyboard());
        onView(withId(R.id.editText_vRegContra)).perform(clearText(),typeText("user123"),closeSoftKeyboard());
        //Registrar
        onView(withId(R.id.button_vRegRegistrar)).perform(click());
        //Volver a inicio
        onView(withId(R.id.button_vRegInicio)).perform(click());
    }

    @Test
    public void registroInicio_vInicioSesion() {
        onView(withId(R.id.button_vInicioRegistrar)).check(matches(notNullValue()));
        onView(withId(R.id.button_vInicioRegistrar)).check(matches(withText("Registrarse")));
        //Ir a registrar
        onView(withId(R.id.button_vInicioRegistrar)).perform(click());
        //Completar registro
        onView(withId(R.id.editText_vRegUsuario)).perform(clearText(),typeText("useruuser123"),closeSoftKeyboard());
        onView(withId(R.id.editText_vRegContra)).perform(clearText(),typeText("user123"),closeSoftKeyboard());
        //Registrar
        onView(withId(R.id.button_vRegRegistrar)).perform(click());
        //Volver a inicio
        onView(withId(R.id.button_vRegInicio)).perform(click());
        //Completar con credenciales creadas
        onView(withId(R.id.editText_vInicioUsuario)).perform(clearText(),typeText("useruuser123"),closeSoftKeyboard());
        onView(withId(R.id.editText_vInicioContra)).perform(clearText(),typeText("user123"),closeSoftKeyboard());
        //Ingresar
        onView(withId(R.id.button_vInicioIngresar)).perform(click());
        //Verifico que estoy en Menu principal
        onView(withId(R.id.textView_vMenuTitulo)).check(matches(withText(("MENÚ PRINCIPAL"))));
    }
}
