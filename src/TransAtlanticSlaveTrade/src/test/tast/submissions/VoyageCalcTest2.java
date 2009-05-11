package test.tast.submissions;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.junit.Before;
import org.junit.Test;

import edu.emory.library.tast.db.HibernateConn;
import edu.emory.library.tast.dm.Fate;
import edu.emory.library.tast.dm.Nation;
import edu.emory.library.tast.dm.Port;
import edu.emory.library.tast.dm.Voyage;
import edu.emory.library.tast.submission.VoyagesCalculation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
public class VoyageCalcTest2 extends TestCase {
	
	//boolean RUN_ALL_MODE = false; 
	//boolean  RUN_SPECIFIC_MODE = true;	
	
	Session session = null;
	Voyage voyage = null;
	Transaction tran = null;
	
	@Before
	public void setUp() throws Exception {	
		if (session == null) {
			session = HibernateConn.getSession();
			tran = session.beginTransaction();
		}			
	}
	

	private void setValuesVoyage(Integer voyageId, String shipName) {
		voyage = new Voyage();
		voyage.setVoyageid(voyageId);
		voyage.setShipname(shipName);
		int revision = 1;
		voyage.setRevision(revision);		
	}
	
	private void deleteVoyage(int voyageId ) {
		Voyage vNew = Voyage.loadByVoyageId(session, voyageId);	
		if (vNew != null) {
			session.delete(vNew);		
			//tran.commit();
		}	
	}
	
	private void saveVoyage(Voyage voyage) {
		session.save(voyage);
		tran.commit();
		session.close();		
	}
	
	public VoyageCalcTest2(String testName) {
		super(testName);
	}	
	/*
	public static TestSuite suite() {
		return new VoyageCalcTest("testing").invokeTestSuite();
	}
	
	public TestSuite invokeTestSuite() {
		if (RUN_ALL_MODE) {
			return runAllTest();
		}else if (RUN_SPECIFIC_MODE) {
			return runSpecificTest(); 
		}
		else {		
			return null;
		}
	}    
	
	public TestSuite runSpecificTest() {
		//overrides so that test can be executed for localized change
		System.out.println("Running Specific Tests");
		TestSuite suite = new TestSuite(this.getClass().getName());
		//suite.addTest(new VoyageCalcTest("testCalculateImputedValueFate2"));
		//suite.addTest(new VoyageCalcTest("testCalculatePtDepImpByPortdep"));
		//suite.addTest(new VoyageCalcTest("testCalculatePtDepImpByMajselpt"));
		//suite.addTest(new VoyageCalcTest("testCalculatePtDepImpByNullValues"));	
		//suite.addTest(new VoyageCalcTest("testCalculateTslmtimp"));
		//suite.addTest(new VoyageCalcTest("testCalculateTslmtimpWithAllVars"));
		suite.addTest(new VoyageCalcTest("testTest"));
		
			
		return suite;
	}
	
	/*
	 * For all tests to run successfully, session has to be recreated each time
	 * /
	public TestSuite runAllTest() {
		System.out.println("Running All Tests");
		TestSuite suite = new TestSuite(this.getClass().getName());
		//add all testcases here
		suite.addTest(new VoyageCalcTest("testCalculateImputedValueFate2"));
		suite.addTest(new VoyageCalcTest("testCalculatePtDepImpByPortdep"));
		suite.addTest(new VoyageCalcTest("testCalculatePtDepImpByMajselpt"));
		return suite;
	}
	*/
	
	
	
	//Helper Functions
	//recode
	public void testDefValInt()
	{
	  Integer I1 =null;
	  Integer I2=10;
	  Integer result=null;
	  VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);
	  
	   result= voyageCalc.defVal(I1, 5);
	   assertEquals((Integer)result, (Integer)5);
	   
