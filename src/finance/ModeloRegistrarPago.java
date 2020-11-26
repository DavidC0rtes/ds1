package finance;

import java.sql.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import db.JDBCConnection;

/**
 * Clase modelo para el registro de pagos.
 * @author david
 *
 */
public class ModeloRegistrarPago {
	private JDBCConnection DB;
	
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
				"SELECT * "
				+ "FROM contratos "
				+ "WHERE id=? "
				+ "AND activo=true";
		
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
	 * Añade todos los resultados del ResultSet a una lista
	 * para ser manejada por el control.
	 * @param rs
	 * @return
	 */
	private HashMap<String, Object> getArrayContrato(ResultSet rs) {
		HashMap<String, Object> datosContrato = new HashMap<String, Object>();
		try {
			datosContrato.put("id_cliente", rs.getInt("id_cliente"));
			datosContrato.put("estrato", rs.getInt("estrato"));
			datosContrato.put("dir_instalacion", rs.getString("dir_instalacion"));
			datosContrato.put("deuda_actual", rs.getFloat("deuda_actual"));
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
	public boolean registrarPago(int numContrato, float monto) {
		String SQL = 
				"INSERT INTO pagos"
				+ "VALUES(?,?,?)";
		
		LocalDateTime date = LocalDateTime.now();
		System.out.println(date.format(DateTimeFormatter.ISO_DATE_TIME));
		String[] params = {Integer.toString(numContrato), "hola", Float.toString(monto)};
		
		//if ( DB.updateRecord(SQL, params) == 1) {
		//	return true;
		//}
		return false;
	}
}
