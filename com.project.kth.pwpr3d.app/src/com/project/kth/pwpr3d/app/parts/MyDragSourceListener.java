package com.project.kth.pwpr3d.app.parts;

import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

public class MyDragSourceListener implements DragSourceListener {

	private Composite parentComposite;
	private DragSource source;

	public MyDragSourceListener(Composite parent, DragSource dragSource1) {
		// TODO Auto-generated constructor stub
		this.parentComposite = parent;
        this.source = dragSource1;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		// TODO Auto-generated method stub
		for (int i = 0; i < parentComposite.getChildren().length; i++) {
            if (parentComposite.getChildren()[i].equals(source.getControl())) {
                event.data = new Integer(i).toString();
                break;
            }
        }
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		// TODO Auto-generated method stub

	}

}
