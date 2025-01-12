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
package edu.emory.library.tast.maintenance.experiments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import edu.emory.library.tast.util.StringUtils;

public class BuildHomemadeTextIndexes
{
	
	private static final String DB_CONN_STRING = "jdbc:postgresql://localhost/tast";
	private static final String DB_USER = "tast";
	private static final String DB_PASS = "tast";

	private static final int DB_REVISION = 1;

	private static void createIndex(Connection conn, int revision, String table, String[] columns) throws SQLException
	{
		
		System.out.print("Creating index '" + table + "' ");
		
		int totalKeywords = 0;
		int maxKeywordsPerVoyage = 0;
		
		String sqlDropTable =
			"DROP TABLE IF EXISTS " + table;
		
		Statement statementDropTable = conn.createStatement();
		statementDropTable.execute(sqlDropTable);

		String sqlCreateTable = 
			"CREATE TABLE " + table + " (" +
			"voyage_iid bigint NOT NULL, " +
			"keyword character varying(60) NOT NULL)";
		
		Statement statementCreateTable = conn.createStatement();
		statementCreateTable.execute(sqlCreateTable);
		
		String sqlInsertKeyword =
			"INSERT INTO " + table + " " +
			"(voyage_iid, keyword) " +
			"VALUES (?, ?)";
		
		PreparedStatement statementInsertKeyword = conn.prepareStatement(sqlInsertKeyword);
		
		String sqlVoyages =
			"SELECT iid, " + StringUtils.join(", ", columns) + " " +
			"FROM voyages " +
			"WHERE revision = " + revision;
		
		Statement statementVoyages = conn.createStatement();
		ResultSet rs = statementVoyages.executeQuery(sqlVoyages);
		
		Set uniqueKeywords = new TreeSet();

		int voyageIndex = 0;
		while (rs.next())
		{
			
			long voyageIid = rs.getLong(1);
			
			uniqueKeywords.clear();
			for (int i = 0; i < columns.length; i++)
			{
				String value = rs.getString(i+2);
				String[] keywords = StringUtils.extractQueryKeywords(value, StringUtils.LOWER_CASE);
				for (int j = 0; j < keywords.length; j++)
				{
					String[] substrings = StringUtils.getAllSubstrings(keywords[j]);
					for (int k = 0; k < substrings.length; k++)
					{
						uniqueKeywords.add(substrings[k]);
					}
				}
			}
			
			if (uniqueKeywords.size() > maxKeywordsPerVoyage)
			{
				maxKeywordsPerVoyage = uniqueKeywords.size(); 
			}
			
			for (Iterator iterator = uniqueKeywords.iterator(); iterator.hasNext();)
			{
				totalKeywords++;
				String keyword = (String) iterator.next();
				statementInsertKeyword.setLong(1, voyageIid);
				statementInsertKeyword.setString(2, keyword);
				statementInsertKeyword.addBatch();
			}
			
			if (voyageIndex % 1000 == 0)
			{
				System.out.print(".");
			}
			
			voyageIndex++;
			
		}
		
		statementInsertKeyword.executeBatch();

		rs.close();
		
		String sqlAddSqlIndex =
			"CREATE INDEX " + table + "_pk " + 
			"ON " + table + " (keyword)";
		
		Statement statementAddSqlIndex = conn.createStatement();
		statementAddSqlIndex.execute(sqlAddSqlIndex);
		
		System.out.println();
		System.out.println("Total number of keywords = " + totalKeywords);
		System.out.println("Max number of keywords per voyage = " + maxKeywordsPerVoyage);
		
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{

		Class.forName("org.postgresql.Driver");
		
		Connection conn = DriverManager.getConnection(DB_CONN_STRING, DB_USER, DB_PASS);
		
		createIndex(conn, DB_REVISION, "voyages_index_shipnames", new String[] {"shipname"});
		createIndex(conn, DB_REVISION, "voyages_index_captains", new String[] {"captaina", "captainb", "captainc"});
		createIndex(conn, DB_REVISION, "voyages_index_owners", new String[] {"ownera", "ownerb", "ownerc", "ownerd", "ownere", "ownerf", "ownerg", "ownerh", "owneri", "ownerj", "ownerk", "ownerl", "ownerm", "ownern", "ownero", "ownerp" });
		createIndex(conn, DB_REVISION, "voyages_index_sources", new String[] {"sourcea", "sourceb", "sourcec", "sourced", "sourcee", "sourcef", "sourceg", "sourceh", "sourcei", "sourcej", "sourcek", "sourcel", "sourcem", "sourcen", "sourceo", "sourcep", "sourceq", "sourcer" });
		
		conn.close();
		
	}

}
