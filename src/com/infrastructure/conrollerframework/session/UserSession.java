
package com.infrastructure.conrollerframework.session;

import java.util.Hashtable;
import java.util.Locale;

public interface UserSession extends java.io.Serializable {

	/**
	 * Get the identifier of this session.
	 */
	public String getId();

	/**
	 * Sets the server message id generated for the login service request. This
	 * id is used for correlating the login request with all subsequent requests
	 * in the audit log. The audit log will print the ServerMessageId of the
	 * login request which will be the same as the LoginMsgId which will be
	 * printed for all requests issued by the same user.
	 */
	public void setLoginMessageId(String loginMessageId);

	/**
	 * Returns the logging message id
	 */
	public String getLoginMessageId();

	/**
	 * To get the user Name
	 */
	public String getUserName();

	/**
	 * To set the user name
	 */
	public void setUserName(String userName);

	/**
	 * To set the user type
	 */
	public void setUserType(String userType);

	/**
	 * To get the user Type
	 */
	public String getUserType();

	/**
	 * To get the user login id
	 */
	public String getUserId();

	/**
	 * To set the user login id
	 */
	public void setUserId(String acs);

	/**
	 * Returns the sessionLocale.
	 * 
	 * @return Locale
	 */
	public Locale getSessionLocale();

	/**
	 * Sets the sessionLocale.
	 * 
	 * @param sessionLocale
	 *            The sessionLocale to set
	 */
	public void setSessionLocale(Locale sessionLocale);

	/**
	 * Returns the sessionProperty of a given key , null if the key is not
	 * defined.
	 * 
	 * @return SessionProperty value
	 */
	public Object getSessionProperty(String key);

	/**
	 * Sets the sessionProperty of a given key , null if the key is not
	 * defined..
	 * 
	 * @param key
	 *            SessionProperty key ,
	 * @param Object
	 *            SessionProperty value
	 */
	public void setSessionProperty(String key, Object value);

	public void setValidUser(boolean valid);

	public boolean isValidUser();

	public void attributesFromXML(Hashtable attributeList);

}
