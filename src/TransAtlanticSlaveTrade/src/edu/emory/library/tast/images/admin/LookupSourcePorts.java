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
package edu.emory.library.tast.images.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.model.SelectItem;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import edu.emory.library.tast.common.LookupSource;
import edu.emory.library.tast.db.HibernateConn;
import edu.emory.library.tast.dm.Port;
import edu.emory.library.tast.util.StringUtils;

public class LookupSourcePorts implements LookupSource
{

	public boolean canReturnAllItems()
	{
		return true;
	}

	public List allItems()
	{
		return getPorts(null);
	}

	public int getMaxLimit()
	{
		return 0;
	}
	
	private List loadPortsIntoUI(Criteria crit)
	{
		List dbPorts = crit.list();
		List uiPorts = new ArrayList(dbPorts.size());
		for (Iterator iter = dbPorts.iterator(); iter.hasNext();)
		{
			Port dbPort = (Port) iter.next();
			SelectItem item = new SelectItem();
			item.setValue(String.valueOf(dbPort.getId()));
			item.setLabel(dbPort.getRegion().getName() + " / " + dbPort.getName());
			uiPorts.add(item);
		}
		return uiPorts;
	}
	
	public List getItemsByValues(String[] idsStr)
	{

		// nothing to do
		if (idsStr == null || idsStr.length == 0)
			return new ArrayList();
		
		Long[] ids = StringUtils.parseLongArray(idsStr);

		// open db
		Session sess = HibernateConn.getSession();
		Transaction transaction = sess.beginTransaction();
		
		// load 
		Criteria crit = sess.createCriteria(Port.class).createAlias("region", "r", Criteria.LEFT_JOIN).addOrder(Order.asc("r.name")).addOrder(Order.asc("name"));
		crit.add(Restrictions.in("id", ids));
		
		// fill UI list
		List uiPorts = loadPortsIntoUI(crit);
		
		// close db
		transaction.commit();
		sess.close();
		
		// return
		return uiPorts;

	}

	public List search(String value)
	{
		return getPorts(value);
	}
	
	private List getPorts(String searchFor)
	{

		// open db
		Session sess = HibernateConn.getSession();
		Transaction transaction = sess.beginTransaction();
		
		// load all ports
		Criteria crit = sess.createCriteria(Port.class).createAlias("region", "r", Criteria.LEFT_JOIN).addOrder(Order.asc("r.name")).addOrder(Order.asc("name"));
		if (searchFor != null) crit.add(Restrictions.ilike("name", "%" + searchFor + "%"));

		// fill UI list
		List uiPorts = loadPortsIntoUI(crit);
		
		// close db
		transaction.commit();
		sess.close();
		
		// return
		return uiPorts;
		
	}

}