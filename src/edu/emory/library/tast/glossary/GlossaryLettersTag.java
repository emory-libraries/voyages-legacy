package edu.emory.library.tast.glossary;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class GlossaryLettersTag extends UIComponentTag
{
	
	private String letters;

	public String getComponentType()
	{
		return "GlossaryLetters";
	}

	public String getRendererType()
	{
		return null;
	}

	protected void setProperties(UIComponent component)
	{
		
		GlossaryLettersComponentV1 glossaryLetters = (GlossaryLettersComponentV1) component;
		Application app = FacesContext.getCurrentInstance().getApplication();
		
		if (letters != null && isValueReference(letters))
		{
			ValueBinding vb = app.createValueBinding(letters);
			glossaryLetters.setValueBinding("letters", vb);
		}
		
	}

	public String getLetters()
	{
		return letters;
	}

	public void setLetters(String letters)
	{
		this.letters = letters;
	}

}

