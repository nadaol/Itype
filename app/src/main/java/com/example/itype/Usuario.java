package com.example.itype;

import android.widget.Toast;

//Clase única(singleton)para almacenar y gestionar datos del usuario
public class Usuario {

    private static String Nombre,Contraseña,VelocidadProm,VelocidadMax,Jugadas;

    private static Usuario usuario;

    private Usuario()
    {

    }


    public static void actualizarInfo() throws Exception
    {//Realiza un update de los datos del usuario con respecto a la última jugada
        if(usuario!=null&&Nombre!=null) {
            try {
                Http_Post request = new Http_Post();//Obtengo los datos del usuario
                String UserInfo = request.execute("http://itype.ml/getdata.php", "username", Nombre).get();
                String[] User_info_array = UserInfo.split(",");
                Jugadas = User_info_array[0];
                VelocidadMax = User_info_array[1];
                VelocidadProm = User_info_array[2];
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else throw new Exception("Usuario inexistente!");
    }

    public static String login(String Name, String Password) throws Exception
    {
        if( Usuario.validacion_entrada(Name)==false || Usuario.validacion_entrada(Password)==false)
        {
            return "Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros";
        }
        if(usuario==null){usuario=new Usuario();}
        Http_Post request = new Http_Post();//nueva conexion post para validar el login
        String result = request.execute("http://itype.ml/login.php","user",Name,"password",Password).get();
        if(result.contains("Log in succesfull")) {
            Nombre = Name;
            Contraseña = Password;
            return "Inicio correcto,Bienvenido "+Name;
        }
        return "Usuario o contraseña incorrectos";
    }

    public static String registrar(String Name, String Password) throws Exception
    {
        if( Usuario.validacion_entrada(Name)==false || Usuario.validacion_entrada(Password)==false)
        {
            return "Campos vacíos, caracteres especiales o espacios. Ingrese solo letras o numeros";
        }
        Http_Post request = new Http_Post();//nueva conexión post para registrar usuario
        String result = request.execute("http://itype.ml/register.php","user",Name,"password",Password).get();
        if(result.contains("Success")) return "Registro correcto. Inicie nuevamente";
        else return "El usuario - " + Name + " - ya existe";
    }

    public static boolean validacion_entrada(String data) {
        if (data.matches("")){
            return false;
        } else if (data.matches("[a-zA-Z0-9]*") ){
            return true;
        } else {
            return false;
        }
    }

    //Métodos Get
    public static String getVelMax()
    {
        return VelocidadMax;
    }
    public static String getVelProm()
    {
        return VelocidadProm;
    }
    public static String getJugadas()
    {
        return Jugadas;
    }
    public static String getName()
    {
        return Nombre;
    }

}
