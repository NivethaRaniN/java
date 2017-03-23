
package com.infrastructure.conrollerframework.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

import com.infrastructure.conrollerframework.data.Command;
import com.infrastructure.conrollerframework.data.CommandReader;
import com.infrastructure.conrollerframework.data.Constants;
import com.infrastructure.conrollerframework.data.HttpRequestProxy;
import com.infrastructure.conrollerframework.data.HttpResponseProxy;
import com.infrastructure.conrollerframework.data.VI;
import com.infrastructure.conrollerframework.session.UserSession;
import com.infrastructure.exception.BaseException;
import com.infrastructure.exception.BusinessException;
import com.infrastructure.exception.InfrastructureException;
import com.infrastructure.conrollerframework.session.*;
import com.infrastructure.conrollerframework.data.*;


/**
 * MVC controlling servlet. This servlet acts as a coordinator for all requests
 * coming into the Portal. Requests must contain a "command" identifier to
 * execute. The Controlling Servlet will map this command to an action, and
 * check authorisation before performing this action.
 * 
 */


@WebServlet(name="ControllerServlet",urlPatterns={"/ControllerServlet"})
public class ControllerServlet extends javax.servlet.http.HttpServlet {

	/**
	 * The name of the servlet init parameter for the portal coontrol file
	 */
	private static final String INIT_PARAM_CONTROL_FILE_NAME = "controlFileName";

	/**
	 * The name of the servlet init parameter for the property file's location
	 */
	//private static final String INIT_PARAM_PROPERTY_FILE_LOCATION = "propertyFileLocation";

	//private static final String INIT_PARAM_SERVICE_FILE_LOCATION = "serviceFileLocation";

	private static final String SESSION_VALIDATION_KEY = "session_validator";

	private static final String SESSION_VALIDATION_VALUE = "session_is_valid";
	/**
	 * The request parameter that contains the command that this servlet will
	 * execute.
	 */
	private static final String REQ_CMD_PARAM = Command.COMMAND_TAG;

	/**
	 * The request parameter that contains the command that this servlet will
	 * execute.
	 */
	//private static final String REQ_SESSION_PARAM = "sessions_id";

	/**
	 * The request parameter that contains the command that this servlet will
	 * execute.
	 */
	//private static final String DEFAULT_REQUEST_ID = "REQ-1";

	/**
	 * User name parameter
	 */
	//private static final String REQ_USERNAME_PARAM = "User_Name";

	/**
	 * User locale
	 */
	//private static final String REQ_USER_LOCALE = "LOCALE";

	/**
	 * Default error jsp
	 */
	private static final String ERROR_JSP = "error.jsp";

	//private static final String LOGIN_JSP = "logon.jsp";

	/**
	 * User Session Attribute
	 */
	//private static final String USERSESSION = "usersession";

	/**
	 * Command Description tag in the command config file
	 */
	private static final String COMMAND_DESCRIPTION = "description";

	/**
	 * Indicate if init() call was successfuly done. If calls falils, the value
	 * of this variable is set to false
	 */
	private static boolean _isInitDone = false;

	/**
	 * The command reader instance creation.
	 */
	private static CommandReader _commandReader = null;
	//private String REG_PAGE = "register.jsp";
	private static final String LOGIN_ERROR_CONDITION = "DBVALIDATE";
	
	//private static final int USR_ALREADY_DELETED = -1;
	private static final int USR_ALREADY_LOCKED = -2;
	private static final int USR_INVALID_CREDENTIALS = -3;
	private static final String ERR_PARAM = "error";
	private static final String LOGINDATE = "logindate";
	//private static final String INIT_PARAM_DATASOURCENAME = "datasourcename";

	/**
	 * This is the default character encoding for the application
	 */
	//private static final String DEFAULT_APP_CHARSET = "utf-8";

	/**
	 * This is the default command that will be executed on Refresh.
	 */
	private static final String DEFAULT_COMMAND_REFRESH = "LoadMenu";

