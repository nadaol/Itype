package com.example.itype;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ControladorPuestos extends AppCompatActivity implements Observador {
    private Button Siguiente;
    private Button Volver;
    private static WebSocketConnection Wc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_puestos);
        Volver = findViewById(R.id.volver_btn);
        Siguiente = findViewById(R.id.siguiente_btn);

        //me inscribo a la conexion para que me lleguen los updates
        WebSocketConnection.addObs(this);
    }

    public void actualizar(final String s) {//update view on UiThread
        runOnUiThread(new Runnable() {
            public void run() {
                if (s.startsWith("T"))//recievieng toast update from websocket

                    Toast.makeText(getApplicationContext(), s.substring(1), Toast.LENGTH_LONG).show();

            else if (s.startsWith("U"))//recieving table Update from websocket
                {
                    UpdateTable(s.substring(1));
                }
        }
 });
}
    public void UpdateTable(String tabla)
    {
        String[] filas = tabla.split("\\r?\\n");//obtengo filas de la tabla en un array
        TableLayout table = findViewById(R.id.stadistic_table);
        for(int i = 0;i<filas.length;i++)
        {
            String[] datos = filas[i].split(",");//obtengo datos de una fila en un array (username|velocidad)
            TableRow row = (TableRow) table.getChildAt(i+1);
            for(int j =0;j< datos.length;j++) {
                TextView dato = (TextView) row.getChildAt(j+1);//obtengo textView correspondiente de la tabla
                Log.d("tabla",datos[j]);
                dato.setText(datos[j]);//seteo el dato en la tabla
            }
        }

    }

    public void MenuPrincipal(View view)
    {
        Intent i = new Intent(this, ControladorMenuPrincipal.class);
        startActivity(i);
        finish();
    }

    public void siguiente(View view)
    {
        Intent intent = new Intent(getApplicationContext(), ControladorPerfil.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onBackPressed() {

    }

}
