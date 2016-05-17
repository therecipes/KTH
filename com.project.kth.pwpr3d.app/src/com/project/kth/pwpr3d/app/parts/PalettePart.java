package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PolygonShape;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.XYLayout;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.part.ViewPart;


public class PalettePart {
	
	 private final static Cursor  ARROW_CURSOR   = new Cursor(null, SWT.CURSOR_ARROW);  
	 
	 private final static Dimension SECNEED_DIMENSION  = new Dimension(40, 18); 
	 private final static Font   SEC_NEED_FONT   = new Font(null, "Arial", 8, SWT.NORMAL);

	@PostConstruct
	public void createComposite(Composite parent) {
		Canvas canvas = createDiagram(parent);
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	
	private Canvas createDiagram(Composite parent) {
		// TODO Auto-generated method stub
		// Create a root figure and simple layout to contain
		// all other figures
		Figure root = new Figure();
		root.setFont(parent.getFont());
		root.setLayoutManager(new XYLayout());

		// Create a canvas to display the root figure
		Canvas canvas = new Canvas(parent, SWT.DOUBLE_BUFFERED);
		canvas.setBackground(ColorConstants.white);
		LightweightSystem lws = new LightweightSystem(canvas);
		lws.setContents(root);

		// Add the father "Andy"
		IFigure andy = createPersonFigure("Andy");
		root.add(andy,
				new Rectangle(new Point(10, 10), andy.getPreferredSize()));

		// Add the mother "Betty"
		IFigure betty = createPersonFigure("Betty");
		root.add(betty,
				new Rectangle(new Point(30, 10), andy.getPreferredSize()));

		// Add the son "Carl"
		IFigure carl = createPersonFigure("Carl");
		root.add(carl,
				new Rectangle(new Point(50, 10), carl.getPreferredSize()));
		
		IFigure marriage = createMarriageFigure();
		root.add(marriage,
				new Rectangle(new Point(70, 10), marriage.getPreferredSize()));

		IFigure benj = createPersonFigure("Benj", "Admin", new Color(null,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128,
				(new Double(Math.random() * 128)).intValue() + 128));
		root.add(benj, new Rectangle(new Point(90, 10), benj.getPreferredSize()));

		

		//root.add(connect(andy, marriage));
		//root.add(connect(betty, marriage));
		//root.add(connect(carl, marriage));

		return canvas;
	}

	private IFigure createPersonFigure(String name, String tooltipName,
			Color borderColor) {

		RectangleFigure result = new RectangleFigure();
		result.setSize(15, 15);
		result.setPreferredSize(SECNEED_DIMENSION);
		result.setCursor(ARROW_CURSOR);
		result.setBorder(new LineBorder(2));
		result.setBackgroundColor(ColorConstants.white);
		result.setForegroundColor(borderColor);
		StackLayout layout = new StackLayout();
		result.setLayoutManager(layout);
		result.getPreferredSize(20, 20);

		Label l = new Label();
		l.setText(name);
		((IFigure) l).setForegroundColor(ColorConstants.black);
		l.setFont(SEC_NEED_FONT);
		result.setToolTip((IFigure) new Label());
		result.add((IFigure) l);
		return result;
	}

	private IFigure createMarriageFigure() {
		// TODO Auto-generated method stub
		Rectangle r = new Rectangle(0, 0, 15, 15);
		PolygonShape polygonShape = new PolygonShape();
		polygonShape.setStart(r.getTop());
		polygonShape.addPoint(r.getTop());
		polygonShape.addPoint(r.getLeft());
		polygonShape.addPoint(r.getBottom());
		polygonShape.addPoint(r.getRight());
		polygonShape.addPoint(r.getTop());
		polygonShape.setEnd(r.getTop());
		polygonShape.setFill(true);
		polygonShape.setBackgroundColor(new Color(null, (new Double(Math
				.random() * 128)).intValue() + 128, (new Double(
				Math.random() * 128)).intValue() + 128, (new Double(Math
				.random() * 128)).intValue() + 128));
		polygonShape.setPreferredSize(r.getSize());
		return polygonShape;
	}

	private IFigure createPersonFigure(String name) {
		// TODO Auto-generated method stub
		RectangleFigure rectangleFigure = new RectangleFigure();
		rectangleFigure.setBackgroundColor(new Color(null, (new Double(Math
				.random() * 128)).intValue(), (new Double(Math.random() * 128))
				.intValue(), (new Double(Math.random() * 128)).intValue()));
		rectangleFigure.setLayoutManager(new StackLayout());
		rectangleFigure.setPreferredSize(15, 15);
		rectangleFigure.add(new Label(name));
		rectangleFigure.setToolTip(new Label(name));
		
		return rectangleFigure;
	}


	
	
}
