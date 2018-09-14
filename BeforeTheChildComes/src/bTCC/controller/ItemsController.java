package bTCC.controller;

import java.net.URL;
import java.util.ResourceBundle;

import bTCC.model.Items;
import bTCC.model.MyFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.CreateThePane;
import utils.Reader;
import utils.Writer;

public class ItemsController implements Initializable {

	@FXML
	private Button backToMainMenu;

	@FXML
	private Button deleteItems;

	@FXML
	private TextField eventDescription;

	@FXML
	private Button addEvent;

	@FXML
	private TextField itemPrice;

	@FXML
	private TableView<Items> itemsTable;
	ObservableList<Items> itemList = FXCollections.observableArrayList();
	@FXML
	private TableColumn<Items, String> taskTable;

	@FXML
	private TableColumn<Items, Double> priceTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		readFromFile();
		selectTable();
	}

	@FXML
	void deleteItems() {
		deleteItemWithSelectedId();
	}

	private void deleteItemWithSelectedId() {
		int itemSelected = itemsTable.getSelectionModel().getSelectedIndex();
		if (itemSelected >= 0)
			itemsTable.getItems().remove(itemSelected);
	}

	@FXML
	void backToMainMenu() {
		CreateThePane pane = new CreateThePane();
		pane.makeNewPane(backToMainMenu, "MainPane.fxml");
		saveAllToFile();
	}

	@FXML
	void addEvent() {
		if (inputTextIsNotEmpty() && inputTextIsNotNull() && isPriceParseToDouble()) {
			double parsePriceToDouble = checkPriceValue();
			itemList.add(new Items(eventDescription.getText(), parsePriceToDouble));
			itemsTable.setItems(itemList);
			refresh();
		}
	}

	private void selectTable() {
		taskTable.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		priceTable.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

	private boolean inputTextIsNotNull() {
		return eventDescription.getText() != null;
	}

	private boolean inputTextIsNotEmpty() {
		return !eventDescription.getText().isEmpty();
	}

	private boolean isPriceParseToDouble() {
		String newPrice = changeCommaIntoDot();
		try {
			Double.parseDouble(newPrice);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private double parseToDouble() {
		String newPrice = changeCommaIntoDot();
		double itemPriceTextToDouble = Double.parseDouble(newPrice);
		return itemPriceTextToDouble;
	}

	private String changeCommaIntoDot() {
		return itemPrice.getText().replace(",", ".");
	}

	private double checkPriceValue() {
		double itemPriceTextToDouble;
		if (isPriceEmpty()) {
			itemPriceTextToDouble = 0;
		} else {
			itemPriceTextToDouble = parseToDouble();
		}
		return itemPriceTextToDouble;
	}

	private boolean isPriceEmpty() {
		return itemPrice.getText().isEmpty();
	}

	private void refresh() {
		eventDescription.setText("");
		itemPrice.setText("0");
	}

	private  void saveAllToFile() {
		try (Writer writer = new Writer(MyFile.ITEM_FILE_NAME);) {
			writer.saveItemListToFile(itemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readFromFile() {
		try (Reader reader = new Reader(MyFile.ITEM_FILE_NAME);) {
			reader.readItemListFromFile(itemList);
			itemsTable.setItems(itemList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
