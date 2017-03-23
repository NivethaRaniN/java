package com.infrastructure.conrollerframework.data;

/*Java Specific import*/
import java.io.Serializable;
import java.util.Locale;

//import org.w3c.dom.Element;

public interface VI extends Serializable {

	public static final String GETTER_PREFIX = "get";
	public static final String OUTPUT_DISPLAY_DELIMITER = ": ";
	public static final String NEW_LINE = "\n";
	/**
	 * XML TAG to represent the class information for the sub class
	 */
	public static final String IMPLEMENTATION_CLASS_XML_TAG = "class";

	/**
	 * XML TAG to represent the class information for the sub class
	 */
	public static final String OBJECT_NAME_TAG = "name";

	/**
	 * The get VO Name to return the name of the VO
	 * 
	 * @return The name of the Value Object
	 */
	public String getVOName();

	/*
	 * The XML Out put format of the the VO object
	 * 
	 * public void toXML(XMLWriter writer);
	 * 
	 * 
	 * The XML Out put format of the the VO object
	 * 
	 * public void toXML(XMLWriter writer, Locale locale);
	 */

	/*
	 * The XML Out put format of the the VO object
	 */
	public void toXML(Locale locale);

	/*
	 * The XML Out put format of the the VO object
	 */
	public void toXML();

}
