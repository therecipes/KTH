package com.project.kth.pwpr3d.app.parts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
//Class for adding Dummy data

public class DummyDataProvider {
	private static DummyDataProvider dd = null;
	private static List<MyFiles> fil = new ArrayList<MyFiles>();
	// Parent Node for storing new ly opened f iles to its children
	public static mainCategory addedFiles = new mainCategory();

	public DummyDataProvider() {
		addedFiles.setName("New Added File");
	}

	public List<mainCategory> getCategories() {
		// Function for creating and retrieving parent Nodes of the Tree
		// Child Nodes are also being created here and are added to the parent
		// Nodes
		List<mainCategory> myCatList = new ArrayList<mainCategory>();
		mainCategory firstCat = new mainCategory();
		File dir = new File("H:/");
		File[] allFiles = dir.listFiles();
		firstCat.setName("F:");
		myCatList.add(firstCat);
		// Loading a Directory into a tree
		for (File anItem : allFiles) {
			MyFiles myfile = new MyFiles(anItem.getName(), anItem.getAbsolutePath());
			firstCat.getFiles().add(myfile);
			fil.add(myfile);
		}
		// List of f all parent Nodes returned
		myCatList.add(addedFiles);
		return myCatList;
	}

	// Function Ensuring that only one instance of DummyData is created
	public static DummyDataProvider getInstance() {
		if (dd == null) {
			dd = new DummyDataProvider();
			// System.out.println( dd.toString());
		}
		return dd;
	}

	public static void addToFileRes(MyFiles F) {
		fil.add(F);
	}

	public MyFiles getFileById(int id) {
		System.out.println(id);

		for (MyFiles file : fil) {
			if (file.getId() == id) {
				System.out.println(file.getId());
				System.out.println("returned");
				return file;
			}
			System.out.println("Actual : " + id + "found : " + file.getId());
		}
		return null;
	}
}