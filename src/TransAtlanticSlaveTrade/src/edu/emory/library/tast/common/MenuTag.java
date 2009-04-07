package edu.emory.library.tast.common;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class MenuTag extends UIComponentTag
{
	
	private String onMenuSelected;
	private String items;
	private String customSubmitFunction;

	protected void setProperties(UIComponent component)
	{
		
		Application app = FacesContext.getCurrentInstance().getApplication();
		MenuComponent menu = (MenuComponent) component;
		
		if (items != null && isValueReference(items))
		{
			ValueBinding vb = app.createValueBinding(items);
			component.setValueBinding("items", vb);
		}
		
		if (onMenuSelected != null && isValueReference(onMenuSelected))
		{
			MethodBinding mb = app.createMethodBinding(onMenuSelected, new Class[] {MenuItemSelectedEvent.class});
			menu.setOnMenuSelected(mb);
		}
		
		menu.setCustomSubmitFunction(customSubmitFunction);

	}

	public String getComponentType()
	{
		return null;
	}

	public String getRendererType()
	{
		return null;
	}
	
	public String getItems()
	{
		return items;
	}

	public void setItems(String items)
	{
		this.items = items;
	}

	public String getOnMenuSelected()
	{
		return onMenuSelected;
	}

	public void setOnMenuSelected(String onMenuSelected)
	{
		this.onMenuSelected = onMenuSelected;
	}

	public String getCustomSubmitFunction()
	{
		return customSubmitFunction;
	}

	public void setCustomSubmitFunction(String customSubmitFunction)
	{
		this.customSubmitFunction = customSubmitFunction;
	}

}
