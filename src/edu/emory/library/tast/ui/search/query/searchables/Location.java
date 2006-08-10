package edu.emory.library.tast.ui.search.query.searchables;

import edu.emory.library.tast.dm.attributes.Attribute;

public class Location
{
	
	private Attribute port;
	private Attribute region;
	
	public Location(Attribute port, Attribute region)
	{
		super();
		this.port = port;
		this.region = region;
	}

	public Attribute getPort()
	{
		return port;
	}

	public Attribute getRegion()
	{
		return region;
	}
	
}