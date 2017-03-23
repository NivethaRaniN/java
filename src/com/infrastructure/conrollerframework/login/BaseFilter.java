
package com.infrastructure.conrollerframework.login;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class BaseFilter implements Filter {

	public static final String USER_NAME_FIELD = "username";
	public static final String SUPPLIER_CODE_FIELD = "suppliercode";
	public static final String USERDN_FIELD = "j_username";
	public static final String USERPW_FIELD = "j_password";
	public static final String APPLICATION_ATTR = "application";
	public static final String USERTYPE_ATTR = "usertype";
	public static final String USER_LANGUAGE = "language";
	public static final String THAI_LANG = "thai";
	public static final String THAI_LANG_CODE = "th";
	public static final String THAILAND_CODE = "th";
	public static final String ENG_LANG_CODE = "en";
	public static final String US_CODE = "US";

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		System.out.println("in the iniit base filter ");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(ServletRequest, ServletResponse,
	 *      FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		chain.doFilter(request, response);
		System.out.println("in the base filter ");
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}

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
