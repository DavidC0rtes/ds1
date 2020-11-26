package finance;

import java.sql.ResultSet;
import java.util.*;

public class ControlRegistrarPago {
	private ModeloRegistrarPago modelo;
	private HashMap<String,Object> contrato;
	
	public ControlRegistrarPago() {
		modelo = new ModeloRegistrarPago();
	}
	
	/**
	 * 
	 * @param contrato
	 * @return
	 */
	public boolean attemptConsultarContrato(String contrato) {
		if (contrato.chars().allMatch( Character::isDigit)) {
			int numContrato = Integer.parseInt(contrato);
			
			if (numContrato > 0) {
				this.contrato = modelo.consultarContrato(numContrato);
				if (this.contrato != null) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public HashMap<String, Object> getContrato() {return contrato;}
}
