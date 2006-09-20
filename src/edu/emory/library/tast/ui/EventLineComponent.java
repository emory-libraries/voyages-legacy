package edu.emory.library.tast.ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.imageio.ImageIO;

import edu.emory.library.tast.util.JsfUtils;

public class EventLineComponent extends UIComponentBase
{
	
	private static final int TOP_LABELS_HEIGHT = 25;
	private static final int TOP_LABELS_WIDTH = 50;
	private static final int LEFT_LABELS_WIDTH = 60;
	private static final int LEFT_LABELS_HEIGHT = 30;
	private static final int LABELS_MARGIN = 10;
	
	private boolean widthSet = false;
	private int width;
	
	private boolean heightSet = false;
	private int height;

	private boolean xMinSet = false;
	private int xMin;

	private boolean xMaxSet = false;
	private int xMax;

	private boolean xSubdivSet = false;
	private int xSubdiv;

	private boolean yMinSet = false;
	private int yMin;

	private boolean yMaxSet = false;
	private int yMax;
	
	private boolean ySubdivSet = false;
	private int ySubdiv;

	private boolean dataSequencesSet = false;
	private EventLineDataSequence[] dataSequences;
	
	private boolean itemsSet = false;
	private EventLineItem[] items;

	public String getFamily()
	{
		return null;
	}
	
	public Object saveState(FacesContext context)
	{
		Object[] state = new Object[9];
		state[0] = super.saveState(context);
		state[1] = new Integer(width);
		state[2] = new Integer(height);
		state[3] = new Integer(xMin);
		state[4] = new Integer(xMax);
		state[5] = new Integer(xSubdiv);
		state[6] = new Integer(yMin);
		state[7] = new Integer(yMax);
		state[8] = new Integer(ySubdiv);
		return state;
	}
	
	public void restoreState(FacesContext context, Object stateObj)
	{
		Object[] state = (Object[]) stateObj;
		super.restoreState(context, state[0]);
		width = ((Integer) state[1]).intValue();
		height = ((Integer) state[2]).intValue();
		xMin = ((Integer) state[3]).intValue();
		xMax = ((Integer) state[4]).intValue();
		xSubdiv = ((Integer) state[5]).intValue();
		yMin = ((Integer) state[6]).intValue();
		yMax = ((Integer) state[7]).intValue();
		ySubdiv = ((Integer) state[8]).intValue();
	}
	
	private void renderGraph(EventLineDataSequence data) throws IOException
	{
		
		int[] x = new int[data.getCount()];
		for (int i = 0; i < x.length; i++) x[i] = i;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gr = image.createGraphics();
		gr.drawPolyline(x, data.getData(), 0);
		gr.dispose();

		ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		ImageIO.write(image, "png", imageStream);
		byte[] imageBytes = imageStream.toByteArray();
		
	}
	
	private void encodeLabel(ResponseWriter writer, String label, String cssClass, int left, int top, int width, int height, String hAlign, String vAlign) throws IOException
	{
		
		StringBuffer cssStyle = new StringBuffer(); 
		cssStyle.append("position: absolute; ");
		cssStyle.append("left: ").append(left).append("px; ");
		cssStyle.append("top: ").append(top).append("px; ");
		cssStyle.append("width: ").append(width).append("px; ");
		cssStyle.append("height: ").append(height).append("px;");

		writer.startElement("table", this);
		writer.writeAttribute("border", "0", null);
		writer.writeAttribute("cellspacing", "0", null);
		writer.writeAttribute("cellpadding", "0", null);
		writer.writeAttribute("style", cssStyle, null);
		writer.writeAttribute("class", cssClass, null);
		writer.startElement("tr", this);

		cssStyle.setLength(0);
		cssStyle.append("text-align: ").append(hAlign).append("; ");
		cssStyle.append("vertical-align: ").append(vAlign).append(";");
		
		writer.startElement("td", this);
		writer.writeAttribute("style", cssStyle, null);
		writer.write(label);
		writer.endElement("td");

		writer.endElement("tr");
		writer.endElement("table");

	}
	
