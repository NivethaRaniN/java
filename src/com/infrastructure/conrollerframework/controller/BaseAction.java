
package com.infrastructure.conrollerframework.controller;

import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infrastructure.conrollerframework.data.HttpRequestProxy;
import com.infrastructure.conrollerframework.data.HttpResponseProxy;
import com.infrastructure.conrollerframework.data.VI;
import com.infrastructure.exception.BaseException;
import com.infrastructure.exception.BusinessException;
import com.infrastructure.exception.InfrastructureException;

public abstract class BaseAction {

	private static HashMap _homeList = new HashMap();
	private ServletContext _servletContext;

	/**
	 * It is the responsibility of this method to call the facade bean and
	 * execute the business logic and create the response object. The controller
	 * will invoke this method. The base class implementation provides general
	 * reflection way of invocation and sub classes are free to override this
	 * method and provide there own version of implementation.
	 * 
	 * @param request
	 *            The incoming request from the client converted to Imv Value
	 *            object .See @link ImvRequest
	 *            com.infrastructure.common.data.ImvRequest for more detailes
	 * @return the response from facade bean. See @link ImvResponse
	 *         com.infrastructure.common.data.ImvResponse for more detailes
	 * @exception InfrastructureException
	 *                in case of infrastructure exceptions. See @link
	 *                InfrastructureException
	 *                com.infrastructure.common.exception.InfrastructureException
	 *                for more detailes
	 * @exception BusinessException
	 *                in case of business exceptions. See @link
	 *                BusinessException
	 *                com.infrastructure.common.exception.BusinessException for
	 *                more detailes
	 */
	public HttpResponseProxy executeRequest(HttpRequestProxy request) throws BaseException {
		return null;
	}

	/**
	 * It is the responsibility of this method to conver the extract the request
	 * data from incoming call and transfor the same in to value object. defined
	 * as abstract all sub classes must provide implementations for this method.
	 * 
	 * @param request
	 *            Http request object from the incoming call. See
	 *            HttpServletRequest javax.servlet.http.HttpServletRequest for
	 *            more detailes
	 * @return The converted value object. See @link ImvResponse
	 *         com.infrastructure.common.data.VI for more detailes
	 * @exception InfrastructureException
	 *                in case of infrastructure exceptions. See @link
	 *                InfrastructureException
	 *                com.infrastructure.common.exception.InfrastructureException
	 *                for more detailes
	 * @exception BusinessException
	 *                in case of business exceptions. See @link
	 *                BusinessException
	 *                com.infrastructure.common.exception.BusinessException for
	 *                more detailes
	 */
	public abstract VI convertRequest(HttpServletRequest request) throws BaseException;

	/**
	 * It is the responsibility of this method to foreward the response to
	 * appropriate jsp. defined as abstract all sub classes must provide
	 * implementations for this method.
	 * 
	 * @param response
	 *            The response from facade bean
	 * @param config
	 *            servlet configuration
	 * @param servletRequest
	 *            servlet request
	 * @param servletResponse
	 *            servlet response
	 * @param servletResponse
	 *            http response object See HttpServletRequest
	 *            javax.servlet.http.HttpServletResponse for more detailes
	 * @exception InfrastructureException
	 *                in case of infrastructure exceptions. See @link
	 *                InfrastructureException
	 *                com.infrastructure.common.exception.InfrastructureException
	 *                for more detailes
	 * @exception BusinessException
	 *                in case of business exceptions. See @link
	 *                BusinessException
	 *                com.infrastructure.common.exception.BusinessException for
	 *                more detailes
	 */
	public abstract void actionForward(VI response, ServletConfig config, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws BaseException;

	/**
	 * It is the responsibility of this method to create the next view in case
	 * of error condition. The controller will invoke this method. The base
	 * class implementation provides general implementation and sub classes are
	 * free to override this method and provide there own version of
	 * implementation.
	 * 
	 * @param exception
	 *            exception object from the business logic
	 * @param servletResponse
	 *            http response object See HttpServletRequest
	 *            javax.servlet.http.HttpServletResponse for more detailes
	 * @exception InfrastructureException
	 *                in case of infrastructure exceptions. See @link
	 *                InfrastructureException
	 *                com.infrastructure.common.exception.InfrastructureException
	 *                for more detailes
	 */
	public void errorForward(BaseException exception, ServletConfig config, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws InfrastructureException {
	}

	/**
	 * The static method to do the jndi lookup and get the home interface and
	 * create the EJB Object
	 * 
	 * @param facadeName
	 *            The jndi name of the bean to look for
	 * @return the newly created remote object
	 * @exception InfrastructureException
	 *                in case of any error in getting the home interface
	 */
	/*
	 * public static Object getFacadeInterface(String facadeName) throws
	 * InfrastructureException { Object ejbHomeObj= null; try {
	 * 
	 * 
	 * ServiceLocator sl=ServiceLocator.getInstance(); ejbHome =
	 * sl.getHome(facadeName); //ejbHome = (EJBHome)
	 * PortableRemoteObject.narrow(home, home.getClass());
	 * 
	 * Method createMethod = ejbHome.getClass().getDeclaredMethod("create",
	 * null); Object ejbObject = createMethod.invoke(ejbHome, null);
	 * 
	 * System.out.println("looking up over and create a bean"); ejbHomeObj=
	 * PortableRemoteObject.narrow( ejbObject, ejbObject.getClass());
	 * 
	 * } catch (NoSuchMethodException ne) { throw new InfrastructureException(
	 * ne.toString(), ReturnCode.GENERAL_ERROR); } catch (Exception ce) { throw
	 * new InfrastructureException( ce.toString(), ReturnCode.GENERAL_ERROR); }
	 * 
	 * return ejbHomeObj; }
	 */

	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	/**
	 * Returns the Command string from the Request
	 * 
	 * @param The
	 *            HTTP request
	 * @return The name of the command
	 */
	protected String getParameterFromRequest(HttpServletRequest req, String param) {
		String cmd = param + "=";
		String cmdValue = req.getParameter(param);
		if (cmdValue == null) {
			// this is due to an bug in SaActivatorRecorder.
			String queryString = req.getQueryString();
			if (queryString != null) {
				int eqPos = queryString.indexOf(cmd);
				if (eqPos > -1) {
					eqPos += cmd.length();
					int ampPos = queryString.indexOf("&", eqPos);
					if (ampPos > -1) {
						cmdValue = queryString.substring(eqPos, ampPos);
					} else {
						cmdValue = queryString.substring(eqPos);
					}
				}
			}
		}
		return cmdValue;
	}

	protected void removeInProduction(HttpServletRequest request) {
		System.out.println("Session parameters name");
		for (java.util.Enumeration e = request.getSession().getAttributeNames(); e.hasMoreElements();) {
			try {
				Object elem = e.nextElement();
				System.out.println("Session PARAMETER NAME =" + elem + ":  VALUE ="
						+ request.getSession().getAttribute((String) elem));
			} catch (Exception ex) {
			}
		}
		System.out.println("Request parameters name");
		for (java.util.Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
			try {
				Object elem = e.nextElement();
				System.out.println(
						"REQUEST PARAMETER NAME =" + elem + ":  VALUE =" + request.getParameter((String) elem));
			} catch (Exception ex) {
			}
		}
		/* For testing */
	}
}
