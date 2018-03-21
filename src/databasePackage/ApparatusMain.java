package databasePackage;

import java.sql.SQLException;

public class ApparatusMain {

	public static void main(String[] args) {
		try {
			RegisterApparatus test = new RegisterApparatus();
			test.createID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