	public void encodeBegin(FacesContext context) throws IOException
	{
		
		// general stuff
		ResponseWriter writer = context.getResponseWriter();
//		UIForm form = JsfUtils.getForm(this, context);
		
		// pull data from beans
		width = getWidth();
		height = getHeight();
		xMin = getXMin();
		xMax = getXMax();
		xSubdiv = getXSubdiv();
		dataSequences = getDataSequences();
		
		// total size
		int totalWidth = LEFT_LABELS_WIDTH + LABELS_MARGIN + width;
		int totalHeight = TOP_LABELS_HEIGHT + LABELS_MARGIN + height;
		
		// style of the main div
		StringBuffer mainContainerCssSyle = new StringBuffer();
		mainContainerCssSyle.append("position: relative; ");
		mainContainerCssSyle.append("width: ").append(totalWidth).append("px; ");
		mainContainerCssSyle.append("height: ").append(totalHeight).append("px; ");

		// main div container
		writer.startElement("div", this);
		writer.writeAttribute("style", mainContainerCssSyle, null);
		writer.writeAttribute("class", "event-line-container", null);
		
		// graphs
		StringBuffer graphCssStyle = new StringBuffer();
		for (int i = 0; i < dataSequences.length; i++)
		{
			
			// position by CSS and put the image to it
			graphCssStyle.setLength(0);
			graphCssStyle.append("position: absolute; ");
			graphCssStyle.append("left: ").append(LEFT_LABELS_WIDTH + LABELS_MARGIN).append("px; ");
			graphCssStyle.append("top: ").append(TOP_LABELS_HEIGHT + LABELS_MARGIN).append("px; ");
			graphCssStyle.append("width: ").append(width).append("px; ");
			graphCssStyle.append("height: ").append(height).append("px; ");
			graphCssStyle.append("background-image: url(").append(dataSequences[i]).append("); ");
			
			// HTML
			writer.startElement("div", this);
			writer.writeAttribute("style", graphCssStyle, null);
			writer.writeAttribute("class", "event-line-graph", null);
			writer.endElement("div");

		}
		
		// left labels
		for (int x = xMin; x <= xMax; x += xSubdiv)
		{
			
			// position
			int left =
				(int) Math.round(width * (double)(x - xMin) / (double)(xMax - xMin)) +
				LEFT_LABELS_WIDTH -
				TOP_LABELS_WIDTH / 2 +
				LABELS_MARGIN;
			
			// render
			encodeLabel(writer, String.valueOf(x), "event-line-top-label",
					left, 0, TOP_LABELS_WIDTH, TOP_LABELS_HEIGHT, "center", "bottom");

		}
		
		// top labels
		for (int y = yMin; y <= xMax; y += ySubdiv)
		{
			
			// position
			int top =
				totalHeight -
				(int) Math.round(height * (double)(y - yMin) / (double)(yMax - yMin)) -
				LEFT_LABELS_HEIGHT / 2; 

			// render
			encodeLabel(writer, String.valueOf(y), "event-line-left-label",
					0, top, LEFT_LABELS_WIDTH, LEFT_LABELS_HEIGHT, "right", "center");
			
		}
		
		// main div container
		writer.endElement("div");
		
	}
	
	public void encodeChildren(FacesContext context) throws IOException
	{
	}

	public void encodeEnd(FacesContext context) throws IOException
	{
	}

	public int getXMin()
	{
		return JsfUtils.getCompPropInt(this, getFacesContext(),
				"xMin", xMinSet, xMin);
	}

	public void setXMin(int extentLeft)
	{
		xMinSet = true;
		this.xMin = extentLeft;
	}

	public int getXMax()
	{
		return JsfUtils.getCompPropInt(this, getFacesContext(),
				"xMax", xMaxSet, xMax);
	}

	public void setXMax(int extentRight)
	{
		xMaxSet = true;
		this.xMax = extentRight;
	}

	public int getYMin()
	{
		return JsfUtils.getCompPropInt(this, getFacesContext(),
				"yMin", yMinSet, yMin);
	}

	public void setYMin(int yMin)
	{
		yMinSet = true;
		this.yMin = yMin;
	}

	public int getYMax()
	{
		return JsfUtils.getCompPropInt(this, getFacesContext(),
				"yMax", yMaxSet, yMax);
	}

	public void setYMax(int yMax)
	{
		yMaxSet = true;
		this.yMax = yMax;
	}
	
	public EventLineDataSequence[] getDataSequences()
	{
		return (EventLineDataSequence[]) JsfUtils.getCompPropObject(this, getFacesContext(),
				"dataSequences", dataSequencesSet, dataSequences);
	}

	public void setDataSequences(EventLineDataSequence[] dataSequences)
	{
		dataSequencesSet = true;
		this.dataSequences = dataSequences;
	}

	public int getHeight()
	{
		return JsfUtils.getCompPropInt(this, getFacesContext(),
				"height", heightSet, height);
	}

	public void setHeight(int height)
	{
		heightSet = true;
		this.height = height;
	}

	public int getXSubdiv()
	{
		return JsfUtils.getCompPropInt(this, getFacesContext(),
				"xSubdiv", xSubdivSet, xSubdiv);
	}

	public void setXSubdiv(int xSubdiv)
	{
		xSubdivSet = true;
		this.xSubdiv = xSubdiv;
	}

	public int getYSubdiv()
	{
		return JsfUtils.getCompPropInt(this, getFacesContext(),
				"ySubdiv", ySubdivSet, ySubdiv);
	}

	public void setYSubdiv(int ySubdiv)
	{
		ySubdivSet = true;
		this.ySubdiv = ySubdiv;
	}

	public int getWidth()
	{
		return JsfUtils.getCompPropInt(this, getFacesContext(),
				"width", widthSet, width);
	}

	public void setWidth(int width)
	{
		widthSet = true;
		this.width = width;
	}

	public EventLineItem[] getItems()
	{
		return (EventLineItem[]) JsfUtils.getCompPropObject(this, getFacesContext(),
				"items", itemsSet, items);
	}

	public void setItems(EventLineItem[] items)
	{
		itemsSet = true;
		this.items = items;
	}

}
