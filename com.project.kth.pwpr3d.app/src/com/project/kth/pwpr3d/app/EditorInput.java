package com.project.kth.pwpr3d.app;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class EditorInput implements IEditorInput {

	public String name = null;

	public EditorInput(String name) {
		this.name = name;
	}

	@Override
	public boolean exists() {
		return (this.name != null);
	}

	public boolean equals(Object o) {
		if (!(o instanceof EditorInput))
			return false;
		return ((EditorInput) o).getName().equals(getName());
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		return ImageDescriptor.getMissingImageDescriptor();
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText() {
		return this.name;
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}
}
