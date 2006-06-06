package edu.emory.library.tas.test;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;

import edu.emory.library.tas.Dictionary;
import edu.emory.library.tas.Slave;
import edu.emory.library.tas.Voyage;
import edu.emory.library.tas.VoyageIndex;
import edu.emory.library.tas.dicts.PortLocation;
import edu.emory.library.tas.dicts.SecondDemPort;
import edu.emory.library.tas.dicts.Temp;
import edu.emory.library.tas.util.HibernateConnector;
import edu.emory.library.tas.util.query.Conditions;
import edu.emory.library.tas.util.query.QueryValue;
import edu.emory.library.tas.web.test.HtmlWriter;

public class HibernateTest {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String command = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		
		HibernateConnector connector = HibernateConnector.getConnector();

		System.out.print("command:>");
		while ((command = reader.readLine()) != null
				&& !command.equalsIgnoreCase("exit")) {

			if (command.equals("store")) {
				Voyage v = Voyage.createNew(new Long(System.currentTimeMillis()));
				v.setShipname("Ship: " + v.getVoyageId().longValue());
				v.setVoyageId(v.getVoyageId());
				
				Slave slave1 = Slave.createNew(new Long(System.currentTimeMillis()));
				Slave slave2 = Slave.createNew(new Long(System.currentTimeMillis()));
				slave1.setName("Slave 1");
				slave2.setName("Slave 2");
				HashSet slaves = new HashSet();
				slaves.add(slave1);
				slaves.add(slave2);
				v.setSlaves(slaves);
				
				v.save();
			} else if (command.equals("list")) {
				try {
//					Voyage v = new Voyage();
//					v.setVoyageId(new Long(30001));
					
					long t1 = System.currentTimeMillis();
//					VoyageIndex[] list = connector.getVoyagesIndexSet(0, 100, HibernateConnector.APPROVED_AND_NOT_APPROVED & HibernateConnector.WITHOUT_HISTORY);
//					Voyage v = Voyage.loadMostRecent(new Long(2314));
					Voyage[] list = Voyage.loadAllMostRecent(0, 100);
					
					
					long t2 = System.currentTimeMillis();
					
					System.out.println("Returned: " + list.length + " time=" + (t2-t1));
					
//					if (v != null) {
//						
////						System.out.println("Event1: " + theVoyage);
//						System.out.println("Printing!");
//						System.out.println(((Slave)v.getSlaves().iterator().next()).getName());
//						System.out.println(v.getShipname() + "---" + v.getPortdep());
//					}
//					for (int i = 0; i < list.length; i++) {
//						Voyage v = list[i].getVoyage();
//						System.out.println("Printing!");
//						System.out.println(v.getShipname() + "---" + v.getPortdep());
//					}

				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			} else if (command.startsWith("modify")) {
				Long id = new Long(command.split(" ")[1]);
				Voyage v = Voyage.loadMostRecent(id);
				if (v != null) {
					v.setShipname(v.getShipname() + "-");
					v.setModified(Voyage.UPDATED);
					v.save();
				} else {
					System.out.println("Empty result for given id - cannot update");
				}
			} else if (command.equals("dictionary")) {
				Dictionary[] dicts = Dictionary.loadDictionary("Temp");
				for (int i = 0; i < dicts.length; i++) {
					System.out.println("Dict: " + dicts[i]);
				}
			} else if (command.equals("testSaving")) {
				Temp dic = new Temp();
				dic.setRemoteId(new Integer(10));
				dic.setName(System.currentTimeMillis() + "");
				dic.save();
				Voyage v = Voyage.loadMostRecent(new Long(1));
				
				if (v == null) {
					System.out.println("No object!");
				} else {
					System.out.println("After loading: " + v);
					v.setTemp(dic);
					System.out.println("Has: " + v.getAdpsale1());
					v.save();
				}
				
				Voyage v_new = Voyage.loadMostRecent(new Long(1));
				System.out.println("After loading: " + v_new);
			} else if (command.equals("limit")) {
				try {
					
					Voyage v[] = Voyage.loadMostRecent(new Long(30001), 10);

					for (int i = 0; i < v.length; i++) {
						Voyage theVoyage = v[i];
						System.out.println(theVoyage.getVoyageId() + ": " + theVoyage);
					}

				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			} else if (command.equals("query")) {
//				Conditions cMain = new Conditions(Conditions.JOIN_AND);
//				cMain.addCondition("first", "fsfs", Conditions.OP_EQUALS);
//				cMain.addCondition("second", "ddd", Conditions.OP_LIKE);
//				cMain.addCondition("third", new Integer(11), Conditions.OP_NOT_EQUALS);
//				Conditions c1 = new Conditions(Conditions.JOIN_OR);
//				c1.addCondition("first1", "fsfs", Conditions.OP_EQUALS);
//				c1.addCondition("second1", new Integer(11), Conditions.OP_NOT_EQUALS);
//				Conditions c11 = new Conditions(Conditions.JOIN_OR);
//				c11.addCondition("first11", "fsfs", Conditions.OP_EQUALS);
//				c11.addCondition("second11", new Integer(11), Conditions.OP_NOT_EQUALS);
//				c1.addCondition(c11);
//				Conditions c2 = new Conditions(Conditions.JOIN_NOT);
//				c11.addCondition("first2", "fsfs", Conditions.OP_EQUALS);
//				Conditions c21 = new Conditions(Conditions.JOIN_AND);
//				c21.addCondition("final", new Float(2.55), Conditions.OP_SMALLER);
//				c2.addCondition(c21);
				
//				cMain.addCondition(c1);
//				cMain.addCondition(c2);
				
				
				Conditions cMain = new Conditions(Conditions.JOIN_AND);
				
//				cMain.addCondition("voyage.shipname", "Pastora de Lima", Conditions.OP_EQUALS);
//				Conditions cL1 = new Conditions(Conditions.JOIN_OR);
//				cMain.addCondition(cL1);
//				cL1.addCondition("voyage.portdep.name", "Lizbon", Conditions.OP_EQUALS);
//				cL1.addCondition("voyage.captaina", "Cunha%", Conditions.OP_LIKE);
				
				
				QueryValue qValue = new QueryValue("VoyageIndex", cMain);
//				qValue.setLimit(100);
//				qValue.addPopulatedAttribute("voyage.shipname");
//				qValue.addPopulatedAttribute("voyage.shipname");
				long t1 = System.currentTimeMillis();
				Object[] res = HibernateConnector.getConnector().loadObjects(qValue);
				long t2 = System.currentTimeMillis();
				
				System.out.println("Returned: " + res.length + " time=" + (t2-t1));
//				for (int i = 0; i < res.length && i < 20; i++) {
//					System.out.println(" -> " + ((VoyageIndex)res[i]).getVoyage().getIid() + "  " + ((VoyageIndex)res[i]).getVoyage().getShipname());
//				}
			} else if (command.equals("serv")) {
				HtmlWriter html = new HtmlWriter(new PrintWriter(new FileWriter("test")));
				
				
				long t1 = System.currentTimeMillis();
				Voyage[] voyages = Voyage.loadAllMostRecent(20000, 100);
				
				
				html.start("List");

				html.beginTable(0, 0, 0);
				html.beginTr();
				
				html.endTr();
				html.endTable();
				
				html.out.println("<br>");

				html.beginTable(0, 0, 0, "width: 100%", null);

				html.beginTr();
				html.th("ID");
				html.th("Ship");
				html.th("Captain");
				html.th("Started");
				html.th("Ended");
				html.th("Embarked");
				html.th("Disembarked");
				html.th("From");
				html.th("To");
				html.endTr();
				
				
				
				for (int i = 0; i < voyages.length; i++)
				{
					Voyage voyage = voyages[i];
					
					html.beginTr();
					html.tdWithHref(voyage.getVoyageId(), "detail?vid=" + voyage.getVoyageId());
					
					html.td(voyage.getShipname());
					html.td(voyage.getCaptaina());

					html.td(voyage.getYearaf());
					html.td(voyage.getYearam());
					
					html.td(voyage.getSlaximp());
					html.td(voyage.getSlamimp());
					
					if (voyage.getMajbuypt() != null) html.td(voyage.getMajbuypt().getName());
					if (voyage.getMajselpt() != null) html.td(voyage.getMajselpt().getName());
					
					html.endTr();
					
				}

				html.endTable();
				
				html.end();
				
				long t2 = System.currentTimeMillis();
				
				System.out.println("Returned: " + voyages.length + " time=" + (t2-t1));
				
			}
			
			System.out.print("command:>");
		}
	}

}
