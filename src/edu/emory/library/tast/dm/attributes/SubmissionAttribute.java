package edu.emory.library.tast.dm.attributes;

import java.util.List;

import org.hibernate.Session;

import edu.emory.library.tast.dm.Dictionary;

public class SubmissionAttribute extends DictionaryAttribute {

	
	
	public SubmissionAttribute(String name, String objType) {
		super(name, objType);
	}

	public Attribute getAttribute(String name) {
		return null;
	}

	public NumericAttribute getItAttribute() {
		return null;
	}

	public List loadAllObjects(Session sess) {
		return null;
	}

	public Dictionary loadObjectById(Session sess, long id) {
		return null;
	}

}