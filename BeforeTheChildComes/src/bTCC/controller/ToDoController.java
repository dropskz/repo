package bTCC.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TableView<LocalEvent> eventTable;
	ObservableList<LocalEvent> eventList = FXCollections.observableArrayList();

	@FXML
	private TableColumn<LocalEvent, Date> dateTable;

	@FXML
	private TableColumn<LocalEvent, String> descriptionTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		datePicker.setValue(LocalDate.now());
		selectTable();
		readTaskListFromFile();
	}

	@FXML
	private void addEvent(Event e) {
		if (inputTextIsNotEmpty() && inputTextIsNotNull()) {
			eventList.add(new LocalEvent(datePicker.getValue(), eventDescription.getText()));
			eventTable.setItems(eventList);
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
		int eventSelected = eventTable.getSelectionModel().getSelectedIndex();
		if (eventSelected >= 0) {
			eventTable.getItems().remove(eventSelected);
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
			writer.saveTaskListToFile(eventList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readTaskListFromFile() {
		String test = "";
		try (Reader reader = new Reader(MyFile.TASK_FILE_NAME);) {
			while ((test = reader.getBufferedReader().readLine()) != null) {
				eventList.add(new LocalEvent(dateFromInputLine(test), taskFromInputLine(test)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		eventTable.setItems(eventList);
	}

	private LocalDate dateFromInputLine(String test) {
		String date = test.substring(0, 10);
		LocalDate local = LocalDate.parse(date);
		return local;
	}

	private String taskFromInputLine(String test) {
		return test.substring(13, test.length());
	}

	private void selectTable() {
		dateTable.setCellValueFactory(new PropertyValueFactory<>("date"));
		descriptionTable.setCellValueFactory(new PropertyValueFactory<>("description"));
	}
}
