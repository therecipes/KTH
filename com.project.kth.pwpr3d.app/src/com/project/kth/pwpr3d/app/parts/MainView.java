package com.project.kth.pwpr3d.app.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

public class MainView extends ViewPart {
	//Creating a Tree Viewer Object to be show n in View
	//TreeViewer is JFace Component i.e. Library built on top of SWT
	
	private static TreeViewer viewer;
	private Text txtInput;
	public MainView () {
	}
	public static final String ID = "com.project.kth.pwpr3d.app.part.ModelView";
	// Called for creating layout of the View





	@Inject
	private MDirtyable dirty;
	private TableViewer tableViewer;



	@PostConstruct
	
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		parent.setLayout(new GridLayout(1, false));

		txtInput = new Text(parent, SWT.BORDER);
		txtInput.setMessage("Search");
		txtInput.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dirty.setDirty(true);
			}
		});
		txtInput.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		

	
		viewer = new TreeViewer(parent,SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL);
		viewer.setContentProvider(new MyContentProvider());
		//Setting Label provider for the tree viewer
		viewer.setLabelProvider(new MyLabelProvider());
		viewer.setAutoExpandLevel(2);
		//Setting Input Data
		viewer.setInput(new DummyDataProvider());
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		getSite().setSelectionProvider(viewer);
		hookDoubleClickCommand();
		}
		//Function for Tree Listeners
		private void hookDoubleClickCommand() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
		public void doubleClick(DoubleClickEvent event) {
		TreeViewer myTV = (TreeViewer)event.getSource();
		//Checking the Object type of the Tree Node
		if(myTV.getSelection().toString().contains("MyFiles")){
		IHandlerService handlerService = (IHandlerService) getSite()
		.getService(IHandlerService.class);
		try {
		//Calling Service Handler
		//executing Command using the given Command Id
		handlerService.executeCommand("de.vogella.rcp.editor.example.openEditor", null);
		} catch (Exception ex) {
		throw new RuntimeException(ex.toString());
		}
		}
		}


		});
		
	}
	

		
		

		public static TreeViewer getViewer(){
		return viewer;
		}
		
	

	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}
	
}
