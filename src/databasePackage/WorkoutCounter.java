package databasePackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkoutCounter {

	List<String> exe = new ArrayList<>();
	
	public int CountWorkouts(String Oving) {
		
		int eid = getExerciseID(Oving);
		
		
		try {
			Connection conn = Connect.getConn();
			PreparedStatement stmt;
			String query =  " Select Count(T.TreningsoktID) FROM Treningsokt as T " + 
						    "INNER JOIN BestarAv as B ON T.TreningsoktID = B.TreningsoktID " + 
							"INNER JOIN Ovelse as O ON B.OvelseID = O.OvelseID " + 
							"WHERE O.OvelseID = ?";
			stmt = conn.prepareStatement(query);
			stmt.setInt(1, eid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1));
				return rs.getInt(1);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
