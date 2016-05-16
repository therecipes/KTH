package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.GridData;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.ui.parts.GraphicalEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;


public class EditorPart extends GraphicalEditor {

	public static final String ID = "com.project.kth.pwpr3d.app.part.editor";
	
	@Override
    public void createPartControl(Composite parent)
    {
        Composite c = new Composite(parent, SWT.None);
        c.setLayout(new GridLayout(1, true));
        ToolBar tb = new ToolBar(c, SWT.None);
        tb.setLayoutData(new org.eclipse.swt.layout.GridData(
                org.eclipse.swt.layout.GridData.FILL_HORIZONTAL));
        ToolItem ti1 = new ToolItem(tb, SWT.PUSH);
        ti1.setText("Tool item 1");
        ToolItem ti2 = new ToolItem(tb, SWT.PUSH);
        ti2.setText("Tool item 2");
        ToolItem ti3 = new ToolItem(tb, SWT.PUSH);
        ti3.setText("Tool item 3");
        ToolItem ti4 = new ToolItem(tb, SWT.PUSH);
        ti4.setText("Tool item 4");
        Composite composite = new Composite(c, SWT.None);
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        composite.setLayout(new FillLayout());
        //super.createPartControl(composite);
    }    
	@Override
	protected void initializeGraphicalViewer() {
		// TODO Auto-generated method stub

	}

	public EditorPart() {
		setEditDomain(new DefaultEditDomain(this));
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
		// TODO Auto-generated method stub

	}

	@PostConstruct
	public void createComposite(Composite parent) {
		createPartControl(parent);

		//getSite().getPage().addSelectionListener(this);
	}

}
