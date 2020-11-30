package finance.pagoSucursal;

import db.JDBCConnection;

public class ModeloSucursal {
	private JDBCConnection DB;
	private int userID;
	
	public ModeloSucursal(int userid) {
		DB = new JDBCConnection();
		this.userID = userid;
	}
	
	public void writeData(String[] datos) {
		String query =
				"INSERT INTO pagos(id_contrato,monto,created_by) "
				+ "VALUES(?,?,?)";
		
		String[] params = {
				String.valueOf(datos[1]),
				datos[2],
				String.valueOf(userID)
		};

		DB.updateRecord(query, params);
	}
}
