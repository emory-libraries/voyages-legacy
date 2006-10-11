package edu.emory.library.tast.ui;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class CheckboxListTag extends UIComponentTag
{
	
	private String items;
	private String selectedValues;

	public String getComponentType()
	{
		return "CheckboxList";
	}

	public String getRendererType()
	{
		return null;
	}

	protected void setProperties(UIComponent component)
	{
		
		Application app = FacesContext.getCurrentInstance().getApplication();
		CheckboxListComponent eventLine = (CheckboxListComponent) component;
		
		if (items != null && isValueReference(items))
		{
			ValueBinding vb = app.createValueBinding(items);
			eventLine.setValueBinding("items", vb);
		}
		
		if (selectedValues != null && isValueReference(selectedValues))
		{
			ValueBinding vb = app.createValueBinding(selectedValues);
			eventLine.setValueBinding("selectedValues", vb);
		}

	}

	public String getItems()
	{
		return items;
	}

	public void setItems(String items)
	{
		this.items = items;
	}

	public String getSelectedValues()
	{
		return selectedValues;
	}

	public void setSelectedValues(String selectedValues)
	{
		this.selectedValues = selectedValues;
	}

}
