import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
	// General class to get connection to the database
	public static Connection getConn() {
		try {	
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://mysql.stud.ntnu.no/evenkjo_Prosjekt?autoReconnect=true&useSSL=false"; //Url for the database
			String user = "evenkjo_TDT4145"; // Username
			String pw = "dbprosjekt"; // Pw
			
			//connection to database
			Connection conn = DriverManager.getConnection(url, user, pw);
			return conn;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
}
