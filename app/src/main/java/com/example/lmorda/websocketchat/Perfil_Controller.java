package com.example.lmorda.websocketchat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
public class Perfil_Controller extends AppCompatActivity {

    private Button Salir;//boton para ir al inicio de sesion
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
        try {Usuario.updateData();}catch(Exception e){e.printStackTrace();}
        Volver = findViewById(R.id.volver_btn);
        Salir = findViewById(R.id.salir_btn);
        jugadas = findViewById(R.id.jugadas);
        VelProm = findViewById(R.id.VelProm);
        VelMax = findViewById(R.id.VelMax);
        UserName = findViewById(R.id.UserName);
        VelProm.setText(Usuario.getVelProm());
        VelMax.setText(Usuario.getVelMax());
        UserName.setText(Usuario.getName());
        jugadas.setText(Usuario.getJugadas());
        /*
                //cambio a la vista de seleccion de dificultad
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Selection_Controller.class);
                startActivity(intent);
            }});

             //cambio a la vista de inicio de sesion
        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Sesion_Controller.class);
                startActivity(intent);
            }});
*/
       }
    }
