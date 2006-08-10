package edu.emory.library.tast.dm.attributes;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;

import edu.emory.library.tast.util.HibernateUtil;

public class Group implements Serializable, VisibleColumn {

	private static final long serialVersionUID = -2786637413827394050L;

	private Long id;

	private String name;

	private String userLabel;

	private Set compoundAttributes = new HashSet();

	private Set attributes = new HashSet();

	private ObjectType objectType;

	private String description;
	
	private Attribute[] attributesSortedByUserLabelOrName = null;
	private Attribute[] attributesSortedByUserLabel = null;
	private Attribute[] attributesSortedByName = null;
	private CompoundAttribute[] compoundAttributesSortedByUserLabelOrName = null;
	private CompoundAttribute[] compoundAttributesSortedByUserLabel = null;
	private CompoundAttribute[] compoundAttributesSortedByName = null;
	private AbstractAttribute[] allAttributesSortedByUserLabelOrName = null;
	private AbstractAttribute[] allAttributesSortedByUserLabel = null;
	private AbstractAttribute[] allAttributesSortedByName = null;

	public Group() {
	}

	public static Group newForVoyages() {
		Group group = new Group();
		group.setObjectType(ObjectType.getVoyages());
		return group;
	}

	public static Group newForVoyages(Session session) {
		Group group = new Group();
		group.setObjectType(ObjectType.getVoyages(session));
		return group;
	}

	public static Group newForSlaves() {
		Group group = new Group();
		group.setObjectType(ObjectType.getSlaves());
		return group;
	}

	public static Group newForSlaves(Session session) {
		Group group = new Group();
		group.setObjectType(ObjectType.getSlaves(session));
		return group;
	}

	public static Group loadById(Long id) {
		Session session = HibernateUtil.getSession();
		Group group = loadById(id, session);
		session.close();
		return group;
	}

	public static Group loadById(Long id, Session session) {
		Criteria crit = session.createCriteria(Group.class);
		crit.add(Expression.eq("id", id));
		crit.setMaxResults(1);
		List list = crit.list();
		if (list == null || list.size() == 0)
			return null;
		return (Group) list.get(0);
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserLabel() {
		return userLabel;
	}

	public void setUserLabel(String userLabel) {
		this.userLabel = userLabel;
	}

	public String getUserLabelOrName() {
		if (userLabel != null && userLabel.length() > 0)
			return userLabel;
		if (name != null && name.length() > 0)
			return name;
		return name;
	}
	
	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Integer getType() {
		return new Integer(AbstractAttribute.TYPE_STRING);
	}

	public Set getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Set attributes)
	{
		clearCachedInfoForAttributes();
		this.attributes = attributes;
	}
	
	public Set getCompoundAttributes()
	{
		return compoundAttributes;
	}

	public void setCompoundAttributes(Set groups)
	{
		clearCachedInfoForCompoundAttributes();
		this.compoundAttributes = groups;
	}
	
	private void clearCachedInfoForAttributes()
	{
		attributesSortedByName = null;
		attributesSortedByUserLabel = null;
		attributesSortedByUserLabelOrName = null;
		allAttributesSortedByName = null;
		allAttributesSortedByUserLabel = null;
		allAttributesSortedByUserLabelOrName = null;
	}

	private void clearCachedInfoForCompoundAttributes()
	{
		compoundAttributesSortedByName = null;
		compoundAttributesSortedByUserLabel = null;
		compoundAttributesSortedByUserLabelOrName = null;
		allAttributesSortedByName = null;
		allAttributesSortedByUserLabel = null;
		allAttributesSortedByUserLabelOrName = null;
	}
	

	public int getAttributesCount() {
		if (attributes == null) return 0;
		return attributes.size();
	}

	public int getCompoundAttributesCount() {
		if (compoundAttributes == null) return 0;
		return compoundAttributes.size();
	}
	
	public int noOfCompoundAttributesInCategory(int category)
	{
		int n = 0;
		for (Iterator iter = compoundAttributes.iterator(); iter.hasNext();)
		{
			CompoundAttribute attr = (CompoundAttribute) iter.next();
			if (attr.isVisibleByCategory(category)) n++;
		}
		return n;
	}
	
	public int noOfAttributesInCategory(int category)
	{
		int n = 0;
		for (Iterator iter = attributes.iterator(); iter.hasNext();)
		{
			Attribute attr = (Attribute) iter.next();
			if (attr.isVisibleByCategory(category)) n++;
		}
		return n;
	}

	public int noOfAllAttributesInCategory(int category)
	{
		return noOfAttributesInCategory(category) + noOfCompoundAttributesInCategory(category);
	}
	
	private Attribute[] createAttributesArray()
	{
		Attribute arr[] = new Attribute[getAttributesCount()];
		attributes.toArray(arr);
		return arr;
	}
	
	public Attribute[] getAttributesSortedByUserLabelOrName()
	{
		if (attributesSortedByUserLabelOrName == null)
		{
			attributesSortedByUserLabelOrName = createAttributesArray();
			Attribute.sortByUserLabelOrName(attributesSortedByUserLabelOrName);
		}
		return attributesSortedByUserLabelOrName;
	}

