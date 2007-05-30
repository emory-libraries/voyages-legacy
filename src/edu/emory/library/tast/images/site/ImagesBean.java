package edu.emory.library.tast.images.site;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIData;
import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.emory.library.tast.TastResource;
import edu.emory.library.tast.common.voyage.VoyageDetailBean;
import edu.emory.library.tast.dm.Image;
import edu.emory.library.tast.dm.ImageCategory;
import edu.emory.library.tast.dm.Voyage;
import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.specific.FunctionAttribute;
import edu.emory.library.tast.images.GalleryImage;
import edu.emory.library.tast.util.HibernateUtil;
import edu.emory.library.tast.util.StringUtils;
import edu.emory.library.tast.util.query.Conditions;
import edu.emory.library.tast.util.query.QueryValue;

public class ImagesBean
{

	private static final int POPUP_EXTRA_HEIGHT = 50;
	private static final int POPUP_EXTRA_WIDTH = 30;
	private static final int DETAIL_IMAGE_WIDTH = 600;

	private List categories = null;
	
	private GalleryImage[] galleryImages;
	
	private String searchQueryTitle;
	private String searchQueryDescription;
	private String searchQueryCategory = "-1";
	private Integer searchQueryFrom = null;
	private Integer searchQueryTo = null;
	
	private String imageId;
	private String imageTitle;
	private String imageDescription;
	private String imageUrl;
	private String imageExpandedURL;
	private List imageInfo;
	private List imageVoyagesInfo;
	
	private UIData linkedVoyagesTable;
	private VoyageDetailBean voyageBean;
	
	private void resetSearchParameters()
	{
		searchQueryTitle = "";
		searchQueryCategory = "";
		searchQueryDescription = "";
		searchQueryFrom = null;
		searchQueryTo = null;
	}
	
	private GalleryImage[] getSample(int catId, int size)
	{

		Session sess = HibernateUtil.getSession();
		Transaction transaction = sess.beginTransaction();
		
		ImageCategory cat = ImageCategory.loadById(sess, catId);
		
		Conditions cond = new Conditions();
		cond.addCondition(Image.getAttribute("category"), cat, Conditions.OP_EQUALS);
		QueryValue qValue = new QueryValue("Image", cond);
		
		qValue.setLimit(size);
		
		qValue.addPopulatedAttribute(Image.getAttribute("id"));
		qValue.addPopulatedAttribute(Image.getAttribute("fileName"));
		qValue.addPopulatedAttribute(Image.getAttribute("title"));
		qValue.addPopulatedAttribute(Image.getAttribute("date"));
		
		qValue.setOrderBy(new Attribute[] {Image.getAttribute("date"), Image.getAttribute("id")});
		qValue.setOrder(QueryValue.ORDER_ASC);

		Object[] images = (Object[])qValue.executeQuery(sess);
		size = Math.min(size, images.length);
		GalleryImage[] ret = new GalleryImage[size];

		for (int i = 0; i < size; i++)
		{
			Object[] row = (Object[])images[i];
			ret[i] = new GalleryImage(
					row[0].toString(),
					(String)row[1],
					(String)row[2],
					((Integer)row[3]).intValue() != 0 ? "(" + row[3] + ")" : null);
		}
		
		transaction.commit();
		sess.close();

		return ret;

	}
	
	public String getQueryTitle()
	{
		
		StringBuffer title = new StringBuffer();
		
		String titleImages = TastResource.getText("images_query_showing");
		String titleContaining = TastResource.getText("images_query_title_containing");
		String titleFrom = TastResource.getText("images_query_title_from");
		String titleOriginated = TastResource.getText("images_query_title_originated");
		String titleBefore = TastResource.getText("images_query_title_before");
		String titleAfter = TastResource.getText("images_query_title_after");
		
		title.append(titleImages);
		
		if (searchQueryTitle != null)
		{
			String query = searchQueryTitle.trim();
			if (searchQueryTitle.length() != 0)
			{
				title.append(" ");
				title.append(titleContaining);
				title.append(" '");
				title.append(query);
				title.append("'");
			}
		}
		
		ImageCategory cat = ImageCategory.loadById(null, Integer.parseInt(searchQueryCategory));
		if (cat != null)
		{
			title.append(" ");
			title.append(titleFrom);
			title.append(" ");
			title.append(cat.getName());
		}
		
		if (searchQueryFrom != null && searchQueryTo != null)
		{
			title.append(" ");
			title.append(titleOriginated);
			title.append(" ");
			title.append(searchQueryFrom);
			title.append(" - ");
			title.append(searchQueryTo);
		}
		else if (searchQueryFrom != null)
		{
			title.append(" ");
			title.append(titleOriginated);
			title.append(" ");
			title.append(titleAfter);
			title.append(" ");
			title.append(searchQueryFrom);
		}
		else if (searchQueryTo != null)
		{
			title.append(" ");
			title.append(titleOriginated);
			title.append(" ");
			title.append(titleBefore);
			title.append(" ");
			title.append(searchQueryTo);
		}

		return title.toString();

	}

