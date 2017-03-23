package com.infrastructure.conrollerframework.data;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is used to provide some safety around the session values. It
 * provides compile time checking of data types of stored values, and also
 * provides a mechanism to ensure correct naming of session values.
 */
public class HttpVO extends VO {

	HttpSession _sess = null;
	HttpServletRequest _req = null;
	HttpServletResponse _res = null;
	Cookie _cook = null;

	public void setSession(HttpSession sess) {
		_sess = sess;
	}

	public HttpSession getSession() {
		return _sess;
	}

	public void setCookie(Cookie cook) {
		_cook = cook;
	}

	public Cookie getCookie() {
		return _cook;
	}

	public void setRequest(HttpServletRequest req) {
		_req = req;
	}

	public HttpServletRequest getRequest() {
		return _req;
	}

	public void setResponse(HttpServletResponse res) {
		_res = res;
	}

	public HttpServletResponse getResponse() {
		return _res;
	}

} // class