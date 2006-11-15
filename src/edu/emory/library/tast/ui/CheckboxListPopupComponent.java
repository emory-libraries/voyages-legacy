package edu.emory.library.tast.ui;

import java.io.IOException;
import java.util.Set;

import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import edu.emory.library.tast.util.JsfUtils;
import edu.emory.library.tast.util.StringUtils;

public class CheckboxListPopupComponent extends CheckboxListComponent
{
	
	private void encodePopup(FacesContext context, ResponseWriter writer, String mainId, String popupId, String inputName, SelectItemWithImage mainItem, SelectItem[] items, Set selectedValuesLookup) throws IOException
	{

		// show
		String onMouseOver =
			"CheckboxListPopupGlobals.popupShow(" +
			"'" + mainId + "', " +
			"'" + popupId + "', " +
			"'" + mainItem.getImageUrl() + "')";

		// hide
		String onMouseOut =
			"CheckboxListPopupGlobals.popupHide(" +
			"'" + mainId + "', " +
			"'" + popupId + "')";
		
		// container
		writer.startElement("div", this);
		writer.writeAttribute("id", popupId, null);
		writer.writeAttribute("class", "checkbox-list-popup-cont", null);
		writer.writeAttribute("style", "display: none; position: absolute;", null);

		// inner container
		writer.startElement("div", this);
		writer.writeAttribute("class", "checkbox-list-popup", null);
		writer.writeAttribute("onmouseover", onMouseOver, null);
		writer.writeAttribute("onmouseout", onMouseOut, null);

		// main table
		writer.startElement("table", this);
		writer.writeAttribute("class", "checkbox-list-table-1", null);
		writer.writeAttribute("border", "0", null);
		writer.writeAttribute("cellspacing", "0", null);
		writer.writeAttribute("cellpadding", "0", null);
		
		// items
		for (int i = 0; i < items.length; i++)
		{

			SelectItemWithImage item = (SelectItemWithImage) items[i];
			boolean checked = selectedValuesLookup.contains(item.getValue());
			String inputId = mainId + "_checkbox_" + item.getValue();
			
			// show
			onMouseOver =
				"CheckboxListPopupGlobals.popupShow(" +
				"'" + mainId + "', " +
				"'" + popupId + "', " +
				"'" + item.getImageUrl() + "')";

			// hide
			onMouseOut =
				"CheckboxListPopupGlobals.popupHide(" +
				"'" + mainId + "', " +
				"'" + popupId + "')";
			
			// item TR begin
			writer.startElement("tr", this);
			
			// checkbox
			writer.startElement("td", this);
			writer.writeAttribute("class", "checkbox-list-checkbox-1", null);
			if (item.isSelectable())
			{
				writer.startElement("input", this);
				writer.writeAttribute("type", "checkbox", null);
				writer.writeAttribute("name", inputName, null);
				writer.writeAttribute("id", inputId, null);
				writer.writeAttribute("value", item.getValue(), null);
				if (checked) writer.writeAttribute("checked", "checked", null);
				writer.endElement("input");
			}
			writer.endElement("td");

			// label
			writer.startElement("td", this);
			writer.writeAttribute("class", "checkbox-list-label-1", null);
			writer.startElement("label", this);
			writer.writeAttribute("for", inputId, null);
			writer.write(item.getText());
			writer.endElement("label");
			writer.endElement("td");

			// item TR end
			writer.endElement("tr");

		}
		
		// end mail table
		writer.endElement("table");
		
		// containers
		writer.endElement("div");
		writer.endElement("div");

	}
	
	private void encodeMainItems(FacesContext context, ResponseWriter writer, String mainId, String inputName, SelectItemWithImage[] items, Set selectedValuesLookup) throws IOException
	{
		
		// main table
		writer.startElement("table", this);
		writer.writeAttribute("class", "checkbox-list-table", null);
		writer.writeAttribute("border", "0", null);
		writer.writeAttribute("cellspacing", "0", null);
		writer.writeAttribute("cellpadding", "0", null);
		
		// does any item has subitem?
		boolean hasSubitems = false;
		for (int i = 0; i < items.length; i++)
		{
			if (items[i].hasSubItems())
			{
				hasSubitems = true;
				break;
			}
		}
		
		// items
		for (int i = 0; i < items.length; i++)
		{

			SelectItemWithImage item = items[i];
			boolean checked = selectedValuesLookup.contains(item.getValue());
			String inputId = mainId + "_checkbox_" + item.getValue();
			String popupId = mainId + "_popup_" + item.getValue();
			
			String onMouseOver =
				"CheckboxListPopupGlobals.popupShow(" +
				"'" + mainId + "', " +
				"'" + popupId + "', " +
				"'" + item.getImageUrl() + "')";
			
			String onMouseOut =
				"CheckboxListPopupGlobals.popupHide(" +
				"'" + mainId + "', " +
				"'" + popupId + "')";

			// item TR begin
			writer.startElement("tr", this);
			writer.writeAttribute("onmouseover", onMouseOver, null);
			writer.writeAttribute("onmouseout", onMouseOut, null);
			
			// checkbox
			writer.startElement("td", this);
			writer.writeAttribute("class", "checkbox-list-checkbox-0", null);
			if (item.hasSubItems())
			{
				encodePopup(context, writer, mainId, popupId, inputName, item, item.getSubItems(), selectedValuesLookup);
			}
			if (item.isSelectable())
			{
				writer.startElement("input", this);
				writer.writeAttribute("type", "checkbox", null);
				writer.writeAttribute("name", inputName, null);
				writer.writeAttribute("id", inputId, null);
				writer.writeAttribute("value", item.getValue(), null);
				if (checked) writer.writeAttribute("checked", "checked", null);
				writer.endElement("input");
			}
			writer.endElement("td");

			// label
			writer.startElement("td", this);
			writer.writeAttribute("class", "checkbox-list-label-0", null);
			writer.startElement("label", this);
			writer.writeAttribute("for", inputId, null);
			writer.write(item.getText());
			writer.endElement("label");
			writer.endElement("td");

			// item TR end
			writer.endElement("tr");

		}
		
		// end mail table
		writer.endElement("table");

	}
	
	public void encodeBegin(FacesContext context) throws IOException
	{

		// standard stuff
		ResponseWriter writer = context.getResponseWriter();
		UIForm form = JsfUtils.getForm(this, context);
		
		// get data from beans
		SelectItemWithImage[] items = (SelectItemWithImage[]) getItems();
		Set selectedValuesLookup = StringUtils.toStringSet(getSelectedValues());
		
		// main id for JS
		String mainId = getClientId(context); 
		
		// JS registration
		StringBuffer regJS = new StringBuffer();
		regJS.append("CheckboxListPopupGlobals.registerCheckboxList(new CheckboxListPopup(");
		regJS.append("'").append(mainId).append("', ");
		regJS.append("'").append(form.getClientId(context)).append("'));");
		
		// render registration JS
		JsfUtils.encodeJavaScriptBlock(this, writer, regJS);

		// encode recursivelly
		if (items == null) return;
		encodeMainItems(context, writer,
				mainId,
				getHtmlNameForSelectemValues(context),
				items,
				selectedValuesLookup);
		
	}

	public void encodeChildren(FacesContext context) throws IOException
	{
	}
	
	public void encodeEnd(FacesContext context) throws IOException
	{
	}

}
