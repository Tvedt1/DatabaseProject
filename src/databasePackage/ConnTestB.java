package databasePackage;


//Kaksda
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class ConnTestB extends Connect  {

	 //String message = "Connected!";
	static Connection conn;
	

	public static int checkConn() {
			conn = Connect.getConn();
			if (conn == null) {
				return 0;
			}
			else {
				return 1;
			}	
		}
	@Test
	public void testPing() throws SQLException {

		assertEquals(checkConn(),1);

	
	}
}
	
