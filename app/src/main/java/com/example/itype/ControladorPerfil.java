package com.example.itype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ControladorPerfil extends AppCompatActivity {

    private Button Volver;//boton para ir a seleccion de dificultad(nuevo juego)
    private TextView UserName;
    private TextView jugadas;
    private TextView VelProm;
    private TextView VelMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_perfil);
        //Obtiene nueva informacion de usuario
        try {Usuario.actualizarInfo();}catch(Exception e){e.printStackTrace();}
        Volver = findViewById(R.id.volver_btn);
        jugadas = findViewById(R.id.jugadas);
        VelProm = findViewById(R.id.VelProm);
        VelMax = findViewById(R.id.VelMax);
        UserName = findViewById(R.id.UserName);
        VelProm.setText(Usuario.getVelProm());
        VelMax.setText(Usuario.getVelMax());
        UserName.setText(Usuario.getName());
        jugadas.setText(Usuario.getJugadas());
       }

    public void MenuPrincipal(View view)
    {
        Intent i = new Intent(this, ControladorMenuPrincipal.class);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed() {

    }

    }
