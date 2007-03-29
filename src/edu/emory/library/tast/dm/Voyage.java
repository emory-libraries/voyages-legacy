package edu.emory.library.tast.dm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import edu.emory.library.tast.dm.attributes.Attribute;
import edu.emory.library.tast.dm.attributes.BooleanAttribute;
import edu.emory.library.tast.dm.attributes.DateAttribute;
import edu.emory.library.tast.dm.attributes.EstimatesExportRegionAttribute;
import edu.emory.library.tast.dm.attributes.EstimatesImportRegionAttribute;
import edu.emory.library.tast.dm.attributes.EstimatesNationAttribute;
import edu.emory.library.tast.dm.attributes.FateAttribute;
import edu.emory.library.tast.dm.attributes.FateOwnerAttribute;
import edu.emory.library.tast.dm.attributes.FateSlavesAttribute;
import edu.emory.library.tast.dm.attributes.FateVesselAttribute;
import edu.emory.library.tast.dm.attributes.ImportableAttribute;
import edu.emory.library.tast.dm.attributes.ResistanceAttribute;
import edu.emory.library.tast.dm.attributes.NationAttribute;
import edu.emory.library.tast.dm.attributes.NumericAttribute;
import edu.emory.library.tast.dm.attributes.PortAttribute;
import edu.emory.library.tast.dm.attributes.RegionAttribute;
import edu.emory.library.tast.dm.attributes.StringAttribute;
import edu.emory.library.tast.dm.attributes.VesselRigAttribute;
import edu.emory.library.tast.util.HibernateConnector;
import edu.emory.library.tast.util.HibernateUtil;

/**
 * Voyage object.
 * 
 */
public class Voyage extends AbstractDescriptiveObject {

	/**
	 * ID of voyage.
	 */
	private Long iid;

