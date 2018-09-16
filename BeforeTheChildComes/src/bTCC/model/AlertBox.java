package bTCC.model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	private static Stage stage;
	private Parent root;
	private Scene scene;
	
	public AlertBox() {}

	public void makeNewPane() {
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		createPageContent();	
	}

	private void createPageContent() {
		makeNewScene();
		showNewStage(scene);
	}

	private void makeNewScene() {
		try {
			root = FXMLLoader.load(getClass().getResource("/bTCC/view/AlertBox.fxml"));
			scene = new Scene(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showNewStage(Scene scene) {
		stage.setScene(scene);
		stage.setTitle("Awaryjne zamykanie aplikacji");
		stage.show();
	}
	public static void close() {
		stage.close();
	}	
}
