package com.infrastructure.conrollerframework.login;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class UserPreLoginFilter extends BaseFilter {

	public UserPreLoginFilter() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(ServletRequest, ServletResponse,
	 * FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String userName = request.getParameter(USER_NAME_FIELD);
		System.out.println("in the pre filter");
		String supplierCode = request.getParameter(SUPPLIER_CODE_FIELD);
		super.doFilter(request, response, chain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
	}
}
