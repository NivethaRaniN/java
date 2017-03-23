package com.infrastructure.conrollerframework.data;

import java.util.Locale;

import javax.servlet.http.HttpSession;

/**
 * This class is used to provide some safety around the session values. It
 * provides compile time checking of data types of stored values, and also
 * provides a mechanism to ensure correct naming of session values.
 */
public class HttpSessionProxy extends SessionProxy {
	private HttpSession session_;
	private final String userProfile = "PROFILE";
	private final String userLocale = "LOCALE";
	private final String menuState = "MENUSTATE";

	private final String lastPostedForm = "LAST_POSTED_FORM";
	private final String lastError = "LAST_ERROR";
	private final String lastFailedCmdName = "LAST_FAILED_CMD_NAME";
	private final String lastCmdName = "LAST_COMMAND_NAME";
	private final String lastMethodType = "LAST_METHOD_TYPE";

	private final String SESSION_ATTRIBUTE_NAME_COMPOSE_MODE = "ComposeMode";
	private final String SESSION_ATTRIBUTE_NAME_CANCEL_ATTACHMENTS_LIST = "deleteAttachmentsListByCancel";
	private final String SESSION_ATTRIBUTE_NAME_HEADERS_IDS = "InboxHeadersIdVector";
	private final String registrationSettings = "REGISTRATION_SETTINGS";

	public HttpSessionProxy(VI vi) {
		super(vi);
		session_ = ((HttpVO) vi).getSession();

	}

	public String getUserRole() {
		// Need to retrieve user bean and then return role from there.
		// [TODO]
		return ("REGISTERED");
	}

	/**
	 * Sets the UserProfile Object into the HttpSession
	 * 
	 * @param prof
	 *            The UserProfile Object
	 */
	public void setUserProfile(VI prof) {
		session_.setAttribute(userProfile, prof);
	}

	/**
	 * Removes the UserProfile Object from the HttpSession
	 */
	public void removeUserProfile() {
		session_.removeAttribute(userProfile);
	}

	/**
	 * Removes the given attribute from the session
	 */
	public void removeAttribute(String strAttributeName) {
		session_.removeAttribute(strAttributeName);
	}

	public VI getUserProfile() {
		return null;
	}

	/**
	 * Returns the Locale of the User Logged in
	 * 
	 * @return java.util.Locale The Locale object of the User
	 */
	public Locale getUserLocale() {
		Locale retVal = null;
		Object obj = null;
		if (session_ != null)
			obj = session_.getAttribute(userLocale);
		if (obj != null)
			retVal = (Locale) obj;
		return retVal;
	}

	/**
	 * sets the Locale Object into the HttpSession
	 * 
	 * @param locale
	 *            The Locale of the user
	 */
	public void setUserLocale(Locale locale) {
		if (locale != null)
			session_.setAttribute(userLocale, locale);
	}

	/**
	 * Returns a String representing the menuState
	 * 
	 * @return java.lang.String which represents the menuState
	 */
	public String getMenuState() {
		return (String) session_.getAttribute(menuState);
	}

	/**
	 * sets the menuState into the HttpSession
	 * 
	 * @param state
	 *            The String representing the menuState
	 */
	public void setMenuState(String state) {
		if (state != null)
			session_.setAttribute(menuState, state);
	}

	/**
	 * Returns an Object representing the RegistrationSettings of a User
	 * 
	 * @return Object which represents the RegistrationSettings of a User
	 */
	public Object getRegistrationSettings() {
		return session_.getAttribute(registrationSettings);
	}

	/**
	 * sets the RegistrationSettings into the HttpSession
	 * 
	 * @param rs
	 *            The Object representing RegistrationSettings of a User
	 */
	public void setRegistrationSettings(Object rs) {
		if (rs != null)
			session_.setAttribute(registrationSettings, rs);
	}

	/**
	 * Invalidates the HttpSession Object
	 */
	public void invalidate() {
		if (session_ != null) {
			session_.invalidate();
			session_ = null;
		}
	}

	/**
	 * Method removeLastPostedForm and the lastError removes the last posted
	 * BaseForm from the session.
	 */
	public void removeLastPostedData() {
		session_.removeAttribute(lastPostedForm);
		session_.removeAttribute(lastError);
		session_.removeAttribute(lastMethodType);
	}

	/**
	 * Method removeLastError removes the last error which had occurred during
	 * form validation from the session.
	 */
	public void removeLastError() {
		session_.removeAttribute(lastError);
	}

	/**
	 * Method getLastFailedCmdName returns the name of the last failed cmd from
	 * the session.
	 * 
	 * @return String
	 */
	public String getLastFailedCmdName() {
		return (String) session_.getAttribute(this.lastFailedCmdName);
	}

	/**
	 * Method setLastFailedCmdName sets the name of the last failed cmd into the
	 * session
	 * 
	 * @param cmd
	 */
	public void setLastFailedCmdName(String cmd) {
		session_.setAttribute(lastFailedCmdName, cmd);
	}

	/**
	 * Method removeLastFailedCmdName removes the name of the last failed cmd
	 * from the session.
	 */
	public void removeLastFailedCmdName() {
		session_.removeAttribute(lastFailedCmdName);
	}

	/**
	 * Method getLastCmdName. returns the name of the last executed command in
	 * the portal.
	 * 
	 * @return String
	 */
	public String getLastCmdName() {
		return (String) session_.getAttribute(this.lastCmdName);
	}

	/**
	 * Method setLastCmdName. sets the name of the last executed command from
	 * the portal.
	 * 
	 * @param cmdName
	 */
	public void setLastCmdName(String cmdName) {
		session_.setAttribute(this.lastCmdName, cmdName);
	}

	/**
	 * Method removeLastCmdName removes the name of the last executed command
	 * from the portal.
	 */
	public void removeLastCmdName() {
		session_.removeAttribute(this.lastCmdName);
	}

	/**
	 * Returns the lastMethodType.
	 * 
	 * @return String
	 */
	public int getLastMethodType() {
		String methodType = (String) session_.getAttribute(this.lastMethodType);
		if (methodType == null || methodType.trim().length() == 0)
			return 0;
		return new Integer(methodType).intValue();
	}

	/**
	 * Sets the lastMethodType.
	 * 
	 * @param lastMethodType
	 *            The lastMethodType to set
	 */
	public void setLastMethodType(int lastMethodType) {
		session_.setAttribute(this.lastMethodType, "" + lastMethodType);
	}

	/**
	 * remove the lastMethodType from session
	 */
	public void removeLastMethodType() {
		session_.removeAttribute(this.lastMethodType);
	}

	public void setParameter(String name, String value) {
		session_.setAttribute(name, value);
	}

	public String getParameter(String name) {
		return (String) session_.getAttribute(name);
	}

	public void setAttribute(String name, Object value) {
		session_.setAttribute(name, value);
	}

	public Object getAttribute(String name) {
		return session_.getAttribute(name);
	}

} // class