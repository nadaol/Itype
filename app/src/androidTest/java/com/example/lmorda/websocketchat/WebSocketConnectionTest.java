package com.example.lmorda.websocketchat;

import org.junit.After;
import org.junit.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

import static org.junit.Assert.*;

public class WebSocketConnectionTest {

 /*   @Test
    public void ConexionWebSocket_actualizacion()
    {
        OkHttpClient sender = new OkHttpClient();
        Request request = new Request.Builder().url("wss://echo.websocket.org").build();
        Observador reciever = new Observador() {
            @Override
            public void actualizar(String s) {
                assertEquals("prueba eco",s);
            }
        };
        WebSocketConnection.addObs(reciever);
       WebSocketConnection w = WebSocketConnection.getInstance();
        sender.newWebSocket(request,w);
        sender.dispatcher().executorService().shutdown();
       try{ WebSocketConnection.enviar("prueba eco");}
       catch (Exception e){e.printStackTrace();};
    }

    @After
    public void tearDown() throws Exception {

    } */

}