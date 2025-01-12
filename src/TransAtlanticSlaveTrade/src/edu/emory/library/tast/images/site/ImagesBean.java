/*
Copyright 2010 Emory University
	
	    This file is part of Trans-Atlantic Slave Voyages.
	
	    Trans-Atlantic Slave Voyages is free software: you can redistribute it and/or modify
	    it under the terms of the GNU General Public License as published by
	    the Free Software Foundation, either version 3 of the License, or
	    (at your option) any later version.
	
	    Trans-Atlantic Slave Voyages is distributed in the hope that it will be useful,
	    but WITHOUT ANY WARRANTY; without even the implied warranty of
	    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	    GNU General Public License for more details.
	
	    You should have received a copy of the GNU General Public License
	    along with Trans-Atlantic Slave Voyages.  If not, see <http://www.gnu.org/licenses/>. 
*/
package edu.emory.library.tast.images.site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIData;
import javax.faces.model.ListDataModel;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.emory.library.tast.AppConfig;
import edu.emory.library.tast.TastResource;
import edu.emory.library.tast.common.SelectItem;
import edu.emory.library.tast.common.voyage.VoyageDetailBean;
import edu.emory.library.tast.db.HibernateConn;
import edu.emory.library.tast.dm.Image;
import edu.emory.library.tast.dm.ImageCategory;
import edu.emory.library.tast.dm.Voyage;
import edu.emory.library.tast.images.GalleryImage;
import edu.emory.library.tast.util.StringUtils;

public class ImagesBean
{

	private static final int INDEX_GALLERY_SAMPLE = 4;

	public static final int DETAIL_THUMBS_COUNT = 8;
	public static final int POPUP_EXTRA_HEIGHT = 50;
	public static final int POPUP_EXTRA_WIDTH = 30;
	public static final int DETAIL_IMAGE_WIDTH = 400;

	private ImageCategoryDescriptor categories[] = null;
	private String[] allCategoryIds = null;
	private Map categoryNames = new HashMap();

	private GalleryImage[] galleryImages;

	private ImagesQuery currentQuery;
	private ImagesQuery workingQuery;

	private String imageId;
	private String imageTitle;
	private String imageDate;
	private String imageDescription;
	private String imageUrl;
	private String imageExpandedURL;
	private List imageInfo;
	private List imageVoyagesInfo;

	private UIData linkedVoyagesTable;
	private VoyageDetailBean voyageBean;
	
	private ListDataModel homepageGallerySamples = null;

	private int selectedImageIndex;

	public ImagesBean()
	{
		resetSearchParameters();
	}

	private void resetSearchParameters()
	{

		this.currentQuery = new ImagesQuery();
		this.workingQuery = new ImagesQuery();

		ensureCategoriesLoaded();
		this.currentQuery.setCategories(this.allCategoryIds);
		this.workingQuery.setCategories(this.allCategoryIds);

		galleryImages = null;

	}

	private void ensureCategoriesLoaded()
	{

		if (categories != null)
			return;

		Session sess = HibernateConn.getSession();
		Transaction transaction = sess.beginTransaction();

		String hql =
			"select img.category.id, img.category.name, count(img) " +
			"from Image img " +
			"where img.readyToGo = true " +
			"group by img.category.id, img.category.name " +
			"order by img.category.name";

		List cats = sess.createQuery(hql).list();
		
		allCategoryIds = new String[cats.size()]; 
		categories = new ImageCategoryDescriptor[cats.size()];
		categoryNames.clear();
		
		int catIdx = 0;
		Iterator iter = cats.iterator();
		while (iter.hasNext())
		{
			Object[] row = (Object[]) iter.next();

			long catId = ((Long)row[0]).longValue();
			long imagesCount = ((Long) row[2]).longValue();
			String name = (String) row[1];

			allCategoryIds[catIdx] = String.valueOf(catId);
			categoryNames.put(new Long(catId), name);
			categories[catIdx] = new ImageCategoryDescriptor(catId, name, imagesCount);

			catIdx++;
		}

		transaction.commit();
		sess.close();

	}

	private GalleryImage[] getSample(Session sess, long catId, int size)
	{

		String hql =
			"select img.id, img.fileName, img.title, img.date " +
			"from Image img " +
			"where img.readyToGo = true and img.category.id = " + catId + " " +
			"order by img.date, img.id";

		List images = sess.createQuery(hql).setMaxResults(size).list();
		size = Math.min(size, images.size());
		GalleryImage[] ret = new GalleryImage[size];

		for (int i = 0; i < size; i++)
		{
			Object[] row = (Object[]) images.get(i);
			ret[i] = new GalleryImage(
					row[0].toString(),
					(String) row[1],
					(String) row[2],
					((Integer) row[3]).intValue() != 0 ? "(" + row[3] + ")" : null);
		}

		return ret;

	}

