package com.example.itype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ControladorRegistro extends Activity {

    EditText Username_et, Password_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_registro);
        Username_et=(EditText)findViewById(R.id.editText_vRegUsuario);
        Password_et=(EditText)findViewById(R.id.editText_vRegContra);
    }

    public void userReg(View view) throws Exception {

        if( Usuario.validacion_entrada(Username_et.getText().toString())==false || Usuario.validacion_entrada(Password_et.getText().toString())==false)
        {
            Toast.makeText(this, "Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros", Toast.LENGTH_LONG).show();
            return;
        }
            try {
                String log = Usuario.registrar(Username_et.getText().toString(), Password_et.getText().toString());
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
