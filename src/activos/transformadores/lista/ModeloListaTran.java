package activos.transformadores.lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JDBCConnection;

public class ModeloListaTran {
	private JDBCConnection DB;
	
	public ModeloListaTran(JDBCConnection conn) {
		DB = conn;
	}
	
	
	private ArrayList<ArrayList<String>> getTransfor(String idSube) {
		String sql = "select * from transformadores where id_subestacion=?";
		String[] params = {idSube};
		ResultSet rs = DB.getRecords(sql, params);
		ArrayList<ArrayList<String>> datosTr = new ArrayList<ArrayList<String>>();
		
		try {
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					ArrayList<String> row = new ArrayList<String>();
					row.add(rs.getString("serial"));
					row.add(rs.getString("capacidad"));
					row.add(rs.getString("estado"));
					datosTr.add(row);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return datosTr;
	}
	
	
	public Object[][] convertData(String idSube) {
		ArrayList<ArrayList<String>> datosTr = getTransfor(idSube);
		Object[][] toReturn = new Object[datosTr.size()][3];
		
		for (int row=0; row<datosTr.size(); row++) {
			toReturn[row][0] = datosTr.get(row).get(0);
			toReturn[row][1] = datosTr.get(row).get(1);
			toReturn[row][2] = datosTr.get(row).get(2);
		}
		
		return toReturn;
	}
	
	public ArrayList<String> getSubes() {
		
		
		ResultSet rs = DB.getRecords(
				"SELECT id "
				+ "FROM subestaciones "
				+ "WHERE estado=true"
				);
		
		
		ArrayList<String> fila = new ArrayList<String>();
		try {
			if (rs.isBeforeFirst()) {
				while(rs.next()) {
					fila.add(rs.getString("id"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fila;
	}
}
