package com.example.menuprincipal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ControladorMenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_menuprincipal);
    }

    public void regresoInicio (View view) {

        //Intent i = new Intent(this, ControladorInicioSesion.class);
        //startActivity(i);
    }

    public void irSeleccionNivel (View view) {

        //Intent i = new Intent(this, ControladorSeleccionNivel.class);
        //startActivity(i);
    }

    public void irPerfil (View view) {

        //Intent i = new Intent(this, ControladorPerfil.class);
        //startActivity(i);
    }
}
