package edu.emory.library.tast.glossary;

import java.io.IOException;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import edu.emory.library.tast.util.JsfUtils;

public class GlossaryLettersComponent extends UIComponentBase
{
	
	public String getFamily()
	{
		return null;
	}
	
	public void encodeBegin(FacesContext context) throws IOException
	{
		
		// standard stuff
		ResponseWriter writer = context.getResponseWriter();
		
		// get data from bean
		GlossaryLetter[] letters = getLetters();
		
		// main table
		writer.startElement("table", this);
		writer.writeAttribute("class", "glossary-selector-letters", null);
		writer.writeAttribute("border", "0", null);
		writer.writeAttribute("cellspacing", "0", null);
		writer.writeAttribute("cellpadding", "0", null);
		writer.startElement("tr", this);
		
		// render
		for (int i = 0; i < letters.length; i++)
		{
			GlossaryLetter letter = letters[i];
			
			// cell itself
			writer.startElement("td", this);
			writer.writeAttribute("class", "glossary-selector-letter", null);
			if (letter.isMatched())
			{
				writer.startElement("a", this);
				writer.writeAttribute("class", "glossary-selector-letter-normal", null);
				writer.writeAttribute("href", "#" + letter.getLetter(), null);
			}
			else
			{
				writer.startElement("div", this);
				writer.writeAttribute("class", "glossary-selector-letter-dimmed", null);
			}
			writer.write(letter.getLetter());
			if (letter.isMatched())
			{
				writer.endElement("a");
			}
			else
			{
				writer.endElement("div");
			}
			writer.endElement("td");
			
		}
		
		// main table
		writer.endElement("tr");
		writer.endElement("table");
		
	}
	
	public GlossaryLetter[] getLetters()
	{
		return (GlossaryLetter[]) JsfUtils.getCompPropObject(this, getFacesContext(),
				"letters", false, null);
	}

}
