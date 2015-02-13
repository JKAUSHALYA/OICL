package com.oicl.editor;

import org.eclipse.ui.editors.text.TextEditor;

public class OICLEditor extends TextEditor {

	private ColorManager colorManager;

	public OICLEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
