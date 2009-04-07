package edu.emory.library.tast.database.query.searchables;

import java.util.Map;

import org.hibernate.Session;

import edu.emory.library.tast.AppConfig;
import edu.emory.library.tast.database.query.QueryCondition;
import edu.emory.library.tast.database.query.QueryConditionText;
import edu.emory.library.tast.db.TastDbConditions;
import edu.emory.library.tast.dm.Voyage;
import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.specific.FunctionAttribute;
import edu.emory.library.tast.util.StringUtils;

public class SearchableAttributeSimpleText extends SearchableAttributeSimple
{
	
	private String textIndexColumn;
	
	public SearchableAttributeSimpleText(String id, String userLabel, UserCategories userCategories, Attribute[] attributes, String spssName, String listDescription, boolean inEstimates, String textIndexColumn)
	{
		super(id, userLabel, userCategories, attributes, spssName, listDescription, inEstimates);
		this.textIndexColumn = textIndexColumn;
	}

	public QueryCondition createQueryCondition()
	{
		return new QueryConditionText(getId());
	}

	public boolean addToConditions(boolean markErrors, TastDbConditions conditions, QueryCondition queryCondition)
	{
		
		// check
		if (!(queryCondition instanceof QueryConditionText))
			throw new IllegalArgumentException("expected QueryConditionText"); 
		
		// cast
		QueryConditionText queryConditionText =
			(QueryConditionText) queryCondition;

		// is empty -> no db condition
		if (!queryConditionText.isNonEmpty())
			return true;
		
		// consider only alfanum and digits
		String[] keywords = StringUtils.extractQueryKeywords(queryConditionText.getValue(), StringUtils.LOWER_CASE);
		if (keywords.length == 0)
			return false;
	
		if (AppConfig.getConfiguration().getBoolean(AppConfig.DATABASE_USE_SQL) && textIndexColumn != null)
		{
			
			conditions.addCondition(
					Voyage.getAttribute(textIndexColumn),
					StringUtils.joinNonEmpty(" & ", keywords),
					TastDbConditions.OP_TEXT_SEARCH);
			
		}
		else
		{
		
			// add keywords to query
			TastDbConditions attrCond = new TastDbConditions(TastDbConditions.OR);
			Attribute[] attributes = getAttributes();
			for (int j = 0; j < attributes.length; j++)
			{
				
				TastDbConditions keywordCond = new TastDbConditions(TastDbConditions.AND);
			
				for (int i = 0; i < keywords.length; i++)
				{
					String keyword = "%" + keywords[i] + "%";
					
					FunctionAttribute attr =
						new FunctionAttribute("remove_accents", new Attribute[] {
									new FunctionAttribute("upper", new Attribute[]{attributes[j]})});
					
					keywordCond.addCondition(attr, keyword, TastDbConditions.OP_LIKE);
	
				}
				
				attrCond.addCondition(keywordCond);
	
			}
			conditions.addCondition(attrCond);
		
		}
		
		// all OK
		return true;
	
	}

	public QueryCondition restoreFromUrl(Session session, Map params)
	{
		
		String value = StringUtils.getFirstElement((String[]) params.get(getId()));
		if (StringUtils.isNullOrEmpty(value))
			return null;
		
		QueryConditionText queryCondition = new QueryConditionText(getId());
		queryCondition.setValue(value);
		return queryCondition;

	}

}