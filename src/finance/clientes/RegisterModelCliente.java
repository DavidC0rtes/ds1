package finance.clientes;
import java.sql.SQLException;

import db.JDBCConnection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
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
        private int estrato;
        private String direccion;
	private JDBCConnection conn;
	private LocalDate myDate = LocalDate.now();
        private int dia = myDate.getDayOfMonth();
        private String idCliente;
        
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
        
        public void idCliente(String numeroDocumento) throws SQLException{
            String query = "SELECT * FROM clientes WHERE num_documento =?";
            String[] params = {numeroDocumento};
            ResultSet rs = conn.getRecords(query, params);
            if(rs.next()){
                idCliente = rs.getString("id");
            }
            else{
            }
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
			String createClienteSQL = 
					"INSERT INTO clientes(tipo_cliente, tipo_documento, num_documento,"
                                        + "primer_nombre, primer_apellido, segundo_apellido, email)VALUES(?,?,?,?,?,?,?)";
                        String[] clienteParams = {
						Integer.toString(tipoCliente),
                                                tipoDocumento,
                                                Integer.toString(numeroDocumento),
						primerNombre,
						primerApellido,
						segundoApellido,
						email,
				};
                        conn.updateRecord(createClienteSQL, clienteParams);
                        
                                
                        String createContratoSQL = 
					"INSERT INTO contratos(id_cliente, dir_instalacion, fecha_corte, estrato)"
                                        + "VALUES(?,?,?,?)";
                        
			idCliente(Integer.toString(numeroDocumento));
                        
                        String[] contratoParams = {
						idCliente,
                                                direccion,
                                                Integer.toString(dia),
						Integer.toString(estrato)
				};
                
			return conn.updateRecord(createContratoSQL, contratoParams);
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
    public void setTipoCliente(String tipoCliente) {
    	this.tipoCliente = tipoCliente.equals("Corporativo") ? 2 : 1;
    }
        
	public void setNumeroDocumento(int numeroDocumento) {this.numeroDocumento = numeroDocumento;}
        
        public void setTipoDocumento(String tipoDocumento) {this.tipoDocumento = tipoDocumento;}
	
	public void setPrimerNombre(String nombre) {this.primerNombre = nombre;}
	
	public void setPrimerApellido(String apellido) {this.primerApellido = apellido;}
	
	public void setSegundoApellido(String apellido) {this.segundoApellido = apellido;}
	
        public void setEmail(String email) {this.email = email;}
	
	public void setDireccion(String direccion) {this.direccion = direccion;}
        
        public void setEstrato(int estrato) {this.estrato = estrato;}
	/* getters */
	//public int getID() {return idRol;}
	
	public int getCC() {return numeroDocumento;}
	
	//public int getIdRol() {return idRol;}
	
	public String getEmail() {return email;}
	
}
