/** 
 * El modulo de acceso a la BD ya está terminado.
 * Este archivo y otros deben ser eliminados una vez
 * que se integren las vistas con el módulo de acceso a la BD del proyecto.
 */

package deprecated;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author USER
 */
public class ds_bd_connection {
   public Connection retrieveConnection() throws SQLException{
      Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-3-91-112-166.compute-1.amazonaws.com/d4fqjan9sskpd3", "slghhoagzebeie", "1a2b4c8f2d1adb828aab34c70784517bc57bbcca6ff8af5694457d3478cf1485");
      return connection;
   }
}
