package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class PaletteView {

	public Label[] label;

	@PostConstruct
	public void createComposite(Composite parent) {

		/*
		 * final Shell shell = new Shell(parent.getDisplay());
		 * shell.setSize(520, 200); shell.setLayout(new RowLayout());
		 * shell.setText("Photo Shuffler");
		 * 
		 * // initialize a parent composite with a grid layout manager // since
		 * the demo application uses 4x pictures the grid has exactly // 4x
		 * columns Composite parent2 = new Composite(shell, SWT.NONE);
		 */
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 4;
		parent.setLayout(gridLayout);

		// determine the path where the pictures are stored

		// initialize an array with the photograph names

		String path = System.getProperty("user.dir") + "/images/";
		String[] imgNames = { "router.png", "tv.png", "switch.png", "printer.png", "server.png", "splitter.png",
				"laptop.png", "workstation.png", "transformer.png" };
		label = new Label[imgNames.length];

		// loop over the photo array and establish all listeners
		for (int i = 0; i < imgNames.length; i++) {
			// labels serve as containers for the images
			label[i] = new Label(parent, SWT.BORDER | SWT.HORIZONTAL | SWT.CENTER);
			Image img = new Image(parent.getDisplay(), path + imgNames[i]);
			label[i].setImage(img);

			createImageDragListener(label[i], parent);
			//new EditorView(label[i]);

		}
		//new EditorView(label);
		/*
		 * //show the SWT window // parent.open(); while (!parent.isDisposed())
		 * { if (!parent.getDisplay().readAndDispatch())
		 * parent.getDisplay().sleep(); } // tear down the SWT window
		 * parent.getDisplay().dispose();
		 */
	}

	

	public Label[] getLabel() {
		return label;
	}



	public void setLabel(Label[] label) {
		this.label = label;
	}



	private void createImageDragListener(Label label, Composite parent) {
		// TODO Auto-generated method stub
		
		System.out.println("label.toString()" + label.toString());
		// enable each label to be draggable
		DragSource source = new DragSource(label, DND.DROP_COPY| DND.DROP_MOVE);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		// add a drag listener
		source.addDragListener(new MyDragSourceListener(parent, source));
	}
}
