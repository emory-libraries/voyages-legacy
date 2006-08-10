package edu.emory.library.tast.dm;

import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.CompoundAttribute;
import edu.emory.library.tast.dm.attributes.Group;
import edu.emory.library.tast.dm.attributes.ObjectType;
import edu.emory.library.tast.dm.dictionaries.BodyPt;
import edu.emory.library.tast.dm.dictionaries.Country;
import edu.emory.library.tast.dm.dictionaries.MarkDegree;
import edu.emory.library.tast.dm.dictionaries.MarkNumber;
import edu.emory.library.tast.dm.dictionaries.MarkSize;
import edu.emory.library.tast.dm.dictionaries.MarkType;
import edu.emory.library.tast.dm.dictionaries.Nation;
import edu.emory.library.tast.dm.dictionaries.OtherMark;
import edu.emory.library.tast.dm.dictionaries.PlaceA;
import edu.emory.library.tast.dm.dictionaries.PlaceB;
import edu.emory.library.tast.dm.dictionaries.SexAge;
import edu.emory.library.tast.dm.dictionaries.SpecialMark;
import edu.emory.library.tast.util.query.Conditions;
import edu.emory.library.tast.util.query.QueryValue;

/**
 * Slave object.
 * 
 * @author Pawel Jurczyk
 * 
 */
public class Slave extends AbstractDescriptiveObject {

	private static final Object SLAVE = "Slave";

	private static Attribute[] attributes;

	/**
	 * Tells whether object has been updated or created.
	 */
	private int modified = 0;

	/**
	 * Loads all groups designed to slave type.
	 * 
	 * @return
	 */
	public static Group[] getGroups() {

		Conditions conditions = new Conditions();
		conditions.addCondition("typeName", SLAVE, Conditions.OP_EQUALS);
		QueryValue query = new QueryValue("ObjectType", conditions);
		query.setCacheable(true);
		Object[] types = (Object[]) query.executeQuery();
		ObjectType type = (ObjectType) types[0];

		conditions = new Conditions();
		conditions.addCondition("objectType", type, Conditions.OP_EQUALS);
		query = new QueryValue("Group", conditions);
		query.setOrder(QueryValue.ORDER_ASC);
		query.setOrderBy(new String[] { "name" });
		query.setCacheable(true);
		Object[] attributes = (Object[]) query.executeQuery();
		Group[] ret = null;
		if (attributes.length == 0) {
			ret = new Group[] {};
		} else {
			ret = new Group[attributes.length];
			for (int i = 0; i < attributes.length; i++) {
				ret[i] = (Group) attributes[i];
			}
		}
		return ret;
	}

	/**
	 * Loads all Attributes designed to slaves.
	 * 
	 * @return
	 */
	public static Attribute[] getAttributes() {

		if (attributes == null) {
			attributes = Attribute.loadAttributesForType("Slave");
		}
		return attributes;
	}

	/**
	 * Gets attribute of given name.
	 * 
	 * @param name
	 * @return attribute, null if given attribute does not exist.
	 */
	public static Attribute getAttribute(String name) {

		if (attributes == null) {
			attributes = Attribute.loadAttributesForType("Slave");
		}
		for (int i = 0; i < attributes.length; i++) {
			if (attributes[i].getName().equals(name)) {
				return attributes[i];
			}
		}
		return null;
	}

	/**
	 * Gets all attribute names.
	 * 
	 * @return
	 */
	public static String[] getAllAttrNames() {
		if (attributes == null) {
			attributes = Attribute.loadAttributesForType("Slave");
		}

		String[] attrsName = new String[attributes.length];
		for (int i = 0; i < attrsName.length; i++) {
			attrsName[i] = attributes[i].getName();
		}
		return attrsName;
	}

	/**
	 * Gets all CompoundAttributes assigned to slaves.
	 * 
	 * @return
	 */
	public static CompoundAttribute[] getCoumpoundAttributes() {

		Conditions conditions = new Conditions();
		conditions.addCondition("typeName", SLAVE, Conditions.OP_EQUALS);
		QueryValue query = new QueryValue("ObjectType", conditions);
		query.setCacheable(true);
		Object[] types = (Object[]) query.executeQuery();
		ObjectType type = (ObjectType) types[0];

		conditions = new Conditions();
		conditions.addCondition("objectType", type, Conditions.OP_EQUALS);
		query = new QueryValue("CompoundAttribute", conditions);
		query.setOrder(QueryValue.ORDER_ASC);
		query.setOrderBy(new String[] { "name" });
		query.setCacheable(true);
		Object[] attributes = (Object[]) query.executeQuery();
		CompoundAttribute[] ret = null;
		if (attributes.length == 0) {
			ret = new CompoundAttribute[] {};
		} else {
			ret = new CompoundAttribute[attributes.length];
			for (int i = 0; i < attributes.length; i++) {
				ret[i] = (CompoundAttribute) attributes[i];
			}
		}
		return ret;
	}