	/**
	 * Standard servlet init method. Reads web.xml file and initializes many
	 * variables.
	 *
	 * @param config
	 *            the ServletConfig object that contains configutation
	 *            information for this servlet
	 *
	 * @exception None
	 */
	public void init(javax.servlet.ServletConfig config) throws ServletException {
		super.init(config);
		
		ServletContext sCtx = config.getServletContext();
		//RequestDispatcher requestDispatcher; 
		        
		if (_isInitDone == true) {
			return;
		}

		// Configuration file handling
		String controlFileName = getInitParameter(INIT_PARAM_CONTROL_FILE_NAME);
		if (controlFileName == null) {
			controlFileName = "config.xml";
			System.out.println("Control File not defined in web.xml. Defaulting to " + controlFileName);
		}

		//String controlFileRealPath = controlFileName;// sCtx.getRealPath(controlFileName);
		String controlFileRealPath = sCtx.getRealPath(controlFileName);
		System.out.println("555555555555555555555555555controlFileRealPath:" + controlFileName);

		// controlFileRealPath=controlFileName;
		// System.out.println("Control File relative path:" + controlFileName);
		System.out.println("Control File real path:" + controlFileRealPath);
		try {
			_commandReader = new CommandReader(controlFileRealPath);
		} catch (InfrastructureException ife) {
			ife.printStackTrace();
			System.out.println("Could not configure command list : " + ife.toString());
			return;
		}

		// Property file handling
		/*String propertyFile = getInitParameter(INIT_PARAM_PROPERTY_FILE_LOCATION);
		if (propertyFile == null) {
			propertyFile = "/config/application.properties";
			System.out.println("Propertiy files location not defined in web.xml. Defaulting to " + propertyFile);
		}
		String serviceProperties = getInitParameter(INIT_PARAM_SERVICE_FILE_LOCATION);
		if (serviceProperties == null) {
			serviceProperties = "/config/application.properties";
		}

		// String propertyFileRealPath = sCtx.getRealPath(propertyFile);
		String servicepropertyFileRealPath = serviceProperties;
		// System.out.println("Property File relative path:" + propertyFile);
		System.out.println("Property serviceProperties:" + serviceProperties);

		try {
			// Properties.getInstance().setup(propertyFileRealPath);
			//PropertyFileReader prpfilereader = PropertyFileReader.getPropertyReader(servicepropertyFileRealPath);

			//System.out.println("Property serviceProperties loaded:" + prpfilereader);

			// ServiceMapping.load(serviceProperties);
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(
			// "Could not initialize property handler : " + e.toString());
			return;
		}
*/
		_isInitDone = true;
	}
	
	
	
	/**
	 * Standard servlet service method.
	 *
	 * @param req
	 *            The HttpServletRequest object that contains the request the
	 *            client made of the servlet.
	 * @param res
	 *            The HttpServletResponse object that contains the response the
	 *            servlet returns to the client.
	 *
	 * @exception java.io.IOException
	 *                If an input or output error occurs while the servlet is
	 *                handling the TRACE request.
	 * @exception ServletException
	 *                If the request for the TRACE cannot be handled.
	 */
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpResponseProxy imvResponse = null;
		BaseAction action = null;
		BaseException exceptionResponse = null;
		String commandName = null;
		String commandDescription = null;
		UserSession imvSession = null;

		System.out.println("-------------------------------------------------------------------------------");
		// removeInProduction(request);

