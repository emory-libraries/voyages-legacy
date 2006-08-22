package edu.emory.library.tast.dm.dictionaries;

import edu.emory.library.tast.dm.Dictionary;

public class PlaceA extends Dictionary {

	public static final Integer TYPE = new Integer(34);
	public static final String NAME = "PlaceA";
	
	public PlaceA() {
		setType(TYPE);
	}
	
	public static PlaceA loadPlaceA(String p_dictVal) {
		Dictionary[] dicts = Dictionary.loadDictionaryByName(NAME, p_dictVal);
		if (dicts.length != 0) {
			return (PlaceA)dicts[0];
		} else {
			return null;
		}
		
	}
}