	   result= voyageCalc.defVal(I2, 1);
	   assertEquals(result, (Integer)10);
	}
	
	public void testDefValDouble()
	{
	  Double D1 =null;
	  Double D2=10.5;
	  Double result=null;
	  VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);
	  
	   result= voyageCalc.defVal(D1, 5.5);
	   assertEquals(result, 5.5);
	   
	   result= voyageCalc.defVal(D2, 1d);
	   assertEquals(result, 10.5);
	}
	
	public void testRoundLong()
	{
		Long L1=5l;
		Long L2=null;
		Long result=null;
		
		 VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);
		 
		 result=voyageCalc.round(L1);
		 assertEquals(result, (Long)5l);
		 
		 result=voyageCalc.round(L2);
		 assertNull(result);
	}
	
	public void testRoundDouble()
	{
		Double D1=5d;
		Double D2=10.5;
		Double D3=20.7;
		Double D4=20.3;
		Double D5=null;
		Double result=null;
		
		 VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);
		 
		 result=voyageCalc.round(D1);
		 assertEquals(result, (Double)5d);
		 
		 result=voyageCalc.round(D2);
		 assertEquals(result, (Double)11d);
		 
		 result=voyageCalc.round(D3);
		 assertEquals(result, (Double)21d);
		 
		 result=voyageCalc.round(D4);
		 assertEquals(result, (Double)20d);
		 
		 result=voyageCalc.round(D5);
		 assertNull(result);
	}
	
	public void testDateDiff()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		
		try {
			Date D1 = sdf.parse("2009-05-11 10:00");
			Date D2 = sdf.parse("2009-05-16 20:00");
			
			VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);
			
			Integer result = voyageCalc.dateDiff(D1, D2);
			assertEquals(result, (Integer)5);
			
			result = voyageCalc.dateDiff(D2, D1);
			assertEquals(result, new Integer("5"));
			
			result = voyageCalc.dateDiff(D1, null);
			assertNull(result);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void testRecode()
	{
		VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);
		
		//Include Endpoints
		ArrayList inputs = new ArrayList();
		inputs.add(new Integer ("10"));
		inputs.add(new Integer ("15"));
		inputs.add(new Integer ("35"));
		inputs.add(new Integer ("0"));
		
		ArrayList ranges = new ArrayList();
		ranges.add(new Integer[]{new Integer("1"), new Integer("10"), new Integer("100")});
		ranges.add(new Integer[]{new Integer("11"), new Integer("20"), new Integer("200")});
		ranges.add(new Integer[]{new Integer("21"), new Integer("35"), new Integer("300")});
		
		ArrayList results = voyageCalc.recode(inputs, ranges, true);
		
		assertEquals((Integer)results.get(0), new Integer("100"));
		assertEquals((Integer)results.get(1), new Integer("200"));
		assertEquals((Integer)results.get(2), new Integer("300"));
		assertEquals((Integer)results.get(3), new Integer("-1"));
		
		//Do Not Include Endpoints
		inputs = new ArrayList();
		inputs.add(new Integer ("10"));
		inputs.add(new Integer ("15"));
		inputs.add(new Integer ("35"));
		inputs.add(new Integer ("0"));
		
		ranges = new ArrayList();
		ranges.add(new Integer[]{new Integer("1"), new Integer("10"), new Integer("100")});
		ranges.add(new Integer[]{new Integer("11"), new Integer("20"), new Integer("200")});
		ranges.add(new Integer[]{new Integer("21"), new Integer("36"), new Integer("300")});
		
		results = voyageCalc.recode(inputs, ranges, false);
		
		assertEquals((Integer)results.get(0), new Integer("-1"));
		assertEquals((Integer)results.get(1), new Integer("200"));
		assertEquals((Integer)results.get(2), new Integer("300"));
		assertEquals((Integer)results.get(3), new Integer("-1"));
	}
	
	public void testNatinImpRet(){
		try {	
			deleteVoyage(99900);
			
			//add test specific variables in voyage object
			setValuesVoyage(new Integer(99900), "shipName_99900");
			long nat = 1;
			Nation nation = Nation.loadById(session, nat);
			voyage.setNational(nation);
			
			VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);			
			
			voyageCalc.calculateValueNatinimp();
			
			saveVoyage(voyage);
			assertEquals(voyage.getNatinimp().getId(), new Long("3"));
			
			
			voyage = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void testNatinImpNoRet(){
		try {	
			deleteVoyage(99901);
			
			//add test specific variables in voyage object
			setValuesVoyage(new Integer(99901), "shipName_99901");
			long nat = 0;
			Nation nation = Nation.loadById(session, nat);
			voyage.setNational(nation);
			
			VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);			
			
			voyageCalc.calculateValueNatinimp();
			
			saveVoyage(voyage);
			assertNull(voyage.getNatinimp());
			
			
			voyage = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testVoyageLens(){
		try {	
			deleteVoyage(99900);
			
			//add test specific variables in voyage object
			setValuesVoyage(new Integer(99900), "shipName_99900");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date datedep=sdf.parse("2009-05-11 00:00");
			Date dateland1=sdf.parse("2009-05-17 23:59");
			Date dateleftafr=sdf.parse("2009-05-27 00:00");
			
			voyage.setDatedep(datedep);
			voyage.setDateland1(dateland1);
			voyage.setDateleftafr(dateleftafr);
			
			VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);			
			
			voyageCalc.calculateValuesVoyLengths();
			
			saveVoyage(voyage);
			assertEquals(voyage.getVoy1imp(), new Integer("6"));
		    assertEquals(voyage.getVoy2imp(), new Integer("10"));
			
			
			voyage = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testVoyageLensWithNull(){
		try {	
			deleteVoyage(99901);
			
			//add test specific variables in voyage object
			setValuesVoyage(new Integer(99901), "shipName_99901");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date datedep=null;
			Date dateland1=sdf.parse("2009-05-17 23:59");
			Date dateleftafr=sdf.parse("2009-05-27 00:00");
			
			voyage.setDatedep(datedep);
			voyage.setDateland1(dateland1);
			voyage.setDateleftafr(dateleftafr);
			
			VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);			
			
			voyageCalc.calculateValuesVoyLengths();
			
			saveVoyage(voyage);
			assertNull(voyage.getVoy1imp());
		    assertEquals(voyage.getVoy2imp(), new Integer("10"));
			
			
			voyage = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testRegions1(){
		try {	
			deleteVoyage(99900);
			
			//add test specific variables in voyage object
			setValuesVoyage(new Integer(99900), "shipName_99900");
			//Input variables
			
			voyage.setPlaccons(Port.loadById(session, 10201));  //not in db
			voyage.setPlacreg(Port.loadById(session, 10299)); // in db
			voyage.setPortdep(null); 
			voyage.setPtdepimp(null);   
			voyage.setEmbport(null); 
			voyage.setEmbport2(null);
			voyage.setPlac1tra(null); 
			voyage.setPlac2tra(null);
			voyage.setPlac3tra(null);
			voyage.setMajbuypt(null);
			voyage.setMjbyptimp(null);
			voyage.setArrport(null);
			voyage.setArrport2(null);
			voyage.setSla1port(null);
			voyage.setAdpsale1(null);
			voyage.setAdpsale2(null);
			voyage.setMajselpt(null);
			voyage.setMjslptimp(null);
			voyage.setPortret(null);
			
			VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);			
			
			voyageCalc.calculateValuesRegion1();
			
			saveVoyage(voyage);
			assertNull(voyage.getConstreg());
			assertEquals(voyage.getRegisreg().getId(), new Long("10200"));
			
			
			voyage = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testRegions2(){
		try {	
			deleteVoyage(99900);
			
			//add test specific variables in voyage object
			setValuesVoyage(new Integer(99900), "shipName_99900");
			
			//Input variables for second region calculation
			voyage.setPortdep(Port.loadById(session, 10101));  
			voyage.setPtdepimp(Port.loadById(session, 41202)); //look-up not in DB 
			voyage.setMjbyptimp(null); 
			voyage.setMjslptimp(null);  
			voyage.setPortret(null);
			
			VoyagesCalculation voyageCalc = new VoyagesCalculation(voyage, session);			
			
			voyageCalc.calculateValuesRegion2();
			
			saveVoyage(voyage);
			assertNull(voyage.getDeptreg1()); //look-up not in DB
			assertNull(voyage.getDeptregimp1()); //look-up not in DB
			
			
			voyage = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
