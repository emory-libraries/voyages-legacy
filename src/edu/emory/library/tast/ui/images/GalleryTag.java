package edu.emory.library.tast.ui.images;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentTag;

public class GalleryTag extends UIComponentTag
{
	
	private String thumbnailWidth;
	private String thumbnailHeight;
	private String columnsCount;
	private String images;

	public String getComponentType()
	{
		return "Gallery";
	}

	public String getRendererType()
	{
		return null;
	}
	
	protected void setProperties(UIComponent component)
	{
		
		GalleryComponent gallery = (GalleryComponent) component;
		Application app = FacesContext.getCurrentInstance().getApplication();
		
		if (thumbnailWidth != null && isValueReference(thumbnailWidth))
		{
			ValueBinding vb = app.createValueBinding(thumbnailWidth);
			gallery.setValueBinding("thumbnailWidth", vb);
		}
		else
		{
			gallery.setThumbnailWidth(Integer.parseInt(thumbnailWidth));
		}

		if (thumbnailHeight != null && isValueReference(thumbnailHeight))
		{
			ValueBinding vb = app.createValueBinding(thumbnailHeight);
			gallery.setValueBinding("thumbnailHeight", vb);
		}
		else
		{
			gallery.setThumbnailHeight(Integer.parseInt(thumbnailHeight));
		}
		
		if (columnsCount != null && isValueReference(columnsCount))
		{
			ValueBinding vb = app.createValueBinding(columnsCount);
			gallery.setValueBinding("columnsCount", vb);
		}
		else
		{
			gallery.setColumnsCount(Integer.parseInt(columnsCount));
		}

		if (images != null && isValueReference(images))
		{
			ValueBinding vb = app.createValueBinding(images);
			gallery.setValueBinding("images", vb);
		}

	}

	public String getColumnsCount()
	{
		return columnsCount;
	}

	public void setColumnsCount(String columnsCount)
	{
		this.columnsCount = columnsCount;
	}

	public String getImages()
	{
		return images;
	}

	public void setImages(String images)
	{
		this.images = images;
	}

	public String getThumbnailHeight()
	{
		return thumbnailHeight;
	}

	public void setThumbnailHeight(String thumbnailHeight)
	{
		this.thumbnailHeight = thumbnailHeight;
	}

	public String getThumbnailWidth()
	{
		return thumbnailWidth;
	}

	public void setThumbnailWidth(String thumbnailWidth)
	{
		this.thumbnailWidth = thumbnailWidth;
	}

}