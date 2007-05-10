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
	
	private String getInputName(String inputPrefix)
	{
		return inputPrefix;
	}

	protected String getSubmittedValue(FacesContext context, String inputPrefix)
	{
		return (String) context.getExternalContext().getRequestParameterMap().get(getInputName(inputPrefix));
	}

	public Value decode(FacesContext context, String inputPrefix, GridEditorComponent gridEditor)
	{
		String submittedValue = getSubmittedValue(context, inputPrefix);
		if (submittedValue == null) return null;
		return new TextareaValue(submittedValue.split("\n"));
	}
	
	public void createValueJavaScript(FacesContext context, StringBuffer regJS, GridEditorComponent gridEditor, String inputPrefix, Row row, Column column, Value value, boolean readOnly) throws IOException
	{
		regJS.append("new GridEditorTextarea('" + getInputName(inputPrefix) + "')");
	}

	private void encodeEditMode(GridEditorComponent gridEditor, String inputPrefix, TextareaValue textboxValue, ResponseWriter writer, TextareaFieldType textareaFieldType) throws IOException
	{

		writer.startElement("textarea", gridEditor);
		writer.writeAttribute("type", "textarea", null);
		writer.writeAttribute("name", getInputName(inputPrefix), null);
		JsfUtils.writeParamIfNotDefault(writer, "rows", textareaFieldType.getRows(), TextareaFieldType.ROWS_DEFAULT);
		JsfUtils.writeParamIfNotNull(writer, "class", textareaFieldType.getCssClass());
		JsfUtils.writeParamIfNotNull(writer, "style", textareaFieldType.getCssStyle());
		writer.write(textboxValue.getText());
		writer.endElement("textarea");

	}

	private void encodeReadOnlyMode(GridEditorComponent gridEditor, String inputPrefix, TextareaValue textboxValue, ResponseWriter writer) throws IOException
	{

		JsfUtils.encodeHiddenInput(
				gridEditor, writer,
				getInputName(inputPrefix),
				textboxValue.getText());

		writer.write(textboxValue.getText().replaceAll("\n", "<br>"));

	}

	public void encode(FacesContext context, GridEditorComponent gridEditor, String clientGridId, UIForm form, Row row, Column column, FieldType fieldType, String inputPrefix, Value value, boolean readOnly) throws IOException
	{
		
		TextareaValue textboxValue = (TextareaValue) value;
		TextareaFieldType textareaFieldType = (TextareaFieldType) fieldType;
		ResponseWriter writer = context.getResponseWriter();
		
		if (readOnly)
		{
			encodeReadOnlyMode(gridEditor, inputPrefix, textboxValue, writer);
		}
		else
		{
			encodeEditMode(gridEditor, inputPrefix, textboxValue, writer, textareaFieldType);
		}
		
	}

}