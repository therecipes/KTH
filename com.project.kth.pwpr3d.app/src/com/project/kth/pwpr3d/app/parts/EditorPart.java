package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class EditorPart  {

	@PostConstruct
	public void createComposite(Composite parent) {
		Canvas canvas = createDiagram(parent);
		canvas.setLayoutData(new GridData(GridData.FILL_BOTH));
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

}
