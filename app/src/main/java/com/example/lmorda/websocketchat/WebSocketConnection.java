package com.example.lmorda.websocketchat;

import java.util.ArrayList;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public final class WebSocketConnection extends WebSocketListener {
    private static ArrayList<Observador> observadores;//el observador(interface) que recibe los updates
    private static WebSocket ws;
    private static WebSocketConnection unique;//el websocket de la conexion al servidor local
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    private WebSocketConnection()
    {

    }
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        ws=webSocket;//al iniciar la conexion enviar string initial
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        for(int i=0;i<observadores.size();i++)
        {
            observadores.get(i).actualizar(text);
        }
    }

        @Override
        public void onClosing (WebSocket webSocket,final int code, final String reason){
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
        @Override
        public void onFailure (WebSocket webSocket,final Throwable t, Response response) {
            for(int i=0;i<observadores.size();i++)
            {
                observadores.get(i).actualizar("TError : " + t.getMessage());
            }
        }
        public static WebSocketConnection getInstance(){if(unique==null) {unique = new WebSocketConnection(); }return unique;}
        public static void addObs(Observador o){if(observadores==null){observadores = new ArrayList<>();}observadores.add(o);}
        public static void delObs(Observador o){observadores.remove(o);}
        public static void enviar(String messege){ws.send(messege);}

}
