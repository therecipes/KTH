package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class PalettePart {
	// private Label[] lblLaptop; lblRouter, lblSwitch, lblConnector;
	String[] lblName = { "Laptop", "Router", "Switch", "Connector", "Transformer", "TV", "Server" };
	private Label[] lblNetwork = new Label[lblName.length];
	
	EditorPart editorpart;

	@PostConstruct
	public void createComposite(Composite parent) {
		Group grpPalette = new Group(parent, SWT.NONE);
		grpPalette.setText("Palette");
		grpPalette.setLayout(new GridLayout());
		grpPalette.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, true));
		
		
		for (int i = 0; i < lblName.length; i++) {
			lblNetwork[i] = new Label(grpPalette, SWT.BORDER | SWT.HORIZONTAL | SWT.CENTER);
			lblNetwork[i].setText(lblName[i]);
			// ADDED A FINAL HERE!!
			lblNetwork[i].setAlignment(SWT.CENTER);
			createDragSourse(lblNetwork[i]);

		}

	}

	private void createDragSourse(Label lblComponent) {
		// TODO Auto-generated method stub
		DragSource dragSource1 = new DragSource(lblComponent, DND.DROP_COPY);
		Transfer[] transfers1 = new Transfer[] { TextTransfer.getInstance() };
		dragSource1.setTransfer(transfers1);
		dragSource1.addDragListener(new DragSourceListener() {
			public void dragStart(DragSourceEvent event) {
				if (lblComponent.getText().length() == 0) {
					event.doit = false;
				}
			}

			public void dragSetData(DragSourceEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					event.data = lblComponent.getText();
				}
			}

			public void dragFinished(DragSourceEvent event) {
			}
		});
	}

}
