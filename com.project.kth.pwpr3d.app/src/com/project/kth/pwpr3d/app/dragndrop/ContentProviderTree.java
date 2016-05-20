package com.project.kth.pwpr3d.app.dragndrop;

import java.util.ArrayList;
import java.util.List;

public enum ContentProviderTree {
	INSTANCE;
	List<String> list = new ArrayList<String>();

	private ContentProviderTree() {
		
	}

	public List<String> getModel() {
		return list;
	}
}