	/**
	 * Basic constructor - object will have assigned new ID.
	 */
	public Slave() {
	}

	/**
	 * Constructor that sets object ID.
	 * 
	 * @param slaveId
	 */
	private Slave(Long slaveId) {
		this.setSlaveId(slaveId);
	}

	/**
	 * Creates new Slave with given ID.
	 * 
	 * @param slaveId
	 *            slave ID
	 * @return Slave object
	 */
	public static Slave createNew(Long slaveId) {
		return new Slave(slaveId);
	}

	/**
	 * Clones object. Copy is deep copy.
	 */
	public Object clone() {
		Slave n = new Slave();
		n.setModified(getModified());
		n.values = values;
		return n;
	}

	/**
	 * Gets modified flag.
	 * 
	 * @return modified flag
	 */
	public int getModified() {
		return modified;
	}

	/**
	 * Sets modified flag
	 * 
	 * @param modified
	 *            new modified value
	 */
	public void setModified(int modified) {
		this.modified = modified;
	}

	/**
	 * Gets String representation of object.
	 */
	public String toString() {
		return "Slave " + this.values;
	}

	/* AUTOGENERATED BLOCK */
	/** Getters/setters * */
	public void setIid(Long iid) {
		if ((iid == null && this.values.get("iid") != null) || (iid != null && !iid.equals(this.values.get("iid")))) {
			this.modified = UPDATED;
		}
		this.values.put("iid", iid);
	}

	public void setSlaveId(Long slaveId) {
		if ((slaveId == null && this.values.get("slaveId") != null)
				|| (slaveId != null && !slaveId.equals(this.values.get("slaveId")))) {
			this.modified = UPDATED;
		}
		this.values.put("slaveId", slaveId);
	}

	public void setVoyageId(Long voyageId) {
		if ((voyageId == null && this.values.get("voyageId") != null)
				|| (voyageId != null && !voyageId.equals(this.values.get("voyageId")))) {
			this.modified = UPDATED;
		}
		this.values.put("voyageId", voyageId);
	}

	public void setSexage(SexAge sexage) {
		if ((sexage == null && this.values.get("sexage") != null)
				|| (sexage != null && !sexage.equals(this.values.get("sexage")))) {
			this.modified = UPDATED;
		}
		this.values.put("sexage", sexage);
	}

	public void setAge(Float age) {
		if ((age == null && this.values.get("age") != null) || (age != null && !age.equals(this.values.get("age")))) {
			this.modified = UPDATED;
		}
		this.values.put("age", age);
	}

	public void setFeet(Integer feet) {
		if ((feet == null && this.values.get("feet") != null)
				|| (feet != null && !feet.equals(this.values.get("feet")))) {
			this.modified = UPDATED;
		}
		this.values.put("feet", feet);
	}

	public void setInch(Float inch) {
		if ((inch == null && this.values.get("inch") != null)
				|| (inch != null && !inch.equals(this.values.get("inch")))) {
			this.modified = UPDATED;
		}
		this.values.put("inch", inch);
	}

	public void setAgeinf(Integer ageinf) {
		if ((ageinf == null && this.values.get("ageinf") != null)
				|| (ageinf != null && !ageinf.equals(this.values.get("ageinf")))) {
			this.modified = UPDATED;
		}
		this.values.put("ageinf", ageinf);
	}

	public void setCountry(Country country) {
		if ((country == null && this.values.get("country") != null)
				|| (country != null && !country.equals(this.values.get("country")))) {
			this.modified = UPDATED;
		}
		this.values.put("country", country);
	}

	public void setName(String name) {
		if ((name == null && this.values.get("name") != null)
				|| (name != null && !name.equals(this.values.get("name")))) {
			this.modified = UPDATED;
		}
		this.values.put("name", name);
	}

