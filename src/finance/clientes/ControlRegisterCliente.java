
package finance.clientes;

import java.sql.SQLException;
import java.util.*;

public class ControlRegisterCliente 
{
    private RegisterModelCliente cliente;
    
    public ControlRegisterCliente() 
    {
	cliente = new RegisterModelCliente();
    }
    public boolean datosCompletos(HashMap<String, String> datos) 
    {
	if (datos.containsValue("")) 
        {
		return false;
	}
	return true;
    }
    public int writeData(HashMap<String, String> datos) 
        {
		datos.forEach((k,v) -> setData(k,v));
		
		try {
			return cliente.createUser();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private void setData(String field, String value) {
		switch(field) 
                {
		case "tipo_cliente":
			cliente.setTipoCliente(value);
			break;
		case "num_documento":
			cliente.setNumeroDocumento(Integer.parseInt(value));
			break;
                case "tipo_documento":
			cliente.setTipoDocumento(value);
			break;
		case "primer_nombre":
			cliente.setPrimerNombre(value);
			break;
		case "primer_apellido":
			cliente.setPrimerApellido(value);
			break;
		case "segundo_apellido":
			cliente.setSegundoApellido(value);
			break;
		case "email":
			cliente.setEmail(value);
			break;
                case "dir_instalacion":
			cliente.setDireccion(value);
			break;
                case "estrato":
			cliente.setEstrato(Integer.parseInt(value));
			break;
		}
	}
}
