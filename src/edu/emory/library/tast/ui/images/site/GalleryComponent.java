package edu.emory.library.tast.ui.images.site;

import java.io.IOException;
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
import javax.servlet.ServletContext;

import org.apache.myfaces.el.MethodBindingImpl;
import org.hibernate.mapping.ValueVisitor;

import edu.emory.library.tast.dm.Image;
import edu.emory.library.tast.dm.Person;
import edu.emory.library.tast.dm.Port;
import edu.emory.library.tast.dm.Region;
import edu.emory.library.tast.ui.search.table.ShowDetailsEvent;
import edu.emory.library.tast.ui.search.table.SortChangeEvent;
import edu.emory.library.tast.util.JsfUtils;

public class GalleryComponent extends UICommand {

	private static final String GALLERY_BACK_BUTTON = "gallery-back-button";

	private static final String GALLERY_FORWARD_BUTTON = "gallery-forward-button";

	private MethodBinding showEvent;
	
	public void restoreState(FacesContext arg0, Object arg1) {
		Object[] state = (Object[])arg1;
		super.restoreState(arg0, state[0]);
		this.showEvent = (MethodBinding)restoreAttachedState(arg0, state[1]);
	}

	public Object saveState(FacesContext arg0) {
		Object[] state = new Object[2];
		state[0] = super.saveState(arg0); 
		state[1] = saveAttachedState(arg0, showEvent);
		return state;
	}

	public void decode(FacesContext context) {
		Map params = context.getExternalContext().getRequestParameterMap();

		System.out.println("Decode!!!!");
		if (params.get(this.getHiddenFieldId(context)) != null) {
			String param = (String)params.get(this.getHiddenFieldId(context));
			queueEvent(new ShowVoyageEvent(this, new Integer(param)));
		}

	}
	
	public void broadcast(FacesEvent event) throws AbortProcessingException {
		super.broadcast(event);

		if (event instanceof ShowVoyageEvent && showEvent != null) {
			showEvent.invoke(getFacesContext(), new Object[] { event });
		}

	}

	public void encodeBegin(FacesContext context) throws IOException {

		UIForm form = JsfUtils.getForm(this, context);

		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", this);
		writer.writeAttribute("align", "center", null);

		JsfUtils.encodeHiddenInput(this, writer, this.getHiddenFieldId(context));

		int rows = Integer.parseInt(this.getValueOrAttribute(context, "rows")
				.toString());
		int cols = Integer.parseInt(this
				.getValueOrAttribute(context, "columns").toString());
		int thumbnailWidth = Integer.parseInt(this.getValueOrAttribute(context,
				"thumbnailWidth").toString());
		int thumbnailHeight = Integer.parseInt(this.getValueOrAttribute(
				context, "thumbnailHeight").toString());

		PictureGalery pictures = (PictureGalery) this.getBoundedValue(context,
				"pictures");

		GalleryRequestBean.GalleryParams params = (GalleryRequestBean.GalleryParams) this
				.getValueOrAttribute(context, "galleryParams");

		params.restoreState();
		
		if (pictures != null) {

			int set = Integer.parseInt(params.getVisibleSet());
			
			writer.startElement("table", this);
			writer.writeAttribute("class", "gallery-table", null);
			writer.startElement("tr", this);

			int prevset = Integer.parseInt(params.getVisibleSet());
			if (pictures.canMoveBackward(set, rows * cols)) {
				prevset--;
			}
			StringBuffer js = new StringBuffer();
			js.append("window.location='galleryp.faces?");
			js.append(GalleryRequestBean.GALLERY_TYPE).append("=");
			js.append(params.getGalleryType());
			js.append("&").append(GalleryRequestBean.ID).append("=");
			js.append(params.getId());
			js.append("&").append(GalleryRequestBean.SET).append("=");
			js.append(prevset);
			if (params.getVisiblePicture() != null) {
				js.append("&").append(GalleryRequestBean.PICT).append("=");
				js.append("0");
			}
			js.append("'");
			this.encodeButton(context, form, writer, GALLERY_BACK_BUTTON, js.toString());

			writer.startElement("td", this);
			writer.startElement("table", this);
			writer.writeAttribute("class", "gallery-table-thumbnails", null);
			GaleryImage[] picts = pictures.getPictures(set, rows * cols);
			for (int i = 0; i < rows * cols && i < picts.length; i++) {
				if (i % rows == 0) {
					writer.startElement("tr", this);
				}
				writer.startElement("td", this);
				if (params.getVisiblePicture() != null) {
					int n = Integer.parseInt(params.getVisiblePicture());
					if (n == i) {
						writer.writeAttribute("class", "gallery-selected", null);
					}
				}
				Image image = picts[i].getImage();
				writer.startElement("a", this);
				StringBuffer link = new StringBuffer();
				link.append("galleryp.faces?");
				link.append(GalleryRequestBean.GALLERY_TYPE).append("=");
				link.append(params.getGalleryType());
				link.append("&").append(GalleryRequestBean.ID).append("=");
				link.append(params.getId());
				link.append("&").append(GalleryRequestBean.SET).append("=");
				link.append(params.getVisibleSet());
				link.append("&").append(GalleryRequestBean.PICT).append("=");
				link.append(i);
				writer.writeAttribute("href", link.toString(), null);

				writer.startElement("img", this);
				if (thumbnailWidth != -1) {
					writer.writeAttribute("width", String
							.valueOf(thumbnailWidth), null);
				}
				if (thumbnailHeight != -1) {
					writer.writeAttribute("height", String
							.valueOf(thumbnailHeight), null);
				}
				writer.writeAttribute("src", "servlet/thumbnail?i="
						+ image.getFileName() + "&w=" + thumbnailWidth + "&h="
						+ thumbnailHeight, null);

				writer.writeAttribute("style", "cursor: pointer;", null);

				writer.write("<br/>");
				writer.write(image.getTitle());

				writer.endElement("td");
				if ((i + 1) % rows == 0) {
					writer.endElement("tr");
				}
			}
			writer.endElement("table");
			writer.startElement("div", this);
			writer.writeAttribute("align", "right;", null);
			writer.write("Showing images from " + (pictures.getFirst(set, rows * cols) + 1)
					+ " to " + (pictures.getLast(set, rows * cols)) + " out of "
					+ pictures.getNumberOfAll());
			writer.endElement("div");
			writer.endElement("td");

			int nextset = Integer.parseInt(params.getVisibleSet());
			if (pictures.canMoveForward(set, rows * cols)) {
				nextset++;
			}
			js = new StringBuffer();
			js.append("window.location='galleryp.faces?");
			js.append(GalleryRequestBean.GALLERY_TYPE).append("=");
			js.append(params.getGalleryType());
			js.append("&").append(GalleryRequestBean.ID).append("=");
			js.append(params.getId());
			js.append("&").append(GalleryRequestBean.SET).append("=");
			js.append(nextset);
			if (params.getVisiblePicture() != null) {
				js.append("&").append(GalleryRequestBean.PICT).append("=");
				js.append("0");
			}
			js.append("'");
			this.encodeButton(context, form, writer, GALLERY_FORWARD_BUTTON, js.toString());
			writer.endElement("tr");
			writer.endElement("table");

			if (params.getVisiblePicture() != null) {
				int size = Integer.parseInt(params.getVisiblePicture());
				GaleryImage visibleImage = picts[size >= picts.length ? picts.length - 1:size];
				writer.write(visibleImage.getImage().getTitle());
				writer.write("<br/>");
				writer.startElement("img", this);
				writer.writeAttribute("src", "images/"
						+ visibleImage.getImage().getFileName(), null);
				writer.endElement("img");
				this.printImageInfo(context, form, writer, visibleImage, params);
			}

		}
	}

