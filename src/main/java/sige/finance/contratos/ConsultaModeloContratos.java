/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sige.finance.contratos;

import sige.db.JDBCConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ConsultaModeloContratos {
    private JDBCConnection DB = new JDBCConnection();
    
    public ArrayList<ArrayList<String>> returnData(){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ResultSet resultSet = DB.getRecords("SELECT * FROM clientes cl, contratos ctr WHERE ctr.id_cliente=cl.id");
        try {
            while (resultSet.next()) {
                ArrayList<String> contratos = new ArrayList<>();   
                contratos.add(resultSet.getString("num_documento")); 
                contratos.add(resultSet.getString("activo"));
                contratos.add(resultSet.getString("dir_instalacion"));
                contratos.add(resultSet.getString("deuda_actual"));
                contratos.add(resultSet.getString("fecha_corte"));
                contratos.add(resultSet.getString("estrato"));
                data.add(contratos);
            }
            } catch (SQLException e) {
		
                e.printStackTrace();
            }
        return data;
    }
        
    public Object[][] obtenerMatrizData(){
        ArrayList<ArrayList<String>> data = returnData();
        String[][] dataInfo;
        dataInfo = new String[data.size()][6];
        for(int x = 0; x < data.size(); x++){
            dataInfo[x][0] = data.get(x).get(0);
            dataInfo[x][1] = data.get(x).get(1);
            dataInfo[x][2] = data.get(x).get(2);
            dataInfo[x][3] = data.get(x).get(3);
            dataInfo[x][4] = data.get(x).get(4);
            dataInfo[x][5] = data.get(x).get(5);
        }
        return dataInfo;
    }
        
    public void updateData(int Column, String dato, String identificador){
        try {
            String SQL = "UPDATE contratos SET ";
            ResultSet resulSet = DB.getRecords("SELECT id FROM clientes WHERE num_documento=" + identificador);
            String idCliente;
            if(resulSet.next()){
                idCliente = resulSet.getString("id");
            }else{
                idCliente = "0";
            }
            String[] params = {dato, idCliente};
            
            switch (Column) {
                case 1:
                    SQL += "activo = ? WHERE id_cliente = ?";
                    break;
                case 2:
                    SQL += "dir_instalacion = ? WHERE id_cliente = ?";
                    break;
                case 3:
                    SQL += "deuda_actual = ? WHERE id_cliente = ?";
                    break;
                case 4:
                    SQL += "fecha_corte = ? WHERE id_cliente = ?";
                    break;
                case 5:
                    SQL += "estrato = ? WHERE id_cliente = ?";
                    break;
                default:
                    break;
            }
            int affectedrows = DB.updateRecord(SQL, params);
            System.out.println("Affected rows: " + affectedrows);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
