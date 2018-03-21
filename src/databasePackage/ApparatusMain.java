package databasePackage;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;

public class ApparatusMain {

	public static void main(String[] args) {
		try {
			/*
			 * RegisterApparatus test = new RegisterApparatus(); //test.createID();
			 * RegisterExercise ExTest = new RegisterExercise(); //ExTest.catchApparatus();
			 * //ExTest.registerNewExercise("Roing","Romaskin",0,0); Workout wtest = new
			 * Workout(); wtest.createExerciseList(); wtest.createUserList();
			 * wtest.registerNewWorkout("2018-03-21", "14:06:23", 2, "Roing", 7, "Romaskin",
			 * "K�re");
			 */

			ResultLog rTest = new ResultLog();
			rTest.listOfResults("2018-03-01", "2018-03-24", "Roing");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
