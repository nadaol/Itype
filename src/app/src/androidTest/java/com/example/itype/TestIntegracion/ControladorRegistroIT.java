package com.example.itype.TestIntegracion;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import com.example.itype.ControladorRegistro;
import com.example.itype.R;
import org.junit.Test;
import org.junit.Rule;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)
public class ControladorRegistroIT{

    String usuarioRegistrado = "usuario";
    String contraRegistrado = "1234";

    @Rule
    public IntentsTestRule <ControladorRegistro> reglaActividad =
            new IntentsTestRule<>(ControladorRegistro.class);

    @Test
    public void despliegueInicioSesion_vRegistro() { //PI6.1
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vRegInicio)).check(matches(notNullValue()));
        //Clic a Ingresar
        onView(withId(R.id.button_vRegInicio)).check(matches(withText("Volver")));
        onView(withId(R.id.button_vRegInicio)).perform(click());
        //Verifico que estoy en inicio de sesion
        onView(withId(R.id.imageView4)).check(matches(notNullValue()));
    }

    @Test
    public void registroVistaInicioRegistro_vRegistro() { //PI6.2
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
