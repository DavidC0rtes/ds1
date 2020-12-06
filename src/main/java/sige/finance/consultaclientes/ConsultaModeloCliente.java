
package sige.finance.consultaclientes;

import sige.db.JDBCConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import sige.finance.clientes.RegisterPanelCliente;
import java.time.LocalDate;

public class ConsultaModeloCliente {
    private JDBCConnection DB = new JDBCConnection();
    
    public ArrayList<ArrayList<String>> returnData(int flag){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        ResultSet resultSet;
        LocalDate myDate = LocalDate.now();
        int dia = myDate.getDayOfMonth();
        switch(flag){
            case 0:
                resultSet = DB.getRecords("SELECT * FROM tipo_clientes tpc, clientes cl WHERE tpc.id=cl.tipo_cliente AND activo=true");
                break;
            case 1:
                resultSet = DB.getRecords("SELECT * FROM tipo_clientes tpc, clientes cl WHERE tpc.id=cl.tipo_cliente AND activo=false");
                break;
            case 2:
                resultSet = DB.getRecords("SELECT * FROM tipo_clientes tpc, clientes cl, "
                        + "contratos ctr WHERE tpc.id=cl.tipo_cliente \n" +
                        "AND cl.id=ctr.id_cliente AND ctr.deuda_actual > 0 AND cl.activo=true AND ctr.fecha_corte <" + dia);
                break;
            default:
                resultSet = DB.getRecords("SELECT * FROM tipo_clientes tpc, clientes cl WHERE tpc.id=cl.tipo_cliente");
                break;
        }
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
        
    public Object[][] obtenerMatrizData(int flag){
        ArrayList<ArrayList<String>> data = returnData(flag);
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
    public void borrarCliente(String numDocumento){
        try {
            ResultSet rs = DB.getRecords("SELECT id FROM clientes WHERE num_documento="+numDocumento);
            String idCliente;
            if(rs.next()){
                idCliente = rs.getString("id");
            }else{
                idCliente = "0";
            }
            String SQLfacturas = "DELETE FROM facturas WHERE id_cliente=?";
            String[] paramsfacturas = {idCliente};
            String SQLcontratos = "DELETE FROM contratos WHERE id_cliente=?";
            String[] paramscontratos = {idCliente};
            String SQLclientes = "DELETE FROM clientes WHERE num_documento=?";
            String[] paramsclientes = {numDocumento};
            
            
            int affectedrows = DB.updateRecord(SQLfacturas, paramsfacturas)
                    + DB.updateRecord(SQLcontratos, paramscontratos)
                    + DB.updateRecord(SQLclientes, paramsclientes);
            System.out.println("Affected rows: " + affectedrows);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
