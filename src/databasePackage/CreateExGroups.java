package databasePackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CreateExGroups {

	// Lage øvelsegrupper og finne øvelser som er i samme gruppe.

	private static Workout workout = new Workout();
	private static Connection conn;

	private static ArrayList<ArrayList<String>> exerciseGroups = new ArrayList<>();
	
	
	public int createID() throws SQLException {
		int id = -1;
		Connection conn = Connect.getConn();
		PreparedStatement stmt = conn.prepareStatement("Select Count(OvelsegruppeID) as OvelsegruppeCount from Ovelsegrupper");
		System.out.println(stmt);

		ResultSet rs = stmt.executeQuery();
		rs.next();
		System.out.println(rs.getInt("OvelsegruppeCount"));

		id = rs.getInt("OvelsegruppeCount");
		stmt.close();
		return id;
	}
	
	
	public void registerExGroup(String name, String brukerID ,String... args) throws SQLException {
		int bid = workout.getUserID(brukerID);
		int id = createID() + 1;
		Connection conn = Connect.getConn();
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO `Ovelsegrupper`(`OvelsegruppeID`, `Navn`, `BrukerID`) VALUES ('%s','%s','%s')", id ,name, bid);
		stmt.executeUpdate(sql);
		stmt.close();

		for (int i = 0; i < args.length; i++) {
			int eid = workout.getExerciseID(args[i]);
			connectToExe(id,eid);
		}
	}
	
	public void connectToExe(int wid, int eid) throws SQLException {
		Connection conn = Connect.getConn();
		Statement stmt = conn.createStatement();
		String sql = String.format("INSERT INTO `Inneholder`(`OvelsegruppeID`, `OvelseID`) VALUES ('%s','%s')", wid,
				eid);
		stmt.executeUpdate(sql);
		stmt.close();
	}

	public ArrayList<ArrayList<String>> groupOfExercises(){
		conn = Connect.getConn();
		
		try {
			Statement myStmt = conn.createStatement();
			ResultSet myRs = myStmt.executeQuery("SELECT O.Navn, OV.Navn FROM Ovelsegrupper as O Inner JOIN Inneholder AS I ON O.OvelsegruppeID = I.OvelsegruppeID Inner JOIN Ovelse as OV ON I.OvelseID = OV.OvelseID");
			
			while (myRs.next()) {
				ArrayList<String> tempExGrouup = new ArrayList<>();
				tempExGrouup.add(String.valueOf((myRs.getString("O.Navn"))));
				tempExGrouup.add(String.valueOf((myRs.getString("OV.Navn"))));
				exerciseGroups.add(tempExGrouup);
			}
		}
		catch (Exception e) {
			
		}
		System.out.println(exerciseGroups);
		return exerciseGroups;
		
		
		
		
	}

	public static void main(String[] args) throws SQLException {
		CreateExGroups g = new CreateExGroups();
		
		g.registerExGroup( "kondisjon", "Kåre","Roing","Roing");
	}

}
