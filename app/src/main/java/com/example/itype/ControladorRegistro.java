package com.example.itype;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ControladorRegistro extends Activity {

    EditText Username_et, Password_et;
    String Username_st,Password_st;
    boolean registroEstablecido = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_registro);
        Username_et=(EditText)findViewById(R.id.editText_vRegUsuario);
        Password_et=(EditText)findViewById(R.id.editText_vRegContra);
    }

    public void userReg(View view) throws Exception {

        Username_st= getStringUsuario(Username_et);
        Password_st= getStringContrasena(Password_et);

        if( ModeloInicioRegistro.validacion_entrada(Username_st) == false || ModeloInicioRegistro.validacion_entrada(Password_st) == false)
        {
            Toast myToast = Toast.makeText(this, "Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros",
                    Toast.LENGTH_LONG);
            myToast.show();
            registroEstablecido = false;
            return;
        }

        connect("http://www.itype.ml/register.php");
    }

    public void connect(String Url)throws Exception
    {
        Http_Post post = new Http_Post();
        String g = post.execute(Url,Username_st,Password_st).get();
        Toast.makeText(this.getApplicationContext(),obtenerInformeRegistro(g), Toast.LENGTH_SHORT).show();
        registroEstablecido = true;
    }

    public void regresoInicioSesion (View view) {
        Intent i = new Intent(this, ControladorInicioSesion.class);
        startActivity(i);
    }

    public String getStringUsuario (EditText Username_edt){
        return Username_edt.getText().toString();
    }

    public String getStringContrasena (EditText Password_edt){
        return Password_edt.getText().toString();
    }

    public String obtenerInformeRegistro (String informe) {
        if(informe.contains("Succes"))
        {
            return "Registro correcto. Inicie nuevamente";
        }
        else if (informe.contains("long"))
        {
            return "Nombre de usuario muy largo. Intente otra vez";
        }
        else
        {
            return "El usuario - " + Username_st + " - ya existe";
        }

    }

    @Override
    public void onBackPressed() {

    }
}
