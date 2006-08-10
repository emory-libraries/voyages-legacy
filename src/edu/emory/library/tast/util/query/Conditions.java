package edu.emory.library.tast.util.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.impl.SessionFactoryImpl;

import edu.emory.library.tast.util.HibernateUtil;

/**
 * Class that represents any conditions for query.
 * It helps to build conditions.
 * It can contain either simple conditions (attribute, value, operator) or
 * can have another level of Conditions. You can build as many levels as you want.
 * @author Pawel Jurczyk
 *
 */
public class Conditions {

	/**
	 * JOIN_ operators are used as constructor of Conditions class.
	 * It tells how to connect attributes/subconditions.
	 */
	public static final int JOIN_AND = 1;

	public static final int JOIN_OR = 2;

	public static final int JOIN_NOT = 0;

	/**
	 * OP_ operators are operators (like == or != etc.)
	 */
	public static final int OP_EQUALS = 1;

	public static final int OP_NOT_EQUALS = 2;

	public static final int OP_GREATER = 3;

	public static final int OP_GREATER_OR_EQUAL = 4;

	public static final int OP_SMALLER = 5;

	public static final int OP_SMALLER_OR_EQUAL = 6;

	public static final int OP_LIKE = 7;

	public static final int OP_IS = 8;

	public static final int OP_IS_NOT = 9;

	public static final int OP_IN = 10;

	/**
	 * Operator used to join conditions/subconditions
	 */
	private int joinCondition = JOIN_AND;

	/**
	 * Array of simple conditions (Keeps Condition objects).
	 */
	private ArrayList conditions = new ArrayList();

	/**
	 * Array of subconditions (keeps Conditions objects).
	 */
	private ArrayList subConditions = new ArrayList();

	/**
	 * Class that represents simple condition.
	 * It has attribute in condition, its value and operator.
	 * @author Pawel Jurczyk
	 *
	 */
	private class Condition {
		
		/**
		 * Operator in condition.
		 */
		public String op;

		/**
		 * Desired value.
		 */
		public Object value;

		/**
		 * Attribute name.
		 */
		public String attribute;

		/**
		 * Constructor of condition.
		 * @param attr attribute name
		 * @param op operator
		 * @param val value
		 */
		public Condition(String attr, String op, Object val) {
			this.op = op;
			this.value = val;
			this.attribute = attr;
		}

	}
	
	/**
	 * Gets attribute from given expression (expression can be e.g. SQL function.)
	 * @param exp expression containing attribute
	 * @return attribute name
	 */
	private String getAttribute(String exp) {
		
		//Use appropriate dialect - the same as hibernate
		Dialect dialect = 
			((SessionFactoryImpl)HibernateUtil.getSessionFactory()).getSettings().getDialect();
		
		//Check registered functions
		Map functions = dialect.getFunctions();
		for (Iterator iter = functions.values().iterator(); iter.hasNext();) {
			SQLFunction element = (SQLFunction) iter.next();
			if (exp.startsWith(element.toString())) {
				//If we have SQL function, extract attribute
				return exp.substring(exp.indexOf(",") + 1, exp.indexOf(")")).trim();
			}
		}  
		
		//No function - passed just attribute
		return exp;
	}

	
	/**
	 * Constructor. Uses by default JOIN_AND.
	 *
	 */
	public Conditions() {
		
		this.joinCondition = JOIN_AND;
		
	}

	/**
	 * Constructor using desired JOIN operator.
	 * @param joinCondition join operator.
	 */
	public Conditions(int joinCondition) {
		this.joinCondition = joinCondition;
	}

	/**
	 * Adds simple condition.
	 * @param attrName attribute
	 * @param value value
	 * @param op operator
	 */
	public void addCondition(String attrName, Object value, int op) {
		String opStr = null;

		//Recognize operator
		switch (op) {
		case OP_EQUALS:
			opStr = " = ";
			break;
		case OP_NOT_EQUALS:
			opStr = " <> ";
			break;
		case OP_GREATER:
			opStr = " > ";
			break;
		case OP_GREATER_OR_EQUAL:
			opStr = " >= ";
			break;
		case OP_SMALLER:
			opStr = " < ";
			break;
		case OP_SMALLER_OR_EQUAL:
			opStr = " <= ";
			break;
		case OP_LIKE:
			opStr = " like ";
			break;
		case OP_IS:
			opStr = " is ";
			break;
		case OP_IS_NOT:
			opStr = " is not ";
			break;
		case OP_IN:
			opStr = " in ";
			break;
		default:
			throw new RuntimeException("Wrong operand!");
		}
		
		//Add simple condition
		this.conditions.add(new Condition(attrName, opStr, value));

	}

	/**
	 * Adds subcondition
	 * @param subCond subcondition
	 */
	public void addCondition(Conditions subCond) {
		this.subConditions.add(subCond);
	}

