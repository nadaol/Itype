package com.example.lmorda.websocketchat;

//Clase única(singleton)para almacenar y gestionar datos del usuario
public class Usuario {

    private static String Nombre,Contraseña,VelocidadProm,VelocidadMax,Jugadas;

    private static Usuario usuario;

    private Usuario()
    {

    }


    public static void updateData() throws Exception
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

    public static String login(String Name,String Password) throws Exception
    {
        if(usuario==null){usuario=new Usuario();}
        Http_Post request = new Http_Post();//nueva conexion post para validar el login
        String result = request.execute("http://itype.ml/login.php","user",Name,"password",Password).get();
        if(result.startsWith("Log in succesfull")) {
            Nombre = Name;
            Contraseña = Password;
        }
        return result;
    }

    public static String register(String Name,String Password) throws Exception
    {
        Http_Post request = new Http_Post();//nueva conexión post para registrar usuario
        return  request.execute("http://itype.ml/register.php","user",Name,"password",Password).get();
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