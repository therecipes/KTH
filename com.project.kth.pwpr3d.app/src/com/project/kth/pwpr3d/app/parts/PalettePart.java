package com.project.kth.pwpr3d.app.parts;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.project.kth.pwpr3d.app.dragndrop.Todo;

public class PalettePart {
	// private Label[] lblLaptop; lblRouter, lblSwitch, lblConnector;
	String[] lblName = {"Router", "TV", "Switch", "Printer", "Server", "Splitter",
			"Laptop", "Workstation", "Transformer"};
	private Label[] lblNetwork = new Label[lblName.length];
	
	static String[] imgNames = { "router.gif", "tv.gif", "switch.gif", "printer.gif", "server.gif", "splitter.gif",
			"laptop.gif", "workstation.gif", "transformer.gif" };
	String path = System.getProperty("user.dir") + "/images/";
	
	static int length = imgNames.length;
	private static Image[] FOLDER = new Image[length];
	
	EditorPart editorpart;

	@PostConstruct
	public void createComposite(Composite parent) {
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.HORIZONTAL;
		parent.setLayout(fillLayout);
		
		Group grpPalette = new Group(parent, SWT.PUSH);
		grpPalette.setText("Palette");
		grpPalette.setLayout(new GridLayout());
		grpPalette.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, false, true));
		grpPalette.pack();
		
		 
		
		for (int i = 0; i < lblName.length; i++) {
			lblNetwork[i] = new Label(grpPalette, SWT.BORDER | SWT.HORIZONTAL | SWT.CENTER);
			lblNetwork[i].setText(lblName[i]);
			FOLDER[i] = getImage(imgNames[i]);
			
			lblNetwork[i].setImage(FOLDER[i]);
			// ADDED A FINAL HERE!!
			lblNetwork[i].setAlignment(SWT.CENTER);
			createDragSourse(lblNetwork[i]);

		}

	}

	private Image getImage(String file) {
		// TODO Auto-generated method stub
		Bundle bundle = FrameworkUtil.getBundle(Todo.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		ImageDescriptor image = ImageDescriptor.createFromURL(url);
		return image.createImage();
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
