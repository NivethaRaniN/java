package com.infrastructure.conrollerframework.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PostLoginFilter extends BaseFilter {
	private static final String DRIVER = "driver";
	private static final String URL = "dburl";
	private static final String USER = "dbuser";
	private static final String PWD = "dbpasswd";
	private static final String USERSESSION_ATTR = "usersession";
	protected static final String USERTYPE = "usertype";
	protected static final String USERTYPE_SUP = "supplier";
	protected static final String USERTYPE_INT = "internaluser";
	protected static final String USER_LANGUAGE = "internaluser";

	private UserProfile uprof = null;

	/**
	 * Constructor for PostLoginFilter.
	 */
	public PostLoginFilter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(FilterConfig)
	 */
	public void init(FilterConfig cfg) throws ServletException {
		String factoryClass = cfg.getInitParameter(DRIVER);
		String ldapurl = cfg.getInitParameter(URL);
		String ldapBindUser = cfg.getInitParameter(USER);
		String ldapBindPwd = cfg.getInitParameter(PWD);
		try {
			uprof = new UserProfile("user", "passwd", "driver", "url");

		} catch (Exception e) {
			throw new ServletException(e.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(ServletRequest, ServletResponse,
	 * FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		// session.setAttribute(USERSESSION_ATTR, "UNDEFINED");
		try {
			// removeInProduction(request);
			try {
				chain.doFilter(request, response);
				System.out.println("in the base post filter ");
			} catch (Exception eee) {
				System.out.println("Exception is in calling the filters chain.doFilter method " + eee);
				eee.printStackTrace();
			}

			// Now connect to LDAP and get user profile
			// First Get additional user information from LDAP
			/*
			 * UserSession userSession = null; userSession =
			 * uprof.getUserSession((String)request.getParameter(USERDN_FIELD),
			 * request.getParameter(USERPW_FIELD)); // Now validate the user
			 * contract with the data base
			 * userSession.setUserId(getAttribute(request, USER_NAME_FIELD,
			 * null)); System.out.println("USER_LANGUAGE"+USER_LANGUAGE);
			 * System.out.println(
			 * "(String)userSession.getSessionProperty(USER_LANGUAGE)"+(String)
			 * userSession.getSessionProperty(USER_LANGUAGE)); System.out.
			 * println("getUserLocale(getAttribute(request, USER_LANGUAGE(String)userSession.getSessionProperty(USER_LANGUAGE)))"
			 * +getUserLocale(getAttribute(request, USER_LANGUAGE,
			 * (String)userSession.getSessionProperty(USER_LANGUAGE))));
			 * userSession.setSessionLocale( getUserLocale(getAttribute(request,
			 * USER_LANGUAGE,
			 * (String)userSession.getSessionProperty(USER_LANGUAGE))));
			 * System.out.println("userSession in post filter"+userSession);
			 * session.setAttribute(Constants.USER_SESSION_ATTR_NAME,userSession
			 * );
			 */
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.toString());
		}
	}

	protected void removeInProduction(HttpServletRequest request) {
		System.out.println(" PostLoginFilter Session parameters name");
		for (java.util.Enumeration e = request.getSession(false).getAttributeNames(); e.hasMoreElements();) {
			try {
				Object elem = e.nextElement();
				System.out.println(" PostLoginFilter Session PARAMETER NAME =" + elem + ":  VALUE ="
						+ request.getSession().getAttribute((String) elem));
			} catch (Exception ex) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// _userAttr.destroy();
	}
}
