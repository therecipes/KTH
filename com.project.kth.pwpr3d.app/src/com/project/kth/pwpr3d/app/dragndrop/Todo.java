package com.project.kth.pwpr3d.app.dragndrop;

import org.eclipse.swt.graphics.Image;

public class Todo {
	private Image shortDescription;
	  private String longDescription;
	  
	  public Todo(Image img, String longDescription) {
	    this.shortDescription = img;
	    this.longDescription = longDescription;
	  }
	  
	  public Image getShortDescription() {
	    return shortDescription;
	  }
	  public void setShortDescription(Image shortDescription) {
	    this.shortDescription = shortDescription;
	  }
	  public String getLongDescription() {
	    return longDescription;
	  }
	  public void setLongDescription(String longDescription) {
	    this.longDescription = longDescription;
	  }
	  
}
