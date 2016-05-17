package com.project.kth.pwpr3d.view;

import javax.annotation.PostConstruct;

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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public abstract class View {

	@PostConstruct
	public void createComposite(Composite parent) {
		// TODO Auto-generated method stub
		final Canvas canvas = new Canvas(parent, SWT.NONE);
		canvas.setLayout(new GridLayout());
		canvas.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final Control[] children = createChildren(canvas);

		for (final Control control : children)
			addDragListener(control);

		addDropListener(canvas);
	}

	abstract protected Control[] createChildren(final Composite parent);

	private void addDropListener(Composite parent) {
		final LocalSelectionTransfer transfer = LocalSelectionTransfer
				.getTransfer();

		final DropTargetAdapter dragAdapter = new DropTargetAdapter() {
			@Override
			public void drop(final DropTargetEvent event) {
				// Step 1: Get first element from the StructuredSelection
				final Control droppedObj = (Control) ((StructuredSelection) transfer
						.getSelection()).getFirstElement();

				// Step 2: Get that control's parent from which it's being
				// dragged
				final Composite oldParent = droppedObj.getParent();

				// If we drag and drop on the same parent, do nothing
				if (oldParent == parent)
					return;

				// Step 3: Figure out what are we dropping
				// This may be done in the dropAccept implementation
				if (droppedObj instanceof Label) {
					final Label droppedLabel = (Label) droppedObj;
					droppedLabel.setParent(parent); // Change parent
				}

				if (droppedObj instanceof Button) {
					final Button droppedButton = (Button) droppedObj;
					droppedButton.setParent(parent); // Change parent
				}

				// Step 4: Tell all parent that the layout has changed
				// This is not necessary, but it looks nicer.
				oldParent.layout();
				parent.layout();
			}
		};

		final DropTarget dropTarget = new DropTarget(parent, DND.DROP_MOVE
				| DND.DROP_COPY);
		dropTarget.setTransfer(new Transfer[] { transfer });
		dropTarget.addDropListener(dragAdapter);

	}

	private void addDragListener(Control control) {
		// TODO Auto-generated method stub
		// Step 1: Get JFace's LocalSelectionTransfer instance
		final LocalSelectionTransfer transfer = LocalSelectionTransfer
				.getTransfer();

		final DragSourceAdapter dragAdapter = new DragSourceAdapter() {
			@Override
			public void dragSetData(final DragSourceEvent event) {
				// Step 2: On drag events, create a new JFace
				// StructuredSelection
				// with the dragged control.
				transfer.setSelection(new StructuredSelection(control));
			}
		};

		final DragSource dragSource = new DragSource(control, DND.DROP_MOVE
				| DND.DROP_COPY);
		dragSource.setTransfer(new Transfer[] { transfer });
		dragSource.addDragListener(dragAdapter);
	}

	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
