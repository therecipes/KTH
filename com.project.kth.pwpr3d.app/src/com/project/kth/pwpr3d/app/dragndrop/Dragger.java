package com.project.kth.pwpr3d.app.dragndrop;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Point;

public class Dragger extends MouseMotionListener.Stub implements MouseListener{
	
	 public Dragger(IFigure figure)
     {
         figure.addMouseMotionListener(this);
         figure.addMouseListener(this);
     }

     Point last;

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDoubleClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

}