	public GalleryImage[] getSampleVessels() {
		return this.getSample(1, 5);
	}
	
	public GalleryImage[] getSampleSlaves() {
		return this.getSample(2, 5);
	}
	
	public GalleryImage[] getSampleSlavers() {
		return this.getSample(3, 5);
	}
	
	public GalleryImage[] getSamplePorts() {
		return this.getSample(4, 5);
	}
	
	public GalleryImage[] getSampleRegions() {
		return this.getSample(5, 5);
	}

	public GalleryImage[] getSampleManuscripts() {
		return this.getSample(6, 5);
	}

	public GalleryImage[] getSamplePresentation() {
		return this.getSample(99, 5);
	}
	
	public String seeVessels() {
		resetSearchParameters();
		this.searchQueryCategory = "1";
		loadGallery();
		return "images-query";
	}

	public String seeSlaves() {
		resetSearchParameters();
		this.searchQueryCategory = "2";
		loadGallery();
		return "images-query";
	}

	public String seeSlavers() {
		resetSearchParameters();
		this.searchQueryCategory = "3";
		loadGallery();
		return "images-query";
	}
	
	public String seePorts() {
		resetSearchParameters();
		this.searchQueryCategory = "4";
		loadGallery();
		return "images-query";
	}
	
	public String seeRegions() {
		resetSearchParameters();
		this.searchQueryCategory = "5";
		loadGallery();
		return "images-query";
	}
	
	public String seeManuscripts() {
		resetSearchParameters();
		this.searchQueryCategory = "6";
		loadGallery();
		return "images-query";
	}

	public String seePresentation() {
		resetSearchParameters();
		this.searchQueryCategory = "99";
		loadGallery();
		return "images-query";
	}

	public String search()
	{
		loadGallery();
		return "images-query";
	}
	
	public String gotoDetailFromGallery()
	{
		loadDetail(false);
		return "images-detail";
	}
	
	public String gotoDetailFromHomepage()
	{
		resetSearchParameters();
		loadDetail(true);
		loadGallery();
		return "images-detail";
	}

	public String next()
	{
		if (this.galleryImages != null) {
			for (int i = 0; i < this.galleryImages.length - 1; i++) {
				if (this.galleryImages[i].getId().equals(this.imageId)) {
					this.imageId = this.galleryImages[i + 1].getId();
					loadDetail(false);
					return null;
				}
			}
		}
		return null;
	}
	
	public String prev()
	{
		if (this.galleryImages != null) {
			for (int i = 1; i < this.galleryImages.length; i++) {
				if (this.galleryImages[i].getId().equals(this.imageId)) {
					this.imageId = this.galleryImages[i - 1].getId();
					loadDetail(false);
					return null;
				}
			}
		}
		return null;
	}
	
	public String back()
	{
		return "images-back";
	}
	
