/*
Copyright 2010 Emory University
	
	    This file is part of Trans-Atlantic Slave Voyages.
	
	    Trans-Atlantic Slave Voyages is free software: you can redistribute it and/or modify
	    it under the terms of the GNU General Public License as published by
	    the Free Software Foundation, either version 3 of the License, or
	    (at your option) any later version.
	
	    Trans-Atlantic Slave Voyages is distributed in the hope that it will be useful,
	    but WITHOUT ANY WARRANTY; without even the implied warranty of
	    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	    GNU General Public License for more details.
	
	    You should have received a copy of the GNU General Public License
	    along with Trans-Atlantic Slave Voyages.  If not, see <http://www.gnu.org/licenses/>. 
*/
package edu.emory.library.tast.slaves;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;

import edu.emory.library.tast.AppConfig;
import edu.emory.library.tast.TastResource;
import edu.emory.library.tast.common.QuerySummaryItem;
import edu.emory.library.tast.db.TastDbConditions;
import edu.emory.library.tast.dm.Area;
import edu.emory.library.tast.dm.Country;
import edu.emory.library.tast.dm.Port;
import edu.emory.library.tast.dm.Region;
import edu.emory.library.tast.dm.SexAge;
import edu.emory.library.tast.dm.Slave;
import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.specific.FunctionAttribute;
import edu.emory.library.tast.util.EqualsUtil;
import edu.emory.library.tast.util.StringUtils;

/**
 * This class represents query for slaves. Each field corresponds to some GUI options.
 * For more details plese see web page: resources->names database.
 * The selection options you see on the left are saved in this class.
 * 
 * This slass also implements XMLExportable so that it can be saved in configuration.
 * For more details about it see SlavesBean.permLink() and SlavesBean.restoreLink() 
 *
 */
public class SlavesQuery implements Cloneable
{
	
	private static final int SIERRA_LEONE_ID = AppConfig.getConfiguration().getInt(AppConfig.SLAVES_SIERRA_LEONE_ID);
	private static final int HAVANA_ID = AppConfig.getConfiguration().getInt(AppConfig.SLAVES_HAVANA_ID);

	private static final int WOMAN_ID = 5;
	private static final int MAN_ID = 1;
	private static final int FEMALE_ID = 4;
	private static final int MALE_ID = 3;
	private static final int GIRL_ID = 6;
	private static final int BOY_ID = 2;
	
	private Integer ageFrom;
	private Integer ageTo;
	private Integer heightFrom;
	private Integer heightTo;
	private Integer yearFrom;
	private Integer yearTo;
	private Integer voyageId;
	private String slaveName;
	private String shipName;
	private Boolean boys;
	private Boolean men;
	private Boolean males;
	private Boolean girls;
	private Boolean women;
	private Boolean females;
	private String[] countries;
	private String[] embPorts;
	private Boolean disembSierraLeone;
	private Boolean disembHavana;

	public SlavesQuery()
	{
		
		ageFrom = null;
		ageTo = null;
		heightFrom = null;
		heightTo = null;
		yearFrom = null;
		yearTo = null;
		slaveName = null;
		shipName = null;
		voyageId = null;
		boys = new Boolean(true);
		men = new Boolean(true);
		males = new Boolean(true);
		girls = new Boolean(true);
		women = new Boolean(true);
		females = new Boolean(true);
		countries = new String[] {};
		embPorts = new String[] {};
		disembSierraLeone = new Boolean(true);
		disembHavana = new Boolean(true);
		
	}
	
	private TastDbConditions prepareMultiselectConditions(Attribute attr, Boolean[] states, Object[] values, List querySummary, String variableName, String[] labels)
	{

		boolean allSelected = true;
		boolean allDeselected = true;
		
		for (int i = 0; i < states.length; i++)
		{
			if (!states[i].booleanValue())
			{
				allSelected = false;
			}
			else
			{
				allDeselected = false;
			}
		}
		
		if (allSelected || allDeselected)
			return null;
		
		TastDbConditions conditions = new TastDbConditions(TastDbConditions.OR);
		
		StringBuffer querySummaryValue = null;
		if (querySummary != null)
			querySummaryValue = new StringBuffer();
		
		int j = 0;
		for (int i = 0; i < states.length; i++)
		{
			if (states[i].booleanValue())
			{
				if (querySummary != null)
				{
					if (j > 0) querySummaryValue.append(", ");
					querySummaryValue.append(labels[i]);
				}
				conditions.addCondition(attr, values[i], TastDbConditions.OP_EQUALS);
				j++;
			}
		}
		
		if (querySummary != null)
			querySummary.add(new QuerySummaryItem(
					variableName, querySummaryValue.toString()));

		return conditions;

	}
	
