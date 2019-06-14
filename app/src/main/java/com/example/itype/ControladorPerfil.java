package com.example.itype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ControladorPerfil extends AppCompatActivity {

    private Button salir;//boton para ir al inicio de sesion
    private Button volver;//boton para ir a seleccion de dificultad(nuevo juego)
    private TextView username;
    private TextView jugadas;
    private TextView velProm;
    private TextView velMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_perfil);
        //Obtiene nueva informacion de usuario
        try {Usuario.actualizarInfo();}catch(Exception e){e.printStackTrace();}
        volver = findViewById(R.id.volver_btn);
        salir = findViewById(R.id.salir_btn);
        jugadas = findViewById(R.id.jugadas);
        velProm = findViewById(R.id.VelProm);
        velMax = findViewById(R.id.VelMax);
        username = findViewById(R.id.UserName);
        velProm.setText(Usuario.getVelProm());
        velMax.setText(Usuario.getVelMax());
        username.setText(Usuario.getName());
        jugadas.setText(Usuario.getJugadas());
       }

    public void irMenuPrincipal(View view)
    {
        Intent i = new Intent(this, ControladorMenuPrincipal.class);
        startActivity(i);
    }

    public void salir(View view)
    {
        Intent i = new Intent(this, ControladorInicioSesion.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
    }
}
