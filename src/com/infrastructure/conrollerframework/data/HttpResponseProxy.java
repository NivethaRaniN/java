package com.infrastructure.conrollerframework.data;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to provide some safety around the response attributes. It
 * provides compile time checking of data types of stored values, and also
 * provides a mechanism to ensure correct naming of response attributes.
 */
public class HttpResponseProxy extends ResponseProxy {
	// response attribute strings.
	private HttpServletResponse response = null;
	private VI vi = null;

	/**
	 * instantiates the response proxy object.
	 */
	public HttpResponseProxy(VI vi) {
		super(vi);
		// this.response= ((HttpVO)vi).getResponse();

	}

	public VI getResponse() {
		return objVi;
	}

	public HttpServletResponse getHttpResponse() {
		return this.response;
	}

	/**
	 * API to set the Header Value from the HttpServletResponse Object
	 * 
	 * @param name
	 *            the name of the Header in the HttpServletResponse Object
	 * @ java.lang.String the value of the header in the
	 *   HttpServletResponseObject
	 */
	public void setHeader(String name) {
		// response.setHeader (name);
	}

	/**
	 * API to set the ContentType of the Request
	 * 
	 * @ java.lang.String the contentType of the HttpServletResponseObject
	 */
	public void setContentType(String cType) {
		// response.setContentType();

	}

	/**
	 * API to set the ContentLength of the Request
	 * 
	 * @ <code>int</code> the contentLength of the HttpServletResponseObject
	 */
	public void setContentLength(int cLength) {
		// response.setContentLength();
	}

	/**
	 * API to set the InputStream to the HttpServletResponse @
	 * ServletInputStream the InputStream to the HttpServletResponseObject
	 */
	public void setOutputStream(OutputStream os) throws IOException {
		// response.setOutputStream();
	}

	/**
	 * API to set the name of the Host
	 * 
	 * @ java.lang.void the name of the Host
	 */
	public void setServerName() {
		// response.setServerName();
	}

	/**
	 * API to set the name of the URL
	 * 
	 * @ java.lang.void the name of the URL
	 */
	public void setRequestURI() {
		// response.setRequestURI();
	}

	/**
	 * API to set the Cookie
	 * 
	 * @ java.lang.void the name of the URL
	 */
	public void setCookies() {

		// Cookie[] cookies = response.setCookies();
		// cookies;

	}

	/**
	 * API to set the name of the URL
	 * 
	 * @ java.lang.void the name of the URL
	 */
	public void setRequestURL() {
		// response.setRequestURL().toString();
	}

	public void setQueryString() {
		// response.setQueryString();
	}

}