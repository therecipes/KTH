package com.project.kth.pwpr3d.app.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import java.io.File;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.ide.FileStoreEditorInput;

import com.project.kth.pwpr3d.app.parts.MainView;
import com.project.kth.pwpr3d.app.parts.MyFiles;

public class CallEditor extends AbstractHandler {
//This Function is automatically executed as a listener
//When w e call the 'Handler execute command Function', as a default handler for the Command
@Override
@Execute
public Object execute(ExecutionEvent event) throws ExecutionException {
System.out.println("called");
// Get the view
IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
IWorkbenchPage page = window.getActivePage();
MainView view = (MainView ) page.findView(MainView.ID);
// Get the selection in the view
ISelection selection = view .getSite().getSelectionProvider()
.getSelection();
if (selection != null && selection instanceof IStructuredSelection) {
Object obj = ((IStructuredSelection) selection).getFirstElement();
// If w e had a selection lets open the editor
if (obj != null) {
MyFiles file = (MyFiles) obj;
if(file.isFile){
try {
@SuppressWarnings("unused")
//Getting Creating New File
File f = new File(file.getDescription());
//IFileStore handles rerieval and access of f ile f rom the FileSystem
IFileStore fileStore = null;
try {
fileStore = EFS.getStore( f .toURI() );
} catch (CoreException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
IEditorInput input = new FileStoreEditorInput(fileStore);
//Opening EDITOR
page.openEditor(input, EditorsUI.DEFAULT_TEXT_EDITOR_ID);
} catch (PartInitException e) {
throw new RuntimeException(e);
}
}
}
}
return null;
}
}
