package edu.emory.library.tas;

public class PlaceB extends Dictionary {

	private static final Integer TYPE = new Integer(2);
	private static final String NAME = "PlaceB";
	
	public PlaceB() {
		setType(TYPE);
	}
	
	public static PlaceB loadPortLocation(String p_dictVal) {
		Dictionary[] dicts = Dictionary.loadDictionary(NAME, p_dictVal);
		if (dicts.length != 0) {
			return (PlaceB)dicts[0];
		} else {
			return null;
		}
		
	}
}
