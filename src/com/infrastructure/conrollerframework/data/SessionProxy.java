package com.infrastructure.conrollerframework.data;

import java.util.Locale;

/**
 * This class is used to provide some safety around the session values. It
 * provides compile time checking of data types of stored values, and also
 * provides a mechanism to ensure correct naming of session values.
 */
public abstract class SessionProxy implements java.io.Serializable {
	private VI objVi;
	private final String userProfile = "PROFILE";
	private final String userLocale = "LOCALE";
	private final String menuState = "MENUSTATE";

	private final String lastPostedForm = "LAST_POSTED_FORM";
	private final String lastError = "LAST_ERROR";
	private final String lastFailedCmdName = "LAST_FAILED_CMD_NAME";
	private final String lastCmdName = "LAST_COMMAND_NAME";
	private final String lastMethodType = "LAST_METHOD_TYPE";

	public SessionProxy(VI vi) {
		objVi = vi;
	}

	public abstract String getUserRole();

	/*
	 * public String getUserRole() { // Need to retrieve user bean and then
	 * return role from there. // [TODO] return ("REGISTERED"); }
	 */
	/**
	 * returns the UserProfile Object
	 * 
	 * @return the UserProfile Object which contains information about the User
	 *         Logged into IDweb
	 */
	public abstract VI getUserProfile();

	/*
	 * public UserProfile getUserProfile() { UserProfile retVal = null; Object
	 * prof = null; if(session_!=null) prof =
	 * session_.getAttribute(userProfile); if (prof != null) retVal =
	 * (UserProfile) prof; return retVal; }
	 */
	/**
	 * Sets the UserProfile Object into the HttpSession
	 * 
	 * @param prof
	 *            The UserProfile Object
	 */
	public abstract void setUserProfile(VI prof);

	/*
	 * public void setUserProfile(UserProfile prof) {
	 * session_.setAttribute(userProfile, prof); }
	 */
	/**
	 * Removes the UserProfile Object from the HttpSession
	 */
	public abstract void removeUserProfile();
	/*
	 * public void removeUserProfile() { session_.removeAttribute(userProfile);
	 * }
	 */

	/**
	 * Removes the given attribute from the session
	 */
	public abstract void removeAttribute(String strAttributeName);
	/*
	 * public void removeAttribute(String strAttributeName) {
	 * session_.removeAttribute(strAttributeName); }
	 */

	/**
	 * Returns the Locale of the User Logged in
	 * 
	 * @return java.util.Locale The Locale object of the User
	 */
	public abstract Locale getUserLocale();

	/*
	 * public Locale getUserLocale() { Locale retVal = null; Object obj =null;
	 * if(session_!=null) obj = session_.getAttribute(userLocale); if (obj !=
	 * null) retVal = (Locale) obj; return retVal; }
	 */
	/**
	 * sets the Locale Object into the HttpSession
	 * 
	 * @param locale
	 *            The Locale of the user
	 */
	public abstract void setUserLocale(Locale locale);
	/*
	 * public void setUserLocale(Locale locale) { if (locale != null)
	 * session_.setAttribute(userLocale, locale); }
	 */

	/**
	 * Returns a String representing the menuState
	 * 
	 * @return java.lang.String which represents the menuState
	 */
	public abstract String getMenuState();

	/*
	 * public String getMenuState() { return (String)
	 * session_.getAttribute(menuState); }
	 */
	/**
	 * sets the menuState into the HttpSession
	 * 
	 * @param state
	 *            The String representing the menuState
	 */
	public abstract void setMenuState(String state);
	/*
	 * public void setMenuState(String state) { if (state != null)
	 * session_.setAttribute(menuState, state); }
	 */

	/**
	 * Returns an Object representing the RegistrationSettings of a User
	 * 
	 * @return Object which represents the RegistrationSettings of a User
	 */
	public abstract Object getRegistrationSettings();

	/*
	 * public Object getRegistrationSettings() { return
	 * session_.getAttribute(registrationSettings); }
	 */
	/**
	 * sets the RegistrationSettings into the HttpSession
	 * 
	 * @param rs
	 *            The Object representing RegistrationSettings of a User
	 */
	public abstract void setRegistrationSettings(Object rs);
	/*
	 * public void setRegistrationSettings(Object rs) { if (rs != null)
	 * session_.setAttribute(registrationSettings, rs); }
	 */

	/**
	 * Invalidates the HttpSession Object
	 */
	public abstract void invalidate();
	/*
	 * public void invalidate() { if(session_!=null) { session_.invalidate();
	 * session_=null; } }
	 */

	/**
	 * Method getLastPostedForm returns the last posted BaseForm which was
	 * stored in the session.
	 * 
	 * @return BaseForm
	 */
	// public abstract BaseForm getLastPostedForm();
	/*
	 * public BaseForm getLastPostedForm() { return (BaseForm)
	 * session_.getAttribute(this.lastPostedForm); }
	 */
	/**
	 * Method setLastPostedForm sets the last posted form into the session. This
	 * method is used when the user switches language after submitting some
	 * data.
	 *
	 * @param form
	 */

