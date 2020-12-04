package sige.finance.Facturas;

import sige.db.JDBCConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class GenerarFactura {

	
	
	public GenerarFactura() {

	}
	
	public void VerifyInput(String entrada) {
		int result;
		
    	if (entrada == null) {
    	} else if (entrada.equalsIgnoreCase("")) {
    		System.out.println("No se ingreso ningún contrato");
    	} else {
    		try {
    			result = Integer.parseInt(entrada);
    			if (result <= 0) {
    				System.out.println("Número invalido");
    			} else {
    				CrearJasperReport(result);
    			}
    		} catch (Exception e) {
    			System.out.println("Contrato inexistente");
    		  }
    	}
    }
	
	public void CrearJasperReport(int contract_ID) {
		final JDBCConnection con;
		
		con = new JDBCConnection();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("IdContrato", contract_ID);

		
		try (InputStream is = GenerarFactura.class.getResourceAsStream("FacturaTest2.jasper")){

			JasperReport reporte = (JasperReport)JRLoader.loadObject(is);
			JasperPrint jp = JasperFillManager.fillReport(reporte, parametros, con.getConnection());
			JasperViewer.viewReport(jp,false);
			
		} 	catch(Exception e) {e.printStackTrace();
	  }	
	}
}