	/**
	 * Object's attributes
	 */
	private static List attributes = new ArrayList();
	static {
		attributes.add(new NumericAttribute("iid", "Voyage",
				NumericAttribute.TYPE_LONG, null));
		attributes.add(new NumericAttribute("voyageid", "Voyage",
				NumericAttribute.TYPE_LONG, "voyageid"));
		attributes.add(new BooleanAttribute("cd", "Voyage", "cd"));
		attributes.add(new StringAttribute("shipname", "Voyage", "shipname"));
		attributes.add(new PortAttribute("placcons", "Voyage", "placcons"));
		attributes.add(new NumericAttribute("yrcons", "Voyage",
				NumericAttribute.TYPE_INTEGER, "yrcons"));
		attributes.add(new PortAttribute("placreg", "Voyage", "placreg"));
		attributes.add(new NumericAttribute("yrreg", "Voyage",
				NumericAttribute.TYPE_INTEGER, "yrreg"));
		attributes.add(new NationAttribute("natinimp", "Voyage", "natinimp"));
		attributes.add(new VesselRigAttribute("rig", "Voyage", "rig"));
		attributes.add(new NumericAttribute("tonnage", "Voyage",
				NumericAttribute.TYPE_INTEGER, "tonnage"));
		attributes.add(new NumericAttribute("tonmod", "Voyage",
				NumericAttribute.TYPE_FLOAT, "tonmod"));
		attributes.add(new NumericAttribute("guns", "Voyage",
				NumericAttribute.TYPE_INTEGER, "guns"));
		attributes.add(new StringAttribute("ownera", "Voyage", "ownera"));
		attributes.add(new StringAttribute("ownerb", "Voyage", "ownerb"));
		attributes.add(new StringAttribute("ownerc", "Voyage", "ownerc"));
		attributes.add(new StringAttribute("ownerd", "Voyage", "ownerd"));
		attributes.add(new StringAttribute("ownere", "Voyage", "ownere"));
		attributes.add(new StringAttribute("ownerf", "Voyage", "ownerf"));
		attributes.add(new StringAttribute("ownerg", "Voyage", "ownerg"));
		attributes.add(new StringAttribute("ownerh", "Voyage", "ownerh"));
		attributes.add(new StringAttribute("owneri", "Voyage", "owneri"));
		attributes.add(new StringAttribute("ownerj", "Voyage", "ownerj"));
		attributes.add(new StringAttribute("ownerk", "Voyage", "ownerk"));
		attributes.add(new StringAttribute("ownerl", "Voyage", "ownerl"));
		attributes.add(new StringAttribute("ownerm", "Voyage", "ownerm"));
		attributes.add(new StringAttribute("ownern", "Voyage", "ownern"));
		attributes.add(new StringAttribute("ownero", "Voyage", "ownero"));
		attributes.add(new StringAttribute("ownerp", "Voyage", "ownerp"));
		attributes.add(new FateAttribute("fate", "Voyage", "fate"));
		attributes.add(new FateSlavesAttribute("fate2", "Voyage", "fate2"));
		attributes.add(new FateVesselAttribute("fate3", "Voyage", "fate3"));
		attributes.add(new FateOwnerAttribute("fate4", "Voyage", "fate4"));
		attributes.add(new ResistanceAttribute("resistance", "Voyage",
				"resistance"));
		attributes.add(new PortAttribute("ptdepimp", "Voyage", "ptdepimp"));
		attributes
				.add(new RegionAttribute("deptregimp", "Voyage", "deptregimp"));
		attributes.add(new PortAttribute("plac1tra", "Voyage", "plac1tra"));
		attributes.add(new PortAttribute("plac2tra", "Voyage", "plac2tra"));
		attributes.add(new PortAttribute("plac3tra", "Voyage", "plac3tra"));
		attributes.add(new PortAttribute("mjbyptimp", "Voyage", "mjbyptimp"));
		attributes.add(new RegionAttribute("regem1", "Voyage", "regem1"));
		attributes.add(new RegionAttribute("regem2", "Voyage", "regem2"));
		attributes.add(new RegionAttribute("regem3", "Voyage", "regem3"));
		attributes.add(new RegionAttribute("majbyimp", "Voyage", "majbyimp"));
		attributes.add(new PortAttribute("npafttra", "Voyage", "npafttra"));
		attributes.add(new PortAttribute("sla1port", "Voyage", "sla1port"));
		attributes.add(new PortAttribute("adpsale1", "Voyage", "adpsale1"));
		attributes.add(new PortAttribute("adpsale2", "Voyage", "adpsale2"));
		attributes.add(new PortAttribute("mjslptimp", "Voyage", "mjslptimp"));
		attributes.add(new PortAttribute("portdep", "Voyage", "portdep"));
		attributes.add(new RegionAttribute("regdis1", "Voyage", "regdis1"));
		attributes.add(new RegionAttribute("regdis2", "Voyage", "regdis2"));
		attributes.add(new RegionAttribute("regdis3", "Voyage", "regdis3"));
		attributes.add(new RegionAttribute("mjselimp", "Voyage", "mjselimp"));
		attributes.add(new PortAttribute("portret", "Voyage", "portret"));
		attributes.add(new RegionAttribute("retrnreg", "Voyage", "retrnreg"));
		attributes.add(new NumericAttribute("yearam", "Voyage",
				NumericAttribute.TYPE_INTEGER, "yearam"));
		attributes.add(new DateAttribute("datedep", "Voyage", "date_dep"));
		attributes.add(new DateAttribute("datebuy", "Voyage", "date_buy"));
		attributes.add(new DateAttribute("dateleftafr", "Voyage",
				"date_leftafr"));
		attributes.add(new DateAttribute("dateland1", "Voyage", "date_land1"));
		attributes.add(new DateAttribute("dateland2", "Voyage", "date_land2"));
		attributes.add(new DateAttribute("dateland3", "Voyage", "date_land3"));
		attributes.add(new DateAttribute("datedepam", "Voyage", "date_depam"));
		attributes.add(new DateAttribute("dateend", "Voyage", "date_end"));
		attributes.add(new NumericAttribute("voy1imp", "Voyage",
				NumericAttribute.TYPE_INTEGER, "voy1imp"));
		attributes.add(new NumericAttribute("voy2imp", "Voyage",
				NumericAttribute.TYPE_INTEGER, "voy2imp"));
		attributes.add(new StringAttribute("captaina", "Voyage", "captaina"));
		attributes.add(new StringAttribute("captainb", "Voyage", "captainb"));
		attributes.add(new StringAttribute("captainc", "Voyage", "captainc"));
		attributes.add(new NumericAttribute("crew1", "Voyage",
				NumericAttribute.TYPE_INTEGER, "crew1"));
		attributes.add(new NumericAttribute("crew3", "Voyage",
				NumericAttribute.TYPE_INTEGER, "crew3"));
		attributes.add(new NumericAttribute("crewdied", "Voyage",
				NumericAttribute.TYPE_INTEGER, "crewdied"));
		attributes.add(new NumericAttribute("slintend", "Voyage",
				NumericAttribute.TYPE_INTEGER, "slintend"));
		attributes.add(new NumericAttribute("ncar13", "Voyage",
				NumericAttribute.TYPE_INTEGER, "ncar13"));
		attributes.add(new NumericAttribute("ncar15", "Voyage",
				NumericAttribute.TYPE_INTEGER, "ncar15"));
		attributes.add(new NumericAttribute("ncar17", "Voyage",
				NumericAttribute.TYPE_INTEGER, "ncar17"));
		attributes.add(new NumericAttribute("tslavesd", "Voyage",
				NumericAttribute.TYPE_INTEGER, "tslavesd"));
		attributes.add(new NumericAttribute("slaarriv", "Voyage",
				NumericAttribute.TYPE_INTEGER, "slaarriv"));
		attributes.add(new NumericAttribute("slas32", "Voyage",
				NumericAttribute.TYPE_INTEGER, "slas32"));
		attributes.add(new NumericAttribute("slas36", "Voyage",
				NumericAttribute.TYPE_INTEGER, "slas36"));
		attributes.add(new NumericAttribute("slas39", "Voyage",
				NumericAttribute.TYPE_INTEGER, "slas39"));
		attributes.add(new NumericAttribute("slaximp", "Voyage",
				NumericAttribute.TYPE_INTEGER, "slaximp"));
		attributes.add(new NumericAttribute("slamimp", "Voyage",
				NumericAttribute.TYPE_INTEGER, "slamimp"));
		attributes.add(new NumericAttribute("menrat7", "Voyage",
				NumericAttribute.TYPE_FLOAT, "menrat7"));
		attributes.add(new NumericAttribute("womrat7", "Voyage",
				NumericAttribute.TYPE_FLOAT, "womrat7"));
		attributes.add(new NumericAttribute("boyrat7", "Voyage",
				NumericAttribute.TYPE_FLOAT, "boyrat7"));
		attributes.add(new NumericAttribute("girlrat7", "Voyage",
				NumericAttribute.TYPE_FLOAT, "girlrat7"));
		attributes.add(new NumericAttribute("malrat7", "Voyage",
				NumericAttribute.TYPE_FLOAT, "malrat7"));
		attributes.add(new NumericAttribute("chilrat7", "Voyage",
				NumericAttribute.TYPE_FLOAT, "chilrat7"));
		attributes.add(new NumericAttribute("jamcaspr", "Voyage",
				NumericAttribute.TYPE_FLOAT, "jamcaspr"));
		attributes.add(new NumericAttribute("vymrtimp", "Voyage",
				NumericAttribute.TYPE_INTEGER, "vymrtimp"));
		attributes.add(new NumericAttribute("vymrtrat", "Voyage",
				NumericAttribute.TYPE_FLOAT, "vymrtrat"));
		attributes.add(new StringAttribute("sourcea", "Voyage", "sourcea"));
		attributes.add(new StringAttribute("sourceb", "Voyage", "sourceb"));
		attributes.add(new StringAttribute("sourcec", "Voyage", "sourcec"));
		attributes.add(new StringAttribute("sourced", "Voyage", "sourced"));
		attributes.add(new StringAttribute("sourcee", "Voyage", "sourcee"));
		attributes.add(new StringAttribute("sourcef", "Voyage", "sourcef"));
		attributes.add(new StringAttribute("sourceg", "Voyage", "sourceg"));
		attributes.add(new StringAttribute("sourceh", "Voyage", "sourceh"));
		attributes.add(new StringAttribute("sourcei", "Voyage", "sourcei"));
		attributes.add(new StringAttribute("sourcej", "Voyage", "sourcej"));
		attributes.add(new StringAttribute("sourcek", "Voyage", "sourcek"));
		attributes.add(new StringAttribute("sourcel", "Voyage", "sourcel"));
		attributes.add(new StringAttribute("sourcem", "Voyage", "sourcem"));
		attributes.add(new StringAttribute("sourcen", "Voyage", "sourcen"));
		attributes.add(new StringAttribute("sourceo", "Voyage", "sourceo"));
		attributes.add(new StringAttribute("sourcep", "Voyage", "sourcep"));
		attributes.add(new StringAttribute("sourceq", "Voyage", "sourceq"));
		attributes.add(new StringAttribute("sourcer", "Voyage", "sourcer"));
		attributes.add(new EstimatesNationAttribute("e_natinimp", "Voyage",
				"e_natinimp"));
		attributes.add(new EstimatesExportRegionAttribute("e_majbyimp",
				"Voyage", "e_majbyimp"));
		attributes.add(new EstimatesImportRegionAttribute("e_mjselimp",
				"Voyage", "e_mjselimp"));
	}

