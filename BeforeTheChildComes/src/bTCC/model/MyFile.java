package bTCC.model;

import java.io.File;
import java.io.IOException;

public class MyFile {
	public static final String TASK_FILE_NAME = "toDoList.txt";
	public static final String ITEM_FILE_NAME = "Items.txt";
	private File toDoFile;
	private File itemFile;
	
	public MyFile() {
			toDoFile = new File(TASK_FILE_NAME);
			itemFile = new File(ITEM_FILE_NAME);
		
	}

	public void createNewFile(File file) {
		
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public File getToDoFile() {
		return toDoFile;
	}

	public File getItemFile() {
		return itemFile;
	}

}
