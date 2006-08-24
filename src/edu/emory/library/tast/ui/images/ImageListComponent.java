package edu.emory.library.tast.ui.images;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.FacesEvent;

import edu.emory.library.tast.util.JsfUtils;
import edu.emory.library.tast.util.StringUtils;

public class ImageListComponent extends UICommand
{
	
	private MethodBinding onImageSelected;

	private boolean imagesSet = false;
	private List images;

	private boolean listStyleSet = false;
	private ImageListStyle listStyle = ImageListStyle.Table;
	
	private boolean thumbnailWidthSet = false;
	private int thumbnailWidth;
	
	private boolean thumbnailHeightSet = false;
	private int thumbnailHeight;

	public String getFamily()
	{
		return "edu.emory.library.tast.ImageList";
	}
	
	public Object saveState(FacesContext context)
	{
		Object values[] = new Object[5];
		values[0] = super.saveState(context);
		values[1] = saveAttachedState(context, onImageSelected);
		values[2] = listStyle;
		values[3] = new Integer(thumbnailWidth);
		values[4] = new Integer(thumbnailHeight);
		return values;
	}
	
	public void restoreState(FacesContext context, Object state)
	{
		Object values[] = (Object[]) state;
		super.restoreState(context, values[0]);
		onImageSelected = (MethodBinding) restoreAttachedState(context, values[1]);
		listStyle = (ImageListStyle) values[2];
		thumbnailWidth = ((Integer) values[3]).intValue();
		thumbnailHeight = ((Integer) values[4]).intValue();
	}
	
	
	private String getFieldNameForSelectedImage(FacesContext context)
	{
		return getClientId(context) + "_selected_id";
	}
	
	public void decode(FacesContext context)
	{
		Map params = context.getExternalContext().getRequestParameterMap();
		
		String imageId = JsfUtils.getParamString(params, getFieldNameForSelectedImage(context));
		if (!StringUtils.isNullOrEmpty(imageId))
		{
			queueEvent(new ImageSelectedEvent(this, imageId));
			queueEvent(new ActionEvent(this));
		}

	}
	
	public void broadcast(FacesEvent event) throws AbortProcessingException
	{
		
		super.broadcast(event);
		
		if (event instanceof ImageSelectedEvent)
			if (onImageSelected != null)
				onImageSelected.invoke(getFacesContext(), new Object[] {event});
		
	}
	
	private void encodeImageThumbnail(FacesContext context, ResponseWriter writer, ImageListItem image, String onClick, int thumbnailWidth, int thumbnailHeight) throws IOException
	{

		writer.startElement("a", this);
		writer.writeAttribute("href", "#", null);
		writer.writeAttribute("onclick", onClick, null);
		
		writer.startElement("img", this);
		writer.writeAttribute("class", "imagelist-thumbnail", null);
		writer.writeAttribute("src", image.getUrl(), null);
		writer.writeAttribute("border", "0", null);
		writer.writeAttribute("width", String.valueOf(thumbnailWidth), null);
		writer.writeAttribute("height", String.valueOf(thumbnailHeight), null);
		writer.endElement("img");
		
		writer.endElement("a");

	}
	
	private void encodeTableOrList(FacesContext context, ResponseWriter writer, UIForm form, List images, boolean displayThumbnails) throws IOException
	{

		writer.startElement("table", this);
		writer.writeAttribute("border", "0", null);
		writer.writeAttribute("cellspacing", "0", null);
		writer.writeAttribute("cellpadding", "0", null);
		writer.writeAttribute("class", "imagelist-table", null);
		
		for (Iterator iter = images.iterator(); iter.hasNext();)
		{
			ImageListItem image = (ImageListItem) iter.next();
			
			String onClick = JsfUtils.generateSubmitJS(
					context, form,
					getFieldNameForSelectedImage(context), image.getId());
			
			writer.startElement("tr", this);

			if (displayThumbnails)
			{
				writer.startElement("td", this);
				encodeImageThumbnail(context, writer, image, onClick, thumbnailWidth, thumbnailHeight);
				writer.endElement("td");
			}

			writer.startElement("td", this);
			writer.startElement("a", this);
			writer.writeAttribute("href", "#", null);
			writer.writeAttribute("onclick", onClick, null);
			writer.write(image.getName());
			writer.endElement("a");
			writer.endElement("td");
			
			writer.startElement("td", this);
			writer.write(image.getWidth() + " x " + image.getHeight());
			writer.endElement("td");

			writer.endElement("tr");
			
		}
		
		writer.endElement("table");
		
	}
	
