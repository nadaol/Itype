package com.example.itype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class  ControladorInicioSesion extends Activity {

    EditText Username_et, Password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_iniciosesion);
        Username_et=(EditText)findViewById(R.id.editText_vInicioUsuario);
        Password_et=(EditText)findViewById(R.id.editText_vInicioContra);
    }

    public void userLogin(View view)throws Exception
    {
        try {
            String log = Usuario.login(Username_et.getText().toString(), Password_et.getText().toString());
            Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();
            if(log.contains("Inicio correcto"))
            {
                irMenuPrincipal();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void borrarUsuario (String nombre){
        try{
            String log = Usuario.quitar(nombre);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void irRegistro (View view) {
        Intent i = new Intent(this, ControladorRegistro.class);
        startActivity(i);
    }

    public void irMenuPrincipal () {
        // Create an Intent to start the second activity
        Intent randomIntent = new Intent(this, ControladorMenuPrincipal.class);
        startActivity(randomIntent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
