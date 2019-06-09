package com.example.itype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ControladorSeleccionNivel extends AppCompatActivity implements View.OnClickListener  {

    private Button b1,b2,b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_seleccionnivel);

        b1=(Button)findViewById(R.id.button_vSelNivFacil);
        b1.setOnClickListener(this);
        b2=(Button)findViewById(R.id.button_vSelNivMedio);
        b2.setOnClickListener(this);
        b3=(Button)findViewById(R.id.button_vSelNivDificil);
        b3.setOnClickListener(this);
    }

    public void iniciarJuego (String dificultad_juego){
        Intent i = new Intent(this,ControladorActiva.class);
        i.putExtra("dificultad",dificultad_juego);
        startActivity(i);
    }

    public void regresoMenu (View view) {
        Intent i = new Intent(this, ControladorMenuPrincipal.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.button_vSelNivFacil:
                Toast.makeText(getBaseContext(), "Dificultad FÁCIL", Toast.LENGTH_LONG).show();
                iniciarJuego ("Facil");
                break;

            case R.id.button_vSelNivMedio:
                Toast.makeText(getBaseContext(), "Dificultad MEDIA", Toast.LENGTH_LONG).show();
                iniciarJuego ("Media");
                break;

            case R.id.button_vSelNivDificil:
                Toast.makeText(getBaseContext(), "Dificultad DIFÍCIL", Toast.LENGTH_LONG).show();
                iniciarJuego ("Dificil");
                break;
        }

    }

    @Override
    public void onBackPressed() {

    }
}
