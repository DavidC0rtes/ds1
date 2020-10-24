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
				JDBCConnection.getRecords(JDBCConnection.getConnection(),
				"select * from clientes;"
				));
	}
}