	/**
	 * Gets all attributes of voyage.
	 * 
	 * @return
	 */
	public static ImportableAttribute[] getAttributes() {
		return (ImportableAttribute[]) attributes
				.toArray(new ImportableAttribute[] {});
	}

	/**
	 * Gets attribute with given name.
	 * 
	 * @param name
	 * @return attribute, null if there is no attribute with given name
	 */
	public static Attribute getAttribute(String name) {
		for (int i = 0; i < attributes.size(); i++) {
			if (((Attribute) attributes.get(i)).getName().equals(name)) {
				return (Attribute) attributes.get(i);
			}
		}
		return null;
	}

	/**
	 * Gets names of all attributes in voyage.
	 * 
	 * @return
	 */
	public static String[] getAllAttrNames() {
		String[] attrsName = new String[attributes.size()];
		for (int i = 0; i < attrsName.length; i++) {
			attrsName[i] = ((Attribute) attributes.get(i)).getName();
		}
		return attrsName;
	}

	/**
	 * Creates new Voyage. Object will have new ID.
	 */
	public Voyage() {
	}

	/**
	 * 
	 * Loads voyage
	 * 
	 * @param voyage
	 *            voyage providing voyage ID
	 * @param option
	 *            option
	 * @return loaded voyage
	 */
	private static Voyage loadInternal(Voyage voyage, int option) {
		Voyage localVoyage = null;

		Session session = HibernateUtil.getSession();

		// Load voyage from DB
		VoyageIndex[] voyageIndex = HibernateConnector.getConnector()
				.getVoyageIndexByVoyage(session, voyage, option);

		// Prepare result
		if (voyageIndex.length != 0) {
			localVoyage = voyageIndex[0].getVoyage();
		}
		session.close();

		return localVoyage;
	}

	/**
	 * Loads Active (most recent active) voyage with given ID.
	 * 
	 * @param voyageId
	 *            voyuage ID
	 * @return voyage object, null if there is no desired Voyage in DB
	 */
	public static Voyage loadActive(Long voyageId) {
		Voyage localVoyage = new Voyage();
		localVoyage.setVoyageid(voyageId);
		return loadInternal(localVoyage, HibernateConnector.APPROVED
				& HibernateConnector.WITHOUT_HISTORY);
	}

	/**
	 * Loads most recent (not necessary active) voyage with given ID.
	 * 
	 * @param voyageId
	 *            voyuage ID
	 * @return voyage object, null if there is no desired Voyage in DB
	 */
	public static Voyage loadMostRecent(Long voyageId) {
		Voyage localVoyage = new Voyage();
		localVoyage.setVoyageid(voyageId);
		return loadInternal(localVoyage,
				HibernateConnector.APPROVED_AND_NOT_APPROVED
						& HibernateConnector.WITHOUT_HISTORY);
	}

	/**
	 * Loads most recent (not necessary active) voyage with given ID.
	 * 
	 * @param voyageId
	 *            voyuage ID
	 * @return voyage object, null if there is no desired Voyage in DB
	 */
	public static Voyage[] loadMostRecent(Long voyageId, int p_packetSize) {
		Voyage localVoyage = new Voyage();
		localVoyage.setVoyageid(voyageId);

		Session session = HibernateUtil.getSession();
		// Load voyage from DB
		VoyageIndex[] voyageIndex = HibernateConnector.getConnector()
				.getVoyagesIndexSet(
						session,
						localVoyage,
						p_packetSize,
						HibernateConnector.APPROVED_AND_NOT_APPROVED
								& HibernateConnector.WITHOUT_HISTORY);

		Voyage[] ret = new Voyage[voyageIndex.length];
		// Prepare result
		for (int i = 0; i < voyageIndex.length; i++) {
			ret[i] = voyageIndex[i].getVoyage();
		}
		session.close();
		return ret;
	}

	/**
	 * Loads a most reecnt voyages.
	 * 
	 * @param p_firstResult
	 *            first result
	 * @param p_fetchSize
	 *            suze of package
	 * @return
	 */
	public static Voyage[] loadAllMostRecent(int p_firstResult, int p_fetchSize) {

		Session session = HibernateUtil.getSession();
		// Load voyage from DB
		VoyageIndex[] voyageIndex = HibernateConnector.getConnector()
				.getVoyagesIndexSet(
						session,
						p_firstResult,
						p_fetchSize,
						HibernateConnector.APPROVED_AND_NOT_APPROVED
								& HibernateConnector.WITHOUT_HISTORY);

		Voyage[] ret = new Voyage[voyageIndex.length];
		// Prepare result
		for (int i = 0; i < voyageIndex.length; i++) {
			ret[i] = voyageIndex[i].getVoyage();

		}
		session.close();
		return ret;
	}

	/**
	 * Loads voyage with given ID and given revision ID.
	 * 
	 * @param voyageId
	 *            voyuage ID
	 * @return voyage object, null if there is no desired Voyage in DB
	 */
	public static Voyage loadByRevision(Long voyageId, Long revisionId) {
		Voyage localVoyage = new Voyage();
		localVoyage.setVoyageid(voyageId);
		return loadInternal(localVoyage, HibernateConnector.WITHOUT_HISTORY);
	}

	/**
	 * List all revis
	 * 
	 * @param voyageId
	 * @param approved
	 * @return
	 */
	public static List loadAllRevisions(Long voyageId, int p_option) {
		int option = p_option | HibernateConnector.WITH_HISTORY;
		Voyage localVoyage = new Voyage();
		localVoyage.setVoyageid(voyageId);

		Session session = HibernateUtil.getSession();
		// Load info from DB
		VoyageIndex[] voyageIndex = HibernateConnector.getConnector()
				.getVoyageIndexByVoyage(session, localVoyage, option);
		List list = new ArrayList();
		// Prepare result
		for (int i = 0; i < voyageIndex.length; i++) {
			Voyage v = voyageIndex[i].getVoyage();
			list.add(v);
		}
		session.close();
		// Return result
		return list;
	}

	/**
	 * Saves voyage to DB.
	 * 
	 */
	public void save() {
		// Prepare VoyageIndex
		VoyageIndex vIndex = new VoyageIndex();
		vIndex.setVoyage(this);
		vIndex.setVoyageId(this.getVoyageid());
		vIndex.setRevisionDate(new Date(System.currentTimeMillis()));

		// Save to DB (or update...)
		HibernateConnector.getConnector().createVoyage(vIndex);
	}

	/**
	 * Gets deep copy of object.
	 */
	public Object clone() {
		// Copy voyage itself
		Voyage newVoyage = new Voyage();

		newVoyage.values = this.values;

		// Return copy object
		return newVoyage;
	}

