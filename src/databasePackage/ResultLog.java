package databasePackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultLog {
	
	List<String> exe = new ArrayList<>();
	List<String> results = new ArrayList<>();
	List<String> users = new ArrayList<>();
	
	
	public List<String> listOfResults(String startDate, String endDate, String exercise, String user) throws ParseException{
		try {
			
			int id = getExerciseID(exercise);
			int uid = getUserID(user);
			
			Connection conn = Connect.getConn();
			PreparedStatement stmt;
			String query = "Select R.Dato, R.Resultat from Ovelse AS O "
					+ "inner join ResultatI AS RI ON (O.OvelseID = RI.OvelseID)"
					+ " INNER JOIN Resultater AS R ON (RI.ResultatID = R.ResultatID)"
					+ " WHERE O.OvelseID = ? and R.Dato between ? and ? ";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, uid);
			stmt.setDate(2, Date.valueOf(startDate));
			stmt.setDate(3, Date.valueOf(endDate));
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					results.add(rs.getString("Dato") + " " + rs.getString("Resultat"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
		
	}
	
	
	
	
	public void createExerciseList() {
		Connection conn = Connect.getConn();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("Select Navn from Ovelse");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				exe.add(rs.getString("Navn"));
			}
			System.out.println(exe);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getExerciseID(String øvelsenavn) {
		
		//initilize uid
		int eid= 0;
		
		try {
			Connection conn = Connect.getConn();
			PreparedStatement stmt = conn.prepareStatement("Select OvelseID from Ovelse where Navn=?"); 
			stmt.setString(1, øvelsenavn);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				eid = (rs.getInt(1)); 
			}
			return eid;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	public int getUserID(String username) {
			
			//initilize uid
			int uid= 0;
			
			try {
				Connection conn = Connect.getConn();
				PreparedStatement stmt = conn.prepareStatement("Select BrukerID from Bruker where Navn=?"); 
				stmt.setString(1, username);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					uid = (rs.getInt(1)); 
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
			return uid;
		}
	public List<String> createUserList() {
		Connection conn = Connect.getConn();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("Select Navn from Bruker");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				users.add(rs.getString("Navn"));
			}
			System.out.println(users);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
}
