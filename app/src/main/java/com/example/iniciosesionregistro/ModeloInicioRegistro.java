package com.example.iniciosesionregistro;

public class ModeloInicioRegistro {

    public static boolean validacion_entrada(String contra) {
        if (contra.matches("")){
            return false;
        } else if (contra.matches("[a-zA-Z0-9]*") ){
            return true;
        } else {
            return false;
        }
    }
}
