package databasePackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AntallTreningsøkter {

//	Treningsøkter trening = new Treningsøkter(); // henter treningsøkter fra treningsøkter-klassen
	
	private static Connection conn;
	private static ArrayList<String> treningsøkter = new ArrayList<>();
	private static ArrayList<String> notatFromØkt = new ArrayList<>();
 	private int countEx = 1;
	
	
	public AntallTreningsøkter() {
	}
	
	
	public static ArrayList<String> getExercises(Integer n) {
		conn = Connect.getConn();
		
		try {	
			//create statement 
			Statement myStmt = conn.createStatement();
			   
			//execute sql query
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM (SELECT * FROM Treningsøkt ORDER BY TreningsøktID DESC LIMIT " + n + ") sub ORDER BY TreningsøktID ASC") ;

			
			//results set
			while (myRs.next()) {
				treningsøkter.add(String.valueOf((myRs.getString("TreningsøktID"))));
				treningsøkter.add(String.valueOf((myRs.getString("Dato"))));
				treningsøkter.add(String.valueOf((myRs.getString("Tidspunkt"))));
				treningsøkter.add(String.valueOf((myRs.getString("Varighet"))));
				treningsøkter.add(String.valueOf((myRs.getString("Øvelser"))));
				treningsøkter.add(String.valueOf((myRs.getString("PersonligForm"))));
				//countEx ++;
				//treningsøkter.add((myRs.getInt("Ant treningsøkter: "+countEx)));
				
				
			}
			System.out.println(treningsøkter);
			return treningsøkter;
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}
	

	
	public ArrayList<String> getNotat() {
		conn = Connect.getConn();
		
		for (int i = 0; i < treningsøkter.size(); i+=5) {
			countEx +=5;
		}
		
		try {
			Statement s = conn.createStatement();
			

			ResultSet rs = s.executeQuery("SELECT informasjon from notat WHERE TreningsøktID =" + countEx);
			
			while (rs.next()) {
				notatFromØkt.add(rs.getString("informasjon"));
			}
			return notatFromØkt;     
		}
		catch (Exception exc) {
			exc.getStackTrace();
		}
		return null;
	}

	
	
	public static void main(String[] args) {
		AntallTreningsøkter n = new AntallTreningsøkter();
		n.getExercises(1);
	}
	
}