	public void setIid(Long iid) {
		this.iid = iid;
	}

	public Long getIid() {
		return this.iid;
	}

	/**
	 * Returns string representation of object.
	 */
	public String toString() {
		return "Voyage: " + values;
	}

	public void saveOrUpdate() {
		HibernateConnector.getConnector().saveOrUpdateObject(this);
	}

	public void saveOrUpdate(Session sess) {
		sess.saveOrUpdate(this);
	}

	public void setVoyageid(Long voyageId) {
		this.values.put("voyageid", voyageId);
	}

	public void setCd(Boolean cd) {
		this.values.put("cd", cd);
	}

	public void setSlas32(Integer slas32) {
		this.values.put("slas32", slas32);
	}

	public void setMjslptimp(Port mjslptimp) {
		this.values.put("mjslptimp", mjslptimp);
	}

	public Port getMjslptimp() {
		return (Port) this.values.get("mjslptimp");
	}

	public void setShipname(String shipname) {
		this.values.put("shipname", shipname);
	}

	public void setCaptaina(String captaina) {
		this.values.put("captaina", captaina);
	}

	public void setCaptainb(String captainb) {
		this.values.put("captainb", captainb);
	}

	public void setCaptainc(String captainc) {
		this.values.put("captainc", captainc);
	}

	public void setDatedep(Date datedep) {
		this.values.put("datedep", datedep);
	}

	public void setTslavesd(Integer tslavesd) {
		this.values.put("tslavesd", tslavesd);
	}

	public void setSlaarriv(Integer slaarriv) {
		this.values.put("slaarriv", slaarriv);
	}

	public void setSlas36(Integer slas36) {
		this.values.put("slas36", slas36);
	}

	public void setSlas39(Integer slas39) {
		this.values.put("slas39", slas39);
	}

	public void setFate(Fate fate) {
		this.values.put("fate", fate);
	}

	public void setSourcea(String sourcea) {
		this.values.put("sourcea", sourcea);
	}

	public void setSourceb(String sourceb) {
		this.values.put("sourceb", sourceb);
	}

	public void setSourcec(String sourcec) {
		this.values.put("sourcec", sourcec);
	}

	public void setSourced(String sourced) {
		this.values.put("sourced", sourced);
	}

	public void setSourcee(String sourcee) {
		this.values.put("sourcee", sourcee);
	}

	public void setSourcef(String sourcef) {
		this.values.put("sourcef", sourcef);
	}

	public void setSourceg(String sourceg) {
		this.values.put("sourceg", sourceg);
	}

	public void setSourceh(String sourceh) {
		this.values.put("sourceh", sourceh);
	}

	public void setSourcei(String sourcei) {
		this.values.put("sourcei", sourcei);
	}

	public void setSourcej(String sourcej) {
		this.values.put("sourcej", sourcej);
	}

	public void setSourcek(String sourcek) {
		this.values.put("sourcek", sourcek);
	}

	public void setSourcel(String sourcel) {
		this.values.put("sourcel", sourcel);
	}

	public void setSourcem(String sourcem) {
		this.values.put("sourcem", sourcem);
	}

	public void setSourcen(String sourcen) {
		this.values.put("sourcen", sourcen);
	}

	public void setSourceo(String sourceo) {
		this.values.put("sourceo", sourceo);
	}

	public void setSourcep(String sourcep) {
		this.values.put("sourcep", sourcep);
	}

	public void setSourceq(String sourceq) {
		this.values.put("sourceq", sourceq);
	}

	public void setSourcer(String sourcer) {
		this.values.put("sourcer", sourcer);
	}

	public void setSlintend(Integer slintend) {
		this.values.put("slintend", slintend);
	}

	public void setTonnage(Integer tonnage) {
		this.values.put("tonnage", tonnage);
	}

	public void setCrewdied(Integer crewdied) {
		this.values.put("crewdied", crewdied);
	}

	public void setNcar13(Integer ncar13) {
		this.values.put("ncar13", ncar13);
	}

	public void setNcar15(Integer ncar15) {
		this.values.put("ncar15", ncar15);
	}

	public void setNcar17(Integer ncar17) {
		this.values.put("ncar17", ncar17);
	}

	public void setGuns(Integer guns) {
		this.values.put("guns", guns);
	}

	public void setCrew1(Integer crew1) {
		this.values.put("crew1", crew1);
	}

	public void setYrreg(Integer yrreg) {
		this.values.put("yrreg", yrreg);
	}

	public void setCrew3(Integer crew3) {
		this.values.put("crew3", crew3);
	}

	public void setResistance(Resistance resistance) {
		this.values.put("resistance", resistance);
	}

	public void setPtdepimp(Port ptdepimp) {
		this.values.put("ptdepimp", ptdepimp);
	}

	public void setOwnera(String ownera) {
		this.values.put("ownera", ownera);
	}

	public void setOwnerb(String ownerb) {
		this.values.put("ownerb", ownerb);
	}

	public void setOwnerc(String ownerc) {
		this.values.put("ownerc", ownerc);
	}

	public void setOwnerd(String ownerd) {
		this.values.put("ownerd", ownerd);
	}

	public void setOwnere(String ownere) {
		this.values.put("ownere", ownere);
	}

	public void setOwnerf(String ownerf) {
		this.values.put("ownerf", ownerf);
	}

	public void setOwnerg(String ownerg) {
		this.values.put("ownerg", ownerg);
	}

	public void setOwnerh(String ownerh) {
		this.values.put("ownerh", ownerh);
	}

	public void setOwneri(String owneri) {
		this.values.put("owneri", owneri);
	}

	public void setOwnerj(String ownerj) {
		this.values.put("ownerj", ownerj);
	}

	public void setOwnerk(String ownerk) {
		this.values.put("ownerk", ownerk);
	}

	public void setOwnerl(String ownerl) {
		this.values.put("ownerl", ownerl);
	}

	public void setOwnerm(String ownerm) {
		this.values.put("ownerm", ownerm);
	}

	public void setOwnern(String ownern) {
		this.values.put("ownern", ownern);
	}

	public void setOwnero(String ownero) {
		this.values.put("ownero", ownero);
	}

	public void setOwnerp(String ownerp) {
		this.values.put("ownerp", ownerp);
	}

	public void setYearam(Integer yearam) {
		this.values.put("yearam", yearam);
	}

	public void setTonmod(Float tonmod) {
		this.values.put("tonmod", tonmod);
	}

	public void setVymrtimp(Integer vymrtimp) {
		this.values.put("vymrtimp", vymrtimp);
	}

	public void setVymrtrat(Float vymrtrat) {
		this.values.put("vymrtrat", vymrtrat);
	}

	public void setSlaximp(Integer slaximp) {
		this.values.put("slaximp", slaximp);
	}

