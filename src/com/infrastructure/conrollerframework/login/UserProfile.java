
package com.infrastructure.conrollerframework.login;

import com.infrastructure.conrollerframework.data.Constants;
import com.infrastructure.conrollerframework.session.UserSession;
import com.infrastructure.conrollerframework.session.UserSessionImpl;
import com.infrastructure.exception.InfrastructureException;

public class UserProfile {

	private static final String[] USERATTRLIST = new String[] { Constants.USERLOGIN, };
	private String _driver = null;
	private String _url = null;
	private String _user = null;
	private String _passwd = null;

	public UserProfile(String driver, String url, String user, String passwd) throws InfrastructureException {
		_driver = driver;
		_url = url;
		_user = user;
		_passwd = passwd;
		init();
	}

	private void init() {
		// bind to oracle
	}

	/**
	 * The public method to get the additional user profile of a user
	 */
	public UserSession getUserSession(String user, String password) {

		UserSessionImpl userSession = null;

		userSession = new UserSessionImpl(user);
		System.out.println("$$$$$$$$$$$$$" + userSession.toString());
		userSession.setSessionProperty(BaseFilter.USER_LANGUAGE, "en");
		// set valid user or not
		userSession.setValidUser(true);
		// userSession.setappAccess("SALE");
		return userSession;

	}

}
