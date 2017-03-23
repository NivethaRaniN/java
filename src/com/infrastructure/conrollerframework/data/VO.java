
/* package name */
package com.infrastructure.conrollerframework.data;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class VO implements VI {

	// We use thread local variables to store the DocumentBuilderFactory,
	// DocumentBuilder and InputSource
	// variables. They are created once per thread and when threads die, they
	// are garbage collected. The
	// use of TLS is for optimization, to prevent multiple objects to be created
	// for the same thread to multiple requests,
	// as it would overtax the garbage collector.
	private static ThreadLocal m_ThreadLocalDBF = new ThreadLocal();
	private static ThreadLocal m_ThreadLocalDB = new ThreadLocal();
	private static ThreadLocal m_ThreadLocalSource = new ThreadLocal();

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private boolean blndirty = false;

	private VI evi = null;

	private Date modDate = null;
	private Date creaDate = null;

	private String creaBy = null;
	private String modiBy = null;

	// Log4j category logger object.
	static Logger log = Logger.getLogger("com.infrastructure.valueobject.VO");

	/**
	 * xml open tag. Normally "<ValueObject name=\""
	 */
	public static final String XML_DATA_START_TAG = "ValueObject";

	/**
	 * The default constructor for the Value Object
	 */
	public VO() {
		System.out.println("VO");
	}

	/**
	 * Method to get the Value Object name Should be overritten by the sub
	 * classes. By default it uses Reflection to get the class name
	 * 
	 * @return The VO class name
	 */
	public String getVOName() {
		String name = this.getClass().getName();
		return name.substring(name.lastIndexOf(".") + 1);
	}

	/**
	 * Used to determine if value object needs updating in the persistent data
	 * store.
	 * 
	 * @return returns boolean
	 */
	public boolean isDirty() {
		return blndirty;
	}

	/**
	 * Called to set the value object's state to blndirty. A blndirty state
	 * signifies that the value object contains changes that need to be
	 * persisted into the data store.
	 */
	protected void setDirty() {
		blndirty = true;
	}

	/**
	 * Override <code>toString()</code> method to provide deep output of all
	 * bean members accessible via getter methods.
	 * 
	 * @return returns String
	 */
	/*
	 * public String toString() { Locale locale = Locale.getDefault();
	 * 
	 * StringBuffer sbfoutput = new StringBuffer(); Method[] mthdgetterMethods =
	 * this.getClass().getDeclaredMethods(); Method mthdmethod = null; String
	 * strmethodName = null;
	 * 
	 * try { int intSize = mthdgetterMethods.length; Object result = null; for
	 * (int intI = 0; intI < intSize; intI++) { mthdmethod =
	 * mthdgetterMethods[intI]; strmethodName = mthdmethod.toString(); if
	 * (strmethodName.indexOf(GETTER_PREFIX) != -1) { result =
	 * mthdmethod.invoke(this, null); sbfoutput.append(strmethodName);
	 * sbfoutput.append(OUTPUT_DISPLAY_DELIMITER); sbfoutput.append(result);
	 * sbfoutput.append(NEW_LINE); } // if
	 * (mthdmethod.toString().indexOf(GETTER_PREFIX) != -1)
	 * 
	 * } // for mthdgetterMethods } catch (IllegalAccessException objExp) {
	 * sbfoutput.append(objExp.getMessage()); } catch (IllegalArgumentException
	 * objExp) { sbfoutput.append(objExp.getMessage()); } catch
	 * (InvocationTargetException objExp) {
	 * sbfoutput.append(objExp.getMessage()); } return sbfoutput.toString(); }
	 */

	/**
	 * This method overrides the super class Object toString() method and
	 * provides the String form in the following format VOName [ attribute XML
	 * representation ]
	 * 
	 * @return returns the String representation of this object
	 */

	/*
	 * public String toString(){ XMLWriter writer = new XMLWriter(true);
	 * this.toXML(writer); return writer.toString(); }
	 */

	/**
	 * @param Locale
	 *            locale
	 * @return String
	 */
	public void toXML(Locale locale) {
		String strApplicationName = null;
		String strXML = null;
		StringBuffer strBuffXML = new StringBuffer("");
		if (locale == null) {
			locale = Locale.US;
		}

		/*
		 * Add the internationalised JS message properties files name to the XML
		 * doc
		 */
		/*
		 * strBuffXML.append("<Event>"); strBuffXML.append("<Repos>" +
		 * CommonUtil.removeSpChars(userName) + "</Repos>");
		 * strBuffXML.append("<Count>" + CommonUtil.removeSpChars(userName) +
		 * "</Count>"); strBuffXML.append("<Header>" +
		 * CommonUtil.removeSpChars(userName) + "</Header>");
		 * strBuffXML.append("</Event>"); strXML = strBuffXML.toString();
		 */

	}

	/*
	 * The XML Out put format of the the VO object
	 */
	public void toXML() {
	};

	/**
	 * @param o
	 *            object
	 * @return boolean
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;

		return false;
	}

	public void setVI(VI evi) {
		this.evi = evi;
	}

	public VI getVI() {
		return evi;
	}

	/**
	 * The method to set the values for the Value Object from XML form of data
	 * The sub classes are not expected to override this method the test system
	 * can re-create the object and bring it back to the same state after the
	 * call to this method. If any attribute contains a collection or sub child
	 * then that element will be put to string and send Ideally the following
	 * must match
	 * 
	 * @param xmlRootElement
	 *            The root element for the XML representation String xmlString =
	 *            vo.toXML() VO otherVO = new SubVO();
	 *            otherVO.fromXML(xmlString); vo.equals(otherVO) should return
	 *            true.
	 */

	/**
	 * This method will be called by the VO to set values for each element
	 * 
	 * @param attributeList
	 *            list of attributes derived from the xml input string
	 */
	public void attributesFromXML(Hashtable attributeList) {
	}

	public Document parse(String str) throws IOException, ParserConfigurationException, SAXException {
		return parse(new StringReader(str));
	}

	public Document parse(Reader in) throws IOException, ParserConfigurationException, SAXException {
		DocumentBuilder db = getDocumentBuilder();

		InputSource src = getInputSource();
		System.out.println("db" + db + "in " + in + " src" + src);
		src.setCharacterStream(in);
		return db.parse(src);
	}

	private DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory dbf = null;
		DocumentBuilder db = null;
		synchronized (this) {
			dbf = (DocumentBuilderFactory) m_ThreadLocalDBF.get();
			if (dbf == null) {
				dbf = DocumentBuilderFactory.newInstance();
				m_ThreadLocalDBF.set(dbf);
			}
			db = (DocumentBuilder) m_ThreadLocalDB.get();
			if (db == null) {
				db = dbf.newDocumentBuilder();
				m_ThreadLocalDB.set(db);
			}
		}

		return db;
	}

	private InputSource getInputSource() {
		InputSource src = null;

		synchronized (this) {
			src = (InputSource) m_ThreadLocalSource.get();
			if (src == null) {
				src = new InputSource();
				m_ThreadLocalSource.set(src);
			}

		}

		return src;
	}

	public void setModifiedDate(Date modifDate) {
		this.modDate = modifDate;
	}

	public void setCreatedDate(Date creaDate) {
		this.creaDate = creaDate;

	}

	public Date getModifiedDate() {
		return modDate;
	}

	public Date getCreatedDate() {

		return creaDate;
	}

	public void setCreateBy(String creaDate) {
		this.creaBy = creaDate;

	}

	public String getCreatedBy() {

		return creaBy;
	}

	public void setModiBy(String modiBy) {
		this.modiBy = modiBy;

	}

	public String getModiBy() {

		return modiBy;
	}

}