		System.out.println("-------------------------------------------------------------------------------");
		HttpSession httpSession = request.getSession();
		boolean isOldRequest = false;
		System.out.println("Starting Command Execution ...lk");
		System.out.println("step1 httpSession" + httpSession);
		try {
			if (_isInitDone == false) {
				throw new InfrastructureException("Internal Error, init() not done properly ", 100);
			}

			/*
			 * if (httpSession == null) { response.sendRedirect(LOGIN_JSP);
			 * return; }
			 */

			// We will check if the session contains our key. This ensures that
			// only logged in and valid sessions will
			// be allowed to proceed
			try {
				// The sessionValue will be set once in the index.jsp after the
				// user logs into the system. As long as the session is
				// valid this attribute will be present in the http session.

				// Create a tem[orary session
				httpSession = request.getSession();
				httpSession.setAttribute(SESSION_VALIDATION_KEY, "session_is_valid");

				// ends sessio n creations
				System.out.println("step2 httpSession" + httpSession);
				System.out.println("Checking if session is valid");
				String sessionValue = null;
				if (httpSession != null) {
					sessionValue = (String) httpSession.getAttribute(SESSION_VALIDATION_KEY);
				}

				boolean isSessionValid = false;
				if (sessionValue == null) {
					sessionValue = "";
				}
				System.out.println("Value of session Value is:" + sessionValue + ":");

				isSessionValid = sessionValue.equals(SESSION_VALIDATION_VALUE);

				System.out.println("Value of isSessionValid:" + isSessionValid);

				if (!isSessionValid) {
					throw new BaseException("The session is not logged into the application", 100);
				}

				// set the character encoding (this is always done before any
				// parameters are read)
				// for supporting a particular character set.
				// setApplicationCharEncoding(request);

				// check for preventing refresh
				/* String formReqUUID = request.getParameter("NEW_REQUEST_UUID");
				System.out.println("Form Request ID :: " + formReqUUID);

				if (formReqUUID != null && formReqUUID.equals("null") == false) {
					String lastReqUUID = (String) httpSession.getAttribute("NEW_REQUEST_UUID");

					System.out.println("Form Request ID :: " + formReqUUID);
					System.out.println("Last Request ID :: " + lastReqUUID);

					if (lastReqUUID != null && lastReqUUID.equals("null") == false) {
						if (!lastReqUUID.equals(formReqUUID)) {
							isOldRequest = true;
						}
					}
				}*/

			} catch (Throwable th) {
				// Send him to the login page
				th.printStackTrace();

				response.sendRedirect("logon.jsp");
				return;
			} // end of try-catch for session checking

			/*if (httpSession.getAttribute(LOGIN_ERROR_CONDITION) != null) {
				String loginErrorCode = (String) httpSession.getAttribute(LOGIN_ERROR_CONDITION);
				httpSession.invalidate();
				if (Integer.parseInt(loginErrorCode) == USR_ALREADY_DELETED) {
					throw new BusinessException("user has been deactivated", 100);
				}
				if (Integer.parseInt(loginErrorCode) == USR_ALREADY_LOCKED) {
					throw new BusinessException("user is locked", 100);
				}
				if (Integer.parseInt(loginErrorCode) == USR_INVALID_CREDENTIALS) {
					throw new BusinessException("Invalid User", 100);
				}
			}
			if (httpSession.getAttribute(LOGINDATE) == null) {
				httpSession.setAttribute(LOGINDATE, new java.util.Date());
			}
			*/
			String requestType = null;
			commandName = getParameterFromRequest(request, REQ_CMD_PARAM, null);

			// Setting to "getworklist" default command on Refresh.
			if (isOldRequest)
				commandName = DEFAULT_COMMAND_REFRESH;
			Command cmd = (Command) _commandReader.getCommand(commandName, true);
			if (commandName == null)
				commandName = cmd.getCommandName();
			requestType = request.getMethod();
			if (cmd == null) {
				throw new InfrastructureException("Improper configuration ", 100);
			}
			// * Check whether the user is an authorized user for executing this
			// command
			// if(!isUserInRole(cmd.getRoles(),request))
			// {
			// throw new InfrastructureException( "Not authorized user",
			// 100);
			// }
			commandDescription = cmd.getCommandProperty(COMMAND_DESCRIPTION, commandName);
			System.out.println("0");
			// Get the action class to use
			action = getActionInstance(cmd);
			// added the new method to the base action class to pass the servlet
			// context
			// action.setServletContext(getServletConfig().getServletContext());
			// Now get the request converted to Request
			System.out.println("step3 httpSession" + httpSession);
			System.out.println("step4 httpSession" + request.getSession());
			VI requestVo = action.convertRequest(request);
			System.out.println("1");
			System.out.println(action.toString());
			traceRequestResponse(commandName, requestVo, true);
			System.out.println("2");
			/*
			 * try { imvSession = (UserSession)
			 * httpSession.getAttribute(USERSESSION);
			 * 
			 * System.out.println("CONTROLLING SERVLET ..imvSession="+imvSession
			 * ); if (imvSession == null) { throw new InfrastructureException(
			 * "Inproper application configuration ", 100); } } catch
			 * (ClassCastException cce) {
			 * System.out.println("CONTROLLING SERVLET" ); throw new
			 * InfrastructureException( "Inproper application configuration ",
			 * 100); }
			 */

			System.out.println("3");
			HttpRequestProxy imvRequest = null;
			try{
				imvRequest = new HttpRequestProxy(requestVo);
			}
			catch(Exception e){
				
			}

			System.out.println("4");

			// Sourcing module related - START
			/*
			 * ImvBaseBO BaseBO = new BaseBO(); String datasource =
			 * BaseBO.getDataSourceName(imvRequest);
			 * request.getSession().setAttribute("datasource", datasource);
			 * request.getSession().setAttribute("userid",
			 * imvRequest.getSession().getUserId());
			 * getServletContext().setAttribute("datasource", datasource);
			 * getServletContext().setAttribute("userid",
			 * imvRequest.getSession().getUserId());
			 * 
			 */
			// Sourcing module related - END

			// Set the Trace for distrubuted logging
			imvResponse = action.executeRequest(imvRequest);
			System.out.println("Response Object Got From Execuute request");
			// After the request reset the trace
			if (imvResponse != null)
				request.setAttribute(Constants.RESPONSE_VI_ATTRIBUTE, imvResponse.getResponse());

			// The code below is added to ensure that there is a unique
			// identifier in session for every request
			// which would be stored in all the forms (which want to get rid of
			// the F5/Refresh problem) as a hidden variable.
			// This would be compared with the value in the session which is
			// done above .
			// All this to prevent the same trasaction which could happen if the
			// user clicks Refresh or F5.
			if (httpSession.getAttribute("NEW_REQUEST_UUID") != null)
				httpSession.removeAttribute("NEW_REQUEST_UUID");

			action.actionForward(((imvResponse != null) ? imvResponse.getResponse() : null), getServletConfig(),
					request, response);
		} catch (Throwable th) {
			th.printStackTrace();
			System.out.println("CONTROLLING SERVLET");
			// If by any chance an exception other than
			// BaseRuntimeException.javaBaseException is thrown then
			if (!(th instanceof BaseException)) {
				if (th.getMessage() != null) {
					exceptionResponse = new BaseException(th.getMessage(), 100);
				} else {
					exceptionResponse = new BaseException(" UnExpected Error - Please try later", 100);
				}
			} else
				exceptionResponse = (BaseException) th;
		} finally {

			try {
				if (exceptionResponse != null) {
					request.setAttribute(Constants.EXCEPTION_RESPONSE_ATTRIB, exceptionResponse);
					if ((action != null) && (exceptionResponse instanceof BusinessException)) {
						action.errorForward(exceptionResponse, getServletConfig(), request, response);
					} else {
						getServletConfig().getServletContext().getRequestDispatcher(ERROR_JSP).forward(request,
								response);
					}
					traceRequestResponse(commandName, exceptionResponse, false);
				} else {
					traceRequestResponse(commandName, ((imvResponse != null) ? imvResponse.getResponse() : null),
							false);
				}
			} catch (Exception ex) {
				System.out.println("CONTROLLING SERVLET");
				System.out.println("Could not complete request : " + ex.toString());
			}
			System.out.println(commandName + " - Command Execution Completed.");
		}
	}

	/**
	 * Returns the Command string from the Request
	 * 
	 * @param The
	 *            HTTP request
	 * @return The name of the command
	 */
	private String getParameterFromRequest(HttpServletRequest req, String param, String defaultValue) {
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
		return (cmdValue != null) ? cmdValue : defaultValue;
	}

	/**
	 * Execute the given command.
	 *
	 * @param req
	 *            The HTTP request
	 * @param doc
	 *            The MPSDocument which the action process will use to write on
	 *            to.
	 * @param cmd
	 *            The command to be processed.
	 * @param requestType
	 *            Indicate if a command should execute in GET or POST manner.
	 *
	 * @return The name of the next command (contained within ActionForward
	 *         object)
	 *
	 * @exception Exception
	 *                When certain failures happen.
	 */
	private BaseAction getActionInstance(Command cmd) throws InfrastructureException {
		// Processing command
		try {
			// Instantiate action class

			if (cmd.getActionClass() == null) {
				System.out.println("Class not found for = " + cmd.getCommandName());
				throw new InfrastructureException("Class not found for = " + cmd.getCommandName(), 100);
			}

			BaseAction action = (BaseAction) cmd.getActionClass().newInstance();

			if (action == null) {
				System.out.println("Action class can not be instanitated for " + cmd.getActionClass());
				throw new Exception("Action class can not be instanitated for " + cmd.getActionClass());
			}
			return action;
		} catch (InstantiationException ie) {
			System.out.println("Controllerservlet ");
			throw new InfrastructureException("Class not found for = " + cmd.getCommandName(), 100);
		} catch (Exception e) {
			System.out.println("Controllerservlet ");
			throw new InfrastructureException("Class not found for = " + cmd.getCommandName(), 100);
		}
	}

	private void traceRequestResponse(String command, Object data, boolean isRequest) {
		try {
			{
				if (isRequest == true) {
					System.out.println("--- Tracing " + command + " Request ---");
				} else {
					System.out.println("--- Tracing " + command + " Response ---");
				}
				System.out.println(((data != null) ? data.toString() : null));
				System.out.println("--- End of Request / Response Trace ---");
			}
		} catch (Throwable th) {
			// Keep quite
		}
	}

	/*public boolean isUserInRole(Collection roleCollection, HttpServletRequest request) {
		System.out.println("*************************** isUserInRole() :: " + roleCollection.toString());
		boolean isUserInRole = false;
		if (roleCollection != null) {
			roleCollection.add(Constants.ROLE_IS_ADMIN);
		}
		if (roleCollection == null || roleCollection.contains(Constants.PUBLIC)) {
			return true;
		}
		for (java.util.Iterator itr = roleCollection.iterator(); itr.hasNext();) {
			isUserInRole = request.isUserInRole((String) itr.next());
			if (isUserInRole) {
				break;
			}
		}
		return isUserInRole;
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
	}
*/
	/**
	 * This method is used to set the character encoding.
	 * 
	 * @param HttpServletRequest
	 */
	/*private void setApplicationCharEncoding(HttpServletRequest request) {

		try {
		 
			 
				request.setCharacterEncoding("UTF8");
		} catch (Exception uee) {
			System.out.println("Encoding Type Present in Properties file is not correct or is improper");
		}
	}*/

	 

} // class
