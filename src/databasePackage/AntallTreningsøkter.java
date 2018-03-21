package databasePackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AntallTreningsøkter<antallTreningsøkter> {

//	Treningsøkter trening = new Treningsøkter(); // henter treningsøkter fra treningsøkter-klassen
	
	private static Connection conn;
	private static ArrayList<ArrayList<String>> treningsøkter = new ArrayList<>();
	private static ArrayList<String> notatFromØkt = new ArrayList<>();
 	private int countEx = 1;
	
	
	public AntallTreningsøkter() {
	}
	
	
	public static ArrayList<ArrayList<String>> getExercises(Integer n) {
		conn = Connect.getConn();
		
		try {	
			//create statement 
			Statement myStmt = conn.createStatement();
			   
			//execute sql query
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM (SELECT * FROM Treningsøkt ORDER BY TreningsøktID DESC LIMIT " + n + ") sub ORDER BY TreningsøktID DESC") ;

			
			//results set
			while (myRs.next()) {
				ArrayList<String> tempTreningsøkt = new ArrayList<>();
				tempTreningsøkt.add(String.valueOf((myRs.getString("TreningsøktID"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("Dato"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("Tidspunkt"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("Varighet"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("Øvelser"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("PersonligForm"))));
				
				treningsøkter.add(tempTreningsøkt);
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
		n.getNotat();
		System.out.println("\n");
		n.getExercises(2);
	}
	
}
