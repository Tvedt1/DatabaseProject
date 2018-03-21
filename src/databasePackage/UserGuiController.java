package databasePackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class UserGuiController implements Initializable{

	
	// Register tab
	@FXML private TextField setApparatName;
	@FXML private TextField setApparatDesc;
	@FXML private TextField setExerciseName;
	@FXML private TextField setExerciseKg;
	@FXML private TextField setExerciseSets;
	@FXML private ChoiceBox chooseApparat;
	@FXML private Tab workouts;

	private Main main;
	
	RegisterExercise regEx = new RegisterExercise();
	List<String> apparatus = regEx.catchApparatus();
	ObservableList<String> chooseApparatus = FXCollections.observableArrayList(apparatus);
	
    
	public void setMain(Main main) {
		this.main = main;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chooseApparat.setItems(chooseApparatus);
		
	}


    // Legger til nytt apparat
	public void addNewApparat() {
        String navn = setApparatName.getText();
        String beskrivelse = setApparatDesc.getText();
        
        if ((!navn.equals("")) || (!beskrivelse.equals(""))){
	        try {
	        		RegisterApparatus regAp = new RegisterApparatus();
				regAp.registerNewApparatus(navn, beskrivelse);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        else {
    			JOptionPane.showMessageDialog(null, "Du må fylle inn tomme felter");
        }
	}
	
	
	// Legger til ny øvelse
	public void addNewExercise() {
        String navn = setExerciseName.getText();
        String anavn = String.valueOf(chooseApparat.getValue());
        int kilo = Integer.parseInt(setExerciseKg.getText());
        int sets = Integer.parseInt(setExerciseSets.getText());
        
        if (!navn.equals("")) {
	        	try {
	        		regEx.RegisterNewExercise(navn, anavn, kilo, sets);
	        	} catch (SQLException e) {
	        		e.printStackTrace();
	        	}        	
        }
        else {
        		JOptionPane.showMessageDialog(null, "Du må fylle inn et navn");
        }
	}
	
	
	public void getLastWorkouts() {
		workouts.setText("LOL");
		int n = 5;
//		ArrayList<String> workoutList = Bjørnfunksjon(n);
	}
	
}

	
	
