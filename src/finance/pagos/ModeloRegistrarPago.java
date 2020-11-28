package finance.pagos;

import java.sql.*;
import java.util.*;

import db.JDBCConnection;

/**
 * Clase modelo para el registro de pagos.
 * @author david
 *
 */
public class ModeloRegistrarPago {
	private JDBCConnection DB;
	
	
	public ModeloRegistrarPago() {
		DB = new JDBCConnection();
	}
	/**
	 * Función que se encarga de consultar un contrato
	 * dado su número/código en la BD.
	 * 
	 * @param numContrato
	 * @return ResultSet | null
	 * @throws SQLException
	 */
	public HashMap<String, Object> consultarContrato(int numContrato) {
		String SQL = 
				"SELECT concat(primer_nombre,' ', segundo_nombre, ' ',primer_apellido, ' ', segundo_apellido),"
				+ "estrato, dir_instalacion, deuda_actual, clientes.id, fecha_corte "
				+ "FROM contratos AS CON "
				+ "INNER JOIN clientes "
				+ "ON CON.id_cliente=clientes.id "
				+ "WHERE CON.id=? "
				+ "AND CON.activo=true";
		
		String[] params = {Integer.toString(numContrato)};
		
		ResultSet rs = DB.getRecords(SQL, params);
		try {
			if (rs.isBeforeFirst()) {
				return getArrayContrato(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Añade todos los resultados del ResultSet a una tabla hash
	 * para ser manejada por el control.
	 * @param rs
	 * @return
	 */
	private HashMap<String, Object> getArrayContrato(ResultSet rs) {
		HashMap<String, Object> datosContrato = new HashMap<String, Object>();
		try {
			rs.next();
			datosContrato.put("nombre", rs.getString("concat"));
			datosContrato.put("estrato", rs.getInt("estrato"));
			datosContrato.put("dir_instalacion", rs.getString("dir_instalacion"));
			datosContrato.put("deuda_actual", rs.getFloat("deuda_actual"));
			datosContrato.put("fecha_corte", rs.getInt("fecha_corte"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return datosContrato;
	}
	
	/**
	 * Registra un pago efectuado a un contrato específico,
	 * con un monto específico. Si todo sale bien retorna true,
	 * de lo contrario, false.
	 * 
	 * @param numContrato
	 * @param monto
	 * @return boolean
	 */
	public boolean registrarPago(int numContrato, float monto, int userID) {
		String SQL = 
				"INSERT INTO pagos(id_contrato,monto,created_by) "
				+ "VALUES(?,?,?)";
		
		String[] params = {
				Integer.toString(numContrato), 
				Float.toString(monto),
				String.valueOf(userID)};
		
		if ( DB.updateRecord(SQL, params) == 1) {
			return true;
		}
		return false;
	}
}
