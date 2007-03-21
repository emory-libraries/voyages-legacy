package edu.emory.library.tast.database.stat;

import javax.faces.model.SelectItem;


/**
 * SelectItem that implements Comparable interface.
 * @author Pawel Jurczyk
 *
 */
public class ComparableSelectItem extends SelectItem implements Comparable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param o
	 */
	public ComparableSelectItem(Object o) {
		super(o);
	}
	
	/**
	 * Constructor
	 * @param o
	 */
	public ComparableSelectItem(Object string, String string2) {
		super(string, string2);
	}
	
	/**
	 * Constructor
	 * @param o
	 */
	public ComparableSelectItem(Object o, String string1, String string2) {
		super(o, string1, string2);
	}
	
	/**
	 * Constructor
	 * @param o
	 */
	public ComparableSelectItem(Object o, String string1, String string2, boolean b) {
		super(o, string1, string2, b);
	}

	/**
	 * Comparable implementation.
	 * Compares whatever value user will see.
	 */
	public int compareTo(Object arg0) {
		if (!(arg0 instanceof ComparableSelectItem)) {
			return 0;
		}
		ComparableSelectItem that = (ComparableSelectItem)arg0;
		return this.getLabel().toLowerCase().compareTo(that.getLabel().toLowerCase());
	}

	public boolean equals(Object o) {
		if (!(o instanceof SelectItem)) {
			return false;
		}
		SelectItem that = (SelectItem)o;
		if (this.getLabel().equals(that.getLabel()) && this.getValue().equals(that.getValue())) {
			return true;
		}
		return false;
	}
	
}
