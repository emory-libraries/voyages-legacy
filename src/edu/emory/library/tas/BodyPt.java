package edu.emory.library.tas;

public class BodyPt extends Dictionary {

	private static final Integer TYPE = new Integer(2);
	private static final String NAME = "DodyPt";
	
	public BodyPt() {
		setType(TYPE);
	}
	
	public static BodyPt loadBodyPt(String p_dictVal) {
		Dictionary[] dicts = Dictionary.loadDictionary(NAME, p_dictVal);
		if (dicts.length != 0) {
			return (BodyPt)dicts[0];
		} else {
			return null;
		}
		
	}
}
