package db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class JDBCConnectionTest {

	@Test
	void testConnection() {
		JDBCConnection conect = new JDBCConnection();
		assertNotNull(conect);
	}
	
	@Test
	void testQuery() {
		JDBCConnection testConn = new JDBCConnection();
		assertNotNull(
				testConn.getRecords("select * from clientes;"));
	}
	
//	@Test
//	void testInsertRole() {
//		JDBCConnection testConn = new JDBCConnection();
//		String[] params = {"gerente"};
//		String query = "insert into roles (nombre_rol) values (?)";
//		assertEquals(1, testConn.updateRecord(query, params));
//	}
}