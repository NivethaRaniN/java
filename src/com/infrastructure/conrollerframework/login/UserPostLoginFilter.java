
package com.infrastructure.conrollerframework.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserPostLoginFilter extends BaseFilter {

	public void init(FilterConfig cfg) throws ServletException {
		System.out.println("---------------------------------------------------user post filter");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(ServletRequest, ServletResponse,
	 * FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {

			super.doFilter(request, response, chain);
			request.setAttribute("Constants.USERTYPE", "Constants.USERTYPE_INT");
			System.out.println("---------------------------------------------------user post filter");
			HttpSession session = ((HttpServletRequest) request).getSession();
			// UserSession userSession =
			// (UserSession)session.getAttribute(Constants.USER_SESSION_ATTR_NAME);
			// userSession.setUserLoginSystem(getAttribute(request,
			// BaseFilter.APPLICATION_ATTR, null));
			// userSession.setUserType(Constants.TMT_USER_CODE);
			// session.setAttribute(Constants.USER_SESSION_ATTR_NAME,userSession);
			// removeInProduction(request);
			/*
			 * Code added for SS
			 */
			System.out.println("---------------------------------------------------user post filter"
					+ request.getParameter("j_username"));
			// String authDone = request.getParameter(Constants.AUTH_DONE);
			// session.setAttribute(Constants.AUTH_DONE,authDone);
			// String appNum = request.getParameter(Constants.APP_NUM);
			// session.setAttribute(Constants.APP_NUM,appNum);
			session.setAttribute("j_username", request.getParameter("j_username"));
			session.setAttribute("j_password", request.getParameter("j_password"));
			// session.setAttribute("loclanguage",request.getParameter("language"));
		} catch (Exception e) {
			throw new ServletException(e.toString());
		}
	}

	protected void removeInProduction(HttpServletRequest request) {
		System.out.println(" UserPostLoginFilter Session parameters name");
		for (java.util.Enumeration e = request.getSession().getAttributeNames(); e.hasMoreElements();) {
			try {
				Object elem = e.nextElement();
				System.out.println(" UserPostLoginFilter Session PARAMETER NAME =" + elem + ":  VALUE ="
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