	public void setUnclear(Integer unclear) {
		if ((unclear == null && this.values.get("unclear") != null)
				|| (unclear != null && !unclear.equals(this.values.get("unclear")))) {
			this.modified = UPDATED;
		}
		this.values.put("unclear", unclear);
	}

	public void setNameimp(String nameimp) {
		if ((nameimp == null && this.values.get("nameimp") != null)
				|| (nameimp != null && !nameimp.equals(this.values.get("nameimp")))) {
			this.modified = UPDATED;
		}
		this.values.put("nameimp", nameimp);
	}

	public void setNatimp(Nation natimp) {
		if ((natimp == null && this.values.get("natimp") != null)
				|| (natimp != null && !natimp.equals(this.values.get("natimp")))) {
			this.modified = UPDATED;
		}
		this.values.put("natimp", natimp);
	}

	public void setBodypt1(BodyPt bodypt1) {
		if ((bodypt1 == null && this.values.get("bodypt1") != null)
				|| (bodypt1 != null && !bodypt1.equals(this.values.get("bodypt1")))) {
			this.modified = UPDATED;
		}
		this.values.put("bodypt1", bodypt1);
	}

	public void setPlacea1(PlaceA placea1) {
		if ((placea1 == null && this.values.get("placea1") != null)
				|| (placea1 != null && !placea1.equals(this.values.get("placea1")))) {
			this.modified = UPDATED;
		}
		this.values.put("placea1", placea1);
	}

	public void setPlaceb1(PlaceB placeb1) {
		if ((placeb1 == null && this.values.get("placeb1") != null)
				|| (placeb1 != null && !placeb1.equals(this.values.get("placeb1")))) {
			this.modified = UPDATED;
		}
		this.values.put("placeb1", placeb1);
	}

	public void setMarksize(MarkSize marksize) {
		if ((marksize == null && this.values.get("marksize") != null)
				|| (marksize != null && !marksize.equals(this.values.get("marksize")))) {
			this.modified = UPDATED;
		}
		this.values.put("marksize", marksize);
	}

	public void setMarktype(MarkType marktype) {
		if ((marktype == null && this.values.get("marktype") != null)
				|| (marktype != null && !marktype.equals(this.values.get("marktype")))) {
			this.modified = UPDATED;
		}
		this.values.put("marktype", marktype);
	}

	public void setMarknumb(MarkNumber marknumb) {
		if ((marknumb == null && this.values.get("marknumb") != null)
				|| (marknumb != null && !marknumb.equals(this.values.get("marknumb")))) {
			this.modified = UPDATED;
		}
		this.values.put("marknumb", marknumb);
	}

	public void setMarkdegr(MarkDegree markdegr) {
		if ((markdegr == null && this.values.get("markdegr") != null)
				|| (markdegr != null && !markdegr.equals(this.values.get("markdegr")))) {
			this.modified = UPDATED;
		}
		this.values.put("markdegr", markdegr);
	}

	public void setOthrmark(OtherMark othrmark) {
		if ((othrmark == null && this.values.get("othrmark") != null)
				|| (othrmark != null && !othrmark.equals(this.values.get("othrmark")))) {
			this.modified = UPDATED;
		}
		this.values.put("othrmark", othrmark);
	}

	public void setMarkspec(SpecialMark markspec) {
		if ((markspec == null && this.values.get("markspec") != null)
				|| (markspec != null && !markspec.equals(this.values.get("markspec")))) {
			this.modified = UPDATED;
		}
		this.values.put("markspec", markspec);
	}

	public void setBodypt2(BodyPt bodypt2) {
		if ((bodypt2 == null && this.values.get("bodypt2") != null)
				|| (bodypt2 != null && !bodypt2.equals(this.values.get("bodypt2")))) {
			this.modified = UPDATED;
		}
		this.values.put("bodypt2", bodypt2);
	}

	public void setPlacea2(PlaceA placea2) {
		if ((placea2 == null && this.values.get("placea2") != null)
				|| (placea2 != null && !placea2.equals(this.values.get("placea2")))) {
			this.modified = UPDATED;
		}
		this.values.put("placea2", placea2);
	}

	public void setPlaceb2(PlaceB placeb2) {
		if ((placeb2 == null && this.values.get("placeb2") != null)
				|| (placeb2 != null && !placeb2.equals(this.values.get("placeb2")))) {
			this.modified = UPDATED;
		}
		this.values.put("placeb2", placeb2);
	}

