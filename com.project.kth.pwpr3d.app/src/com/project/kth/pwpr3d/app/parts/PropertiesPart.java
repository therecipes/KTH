package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

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

	}

}
