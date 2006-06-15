package edu.emory.library.tas.web.components.tabs;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

import edu.emory.library.tas.util.query.Conditions;

public class TimeLineResultTabTag extends UIComponentTag {

	private static final String STAT_RESULT_TAB = "TimeLineResultTab";

	private String style;
	private String styleClass;

	private String conditions;
	private String conditionsOut;
	
	
	protected void setProperties(UIComponent component) {

		super.setProperties(component);

		if (style != null) {
			if (isValueReference(style)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(style);
				component.setValueBinding("style", vb);
			} else {
				component.getAttributes().put("style", style);
			}
		}
		if (styleClass != null) {
			if (isValueReference(styleClass)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(styleClass);
				component.setValueBinding("styleClass", vb);
			} else {
				component.getAttributes().put("styleClass", styleClass);
			}
		}
		if (conditions != null) {
			if (isValueReference(conditions)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(conditions);
				component.setValueBinding("conditions", vb);
			} else {
				component.getAttributes().put("conditions", styleClass);
			}
		}
		if (conditionsOut != null) {
			if (isValueReference(conditionsOut)) {
				ValueBinding vb = getFacesContext().getApplication()
						.createValueBinding(conditionsOut);
				component.setValueBinding("conditionsOut", vb);
			} else {
				component.getAttributes().put("conditionsOut", styleClass);
			}
		}
    
	}
	
	public void release() {
		super.release();
	}

	public String getComponentType() {
		return STAT_RESULT_TAB;
	}

	public String getRendererType() {
		return null;
	}
	
	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	public void setQuery(String c) {
		this.conditions = c;
	}

	public String getConditionsOut() {
		return conditionsOut;
	}

	public void setConditionsOut(String conditionsOut) {
		this.conditionsOut = conditionsOut;
	}
	
}