	public void setSlamimp(Integer slamimp) {
		this.values.put("slamimp", slamimp);
	}

	public void setVoy2imp(Integer voy2imp) {
		this.values.put("voy2imp", voy2imp);
	}

	public void setVoy1imp(Integer voy1imp) {
		this.values.put("voy1imp", voy1imp);
	}

	public void setMalrat7(Float malrat7) {
		this.values.put("malrat7", malrat7);
	}

	public void setChilrat7(Float chilrat7) {
		this.values.put("chilrat7", chilrat7);
	}

	public void setWomrat7(Float womrat7) {
		this.values.put("womrat7", womrat7);
	}

	public void setMenrat7(Float menrat7) {
		this.values.put("menrat7", menrat7);
	}

	public void setGirlrat7(Float girlrat7) {
		this.values.put("girlrat7", girlrat7);
	}

	public void setBoyrat7(Float boyrat7) {
		this.values.put("boyrat7", boyrat7);
	}

	public void setJamcaspr(Float jamcaspr) {
		this.values.put("jamcaspr", jamcaspr);
	}

	public void setPlac1tra(Port plac1tra) {
		this.values.put("plac1tra", plac1tra);
	}

	public void setPlac2tra(Port plac2tra) {
		this.values.put("plac2tra", plac2tra);
	}

	public void setPlac3tra(Port plac3tra) {
		this.values.put("plac3tra", plac3tra);
	}

	public void setNpafttra(Port npafttra) {
		this.values.put("npafttra", npafttra);
	}

	public void setSla1port(Port sla1port) {
		this.values.put("sla1port", sla1port);
	}

	public void setAdpsale1(Port adpsale1) {
		this.values.put("adpsale1", adpsale1);
	}

	public void setAdpsale2(Port adpsale2) {
		this.values.put("adpsale2", adpsale2);
	}

	public void setPortret(Port portret) {
		this.values.put("portret", portret);
	}

	public void setRig(VesselRig rig) {
		this.values.put("rig", rig);
	}

	public void setPlaccons(Port placcons) {
		this.values.put("placcons", placcons);
	}

	public void setPlacreg(Port placreg) {
		this.values.put("placreg", placreg);
	}

	public void setNatinimp(Nation natinimp) {
		this.values.put("natinimp", natinimp);
	}

	public void setRetrnreg(Region retrnreg) {
		this.values.put("retrnreg", retrnreg);
	}

	public void setRegem1(Region regem1) {
		this.values.put("regem1", regem1);
	}

	public void setRegem2(Region regem2) {
		this.values.put("regem2", regem2);
	}

	public void setRegem3(Region regem3) {
		this.values.put("regem3", regem3);
	}

	public void setMajbyimp(Region majbyimp) {
		this.values.put("majbyimp", majbyimp);
	}

	public void setRegdis1(Region regdis1) {
		this.values.put("regdis1", regdis1);
	}

	public void setRegdis2(Region regdis2) {
		this.values.put("regdis2", regdis2);
	}

	public void setRegdis3(Region regdis3) {
		this.values.put("regdis3", regdis3);
	}

	public void setFate2(FateSlaves fate2) {
		this.values.put("fate2", fate2);
	}

	public void setFate3(FateVessel fate3) {
		this.values.put("fate3", fate3);
	}

	public void setFate4(FateOwner fate4) {
		this.values.put("fate4", fate4);
	}

	public void setMjselimp(Region mjselimp) {
		this.values.put("mjselimp", mjselimp);
	}

	public void setMjbyptimp(Port purchasePort) {
		this.values.put("mjbyptimp", purchasePort);
	}

	public void setYrcons(Integer obj) {
		this.values.put("yrcons", obj);
	}

	public void setDatebuy(Date obj) {
		this.values.put("datebuy", obj);
	}

	public void setDatedepam(Date obj) {
		this.values.put("datedepam", obj);
	}

	public void setDateend(Date obj) {
		this.values.put("dateend", obj);
	}

	public void setDateland1(Date obj) {
		this.values.put("dateland1", obj);
	}

	public void setDateland2(Date obj) {
		this.values.put("dateland2", obj);
	}

	public void setDateland3(Date obj) {
		this.values.put("dateland3", obj);
	}

	public void setDateleftafr(Date obj) {
		this.values.put("dateleftafr", obj);
	}

	public void setDeptregimp(Region obj) {
		this.values.put("deptregimp", obj);
	}

	public void setE_majbyimp(EstimatesExportRegion obj) {
		this.values.put("e_majbyimp", obj);
	}

	public void setE_mjselimp(EstimatesImportRegion obj) {
		this.values.put("e_mjselimp", obj);
	}

	public void setE_natinimp(EstimatesNation obj) {
		this.values.put("e_natinimp", obj);
	}

	public void setPortdep(Port obj) {
		this.values.put("portdep", obj);
	}

	public Port getPortdep() {
		return (Port) this.values.get("portdep");
	}

	public Port getSla1port() {
		return (Port) this.values.get("sla1port");
	}

	public Integer getYrcons() {
		return (Integer) this.values.get("yrcons");
	}

	public Date getDatebuy() {
		return (Date) this.values.get("datebuy");
	}

	public Date getDatedepam() {
		return (Date) this.values.get("datedepam");
	}

	public Date getDateend() {
		return (Date) this.values.get("dateend");
	}

	public Date getDateland1() {
		return (Date) this.values.get("dateland1");
	}

	public Date getDateland2() {
		return (Date) this.values.get("dateland2");
	}

	public Date getDateland3() {
		return (Date) this.values.get("dateland3");
	}

	public Date getDateleftafr() {
		return (Date) this.values.get("dateleftafr");
	}

	public Region getDeptregimp() {
		return (Region) this.values.get("deptregimp");
	}

	public EstimatesExportRegion getE_majbyimp() {
		return (EstimatesExportRegion) this.values.get("e_majbyimp");
	}

	public EstimatesImportRegion getE_mjselimp() {
		return (EstimatesImportRegion) this.values.get("e_mjselimp");
	}

	public EstimatesNation getE_natinimp() {
		return (EstimatesNation) this.values.get("e_natinimp");
	}

	public Long getVoyageid() {
		return (Long) this.values.get("voyageid");
	}

	public Boolean getCd() {
		return (Boolean) this.values.get("cd");
	}

	public String getShipname() {
		return (String) this.values.get("shipname");
	}

	public String getCaptaina() {
		return (String) this.values.get("captaina");
	}

	public String getCaptainb() {
		return (String) this.values.get("captainb");
	}

	public String getCaptainc() {
		return (String) this.values.get("captainc");
	}

	public Date getDatedep() {
		return (Date) this.values.get("datedep");
	}

