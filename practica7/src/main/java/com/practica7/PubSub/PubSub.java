package com.practica7.PubSub;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.practica7.BaseDatos;

public class PubSub {
    public static int PORT = 8989;

    //Por ejemplo
    
    public static HashMap<String, ListaSub> subscripciones;


    @SuppressWarnings("resource")
    public static void main(String[] args) {
        BaseDatos.Conexion();
        subscripciones = new HashMap<String, ListaSub>();
        /* 
        De esta forma se almacenan a los que estan subscritos al PubSub
        ListaSub pruebaLista = new ListaSub();
        pruebaLista.canales.add("num_pasajeros");
        pruebaLista.canales.add("temperatura");
        subscripciones.put("192.168.0.1:2323", pruebaLista);
        */

        ServerSocket serverSocket = null;
        Socket socket = null;

       
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("-Publisher iniciado-");
        while (true) {
            try {
                socket = serverSocket.accept();
                
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new thread for a client
            new PubSubThread(socket).start();
            System.out.println("-Socket Empezado-");
        }

    }
}