	private void loadDetail(boolean setCategory)
	{
		
		Session sess = HibernateUtil.getSession();
		Transaction transaction = sess.beginTransaction();
		
		Image img = Image.loadById(Integer.parseInt(imageId), sess);

		if (img != null)
		{
			
			imageTitle = img.getTitle();
			imageDescription = img.getDescription();
			
			imageUrl = "../servlet/thumbnail" +
				"?i=" + img.getFileName() +
				"&w=" + DETAIL_IMAGE_WIDTH +
				"&h=0";
			
			imageExpandedURL = "../images-database/" +
				img.getFileName();
			
			if (setCategory)
			{
				ImageCategory cat = img.getCategory();
				if (cat != null) searchQueryCategory = String.valueOf(cat.getId().intValue());
			}
		
			imageInfo = new ArrayList();
	
			if (img.getDate() != 0)
				imageInfo.add(new ImageInfo("Date:", String.valueOf(img.getDate())));
	
			if (!StringUtils.isNullOrEmpty(img.getCreator()))
				imageInfo.add(new ImageInfo("Creator:", img.getCreator()));
				
			if (!StringUtils.isNullOrEmpty(img.getSource()))
				imageInfo.add(new ImageInfo("Source:", img.getSource()));
	
			if (!StringUtils.isNullOrEmpty(img.getLanguage(), true))
				imageInfo.add(new ImageInfo("Language:", img.getLanguageName()));
			
			imageVoyagesInfo = new ArrayList();
			
			List voyages = Voyage.loadByVoyageIds(sess, img.getVoyageIds());
			
			if (voyages != null && voyages.size() > 0)
			{
			
				StringBuffer info = new StringBuffer();
				
				for (Iterator iter = voyages.iterator(); iter.hasNext();)
				{
					
					Voyage voyage = (Voyage) iter.next();
					
					info.setLength(0);
					if (StringUtils.isNullOrEmpty(voyage.getShipname()))
						info.append(TastResource.getText("images_shipname_unknown"));
					else
						info.append(voyage.getShipname());
					
					info.append(", ");
					if (voyage.getYearam() == null)
						info.append(TastResource.getText("images_year_unknown"));
					else
						info.append(voyage.getYearam());
		
					imageVoyagesInfo.add(new ImageLinkedVoyageInfo(
							voyage.getIid().longValue(),
							voyage.getVoyageid().intValue(),
							info.toString()));
				}

			}
			

		}
		
		transaction.commit();
		sess.close();

	}
	
	private void loadGallery()
	{
		
		Session sess = HibernateUtil.getSession();
		Transaction transaction = sess.beginTransaction();
		
		Conditions conds = new Conditions();
		
		String[] keywordsTitle = StringUtils.extractQueryKeywords(this.searchQueryTitle, true);
		if (keywordsTitle.length > 0)
		{
			Conditions keywordsSubCond = new Conditions(Conditions.JOIN_AND);
			Attribute titleUpperAttr = new FunctionAttribute("upper", new Attribute[] {Image.getAttribute("title")}); 
			for (int i = 0; i < keywordsTitle.length; i++)
			{
				String keyword = "%" + keywordsTitle[i] + "%";
				keywordsSubCond.addCondition(titleUpperAttr, keyword, Conditions.OP_LIKE);
			}
			conds.addCondition(keywordsSubCond);
		}
		
		String[] keywordsDescripton = StringUtils.extractQueryKeywords(this.searchQueryDescription, true);
		if (keywordsDescripton.length > 0)
		{
			Conditions keywordsSubCond = new Conditions(Conditions.JOIN_AND);
			Attribute descUpperAttr = new FunctionAttribute("upper", new Attribute[] {Image.getAttribute("description")}); 
			for (int i = 0; i < keywordsDescripton.length; i++)
			{
				String keyword = "%" + keywordsDescripton[i] + "%";
				keywordsSubCond.addCondition(descUpperAttr, keyword, Conditions.OP_LIKE);
			}
			conds.addCondition(keywordsSubCond);
		}

		ImageCategory cat = ImageCategory.loadById(sess, Long.parseLong(this.searchQueryCategory));
		if (cat != null) conds.addCondition(Image.getAttribute("category"), cat, Conditions.OP_EQUALS);
		
		if (searchQueryFrom != null) conds.addCondition(Image.getAttribute("date"), searchQueryFrom, Conditions.OP_GREATER_OR_EQUAL);
		if (searchQueryTo != null) conds.addCondition(Image.getAttribute("date"), searchQueryTo, Conditions.OP_SMALLER_OR_EQUAL);
		
		QueryValue qValue = new QueryValue("Image", conds);

		qValue.addPopulatedAttribute(Image.getAttribute("id"));
		qValue.addPopulatedAttribute(Image.getAttribute("fileName"));
		qValue.addPopulatedAttribute(Image.getAttribute("title"));
		qValue.addPopulatedAttribute(Image.getAttribute("date"));
		
		qValue.setOrderBy(new Attribute[] {Image.getAttribute("date"), Image.getAttribute("id")});
		qValue.setOrder(QueryValue.ORDER_ASC);
		
		Object[] response = qValue.executeQuery(sess);
		galleryImages = new GalleryImage[response.length];

		for (int i = 0; i < response.length; i++)
		{
			Object[] row = (Object[]) response[i];
			galleryImages[i] = new GalleryImage(
					row[0].toString(),
					(String)row[1],
					(String)row[2],
					((Integer)row[3]).intValue() != 0 ? "(" + row[3] + ")" : null);
		}
		
		transaction.commit();
		sess.close();
		
		System.out.println("loaded = " + galleryImages.length);
		
	}
	
