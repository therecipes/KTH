package com.project.kth.pwpr3d.app.parts;

import java.net.URL;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class MyLabelProvider extends LabelProvider {
//Images Initialized
private static Image FOLDER = getImage("folder.png");
private static Image FILE = getImage("File.png");
//Used to get Text of tree Node
@Override
public String getText(Object element) {
//Checking the Node type and setting text for the parent node
if (element instanceof mainCategory) {
mainCategory category = (mainCategory) element;
//Appropriate function called for retrieving text for the parent node category
return category.getName();
}
//Appropriate function called for retrieving text for the Child node category
return ((MyFiles) element).getSummary();
}
//Used to get Image of tree Node
@Override
public Image getImage(Object element) {
if (element instanceof mainCategory) {
return FOLDER;
}
return FILE;
}
private static Image getImage(String file) {
Bundle bundle = FrameworkUtil.getBundle(MyLabelProvider.class);
URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
ImageDescriptor image = ImageDescriptor.createFromURL(url);
return image.createImage();
}
}
