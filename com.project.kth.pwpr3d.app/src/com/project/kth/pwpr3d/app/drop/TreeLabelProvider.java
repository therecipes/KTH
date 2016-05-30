package com.project.kth.pwpr3d.app.drop;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

public class TreeLabelProvider extends LabelProvider {
	  @Override
	  public Image getImage(Object element) {
	    Image s = (Image) element; 
	    
	    return s;
	  }
}
