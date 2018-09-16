package bTCC.model;

import java.time.LocalDate;


public class LocalEvent {
	private String description;
	private LocalDate date;
	
	public LocalEvent() {}
	
	public LocalEvent(LocalDate date, String desc) {
		this.setDate(date);
		this.setDescription(desc);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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
