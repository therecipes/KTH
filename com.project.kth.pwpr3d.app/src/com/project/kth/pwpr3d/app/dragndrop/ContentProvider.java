package com.project.kth.pwpr3d.app.dragndrop;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageFileNameProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.project.kth.pwpr3d.app.parts.MyLabelProvider;
import com.project.kth.pwpr3d.app.parts.mainCategory;

public enum ContentProvider {
	INSTANCE;
	String path = System.getProperty("user.dir") + "/images/";
	 static String[] imgNames = { "router.gif", "tv.gif", "switch.gif", "printer.gif", "server.gif", "splitter.gif",
			"laptop.gif", "workstation.gif", "transformer.gif" };
	 //private Image[] img;

	
	static int length=imgNames.length;
	private static Image[] FOLDER =new Image[length];
	
	
	       
			//new I getImage("folder.png");
	private static Image FILE = getImage("File.png");
	
	public Image getImage(Object element) {
		if (element instanceof mainCategory) {
	  //return FOLDER;
		}
		return FILE;
		}
		private static Image getImage(String file) {
		Bundle bundle = FrameworkUtil.getBundle(Todo.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();
		}
	public List<Todo> getModel() {
		List<Todo> list = new ArrayList<>();
		
		for (int i = 0; i < imgNames.length; i++) {
			FOLDER[i] = getImage(imgNames[i]);
			//Image img = new Image(null, path + imgNames[i]);
			Todo todo = new Todo(FOLDER[i], "");
			list.add(todo);
			
		}
	    return list;
	  }
}
