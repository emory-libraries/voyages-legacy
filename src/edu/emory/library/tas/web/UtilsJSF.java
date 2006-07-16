package edu.emory.library.tas.web;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

public class UtilsJSF
{
	
	public static UIForm getForm(UIComponent component, FacesContext context)
	{
        UIComponent parent = component.getParent();
        while (parent != null && !"javax.faces.Form".equals(parent.getFamily()))
        {
            parent = parent.getParent();
        }
        return (UIForm) parent;
	}
	
	public static void encodeHiddenInput(UIComponent component, ResponseWriter writer, String name) throws IOException
	{
		encodeHiddenInput(component, writer, name, null);
	}

	public static void encodeHiddenInput(UIComponent component, ResponseWriter writer, String name, String value) throws IOException
	{
		writer.startElement("input", component);
		writer.writeAttribute("type", "hidden", null);
		writer.writeAttribute("name", name, null);
		if (value != null) writer.writeAttribute("value", value, null);
		writer.endElement("input");
	}
	
	public static void encodeJavaScriptStart(UIComponent component, ResponseWriter writer) throws IOException
	{
		writer.startElement("script", component);
		writer.writeAttribute("type", "text/javascript", null);
		writer.writeAttribute("language", "javascript", null);
		//writer.write("\n");
	}

	public static void encodeJavaScriptEnd(UIComponent component, ResponseWriter writer) throws IOException
	{
		//writer.write("\n");
		writer.endElement("script");
	}
	
	public static String generateSubmitJS(FacesContext context, UIForm form, String elementName, String value)
	{
		
		StringBuffer js = new StringBuffer();

		if (elementName != null && value != null)
		{
			appendFormElementValJS(js, context, form, elementName);
			js.append(" = '").append(value).append("';");
		}

		if (js.length() > 0) js.append(" ");
		appendFormRefJS(js, context, form);
		js.append(".submit();");
		
		if (js.length() > 0) js.append(" ");
		js.append("return false;");
		
		return js.toString();
	
	}
	
	public static StringBuffer appendFormRefJS(StringBuffer js, FacesContext context, UIForm form)
	{
		js.append("document.");
		js.append("forms['").append(form.getClientId(context)).append("']");
		return js;
	}

	public static StringBuffer appendFormElementRefJS(StringBuffer js, FacesContext context, UIForm form, String elementName)
	{
		appendFormRefJS(js, context, form);
		js.append(".elements['").append(elementName).append("']");
		return js;
	}

	public static StringBuffer appendFormElementValJS(StringBuffer js, FacesContext context, UIForm form, String elementName)
	{
		appendFormElementRefJS(js, context, form, elementName);
		js.append(".value");
		return js;
	}
	
	public static StringBuffer appendElementRefJS(StringBuffer js, String elementId)
	{
		js.append("document.");
		js.append("getElementById('").append(elementId).append("')");
		return js;
	}
	
	public static StringBuffer appendSetElementStyle(StringBuffer js, String elementId, String style, String value)
	{
		appendElementRefJS(js, elementId);
		js.append(".style.").append(style);
		js.append(" = '").append(value).append("';");
		return js;
	}

	public static StringBuffer appendHideElement(StringBuffer js, String elementId)
	{
		return appendSetElementStyle(js, elementId, "display", "none");
	}

	public static StringBuffer appendShowElement(StringBuffer js, String elementId)
	{
		return appendSetElementStyle(js, elementId, "display", "");
	}

	public static StringBuffer appendToggleElement(StringBuffer js, String elementId)
	{
		js.append("{");
		js.append("var el = ");
		appendElementRefJS(js, elementId).append("; ");
		js.append("el.style.display = el.style.display == 'none' ? '' : 'none' ;");
		js.append("}");
		return js;
	}

	public static String getParam(String name)
	{
		return (String) FacesContext.getCurrentInstance().getExternalContext().
			getRequestParameterMap().get(name);	
	}
	
    public static void renderChildren(FacesContext context, UIComponent component) throws IOException
    {
        if (component.getChildCount() > 0)
        {
            for (Iterator it = component.getChildren().iterator(); it.hasNext(); )
            {
                UIComponent child = (UIComponent)it.next();
                renderChild(context, child);
            }
        }
    }

    public static void renderChild(FacesContext context, UIComponent child) throws IOException
    {
        if (!child.isRendered())
        	return;
        
        child.encodeBegin(context);
        if (child.getRendersChildren())
        {
            child.encodeChildren(context);
        }
        else
        {
            renderChildren(context, child);
        }
        child.encodeEnd(context);
    }
}
