package com.example.itype.TestSistema;

import android.widget.TextView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.itype.ControladorActiva;
import com.example.itype.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

// @RunWith is required only if you use a mix of JUnit3 and JUnit4.
@RunWith(AndroidJUnit4.class)


public class ControladorActivaTestSistema {

    String usuarioRegistrado = "usuario";
    String contraRegistrado = "1234";


    @Rule
    public ActivityTestRule<ControladorActiva> reglaActActiva = new ActivityTestRule<>(ControladorActiva.class);

    @Test
    public void prueba_jugadaPalabra() {          // Realizo la prueba entera, no escribo palabras, vuelvo a menu principal
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        //Espero 5 segundos
        try{Thread.sleep(TimeUnit.SECONDS.toMillis(5));}
        catch (Exception e){};
        TextView textView = (TextView) reglaActActiva.getActivity().findViewById(R.id.Palabra_modelo);
        String palabra = textView.getText().toString();
        System.out.println("****************PALABRA: " + palabra);
        onView(withId(R.id.Entrada_Etext)).perform(clearText(),typeText(palabra), pressKey(66));
    }

    @Test
    public void prueba_jugadaPerfecta() {          // Realizo la prueba entera, no escribo palabras, vuelvo a menu principal
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        boolean fin = false;
        TextView reloj = (TextView) reglaActActiva.getActivity().findViewById(R.id.Tiempo_Tview);
        while (fin == false) {
            TextView textView = (TextView) reglaActActiva.getActivity().findViewById(R.id.Palabra_modelo);
            String palabra = textView.getText().toString();
            onView(withId(R.id.Entrada_Etext)).perform(clearText(), typeText(palabra), pressKey(66));
            if(reloj.getText().toString().equals("0")) {
                fin = true;
            }
        }
        try{Thread.sleep(TimeUnit.SECONDS.toMillis(5));}
        catch (Exception e){};
        onView(withId(R.id.siguiente_btn)).perform(click());
        onView(withId(R.id.volver_btn)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText(("Iniciar juego"))));
    }

    @Test
    public void prueba_jugadaPerfectaMayuscula() {          // TR1.2
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        boolean fin = false;
        TextView reloj = (TextView) reglaActActiva.getActivity().findViewById(R.id.Tiempo_Tview);
        while (fin == false) {
            TextView textView = (TextView) reglaActActiva.getActivity().findViewById(R.id.Palabra_modelo);
            String palabra = textView.getText().toString().toUpperCase();
            onView(withId(R.id.Entrada_Etext)).perform(clearText(), typeText(palabra), pressKey(66));
            if(reloj.getText().toString().equals("0")) {
                fin = true;
            }
        }
        try{Thread.sleep(TimeUnit.SECONDS.toMillis(5));}
        catch (Exception e){};
        onView(withId(R.id.siguiente_btn)).perform(click());
        onView(withId(R.id.volver_btn)).perform(click());
        onView(withId(R.id.button_vMenuInicioJuego)).check(matches(withText(("Iniciar juego"))));
    }

    @Test
    public void prueba_palabraBorrada() {          // TR1.3
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
            TextView textView = (TextView) reglaActActiva.getActivity().findViewById(R.id.Palabra_modelo);
            String palabra = textView.getText().toString();
            onView(withId(R.id.Entrada_Etext)).perform(clearText(), typeText(palabra), pressKey(66));
            onView(withId(R.id.Entrada_Etext)).check(matches(withText("")));
    }

    @Test
    public void prueba_cantPalabrasModelo() {          // TR2.5
        boolean espacio = false;
        onView(withId(R.id.comenzar_btn)).perform(click()); // clic en iniciar la jugada
        boolean esdoble = false;
        TextView textView = (TextView) reglaActActiva.getActivity().findViewById(R.id.Palabra_modelo);
        String palabra = textView.getText().toString();
        espacio = palabra.contains(" ");
        onView(withId(R.id.Entrada_Etext)).perform(clearText(), typeText(palabra), pressKey(66));
        assertEquals(esdoble, false);
    }


}

