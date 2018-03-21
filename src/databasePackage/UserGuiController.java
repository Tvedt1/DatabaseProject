package databasePackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class UserGuiController implements Initializable{

	
	// Register tab
	@FXML private TextField setApparatName;
	@FXML private TextField setApparatDesc;
	@FXML private TextField setExerciseName;
	@FXML private TextField setExerciseKg;
	@FXML private TextField setExerciseSets;

	private Main main;
	
	
    
	public void setMain(Main main) {
		this.main = main;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

    // Legger til nytt apparat
	public void addNewApparat() {
        String Navn = setApparatName.getText();
        String beskrivelse = setApparatDesc.getText();
        
        try {
        		RegisterApparatus reg = new RegisterApparatus();
			reg.registerNewApparatus(Navn, beskrivelse);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	// Legger til ny øvelse
	public void addNewExercise() {
		
	}
	
}

	
	
