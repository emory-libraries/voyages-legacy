package edu.emory.library.tast.dm;

import java.util.HashMap;
import java.util.Map;

import edu.emory.library.tas.util.HibernateConnector;
import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.DictionaryAttribute;
import edu.emory.library.tast.dm.attributes.NumericAttribute;

public class Estimate {

	private static Map attributes = new HashMap();
	static {
		attributes.put("id", new NumericAttribute("id", "Estimate"));
		attributes.put("year", new NumericAttribute("year", "Estimate"));
		attributes.put("nation", new DictionaryAttribute("nation", "Estimate"));
		attributes.put("expRegion", new DictionaryAttribute("expRegion", "Estimate"));
		attributes.put("impRegion", new DictionaryAttribute("impRegion", "Estimate"));
		attributes.put("slavImported", new NumericAttribute("slavImported", "Estimate"));
		attributes.put("slavExported", new NumericAttribute("slavExported", "Estimate"));
	}

	private Long id;
	
	private Integer year;

	private Nation nation;

	private Region expRegion;

	private Region impRegion;

	private double slavImported;

	private double slavExported;

	public Nation getNation() {
		return nation;
	}

	public void setNation(Nation nation) {
		this.nation = nation;
	}

	public double getSlavExported() {
		return slavExported;
	}

	public void setSlavExported(double slavExported) {
		this.slavExported = slavExported;
	}

	public double getSlavImported() {
		return slavImported;
	}

	public void setSlavImported(double slavImported) {
		this.slavImported = slavImported;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Region getExpRegion() {
		return expRegion;
	}

	public void setExpRegion(Region expRegion) {
		this.expRegion = expRegion;
	}

	public Region getImpRegion() {
		return impRegion;
	}

	public void setImpRegion(Region impRegion) {
		this.impRegion = impRegion;
	}

	public String toString() {
		return "Estimate: " + this.nation + ", " + this.expRegion + ", "
				+ this.impRegion + ", " + this.year + ", " + this.slavExported
				+ ", " + this.slavImported;
	}
	
	public boolean equals(Object o) {
		if (!(o instanceof Estimate)) {
			return false;
		}
		Estimate that = (Estimate)o;
		return this.getNation().equals(that.getNation()) &&
				this.year.equals(that.year) &&
				((this.expRegion == null && that.expRegion == null) || 
						(this.expRegion != null && this.expRegion.equals(that.expRegion))) &&
				((this.impRegion == null && that.impRegion == null) || 
						(this.impRegion != null && this.impRegion.equals(that.impRegion)));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void save() {
		HibernateConnector.getConnector().saveObject(this);
	}

	public static Attribute getAttribute(String name)
	{
		return (Attribute)attributes.get(name);
	}

}