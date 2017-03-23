package com.infrastructure.conrollerframework.session;

/**
  *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetSessionObjects extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(resp.getOutputStream());

		resp.setContentType("application/octet-stream");

		HttpSession session = req.getSession();

		Hashtable ssn = new Hashtable();

		Enumeration enum1 = session.getAttributeNames();
		while (enum1.hasMoreElements()) {
			String key = (String) enum1.nextElement();
			System.out.println("Key: " + key + "\n");
			if (key.equals("usersession")) {
				System.out.println("Getting only usersession");
				UserSession value = (UserSession) session.getAttribute(key);
				ssn.put(key, value);
			}

		}

		// Remove this console list of cookies for production
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				System.out.println(cookies[i].getName() + ":" + cookies[i].getValue());
			}
		}

		// write the serialized Hashtable
		out.writeObject(ssn);

		out.flush();
		out.close();

	}

}