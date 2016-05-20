package com.project.kth.pwpr3d.app.parts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
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

import com.project.kth.pwpr3d.app.dragndrop.Dragger;

public class MyDropTargetListener extends MouseMotionListener.Stub implements DropTargetListener, MouseListener {

	private Composite parentComposite;
	private DropTarget target;

	String path = System.getProperty("user.dir") + "/images/";
	String[] imgNames = { "router.png", "tv.png", "switch.png", "printer.png", "server.png", "splitter.png",
			"laptop.png", "workstation.png", "transformer.png" };
	private Figure panel;
public MyDropTargetListener(Composite parent, DropTarget dropTarget, Figure panel) {
		// TODO Auto-generated constructor stub
		this.parentComposite = parent;
		this.target = dropTarget;
		this.panel = panel;

	}

	@Override
	public void dragEnter(DropTargetEvent event) {
		// TODO Auto-generated method stub
		if (event.detail == DND.DROP_DEFAULT) {
			if ((event.operations & DND.DROP_COPY) != 0) {
				event.detail = DND.DROP_COPY;
			} else {
				event.detail = DND.DROP_NONE;
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

	}

	@Override
	public void dragOver(DropTargetEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drop(DropTargetEvent event) {
		// TODO Auto-generated method stub
		// retrieve the stored index
		int sourceIndex = Integer.valueOf(event.data.toString());

		// compute the index of target control
		Control targetControl = target.getControl();
		int targetIndex = -1;
		for (int i = 0; i < parentComposite.getChildren().length; i++) {
			if (parentComposite.getChildren()[i].equals(targetControl)) {
				targetIndex = i;
				break;
			}
		}

		Control sourceControl = parentComposite.getChildren()[sourceIndex];
		// do not do anything if the dragged photo is dropped at the same
		// position
		if (targetIndex == sourceIndex)
			return;

		// if dragged from left to right
		// shift the old picture to the left
		if (targetIndex > sourceIndex)
			sourceControl.moveBelow(targetControl);
		// if dragged from right to left
		// shift the old picture to the right
		else
			sourceControl.moveAbove(targetControl);

		// repaint the parent composite
		parentComposite.layout();
	}

	@Override
	public void dropAccept(DropTargetEvent event) {
		// TODO Auto-generated method stub
		
		int sourceIndex = Integer.valueOf(event.data.toString());

		if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
			String d = (String) TextTransfer.getInstance().nativeToJava(event.currentDataType);

			org.eclipse.swt.graphics.Point droppoint = parentComposite.toControl(event.x, event.y);

			// DRAW 2D SECTION
			RectangleFigure node1 = new RectangleFigure();
			Rectangle rect = new Rectangle(droppoint.x, droppoint.y, 20, 20);
			Rectangle rect2 = new Rectangle(droppoint.x, droppoint.y, 100, 25);
			node1.setBounds(rect);
			node1.setBackgroundColor(ColorConstants.cyan);

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