	public Attribute[] getAttributesSortedByUserLabel()
	{
		if (attributesSortedByUserLabel == null)
		{
			attributesSortedByUserLabel = createAttributesArray();
			Attribute.sortByUserLabel(attributesSortedByUserLabel);
		}
		return attributesSortedByUserLabel;
	}

	public Attribute[] getAttributesSortedByName()
	{
		if (attributesSortedByName == null)
		{
			attributesSortedByName = createAttributesArray();
			Attribute.sortByName(attributesSortedByName);
		}
		return attributesSortedByName;
	}
	
	private CompoundAttribute[] createCompoundAttributesArray()
	{
		CompoundAttribute arr[] = new CompoundAttribute[getCompoundAttributesCount()];
		compoundAttributes.toArray(arr);
		return arr;
	}
	
	public CompoundAttribute[] getCompoundAttributesSortedByUserLabelOrName()
	{
		if (compoundAttributesSortedByUserLabelOrName == null)
		{
			compoundAttributesSortedByUserLabelOrName = createCompoundAttributesArray();
			CompoundAttribute.sortByUserLabelOrName(compoundAttributesSortedByUserLabelOrName);
		}
		return compoundAttributesSortedByUserLabelOrName;
	}

	public CompoundAttribute[] getCompoundAttributesSortedByUserLabel()
	{
		if (compoundAttributesSortedByUserLabel == null)
		{
			compoundAttributesSortedByUserLabel = createCompoundAttributesArray();
			CompoundAttribute.sortByUserLabel(compoundAttributesSortedByUserLabel);
		}
		return compoundAttributesSortedByUserLabel;
	}

	public CompoundAttribute[] getCompoundAttributesSortedByName()
	{
		if (compoundAttributesSortedByName == null)
		{
			compoundAttributesSortedByName = createCompoundAttributesArray();
			CompoundAttribute.sortByName(compoundAttributesSortedByName);
		}
		return compoundAttributesSortedByName;
	}
	
	private AbstractAttribute[] createAllAttributesArray()
	{
		AbstractAttribute[] arr = new AbstractAttribute[getAttributesCount() + getCompoundAttributesCount()];
		int i = 0;
		if (attributes != null)
		{
			for (Iterator iter = attributes.iterator(); iter.hasNext();)
			{
				Attribute attr = (Attribute) iter.next();
				arr[i++] = attr; 
			}
		}
		if (compoundAttributes != null)
		{
			for (Iterator iter = compoundAttributes.iterator(); iter.hasNext();)
			{
				CompoundAttribute compAttr = (CompoundAttribute) iter.next();
				arr[i++] = compAttr; 
			}
		}
		return arr;
	}

	public AbstractAttribute[] getAllAttributesSortedByUserLabelOrName()
	{
		if (allAttributesSortedByUserLabelOrName == null)
		{
			allAttributesSortedByUserLabelOrName = createAllAttributesArray();
			AbstractAttribute.sortByUserLabelOrName(allAttributesSortedByUserLabelOrName);
		}
		return allAttributesSortedByUserLabelOrName;
	}

	public AbstractAttribute[] getAllAttributesSortedByUserLabel()
	{
		if (allAttributesSortedByUserLabel == null)
		{
			allAttributesSortedByUserLabel = createAllAttributesArray();
			AbstractAttribute.sortByUserLabel(allAttributesSortedByUserLabel);
		}
		return allAttributesSortedByUserLabel;
	}

	public AbstractAttribute[] getAllAttributesSortedByName()
	{
		if (allAttributesSortedByName == null)
		{
			allAttributesSortedByName = createAllAttributesArray();
			AbstractAttribute.sortByName(allAttributesSortedByName);
		}
		return allAttributesSortedByName;
	}
	
	public String toLongString() {
		return "Group: " + "id = " + this.id + "\n" + "name = " + this.name
				+ "\n" + "description = " + this.description + "\n"
				+ "user label = " + this.userLabel + "\n" + "attributes = "
				+ this.attributes.size() + "\n" + "compound attributes = "
				+ this.compoundAttributes.size();
	}

	public String toString() {
		if (this.userLabel != null && !this.userLabel.equals("")) {
			return this.userLabel;
		} else {
			return this.name;
		}
	}

	public static class UserLabelComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Group g1 = (Group) o1;
			Group g2 = (Group) o2;
			return g1.getUserLabel().compareTo(g2.getUserLabel());
		}
	}

	public static void sortByUserLabel(Object[] array) {
		Arrays.sort(array, new Group.UserLabelComparator());
	}

	public static class NameComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Group g1 = (Group) o1;
			Group g2 = (Group) o2;
			return g1.getName().compareTo(g2.getName());
		}
	}

	public static void sortByName(Object[] array) {
		Arrays.sort(array, new Group.NameComparator());
	}

	public static class UserLabelOrNameComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Group g1 = (Group) o1;
			Group g2 = (Group) o2;
			return g1.getUserLabelOrName().compareToIgnoreCase(g2.getUserLabelOrName());
		}
	}

	public static void sortByUserLabelOrName(Object[] array) {
		Arrays.sort(array, new Group.UserLabelOrNameComparator());
	}

	public static void sortByUserLabelOrName(List list) {
		Collections.sort(list, new Group.UserLabelOrNameComparator());
	}
	
	public String encodeToString() {
		return "Group_" + this.getId();
	}

}