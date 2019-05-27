package com.example.lmorda.websocketchat;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public final class WebSocketConnection extends WebSocketListener {
    private static Observador obs;//el observador(interface) que recibe los updates
    private static WebSocket ws;
    private static WebSocketConnection unique;//el websocket de la conexion al servidor local
    private static final int NORMAL_CLOSURE_STATUS = 1000;
    private static String initial;

    private WebSocketConnection()
    {

    }
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        ws=webSocket;//al iniciar la conexion enviar string initial
       if (initial!=null) ws.send(initial);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
    obs.actualizar(text);//Genera el update al observador
    }

        @Override
        public void onClosing (WebSocket webSocket,final int code, final String reason){
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
        @Override
        public void onFailure (WebSocket webSocket,final Throwable t, Response response) {

            obs.actualizar("TError : " + t.getMessage());
        }
        public static WebSocketConnection getInstance(){
        if(unique==null)
        {
            unique = new WebSocketConnection();
        }
        return unique;}
        public static void setObs(Observador o){obs=o;}
        public static void enviar(String messege){ws.send(messege);}

}
