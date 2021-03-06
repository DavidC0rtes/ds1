package activos.subestacion.lista;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JDBCConnection;

public class ModeloLista {
	private JDBCConnection DB;

	
	public ModeloLista(JDBCConnection conn) {
		DB = conn;
	}
	
	private ArrayList<ArrayList<String>> getAll() {
		String sql = "select * from subestaciones";
		
		ResultSet rs = DB.getRecords(sql);
		ArrayList<ArrayList<String>> datosSub = new ArrayList<ArrayList<String>>();
		try {
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					ArrayList<String> row = new ArrayList<String>();
					row.add(rs.getString("id"));
					row.add(rs.getString("nombre"));
					row.add(rs.getString("id_jefe_subestacion"));
					row.add(rs.getString("ciudad"));
					row.add(rs.getString("direccion"));
					row.add(rs.getString("estado"));
					datosSub.add(row);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return datosSub;
	}
	
	public Object[][] convertData() {
		ArrayList<ArrayList<String>> datosSub = getAll();
		Object[][] toReturn = new Object[datosSub.size()][6];
		
		for (int row=0; row<datosSub.size(); row++) {
			toReturn[row][0] = datosSub.get(row).get(0);
			toReturn[row][1] = datosSub.get(row).get(1);
			toReturn[row][2] = datosSub.get(row).get(2);
			toReturn[row][3] = datosSub.get(row).get(3);
			toReturn[row][4] = datosSub.get(row).get(4);
			toReturn[row][5] = datosSub.get(row).get(5);
		}
		
		return toReturn;
	}
	
	public void updateData(int col, String id, String newData) {
		String sql = "update subestaciones set ";
		
		switch (col) {
		case 1:
			sql += "nombre=?";
			break;
		case 2:
			sql += "id_jefe_subestacion=?";
			break;
		case 3:
			sql += "ciudad=?";
			break;
		case 4:
			sql += "direccion=?";
			break;
		case 5:
			sql += "estado=?";
			break;
		}
		
		sql += " where id=?";
		DB.updateRecord(sql, new String[] {newData,id});
		
	}
}