	// public abstract void setLastPostedForm(BaseForm form);
	/*
	 * public void setLastPostedForm(BaseForm form) {
	 * session_.setAttribute(lastPostedForm, form); }
	 */
	/**
	 * Method removeLastPostedForm and the lastError removes the last posted
	 * BaseForm from the session.
	 */
	public abstract void removeLastPostedData();
	/*
	 * public void removeLastPostedData() {
	 * session_.removeAttribute(lastPostedForm);
	 * session_.removeAttribute(lastError);
	 * session_.removeAttribute(lastMethodType); }
	 */
	/**
	 * Method getLastError returns the last error which occurred in the form
	 * validation in the session.
	 * 
	 * @return IDEActionError
	 */
	// public abstract IDEActionError getLastError();
	/*
	 * public IDEActionError getLastError() { return (IDEActionError)
	 * session_.getAttribute(lastError); }
	 */
	/**
	 * Method setLastError sets the last error which occured during form
	 * validation in the session.
	 * 
	 * @param err
	 */

	// public abstract void setLastError(IDEActionError err);
	/*
	 * public void setLastError(IDEActionError err) {
	 * session_.setAttribute(lastError, err); }
	 */
	/**
	 * Method removeLastError removes the last error which had occurred during
	 * form validation from the session.
	 */
	public abstract void removeLastError();

	/*
	 * public void removeLastError() { session_.removeAttribute(lastError); }
	 */
	/**
	 * Method getLastFailedCmdName returns the name of the last failed cmd from
	 * the session.
	 * 
	 * @return String
	 */
	public abstract String getLastFailedCmdName();

	/*
	 * public String getLastFailedCmdName() { return (String)
	 * session_.getAttribute(this.lastFailedCmdName); }
	 */
	/**
	 * Method setLastFailedCmdName sets the name of the last failed cmd into the
	 * session
	 * 
	 * @param cmd
	 */
	public abstract void setLastFailedCmdName(String cmd);

	/*
	 * public void setLastFailedCmdName(String cmd) {
	 * session_.setAttribute(lastFailedCmdName, cmd); }
	 */
	/**
	 * Method removeLastFailedCmdName removes the name of the last failed cmd
	 * from the session.
	 */
	public abstract void removeLastFailedCmdName();

	/*
	 * public void removeLastFailedCmdName() {
	 * session_.removeAttribute(lastFailedCmdName); }
	 */
	/**
	 * Method getLastCmdName. returns the name of the last executed command in
	 * the portal.
	 * 
	 * @return String
	 */
	public abstract String getLastCmdName();

	/*
	 * public String getLastCmdName() { return (String)
	 * session_.getAttribute(this.lastCmdName); }
	 */
	/**
	 * Method setLastCmdName. sets the name of the last executed command from
	 * the portal.
	 * 
	 * @param cmdName
	 */
	public abstract void setLastCmdName(String cmdName);

	/*
	 * public void setLastCmdName(String cmdName) {
	 * session_.setAttribute(this.lastCmdName, cmdName); }
	 */
	/**
	 * Method removeLastCmdName removes the name of the last executed command
	 * from the portal.
	 */
	public abstract void removeLastCmdName();
	/*
	 * public void removeLastCmdName() {
	 * session_.removeAttribute(this.lastCmdName); }
	 */

	/**
	 * Returns the lastMethodType.
	 * 
	 * @return String
	 */
	public abstract int getLastMethodType();
	/*
	 * public int getLastMethodType() { String methodType = (String)
	 * session_.getAttribute(this.lastMethodType); if (methodType == null ||
	 * methodType.trim().length() == 0) return 0; return new
	 * Integer(methodType).intValue(); }
	 */

	/**
	 * Sets the lastMethodType.
	 * 
	 * @param lastMethodType
	 *            The lastMethodType to set
	 */
	public abstract void setLastMethodType(int lastMethodType);
	/*
	 * public void setLastMethodType(int lastMethodType) {
	 * session_.setAttribute(this.lastMethodType, "" + lastMethodType); }
	 */

	/**
	 * remove the lastMethodType from session
	 */
	public abstract void removeLastMethodType();
	/*
	 * public void removeLastMethodType() {
	 * session_.removeAttribute(this.lastMethodType); }
	 */

	public abstract void setParameter(String name, String value);

	/*
	 * public void setParameter(String name,String value) {
	 * session_.setAttribute(name,value); }
	 */
	public abstract String getParameter(String name);

	/*
	 * public String getParameter(String name) { return (String)
	 * session_.getAttribute(name); }
	 */
	public abstract void setAttribute(String name, Object value);
	/*
	 * public void setAttribute(String name, Object value) {
	 * session_.setAttribute(name,value); }
	 */

	public abstract Object getAttribute(String name);
	/*
	 * public Object getAttribute(String name) { return
	 * session_.getAttribute(name); }
	 */

} // class