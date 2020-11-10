/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tables;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author daveybtw
 */
public class ds_bd {
    
    ds_bd_connection newConexion = new ds_bd_connection();
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
    }
    
    public ArrayList<ArrayList<String>> returnData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try (Connection connection = newConexion.retrieveConnection()){
 
            System.out.println("Java JDBC PostgreSQL Example");
            // When this class first attempts to establish a connection, it automatically loads any JDBC 4.0 drivers found within 
            // the class path. Note that your application must manually load any JDBC drivers prior to version 4.0.
//          Class.forName("org.postgresql.Driver"); 
 
            System.out.println("Connection succesful to the database!");
            Statement statement = connection.createStatement();
            System.out.println("Reading users records...");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM public.usuarios");
            while (resultSet.next()) {
                ArrayList<String> usuarios = new ArrayList<>();
                usuarios.add(resultSet.getString("num_documento"));
                usuarios.add(resultSet.getString("primer_nombre"));
                usuarios.add(resultSet.getString("primer_apellido"));
                usuarios.add(resultSet.getString("password"));
                usuarios.add(resultSet.getString("email"));
                usuarios.add(resultSet.getString("activo"));
                usuarios.add(resultSet.getString("id_rol"));
                data.add(usuarios);
            }
            System.out.print(data);
            return data;
 
        } /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return null;
    }
    
    public Object[][] obtenerMatrizData(){
        ArrayList<ArrayList<String>> data = returnData();
        String[][] dataInfo;
        dataInfo = new String[data.size()][7];
        for(int x = 0; x < data.size(); x++){
            dataInfo[x][0] = data.get(x).get(0);
            dataInfo[x][1] = data.get(x).get(1);
            dataInfo[x][2] = data.get(x).get(2);
            dataInfo[x][3] = data.get(x).get(3);
            dataInfo[x][4] = data.get(x).get(4);
            dataInfo[x][5] = data.get(x).get(5);
            dataInfo[x][6] = data.get(x).get(6);
        }
        return dataInfo;
    }
    
    public void updateData(int Column, String dato, String identificador){
       String SQL = null;
        
        switch (Column) {
            case 0:
                SQL = "UPDATE public.usuarios "
                        + "SET num_documento = '" + Integer.parseInt(dato) + "' "
                        + "WHERE num_documento = ?";
                System.out.println(Integer.parseInt(dato));
                break;
            case 1:
                SQL = "UPDATE public.usuarios "
                        + "SET primer_nombre = '" + dato + "' "
                        + "WHERE num_documento = ?";
                break;
            case 2:
                SQL = "UPDATE public.usuarios "
                        + "SET primer_apellido = '" + dato + "' "
                        + "WHERE num_documento = ?";
                break;
            case 3:
                SQL = "UPDATE public.usuarios "
                        + "SET password = '" + dato + "' "
                        + "WHERE num_documento = ?";
                break;
            case 4:
                SQL = "UPDATE public.usuarios "
                        + "SET email = '" + dato + "' "
                        + "WHERE num_documento = ?";
                break;
            case 5:
                SQL = "UPDATE public.usuarios "
                        + "SET activo = '" + dato + "' "
                        + "WHERE num_documento = ?";
                break;
            case 6:
                SQL = "UPDATE public.usuarios "
                        + "SET id_rol = " + Integer.parseInt(dato) + " "
                        + "WHERE num_documento = ?";
                break;
            default:
                break;
        }
        
        
        int affectedrows = 0;
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-3-91-112-166.compute-1.amazonaws.com/d4fqjan9sskpd3", "slghhoagzebeie", "1a2b4c8f2d1adb828aab34c70784517bc57bbcca6ff8af5694457d3478cf1485");
                PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            
            pstmt.setInt(1, Integer.parseInt(identificador));

            affectedrows = pstmt.executeUpdate();
            System.out.println(affectedrows);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public int loginDatabase(int user, String password){
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-3-91-112-166.compute-1.amazonaws.com/d4fqjan9sskpd3", "slghhoagzebeie", "1a2b4c8f2d1adb828aab34c70784517bc57bbcca6ff8af5694457d3478cf1485")){
            String SQL = "SELECT * FROM public.usuarios"
                    + " WHERE num_documento = " + user + " AND"
                    + " password = '" + password + "'";
            
            Statement statement = connection.createStatement();
            System.out.println("Looking for " + user + " with " + password);
            ResultSet resultSet = statement.executeQuery(SQL);
            ArrayList<ArrayList<String>> data = new ArrayList<>();
            if(resultSet.next()){                
                return Integer.parseInt(resultSet.getString("id_rol"));
            } else {
                return 0;
            }
        } /*catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found.");
            e.printStackTrace();
        }*/ catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
        return 0;
    }
    
    public int createNewUser(int identificacion, String nombre, String apellido, String password, String email, int rol_id){
        int affectedrows = 0;
        String SQL = "INSERT INTO public.usuarios (num_documento, primer_nombre, primer_apellido, password, email, activo, id_rol) "
                + "VALUES (?, ?, ?, ?, ?, true, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-3-91-112-166.compute-1.amazonaws.com/d4fqjan9sskpd3", "slghhoagzebeie", "1a2b4c8f2d1adb828aab34c70784517bc57bbcca6ff8af5694457d3478cf1485");
                PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            
            pstmt.setInt(1, identificacion);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setString(4, password);
            pstmt.setString(5, email);
            pstmt.setInt(6, rol_id);

            affectedrows = pstmt.executeUpdate();
            return affectedrows;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
    public int createNewSubE(int identificacion, String ciudad, String direccion, boolean estado){
        int affectedrows = 0;
        String SQL = "INSERT INTO public.subestaciones (id_jefe_subestacion, ciudad, direccion, estado) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-3-91-112-166.compute-1.amazonaws.com/d4fqjan9sskpd3", "slghhoagzebeie", "1a2b4c8f2d1adb828aab34c70784517bc57bbcca6ff8af5694457d3478cf1485");
                PreparedStatement pstmt = connection.prepareStatement(SQL)) {
            
            pstmt.setInt(1, identificacion);
            pstmt.setString(2, ciudad);
            pstmt.setString(3, direccion);
            pstmt.setBoolean(4, estado);
            affectedrows = pstmt.executeUpdate();
            return affectedrows;
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return 0;
    }
    
}
