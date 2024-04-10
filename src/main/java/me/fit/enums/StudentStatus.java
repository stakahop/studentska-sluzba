package me.fit.enums;

public enum StudentStatus {
	EXISTS("Student vec postoji");
	
	private String label;

	private StudentStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
