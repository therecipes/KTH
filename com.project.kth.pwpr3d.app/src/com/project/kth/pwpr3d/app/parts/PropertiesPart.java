package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.project.kth.pwpr3d.app.dragndrop.Dragger;

public class PropertiesPart {
	private Label  lblUnicorn;
	private Canvas canvas;
    @PostConstruct
	public void createComposite(Composite parent) {
		Group grpPalette = new Group(parent, SWT.NONE);
        grpPalette.setText("Palette");
        grpPalette.setLayout(new GridLayout());
        grpPalette.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, true));

        lblUnicorn = new Label(grpPalette, SWT.BORDER | SWT.HORIZONTAL | SWT.CENTER);
        lblUnicorn.setText("UNICORN");
        // ADDED A FINAL HERE!!
        lblUnicorn.setAlignment(SWT.CENTER);

        final Group grpCanvas = new Group(EditorPart.getCanvas(), SWT.NONE);
        grpCanvas.setText("Canvas");
        grpCanvas.setLayout(new GridLayout());
        grpCanvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        canvas = new Canvas(grpCanvas, SWT.NONE);
        canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        LightweightSystem lws = new LightweightSystem(canvas); //
        EditorPart.panel = new Figure(); //
        lws.setContents(EditorPart.panel); //
        
        DragSource dragSource1 = new DragSource(lblUnicorn, DND.DROP_COPY);
        Transfer[] transfers1 = new Transfer[] { TextTransfer.getInstance() };
        dragSource1.setTransfer(transfers1);
        dragSource1.addDragListener(new DragSourceListener()
        {
            public void dragStart(DragSourceEvent event)
            {
                if (lblUnicorn.getText().length() == 0)
                {
                    event.doit = false;
                }
            }

            public void dragSetData(DragSourceEvent event)
            {
                if (TextTransfer.getInstance().isSupportedType(event.dataType))
                {
                    event.data = lblUnicorn.getText();
                }
            }

            public void dragFinished(DragSourceEvent event)
            {
            }
        });
        Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
        DropTarget dropTarget = new DropTarget(EditorPart.getCanvas(), DND.DROP_COPY | DND.DROP_DEFAULT);
        dropTarget.setTransfer(types);dropTarget.addDropListener(new DropTargetListener()
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

                    org.eclipse.swt.graphics.Point droppoint = EditorPart.getCanvas().toControl(event.x, event.y);

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
                    EditorPart.panel.add(node1);
                    EditorPart.panel.add(droppedName);

                    new Dragger(node1);
                    new Dragger(droppedName);

                    EditorPart.getCanvas().redraw();
                }
            }
        });

	}


}
