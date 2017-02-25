package com.project.kth.pwpr3d.app.drag;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.TextTransfer;

import com.project.kth.pwpr3d.app.dragndrop.Todo;

public class MyDragListener implements DragSourceListener {

	private final TableViewer viewer;

	public MyDragListener(TableViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		System.out.println("Finshed Drag");
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		// Here you do the convertion to the type which is expected.
		IStructuredSelection selection = viewer.getStructuredSelection();
		Todo firstElement = (Todo) selection.getFirstElement();

		/*if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = firstElement.getShortDescription();// + " " + firstElement.getLongDescription();
		}
		
		if (FileTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = firstElement.getShortDescription();// + " " + firstElement.getLongDescription();
		}*/
		
		
		if (FileTransfer.getInstance().isSupportedType(event.dataType) ||TextTransfer.getInstance().isSupportedType(event.dataType) ) {
			event.data = firstElement.getShortDescription();// + " " + firstElement.getLongDescription();
		}
		

	}

	@Override
	public void dragStart(DragSourceEvent event) {
		System.out.println("Start Drag");
	}
}