	public TastDbConditions createConditions(Session sess, List querySummary)
	{
		
		Port portHavana = Port.loadById(sess, HAVANA_ID);
		Port portSierraLeone = Port.loadById(sess, SIERRA_LEONE_ID);

		SexAge genderBoy = SexAge.loadById(sess, BOY_ID);
		SexAge genderGirl = SexAge.loadById(sess, GIRL_ID);
		SexAge genderMale = SexAge.loadById(sess, MALE_ID);
		SexAge genderFemale = SexAge.loadById(sess, FEMALE_ID);
		SexAge genderMan = SexAge.loadById(sess, MAN_ID);
		SexAge genderWoman = SexAge.loadById(sess, WOMAN_ID);
		
		TastDbConditions c = new TastDbConditions();
		
		FunctionAttribute slaveNameUpperAttr = new FunctionAttribute(
				"remove_accents", new Attribute[] {
						new FunctionAttribute("upper", new Attribute[] {Slave.getAttribute("name")})});
		
		if (!StringUtils.isNullOrEmpty(this.slaveName, true))
		{
			String[] s = StringUtils.extractQueryKeywords(this.slaveName, StringUtils.UPPER_CASE);
			for (int i = 0; i < s.length; i++)
			{
				c.addCondition(slaveNameUpperAttr, "%" + s[i] + "%", TastDbConditions.OP_LIKE);
			}
			if (querySummary != null && s.length > 0)
			{
				querySummary.add(new QuerySummaryItem(
						TastResource.getText("slaves_query_slave_name"),
						this.slaveName.trim()));
			}
		}
		
		FunctionAttribute shipNameUpperAttr = new FunctionAttribute(
				"remove_accents", new Attribute[] {
						new FunctionAttribute("upper", new Attribute[] {Slave.getAttribute("shipname")})});
		
		if (!StringUtils.isNullOrEmpty(this.shipName, true))
		{
			String[] s = StringUtils.extractQueryKeywords(this.shipName, StringUtils.UPPER_CASE);
			for (int i = 0; i < s.length; i++)
			{
				c.addCondition(shipNameUpperAttr, "%" + s[i] + "%", TastDbConditions.OP_LIKE);
			}
			if (querySummary != null && s.length > 0)
			{
				querySummary.add(new QuerySummaryItem(
						TastResource.getText("slaves_query_ship_name"),
						this.shipName.trim()));
			}
		}
		
		if (this.voyageId != null)
		{
			c.addCondition(Slave.getAttribute("voyageId"), this.voyageId, TastDbConditions.OP_EQUALS);
		}
		
		if (querySummary != null && this.voyageId != null)
		{
			QuerySummaryItem querySummaryItem = new QuerySummaryItem(TastResource.getText("slaves_query_voyage_id"));
			querySummary.add(querySummaryItem);
			querySummaryItem.setValue(this.voyageId.toString());
		}

		if (this.yearFrom != null)
		{
			c.addCondition(Slave.getAttribute("datearr"), this.yearFrom, TastDbConditions.OP_GREATER_OR_EQUAL);
		}
		
		if (this.yearTo != null)
		{
			c.addCondition(Slave.getAttribute("datearr"), this.yearTo, TastDbConditions.OP_SMALLER_OR_EQUAL);
		}
		
		if (querySummary != null && (this.yearFrom != null || this.yearTo != null))
		{
			QuerySummaryItem querySummaryItem = new QuerySummaryItem(TastResource.getText("slaves_query_ship_name"));
			querySummary.add(querySummaryItem);
			if (this.yearFrom == null)
			{
				querySummaryItem.setValue(
						TastResource.getText("slaves_query_year_to") + " " +
						this.yearTo);
			}
			else if (this.yearTo == null)
			{
				querySummaryItem.setValue(
						TastResource.getText("slaves_query_year_from") + " " +
						this.yearFrom);
			}
			else
			{
				querySummaryItem.setValue(
						this.yearFrom + " - " +
						this.yearTo);
			}
		}

		if (this.ageFrom != null)
		{
			c.addCondition(
					Slave.getAttribute("age"),
					this.ageFrom,
					TastDbConditions.OP_GREATER_OR_EQUAL);
		}
		
		if (this.ageTo != null)
		{
			c.addCondition(
					Slave.getAttribute("age"),
					this.ageTo,
					TastDbConditions.OP_SMALLER_OR_EQUAL);
		}
		
		if (querySummary != null && (this.ageFrom != null || this.ageTo != null))
		{
			QuerySummaryItem querySummaryItem = new QuerySummaryItem(TastResource.getText("slaves_query_age"));
			querySummary.add(querySummaryItem);
			if (this.ageFrom == null)
			{
				querySummaryItem.setValue(
						TastResource.getText("slaves_query_age_to") + " " +
						this.ageTo);
			}
			else if (this.ageTo == null)
			{
				querySummaryItem.setValue(
						TastResource.getText("slaves_query_age_from") + " " +
						this.ageFrom);
			}
			else
			{
				querySummaryItem.setValue(
						this.ageFrom + " - " +
						this.ageTo);
			}
		}

		if (this.heightFrom != null)
		{
			c.addCondition(
					Slave.getAttribute("height"),
					new Double(this.heightFrom.intValue()),
					TastDbConditions.OP_GREATER_OR_EQUAL);
		}
		
		if (this.heightTo != null)
		{
			c.addCondition(
					Slave.getAttribute("height"),
					new Double(this.heightTo.intValue()),
					TastDbConditions.OP_SMALLER_OR_EQUAL);
		}
		
		if (querySummary != null && (this.heightFrom != null || this.heightTo != null))
		{
			QuerySummaryItem querySummaryItem = new QuerySummaryItem(TastResource.getText("slaves_query_height"));
			querySummary.add(querySummaryItem);
			if (this.heightFrom == null)
			{
				querySummaryItem.setValue(
						TastResource.getText("slaves_query_height_to") + " " +
						this.ageTo);
			}
			else if (this.heightTo == null)
			{
				querySummaryItem.setValue(
						TastResource.getText("slaves_query_height_from") + " " +
						this.ageFrom);
			}
			else
			{
				querySummaryItem.setValue(
						this.heightFrom + " - " +
						this.heightTo);
			}
		}
		
		
		if (countries != null && countries.length > 0)
		{
			
			Set countriesSet = StringUtils.toLongSet(countries);
			
			TastDbConditions condCountries = new TastDbConditions(TastDbConditions.OR);

			String allCountriesHQL =
				"from Country c " +
				"where c in (select s.country from Slave s group by s.country) " +
				"order by c.name";

			List allCountries = sess.createQuery(allCountriesHQL).list();
			
			boolean allCountriesSelected = countriesSet.size() == allCountries.size();
			
			QuerySummaryItem querySummaryItem = null;
			StringBuffer countriesBuff = null;
			if (querySummary != null && !allCountriesSelected)
			{
				countriesBuff = new StringBuffer();
				querySummaryItem = new QuerySummaryItem(TastResource.getText("slaves_query_country"));
				querySummary.add(querySummaryItem);
			}

			int i = 0;
			for (Iterator iter = allCountries.iterator(); iter.hasNext();)
			{
				Country country = (Country) iter.next();
				if (countriesSet.contains(country.getId()))
				{
					if (querySummary != null && !allCountriesSelected)
					{
						if (i > 0) countriesBuff.append(", ");
						countriesBuff.append(country.getName());
					}
					condCountries.addCondition(Slave.getAttribute("country"), country, TastDbConditions.OP_EQUALS);
					i++;
				}
			}
			
			if (querySummary != null && !allCountriesSelected)
				querySummaryItem.setValue(countriesBuff.toString());

			c.addCondition(condCountries);
			
		}

		if (embPorts != null && embPorts.length > 0)
		{
			
			Set embPortsSet = StringUtils.toStringSet(embPorts);

			TastDbConditions condPorts = new TastDbConditions(TastDbConditions.OR);
			
			String allEmbPortsHQL =
				"from Port p " +
				"where p in (select s.majbuypt from Slave s group by s.majbuypt) " +
				"order by p.region.area.order, p.region.order, p.order";
			
			List allEmpPorts = sess.createQuery(allEmbPortsHQL).list();

			QuerySummaryItem querySummaryItem = null;
			StringBuffer portsBuff = null;
			int portsBuffItems = 0;

			if (querySummary != null)
			{
				portsBuff = new StringBuffer();
				querySummaryItem = new QuerySummaryItem(TastResource.getText("slaves_query_embarkation"));
				querySummary.add(querySummaryItem);
			}
			
			int portsCount = allEmpPorts.size();
			Port port = (Port) allEmpPorts.get(0);
			Region region = port.getRegion();
			Region prevRegion = region;
			Area area = region.getArea();
			int i = 0;

			while (i < portsCount)
			{
				Area groupArea = area;
				
				while (i < portsCount && groupArea.equals(area))
				{
					Region groupRegion = region;
					
					boolean allPortsInRegionSelected = true;
					boolean somePortsInRegionSelected = false;
					StringBuffer portsInRegionBuff = new StringBuffer(); 
					int portsInRegionBuffItems = 0;
					
					while (i < portsCount && groupRegion.equals(region))
					{
						
						if (embPortsSet.contains(area.getId() + ":" + region.getId() + ":" + port.getId()))
						{
							condPorts.addCondition(Slave.getAttribute("majbuypt"), port, TastDbConditions.OP_EQUALS);
							if (querySummary != null)
							{
								if (portsInRegionBuffItems > 0) portsInRegionBuff.append(", ");
								portsInRegionBuff.append(port.getName());
								portsInRegionBuffItems++;
							}
							somePortsInRegionSelected = true;
						}
						else
						{
							allPortsInRegionSelected = false;
						}

						prevRegion = region;
						if (++i < portsCount)
						{
							port = (Port) allEmpPorts.get(i);
							region = port.getRegion();
							area = region.getArea();
						}

					}
					
					if (querySummary != null && somePortsInRegionSelected)
					{
						if (portsBuffItems > 0) portsBuff.append(", ");
						portsBuff.append(prevRegion.getName());
						portsBuff.append(": ");
						if (allPortsInRegionSelected)
						{
							portsBuff.append(TastResource.getText("slaves_all_port_selected"));
						}
						else
						{
							portsBuff.append(portsInRegionBuff);
						}
						portsBuffItems++;
					}

				}
				
			}
			
			if (querySummary != null)
				querySummaryItem.setValue(portsBuff.toString());

			c.addCondition(condPorts);

		}
		
		TastDbConditions subGender = prepareMultiselectConditions(
				Slave.getAttribute("sexage"),
				new Boolean[] {
					boys,
					men,
					males,
					girls,
					women,
					females},
				new Object[] {
					genderBoy,
					genderMan,
					genderMale,
					genderGirl,
					genderWoman,
					genderFemale},
				querySummary,
				TastResource.getText("slaves_query_sexage"),
				new String[] {
					TastResource.getText("slaves_checkbox_boys"),
					TastResource.getText("slaves_checkbox_man"),
					TastResource.getText("slaves_checkbox_males"),
					TastResource.getText("slaves_checkbox_girls"),
					TastResource.getText("slaves_checkbox_woman"),
					TastResource.getText("slaves_checkbox_females")});
		
		if (subGender != null)
			c.addCondition(subGender);
		
		TastDbConditions subDisembarkation = prepareMultiselectConditions(
				Slave.getAttribute("majselpt"),
				new Boolean[] {
					disembHavana,
					disembSierraLeone},
				new Object[] {
					portHavana,
					portSierraLeone},
				querySummary,
				TastResource.getText("slaves_query_captured"),
				new String[] {
					TastResource.getText("slaves_captured_havana"),
					TastResource.getText("slaves_captured_sierra_leone")});

		if (subDisembarkation != null)
			c.addCondition(subDisembarkation);
		
		return c;
		
	}
	
