package databasePackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AntallTreningsøkter {

//	Treningsøkter trening = new Treningsøkter(); // henter treningsøkter fra treningsøkter-klassen
	
	private static Connection conn;
	private static ArrayList<Integer> treningsøkter = new ArrayList<>();
	private static ArrayList<String> notatFromØkt = new ArrayList<>();
 	private static int countEx = 0;
	
	
	public AntallTreningsøkter() {
	}
	
	
	public static ArrayList<Integer> getExercises(Integer n) {
		conn = Connect.getConn();
		
		try {	
			//create statement 
			Statement myStmt = conn.createStatement();
			   
			//execute sql query
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM (SELECT * FROM Treningsøkt ORDER BY TreningsøktID DESC LIMIT " + n + ") sub\n" + " ORDER BY TreningsøktID DESC") ;

			
			//results set
			while (myRs.next()) {
				treningsøkter.add((myRs.getInt("TreningsøktID")));
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
	

	
	public String getNotat() {
		conn = Connect.getConn();
		
		for (int i = 0; i < treningsøkter.size(); i++) {
			countEx ++;
		}
		
		try {
			Statement s = conn.createStatement();
			

			ResultSet rs = s.executeQuery("SELECT informasjon from notat WHERE TreningsøktID =" + countEx);
			
			while (rs.next()) {
				notatFromØkt.add("informasjon");
			}
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
