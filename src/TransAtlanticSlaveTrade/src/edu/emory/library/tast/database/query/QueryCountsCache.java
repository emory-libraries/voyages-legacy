package edu.emory.library.tast.database.query;

import java.util.HashMap;
import java.util.Map;

/* Jan Zich, 9/11/2008
 * 
 * An attempt to make cache the number of voyages for each query across all users.
 * It's not fully finished and tested yet. To make it really meaningful, it would
 * be good to make the hash values of all query conditions cached as well. Also,
 * it's questionable whether a shared cache for all users makes really sense. How
 * often may users generate the same queries?  
 */

public class QueryCountsCache
{

	private static final int MAX_QUERIES = 1000;

	private class CachedQuery
	{
		public Query query;
		public CachedQuery next;
		public CachedQuery prev;
		public int count;
	}
	
	private Map lookup = new HashMap();
	private CachedQuery listHead = null;
	private CachedQuery listTail = null;
	
	private static QueryCountsCache current = new QueryCountsCache();
	
	private QueryCountsCache()
	{
	}
	
	public static QueryCountsCache getCurrent()
	{
		return current;
	}
	
	public synchronized int getCount(Query query)
	{
		CachedQuery cachedQuery = (CachedQuery) lookup.get(query);
		if (cachedQuery != null)
		{
			moveToHead(cachedQuery);
			return cachedQuery.count;
		}
		else
		{
			return -1;
		}
	}

	public synchronized void update(Query query, int count)
	{
		
		CachedQuery cachedQuery = (CachedQuery) lookup.get(query);
		
		if (cachedQuery != null)
		{
			
			moveToHead(cachedQuery);

		}
		else
		{
		
			CachedQuery oldListHead = listHead;
			listHead = new CachedQuery();
			listHead.query = query;
			listHead.prev = null;
			listHead.next = oldListHead;
			if (oldListHead != null) oldListHead.prev = listHead;
			if (listTail == null) listTail = listHead;
			
			lookup.put(query, listHead);
		
		}
		
		if (lookup.size() > MAX_QUERIES)
		{
			listTail = listTail.prev;
			listTail.next = null;
		}
		
	}
	
	private void moveToHead(CachedQuery cachedQuery)
	{
		if (cachedQuery != listHead)
		{
			if (listTail == cachedQuery) listTail = listTail.prev; 
			if (cachedQuery.next != null) cachedQuery.next.prev = cachedQuery.prev; 
			if (cachedQuery.prev != null) cachedQuery.prev.next = cachedQuery.next;
			cachedQuery.prev = null;
			cachedQuery.next = listHead;
			if (listHead != null) listHead.prev = cachedQuery;
			listHead = cachedQuery; 
		}
	}

}
