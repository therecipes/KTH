package com.project.kth.pwpr3d.app.parts;

public class MyFiles {
	// For storing any String description for Tree
	private String summary = "";
	private String description = "";
	private int Id;
	public static int counter = 0;
	public boolean isFile = false;

	// Functions including Getters and Setters For class
	public MyFiles(String sumry) {
		this.setSummary(sumry);
		Id = counter++;
	}

	public MyFiles(String sumry, String desc) {
		this.setSummary(sumry);
		this.setDescription(desc);
		Id = counter++;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return this.Id;
	}
}
