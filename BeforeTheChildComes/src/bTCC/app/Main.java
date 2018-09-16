package bTCC.app;

import bTCC.model.MyFile;
import javafx.application.Application;
import javafx.stage.Stage;
import utils.CreateThePane;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		CreateThePane pane = new CreateThePane();
		pane.createMainPane(primaryStage, "MainPane.fxml");
		createNewFile();		
	}

	private void createNewFile() {
		MyFile file = new MyFile();	
		file.createNewFile(file.getItemFile());
		file.createNewFile(file.getToDoFile());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}