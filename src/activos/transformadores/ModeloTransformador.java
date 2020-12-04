package activos.transformadores;

import db.JDBCConnection;
import java.util.*;

public class ModeloTransformador {
	private JDBCConnection DB;
	
	public ModeloTransformador(JDBCConnection conn) {
		this.DB = conn;
	}
	
	public boolean writeData(HashMap<String, String> datos) {
		String sql =
				"insert into transformadores (capacidad,estado,serial,id_subestacion) "
				+ "values(?,?,?,?)";
		
		String[] params = {
				datos.get("capacidad"),
				datos.get("estado"),
				datos.get("serial"),
				datos.get("id_subestacion")
		};
		
		if (DB.updateRecord(sql, params) == 1) {
			return true;
		}
		return false;
	}
}
