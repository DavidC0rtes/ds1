package finance.pagoSucursal;

import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

import user.User;

/**
 * Control de la funcionalidad que permite a los clientes
 * pagar sus contratos con la empresa en sucursales
 * bancarias.
 *
 */
public class ControlSucursal {
	private BufferedReader br;
	private Stream<String> lines;
	private String[] datos;
	private ModeloSucursal modelo;
	
	public ControlSucursal(File bankfile, int userID) {

		modelo = new ModeloSucursal(userID);
		try {
			this.br = new BufferedReader(new FileReader(bankfile));
			this.lines = br.lines();
			datos = lines.filter(x->!x.equals("****")).toArray(String[]::new);
			br.close();
			
		} catch (IOException e ) {	
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Se asegura de que los contenidos del archivo sean válidos.
	 * @param br
	 */
	public int validateFile() {
		
		for (int i=0; i<datos.length; i+=4) {
			if (	!datos[i].chars().allMatch(Character::isDigit) ||
					!datos[i+1].chars().allMatch(Character::isDigit) ||
					!datos[i+2].matches("[0-9]+\\.*[0-9]*$") ||
					!datos[i+3].matches("^[0-9]{4}\\/[0-9]{2}\\/[0-9]{2}$"))
				{
					return i;	
				}
		}
		
		for (int i=0; i<datos.length; i+=4) {
			modelo.writeData(Arrays.copyOfRange(datos, i,i+4));
		}
		return -1;
	}
}
