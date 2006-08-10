package edu.emory.library.tast.ui;

import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentTag;

/**
 * JSP tag for {@link edu.emory.library.tast.ui.SectionComponent}.
 * 
 * @author Jan Zich
 * 
 */
public class SectionTag extends UIComponentTag
{
	
	private String title;
	private String sectionId;

	public String getComponentType()
	{
		return "Section";
	}

	public String getRendererType()
	{
		return null;
	}
	
	protected void setProperties(UIComponent component)
	{
		
		SectionComponent section = (SectionComponent) component;

		section.setTitle(title);
		section.setSectionId(sectionId);
		
	}

	public String getSectionId()
	{
		return sectionId;
	}

	public void setSectionId(String sectionId)
	{
		this.sectionId = sectionId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

}