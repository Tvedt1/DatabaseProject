package databasePackage;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		userGuiWindow();
	}

	// Opens login window
	public void userGuiWindow() {
		try {
			// View
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("UserGuiView.fxml"));
			TabPane pane = loader.load();

			// Controller
			UserGuiController userGuiController = loader.getController();
			userGuiController.setMain(this);

			// Scene on stage
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Treningsdagbok");
			primaryStage.show();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
