package com.project.kth.pwpr3d.app.parts;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class MyContentProvider implements ITreeContentProvider {
	// Class for Integrating Dummy Data Provider w ith Tree View er
	private DummyDataProvider ddp;

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// Initializing DummyDataProvider Instance
		this.ddp = (DummyDataProvider) newInput;
	}

	// This function is called automatically to retrieve data for parent Nodes
	// of the tree
	@Override
	public Object[] getElements(Object inputElement) {
		return ddp.getCategories().toArray();
	}

	// This function is called automatically to retrieve data for child Nodes of
	// the tree
	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof mainCategory) {
			mainCategory myObj = (mainCategory) parentElement;
			return myObj.getFiles().toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof mainCategory) {
			return true;
		}
		return false;
	}

}