	public void setMarksiz2(MarkSize marksiz2) {
		if ((marksiz2 == null && this.values.get("marksiz2") != null)
				|| (marksiz2 != null && !marksiz2.equals(this.values.get("marksiz2")))) {
			this.modified = UPDATED;
		}
		this.values.put("marksiz2", marksiz2);
	}

	public void setMarktyp2(MarkType marktyp2) {
		if ((marktyp2 == null && this.values.get("marktyp2") != null)
				|| (marktyp2 != null && !marktyp2.equals(this.values.get("marktyp2")))) {
			this.modified = UPDATED;
		}
		this.values.put("marktyp2", marktyp2);
	}

	public void setMarknum2(MarkNumber marknum2) {
		if ((marknum2 == null && this.values.get("marknum2") != null)
				|| (marknum2 != null && !marknum2.equals(this.values.get("marknum2")))) {
			this.modified = UPDATED;
		}
		this.values.put("marknum2", marknum2);
	}

	public void setMarkdeg2(MarkDegree markdeg2) {
		if ((markdeg2 == null && this.values.get("markdeg2") != null)
				|| (markdeg2 != null && !markdeg2.equals(this.values.get("markdeg2")))) {
			this.modified = UPDATED;
		}
		this.values.put("markdeg2", markdeg2);
	}

	public void setBodypt3(BodyPt bodypt3) {
		if ((bodypt3 == null && this.values.get("bodypt3") != null)
				|| (bodypt3 != null && !bodypt3.equals(this.values.get("bodypt3")))) {
			this.modified = UPDATED;
		}
		this.values.put("bodypt3", bodypt3);
	}

	public void setPlacea3(PlaceA placea3) {
		if ((placea3 == null && this.values.get("placea3") != null)
				|| (placea3 != null && !placea3.equals(this.values.get("placea3")))) {
			this.modified = UPDATED;
		}
		this.values.put("placea3", placea3);
	}

	public void setPlaceb3(PlaceB placeb3) {
		if ((placeb3 == null && this.values.get("placeb3") != null)
				|| (placeb3 != null && !placeb3.equals(this.values.get("placeb3")))) {
			this.modified = UPDATED;
		}
		this.values.put("placeb3", placeb3);
	}

	public void setMarksiz3(MarkSize marksiz3) {
		if ((marksiz3 == null && this.values.get("marksiz3") != null)
				|| (marksiz3 != null && !marksiz3.equals(this.values.get("marksiz3")))) {
			this.modified = UPDATED;
		}
		this.values.put("marksiz3", marksiz3);
	}

	public void setMarktyp3(MarkType marktyp3) {
		if ((marktyp3 == null && this.values.get("marktyp3") != null)
				|| (marktyp3 != null && !marktyp3.equals(this.values.get("marktyp3")))) {
			this.modified = UPDATED;
		}
		this.values.put("marktyp3", marktyp3);
	}

	public void setMarknum3(MarkNumber marknum3) {
		if ((marknum3 == null && this.values.get("marknum3") != null)
				|| (marknum3 != null && !marknum3.equals(this.values.get("marknum3")))) {
			this.modified = UPDATED;
		}
		this.values.put("marknum3", marknum3);
	}

	public void setMarkdeg3(MarkDegree markdeg3) {
		if ((markdeg3 == null && this.values.get("markdeg3") != null)
				|| (markdeg3 != null && !markdeg3.equals(this.values.get("markdeg3")))) {
			this.modified = UPDATED;
		}
		this.values.put("markdeg3", markdeg3);
	}

	public void setBodypt4(BodyPt bodypt4) {
		if ((bodypt4 == null && this.values.get("bodypt4") != null)
				|| (bodypt4 != null && !bodypt4.equals(this.values.get("bodypt4")))) {
			this.modified = UPDATED;
		}
		this.values.put("bodypt4", bodypt4);
	}

	public void setBodypt5(BodyPt bodypt5) {
		if ((bodypt5 == null && this.values.get("bodypt5") != null)
				|| (bodypt5 != null && !bodypt5.equals(this.values.get("bodypt5")))) {
			this.modified = UPDATED;
		}
		this.values.put("bodypt5", bodypt5);
	}

	public void setBodypt6(BodyPt bodypt6) {
		if ((bodypt6 == null && this.values.get("bodypt6") != null)
				|| (bodypt6 != null && !bodypt6.equals(this.values.get("bodypt6")))) {
			this.modified = UPDATED;
		}
		this.values.put("bodypt6", bodypt6);
	}

