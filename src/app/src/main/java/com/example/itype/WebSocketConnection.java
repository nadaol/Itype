package com.example.itype;

import java.util.ArrayList;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

//clase unica(singletnon)para la conexión tcp/ip con el servidor local
public final class WebSocketConnection extends WebSocketListener {
    private static ArrayList<Observador> observadores;//los observadores(interfaces) que recibe las actualizaciones
    private static WebSocket ws;//el websocket de la conexion al servidor local
    private static WebSocketConnection unique;
    private static final int NORMAL_CLOSURE_STATUS = 1000;

    private WebSocketConnection() { }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        ws=webSocket;
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        if(observadores!=null) {//al recibir msg del servidor local notifica a todos los observadores la actualizacion
            for (int i = 0; i < observadores.size(); i++) {
                observadores.get(i).actualizar(text);
            }
        }
    }

        @Override
        public void onClosing (WebSocket webSocket,final int code, final String reason){
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }
        @Override
        public void onFailure (WebSocket webSocket, final Throwable t, Response response) {
            if(observadores!=null) {
                for (int i = 0; i < observadores.size(); i++) {
                    observadores.get(i).actualizar("TError : " + t.getMessage());
                }
            }
        }
        public static WebSocketConnection getInstance(){if(unique==null) {unique = new WebSocketConnection(); }return unique;}
        public static void addObs(Observador o){if(observadores==null){getInstance();observadores = new ArrayList<>();}observadores.add(o);}
        public static void delObs(Observador o){observadores.remove(o);}
        public static void enviar(String messege){ws.send(messege);}

}