	public String gotoVoyage()
	{
		
		ImageLinkedVoyageInfo voyageInfo = (ImageLinkedVoyageInfo) linkedVoyagesTable.getRowData();
		
		voyageBean.openVoyage(voyageInfo.getVoyageIid());
		voyageBean.setBackPage("images-detail");
		
		return "voyage-detail";
		
	}
	
	public String getExpandJavaScript()
	{
		
		Image img = Image.loadById(Integer.parseInt(this.imageId));
		
		StringBuffer js = new StringBuffer();
		js.append("window.open(");
		
		js.append("'images-detail-expanded.faces'");
		js.append(", ");

		js.append("'imagedetail'");
		js.append(", ");
		
		js.append("'");
		js.append("width=").append(img.getWidth() + POPUP_EXTRA_WIDTH);
		js.append(", ");
		js.append("height=").append(img.getHeight() + POPUP_EXTRA_HEIGHT);
		js.append(", ");
		js.append("menubar=no");
		js.append(", ");
		js.append("resizable=yes");
		js.append(", ");
		js.append("status=no");
		js.append(", ");
		js.append("toolbar=no");
		js.append("'");

		js.append(");");

		return js.toString();
		
	}
	
	public List getCategories()
	{
		if (categories == null)
		{
			categories = new ArrayList();
			categories.add(new SelectItem("-1", TastResource.getText("images_all_categories")));
			List cats = ImageCategory.loadAll(HibernateUtil.getSession(), "id");
			Iterator iter = cats.iterator();
			while (iter.hasNext())
			{
				ImageCategory cat = (ImageCategory)iter.next();
				categories.add(new SelectItem(cat.getId() + "", cat.getName()));
			}
		}
		return categories;
	}

	public String getSearchQueryTitle()
	{
		return searchQueryTitle;
	}

	public void setSearchQueryTitle(String imageLike)
	{
		this.searchQueryTitle = imageLike;
	}

	public String getSearchQueryCategory()
	{
		return searchQueryCategory;
	}

	public void setSearchQueryCategory(String selectedCategory)
	{
		this.searchQueryCategory = selectedCategory;
	}

	public Integer getSearchQueryFrom()
	{
		return searchQueryFrom;
	}

	public void setSearchQueryFrom(Integer  from)
	{
		this.searchQueryFrom = from;
	}

	public Integer getSearchQueryTo()
	{
		return searchQueryTo;
	}

	public void setSearchQueryTo(Integer to)
	{
		this.searchQueryTo = to;
	}

	public String getImageId()
	{
		return imageId;
	}

	public void setImageId(String imageId)
	{
		this.imageId = imageId;
	}
	
	public String getImageTitle()
	{
		return imageTitle;
	}

	public String getImageDescription()
	{
		return imageDescription;
	}
	
	public String getImageURL()
	{
		return imageUrl;
	}
	
	public String getImageExpandedURL()
	{
		return imageExpandedURL;
	}

	public List getImageInfo()
	{
		return imageInfo;
	}
	
	public List getImageVoyages()
	{
		return imageVoyagesInfo;
	}

	public UIData getLinkedVoyagesTable()
	{
		return linkedVoyagesTable;
	}

	public void setLinkedVoyagesTable(UIData linkedVoyagesTable)
	{
		this.linkedVoyagesTable = linkedVoyagesTable;
	}

	public VoyageDetailBean getVoyageBean()
	{
		return voyageBean;
	}

	public void setVoyageBean(VoyageDetailBean voyageDetailBean)
	{
		this.voyageBean = voyageDetailBean;
	}

	public GalleryImage[] getGalleryImages()
	{
		return galleryImages;
	}

	public String getSearchQueryDescription()
	{
		return searchQueryDescription;
	}

	public void setSearchQueryDescription(String searchQueryOther)
	{
		this.searchQueryDescription = searchQueryOther;
	}

}