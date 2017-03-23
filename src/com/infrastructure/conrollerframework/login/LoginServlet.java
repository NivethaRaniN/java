package com.infrastructure.conrollerframework.login;

/**
  *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.infrastructure.conrollerframework.data.Constants;
import com.infrastructure.conrollerframework.session.UserSession;

public class LoginServlet extends HttpServlet {

	private ServletContext ctx;
	public static final String USER_NAME_FIELD = "username";
	public static final String USERDN_FIELD = "j_username";
	public static final String USERPW_FIELD = "j_password";
	public static final String APPLICATION_ATTR = "application";
	public static final String USERTYPE_ATTR = "usertype";
	public static final String THAI_LANG = "thai";
	public static final String THAI_LANG_CODE = "th";
	public static final String THAILAND_CODE = "th";
	public static final String ENG_LANG_CODE = "en";
	public static final String US_CODE = "US";

	private static final String DB_DRIVER = "dbdriver";
	private static final String DB_URL = "dburl";
	private static final String DBBIND_USER = "dbuser";
	private static final String DBBIND_PWD = "dbpassword";
	private static final String USERSESSION_ATTR = "usersession";
	protected static final String USERTYPE = "usertype";
	protected static final String USERTYPE_SUP = "supplier";
	protected static final String USERTYPE_INT = "internaluser";
	protected static final String USER_LANGUAGE = "internaluser";
	private UserProfile uprof = null;
	private UserSession userSession = null;

	public void init(ServletConfig sc) throws ServletException {
		// Ensure supers constructor is called.
		super.init(sc);

		System.out.println("post login 1");
		String driver = getInitParameter(DB_DRIVER);
		String dburl = getInitParameter(DB_URL);
		String dbBindUser = getInitParameter(DBBIND_USER);
		String dbBindPwd = getInitParameter(DBBIND_PWD);

		try {
			System.out.println(driver + " " + dburl + " " + dbBindUser + " " + dbBindPwd);
			uprof = new UserProfile(driver, dburl, dbBindUser, dbBindPwd);
			System.out.println("in init filter " + uprof);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.toString());
		}
		this.ctx = getServletContext();
	}

	protected void removeInProduction(HttpServletRequest request) {
		System.out.println(" LoginServlet Session parameters name");
		for (java.util.Enumeration e = request.getSession().getAttributeNames(); e.hasMoreElements();) {
			try {
				Object elem = e.nextElement();
				System.out.println(" LoginServlet Session PARAMETER NAME =" + elem + ":  VALUE ="
						+ request.getSession().getAttribute((String) elem));
			} catch (Exception ex) {
			}
		}

	}

	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String logincommand = req.getParameter("Login");

		if (logincommand.equals("logincommand")) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");

			System.out.println("User Name:" + username);

			removeInProduction(req);

			// If user exist redirect to welcome.jsp

			HttpSession session = req.getSession();
			session.putValue("username", username);

			userSession = uprof.getUserSession((String) req.getParameter(USERDN_FIELD), req.getParameter(USERPW_FIELD));

			System.out.println("userSession in post filter 1 userSession" + userSession);

			// Now validate the user contract with the data base
			userSession.setUserId(getAttribute(req, USER_NAME_FIELD, null));

			System.out.println("USER_LANGUAGE" + USER_LANGUAGE);
			// System.out.println("(String)userSession.getSessionProperty(USER_LANGUAGE)"+(String)userSession.getSessionProperty(USER_LANGUAGE));
			// System.out.println("getUserLocale(getAttribute(req,
			// USER_LANGUAGE(String)userSession.getSessionProperty(USER_LANGUAGE)))"+getUserLocale(getAttribute(req,
			// USER_LANGUAGE,(String)userSession.getSessionProperty(USER_LANGUAGE))));

			// userSession.setSessionLocale( getUserLocale(getAttribute(req,
			// USER_LANGUAGE,
			// (String)userSession.getSessionProperty(USER_LANGUAGE))));

			System.out.println("userSession in post filter session" + session);
			session.setAttribute(Constants.USER_SESSION_ATTR_NAME, userSession);

			userSession = (UserSession) session.getAttribute(Constants.USER_SESSION_ATTR_NAME);

			// userSession.setUserLoginSystem(getAttribute(req,
			// BaseFilter.APPLICATION_ATTR, null));
			userSession.setUserType(Constants.USER_CODE);
			System.out.println("userSession in userpostloginfilter" + userSession);
			session.setAttribute(Constants.USER_SESSION_ATTR_NAME, userSession);
			System.out.println("F3" + session.getAttribute(Constants.USER_SESSION_ATTR_NAME));
			/*
			 * Code added for SS
			 */
			String authDone = req.getParameter(Constants.AUTH_DONE);
			session.setAttribute(Constants.AUTH_DONE, authDone);
			String appNum = req.getParameter(Constants.APP_NUM);

			session.setAttribute(Constants.APP_NUM, appNum);
			session.setAttribute("j_username", req.getParameter("username"));
			// session.setAttribute("j_password",req.getParameter("j_password"));
			// session.setAttribute("loclanguage",req.getParameter("language"));

			session.setAttribute("TESTSESSION", "TESTSESSION");

			resp.sendRedirect("/commonlogin/resources/jsp/welcome.jsp");

		}

	}

	// from base class

	protected String getAttribute(ServletRequest request, String key, String defaultValue) {
		String cmd = key + "=";
		String cmdValue = request.getParameter(key);
		if (cmdValue == null) {
			// this is due to an bug in SaActivatorRecorder.
			String queryString = ((HttpServletRequest) request).getQueryString();
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

	protected Locale getUserLocale(String language) {
		if ((language != null) && (language.equalsIgnoreCase(THAI_LANG) == true)) {
			return new Locale(THAI_LANG_CODE, THAILAND_CODE);
		}
		return new Locale(ENG_LANG_CODE, US_CODE);
	}

}