	private void encodeGallery(FacesContext context, ResponseWriter writer, UIForm form, List images) throws IOException
	{

		writer.startElement("div", this);
		
		for (Iterator iter = images.iterator(); iter.hasNext();)
		{
			ImageListItem image = (ImageListItem) iter.next();
			
			String onClick = JsfUtils.generateSubmitJS(
					context, form,
					getFieldNameForSelectedImage(context), image.getId());

			writer.startElement("div", this);
			writer.writeAttribute("class", "imagelist-gallery-image", null);
			
			writer.startElement("div", this);
			writer.writeAttribute("class", "imagelist-gallery-thumbnail", null);
			encodeImageThumbnail(context, writer, image, onClick, thumbnailWidth, thumbnailHeight);
			writer.endElement("div");

			writer.startElement("div", this);
			writer.writeAttribute("class", "imagelist-gallery-name", null);
			writer.writeAttribute("href", "#", null);
			writer.writeAttribute("onclick", onClick, null);
			writer.write(image.getName());
			writer.endElement("div");
			
			writer.startElement("div", this);
			writer.writeAttribute("class", "imagelist-gallery-size", null);
			writer.write(image.getWidth() + " x " + image.getHeight());
			writer.endElement("div");

			writer.endElement("div");
			
		}
		
		writer.endElement("div");
	
	}

	public void encodeBegin(FacesContext context) throws IOException
	{
		
		// general stuff
		ResponseWriter writer = context.getResponseWriter();
		UIForm form = JsfUtils.getForm(this, context);
		
		// a field for storing the selected image id
		JsfUtils.encodeHiddenInput(this, writer, getFieldNameForSelectedImage(context));

		// get data from a bean
		List images = getImages();
		listStyle = getListStyle();
		thumbnailWidth = getThumbnailWidth();
		thumbnailHeight = getThumbnailHeight();
		
 		// render table
		if (listStyle.equals(ImageListStyle.Table))
			encodeTableOrList(context, writer, form,
					images, false);

 		// render list
		else if (listStyle.equals(ImageListStyle.List))
			encodeTableOrList(context, writer, form,
					images, true);

		// render gallery
		else if (listStyle.equals(ImageListStyle.Gallery))
			encodeGallery(context, writer, form,
					images);
		
	}
	
	public void encodeChildren(FacesContext context) throws IOException
	{
	}
	
	public void encodeEnd(FacesContext context) throws IOException
	{
	}

	public void setImages(List images)
	{
		imagesSet = true;
		this.images = images;
	}

	public List getImages()
	{
        if (imagesSet) return images;
        ValueBinding vb = getValueBinding("images");
        if (vb == null) return images;
        return (List) vb.getValue(getFacesContext());
	}

	public void setListStyle(ImageListStyle listStyle)
	{
		this.listStyle = listStyle;
	}

	public ImageListStyle getListStyle()
	{
        if (listStyleSet) return listStyle;
        ValueBinding vb = getValueBinding("listStyle");
        if (vb == null) return listStyle;
        Object listStyleLocalObj = vb.getValue(getFacesContext());
        if (listStyleLocalObj instanceof String)
        	return ImageListStyle.parse((String) listStyleLocalObj);
        else
        	return (ImageListStyle) listStyleLocalObj;
	}

	public MethodBinding getOnImageSelected()
	{
		return onImageSelected;
	}

	public void setOnImageSelected(MethodBinding onImageSelected)
	{
		this.onImageSelected = onImageSelected;
	}

	public int getThumbnailHeight()
	{
        if (thumbnailHeightSet) return thumbnailHeight;
        ValueBinding vb = getValueBinding("thumbnailHeight");
        if (vb == null) return thumbnailHeight;
        return ((Integer)vb.getValue(getFacesContext())).intValue();
	}

	public void setThumbnailHeight(int thumbnailHeight)
	{
		thumbnailHeightSet = true;
		this.thumbnailHeight = thumbnailHeight; 
	}

	public int getThumbnailWidth()
	{
        if (thumbnailWidthSet) return thumbnailWidth;
        ValueBinding vb = getValueBinding("thumbnailWidth");
        if (vb == null) return thumbnailWidth;
        return ((Integer)vb.getValue(getFacesContext())).intValue();
	}

	public void setThumbnailWidth(int thumbnailWidth)
	{
		thumbnailWidthSet = true;
		this.thumbnailWidth = thumbnailWidth;
	}

}