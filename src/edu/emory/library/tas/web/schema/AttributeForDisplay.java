package edu.emory.library.tas.web.schema;

import edu.emory.library.tas.attrGroups.Attribute;

public class AttributeForDisplay extends AbstractAttributeForDisplay
{
	
	private String compoundAttributesHTML;
	private String proxiedGroupsHTML;
	
	public AttributeForDisplay(Attribute attribute)
	{
		this.setId(attribute.getId());
		this.setName(attribute.getName());
		this.setUserLabel(attribute.getUserLabel());
		this.setDescription(attribute.getDescription());
		this.setTypeDisplayName(attribute.getTypeDisplayName());
	}

	public String getCompoundAttributesHTML()
	{
		return compoundAttributesHTML;
	}
	
	public void setCompoundAttributesHTML(String compoundAttributesHTML)
	{
		this.compoundAttributesHTML = compoundAttributesHTML;
	}

	public String getProxiedGroupsHTML()
	{
		return proxiedGroupsHTML;
	}

	public void setProxiedGroupsHTML(String proxiedGroupsHTML)
	{
		this.proxiedGroupsHTML = proxiedGroupsHTML;
	}

}