package edu.emory.library.tast.common.grideditor.textbox;

import java.io.IOException;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import edu.emory.library.tast.common.grideditor.Adapter;
import edu.emory.library.tast.common.grideditor.GridEditorComponent;
import edu.emory.library.tast.common.grideditor.Value;

public class TextboxAdapter extends Adapter
{
	
	public static final String TYPE = "textbox"; 

	public Value decode(FacesContext context, String inputPrefix, GridEditorComponent gridEditor)
	{
		String text = (String) context.getExternalContext().getRequestParameterMap().get(inputPrefix);
		return new TextboxValue(text);
	}
	
	public void encode(FacesContext context, GridEditorComponent gridEditor, UIForm form, String inputPrefix, Value value, boolean readOnly) throws IOException
	{
		
		TextboxValue textboxValue = (TextboxValue) value;
		ResponseWriter writer = context.getResponseWriter();
		
		writer.startElement("input", gridEditor);
		writer.writeAttribute("type", "text", null);
		writer.writeAttribute("name", inputPrefix, null);
		writer.writeAttribute("value", textboxValue.getText(), null);
		writer.endElement("input");
		
	}

}