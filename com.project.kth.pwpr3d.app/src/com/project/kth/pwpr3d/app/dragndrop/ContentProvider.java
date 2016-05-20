package com.project.kth.pwpr3d.app.dragndrop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public enum ContentProvider {
	INSTANCE;
	String path = System.getProperty("user.dir") + "/images/";
	String[] imgNames = { "router.png", "tv.png", "switch.png", "printer.png", "server.png", "splitter.png",
			"laptop.png", "workstation.png", "transformer.png" };
	//private Image[] img;

	public List<Todo> getModel() {
		List<Todo> list = new ArrayList<Todo>();
		
		for (int i = 0; i < imgNames.length; i++) {
			Image img = new Image(null, path + imgNames[i]);
			Todo todo = new Todo(img, imgNames[i]);
			list.add(todo);
		}
	    return list;
	  }
}
