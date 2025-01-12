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
package edu.emory.library.tast.reditor;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.faces.component.UIComponentBase;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;

import edu.emory.library.tast.util.JsfUtils;

public class EditorComponent extends UIComponentBase
{
	
	private boolean schemaSet;
	private Schema schema;
	
	private boolean valuesSet;
	private Values values;

	private FieldSchemaState[] schemaFromState;
	
	public String getFamily()
	{
		return null;
	}
	
	public Object saveState(FacesContext context)
	{
		Object[] values = new Object[2];
		values[0] = super.saveState(context);
		if (schema != null) values[1] = saveAttachedState(context, schema.getSerializableState());
		return values;
	}
	
	public void restoreState(FacesContext context, Object state)
	{
		Object[] values = (Object[]) state;
		super.restoreState(context, values[0]);
		schemaFromState = (FieldSchemaState[]) restoreAttachedState(context, values[1]);
	}
	
	public void decode(FacesContext context)
	{
		values = new Values();
		for (int i = 0; i < schemaFromState.length; i++)
		{
			FieldValue fieldValue = FieldValue.createFieldValue(schemaFromState[i]);
			fieldValue.decode(this, context);
			values.addValue(fieldValue);
		}
	}
	
	public void processUpdates(FacesContext context)
	{
		if (values != null)
		{
			ValueBinding vb = getValueBinding("values");
			if (vb != null) vb.setValue(context, values);
		}
	}
	
	public void encodeBegin(FacesContext context) throws IOException
	{
		
		// standard stuff
		ResponseWriter writer = context.getResponseWriter();
		UIForm form = JsfUtils.getForm(this, context);
		
		// get schema and values from a bean
		schema = getSchema();
		values = getValues();
		if (values == null) values = new Values(); 
		
		// component id
		String mainId = getClientId(context);
		
		// JS registration
		StringBuffer regJS = new StringBuffer();
		regJS.append("RecordEditorGlobals.registerEditor(new RecordEditor(");
		regJS.append("'").append(mainId).append("', ");
		regJS.append("'").append(form.getClientId(context)).append("', ");
		
		// lists
		regJS.append("[");
		int j = 0;
		for (Iterator iter = schema.getLists().entrySet().iterator(); iter.hasNext();)
		{
			Entry listEntry = (Entry) iter.next();;
			String listName = (String) listEntry.getKey();
			ListItem[] items = (ListItem[]) listEntry.getValue();

			if (j > 0) regJS.append(", ");
			regJS.append("new RecordEditorList(");
			regJS.append("'").append(listName).append("', ");
			regJS.append("[");
			for (int i = 0; i < items.length; i++)
			{
				ListItem item = items[i];
				if (i > 0) regJS.append(", ");
				regJS.append("new ListItem(");
				if (item.getValue() == null)
				{
					regJS.append("null, ");
				}
				else
				{
					regJS.append("'").append(item.getValue()).append("', ");
				}
				if (item.getParentValue() == null)
				{
					regJS.append("null, ");
				}
				else
				{
					regJS.append("'").append(item.getParentValue()).append("', ");
				}
				regJS.append("'").append(JsfUtils.escapeStringForJS(item.getText())).append("'");
				regJS.append(")");
			}
			regJS.append("]");
			regJS.append(")");
			
			j++;

		}
		regJS.append("], ");

		// fields
		regJS.append("[");
		j = 0;
		for (Iterator iter = schema.getFields().iterator(); iter.hasNext();)
		{
			FieldSchema fieldSchema = (FieldSchema) iter.next();
			if (j > 0) regJS.append(", ");
			fieldSchema.createRegJS(this, mainId, form, context, schema, regJS);
			j++;
		}
		regJS.append("]");

		// end js registration
		regJS.append("));");

		// render JS
		JsfUtils.encodeJavaScriptBlock(this, writer, regJS);

		// start main table
		writer.startElement("table", this);
		writer.writeAttribute("border", "0", null);
		writer.writeAttribute("cellspacing", "0", null);
		writer.writeAttribute("cellpadding", "0", null);
		writer.writeAttribute("class", "record-editor", null);
		
		// for all fields in schema
		for (Iterator iter = schema.getFields().iterator(); iter.hasNext();)
		{

			// schema and value
			FieldSchema fieldSchema = (FieldSchema) iter.next();
			FieldValue fieldValue = values.getValueFor(fieldSchema.getName());
			if (fieldValue == null) fieldValue = FieldValue.createFieldValue(fieldSchema);

			writer.startElement("tr", this);

			// label
			writer.startElement("td", this);
			writer.writeAttribute("class", "record-editor-label", null);
			writer.write(fieldSchema.getLabel());
			writer.write(" (");
			writer.write(fieldSchema.getName());
			writer.write(")");
			writer.endElement("td");
			
			// value
			writer.startElement("td", this);
			writer.writeAttribute("class", "record-editor-field", null);
			fieldSchema.encode(this, mainId, form, context, schema, fieldValue);
			writer.endElement("td");

			writer.endElement("tr");

		}
		
		writer.endElement("table");

	}

	public void encodeChildren(FacesContext context) throws IOException
	{
	}

	public void encodeEnd(FacesContext context) throws IOException
	{
	}

	private Schema getSchema()
	{
		return (Schema) JsfUtils.getCompPropObject(this, getFacesContext(),
				"schema", schemaSet, schema);
	}

	public void setSchema(Schema schema)
	{
		schemaSet = true;
		this.schema = schema;
	}

	public Values getValues()
	{
		return (Values) JsfUtils.getCompPropObject(this, getFacesContext(),
				"values", valuesSet, values);
	}

	public void setValues(Values values)
	{
		valuesSet = true;
		this.values = values;
	}

}