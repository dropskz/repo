package bTCC.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import bTCC.model.LocalEvent;
import bTCC.model.MyFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import utils.CreateThePane;
import utils.Reader;
import utils.Writer;

public class ToDoController implements Initializable {

	@FXML
	private Button addEvent;

	@FXML
	private Button deleteEvent;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TextField eventDescription;

	@FXML
	private Button returnToMenu;

	@FXML
	private ListView<LocalEvent> eventList;

	ObservableList<LocalEvent> list = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePicker.setValue(LocalDate.now());

		readTaskListFromFile();
	}

	@FXML
	private void addEvent(Event e) {

		if (inputTextIsNotEmpty() && inputTextIsNotNull()) {
			list.add(new LocalEvent(datePicker.getValue().toString(), eventDescription.getText()));
			eventList.setItems(list);
			refresh();
		}
	}

	private boolean inputTextIsNotNull() {
		return eventDescription.getText() != null;
	}

	private boolean inputTextIsNotEmpty() {
		return !eventDescription.getText().isEmpty();
	}

	private void refresh() {
		datePicker.setValue(LocalDate.now());
		eventDescription.setText("");
	}

	@FXML
	void deleteEvent(ActionEvent event) {
		deleteEventWithSelectedId();
	}

	private void deleteEventWithSelectedId() {
		int eventSelected = eventList.getSelectionModel().getSelectedIndex();
		if(eventSelected >=0) {
		eventList.getItems().remove(eventSelected);
		}
	}

	@FXML
	private void returnToMenu() {
		makeNewMainPane();
		saveAllToFile();
	}

	private void makeNewMainPane() {
		CreateThePane pane = new CreateThePane();
		pane.makeNewPane(returnToMenu, "MainPane.fxml");
	}

	private void saveAllToFile() {
		try (Writer writer = new Writer(MyFile.TASK_FILE_NAME);) {
			writer.saveTaskListToFile(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readTaskListFromFile() {
		String test = "";
		try (Reader reader = new Reader(MyFile.TASK_FILE_NAME);) {
			while ((test = reader.getBufferedReader().readLine()) != null) {
				list.add(new LocalEvent(dataFromInputLine(test), taskFromInputLine(test)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		eventList.setItems(list);

	}

	private String taskFromInputLine(String test) {
		return test.substring(13, test.length());
	}

	private String dataFromInputLine(String test) {
		return test.substring(0, 10);
	}

}
