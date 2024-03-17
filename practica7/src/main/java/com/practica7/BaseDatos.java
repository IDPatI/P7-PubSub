package com.practica7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class BaseDatos {
    public static String nombreBD = "vayven";
    public static String user = "root";
    public static String password = "";
    public static Connection conn;
    public static void Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("-La clase no fue encontrada-");
            System.exit(0);
        }
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ nombreBD, user, password);
        } catch (SQLException e) {
            System.out.println("-Error al conectarse con la Base de Datos-");
            System.exit(0);
        }
    }

    public static ArrayList<String[]> SELECT(String sql){
        Statement stmt = null;
        ResultSet rs = null;
        Vector<String> columnNames = new Vector<String>();
        ArrayList<String[]> resultado = new ArrayList<>();
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs != null) {
                ResultSetMetaData columns = (ResultSetMetaData) rs.getMetaData();
                int i = 0;
                String[] actual = new String[columns.getColumnCount()];
                while (i < columns.getColumnCount()) {
                    i++;
                    actual[i-1] = columns.getColumnName(i);
                    columnNames.add(columns.getColumnName(i));
                }
                while (rs.next()) {
                    actual = new String[columns.getColumnCount()];
                for (i = 0; i < columnNames.size(); i++) {
                    actual[i] =  rs.getString(columnNames.get(i));
                }
                resultado.add(actual);
                }
            }else{
                String[] okay = {"No Match"};
                resultado.add(okay);
            }
        } catch (Exception e) {
        System.out.println("Exception: " + e.toString());
        }finally {
        try {
            if (rs != null) {
            rs.close();
            }
            if (stmt != null) {
            stmt.close();
            }
        } catch (Exception mysqlEx) {
            System.out.println(mysqlEx.toString());
        }

        }
        return resultado;
  }

  public static boolean INSERT(Integer num_pasajeros, Double velocidad, Double temperatura,Double l_gasolina, String alarma, Double latitud, Double longitud,  String nom_conductor, Integer ruta, Integer id_autobus) throws SQLException{
    String insert = "INSERT INTO camiones (num_pasajeros, velocidad, temperatura, l_gasolina, alarma, latitud, longitud, nom_conductor, ruta, id_autobus) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?)";
    PreparedStatement preparedStmt = conn.prepareStatement(insert);
    preparedStmt.setInt(1, num_pasajeros);
    preparedStmt.setFloat(2, velocidad.floatValue());
    preparedStmt.setFloat(3, velocidad.floatValue());
    preparedStmt.setFloat(4, velocidad.floatValue());
    preparedStmt.setString(5, alarma);
    preparedStmt.setDouble(6, latitud);
    preparedStmt.setDouble(7, longitud);
    preparedStmt.setString(8, nom_conductor);
    preparedStmt.setInt(9, ruta);
    preparedStmt.setInt(10, id_autobus);
    return preparedStmt.execute();


  }

}
