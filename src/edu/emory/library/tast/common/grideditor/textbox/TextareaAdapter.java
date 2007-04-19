package edu.emory.library.tast.common.grideditor.textbox;

import java.io.IOException;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import edu.emory.library.tast.common.grideditor.Adapter;
import edu.emory.library.tast.common.grideditor.Column;
import edu.emory.library.tast.common.grideditor.FieldType;
import edu.emory.library.tast.common.grideditor.GridEditorComponent;
import edu.emory.library.tast.common.grideditor.Row;
import edu.emory.library.tast.common.grideditor.Value;
import edu.emory.library.tast.util.JsfUtils;

public class TextareaAdapter extends Adapter
{

	public static final String TYPE = "textarea";
	
	protected String getSubmittedValue(FacesContext context, String inputPrefix)
	{
		return (String) context.getExternalContext().getRequestParameterMap().get(inputPrefix);
	}

	public Value decode(FacesContext context, String inputPrefix, GridEditorComponent gridEditor)
	{
		String submittedValue = getSubmittedValue(context, inputPrefix);
		if (submittedValue == null) return null;
		return new TextareaValue(submittedValue.split("\n"));
	}
	
	private void encodeEditMode(GridEditorComponent gridEditor, String inputPrefix, TextareaValue textboxValue, ResponseWriter writer, TextareaFieldType textareaFieldType) throws IOException
	{

		writer.startElement("textarea", gridEditor);
		writer.writeAttribute("type", "textarea", null);
		writer.writeAttribute("name", inputPrefix, null);
		if (textareaFieldType.getRows() != TextareaFieldType.ROWS_DEFAULT) writer.writeAttribute("rows", String.valueOf(textareaFieldType.getRows()), null);
		writer.write(textboxValue.getText());
		writer.endElement("input");

	}

	private void encodeReadOnlyMode(GridEditorComponent gridEditor, String inputPrefix, TextareaValue textboxValue, ResponseWriter writer) throws IOException
	{

		writer.write(textboxValue.getText().replaceAll("\n", "<br>"));
		
		JsfUtils.encodeHiddenInput(
				gridEditor, writer,
				inputPrefix,
				textboxValue.getText());

	}

	public void encode(FacesContext context, GridEditorComponent gridEditor, String clientGridId, UIForm form, Row row, Column column, FieldType fieldType, String inputPrefix, Value value, boolean readOnly) throws IOException
	{
		
		TextareaValue textboxValue = (TextareaValue) value;
		TextareaFieldType textareaFieldType = (TextareaFieldType) fieldType;
		ResponseWriter writer = context.getResponseWriter();
		
		if (readOnly)
		{
			encodeEditMode(gridEditor, inputPrefix, textboxValue, writer, textareaFieldType);
		}
		else
		{
			encodeReadOnlyMode(gridEditor, inputPrefix, textboxValue, writer);
		}
		
	}

}