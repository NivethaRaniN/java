package com.infrastructure.conrollerframework.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * This class is used to provide some safety around the request attributes. It
 * provides compile time checking of data types of stored values, and also
 * provides a mechanism to ensure correct naming of request attributes.
 */
public class HttpRequestProxy extends RequestProxy {
	// Request attribute strings.
	private HttpServletRequest request = null;
	public static final String STYLESHEET_NAME = "stylesheetName";
	public static final String XML_DOC = "xmlDoc";
	public static final String OUTPUT = "output";
	private final String COMMAND = "COMMAND";
	private VI vi = null;

	/**
	 * A unique command id.
	 */
	private String _requestId = null;

	/**
	 * The command that is beeing used by this request
	 */
	private Command _commandConfig = null;

	/**
	 * instantiates the request proxy object.
	 */
	public HttpRequestProxy(VI vi) {
		super(vi);
		this.request = ((HttpVO) vi).getRequest();

	}

	public HttpServletRequest getHttpRequest() {
		return request;
	}

	public VI getRequest() {
		return objVi;
	}

	/**
	 * Get stylesheet name. This is the base name for the family of desired
	 * stylesheets. This value is set up during the XML generation phase. An
	 * extension must still be added to this name based on the client device
	 * type. eg. stylesheet="homePage/public" but the actual stylesheet might be
	 * called "homePage/public_HTML.xsl" or "homePage/public_WML.xsl" etc
	 * depending on device.
	 *
	 * @return stylesheetName
	 */
	public String getStylesheetName() {
		return (String) request.getAttribute(STYLESHEET_NAME);
	}

	/**
	 * Set stylesheet name associated with an XML document. This is the base
	 * name of the stylesheet excluding any device specific extension.
	 * 
	 * @param stylesheet
	 */
	public void setStylesheetName(String stylesheet) {
		if (stylesheet != null)
			request.setAttribute(STYLESHEET_NAME, stylesheet);
	}

	/**
	 * Get presentation output.
	 *
	 * @return output
	 */
	public String getOutput() {
		return (String) request.getAttribute(OUTPUT);
	}

	/**
	 * Set presentation output.
	 *
	 * @param output
	 */
	public void setOutput(String output) {
		if (output != null)
			request.setAttribute(OUTPUT, output);
	}

	/*
	 * Returns the Name of the current command. This is set into the request by
	 * the infrastructure.
	 */
	public String getCommandName() {
		Command cmd = (Command) request.getAttribute(COMMAND);
		return cmd.getCommandName();
	}

	/*
	 * Retuns the Role which is required to execute the current command. This is
	 * also set by the infrastructure. The required role is specified in the
	 * IDweb_control.xml
	 */
	public Collection getCommandRole() {
		Command cmd = (Command) request.getAttribute(COMMAND);
		return cmd.getRoles();
	}

	/**
	 * API to get any object from the request attribute
	 * 
	 * @param name
	 *            the key to extract the Object from the HttpServletRequest
	 *            Object
	 */
	public Object getAttribute(String name) {
		return request.getAttribute(name);
	}

	/**
	 * API to set any object into the request attribute.
	 * 
	 * @param name
	 *            the key used for setting the Object into the
	 *            HttpServletRequest
	 * @param val
	 *            The Object to be set in the HttpServletRequest
	 */
	public void setAttribute(String name, Object val) {
		request.setAttribute(name, val);
	}

	/**
	 * API to get the Parameter from the HttpServletRequest Object
	 * 
	 * @param name
	 *            the name of the parameter in the HttpServletRequest Object
	 * @return java.lang.String the value of the parameter in the
	 *         HttpServletRequestObject
	 */
	public String getParameter(String name) {
		return request.getParameter(name);
	}

	/**
	 * API to get the ParameterValues from the HttpServletRequest Object
	 * 
	 * @param name
	 *            the name of the parameter in the HttpServletRequest Object
	 * @return java.lang.String [] the values of the parameter in the
	 *         HttpServletRequestObject
	 */
	public String[] getParameterValues(String name) {
		return request.getParameterValues(name);
	}

	/**
	 * API to get the ParameterNames from the HttpServletRequest Object
	 * 
	 * @return java.util.Enumeration. the Enumeration of the parameter names in
	 *         the HttpServletRequestObject
	 */
	public Enumeration getParameterNames() {
		return request.getParameterNames();
	}

	/**
	 * API to get the Header Value from the HttpServletRequest Object
	 * 
	 * @param name
	 *            the name of the Header in the HttpServletRequest Object
	 * @return java.lang.String the value of the header in the
	 *         HttpServletRequestObject
	 */
	public String getHeader(String name) {
		return request.getHeader(name);
	}

	/**
	 * API to get the ContentType of the Request
	 * 
	 * @return java.lang.String the contentType of the HttpServletRequestObject
	 */
	public String getContentType() {
		return request.getContentType();
	}

	/**
	 * API to get the ContentLength of the Request
	 * 
	 * @return <code>int</code> the contentLength of the
	 *         HttpServletRequestObject
	 */
	public int getContentLength() {
		return request.getContentLength();
	}

	/**
	 * API to get the InputStream to the HttpServletRequest
	 * 
	 * @return ServletInputStream the InputStream to the
	 *         HttpServletRequestObject
	 */
	public InputStream getInputStream() throws IOException {
		return request.getInputStream();
	}

	/**
	 * API to get the name of the Host
	 * 
	 * @return java.lang.String the name of the Host
	 */
	public String getServerName() {
		return request.getServerName();
	}

	/**
	 * API to get the name of the URL
	 * 
	 * @return java.lang.String the name of the URL
	 */
	public String getRequestURI() {
		return request.getRequestURI();
	}

	/**
	 * API to get the Cookie
	 * 
	 * @return java.lang.String the name of the URL
	 */
	public Cookie[] getCookies() {

		Cookie[] cookies = request.getCookies();
		return cookies;

	}

	/**
	 * API to get the name of the URL
	 * 
	 * @return java.lang.String the name of the URL
	 */
	public String getRequestURL() {
		return request.getRequestURL().toString();
	}

	public String getQueryString() {
		return request.getQueryString();
	}

	/**
	 * get the id of the request
	 * 
	 * @return a string representing the request id
	 */
	public String getRequestId() {
		return _requestId;
	}

	/**
	 * set the id of the request
	 * 
	 * @param id
	 *            representing the id
	 */
	public void setRequestId(String id) {
		_requestId = id;
	}

	/**
	 * get command of the request
	 * 
	 * @return the command configuration associated with this request
	 */
	public Command getCommandConfig() {
		return _commandConfig;
	}

	/**
	 * sets the command con figuration for this request
	 * 
	 * @param command
	 *            configuration
	 */
	public void setCommandConfig(Command command) {
		_commandConfig = command;
	}

}