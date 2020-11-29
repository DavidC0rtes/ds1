
package finance.consultaclientes;

import db.JDBCConnection;
import deprecated.ds_bd_connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import finance.clientes.RegisterPanelCliente;

public class ConsultaModeloCliente {
    private JDBCConnection DB = new JDBCConnection();
    
    public ArrayList<ArrayList<String>> returnData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ResultSet resultSet = DB.getRecords("SELECT * FROM tipo_clientes tpc, clientes cl WHERE tpc.id=cl.tipo_cliente");
        try {
            while (resultSet.next()) {
                ArrayList<String> clientes = new ArrayList<>();      
                clientes.add(resultSet.getString("descripcion")); 
                clientes.add(resultSet.getString("tipo_documento"));
                clientes.add(resultSet.getString("num_documento"));
                clientes.add(resultSet.getString("primer_nombre"));
                clientes.add(resultSet.getString("primer_apellido"));
                clientes.add(resultSet.getString("segundo_apellido"));
                clientes.add(resultSet.getString("email"));
                clientes.add(resultSet.getString("activo"));
                data.add(clientes);
            }
            } catch (SQLException e) {
		
                e.printStackTrace();
            }
        return data;
    }
        
    public Object[][] obtenerMatrizData(){
        ArrayList<ArrayList<String>> data = returnData();
        String[][] dataInfo;
        dataInfo = new String[data.size()][8];
        for(int x = 0; x < data.size(); x++){
            dataInfo[x][0] = data.get(x).get(0);
            dataInfo[x][1] = data.get(x).get(1);
            dataInfo[x][2] = data.get(x).get(2);
            dataInfo[x][3] = data.get(x).get(3);
            dataInfo[x][4] = data.get(x).get(4);
            dataInfo[x][5] = data.get(x).get(5);
            dataInfo[x][6] = data.get(x).get(6);
            dataInfo[x][7] = data.get(x).get(7);
        }
        return dataInfo;
    }
    public void updateData(int Column, String dato, String identificador){
    	String SQL = "UPDATE clientes SET ";
        String[] params = {dato, identificador};
        
        switch (Column) {
            case 0:
                SQL += "tipo_cliente = ? WHERE num_documento = ?";
                System.out.println(Integer.parseInt(dato));
                break;
            case 3:
                SQL += "primer_nombre = ? WHERE num_documento = ?";            
                break;
            case 4:
                SQL += "primer_apellido = ? WHERE num_documento = ?";
                break;
            case 5:
                SQL += "segundo_apellido = ? WHERE num_documento = ?";
                break;
            case 6:
                SQL += "email = ? WHERE num_documento = ?";
                break;
            case 7:
                SQL += "activo = ? WHERE num_documento = ?";
                break;
            default:
                break;
        }
        int affectedrows = DB.updateRecord(SQL, params);
        System.out.println("Affected rows: " + affectedrows);
    }
}
