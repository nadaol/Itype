package com.example.itype.TestIntegracion;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import com.example.itype.ControladorSeleccionNivel;
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

public class ControladorSeleccionNivelIT {

    @Rule
    public IntentsTestRule <ControladorSeleccionNivel> reglaActividad =
            new IntentsTestRule<>(ControladorSeleccionNivel.class);

    @Test
    public void despliegueModoFacil_vSeleccionNivel() { //PI7.1
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vSelNivFacil)).check(matches(notNullValue()));
        onView(withId(R.id.button_vSelNivFacil)).check(matches(withText("Fácil")));
        //Clic
        onView(withId(R.id.button_vSelNivFacil)).perform(click());
        //Verifico menu principal
        onView(withId(R.id.VelocidadTitle_Tview)).check(matches(withText(("Velocidad:"))));
    }

    @Test
    public void despliegueModoIntermedio_vSeleccionNivel() { //PI7.2
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vSelNivMedio)).check(matches(notNullValue()));
        onView(withId(R.id.button_vSelNivMedio)).check(matches(withText("Medio")));
        //Clic
        onView(withId(R.id.button_vSelNivMedio)).perform(click());
        //Verifico menu principal
        onView(withId(R.id.VelocidadTitle_Tview)).check(matches(withText(("Velocidad:"))));
    }

    @Test
    public void despliegueModoDificil_vSeleccionNivel() { //PI7.3
        //Pruebo la existencia del boton Ingresar
        onView(withId(R.id.button_vSelNivDificil)).check(matches(notNullValue()));
        onView(withId(R.id.button_vSelNivDificil)).check(matches(withText("Difícil")));
        //Clic
        onView(withId(R.id.button_vSelNivDificil)).perform(click());
        //Verifico menu principal
        onView(withId(R.id.VelocidadTitle_Tview)).check(matches(withText(("Velocidad:"))));
    }
}
