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
	
	List<String> exeRL = new ArrayList<>();
	ArrayList<ArrayList<String>> results = new ArrayList<>();
	List<String> usersRL = new ArrayList<>();
	
	
	public ArrayList<ArrayList<String>> listOfResults(String startDate, String endDate, String exercise, String user) throws ParseException{
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
				ArrayList<String> res = new ArrayList<String>();
				res.add(String.valueOf((rs.getString("Dato"))));
				res.add(String.valueOf((rs.getString("Resultat"))));
				results.add(res);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(results);
		System.out.println(results.get(1));
		return results;
		
	}
	
	
	
	
	public List<String> createExerciseList() {
		Connection conn = Connect.getConn();
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("Select Navn from Ovelse");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				exeRL.add(rs.getString("Navn"));
			}
			return exeRL;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exeRL;
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
				usersRL.add(rs.getString("Navn"));
			}
			return usersRL;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usersRL;
	}
}
