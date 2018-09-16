package bTCC.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import utils.CreateThePane;

public class MainController implements Initializable {

	private CreateThePane pane;

	@FXML
	private AnchorPane MainSite;

	@FXML
	private Button closeWindow;

	@FXML
	private Button toDoList;

	@FXML
	private Button toBuyList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void toDoList(Event e) {
		makeNewPane(toDoList, "ToDoPane.fxml");
	}

	@FXML
	private void toBuyList() {
		makeNewPane(toBuyList, "ItemsPane.fxml");
	}

	@FXML
	private void closeWindow(ActionEvent event) {
		Platform.exit();
	}

	private void makeNewPane(Button button, String path) {
		pane = new CreateThePane();
		pane.makeNewPane(button, path);
	}
}