	public Integer getTslavesd() {
		return (Integer) this.values.get("tslavesd");
	}

	public Integer getSlaarriv() {
		return (Integer) this.values.get("slaarriv");
	}

	public Integer getSlas32() {
		return (Integer) this.values.get("slas32");
	}

	public Integer getSlas36() {
		return (Integer) this.values.get("slas36");
	}

	public Integer getSlas39() {
		return (Integer) this.values.get("slas39");
	}

	public Fate getFate() {
		return (Fate) this.values.get("fate");
	}

	public String getSourcea() {
		return (String) this.values.get("sourcea");
	}

	public String getSourceb() {
		return (String) this.values.get("sourceb");
	}

	public String getSourcec() {
		return (String) this.values.get("sourcec");
	}

	public String getSourced() {
		return (String) this.values.get("sourced");
	}

	public String getSourcee() {
		return (String) this.values.get("sourcee");
	}

	public String getSourcef() {
		return (String) this.values.get("sourcef");
	}

	public String getSourceg() {
		return (String) this.values.get("sourceg");
	}

	public String getSourceh() {
		return (String) this.values.get("sourceh");
	}

	public String getSourcei() {
		return (String) this.values.get("sourcei");
	}

	public String getSourcej() {
		return (String) this.values.get("sourcej");
	}

	public String getSourcek() {
		return (String) this.values.get("sourcek");
	}

	public String getSourcel() {
		return (String) this.values.get("sourcel");
	}

	public String getSourcem() {
		return (String) this.values.get("sourcem");
	}

	public String getSourcen() {
		return (String) this.values.get("sourcen");
	}

	public String getSourceo() {
		return (String) this.values.get("sourceo");
	}

	public String getSourcep() {
		return (String) this.values.get("sourcep");
	}

	public String getSourceq() {
		return (String) this.values.get("sourceq");
	}

	public String getSourcer() {
		return (String) this.values.get("sourcer");
	}

	public Integer getSlintend() {
		return (Integer) this.values.get("slintend");
	}

	public Integer getTonnage() {
		return (Integer) this.values.get("tonnage");
	}

	public Integer getCrewdied() {
		return (Integer) this.values.get("crewdied");
	}

	public Integer getNcar13() {
		return (Integer) this.values.get("ncar13");
	}

	public Integer getNcar15() {
		return (Integer) this.values.get("ncar15");
	}

	public Integer getNcar17() {
		return (Integer) this.values.get("ncar17");
	}

	public Integer getGuns() {
		return (Integer) this.values.get("guns");
	}

	public Integer getCrew1() {
		return (Integer) this.values.get("crew1");
	}

	public Integer getYrreg() {
		return (Integer) this.values.get("yrreg");
	}

	public Integer getCrew3() {
		return (Integer) this.values.get("crew3");
	}

	public Resistance getResistance() {
		return (Resistance) this.values.get("resistance");
	}

	public Port getPtdepimp() {
		return (Port) this.values.get("ptdepimp");
	}

	public String getOwnera() {
		return (String) this.values.get("ownera");
	}

	public String getOwnerb() {
		return (String) this.values.get("ownerb");
	}

	public String getOwnerc() {
		return (String) this.values.get("ownerc");
	}

	public String getOwnerd() {
		return (String) this.values.get("ownerd");
	}

	public String getOwnere() {
		return (String) this.values.get("ownere");
	}

	public String getOwnerf() {
		return (String) this.values.get("ownerf");
	}

	public String getOwnerg() {
		return (String) this.values.get("ownerg");
	}

	public String getOwnerh() {
		return (String) this.values.get("ownerh");
	}

	public String getOwneri() {
		return (String) this.values.get("owneri");
	}

	public String getOwnerj() {
		return (String) this.values.get("ownerj");
	}

	public String getOwnerk() {
		return (String) this.values.get("ownerk");
	}

	public String getOwnerl() {
		return (String) this.values.get("ownerl");
	}

	public String getOwnerm() {
		return (String) this.values.get("ownerm");
	}

	public String getOwnern() {
		return (String) this.values.get("ownern");
	}

	public String getOwnero() {
		return (String) this.values.get("ownero");
	}

	public String getOwnerp() {
		return (String) this.values.get("ownerp");
	}

	public Integer getYearam() {
		return (Integer) this.values.get("yearam");
	}

	public Float getTonmod() {
		return (Float) this.values.get("tonmod");
	}

	public Integer getVymrtimp() {
		return (Integer) this.values.get("vymrtimp");
	}

	public Float getVymrtrat() {
		return (Float) this.values.get("vymrtrat");
	}

	public Integer getSlaximp() {
		return (Integer) this.values.get("slaximp");
	}

	public Integer getSlamimp() {
		return (Integer) this.values.get("slamimp");
	}

	public Integer getVoy2imp() {
		return (Integer) this.values.get("voy2imp");
	}

	public Integer getVoy1imp() {
		return (Integer) this.values.get("voy1imp");
	}

	public Float getMalrat7() {
		return (Float) this.values.get("malrat7");
	}

	public Float getChilrat7() {
		return (Float) this.values.get("chilrat7");
	}

	public Float getWomrat7() {
		return (Float) this.values.get("womrat7");
	}

	public Float getMenrat7() {
		return (Float) this.values.get("menrat7");
	}

	public Float getGirlrat7() {
		return (Float) this.values.get("girlrat7");
	}

	public Float getBoyrat7() {
		return (Float) this.values.get("boyrat7");
	}

	public Float getJamcaspr() {
		return (Float) this.values.get("jamcaspr");
	}

	public Port getPlac1tra() {
		return (Port) this.values.get("plac1tra");
	}

	public Port getPlac2tra() {
		return (Port) this.values.get("plac2tra");
	}

	public Port getPlac3tra() {
		return (Port) this.values.get("plac3tra");
	}

	public Port getNpafttra() {
		return (Port) this.values.get("npafttra");
	}

	public Port getAdpsale1() {
		return (Port) this.values.get("adpsale1");
	}

	public Port getAdpsale2() {
		return (Port) this.values.get("adpsale2");
	}

	public Port getPortret() {
		return (Port) this.values.get("portret");
	}

	public VesselRig getRig() {
		return (VesselRig) this.values.get("rig");
	}

	public Port getPlaccons() {
		return (Port) this.values.get("placcons");
	}

	public Port getPlacreg() {
		return (Port) this.values.get("placreg");
	}

	public Nation getNatinimp() {
		return (Nation) this.values.get("natinimp");
	}

	public Region getRetrnreg() {
		return (Region) this.values.get("retrnreg");
	}

	public Region getRegem1() {
		return (Region) this.values.get("regem1");
	}

	public Region getRegem2() {
		return (Region) this.values.get("regem2");
	}

