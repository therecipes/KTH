package com.project.kth.pwpr3d.app.dragndrop;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.Image;

public enum ContentProviderTree {
	INSTANCE;
	List<String> stringList = new ArrayList<String>();
	List<Image> imageList = new ArrayList<Image>();

	private ContentProviderTree() {
		
	}

	public List<String> getModelString() {
		return stringList;
	}
	
	public List<Image> getModelImage() {
		return imageList;
	}
}
