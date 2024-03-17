package com.practica7;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    //El orden para probar es
    //Ejecutar PubSub
    //Ejecutar 2 PublisherCamion
    
    public static void main(String[] args) throws SQLException {
        BaseDatos.Conexion();

        ArrayList<String[]> r = BaseDatos.SELECT("SELECT * FROM camiones ORDER BY id DESC");
        for(String[] fila: r){
            for(String c: fila){
                System.out.print(c +"\t");
            }
            System.out.println("");
        }

        System.out.println("");
        int num_pasajeros = 12;
        double velocidad = 12.23;
        double temperatura = 30.00;
        double l_gasolina = 40.3;
        String alarma = "STILL_OK";
        double latitud = 20.9685;
        double longitud = -89.62756;
        String nom_conductor=  "Juan Efren Perez Gomez";
        int ruta = 23;
        int id_autobus = 1;
        boolean resultado =  BaseDatos.INSERT(num_pasajeros, velocidad, temperatura,l_gasolina, alarma,  latitud, longitud, nom_conductor, ruta, id_autobus);
        System.out.println(resultado);
    }
      
}