package edu.emory.library.tast.images.admin;

import java.util.List;

import edu.emory.library.tast.common.LookupSource;

public class LookupSourceVoyages implements LookupSource
{

	public List allItems()
	{
		return null;
	}

	public boolean canReturnAllItems()
	{
		return false;
	}

	public int getMaxLimit()
	{
		return 100;
	}

	public List getItemsByValues(String[] ids)
	{
		return null;
	}

	public List search(String value)
	{
		return null;
	}

}