	public boolean equals(Object obj)
	{
		
		if (!(obj instanceof SlavesQuery))
			return false;
		
		SlavesQuery that = (SlavesQuery) obj;
		
		return
			EqualsUtil.areEqual(this.ageFrom, that.ageFrom) &&
			EqualsUtil.areEqual(this.ageTo, that.ageTo) &&
			EqualsUtil.areEqual(this.heightFrom, that.heightFrom) &&
			EqualsUtil.areEqual(this.heightTo, that.heightTo) &&
			EqualsUtil.areEqual(this.yearFrom, that.yearFrom) &&
			EqualsUtil.areEqual(this.yearTo, that.yearTo) &&
			EqualsUtil.areEqual(this.slaveName, that.slaveName) &&
			EqualsUtil.areEqual(this.shipName, that.shipName) &&
			EqualsUtil.areEqual(this.boys, that.boys) &&
			EqualsUtil.areEqual(this.men, that.men) &&
			EqualsUtil.areEqual(this.males, that.males) &&
			EqualsUtil.areEqual(this.girls, that.girls) &&
			EqualsUtil.areEqual(this.women, that.women) &&
			EqualsUtil.areEqual(this.females, that.females) &&
			EqualsUtil.areEqual(this.countries, that.countries) &&
			EqualsUtil.areEqual(this.embPorts, that.embPorts) &&
			EqualsUtil.areEqual(this.disembSierraLeone, that.disembSierraLeone) &&
			EqualsUtil.areEqual(this.disembHavana, that.disembHavana) &&
			EqualsUtil.areEqual(this.voyageId, that.voyageId);
		
	}
	
