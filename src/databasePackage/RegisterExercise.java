package databasePackage;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegisterExercise {
	
	List<String> apparatus = new ArrayList<>();
	
	public int createID() throws SQLException {
		int id = -1;
		Connection conn = Connect.getConn();
		PreparedStatement stmt = conn.prepareStatement("Select Count(ï¿½velseID) as ï¿½velseCount from ï¿½velse"); 
		System.out.println(stmt);
		
			ResultSet rs = stmt.executeQuery();
			rs.next();
		System.out.println(rs.getInt("ï¿½velseCount"));
		
		id = rs.getInt("ï¿½velseCount");
		stmt.close();
		return id;	
	}
	
	

	public void registerNewExercise(String name, String aname,int kilo, int sets) throws SQLException{

		int id = createID() + 1;
		Connection conn = Connect.getConn();
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO `ï¿½velse`(`ï¿½velseID`, `Navn`) VALUES ('%s','%s')", id, name);
		stmt.executeUpdate(sql);
		stmt.close();

		int aid = getApparatusID(aname);
		connectToApparatus(aid, kilo, sets, id);
		
	}
	
	public void connectToApparatus(int aid,int kilo, int sets, int eid) throws SQLException {
		Connection conn = Connect.getConn();
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO `ApparatØvelse`(`ØvelseID`, `Kilo`, `Sett`, `ApparatID`) VALUES ('%s','%s','%s','%s')", eid, kilo, sets, aid);
		stmt.executeUpdate(sql);
		stmt.close();
	}
	
	public List<String> catchApparatus()  {
		Connection conn = Connect.getConn();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("Select Navn from Apparat");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				apparatus.add(rs.getString("Navn"));
			}
			System.out.println(apparatus);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return apparatus;
	}
	
	public int getApparatusID(String apparatus) {
		
		//initilize uid
		int aid= 0;
		
		try {
			Connection conn = Connect.getConn();
			PreparedStatement stmt = conn.prepareStatement("Select ApparatID from Apparat where Navn=?"); 
			stmt.setString(1, apparatus);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				aid = (rs.getInt(1)); 
			}
			return aid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
}
