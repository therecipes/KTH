package com.project.kth.pwpr3d.view;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import java.io.File;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.EditorsUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.ide.FileStoreEditorInput;




public class FileOpen  {
	
	
@Execute
public Object execute(ExecutionEvent event) throws ExecutionException {
Shell shell = PlatformUI.getWorkbench().
getActiveWorkbenchWindow().getShell();
//For Opening File Dialog
FileDialog dialog = new FileDialog(shell, SWT.OPEN);
dialog.setFilterExtensions(new String[] {"*.txt", "*.*"});
dialog.setFilterNames(new String[] {"Text File", "All Files"});
String fileSelected = dialog.open();
if (fileSelected != null) {
// Perform Action, like open the file.
IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
IWorkbenchPage page = window.getActivePage();
File myNewFile = new File(fileSelected);
IFileStore fileStore = null;
try {
fileStore = EFS.getStore(myNewFile.toURI());
}
catch (CoreException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
}
try{
//Opening the Text Editor
//FileStoreInput returns an EditorInput object
IEditorInput input = new FileStoreEditorInput(fileStore);
page.openEditor(input, EditorsUI.DEFAULT_TEXT_EDITOR_ID);//Opening eclipse default Editor

}
catch (PartInitException e) {
throw new RuntimeException();
}
}
return null;
}
}