	public Region getRegem3() {
		return (Region) this.values.get("regem3");
	}

	public Region getMajbyimp() {
		return (Region) this.values.get("majbyimp");
	}

	public Region getRegdis1() {
		return (Region) this.values.get("regdis1");
	}

	public Region getRegdis2() {
		return (Region) this.values.get("regdis2");
	}

	public Region getRegdis3() {
		return (Region) this.values.get("regdis3");
	}

	public FateSlaves getFate2() {
		return (FateSlaves) this.values.get("fate2");
	}

	public FateVessel getFate3() {
		return (FateVessel) this.values.get("fate3");
	}

	public FateOwner getFate4() {
		return (FateOwner) this.values.get("fate4");
	}

	public Region getMjselimp() {
		return (Region) this.values.get("mjselimp");
	}

	public Port getMjbyptimp() {
		return (Port) this.values.get("mjbyptimp");
	}

	public Port getEmbport() {
		return (Port) this.values.get("embport");
	}

	public Port getArrport() {
		return (Port) this.values.get("arrport");
	}

	public Port getEmbport2() {
		return (Port) this.values.get("embport2");
	}

	public Port getArrport2() {
		return (Port) this.values.get("arrport2");
	}

	public Integer getTontype() {
		return (Integer) this.values.get("tontype");
	}

	public Integer getSladamer() {
		return (Integer) this.values.get("sladamer");
	}

	public Integer getSaild1() {
		return (Integer) this.values.get("saild1");
	}

	public Integer getSaild2() {
		return (Integer) this.values.get("saild2");
	}

	public Integer getSaild3() {
		return (Integer) this.values.get("saild3");
	}

	public Integer getSaild4() {
		return (Integer) this.values.get("saild4");
	}

	public Integer getSaild5() {
		return (Integer) this.values.get("saild5");
	}

	public Integer getVoyage() {
		return (Integer) this.values.get("voyage");
	}

	public Integer getChild2() {
		return (Integer) this.values.get("child2");
	}

	public Integer getChild3() {
		return (Integer) this.values.get("child3");
	}

	public Integer getCrew4() {
		return (Integer) this.values.get("crew4");
	}

	public Integer getCrew5() {
		return (Integer) this.values.get("crew5");
	}

	public Integer getAdult1() {
		return (Integer) this.values.get("adult1");
	}

	public Integer getChild1() {
		return (Integer) this.values.get("child1");
	}

	public Integer getFemale1() {
		return (Integer) this.values.get("female1");
	}

	public Integer getMale1() {
		return (Integer) this.values.get("male1");
	}

	public Integer getMen1() {
		return (Integer) this.values.get("men1");
	}

	public Integer getWomen1() {
		return (Integer) this.values.get("women1");
	}

	public Integer getBoy1() {
		return (Integer) this.values.get("boy1");
	}

	public Integer getGirl1() {
		return (Integer) this.values.get("girl1");
	}

	public Integer getAdult2() {
		return (Integer) this.values.get("adult2");
	}

	public Integer getFemale2() {
		return (Integer) this.values.get("female2");
	}

	public Integer getMale2() {
		return (Integer) this.values.get("male2");
	}

	public Integer getMen2() {
		return (Integer) this.values.get("men2");
	}

	public Integer getWomen2() {
		return (Integer) this.values.get("women2");
	}

	public Integer getBoy2() {
		return (Integer) this.values.get("boy2");
	}

	public Integer getGirl2() {
		return (Integer) this.values.get("girl2");
	}

	public Integer getAdult3() {
		return (Integer) this.values.get("adult3");
	}

	public Integer getFemale3() {
		return (Integer) this.values.get("female3");
	}

	public Integer getMale3() {
		return (Integer) this.values.get("male3");
	}

	public Integer getMen3() {
		return (Integer) this.values.get("men3");
	}

	public Integer getWomen3() {
		return (Integer) this.values.get("women3");
	}

	public Integer getBoy3() {
		return (Integer) this.values.get("boy3");
	}

	public Integer getGirl3() {
		return (Integer) this.values.get("girl3");
	}

	public Integer getChild4() {
		return (Integer) this.values.get("child4");
	}

	public Integer getFemale4() {
		return (Integer) this.values.get("female4");
	}

	public Integer getMale4() {
		return (Integer) this.values.get("male4");
	}

	public Integer getMen4() {
		return (Integer) this.values.get("men4");
	}

	public Integer getWomen4() {
		return (Integer) this.values.get("women4");
	}

	public Integer getBoy4() {
		return (Integer) this.values.get("boy4");
	}

	public Integer getGirl4() {
		return (Integer) this.values.get("girl4");
	}

	public Integer getChild6() {
		return (Integer) this.values.get("child6");
	}

	public Integer getFemale6() {
		return (Integer) this.values.get("female6");
	}

	public Integer getMale6() {
		return (Integer) this.values.get("male6");
	}

	public Integer getMen6() {
		return (Integer) this.values.get("men6");
	}

	public Integer getWomen6() {
		return (Integer) this.values.get("women6");
	}

	public Integer getBoy6() {
		return (Integer) this.values.get("boy6");
	}

	public Integer getGirl6() {
		return (Integer) this.values.get("girl6");
	}

	public Integer getCrew2() {
		return (Integer) this.values.get("crew2");
	}

	public Integer getInfantm3() {
		return (Integer) this.values.get("infantm3");
	}

	public Integer getInfantf3() {
		return (Integer) this.values.get("infantf3");
	}

	public Integer getSladied1() {
		return (Integer) this.values.get("sladied1");
	}

	public Integer getSladied2() {
		return (Integer) this.values.get("sladied2");
	}

	public Integer getSladied3() {
		return (Integer) this.values.get("sladied3");
	}

	public Integer getSladied4() {
		return (Integer) this.values.get("sladied4");
	}

	public Integer getSladied5() {
		return (Integer) this.values.get("sladied5");
	}

	public Integer getSladied6() {
		return (Integer) this.values.get("sladied6");
	}

	public Integer getInsurrec() {
		return (Integer) this.values.get("insurrec");
	}

	public Integer getEvgreen() {
		return (Integer) this.values.get("evgreen");
	}

	public Integer getFemale5() {
		return (Integer) this.values.get("female5");
	}

	public Integer getMale5() {
		return (Integer) this.values.get("male5");
	}

	public Integer getChild5() {
		return (Integer) this.values.get("child5");
	}

	public Integer getMen5() {
		return (Integer) this.values.get("men5");
	}

	public Integer getWomen5() {
		return (Integer) this.values.get("women5");
	}

	public Integer getBoy5() {
		return (Integer) this.values.get("boy5");
	}

	public Integer getGirl5() {
		return (Integer) this.values.get("girl5");
	}

