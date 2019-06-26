package com.example.itype.TestUnitarios;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.itype.ControladorSeleccionNivel;
import com.example.itype.R;

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
public class ControladorSeleccionNivelTest {

    @Rule
    public ActivityTestRule<ControladorSeleccionNivel> reglaActividad =
            new ActivityTestRule<>(ControladorSeleccionNivel.class);

    @Test
    public void useAppContext() { //PU4.1
        // Context of the app under test.
        Context appContext = ApplicationProvider.getApplicationContext();
        assertEquals("com.example.itype", appContext.getPackageName());
    }
    @Test
    public void stringTituloExiste_vSelNiv() { //PU4.2
        onView(ViewMatchers.withId(R.id.textView_vSelNivTexto)).check(matches(withText("Seleccione la dificultad del juego:")));
    }

    @Test
    public void botonFacilExiste_vSelNiv(){ //PU4.3
        onView(withId(R.id.button_vSelNivFacil)).check(matches(withText("Fácil")));
    }

    @Test
    public void botonMedioExiste_vSelNiv(){ //PU4.4
        onView(withId(R.id.button_vSelNivMedio)).check(matches(withText("Medio")));
    }

    @Test
    public void botonDificilExiste_vSelNiv(){ //PU4.5
        onView(withId(R.id.button_vSelNivDificil)).check(matches(withText("Difícil")));
    }

    @Test
    public void botonVolverExiste_vSelNiv(){ //PU4.6
        onView(withId(R.id.button_vSelNivSalir)).check(matches(withText("Volver")));
    }

    @Test
    public void botonFacilTactil_vInicio(){ //PU4.7
        onView(withId(R.id.button_vSelNivFacil)).check(matches(isClickable()));
    }

    @Test
    public void botonMedioTactil_vInicio(){ //PU4.8
        onView(withId(R.id.button_vSelNivMedio)).check(matches(isClickable()));
    }
    @Test
    public void botonDificilTactil_vInicio(){ //PU4.9
        onView(withId(R.id.button_vSelNivDificil)).check(matches(isClickable()));
    }

    @Test
    public void botonFacilVolver_vInicio(){ //PU4.10
        onView(withId(R.id.button_vSelNivSalir)).check(matches(isClickable()));
    }



}