	protected Object clone() throws CloneNotSupportedException
	{
		SlavesQuery newQuery = (SlavesQuery) super.clone();
		newQuery.countries = (String[]) this.countries.clone();
		newQuery.embPorts = (String[]) this.embPorts.clone();
		return newQuery;
	}
	
	public Integer getAgeFrom()
	{
		return ageFrom;
	}

	public void setAgeFrom(Integer ageFrom)
	{
		this.ageFrom = ageFrom;
	}

	public Integer getAgeTo()
	{
		return ageTo;
	}

	public void setAgeTo(Integer ageTo)
	{
		this.ageTo = ageTo;
	}

	public Boolean getBoys()
	{
		return boys;
	}

	public void setBoys(Boolean boys)
	{
		if (boys != null) this.boys = boys;
	}

	public String[] getCountries()
	{
		return countries;
	}

	public void setCountries(String[] countries)
	{
		this.countries = countries;
	}

	public Boolean getDisembHavana()
	{
		return disembHavana;
	}

	public void setDisembHavana(Boolean disembHavana)
	{
		this.disembHavana = disembHavana;
	}

	public Boolean getDisembSierraLeone()
	{
		return disembSierraLeone;
	}

	public void setDisembSierraLeone(Boolean disembSierraLeone)
	{
		this.disembSierraLeone = disembSierraLeone;
	}

