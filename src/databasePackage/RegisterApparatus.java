package databasePackage;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterApparatus {
	
	
	public int createID() throws SQLException {
		int id = -1;
		Connection conn = Connect.getConn();
		PreparedStatement stmt = conn.prepareStatement("Select Count(ApparatID) as ApparatCount from Apparat"); 
		System.out.println(stmt);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		System.out.println(rs.getInt("ApparatCount"));
		
		id = rs.getInt("ApparatCount");
		stmt.close();
		return id;
		
		
	}
	public void registerNewApparatus( String name, String beskrivelse) throws SQLException {
		int id = createID() + 1;
		
		Connection conn = Connect.getConn();
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO `Apparat`(`ApparatID`, `Navn`, `Beskrivelse`) VALUES ('%s','%s','%s')", id, name, beskrivelse);
		stmt.executeUpdate(sql);
		stmt.close();
	}
}
