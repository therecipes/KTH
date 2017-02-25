package com.project.kth.pwpr3d.app.drag;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

import com.project.kth.pwpr3d.app.dragndrop.Todo;

public class TableContentProvider implements IStructuredContentProvider {

	  @SuppressWarnings("unchecked")
	@Override
	  public Object[] getElements(Object inputElement) {
	    List<Todo> list = (List<Todo>) inputElement;
	    return list.toArray();
	  }

	  @Override
	  public void dispose() {
	    
	  }

	  @Override
	  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	  }

}