	public void setPlacea4(PlaceA placea4) {
		if ((placea4 == null && this.values.get("placea4") != null)
				|| (placea4 != null && !placea4.equals(this.values.get("placea4")))) {
			this.modified = UPDATED;
		}
		this.values.put("placea4", placea4);
	}

	public void setPlacea5(PlaceA placea5) {
		if ((placea5 == null && this.values.get("placea5") != null)
				|| (placea5 != null && !placea5.equals(this.values.get("placea5")))) {
			this.modified = UPDATED;
		}
		this.values.put("placea5", placea5);
	}

	public void setPlacea6(PlaceA placea6) {
		if ((placea6 == null && this.values.get("placea6") != null)
				|| (placea6 != null && !placea6.equals(this.values.get("placea6")))) {
			this.modified = UPDATED;
		}
		this.values.put("placea6", placea6);
	}

	public void setPlaceb4(PlaceB placeb4) {
		if ((placeb4 == null && this.values.get("placeb4") != null)
				|| (placeb4 != null && !placeb4.equals(this.values.get("placeb4")))) {
			this.modified = UPDATED;
		}
		this.values.put("placeb4", placeb4);
	}

	public void setPlaceb5(PlaceB placeb5) {
		if ((placeb5 == null && this.values.get("placeb5") != null)
				|| (placeb5 != null && !placeb5.equals(this.values.get("placeb5")))) {
			this.modified = UPDATED;
		}
		this.values.put("placeb5", placeb5);
	}

	public void setPlaceb6(PlaceB placeb6) {
		if ((placeb6 == null && this.values.get("placeb6") != null)
				|| (placeb6 != null && !placeb6.equals(this.values.get("placeb6")))) {
			this.modified = UPDATED;
		}
		this.values.put("placeb6", placeb6);
	}

	public void setMarksiz4(MarkSize marksiz4) {
		if ((marksiz4 == null && this.values.get("marksiz4") != null)
				|| (marksiz4 != null && !marksiz4.equals(this.values.get("marksiz4")))) {
			this.modified = UPDATED;
		}
		this.values.put("marksiz4", marksiz4);
	}

	public void setMarksiz5(MarkSize marksiz5) {
		if ((marksiz5 == null && this.values.get("marksiz5") != null)
				|| (marksiz5 != null && !marksiz5.equals(this.values.get("marksiz5")))) {
			this.modified = UPDATED;
		}
		this.values.put("marksiz5", marksiz5);
	}

	public void setMarksiz6(MarkSize marksiz6) {
		if ((marksiz6 == null && this.values.get("marksiz6") != null)
				|| (marksiz6 != null && !marksiz6.equals(this.values.get("marksiz6")))) {
			this.modified = UPDATED;
		}
		this.values.put("marksiz6", marksiz6);
	}

	public void setMarktyp6(MarkType marktyp6) {
		if ((marktyp6 == null && this.values.get("marktyp6") != null)
				|| (marktyp6 != null && !marktyp6.equals(this.values.get("marktyp6")))) {
			this.modified = UPDATED;
		}
		this.values.put("marktyp6", marktyp6);
	}

	public void setMarktyp5(MarkType marktyp5) {
		if ((marktyp5 == null && this.values.get("marktyp5") != null)
				|| (marktyp5 != null && !marktyp5.equals(this.values.get("marktyp5")))) {
			this.modified = UPDATED;
		}
		this.values.put("marktyp5", marktyp5);
	}

	public void setMarktyp4(MarkType marktyp4) {
		if ((marktyp4 == null && this.values.get("marktyp4") != null)
				|| (marktyp4 != null && !marktyp4.equals(this.values.get("marktyp4")))) {
			this.modified = UPDATED;
		}
		this.values.put("marktyp4", marktyp4);
	}

	public void setMarknum4(MarkNumber marknum4) {
		if ((marknum4 == null && this.values.get("marknum4") != null)
				|| (marknum4 != null && !marknum4.equals(this.values.get("marknum4")))) {
			this.modified = UPDATED;
		}
		this.values.put("marknum4", marknum4);
	}

	public void setMarknum5(MarkNumber marknum5) {
		if ((marknum5 == null && this.values.get("marknum5") != null)
				|| (marknum5 != null && !marknum5.equals(this.values.get("marknum5")))) {
			this.modified = UPDATED;
		}
		this.values.put("marknum5", marknum5);
	}

