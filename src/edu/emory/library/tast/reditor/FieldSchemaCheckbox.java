package edu.emory.library.tast.reditor;

import java.io.IOException;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class FieldSchemaCheckbox extends FieldSchema
{

	public String getType()
	{
		return FieldValueCheckbox.TYPE;
	}

	public FieldSchemaCheckbox(String name, String description)
	{
		super(name, description);
	}

	public void encode(EditorComponent editor, UIForm form, FacesContext context, Schema schema, FieldValue value) throws IOException
	{
		
		// type check
		if (!(value instanceof FieldValueCheckbox))
			throw new RuntimeException("FieldSchemaCheckbox expected FieldValueCheckbox");
		
		// cast
		FieldValueCheckbox valueCheckbox = (FieldValueCheckbox) value;

		// get writer
		ResponseWriter writer = context.getResponseWriter();

		// input name
		String htmlFieldName = FieldValueText.getHtmlFieldName(editor, context, getName());
		
		// main div container
		writer.startElement("input", editor);
		writer.writeAttribute("name", htmlFieldName, null);
		if (valueCheckbox.isChecked()) writer.writeAttribute("checked", "checked", null);
		writer.endElement("input");

	}

}