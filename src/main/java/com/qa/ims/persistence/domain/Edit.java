package com.qa.ims.persistence.domain;

public class Edit {

	private String Editor;
	private String Change;
	
	

	@Override
	public String toString() {
		return "Editor=" + Editor + ", Change=" + Change + "]";
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Change == null) ? 0 : Change.hashCode());
		result = prime * result + ((Editor == null) ? 0 : Editor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edit other = (Edit) obj;
		if (Change == null) {
			if (other.Change != null)
				return false;
		} else if (!Change.equals(other.Change))
			return false;
		if (Editor == null) {
			if (other.Editor != null)
				return false;
		} else if (!Editor.equals(other.Editor))
			return false;
		return true;
	}
	
}
