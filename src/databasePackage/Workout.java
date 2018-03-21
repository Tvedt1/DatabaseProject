package databasePackage;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Workout {
	
	List<String> users = new ArrayList<>();
	List<String> exe = new ArrayList<>();
	
	public int createID() throws SQLException {
		int id = -1;
		Connection conn = Connect.getConn();
		PreparedStatement stmt = conn.prepareStatement("Select Count(TreningsoktID) as TreningsoktCount from Treningsokt"); 
		System.out.println(stmt);
		
		ResultSet rs = stmt.executeQuery();
		rs.next();
		System.out.println(rs.getInt("TreningsoktCount"));
		
		id = rs.getInt("TreningsoktCount");
		stmt.close();
		return id;
	}
	
	public void registerNewWorkout(String Dato, String tid, int varighet, String Øvelser, int form, String øvelse, String username) {
		try {
			int id = createID() + 1;
			int uid = getUserID(username);
			int eid = getExerciseID(øvelse);
			
			
			String s = Dato;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date uDate = sdf.parse(s);
			Date d = new Date(uDate.getTime());
			
			Time time = java.sql.Time.valueOf(tid);
			
			Connection conn = Connect.getConn();
			Statement stmt = conn.createStatement();
			String sql = String.format("INSERT INTO `Treningsokt`(`TreningsoktID`, `Dato`, `Tidspunkt`, `Varighet`, `Ovelser`, `PersonligForm`, `OvelseID`, `BrukerID`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')", id, d, time, varighet, Øvelser, form, eid, uid);
			stmt.executeUpdate(sql);
			stmt.close();
			connectToExe(id,eid);
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void connectToExe(int wid, int eid) throws SQLException {
		Connection conn = Connect.getConn();
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO `Inneholder`(`OvelsegruppeID`, `OvelseID`) VALUES ('%s','%s')", wid,eid);
		stmt.executeUpdate(sql);
		stmt.close();
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
