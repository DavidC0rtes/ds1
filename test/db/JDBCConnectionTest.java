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
		assertNotNull(
				JDBCConnection.getRecords("select * from clientes;"));
	}
	
	@Test
	void testInsertRole() {
		String[] params = {"gerente"};
		String query = "insert into roles (nombre_rol) values (?)";
		assertEquals(1, JDBCConnection.updateRecord(query, params));
	}
}