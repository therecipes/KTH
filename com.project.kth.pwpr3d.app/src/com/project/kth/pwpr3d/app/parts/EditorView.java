package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class EditorView {

	Label[] lblImage = new Label[9];
	private Figure panel;
	PaletteView paletteView;

	/*
	 * public EditorView(Label label) { // TODO Auto-generated constructor stub
	 * this.lblImage = label; System.out.println("lblImage.toString()" +
	 * lblImage.toString()); }
	 */
	@PostConstruct
	public void createComposite(Composite parent) {
		System.out.println("parent.toString()" + parent.toString());
		Canvas canvas = createDiagram(parent);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));

		LightweightSystem lws = new LightweightSystem(canvas); //
		panel = new Figure(); //
		lws.setContents(panel);
		
		
			
			DropTarget target = new DropTarget(canvas, DND.DROP_COPY | DND.DROP_DEFAULT);
			target.setTransfer(new Transfer[] { TextTransfer.getInstance() });
			// add a drop listener
			target.addDropListener(new MyDropTargetListener(parent, target, panel));
		

		// createImageDropListener( canvas);
		/*
		 * for (int i = 0; i < lblImage.length; i++) { // lblImage[i] = new
		 * Label(canvas, SWT.NONE); createImageDropListener(lblImage[i],
		 * canvas); }
		 */
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

	// enable each label to be a drop target

	public void imageLabelTransfer(Label label) {
		// TODO Auto-generated method stub

	}

}