	public Integer getInfant3() {
		return (Integer) this.values.get("infant3");
	}

	public Integer getInfant1() {
		return (Integer) this.values.get("infant1");
	}

	public Integer getAdult5() {
		return (Integer) this.values.get("adult5");
	}

	public Integer getAdult4() {
		return (Integer) this.values.get("adult4");
	}

	public Integer getInfant4() {
		return (Integer) this.values.get("infant4");
	}

	public Integer getCrew() {
		return (Integer) this.values.get("crew");
	}

	public void setEmbport(Port val) {
		this.values.put("embport", val);
	}

	public void setArrport(Port val) {
		this.values.put("arrport", val);
	}

	public void setEmbport2(Port val) {
		this.values.put("embport2", val);
	}

	public void setArrport2(Port val) {
		this.values.put("arrport2", val);
	}

	public void setTontype(Integer val) {
		this.values.put("tontype", val);
	}

	public void setSladamer(Integer val) {
		this.values.put("sladamer", val);
	}

	public void setSaild1(Integer val) {
		this.values.put("saild1", val);
	}

	public void setSaild2(Integer val) {
		this.values.put("saild2", val);
	}

	public void setSaild3(Integer val) {
		this.values.put("saild3", val);
	}

	public void setSaild4(Integer val) {
		this.values.put("saild4", val);
	}

	public void setSaild5(Integer val) {
		this.values.put("saild5", val);
	}

	public void setVoyage(Integer val) {
		this.values.put("voyage", val);
	}

	public void setChild2(Integer val) {
		this.values.put("child2", val);
	}

	public void setChild3(Integer val) {
		this.values.put("child3", val);
	}

	public void setCrew4(Integer val) {
		this.values.put("crew4", val);
	}

	public void setCrew5(Integer val) {
		this.values.put("crew5", val);
	}

	public void setAdult1(Integer val) {
		this.values.put("adult1", val);
	}

	public void setChild1(Integer val) {
		this.values.put("child1", val);
	}

	public void setFemale1(Integer val) {
		this.values.put("female1", val);
	}

	public void setMale1(Integer val) {
		this.values.put("male1", val);
	}

	public void setMen1(Integer val) {
		this.values.put("men1", val);
	}

	public void setWomen1(Integer val) {
		this.values.put("women1", val);
	}

	public void setBoy1(Integer val) {
		this.values.put("boy1", val);
	}

	public void setGirl1(Integer val) {
		this.values.put("girl1", val);
	}

	public void setAdult2(Integer val) {
		this.values.put("adult2", val);
	}

	public void setFemale2(Integer val) {
		this.values.put("female2", val);
	}

	public void setMale2(Integer val) {
		this.values.put("male2", val);
	}

	public void setMen2(Integer val) {
		this.values.put("men2", val);
	}

	public void setWomen2(Integer val) {
		this.values.put("women2", val);
	}

	public void setBoy2(Integer val) {
		this.values.put("boy2", val);
	}

	public void setGirl2(Integer val) {
		this.values.put("girl2", val);
	}

	public void setAdult3(Integer val) {
		this.values.put("adult3", val);
	}

	public void setFemale3(Integer val) {
		this.values.put("female3", val);
	}

	public void setMale3(Integer val) {
		this.values.put("male3", val);
	}

	public void setMen3(Integer val) {
		this.values.put("men3", val);
	}

	public void setWomen3(Integer val) {
		this.values.put("women3", val);
	}

	public void setBoy3(Integer val) {
		this.values.put("boy3", val);
	}

	public void setGirl3(Integer val) {
		this.values.put("girl3", val);
	}

	public void setChild4(Integer val) {
		this.values.put("child4", val);
	}
	
	public void setChild5(Integer val) {
		this.values.put("child4", val);
	}

	public void setFemale4(Integer val) {
		this.values.put("female4", val);
	}

	public void setMale4(Integer val) {
		this.values.put("male4", val);
	}

	public void setMen4(Integer val) {
		this.values.put("men4", val);
	}

	public void setWomen4(Integer val) {
		this.values.put("women4", val);
	}

	public void setBoy4(Integer val) {
		this.values.put("boy4", val);
	}

	public void setGirl4(Integer val) {
		this.values.put("girl4", val);
	}

	public void setChild6(Integer val) {
		this.values.put("child6", val);
	}

	public void setFemale6(Integer val) {
		this.values.put("female6", val);
	}

	public void setMale6(Integer val) {
		this.values.put("male6", val);
	}

	public void setMen6(Integer val) {
		this.values.put("men6", val);
	}

	public void setWomen6(Integer val) {
		this.values.put("women6", val);
	}

	public void setBoy6(Integer val) {
		this.values.put("boy6", val);
	}

	public void setGirl6(Integer val) {
		this.values.put("girl6", val);
	}

	public void setCrew2(Integer val) {
		this.values.put("crew2", val);
	}

	public void setinfantm3(Integer val) {
		this.values.put("infantm3", val);
	}

	public void setInfantf3(Integer val) {
		this.values.put("infantf3", val);
	}

	public void setSladied1(Integer val) {
		this.values.put("sladied1", val);
	}

	public void setSladied2(Integer val) {
		this.values.put("sladied2", val);
	}

	public void setSladied3(Integer val) {
		this.values.put("sladied3", val);
	}

	public void setSladied4(Integer val) {
		this.values.put("sladied4", val);
	}

	public void setSladied5(Integer val) {
		this.values.put("sladied5", val);
	}

	public void setSladied6(Integer val) {
		this.values.put("sladied6", val);
	}

	public void setInsurrec(Integer val) {
		this.values.put("insurrec", val);
	}

	public void setEvgreen(Integer val) {
		this.values.put("evgreen", val);
	}

	public void setFemale5(Integer val) {
		this.values.put("female5", val);
	}

	public void setMale5(Integer val) {
		this.values.put("male5", val);
	}

	public void setSChild5(Integer val) {
		this.values.put("child5", val);
	}

	public void setMen5(Integer val) {
		this.values.put("men5", val);
	}

	public void setWomen5(Integer val) {
		this.values.put("women5", val);
	}

	public void setBoy5(Integer val) {
		this.values.put("boy5", val);
	}

	public void setGirl5(Integer val) {
		this.values.put("girl5", val);
	}

	public void setInfant3(Integer val) {
		this.values.put("infant3", val);
	}

	public void setInfant1(Integer val) {
		this.values.put("infant1", val);
	}

	public void setAdult5(Integer val) {
		this.values.put("adult5", val);
	}

	public void setAdult4(Integer val) {
		this.values.put("adult4", val);
	}

	public void setInfant4(Integer val) {
		this.values.put("infant4", val);
	}

	public void setCrew(Integer val) {
		this.values.put("crew", val);
	}
}
