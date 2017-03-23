package com.infrastructure.conrollerframework.data;

import java.io.IOException;
import java.io.OutputStream;

//import com.infrastructure.mvc.Command;

/**
 * This class is used to provide some safety around the request attributes. It
 * provides compile time checking of data types of stored values, and also
 * provides a mechanism to ensure correct naming of request attributes.
 */
public abstract class ResponseProxy implements java.io.Serializable {
	// Request attribute strings.
	// private HttpServletRequest request = null;
	protected VI objVi = null;

	/**
	 * instantiates the request proxy object.
	 */
	/*
	 * public ResponseProxy (HttpServletRequest request ) { this.request =
	 * request; }
	 */
	public ResponseProxy(VI vi) {
		this.objVi = vi;
	}

	public abstract VI getResponse();

	/**
	 * API to set the Header Value from the HttpServletResponse Object
	 * 
	 * @param name
	 *            the name of the Header in the HttpServletResponse Object
	 * @ java.lang.String the value of the header in the
	 *   HttpServletResponseObject
	 */
	public abstract void setHeader(String name);

	/**
	 * API to set the ContentType of the Request
	 * 
	 * @ java.lang.String the contentType of the HttpServletResponseObject
	 */
	public abstract void setContentType(String cType);

	/**
	 * API to set the ContentLength of the Request
	 * 
	 * @ <code>int</code> the contentLength of the HttpServletResponseObject
	 */
	public abstract void setContentLength(int cLength);

	/**
	 * API to set the InputStream to the HttpServletResponse @
	 * ServletInputStream the InputStream to the HttpServletResponseObject
	 */
	public abstract void setOutputStream(OutputStream os) throws IOException;

	/**
	 * API to set the name of the Host
	 * 
	 * @ java.lang.void the name of the Host
	 */
	public abstract void setServerName();;

	/**
	 * API to set the name of the URL
	 * 
	 * @ java.lang.void the name of the URL
	 */
	public abstract void setRequestURI();

	/**
	 * API to set the name of the URL
	 * 
	 * @ java.lang.void the name of the URL
	 */
	public abstract void setRequestURL();

	public abstract void setQueryString();

}