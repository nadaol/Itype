package com.example.lmorda.websocketchat;

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

public class Puestos_Controller extends AppCompatActivity implements Observador {
    private Button Siguiente;
    private Button Volver;
    private static WebSocketConnection Wc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_estadisticas);
        Volver = findViewById(R.id.volver_btn);
        Siguiente = findViewById(R.id.siguiente_btn);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://192.168.0.21:8080").build();
        //Crea el websocket para que me avisen los updates

        //Esto tiene que estar al terminar la prueba
        String Velocidad="19";//vel calculada
        WebSocketConnection.getInstance();
        WebSocketConnection.addObs(this);
        client.newWebSocket(request,WebSocketConnection.getInstance());
        client.dispatcher().executorService().shutdown();
//

        try {
            //Esto tiene que estar en el login
            String log = Usuario.login("User5","12345");
            Toast.makeText(getApplicationContext(),log,Toast.LENGTH_LONG).show();
           WebSocketConnection.enviar(Usuario.getName()+","+Velocidad);//requiere conexion con servidor local,debería enviar la velocidad al terminar la prueba
        }
        catch (Exception e){e.printStackTrace();}


        /*
        //cambio a la vista de seleccion de dificultad
        Volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(this,Seleccion_Controller.class);
                startActivity(intent2);
            }}); */

        //cambio a la vista de info de usuario
        Siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Perfil_Controller.class);
                startActivity(intent);
            }});


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
        String [] filas = tabla.split("\\r?\\n");//obtengo filas de la tabla en un array
        TableLayout table = findViewById(R.id.stadistic_table);
        for(int i = 0;i<filas.length;i++)
        {
            String [] datos = filas[i].split(",");//obtengo datos de una fila en un array (username|velocidad)
            TableRow row = (TableRow) table.getChildAt(i+1);
            for(int j =0;j< datos.length;j++) {
                TextView dato = (TextView) row.getChildAt(j+1);//obtengo textView correspondiente de la tabla
                Log.d("tabla",datos[j]);
                dato.setText(datos[j]);//seteo el dato en la tabla
            }
        }

    }
}
