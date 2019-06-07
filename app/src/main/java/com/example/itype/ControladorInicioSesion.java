package com.example.itype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ControladorInicioSesion extends Activity {

    EditText Username_et, Password_et;
    String Username_st,Password_st;
    boolean conexionEstablecida = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_iniciosesion);
        Username_et=(EditText)findViewById(R.id.editText_vInicioUsuario);
        Password_et=(EditText)findViewById(R.id.editText_vInicioContra);
    }

    public void userLogin(View view)throws Exception
    {
        Username_st= getStringUsuario(Username_et);
        Password_st= getStringContrasena(Password_et);

        if( ModeloInicioRegistro.validacion_entrada(Username_st) == false || ModeloInicioRegistro.validacion_entrada(Password_st) == false)
        {
            Toast myToast = Toast.makeText(this, "Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros",
                    Toast.LENGTH_LONG);
            myToast.show();
            conexionEstablecida = false;
            return;
        }
        connect("http://www.itype.ml/login.php");

    }

    public void connect(String Url)throws Exception
    {
        //Username_st= conversionString( Username_et.getText().toString());
        //Password_st=Password_et.getText().toString();
        Http_Post post = new Http_Post();
        String g = post.execute(Url,Username_st,Password_st).get();
        Toast.makeText(this.getApplicationContext(),obtenerInformeInicio(g), Toast.LENGTH_SHORT).show();
        //Toast.makeText(this.getApplicationContext(),g, Toast.LENGTH_SHORT).show();

        conexionEstablecida = true;
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

    public String getStringUsuario (EditText Username_edt){
        return Username_edt.getText().toString();
    }

    public String getStringContrasena (EditText Password_edt){
        return Password_edt.getText().toString();
    }

    public String obtenerInformeInicio (String informe) {
        if(informe.contains("Log in succesfull"))
        {
            irMenuPrincipal();
            return "Ingreso correcto";

        }
        else
        {
            return "Usuario o contraseña incorrectos";
        }

    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
