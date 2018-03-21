package databasePackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserGuiController implements Initializable {

	// Register tab
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

	WorkoutCounter count = new WorkoutCounter();
	CreateExGroups CEG = new CreateExGroups();
	AntallTreningsøkter tOkt = new AntallTreningsøkter();
	ResultLog resLog = new ResultLog();
	RegisterExercise regEx = new RegisterExercise();
	private Main main;
	
	
	// Henter apparater i en liste
	public ObservableList<String> getApparater() {
		List<String> apparatus = regEx.catchApparatus();
		ObservableList<String> chooseApparatus = FXCollections.observableArrayList(apparatus);
		return chooseApparatus;
	}
	
	public ObservableList<String> getUsername() {
		List<String> username = resLog.createUserList();
		ObservableList<String> userNames = FXCollections.observableArrayList(username);
		return userNames;
	}
	
	public ObservableList<String> getOvelser() {
		List<String> exe = resLog.createExerciseList();
		ObservableList<String> exercises = FXCollections.observableArrayList(exe);
		return exercises;
	}
	
	
	// Sets main
	public void setMain(Main main) {
		this.main = main;
	}

	
	// *************** INITIALIZE ************
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Setter apparater inn i chooseApparater-dropdown
		chooseApparat.setItems(getApparater());
		userName.setItems(getUsername());
		ovelse.setItems(getOvelser());	
		o1.setItems(getOvelser());
		o2.setItems(getOvelser());
		o3.setItems(getOvelser());
		o4.setItems(getOvelser());
		o5.setItems(getOvelser());
		counterUser.setItems(getUsername());
		counterExe.setItems(getOvelser());
	}
	
	
	// *************** REGISTRERE ************
	
	// Legger til nytt apparat
	public void addNewApparat() {

        String navn = setApparatName.getText();
        String beskrivelse = setApparatDesc.getText();
        
        try {
        		RegisterApparatus regAp = new RegisterApparatus();
			regAp.registerNewApparatus(navn, beskrivelse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	// Legger til ny øvelse
	public void addNewExercise() {
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

	
	// *************** TRENINGSØKTER ************
	
	public ObservableList<Table> getData(ArrayList<ArrayList<String>> okter) {
		ObservableList<Table> workT = FXCollections.observableArrayList();
		for (int i = 0; i<okter.size(); i++) {
				String a = okter.get(i).get(0);
				String b = okter.get(i).get(1);
				String c = okter.get(i).get(2);
				String d = okter.get(i).get(3);
				String e = okter.get(i).get(4);;
				String f = okter.get(i).get(5);
				String g = okter.get(i).get(6);
				workT.add(new Table(a, b, c, d, e, f, g));
		}		
		return workT;
	}		
	
	
	// Henter siste n øvelser
	public void getLastWorkouts() {
		int n = Integer.parseInt(workoutNumber.getText());
		
		workID.setCellValueFactory(new PropertyValueFactory<Table, String>("rID"));
		workDate.setCellValueFactory(new PropertyValueFactory<Table, String>("rDate"));
		workTime.setCellValueFactory(new PropertyValueFactory<Table, String>("rTime"));
		workDuration.setCellValueFactory(new PropertyValueFactory<Table, String>("rDur"));
		workEx.setCellValueFactory(new PropertyValueFactory<Table, String>("rEx"));
		workShape.setCellValueFactory(new PropertyValueFactory<Table, String>("rShape"));
		workNote.setCellValueFactory(new PropertyValueFactory<Table, String>("rNote"));
		
		ArrayList<ArrayList<String>> okter = tOkt.getExercises(n);
		
		workoutTable.setItems(getData(okter));
		
	}
	
	
	// *************** RESULTATLOGG ************
	
	@FXML private ChoiceBox<String> userName;
	@FXML private ChoiceBox<String> ovelse;
	@FXML private TextField sDate;
	@FXML private TextField eDate;
	
	@FXML private TableView<Results> results;
	@FXML private TableColumn<Results, String> resDate;
	@FXML private TableColumn<Results, String> resRes;
	
	
	
	public void showResults() throws ParseException {
        String user = String.valueOf(userName.getValue());
        String exercise = String.valueOf(ovelse.getValue());
        String startDate = sDate.getText();
        String endDate = eDate.getText();
        
		resDate.setCellValueFactory(new PropertyValueFactory<Results, String>("rDate"));
		resRes.setCellValueFactory(new PropertyValueFactory<Results, String>("rRes"));
		
		
//		ArrayList<ArrayList<String>> okter = tOkt.getExercises(n);
		ArrayList<ArrayList<String>> ress = resLog.listOfResults(startDate, endDate, exercise, user);
		System.out.println(ress);
		results.setItems(getResults(ress));

	}

	
	public ObservableList<Results> getResults(ArrayList<ArrayList<String>> ress) {
		ObservableList<Results> resU = FXCollections.observableArrayList();
		for (int i = 0; i<ress.size(); i++) {
			String a = ress.get(i).get(0);
			String b = ress.get(i).get(1);
			resU.add(new Results(a, b));
			System.out.println(resU);
		}		
		return resU;
	}
	
	
	// *************** ØVELSESGRUPPER ************

	@FXML private TextField grpName;
	@FXML private TextField grpUserName;
	@FXML private ChoiceBox<String> o1;
	@FXML private ChoiceBox<String> o2;
	@FXML private ChoiceBox<String> o3;
	@FXML private ChoiceBox<String> o4;
	@FXML private ChoiceBox<String> o5;
	
	public void newOvelsesGroup() throws SQLException {	
		String name = grpName.getText();
		String brukerID = grpUserName.getText();
		String ov1 = String.valueOf(o1.getValue());
		String ov2 = String.valueOf(o2.getValue());
		String ov3 = String.valueOf(o3.getValue());
		String ov4 = String.valueOf(o4.getValue());
		String ov5 = String.valueOf(o5.getValue());
		CEG.registerExGroup(name, brukerID, ov1, ov2, ov3, ov4, ov5);
		
	}
	
	
	// *************** TELLER ************

	@FXML private ChoiceBox<String> counterUser;
	@FXML private ChoiceBox<String> counterExe;
	@FXML private Label countNumber;
	
	public void showCounts() {
		String Oving = String.valueOf(counterExe.getValue());
		String bruker = String.valueOf(counterUser.getValue());
		
		int counts = count.CountWorkouts(Oving, bruker);
		String c = String.valueOf(counts);
		
		countNumber.setText(c);
	}
	

	
	

			
}