	private void printImageInfo(FacesContext context, UIForm form,
			ResponseWriter writer, GaleryImage visibleImage, GalleryRequestBean.GalleryParams params) throws IOException {
		 
		
		writer.startElement("div", this);
		writer.writeAttribute("style", "width: "
				+ visibleImage.getImage().getWidth() + "px;", null);
		writer.writeAttribute("align", "left", null);
		writer.startElement("table", this);
		writer.writeAttribute("class", "gallery-info-table", null);
		writer.startElement("tr", this);
		writer.startElement("td", this);
		writer.write("Title:");
		writer.endElement("td");
		writer.startElement("td", this);
		if (visibleImage.getImage().getTitle() != null) {
			writer.write(visibleImage.getImage().getTitle());
		} else {
			writer.write("none");
		}
		writer.endElement("td");
		writer.endElement("tr");

		writer.startElement("tr", this);
		writer.startElement("td", this);
		writer.write("Description:");
		writer.endElement("td");
		writer.startElement("td", this);
		if (visibleImage.getImage().getDescription() != null) {
			writer.write(visibleImage.getImage().getDescription());
		} else {
			writer.write("none");
		}
		writer.endElement("td");
		writer.endElement("tr");
		
		
		writer.startElement("tr", this);
		writer.startElement("td", this);
		writer.write("Date:");
		writer.endElement("td");
		writer.startElement("td", this);
		if (visibleImage.getImage().getDate() != null) {
			writer.write(visibleImage.getImage().getDate());
		} else {
			writer.write("unknown");
		}
		writer.endElement("td");
		writer.endElement("tr");
		
		writer.startElement("tr", this);
		writer.startElement("td", this);
		writer.write("Source:");
		writer.endElement("td");
		writer.startElement("td", this);
		if (visibleImage.getImage().getSource() != null) {
			writer.write(visibleImage.getImage().getSource());
		} else {
			writer.write("unknown");
		}
		writer.endElement("td");
		writer.endElement("tr");
		
		writer.startElement("tr", this);
		writer.startElement("td", this);
		writer.write("Creator:");
		writer.endElement("td");
		writer.startElement("td", this);
		if (visibleImage.getImage().getCreator() != null) {
			writer.write(visibleImage.getImage().getCreator());
		} else {
			writer.write("unknown");
		}
		writer.endElement("td");
		writer.endElement("tr");
		
		
		writer.startElement("tr", this);
		writer.startElement("td", this);
		writer.write("Linked voyage:");
		writer.endElement("td");
		writer.startElement("td", this);	
		if (visibleImage.getImage().getVoyageid() != null) {
			StringBuffer link = new StringBuffer();
			link.append("galleryp.faces?").append("vid=");
			link.append(visibleImage.getImage().getVoyageid() + "");
			
			writer.startElement("a", this);
			writer.writeAttribute("href", "#", null);
			String jsClick = JsfUtils.generateSubmitJS(context, form,
					getHiddenFieldId(context), "" + visibleImage.getImage().getVoyageid());
			writer.writeAttribute("onclick", jsClick, null);
			writer.write(visibleImage.getImage().getVoyageid() + "");
			writer.endElement("a");
		} else {
			writer.write("none");
		}
		writer.endElement("td");
		writer.endElement("tr");
		
//		writer.write("Related people:");
//		writer.endElement("td");
//		writer.startElement("td", this);
//		Person[] persons = visibleImage.getPeople();
//		if (persons != null) {
//			for (int i = 0; i < persons.length; i++) {
//				Person person = persons[i];
//				writer.startElement("a", this);
//
//				StringBuffer link = new StringBuffer();
//				link.append("galleryp.faces?").append(GalleryRequestBean.GALLERY_TYPE);				
//				link.append("=").append("people");
//				link.append("&").append(GalleryRequestBean.ID);
//				link.append("=").append(person.getId());
//				link.append("&").append(GalleryRequestBean.SET);
//				link.append("=1");				
//				writer.writeAttribute("href", link, null);
//				if (person.getFirstName() != null) {
//					writer.write(person.getFirstName());
//				}
//				writer.write(" ");
//				if (person.getLastName() != null) {
//					writer.write(person.getLastName());
//				}
//				writer.endElement("a");
//				if (i + 1 < persons.length) {
//					writer.write("; ");
//				}
//			}
//		}
//		writer.endElement("td");
//		writer.endElement("tr");
//
//		writer.startElement("tr", this);
//		writer.startElement("td", this);
//		writer.write("Related ports:");
//		writer.endElement("td");
//		writer.startElement("td", this);
//		Port[] ports = visibleImage.getPorts();
//		if (ports != null) {
//			for (int i = 0; i < ports.length; i++) {
//				Port port = ports[i];
//				writer.startElement("a", this);
//				
//				StringBuffer link = new StringBuffer();
//				link.append("galleryp.faces?").append(GalleryRequestBean.GALLERY_TYPE);				
//				link.append("=").append("ports");
//				link.append("&").append(GalleryRequestBean.ID);
//				link.append("=").append(port.getId());
//				link.append("&").append(GalleryRequestBean.SET);
//				link.append("=1");
//				writer.writeAttribute("href", link, null);
//
//				writer.write(port.getName());
//				writer.endElement("a");
//				if (i + 1 < ports.length) {
//					writer.write("; ");
//				}
//			}
//		}
//		writer.endElement("td");
//		writer.endElement("tr");
//
//		writer.startElement("tr", this);
//		writer.startElement("td", this);
//		writer.write("Related regions:");
//		writer.endElement("td");
//		writer.startElement("td", this);
//		Region[] regions = visibleImage.getRegions();
//		if (regions != null) {
//			for (int i = 0; i < regions.length; i++) {
//				Region region = regions[i];
//				writer.startElement("a", this);
//				
//				StringBuffer link = new StringBuffer();
//				link.append("galleryp.faces?").append(GalleryRequestBean.GALLERY_TYPE);				
//				link.append("=").append("regions");
//				link.append("&").append(GalleryRequestBean.ID);
//				link.append("=").append(region.getId());
//				link.append("&").append(GalleryRequestBean.SET);
//				link.append("=1");
//				writer.writeAttribute("href", link, null);
//
//				writer.write(region.getName());
//				writer.endElement("a");
//				if (i + 1 < regions.length) {
//					writer.write("; ");
//				}
//			}
//		}
//		writer.endElement("td");
//		writer.endElement("tr");
		writer.endElement("table");
		writer.endElement("div");
	}

	private void encodeButton(FacesContext context, UIForm form,
			ResponseWriter writer, String button, String js) throws IOException {
		writer.startElement("td", this);
		writer.startElement("div", this);
		writer.writeAttribute("class", button, null);
		writer.writeAttribute("onclick", js, null);
		writer.endElement("div");
		writer.endElement("td");
	}

	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("div");
	}

	private Object getBoundedValue(FacesContext context, String string) {
		ValueBinding vb = this.getValueBinding(string);
		if (vb != null && vb.getValue(context) != null) {
			return vb.getValue(context);
		}
		return null;
	}

	private Object getValueOrAttribute(FacesContext context, String string) {
		if (this.getAttributes().containsKey(string)) {
			return this.getAttributes().get(string);
		} else if (this.getBoundedValue(context, string) != null) {
			return this.getBoundedValue(context, string);
		} else {
			return "-1";
		}
	}

	private String getHiddenFieldId(FacesContext context) {
		return this.getId() + "_actionSensor";
	}

	public void setShowEventHandler(MethodBindingImpl impl) {
		this.showEvent = impl;
	}
}
