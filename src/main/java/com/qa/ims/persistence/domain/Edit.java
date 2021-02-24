package com.qa.ims.persistence.domain;

public class Edit {

	private String Editor;
	private String Change;
	
	

	public Edit(String editor, String change) {
		Editor = editor;
		Change = change;
	}
	public String getChange() {
		return Change;
	}
	public void setChange(String change) {
		Change = change;
	}
	public String getEditor() {
		return Editor;
	}
	public void setEditor(String editor) {
		Editor = editor;
	}
}
