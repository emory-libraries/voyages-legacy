package edu.emory.library.tas;

public class MarkType extends Dictionary {

	private static final Integer TYPE = new Integer(2);
	private static final String NAME = "MarkType";
	
	public MarkType() {
		setType(TYPE);
	}
	
	public static MarkType loadMarkType(String p_dictVal) {
		Dictionary[] dicts = Dictionary.loadDictionary(NAME, p_dictVal);
		if (dicts.length != 0) {
			return (MarkType)dicts[0];
		} else {
			return null;
		}
		
	}
}
