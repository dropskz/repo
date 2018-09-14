package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import bTCC.model.Items;

public class Writer implements AutoCloseable {

	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;

	public Writer(String fileName) throws IOException {
		fileWriter = new FileWriter(fileName);
		bufferedWriter = new BufferedWriter(fileWriter);
	}

	public BufferedWriter getBufferedWriter() {
		return bufferedWriter;
	}

	@Override
	public void close() throws Exception {
		bufferedWriter.close();
		fileWriter.close();
	}

	public void saveTaskListToFile(List<?> list) throws IOException {
		for (int i = 0; i < list.size(); i++) {
			bufferedWriter.write(list.get(i).toString());
			bufferedWriter.newLine();
		}
	}

	public void saveItemListToFile(List<Items> list) throws IOException {
		for (int i = 0; i < list.size(); i++) {
			saveItemToFile(list, i);
		}
	}

	private void saveItemToFile(List<Items> list, int i) throws IOException {
		writeItemName(list, i);
		writeItemPrice(list, i);
	}

	private void writeItemPrice(List<Items> list, int i) throws IOException {
		bufferedWriter.write(String.valueOf(list.get(i).getPrice()));
		bufferedWriter.newLine();
	}

	private void writeItemName(List<Items> list, int i) throws IOException {
		bufferedWriter.write(list.get(i).getItemName());
		bufferedWriter.newLine();
	}

}
