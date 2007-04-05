package edu.emory.library.tast.common.grideditor;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class GridEditorTag extends UIComponentTag
{
	
	private String rows;
	private String columns;
	private String values;

	public String getComponentType()
	{
		return "GridEditor";
	}

	public String getRendererType()
	{
		return null;
	}
	
	protected void setProperties(UIComponent component)
	{

		GridEditorComponent gridEditor = (GridEditorComponent) component;
		Application app = FacesContext.getCurrentInstance().getApplication();
		
		if (rows != null && isValueReference(rows))
		{
			ValueBinding vb = app.createValueBinding(rows);
			gridEditor.setValueBinding("rows", vb);
		}
		
		if (columns != null && isValueReference(columns))
		{
			ValueBinding vb = app.createValueBinding(columns);
			gridEditor.setValueBinding("columns", vb);
		}

		if (values != null && isValueReference(values))
		{
			ValueBinding vb = app.createValueBinding(values);
			gridEditor.setValueBinding("values", vb);
		}
	
	}

	public String getColumns()
	{
		return columns;
	}

	public void setColumns(String columns)
	{
		this.columns = columns;
	}

	public String getRows()
	{
		return rows;
	}

	public void setRows(String rows)
	{
		this.rows = rows;
	}

	public String getValues()
	{
		return values;
	}

	public void setValues(String values)
	{
		this.values = values;
	}

}