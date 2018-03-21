package databasePackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AntallTreningsøkter<antallTreningsøkter> {

	private static Connection conn;
	private static ArrayList<ArrayList<String>> treningsøkter = new ArrayList<>();

	public AntallTreningsøkter() {
	}

	public static ArrayList<ArrayList<String>> getExercises(Integer n) {
		conn = Connect.getConn();

		try {
			// create statement
			Statement myStmt = conn.createStatement();

			// execute sql query
			// ResultSet myRs = myStmt.executeQuery("SELECT * FROM (SELECT * FROM
			// Treningsøkt ORDER BY TreningsøktID DESC LIMIT " + n + ") sub ORDER BY
			// TreningsøktID DESC") ;
			ResultSet myRs = myStmt.executeQuery(
					"Select N.Informasjon, TR.TreningsoktID, TR.Dato, TR.Tidspunkt, TR.Varighet, TR.Ovelser, TR.PersonligForm from Notat as N \n"
							+ "INNER JOIN Treningsokt AS TR ON TR.TreningsoktID = N.TreningsoktID ORDER BY TreningsoktID DESC LIMIT "
							+ n);

			// results set
			while (myRs.next()) {
				ArrayList<String> tempTreningsøkt = new ArrayList<>();
				tempTreningsøkt.add(String.valueOf((myRs.getString("TreningsoktID"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("Dato"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("Tidspunkt"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("Varighet"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("Ovelser"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("PersonligForm"))));
				tempTreningsøkt.add(String.valueOf((myRs.getString("informasjon"))));

				treningsøkter.add(tempTreningsøkt);
			}
			System.out.println(treningsøkter);
			return treningsøkter;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		AntallTreningsøkter n = new AntallTreningsøkter();
		System.out.println("\n");
		n.getExercises(2);
	}
}
