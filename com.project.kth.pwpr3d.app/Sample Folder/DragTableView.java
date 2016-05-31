package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.ImageTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;

import com.project.kth.pwpr3d.app.drag.MyDragListener;
import com.project.kth.pwpr3d.app.drag.TableContentProvider;
import com.project.kth.pwpr3d.app.drag.TableLabelProvider;
import com.project.kth.pwpr3d.app.dragndrop.ContentProvider;

public class DragTableView {
	 @PostConstruct
	  public void createPartControl(Composite parent) {
	    TableViewer viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
	        | SWT.V_SCROLL);
	    int operations = DND.DROP_COPY| DND.DROP_MOVE;
	    Transfer[] transferTypes = new Transfer[]{ FileTransfer.getInstance(), TextTransfer.getInstance()};
	    viewer.addDragSupport(operations, transferTypes , new MyDragListener(viewer));
	    viewer.setContentProvider(new TableContentProvider());
	    viewer.setLabelProvider(new TableLabelProvider());
	    viewer.setInput(ContentProvider.INSTANCE.getModel());
	  }
}