	private synchronized void ensureHomepageGallerySamplesLoaded()
	{
		
		if (homepageGallerySamples != null)
			return;
		
		List samples = new LinkedList();
		homepageGallerySamples = new ListDataModel(samples);
		
		Session sess = HibernateConn.getSession();
		Transaction trans = sess.beginTransaction();
		
		
			for (int i = 0; i < categories.length; i++)
			{
			ImageCategoryDescriptor cat = categories[i];
			long catId = cat.getId();
			GalleryImage[] images = getSample(sess, catId, INDEX_GALLERY_SAMPLE);
			samples.add(new GallerySample(cat.getName(), catId, images));
		}
		
		trans.commit();
		sess.close();
		
	}

	public String openCategoryFromHomepage()
	{
		
		if (homepageGallerySamples == null)
			return "images";
		
		ensureCategoriesLoaded();
		
		resetSearchParameters();
		
		ImageCategoryDescriptor cat = categories[homepageGallerySamples.getRowIndex()]; 
		workingQuery.setCategory(cat.getId());
		
		search();
		
		return "images-list";
		
	}

	public String openDetailFromHomepage()
	{
		loadDetail(imageId, null, true);
		loadList();
		return "images-detail";
	}

	public void openImageFromVoyageDetail(String imageId)
	{
		loadDetail(imageId, null, true);
		loadList();
	}

	public void openImageFromUrl(String externalId)
	{
		loadDetail(null, externalId, true);
		loadList();
	}

	public String gotoDetailFromGallery()
	{
		
		if (galleryImages == null)
			return "images";
		
		selectedImageIndex = -1;
		for (int i = 0; i < galleryImages.length; i++)
		{
			if (galleryImages[i].getId().equals(imageId))
			{
				selectedImageIndex = i;
				break;
			}
		}
		
		if (selectedImageIndex == -1)
			return "images";
		
		loadDetail(imageId, null, false);
		return "images-detail";
	}
	
	public String gotoPrev()
	{
		if (selectedImageIndex > 0)
		{
			selectedImageIndex--;
			imageId = galleryImages[selectedImageIndex].getId();
			loadDetail(imageId, null, false);
		}
		return null;
	}

	public String gotoNext()
	{
		if (selectedImageIndex < galleryImages.length - 1)
		{
			selectedImageIndex++;
			imageId = galleryImages[selectedImageIndex].getId();
			loadDetail(imageId, null, false);
		}
		return null;
	}
	
	public String gotoVoyage()
	{
		ImageLinkedVoyageInfo voyageInfo = (ImageLinkedVoyageInfo) linkedVoyagesTable.getRowData();
		voyageBean.openVoyageByIid(voyageInfo.getVoyageIid());
		voyageBean.setSelectedTab("variables");
		return "search-interface";
	}
	
	public String search()
	{
		this.currentQuery = (ImagesQuery) this.workingQuery.clone();
		loadList();
		return "images-list";
	}

	public String startAgain()
	{
		resetSearchParameters();
		return "images";
	}

