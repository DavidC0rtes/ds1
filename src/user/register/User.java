package user.register;

/**
 * Sirve como modelo para los usuarios del sistema.
 */
public class User {
	private int cedula;
	private int idRol;
	
	private String rol;
	private String[] nombre;
	private String email;
	private String password;

	
	/** setters **/
	public void setCedula(int cedula) {this.cedula = cedula;}
	
	public void setEmail(String email) {this.email = email;}
	
	public void setName(String[] nombre) {this.nombre = nombre;}
	
	public void setPassword(String password) {this.password = password;}
	
	public void setIDRol(int idRol) {this.idRol = idRol;}
	
	
	/* getters */
	public int getID() {return idRol;}
	
	public int getCC() {return cedula;}
	
	public int getIdRol() {return idRol;}
	
	public String getEmail() {return email;}
	
	public String[] getName() {return nombre;}
	
	public String getPassword() {return password;}
}
