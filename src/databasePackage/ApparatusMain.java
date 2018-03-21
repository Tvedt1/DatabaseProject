package databasePackage;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

public class ApparatusMain {

	public static void main(String[] args) {
		try {
			RegisterApparatus test = new RegisterApparatus();
			test.createID();
			RegisterExercise ExTest = new RegisterExercise();
			ExTest.catchApparatus();
			//ExTest.registerNewExercise("Roing","Romaskin",0,0);
			Workout wtest = new Workout();
			wtest.createExerciseList();
			wtest.createUserList();
			//wtest.registerNewWorkout("2018-03-21", "14:06:23", 2, "Roing", 7, "Romaskin", "Kåre");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