	public String[] getEmbPorts()
	{
		return embPorts;
	}

	public void setEmbPorts(String[] embPorts)
	{
		this.embPorts = embPorts;
	}

	public Boolean getFemales()
	{
		return females;
	}

	public void setFemales(Boolean female)
	{
		if (females != null) this.females = female;
	}

	public Boolean getGirls()
	{
		return girls;
	}

	public void setGirls(Boolean girls)
	{
		if (girls != null) this.girls = girls;
	}

	public Integer getHeightFrom()
	{
		return heightFrom;
	}

	public void setHeightFrom(Integer heightFrom)
	{
		this.heightFrom = heightFrom;
	}

	public Integer getHeightTo()
	{
		return heightTo;
	}

	public void setHeightTo(Integer heightTo)
	{
		this.heightTo = heightTo;
	}

	public Boolean getMales()
	{
		return males;
	}

	public void setMales(Boolean males)
	{
		if (males != null) this.males = males;
	}

	public Boolean getMen()
	{
		return men;
	}

	public void setMen(Boolean men)
	{
		if (men != null) this.men = men;
	}

	public String getShipName()
	{
		return shipName;
	}

	public void setShipName(String shipName)
	{
		this.shipName = shipName;
	}

	public String getSlaveName()
	{
		return slaveName;
	}

	public void setSlaveName(String slaveName)
	{
		this.slaveName = slaveName;
	}

	public Boolean getWomen()
	{
		return women;
	}

	public void setWomen(Boolean women)
	{
		if (women != null) this.women = women;
	}

	public Integer getYearFrom()
	{
		return yearFrom;
	}

	public void setYearFrom(Integer yearFrom)
	{
		this.yearFrom = yearFrom;
	}

	public Integer getYearTo()
	{
		return yearTo;
	}

	public void setYearTo(Integer yearTo)
	{
		this.yearTo = yearTo;
	}

	public Integer getVoyageId()
	{
		return voyageId;
	}

	public void setVoyageId(Integer voyageId)
	{
		this.voyageId = voyageId;
	}
	
}