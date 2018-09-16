package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import bTCC.model.Items;
import javafx.collections.ObservableList;

public class Reader implements AutoCloseable {

	private FileReader fileReader;
	private BufferedReader bufferedReader;

	public Reader(String fileName) {
		try {
			fileReader = new FileReader(fileName);
			bufferedReader = new BufferedReader(fileReader);
		} catch (FileNotFoundException e) {

		}
	}

	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}

	@Override
	public void close() throws Exception {
		bufferedReader.close();
		fileReader.close();
	}

	public void readItemListFromFile(ObservableList<Items> itemList) throws NumberFormatException, IOException {
		String inputFromFile;
		while ((inputFromFile = bufferedReader.readLine()) != null) {
			double doubleInput = Double.parseDouble(bufferedReader.readLine());
			itemList.add(new Items(inputFromFile, doubleInput));
		}
	}
}