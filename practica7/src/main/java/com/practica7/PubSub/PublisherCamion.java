package com.practica7.PubSub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

public class PublisherCamion {
    public static int ruta = getRandomNumber(0, 100);
    public static int id_autobus = getRandomNumber(0, 10);
    public static String[] nombres = {
        "Juan Pérez",
        "María García",
        "Carlos López",
        "Laura Martínez",
        "Andrés Rodríguez",
        "Ana Sánchez",
        "David Fernández",
        "Claudia Ramírez",
        "Javier González",
        "Rosa Díaz"
    };
    public static String nom_conductor=  nombres[getRandomNumber(0, 9)];

    public static void main(String args[]) throws IOException{
        String address = "192.168.0.8";
        int port = 8989;
        Socket s1=null;
        BufferedReader is=null;
        PrintWriter os=null;
        
        try {
            s1=new Socket(address, port); 
            //Donde lees los mensajes que te llegan
            is=new BufferedReader(new InputStreamReader(s1.getInputStream()));
            //Donde mandas los mensajes hacia el socket
            os= new PrintWriter(s1.getOutputStream());
        }
        catch (IOException e){
            e.printStackTrace();
            System.err.print("IO Exception");
        }

        System.out.println("Server : "+address + ":"+ port);
        try{
            //Numero de mensajes
            int numMensajes = 100;
            for(int i= 0; i<numMensajes; i++){
                os.println(datosRandom());
                os.flush();
                System.out.println("Mensajes mandado: "+ i);
                //10 segundos entre cada mensaje
                Thread.sleep(10000);
            }
            os.println("QUIT");
        }
        catch( InterruptedException e){
            e.printStackTrace();
            System.out.println("Socket read Error");
        }
        finally{
            is.close();os.close();s1.close();
        System.out.println("Finalizo la coneccion");

        }
    }
    public static String datosRandom(){
        int num_pasajeros = getRandomNumber(0, 84);
        double velocidad = getRandomNumber(20.0, 100.0);
        double temperatura = getRandomNumber(23.0, 38.0);
        double l_gasolina = getRandomNumber(1.0, 100.0);
        String alarma = "STILL_OK" +getRandomNumber(0, 100);
        double latitud = getRandomNumber(20.9685, 21.9685);
        double longitud = getRandomNumber(-89.62756, -88.52756);
        

        JSONObject retornar = new JSONObject();
        retornar.put("tipo", "publisher");
        retornar.put("num_pasajeros", num_pasajeros);
        retornar.put("velocidad", velocidad);
        retornar.put("temperatura", temperatura);
        retornar.put("l_gasolina", l_gasolina);
        retornar.put("alarma", alarma);
        retornar.put("latitud", latitud);
        retornar.put("longitud", longitud);
        retornar.put("nom_conductor", PublisherCamion.nom_conductor);
        retornar.put("ruta", PublisherCamion.ruta);
        retornar.put("id_autobus", PublisherCamion.id_autobus);
        return retornar.toString();
        
    }
    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static double getRandomNumber(double min, double max) {
        return (double) ((Math.random() * (max - min)) + min);
    }

}
