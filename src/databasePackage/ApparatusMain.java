package databasePackage;

import java.sql.SQLException;

public class ApparatusMain {

	public static void main(String[] args) {
		try {
			RegisterApparatus test = new RegisterApparatus();
			test.createID();
			RegisterExercise ExTest = new RegisterExercise();
			ExTest.catchApparatus();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
