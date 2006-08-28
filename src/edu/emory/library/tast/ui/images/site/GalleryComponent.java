package edu.emory.library.tast.ui.images.site;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.el.ValueBinding;

import edu.emory.library.tast.dm.Image;
import edu.emory.library.tast.dm.Person;
import edu.emory.library.tast.dm.Port;
import edu.emory.library.tast.dm.Region;
import edu.emory.library.tast.util.JsfUtils;

public class GalleryComponent extends UIComponentBase {

	private static final String GALLERY_BACK_BUTTON = "gallery-back-button";

	private static final String GALLERY_FORWARD_BUTTON = "gallery-forward-button";

	public String getFamily() {
		return null;
	}

	public void decode(FacesContext context) {
		Map params = context.getExternalContext().getRequestParameterMap();

		String action = (String) params.get(getHiddenFieldId(context));
		if (action != null) {
			int rows = Integer.parseInt(this.getValueOrAttribute(context,
					"rows").toString());
			int cols = Integer.parseInt(this.getValueOrAttribute(context,
					"columns").toString());
			PictureGalery pictures = (PictureGalery) this.getValueBinding(
					context, "pictures");

			if (pictures != null) {
				if (GALLERY_FORWARD_BUTTON.equals(action)) {
					if (pictures.canMoveForward(rows * cols)) {
						pictures.moveForward(rows * cols);
					}
				} else if (GALLERY_BACK_BUTTON.equals(action)) {
					if (pictures.canMoveBackward(rows * cols)) {
						pictures.moveBackward(rows * cols);
					}
				} else if (!action.trim().equals("")) {
					int visible = Integer.parseInt(action);

					GaleryImage[] picts = pictures.getPictures(rows * cols);
					pictures.setVisiblePicture(picts[visible]);
				}
			}
		}

	}

	public void encodeBegin(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.startElement("div", this);
		writer.writeAttribute("align", "center", null);

		JsfUtils
				.encodeHiddenInput(this, writer, this.getHiddenFieldId(context));

		int rows = Integer.parseInt(this.getValueOrAttribute(context, "rows")
				.toString());
		int cols = Integer.parseInt(this
				.getValueOrAttribute(context, "columns").toString());
		int thumbnailWidth = Integer.parseInt(this.getValueOrAttribute(context,
				"thumbnailWidth").toString());
		int thumbnailHeight = Integer.parseInt(this.getValueOrAttribute(
				context, "thumbnailHeight").toString());

		PictureGalery pictures = (PictureGalery) this.getValueBinding(context,
				"pictures");
		if (pictures != null) {

//			writer.startElement("table", this);
//			writer.startElement("tr", this);
//			writer.startElement("td", this);
//			writer.writeAttribute("style", "text-align: center;", null);

			writer.startElement("table", this);
			writer.writeAttribute("class", "gallery-table-thumbnails", null);
			writer.startElement("tr", this);
			this.encodeButton(context, writer, GALLERY_BACK_BUTTON);

			writer.startElement("td", this);
			writer.startElement("table", this);
			GaleryImage[] picts = pictures.getPictures(rows * cols);
			for (int i = 0; i < rows * cols && i < picts.length; i++) {
				if (i % rows == 0) {
					writer.startElement("tr", this);
				}
				writer.startElement("td", this);
				writer.writeAttribute("style", "text-align: center;", null);
				Image image = picts[i].getImage();
				writer.startElement("a", this);
				writer.writeAttribute("href", "#", null);
				String js = JsfUtils.generateSubmitJS(context, JsfUtils
						.getForm(this, context),
						this.getHiddenFieldId(context), i + "");
				writer.writeAttribute("onclick", js, null);

				writer.startElement("img", this);
				if (thumbnailWidth != -1) {
					writer.writeAttribute("width", String
							.valueOf(thumbnailWidth), null);
				}
				if (thumbnailHeight != -1) {
					writer.writeAttribute("height", String
							.valueOf(thumbnailHeight), null);
				}
				writer.writeAttribute("src", "images/"
						+ image.getThumbnailFileName(), null);
				writer.writeAttribute("style", "cursor: pointer;", null);

				writer.write("<br/>");
				writer.write(image.getName());

				writer.endElement("td");
				if ((i + 1) % rows == 0) {
					writer.endElement("tr");
				}
			}
			writer.endElement("table");
			writer.endElement("td");

			this.encodeButton(context, writer, GALLERY_FORWARD_BUTTON);
			writer.endElement("tr");
			writer.endElement("table");

//			writer.endElement("td");
//			writer.endElement("tr");
//			writer.startElement("tr", this);
//			writer.startElement("td", this);
//			writer.writeAttribute("style", "text-align: center;", null);

			GaleryImage visibleImage = pictures.getVisiblePicture();
			if (visibleImage != null) {
				writer.write(visibleImage.getImage().getName());
				writer.write("<br/>");
				writer.startElement("img", this);
				writer.writeAttribute("src", "images/"
						+ visibleImage.getImage().getFileName(), null);
				writer.endElement("img");
				this.printImageInfo(context, writer, visibleImage);
			}

//			writer.endElement("td");

//			writer.endElement("tr");
//			writer.endElement("table");
		}
	}

