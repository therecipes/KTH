package com.project.kth.pwpr3d.app.parts;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.project.kth.pwpr3d.app.dragndrop.Dragger;

public class MyDropTargetListener extends MouseMotionListener.Stub implements DropTargetListener, MouseListener {
	
    private static LabelProvider label;
	private Composite parentComposite;
	private DropTarget target;
	private static Image FOLDER = label.getImage("folder.png");
	private static Image FILE = label.getImage("File.png");
	String path = System.getProperty("user.dir") + "/images/";
	String[] imgNames = { "router.png", "tv.png", "switch.png", "printer.png", "server.png", "splitter.png",
			"laptop.png", "workstation.png", "transformer.png" };
	private Figure panel;
	Table dropTable;
	
	

	public MyDropTargetListener(Composite parent, DropTarget dropTarget, Figure panel) {
		// TODO Auto-generated constructor stub
		this.parentComposite = parent;
		this.target = dropTarget;
		this.panel = panel;
		dropTable = new Table(parent, SWT.BORDER);
		for (int i = 0; i < 10; i++) {
			TableItem item = new TableItem(dropTable, SWT.NONE);
			item.setText(path + imgNames[i]);
		}
	}

	@Override
	public void dragEnter(DropTargetEvent event) {
		// TODO Auto-generated method stub
		/*
		 * if (event.detail == DND.DROP_DEFAULT) { if ((event.operations &
		 * DND.DROP_COPY) != 0) { event.detail = DND.DROP_COPY; } else {
		 * event.detail = DND.DROP_NONE; } }
		 */

		if (event.detail == DND.DROP_DEFAULT) {
			if ((event.operations & DND.DROP_COPY) != 0) {
				event.detail = DND.DROP_COPY;
			} else {
				event.detail = DND.DROP_NONE;
			}
		}
		// will accept text but prefer to have files dropped
		for (int i = 0; i < event.dataTypes.length; i++) {
			if (EditorView.imageTransfer.isSupportedType(event.dataTypes[i])) {
				event.currentDataType = event.dataTypes[i];
				// files should only be copied
				if (event.detail != DND.DROP_COPY) {
					event.detail = DND.DROP_NONE;
				}
				break;
			}
		}
	}

	@Override
	public void dragLeave(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dragOperationChanged(DropTargetEvent event) {
		// TODO Auto-generated method stub
		if (event.detail == DND.DROP_DEFAULT) {
			if ((event.operations & DND.DROP_COPY) != 0) {
				event.detail = DND.DROP_COPY;
			} else {
				event.detail = DND.DROP_NONE;
			}
		}
		if (EditorView.imageTransfer.isSupportedType(event.currentDataType)) {
			if (event.detail != DND.DROP_COPY) {
				event.detail = DND.DROP_NONE;
			}
		}
	}

	@Override
	public void dragOver(DropTargetEvent event) {
		// TODO Auto-generated method stub
		event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
		if (EditorView.textTransfer.isSupportedType(event.currentDataType)) {
			// NOTE: on unsupported platforms this will return null
			Object o = EditorView.textTransfer.nativeToJava(event.currentDataType);
			String t = (String) o;
			if (t != null)
				System.out.println(t);
		}
	}

	@Override
	public void drop(DropTargetEvent event) {
		// TODO Auto-generated method stub
		if (EditorView.textTransfer.isSupportedType(event.currentDataType)) {
			String text = (String) event.data;
			TableItem item = new TableItem(dropTable, SWT.NONE);
			item.setText(text);
		}
		if (EditorView.imageTransfer.isSupportedType(event.currentDataType)) {
			Image[] files = (Image[]) event.data;
			for (int i = 0; i < files.length; i++) {
				TableItem item = new TableItem(dropTable, SWT.NONE);
				item.setImage(files[i]);
			}
		}
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		// TODO Auto-generated method stub
		// int index = (int)event.getSource();
		int sourceIndex = (Integer) event.getSource();

		if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
			String d = (String) TextTransfer.getInstance().nativeToJava(event.currentDataType);

			org.eclipse.swt.graphics.Point droppoint = parentComposite.toControl(event.x, event.y);

			// DRAW 2D SECTION
			RectangleFigure node1 = new RectangleFigure();
			Rectangle rect = new Rectangle(droppoint.x, droppoint.y, 20, 20);
			Rectangle rect2 = new Rectangle(droppoint.x, droppoint.y, 100, 25);
			node1.setBounds(rect);
			node1.setBackgroundColor(ColorConstants.darkBlue);

			org.eclipse.draw2d.Label droppedName = new org.eclipse.draw2d.Label(d);
			droppedName.setIcon(new Image(parentComposite.getDisplay(), path + imgNames[sourceIndex]));
			droppedName.setLocation(new Point(droppoint.x, droppoint.y)); // draw2d.
																			// point
			droppedName.setBounds(rect2);

			node1.add(droppedName);
			panel.add(node1);
			panel.add(droppedName);

			new Dragger(node1);
			new Dragger(droppedName);

			parentComposite.redraw();
		}
	}

	@Override
	public void mouseDoubleClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
