package finance.Facturas;

import db.JDBCConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import java.util.HashMap;
import java.util.Map;


public class GenerarFactura {

	JasperReport reporte; 
	
	
	public GenerarFactura() {

	}
	
	public void CrearJasperReport(int contract_ID) {
		final JDBCConnection con;
		
		con = new JDBCConnection();
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("IdContrato", contract_ID);
		try {
			reporte = JasperCompileManager.compileReport("/src/finance/Facturas/FacturaTest1.jrxml");
			JasperPrint jp = JasperFillManager.fillReport(reporte, parametros, con.getConnection());
			JasperViewer.viewReport(jp,false);
			
		} 	catch(Exception e) {e.printStackTrace();
	  }	
	}
}
