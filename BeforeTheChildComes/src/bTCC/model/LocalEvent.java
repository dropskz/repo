package bTCC.model;

public class LocalEvent {
	private String description;
	private String date;
	
	public LocalEvent() {}
	
	public LocalEvent(String date, String desc) {
		this.setDate(date);
		this.setDescription(desc);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		
		return this.getDate() +  "   " + this.getDescription();
	}
	
	
}
