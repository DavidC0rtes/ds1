package finance;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.JDBCConnection;
/**
 *
 * @author user
 */
public class RegisterModelCliente 
{	
	private int tipoCliente;
        private int numeroDocumento;
        private String tipoDocumento;
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String email;
	private JDBCConnection conn;
	
	public RegisterModelCliente() {
		conn = new JDBCConnection();
	}

	
	/**
	 * Se asegura de que no exista una fila en la tabla de clientes
	 * con la misma cedula.
	 * 
	 * @param String cedula
	 * @return false sí el cliente no existe | true en caso contrario.
	 * @throws SQLException
	 */
	public boolean userExists(int numeroDocumento) throws SQLException 
        {
		String query = 
				"SELECT * "
				+ "FROM clientes "
				+ "WHERE num_documento = "
				+ numeroDocumento;
		
		if (!conn.getRecords(query).isBeforeFirst()) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Verifica que el tipo de cliente asignado al cliente sea válido.
	 * @param String rol
	 * @return true | false
	 * @throws SQLException
	 */
	public boolean roleIsValid(int tipoCliente) throws SQLException 
        {
		String query ="SELECT * FROM tipo_clientes WHERE id = " + tipoCliente;
		
		if (conn.getRecords(query).isBeforeFirst()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Escribe el objeto cliente a la base de datos.
	 * Es decir, crea el cliente en la bd.
	 * 
	 */
	public int createUser() throws SQLException {
		/**
		 * Antes de hacer persistentes los datos, hay que hacer
		 * ciertas validaciones.
		 */
		if (!userExists(numeroDocumento)/* && roleIsValid(tipoCliente) */) {
			String createUserSQL = 
					"INSERT INTO clientes(tipo_cliente, tipo_documento, num_documento,"
                                        + "primer_nombre, primer_apellido, segundo_apellido, email)VALUES(?,?,?,?,?,?,?)";
			
			String[] userParams = {
						Integer.toString(tipoCliente),
                                                tipoDocumento,
                                                Integer.toString(numeroDocumento),
						primerNombre,
						primerApellido,
						segundoApellido,
						email,
				};
			return conn.updateRecord(createUserSQL, userParams);
		}
		return -1;
	}
	
	private int getIdRol(String rol) throws SQLException 
        {
		ResultSet rs = conn.getRecords("select id from roles where nombre_rol='"+rol+"'");
		if(rs.next()) {
			return rs.getInt("id");
		}
		return 0;
	}
	
	/** setters **/
        public void setTipoCliente(int tipoCliente) {this.tipoCliente = tipoCliente;}
        
	public void setNumeroDocumento(int numeroDocumento) {this.numeroDocumento = numeroDocumento;}
        
        public void setTipoDocumento(String tipoDocumento) {this.tipoDocumento = tipoDocumento;}
	
	public void setPrimerNombre(String nombre) {this.primerNombre = nombre;}
	
	public void setPrimerApellido(String apellido) {this.primerApellido = apellido;}
	
	public void setSegundoApellido(String apellido) {this.segundoApellido = apellido;}
	
        public void setEmail(String email) {this.email = email;}
	
	
	/* getters */
	//public int getID() {return idRol;}
	
	public int getCC() {return numeroDocumento;}
	
	//public int getIdRol() {return idRol;}
	
	public String getEmail() {return email;}
	
}
