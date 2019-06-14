package com.example.itype;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ControladorMenuPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_menuprincipal);
    }

    public void regresoInicio (View view) {
        Intent i = new Intent(this, ControladorInicioSesion.class);
        startActivity(i);
        finish();
    }

    public void irSeleccionNivel (View view) {

        Intent i = new Intent(this, ControladorSeleccionNivel.class);
        startActivity(i);
        finish();
    }

    public void irPerfil (View view) {

        Intent i = new Intent(this, ControladorPerfil.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
