package utils;

import java.io.IOException;

import bTCC.model.AlertBox;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateThePane {
	private static final String APP_NAME = "Before The Child Comes v0.3";
	private Stage stage;
	private Parent root;
	private Scene scene;

	public CreateThePane() {
	}

	public void createMainPane(Stage primaryStage, String paneName) {
		stage = primaryStage;
		createPageContent(paneName);
	}

	public void makeNewPane(Button buttonName, String paneName) {
		stage = (Stage) buttonName.getScene().getWindow();
		createPageContent(paneName);
	}


	private void createPageContent(String paneName) {
		makeNewScene(paneName);
		showNewStage(scene);
	}

	private void makeNewScene(String paneName) {
		try {
			root = FXMLLoader.load(getClass().getResource("/bTCC/view/" + paneName));
			scene = new Scene(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showNewStage(Scene scene) {
		stage.setScene(scene);
		closeWindowRequest();
		stage.setTitle(APP_NAME);
		stage.show();
	}

	private void closeWindowRequest() {
		stage.setOnCloseRequest(e -> {
			e.consume();
			closeProgram();
		});
	}

	private void closeProgram() {
		boolean result = AlertBox.display("Awaryjne zamykanie programu",
				"Zamknięncie aplikacji spowoduje utratę danych\n      sesji, czy mimo to chcesz kontunuować");
		if (result) {
			stage.close();
		}
	}
	
}
