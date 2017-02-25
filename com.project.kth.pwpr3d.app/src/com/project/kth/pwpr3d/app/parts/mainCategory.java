package com.project.kth.pwpr3d.app.parts;

import java.util.ArrayList;
import java.util.List;

public class mainCategory {
	// Class for parent Node for Tree
	private String name;
	private int sort;
	String DummyProjectNames[] = { "Grid Connection", "Networking" };
	// Array List For storing List of Child Nodes
	private List<MyFiles> files = new ArrayList<MyFiles>();
	private String setName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSort() {

		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public List<MyFiles> getFiles() {
		return files;
	}

	public String add(String f) {
		for (String anItem : DummyProjectNames) {
			f = anItem;
			setName = f;
		}
		// TODO Auto-generated method stub
		return setName;

	}
}