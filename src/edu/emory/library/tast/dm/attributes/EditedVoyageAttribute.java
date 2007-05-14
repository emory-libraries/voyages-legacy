package edu.emory.library.tast.dm.attributes;

import java.util.List;

import org.hibernate.Session;

import edu.emory.library.tast.dm.Dictionary;
import edu.emory.library.tast.dm.Voyage;

public class EditedVoyageAttribute extends DictionaryAttribute {

	public EditedVoyageAttribute(String name, String objType) {
		super(name, objType);
	}

	public Attribute getAttribute(String name)
	{
		return null;
	}

	public List loadAllObjects(Session sess)
	{
		return null;
	}
	
	public NumericAttribute getItAttribute()
	{
		return (NumericAttribute) Voyage.getAttribute("iid");
	}

	public Dictionary loadObjectById(Session sess, long id) {
		return null;
	}

}