package edu.emory.library.tast.ui.maps.component;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

import org.apache.myfaces.el.MethodBindingImpl;

public class LegendTag extends UIComponentTag {

	private String id;

	private String style;

	private String styleClass;

	private String legend;

	private String layers;

	private String refreshAction;

	protected void setProperties(UIComponent component) {

		Application app = FacesContext.getCurrentInstance().getApplication();

		if (id != null && isValueReference(id)) {
			ValueBinding vb = app.createValueBinding(id);
			component.setValueBinding("id", vb);
		} else if (id != null) {
			component.getAttributes().put("id", id);
		}
		
		if (style != null && isValueReference(style)) {
			ValueBinding vb = app.createValueBinding(style);
			component.setValueBinding("style", vb);
		} else  if (style != null) {
			component.getAttributes().put("style", style);
		}
		
		if (styleClass != null && isValueReference(styleClass)) {
			ValueBinding vb = app.createValueBinding(styleClass);
			component.setValueBinding("styleClass", vb);
		} else  if (styleClass != null)  {
			component.getAttributes().put("styleClass", styleClass);
		}
		
		if (legend != null && isValueReference(legend)) {
			ValueBinding vb = app.createValueBinding(legend);
			component.setValueBinding("legend", vb);
		} else  if (legend != null)  {
			component.getAttributes().put("legend", legend);
		}
		
		if (layers != null && isValueReference(layers)) {
			ValueBinding vb = app.createValueBinding(layers);
			component.setValueBinding("layers", vb);
		} else  if (layers != null)  {
			component.getAttributes().put("layers", layers);
		}
		
		if (component instanceof LegendComponent && refreshAction != null) {
			LegendComponent tab = (LegendComponent)component;
			tab.setRefreshAction(new MethodBindingImpl(app, refreshAction, new Class[] {}));
		}
	}

	public String getComponentType() {
		return "Legend";
	}

	public String getRendererType() {
		return null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLayers() {
		return layers;
	}

	public void setLayers(String layers) {
		this.layers = layers;
	}

	public String getLegend() {
		return legend;
	}

	public void setLegend(String legendItems) {
		this.legend = legendItems;
	}

	public String getRefreshAction() {
		return refreshAction;
	}

	public void setRefreshAction(String refreshAction) {
		this.refreshAction = refreshAction;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
}
