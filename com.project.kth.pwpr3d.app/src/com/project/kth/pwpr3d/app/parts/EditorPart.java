package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.nebula.snippets.compositetable.CompositeTableSnippet5.Dragger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.project.kth.pwpr3d.view.View;

public class EditorPart extends View {

	public static final String ID = "com.project.kth.pwpr3d.app.part.editor";

	@PostConstruct
	public void createComposite(Composite parent) {
		Canvas canvas = createDiagram(parent);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
		final Group grpCanvas = new Group(canvas, SWT.NONE);
        grpCanvas.setText("Canvas");
        grpCanvas.setLayout(new GridLayout());
        grpCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        canvas = new Canvas(grpCanvas, SWT.NONE);
        canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        LightweightSystem lws = new LightweightSystem(canvas); //
        final IFigure panel = new Figure(); //
        lws.setContents(panel); //
        
        Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
        DropTarget dropTarget = new DropTarget(canvas, DND.DROP_COPY | DND.DROP_DEFAULT);
        dropTarget.setTransfer(types);

        dropTarget.addDropListener(new DropTargetListener()
        {
            public void dragEnter(DropTargetEvent event)
            {
                if (event.detail == DND.DROP_DEFAULT)
                {
                    if ((event.operations & DND.DROP_COPY) != 0)
                    {
                        event.detail = DND.DROP_COPY;
                    }
                    else
                    {
                        event.detail = DND.DROP_NONE;
                    }
                }
            }

            public void dragLeave(DropTargetEvent event)
            {
            }

            public void dragOperationChanged(DropTargetEvent event)
            {
            }

            public void dragOver(DropTargetEvent event)
            {
            }

            public void drop(DropTargetEvent event)
            {
            }

            public void dropAccept(final DropTargetEvent event)
            {

                if (TextTransfer.getInstance().isSupportedType(event.currentDataType))
                {
                    String d = (String) TextTransfer.getInstance().nativeToJava(event.currentDataType);

                    org.eclipse.swt.graphics.Point droppoint = canvas.toControl(event.x, event.y);

                    // DRAW 2D SECTION
                    RectangleFigure node1 = new RectangleFigure();
                    Rectangle rect = new Rectangle(droppoint.x, droppoint.y, 20, 20);
                    Rectangle rect2 = new Rectangle(droppoint.x, droppoint.y, 100, 25);
                    node1.setBounds(rect);
                    node1.setBackgroundColor(ColorConstants.cyan);

                    org.eclipse.draw2d.Label droppedName = new org.eclipse.draw2d.Label(d);
                    droppedName.setLocation(new Point(droppoint.x, droppoint.y)); // draw2d.
                                                                                  // point
                    droppedName.setBounds(rect2);

                    node1.add(droppedName);
                    panel.add(node1);
                    panel.add(droppedName);

                    new Dragger(node1);
                    new Dragger(droppedName);

                    canvas.redraw();
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

	@Override
	protected Control[] createChildren(Composite parent) {
		// TODO Auto-generated method stub
		final Label label = new Label(parent, SWT.BORDER);
		label.setText("Another Label");

		return new Control[] { label };
	}

}
