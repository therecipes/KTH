package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.project.kth.pwpr3d.app.dragndrop.Dragger;

public class EditorPart {

	private Canvas canvas;
	IFigure panel;
	DragSourceEvent event;

	@PostConstruct
	public void createComposite(Composite parent) {
		setCanvas(createDiagram(parent));
		getCanvas().setLayoutData(new GridData(GridData.FILL_BOTH));

		/*final Group grpCanvas = new Group(EditorPart.getCanvas(), SWT.NONE);
		grpCanvas.setText("Canvas");
		grpCanvas.setLayout(new GridLayout());
		grpCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		canvas = new Canvas(grpCanvas, SWT.NONE);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));*/

		LightweightSystem lws = new LightweightSystem(getCanvas()); //
		panel = new Figure(); //
		lws.setContents(panel); //

		Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		DropTarget dropTarget = new DropTarget(getCanvas(),
				DND.DROP_COPY | DND.DROP_DEFAULT);
		dropTarget.setTransfer(types);
		dropTarget.addDropListener(new DropTargetListener() {

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
			public void dragLeave(DropTargetEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragOperationChanged(DropTargetEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dragOver(DropTargetEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void drop(DropTargetEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void dropAccept(DropTargetEvent event) {
				// TODO Auto-generated method stub
				if (TextTransfer.getInstance().isSupportedType(
						event.currentDataType)) {
					String d = (String) TextTransfer.getInstance()
							.nativeToJava(event.currentDataType);

					org.eclipse.swt.graphics.Point droppoint = getCanvas().toControl(event.x, event.y);

					// DRAW 2D SECTION
					RectangleFigure node1 = new RectangleFigure();
					Rectangle rect = new Rectangle(droppoint.x, droppoint.y,
							20, 20);
					Rectangle rect2 = new Rectangle(droppoint.x, droppoint.y,
							100, 25);
					node1.setBounds(rect);
					node1.setBackgroundColor(ColorConstants.cyan);

					org.eclipse.draw2d.Label droppedName = new org.eclipse.draw2d.Label(
							d);
					droppedName
							.setLocation(new Point(droppoint.x, droppoint.y)); // draw2d.
																				// point
					droppedName.setBounds(rect2);

					node1.add(droppedName);
					panel.add(node1);
					panel.add(droppedName);

					new Dragger(node1);
					new Dragger(droppedName);

					getCanvas().redraw();

				}
			}
		});

	}

	private Canvas createDiagram(Composite parent) {
		// TODO Auto-generated method stub
		// Create a root figure and simple layout to contain
		// all other figures
		Figure root = new Figure();
		root.setFont(parent.getFont());
		XYLayout layout = new XYLayout();
		root.setLayoutManager(layout);

		// Create a canvas to display the root figure
		Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.white);
		LightweightSystem lws = new LightweightSystem(canvas);
		lws.setContents(root);
		return canvas;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

	Point last;

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseDoubleClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		last = e.getLocation();
	}

	public void mouseDragged(MouseEvent e) {
		Point p = e.getLocation();
		Dimension delta = p.getDifference(last);
		{
			last = p;
			Figure f = ((Figure) e.getSource());
			f.setBounds(f.getBounds().getTranslated(delta.width, delta.height));
		}
	}
}
