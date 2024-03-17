package com.practica7.PubSub;

import java.io.BufferedReader;
//Es para mandar los mensaajes de vuelta
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;
import java.util.HashMap;

import org.json.JSONObject;

import com.practica7.BaseDatos;



public class PubSubThread extends Thread{
    protected Socket socket;

    public PubSubThread(Socket clientSocket) {
        this.socket = clientSocket;
    }

    public void run() {
        InputStream inp = null;
        BufferedReader brinp = null;
        //Nada de out, crear una clase totalmente nueva para el subscriber
        //Si el mensaje es de tipo "subscriber", el PubSub, lo añades al Hashmap de subscripciones
        //Entonces creas un nuevo socket para ir mandandole mensajes

        //Primero le mandas todo lo de la base de datos y luego le mandas lo que vaya llegando

        //No vas a poder devolver mensajes si intentas devolver el mensaje con el socket actual
        //Si lo logras pues lo puedes hacer de esa forma, si no, checa como lo hace publisher para mandar sus mensajes

        //Obtencion del puerto e ip        
        //int port = socket.getPort();
        //String address = socket.getInetAddress().toString();

        //DataOutputStream out;
        System.out.println(socket.getInetAddress());
        try {
            inp = socket.getInputStream();
            brinp = new BufferedReader(new InputStreamReader(inp));
            //out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            return;
        }
        String line;
        while (true) {
            try {
                line = brinp.readLine();
                if((line == null) || line.equalsIgnoreCase("QUIT")) {
                    socket.close();
                    return;
                }
                System.out.println(line);
                
                JSONObject mensaje = new JSONObject(line);
                String tipo = mensaje.getString("tipo");
                switch (tipo) {
                    case "publisher":
                        guardarInfo(mensaje);
                        enviarMensajes(PubSub.subscripciones);
                        break;
                    case "subscriber":
                        //El subscriber solo manda un mensaje, donde dice a que canales quiere estar subscrito
                        //Luego, el va a estar esperando mensajes, el primero siempre es de todo lo que hay en la base de datos
                        //Los siguientes, los publishers van a detonar el envio en enviarMensajes
                        break;
                    default:
                        break;
                }
                
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }


    }
    private void enviarMensajes(HashMap<String, ListaSub> subscribers) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'enviarMensajes'");
    }

    public void guardarInfo(JSONObject mensaje){
        int num_pasajeros = mensaje.getInt("num_pasajeros");
        double velocidad = mensaje.getDouble("velocidad");
        double temperatura = mensaje.getDouble("temperatura");
        double l_gasolina = mensaje.getDouble("l_gasolina");
        String alarma = mensaje.getString("alarma");
        double latitud = mensaje.getDouble("latitud");
        double longitud = mensaje.getDouble("longitud");
        String nom_conductor=mensaje.getString("nom_conductor");
        int ruta = mensaje.getInt("ruta");
        int id_autobus = mensaje.getInt("id_autobus");
        try {
            BaseDatos.INSERT(num_pasajeros, velocidad, temperatura,l_gasolina, alarma,  latitud, longitud, nom_conductor, ruta, id_autobus);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("-Guardado-");
    }
}
