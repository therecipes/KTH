package com.project.kth.pwpr3d.app.drop;

import org.eclipse.jface.viewers.LabelProvider;

public class TreeLabelProvider extends LabelProvider {
	  @Override
	  public String getText(Object element) {
	    String s = (String) element; 
	    return s;
	  }
}
