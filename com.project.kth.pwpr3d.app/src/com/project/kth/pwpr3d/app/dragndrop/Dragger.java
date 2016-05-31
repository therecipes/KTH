package com.project.kth.pwpr3d.app.dragndrop;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

public class Dragger extends MouseMotionListener.Stub implements MouseListener {
	public Dragger(IFigure figure) {
		figure.addMouseMotionListener(this);
		figure.addMouseListener(this);
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

	public void mouseDoubleClick(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseDown(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseUp(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