	private void printImageInfo(FacesContext context, ResponseWriter writer,
			GaleryImage visibleImage) throws IOException {
		writer.startElement("table", this);
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
		writer.write("Related people:");
		writer.endElement("td");
		writer.startElement("td", this);
		Person[] persons = visibleImage.getPeople();
		if (persons != null) {
			for (int i = 0; i < persons.length; i++) {
				Person person = persons[i];
				writer.startElement("a", this);
				writer.writeAttribute("href", "#", null);
				if (person.getFirstName() != null) {
					writer.write(person.getFirstName());
				}
				writer.write(" ");
				if (person.getLastName() != null) {
					writer.write(person.getLastName());
				}
				writer.endElement("a");
				writer.write(" ");
			}
		}
		writer.endElement("td");
		writer.endElement("tr");

		writer.startElement("tr", this);
		writer.startElement("td", this);
		writer.write("Related ports:");
		writer.endElement("td");
		writer.startElement("td", this);
		Port[] ports = visibleImage.getPorts();
		if (ports != null) {
			for (int i = 0; i < ports.length; i++) {
				Port port = ports[i];
				writer.startElement("a", this);
				writer.writeAttribute("href", "#", null);
				writer.write(port.getName());
				writer.endElement("a");
				writer.write(" ");
			}
		}
		writer.endElement("td");
		writer.endElement("tr");

		writer.startElement("tr", this);
		writer.startElement("td", this);
		writer.write("Related regions:");
		writer.endElement("td");
		writer.startElement("td", this);
		Region[] regions = visibleImage.getRegions();
		if (regions != null) {
			for (int i = 0; i < regions.length; i++) {
				Region region = regions[i];
				writer.startElement("a", this);
				writer.writeAttribute("href", "#", null);
				writer.write(region.getName());
				writer.endElement("a");
				writer.write(" ");
			}
		}
		writer.endElement("td");
		writer.endElement("tr");
	}

	private void encodeButton(FacesContext context, ResponseWriter writer,
			String button) throws IOException {
		writer.startElement("td", this);
		writer.startElement("div", this);
		writer.writeAttribute("class", button, null);
		String js = JsfUtils.generateSubmitJS(context, JsfUtils.getForm(this,
				context), this.getHiddenFieldId(context), button);
		writer.writeAttribute("onclick", js, null);
		writer.endElement("div");
		writer.endElement("td");
	}

	public void encodeEnd(FacesContext context) throws IOException {
		ResponseWriter writer = context.getResponseWriter();
		writer.endElement("div");
	}

	private Object getValueBinding(FacesContext context, String string) {
		ValueBinding vb = this.getValueBinding(string);
		if (vb != null && vb.getValue(context) != null) {
			return vb.getValue(context);
		}
		return null;
	}

	private Object getValueOrAttribute(FacesContext context, String string) {
		if (this.getAttributes().containsKey(string)) {
			return this.getAttributes().get(string);
		} else if (this.getValueBinding(context, string) != null) {
			return this.getValueBinding(context, string);
		} else {
			return "-1";
		}
	}

	private String getHiddenFieldId(FacesContext context) {
		return this.getId() + "_actionSensor";
	}
}