	/**
	 * Gets HQL for conditions.
	 * @return ConditionResponse object.
	 */
	public ConditionResponse getConditionHQL() {
		
		//Check the number of items.
		int size = this.conditions.size() + this.subConditions.size();
		
		if (this.joinCondition == JOIN_NOT && size != 1) {
			throw new RuntimeException(
					"With JOIN_NOT only one condition allowable!");
		}

		int processed = 0;
		StringBuffer ret = new StringBuffer();
		
		//Handle not
		if (this.joinCondition == JOIN_NOT) {
			ret.append("not (");
		}

		HashMap retMap = new HashMap();

		//Handle simple conditions.
		Iterator iter = this.conditions.iterator();
		while (iter.hasNext()) {
			Condition c = (Condition) iter.next();
			if (c.value == null) {
				//Handle null request
				String attr = this.getAttribute(c.attribute);
				processed++;
				ret.append(attr);
				ret.append(c.op);
				ret.append("null");
			} else if (c.op.equals(" in ")) {
				//Handle in request
				String attr = c.attribute;
				String val = (this.getAttribute(attr).replaceAll("\\.", "") + attr.hashCode() + c.value.hashCode()).replace('-', '_');
				Object[] values = (Object[])c.value;
				processed++;
				ret.append(attr);
				ret.append(c.op);
				ret.append("(");
				for (int i = 0; i < values.length; i++) {
					ret.append(" :");
					ret.append(val + "_" + i);
					retMap.put(val + "_" + i, values[i]);
					if (i < values.length - 1) {
						ret.append(", ");
					}
				}
				ret.append(") ");
			} else if (!(c.value instanceof DirectValue)) {
				//Handle anything except direct value - avoid using :param notation.
				String attr = c.attribute;
				String val = (this.getAttribute(attr).replaceAll("\\.", "") + attr.hashCode() + c.value.hashCode()).replace('-', '_');
				Object value = c.value;
				processed++;
				ret.append(attr);
				ret.append(c.op);
				ret.append(" :");
				ret.append(val);
				retMap.put(val, value);
			} else {
				//Handle direct value - avoid using :param notation.
				processed++;
				ret.append(c.attribute);
				ret.append(c.op);
				ret.append(c.value.toString());
			}
			if (processed < size) {
				ret.append(this.joinCondition == JOIN_AND ? " and " : " or ");
			}
		}

		//Handle  subconditions
		iter = this.subConditions.iterator();
		while (iter.hasNext()) {
			processed++;
			ConditionResponse child = ((Conditions) iter.next())
					.getConditionHQL();
			ret.append("(").append(child.conditionString).append(")");
			retMap.putAll(child.properties);
			if (processed < size) {
				ret.append(this.joinCondition == JOIN_AND ? " and " : " or ");
			}
		}

		if (this.joinCondition == JOIN_NOT) {
			ret.append(")");
		}

		//Return result.
		ConditionResponse res = new ConditionResponse();
		res.conditionString = ret;
		res.properties = retMap;
		return res;
	}
	
	/**
	 * Equals implementation.
	 * Two conditions are equal if the have the same set of subconditions, attributes and are joined
	 * using the same join operator.
	 */
	public boolean equals(Object o) {
		if (o instanceof Conditions) {
			Conditions that = (Conditions)o;
			return (this.conditions.equals(that.conditions) 
					&& this.joinCondition == that.joinCondition
					&& this.subConditions.equals(that.subConditions));
		}
		return false;
	}
	
	/**
	 * Clones conditions.
	 */
	public Object clone() {
		Conditions newC = new Conditions();
		newC.conditions = (ArrayList) this.conditions.clone();
		newC.joinCondition = this.joinCondition;
		newC.subConditions = (ArrayList) this.subConditions.clone();
		return newC;
	}

	/**
	 * Creates new Conditions object. It has the same subconditions but all the attributes
	 * have added specific prefix.
	 */
	public Conditions addAttributesPrefix(String prefix) {
		Conditions newC = new Conditions();
		ArrayList conditions = new ArrayList();
		//Handle simple conditions
		for (Iterator iter = this.conditions.iterator(); iter.hasNext();) {
			Condition condition = (Condition) iter.next();
			Condition newCondition;
			if (condition.attribute.startsWith("date_")) {
				String attribute = getAttribute(condition.attribute); 
				newCondition = new Condition(condition.attribute.replaceAll(attribute, prefix + attribute), condition.op, condition.value);
			} else {
				newCondition = new Condition(prefix + condition.attribute, condition.op, condition.value);
			}
			conditions.add(newCondition);
		}
		//Handle subconditions.
		ArrayList newSubconditions = new ArrayList();
		for (Iterator iter = this.subConditions.iterator(); iter.hasNext();) {
			Conditions subConditions = (Conditions) iter.next();
			newSubconditions.add(subConditions.addAttributesPrefix(prefix));			
		}
		
		//Set values in new query.
		newC.conditions = conditions;
		newC.subConditions = newSubconditions;
		newC.joinCondition = this.joinCondition;
		return newC;
	}
	
	/**
	 * Gets String representation of query.
	 */
	public String toString() {
		ConditionResponse response = this.getConditionHQL();
		String out = response.conditionString.toString();
		System.out.println(out);
		Iterator iter = response.properties.keySet().iterator();
		while (iter.hasNext()) {
			String key = iter.next().toString();
			System.out.println("Replacing " + key + " by " + response.properties.get(key).toString());
			out = out.replaceAll(":" + key, response.properties.get(key).toString());
		}
		return out;
	}
	
//	/**
//	 * Gets all attributes that are present in conditions.
//	 * @return
//	 */
//	public List getConditionedAttributes() {
//		ArrayList list = new ArrayList();
//		for (Iterator iter = this.conditions.iterator(); iter.hasNext();) {
//			Condition element = (Condition) iter.next();
//			list.add(element.attribute);			
//		}
//		for (Iterator iter = this.subConditions.iterator(); iter.hasNext();) {
//			Conditions element = (Conditions) iter.next();
//			list.addAll(element.getConditionedAttributes());
//		}
//		return list;
//	}
}