	public void setMarknum6(MarkNumber marknum6) {
		if ((marknum6 == null && this.values.get("marknum6") != null)
				|| (marknum6 != null && !marknum6.equals(this.values.get("marknum6")))) {
			this.modified = UPDATED;
		}
		this.values.put("marknum6", marknum6);
	}

	public void setMarkdeg6(MarkDegree markdeg6) {
		if ((markdeg6 == null && this.values.get("markdeg6") != null)
				|| (markdeg6 != null && !markdeg6.equals(this.values.get("markdeg6")))) {
			this.modified = UPDATED;
		}
		this.values.put("markdeg6", markdeg6);
	}

	public void setMarkdeg5(MarkDegree markdeg5) {
		if ((markdeg5 == null && this.values.get("markdeg5") != null)
				|| (markdeg5 != null && !markdeg5.equals(this.values.get("markdeg5")))) {
			this.modified = UPDATED;
		}
		this.values.put("markdeg5", markdeg5);
	}

	public void setMarkdeg4(MarkDegree markdeg4) {
		if ((markdeg4 == null && this.values.get("markdeg4") != null)
				|| (markdeg4 != null && !markdeg4.equals(this.values.get("markdeg4")))) {
			this.modified = UPDATED;
		}
		this.values.put("markdeg4", markdeg4);
	}

	public void setMajselpt(Integer majselpt) {
		if ((majselpt == null && this.values.get("majselpt") != null)
				|| (majselpt != null && !majselpt.equals(this.values.get("majselpt")))) {
			this.modified = UPDATED;
		}
		this.values.put("majselpt", majselpt);
	}

	public void setSource(String source) {
		if ((source == null && this.values.get("source") != null)
				|| (source != null && !source.equals(this.values.get("source")))) {
			this.modified = UPDATED;
		}
		this.values.put("source", source);
	}

	public void setMajbuypt(Integer majbuypt) {
		if ((majbuypt == null && this.values.get("majbuypt") != null)
				|| (majbuypt != null && !majbuypt.equals(this.values.get("majbuypt")))) {
			this.modified = UPDATED;
		}
		this.values.put("majbuypt", majbuypt);
	}

	public void setDatarr34(Integer datarr34) {
		if ((datarr34 == null && this.values.get("datarr34") != null)
				|| (datarr34 != null && !datarr34.equals(this.values.get("datarr34")))) {
			this.modified = UPDATED;
		}
		this.values.put("datarr34", datarr34);
	}

	public void setRegem1(Integer regem1) {
		if ((regem1 == null && this.values.get("regem1") != null)
				|| (regem1 != null && !regem1.equals(this.values.get("regem1")))) {
			this.modified = UPDATED;
		}
		this.values.put("regem1", regem1);
	}

	public Long getIid() {
		return (Long) this.values.get("iid");
	}

	public Long getSlaveId() {
		return (Long) this.values.get("slaveId");
	}

	public Long getVoyageId() {
		return (Long) this.values.get("voyageId");
	}

	public SexAge getSexage() {
		return (SexAge) this.values.get("sexage");
	}

	public Float getAge() {
		return (Float) this.values.get("age");
	}

	public Integer getFeet() {
		return (Integer) this.values.get("feet");
	}

	public Float getInch() {
		return (Float) this.values.get("inch");
	}

	public Integer getAgeinf() {
		return (Integer) this.values.get("ageinf");
	}

	public Country getCountry() {
		return (Country) this.values.get("country");
	}

	public String getName() {
		return (String) this.values.get("name");
	}

	public Integer getUnclear() {
		return (Integer) this.values.get("unclear");
	}

	public String getNameimp() {
		return (String) this.values.get("nameimp");
	}

	public Nation getNatimp() {
		return (Nation) this.values.get("natimp");
	}

	public BodyPt getBodypt1() {
		return (BodyPt) this.values.get("bodypt1");
	}

	public PlaceA getPlacea1() {
		return (PlaceA) this.values.get("placea1");
	}

	public PlaceB getPlaceb1() {
		return (PlaceB) this.values.get("placeb1");
	}

	public MarkSize getMarksize() {
		return (MarkSize) this.values.get("marksize");
	}

	public MarkType getMarktype() {
		return (MarkType) this.values.get("marktype");
	}

	public MarkNumber getMarknumb() {
		return (MarkNumber) this.values.get("marknumb");
	}

