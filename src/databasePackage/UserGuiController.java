package databasePackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserGuiController implements Initializable {

	// Register tab
<<<<<<< HEAD
	@FXML
	private TextField setApparatName;
	@FXML
	private TextField setApparatDesc;
	@FXML
	private TextField setExerciseName;
	@FXML
	private TextField setExerciseKg;
	@FXML
	private TextField setExerciseSets;
	@FXML
	private ChoiceBox chooseApparat;
	@FXML
	private Tab workouts;

	private Main main;

	RegisterExercise regEx = new RegisterExercise();
	List<String> apparatus = regEx.catchApparatus();
	ObservableList<String> chooseApparatus = FXCollections.observableArrayList(apparatus);

=======
	@FXML private TextField setApparatName;
	@FXML private TextField setApparatDesc;
	@FXML private TextField setExerciseName;
	@FXML private TextField setExerciseKg;
	@FXML private TextField setExerciseSets;
	@FXML private TextField workoutNumber;
	@FXML private ChoiceBox<String> chooseApparat;
	
	// TABLEVIEW & TABLECOLUMNS
	@FXML TableView<Table> workoutTable;
	@FXML TableColumn<Table, String> workID;
	@FXML TableColumn<Table, String> workDate;
	@FXML TableColumn<Table, String> workTime;
	@FXML TableColumn<Table, String> workDuration;
	@FXML TableColumn<Table, String> workEx;
	@FXML TableColumn<Table, String> workShape;
	@FXML TableColumn<Table, String> workNote;

	// TEST TABLE
	@FXML TableView<testTable> testTable;
	@FXML TableColumn<testTable, String> c1;
	@FXML TableColumn<testTable, String> c2;

	final ObservableList<testTable> data = FXCollections.observableArrayList(
			new testTable("Navn", "Kollonne"), 
			new testTable("Test", "Noe")
			);
	
	
	final ObservableList<Table> workT = FXCollections.observableArrayList(
			new Table("1", "2", "3", "4", "5", "6", "7")
			);

	
	RegisterExercise regEx = new RegisterExercise();
	private Main main;
	
	
	// Henter apparater i en liste
	public ObservableList<String> getApparater() {
		List<String> apparatus = regEx.catchApparatus();
		ObservableList<String> chooseApparatus = FXCollections.observableArrayList(apparatus);
		return chooseApparatus;
	}
	
	// Sets main
>>>>>>> d0d0efe93809cb3a82730e1e793144a7e77190b9
	public void setMain(Main main) {
		this.main = main;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
<<<<<<< HEAD
		chooseApparat.setItems(chooseApparatus);

	}
=======
		// Setter apparater inn i chooseApparater-dropdown
		chooseApparat.setItems(getApparater());
		
		c1.setCellValueFactory(new PropertyValueFactory<testTable, String>("r1"));
		c2.setCellValueFactory(new PropertyValueFactory<testTable, String>("r2"));

		testTable.setItems(data);
		
		
		workID.setCellValueFactory(new PropertyValueFactory<Table, String>("rID"));
		workDate.setCellValueFactory(new PropertyValueFactory<Table, String>("rDate"));
		workTime.setCellValueFactory(new PropertyValueFactory<Table, String>("rTime"));
		workDuration.setCellValueFactory(new PropertyValueFactory<Table, String>("rDur"));
		workEx.setCellValueFactory(new PropertyValueFactory<Table, String>("rEx"));
		workShape.setCellValueFactory(new PropertyValueFactory<Table, String>("rShape"));
		workNote.setCellValueFactory(new PropertyValueFactory<Table, String>("rNote"));
		
		workoutTable.setItems(workT);
	}
	
>>>>>>> d0d0efe93809cb3a82730e1e793144a7e77190b9

	// Legger til nytt apparat
	public void addNewApparat() {
<<<<<<< HEAD
		String navn = setApparatName.getText();
		String beskrivelse = setApparatDesc.getText();

		if ((!navn.equals("")) || (!beskrivelse.equals(""))) {
			try {
				RegisterApparatus regAp = new RegisterApparatus();
				regAp.registerNewApparatus(navn, beskrivelse);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Du må fylle inn tomme felter");
=======
        String navn = setApparatName.getText();
        String beskrivelse = setApparatDesc.getText();
        
        try {
        		RegisterApparatus regAp = new RegisterApparatus();
			regAp.registerNewApparatus(navn, beskrivelse);
		} catch (SQLException e) {
			e.printStackTrace();
>>>>>>> d0d0efe93809cb3a82730e1e793144a7e77190b9
		}
	}

	// Legger til ny øvelse
	public void addNewExercise() {
<<<<<<< HEAD
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
		} else {
			JOptionPane.showMessageDialog(null, "Du må fylle inn et navn");
		}
	}

	public void getLastWorkouts() {
		workouts.setText("LOL");
		int n = 5;
		// ArrayList<String> workoutList = Bjørnfunksjon(n);
	}
=======
        String navn = setExerciseName.getText();
        String anavn = String.valueOf(chooseApparat.getValue());
        int kilo = Integer.parseInt(setExerciseKg.getText());
        int sets = Integer.parseInt(setExerciseSets.getText());
        
        	try {
        		regEx.registerNewExercise(navn, anavn, kilo, sets);
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}        	
	}
	
	
	// Henter siste n øvelser
	public void getLastWorkouts() {
//		ArrayList<String> workoutList =
//		int n = Integer.parseInt(workoutNumber.getText());
				
	}
	
	
}
>>>>>>> d0d0efe93809cb3a82730e1e793144a7e77190b9

}
