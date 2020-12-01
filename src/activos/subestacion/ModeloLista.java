package activos.subestacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.JDBCConnection;

public class ModeloLista {
	private JDBCConnection DB;
	private ArrayList<ArrayList<String>> datosSub = new ArrayList<ArrayList<String>>();
	
	public ModeloLista() {
		DB = new JDBCConnection();
	}
	
	private void getAll() {
		String sql = "select * from subestaciones";
		
		ResultSet rs = DB.getRecords(sql);
		
		try {
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					ArrayList<String> row = new ArrayList<String>();
					row.add(rs.getString("id"));
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
	}
	
	public Object[][] convertData() {
		getAll();
		Object[][] toReturn = new Object[datosSub.size()][5];
		
		for (int row=0; row<datosSub.size(); row++) {
			toReturn[row][0] = datosSub.get(row).get(0);
			toReturn[row][1] = datosSub.get(row).get(1);
			toReturn[row][2] = datosSub.get(row).get(2);
			toReturn[row][3] = datosSub.get(row).get(3);
			toReturn[row][4] = datosSub.get(row).get(4);
		}
		
		return toReturn;
	}
}