	public MarkDegree getMarkdegr() {
		return (MarkDegree) this.values.get("markdegr");
	}

	public OtherMark getOthrmark() {
		return (OtherMark) this.values.get("othrmark");
	}

	public SpecialMark getMarkspec() {
		return (SpecialMark) this.values.get("markspec");
	}

	public BodyPt getBodypt2() {
		return (BodyPt) this.values.get("bodypt2");
	}

	public PlaceA getPlacea2() {
		return (PlaceA) this.values.get("placea2");
	}

	public PlaceB getPlaceb2() {
		return (PlaceB) this.values.get("placeb2");
	}

	public MarkSize getMarksiz2() {
		return (MarkSize) this.values.get("marksiz2");
	}

	public MarkType getMarktyp2() {
		return (MarkType) this.values.get("marktyp2");
	}

	public MarkNumber getMarknum2() {
		return (MarkNumber) this.values.get("marknum2");
	}

	public MarkDegree getMarkdeg2() {
		return (MarkDegree) this.values.get("markdeg2");
	}

	public BodyPt getBodypt3() {
		return (BodyPt) this.values.get("bodypt3");
	}

	public PlaceA getPlacea3() {
		return (PlaceA) this.values.get("placea3");
	}

	public PlaceB getPlaceb3() {
		return (PlaceB) this.values.get("placeb3");
	}

	public MarkSize getMarksiz3() {
		return (MarkSize) this.values.get("marksiz3");
	}

	public MarkType getMarktyp3() {
		return (MarkType) this.values.get("marktyp3");
	}

	public MarkNumber getMarknum3() {
		return (MarkNumber) this.values.get("marknum3");
	}

	public MarkDegree getMarkdeg3() {
		return (MarkDegree) this.values.get("markdeg3");
	}

	public BodyPt getBodypt4() {
		return (BodyPt) this.values.get("bodypt4");
	}

	public BodyPt getBodypt5() {
		return (BodyPt) this.values.get("bodypt5");
	}

	public BodyPt getBodypt6() {
		return (BodyPt) this.values.get("bodypt6");
	}

	public PlaceA getPlacea4() {
		return (PlaceA) this.values.get("placea4");
	}

	public PlaceA getPlacea5() {
		return (PlaceA) this.values.get("placea5");
	}

	public PlaceA getPlacea6() {
		return (PlaceA) this.values.get("placea6");
	}

	public PlaceB getPlaceb4() {
		return (PlaceB) this.values.get("placeb4");
	}

	public PlaceB getPlaceb5() {
		return (PlaceB) this.values.get("placeb5");
	}

	public PlaceB getPlaceb6() {
		return (PlaceB) this.values.get("placeb6");
	}

	public MarkSize getMarksiz4() {
		return (MarkSize) this.values.get("marksiz4");
	}

	public MarkSize getMarksiz5() {
		return (MarkSize) this.values.get("marksiz5");
	}

	public MarkSize getMarksiz6() {
		return (MarkSize) this.values.get("marksiz6");
	}

	public MarkType getMarktyp6() {
		return (MarkType) this.values.get("marktyp6");
	}

	public MarkType getMarktyp5() {
		return (MarkType) this.values.get("marktyp5");
	}

	public MarkType getMarktyp4() {
		return (MarkType) this.values.get("marktyp4");
	}

	public MarkNumber getMarknum4() {
		return (MarkNumber) this.values.get("marknum4");
	}

	public MarkNumber getMarknum5() {
		return (MarkNumber) this.values.get("marknum5");
	}

	public MarkNumber getMarknum6() {
		return (MarkNumber) this.values.get("marknum6");
	}

	public MarkDegree getMarkdeg6() {
		return (MarkDegree) this.values.get("markdeg6");
	}

	public MarkDegree getMarkdeg5() {
		return (MarkDegree) this.values.get("markdeg5");
	}

	public MarkDegree getMarkdeg4() {
		return (MarkDegree) this.values.get("markdeg4");
	}

	public Integer getMajselpt() {
		return (Integer) this.values.get("majselpt");
	}

	public String getSource() {
		return (String) this.values.get("source");
	}

	public Integer getMajbuypt() {
		return (Integer) this.values.get("majbuypt");
	}

	public Integer getDatarr34() {
		return (Integer) this.values.get("datarr34");
	}

	public Integer getRegem1() {
		return (Integer) this.values.get("regem1");
	}
	/* END AUTOGENERATED */
}