package edu.emory.library.tast.dm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.NumericAttribute;
import edu.emory.library.tast.dm.attributes.StringAttribute;

public class Port extends Location
{
	
	private static Map attributes = new HashMap();
	static
	{
		attributes.put("id", new NumericAttribute("id", "Port", NumericAttribute.TYPE_LONG));
		attributes.put("name", new StringAttribute("name", "Port"));
		attributes.put("region", new NumericAttribute("region", "Port", NumericAttribute.TYPE_LONG));
	}

	private Region region;

	public Region getRegion()
	{
		return region;
	}

	public void setRegion(Region region)
	{
		this.region = region;
	}
	
	public static List loadAll(Session sess)
	{
		return Port.loadAll(Port.class, sess);
	}
	
	public static Port loadById(Session sess, long portId)
	{
		return (Port) Dictionary.loadById(Port.class, sess, portId);
	}

	public static Attribute getAttribute(String name)
	{
		return (Attribute)attributes.get(name);
	}

}