	private void loadDetail(String imageId, String imageExternalId, boolean setCategory)
	{

		String imagesBaseUrl = AppConfig.getConfiguration().getString(AppConfig.IMAGES_URL);
		imagesBaseUrl = StringUtils.trimEnd(imagesBaseUrl, '/');

		Session sess = HibernateConn.getSession();
		Transaction transaction = sess.beginTransaction();

		Image img = imageId != null ?
				Image.loadById(Integer.parseInt(imageId), sess) :
					Image.loadByExternalId(imageExternalId, sess);

		if (img != null)
		{

			imageId = this.imageId = String.valueOf(img.getId());
			imageTitle = img.getTitle();
			imageDescription = img.getDescription();
			imageDate = String.valueOf(img.getDate());

			imageUrl = "../servlet/thumbnail" +
				"?i=" + img.getFileName() +
				"&w=" + DETAIL_IMAGE_WIDTH +
				"&h=0";

			imageExpandedURL = imagesBaseUrl + "/" + img.getFileName();

			if (setCategory)
			{
				ImageCategory cat = img.getCategory();
				if (cat != null)
				{
					resetSearchParameters();
					workingQuery.setCategory(cat.getId().intValue());
					currentQuery = (ImagesQuery) workingQuery.clone(); 
				}
			}

			imageInfo = new ArrayList();

			if (img.getDate() != 0)
				imageInfo.add(new ImageDetailInfo("Year:", String.valueOf(img.getDate())));

			if (!StringUtils.isNullOrEmpty(img.getCreator()))
				imageInfo.add(new ImageDetailInfo("Creator:", img.getCreator()));

			if (!StringUtils.isNullOrEmpty(img.getSource()))
				imageInfo.add(new ImageDetailInfo("Source:", img.getSource()));

			if (!StringUtils.isNullOrEmpty(img.getLanguage(), true))
				imageInfo.add(new ImageDetailInfo("Language:", img.getLanguageName()));

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
						info.append(TastResource
								.getText("images_shipname_unknown"));
					else
						info.append(voyage.getShipname());

					info.append(", ");
					if (voyage.getYearam() == null)
						info
								.append(TastResource
										.getText("images_year_unknown"));
					else
						info.append(voyage.getYearam());

					imageVoyagesInfo.add(new ImageLinkedVoyageInfo(voyage
							.getIid().longValue(), voyage.getVoyageid()
							.intValue(), info.toString()));
				}

			}

		}

		transaction.commit();
		sess.close();

	}

	private void loadList()
	{

		Session sess = HibernateConn.getSession();
		Transaction transaction = sess.beginTransaction();

		StringBuffer hqlWhere = new StringBuffer();
		int conditionsCount = 0;

		String[] keywords = StringUtils.extractQueryKeywords(currentQuery.getKeyword(), StringUtils.UPPER_CASE);
		if (keywords.length > 0)
		{
			if (conditionsCount > 0) hqlWhere.append(" and ");
			hqlWhere.append("(");
			for (int i = 0; i < keywords.length; i++)
			{
				if (i > 0) hqlWhere.append(" and ");
				hqlWhere.append("(");
				hqlWhere.append("remove_accents(upper(title)) like ");
				hqlWhere.append("remove_accents(upper(:keyword").append(i).append("))");
				hqlWhere.append(" or ");
				hqlWhere.append("remove_accents(upper(description)) like ");
				hqlWhere.append("remove_accents(upper(:keyword").append(i).append("))");
				hqlWhere.append(")");
			}
			hqlWhere.append(")");
			conditionsCount++;
		}

		String[] categories = currentQuery.getCategories();
		if (categories != null && categories.length != 0)
		{
			if (conditionsCount > 0) hqlWhere.append(" and ");
			hqlWhere.append("(");
			for (int i = 0; i < categories.length; i++)
			{
				if (i > 0) hqlWhere.append(" or ");
				hqlWhere.append("category.id = :categoryId").append(i);
			}
			hqlWhere.append(")");
			conditionsCount++;
		}

		if (currentQuery.getYearFrom() != null)
		{
			if (conditionsCount > 0) hqlWhere.append(" and ");
			hqlWhere.append("date >= :dateFrom");
			conditionsCount++;
		}

		if (currentQuery.getYearTo() != null)
		{
			if (conditionsCount > 0) hqlWhere.append(" and ");
			hqlWhere.append("date <= :dateTo");
			conditionsCount++;
		}

		if (currentQuery.getSearchPortId() != null)
		{
			if (conditionsCount > 0) hqlWhere.append(" and ");
			hqlWhere.append(":portId = some elements(ports)");
			conditionsCount++;
		}

		if (currentQuery.getSearchRegionId() != null)
		{
			if (conditionsCount > 0) hqlWhere.append(" and ");
			hqlWhere.append(":regionId = some elements(regions)");
			conditionsCount++;
		}

		// if (workingQuery.getSearchVoyageId() != null)
		// {
		// if (conditionsCount > 0) hqlWhere.append(" and ");
		// hqlWhere.append(":voyageId = some elements(voyageIds)");
		// conditionsCount++;
		// }

		if (conditionsCount > 0) hqlWhere.append(" and ");
		hqlWhere.append("readyToGo = true");
		conditionsCount++;

		StringBuffer hsql = new StringBuffer();
		hsql.append("select id, fileName, title, date from Image");
		if (hqlWhere.length() > 0)
		{
			hsql.append(" where ");
			hsql.append(hqlWhere);
		}
		hsql.append(" order by date asc, id asc");


		Query query = sess.createQuery(hsql.toString());

		for (int i = 0; i < keywords.length; i++)
			query.setParameter("keyword" + i, "%" + keywords[i] + "%");

		if (categories != null)
			for (int i = 0; i < categories.length; i++)
				query.setParameter("categoryId" + i, new Long(categories[i]));

		if (workingQuery.getYearFrom() != null)
			query.setParameter("dateFrom", workingQuery.getYearFrom());

		if (workingQuery.getYearTo() != null)
			query.setParameter("dateTo", workingQuery.getYearTo());

		if (workingQuery.getSearchPortId() != null)
			query.setParameter("portId", workingQuery.getSearchPortId());

		if (workingQuery.getSearchRegionId() != null)
			query.setParameter("regionId", workingQuery.getSearchRegionId());

		List response = query.list();
		galleryImages = new GalleryImage[response.size()];

		int imageIndex = 0;
		selectedImageIndex = -1;
		for (Iterator iter = response.iterator(); iter.hasNext();)
		{
			Object[] row = (Object[]) iter.next();
			String galleryImageId = row[0].toString();
			if (galleryImageId.equals(imageId)) selectedImageIndex = imageIndex;
			galleryImages[imageIndex++] = new GalleryImage(
					galleryImageId,
					(String) row[1],
					(String) row[2],
					((Integer) row[3]).intValue() != 0 ? "(" + row[3] + ")" : null);
		}

		transaction.commit();
		sess.close();

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
		js.append(", ");
		js.append("scrollbars=yes");
		js.append("'");

		js.append(");");

		return js.toString();

	}

	public String getListTitle()
	{

		ensureCategoriesLoaded();

		boolean oneCategory =
			currentQuery.getCategories() != null &&
			currentQuery.getCategories().length == 1;

		boolean allCategories =
			currentQuery.getCategories() != null &&
			currentQuery.getCategories().length == categories.length;

		boolean hasConditions =
			!StringUtils.isNullOrEmpty(currentQuery .getKeyword())
				|| currentQuery.getYearFrom() != null
				|| currentQuery.getYearTo() != null;

		if (allCategories && !hasConditions)
		{
			return "All images";
		}
		else if (oneCategory && !hasConditions)
		{
			Long catId = new Long(currentQuery.getCategories()[0]);
			return "Images of " + categoryNames.get(catId);
		}
		else
		{
			return "Search results";
		}

	}

	public String getDetailTitle()
	{
		if (StringUtils.isNullOrEmpty(imageDate))
		{
			return imageTitle;
		}
		else
		{
			return imageTitle + " (" + imageDate + ")";
		}
	}
	
	public String getBackFromDetailTitle()
	{
		return getListTitle(); 
	}
	
	public String getGalleryPositionIndicator()
	{
		return (selectedImageIndex + 1) + " / " + galleryImages.length;
	}

	public boolean isHasPrevImage()
	{
		return selectedImageIndex > 0;
	}
	
	public boolean isHasNextImage()
	{
		return selectedImageIndex < galleryImages.length - 1;
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

	public boolean isHasImageVoyages()
	{
		return imageVoyagesInfo != null && imageVoyagesInfo.size() != 0;
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

	public SelectItem[] getCategories()
	{
		
		ensureCategoriesLoaded();
		
		SelectItem[] categoriesUi = new SelectItem[categories.length]; 
		
		for (int i = 0; i < categories.length; i++)
		{
			
			ImageCategoryDescriptor cat = categories[i];
			String label = cat.getName();

			if (cat.getImagesCount() == 1)
				label += " " +
					"<span class=\"images-count\">" +
					"(1 image)" + "</span>";
			else
				label += " " +
					"<span class=\"images-count\">" +
					"(" + cat.getImagesCount() + " images)" + "</span>";
			
			categoriesUi[i] = new SelectItem(
					label,
					String.valueOf(cat.getId()));
			
		}
		
		return categoriesUi;
	}

	public GalleryImage[] getGalleryImages()
	{
		if (galleryImages == null) loadList();
		return galleryImages;
	}
	
	public String getGalleryImagesCount()
	{
		if (galleryImages == null) loadList();
		if (galleryImages.length == 1)
			return "1 image";
		else
			return galleryImages.length  + " images";
	}

	public int getDetailThumbsCount()
	{
		return DETAIL_THUMBS_COUNT;
	}

	public ImagesQuery getWorkingQuery()
	{
		return workingQuery;
	}

	public void setWorkingQuery(ImagesQuery workingQuery)
	{
		this.workingQuery = workingQuery;
	}

	public ListDataModel getHomepageGallerySamples()
	{
		ensureHomepageGallerySamplesLoaded();
		return homepageGallerySamples;
	}

}