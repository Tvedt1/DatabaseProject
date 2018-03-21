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
	
	
	public void listOfResults(String startDate, String endDate, String exercise) throws ParseException{
		try {
			String sDate = startDate;
			SimpleDateFormat sdfStart = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date uDate = sdfStart.parse(sDate);
			Date start = new Date(uDate.getTime());
			
			String eDate = endDate;
			SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date stDate = sdfEnd.parse(eDate);
			Date end = new Date(stDate.getTime());
			
			int id = getExerciseID(exercise);
			
			Connection conn = Connect.getConn();
			PreparedStatement stmt;
			String query = "Select R.Dato, R.Resultat from Ovelse AS O "
					+ "inner join ResultatI AS RI ON O.OvelseID = RI.OvelseID"
					+ "INNER JOIN Resultater AS R ON RI.ResultatID = R.ResultatID"
					+ "WHERE R.Dato between ? and ? ";
			
			stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
					results.add(rs.getString("Dato") + rs.getString("Resultater"));
			}
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
}
