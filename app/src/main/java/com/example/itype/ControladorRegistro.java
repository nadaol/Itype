package com.example.itype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ControladorRegistro extends Activity {

    EditText username_et, password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_registro);
        username_et =(EditText)findViewById(R.id.editText_vRegUsuario);
        password_et =(EditText)findViewById(R.id.editText_vRegContra);
    }

    public void userReg(View view) throws Exception {

            try {
                String log = Usuario.registrar(username_et.getText().toString(), password_et.getText().toString());
                Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

    }

    public void regresoInicioSesion (View view) {
        Intent i = new Intent(this, ControladorInicioSesion.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {

    }
}
