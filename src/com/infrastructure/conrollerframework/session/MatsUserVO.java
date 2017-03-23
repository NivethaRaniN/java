package com.infrastructure.conrollerframework.session;

import java.util.HashMap;
import java.util.Locale;

import com.infrastructure.conrollerframework.data.Constants;
import com.infrastructure.conrollerframework.data.VO;
import com.infrastructure.exception.InfrastructureException;
import com.infrastructure.exception.ReturnCode;

public class MatsUserVO extends VO {

	/**
	 * String session id delimiter
	 */
	private static final String DELIMITER = ":";

	/**
	 * session internal id
	 */
	private String _sessionId = null;

	/**
	 * user loggin message id
	 */
	private String _loginMessageId = null;

	/**
	 * session last used time
	 */
	private long _lastused;

	/**
	 * Logged in user name
	 */
	private String _userName = null;

	/**
	 * logged in user type eg;
	 */
	private String _userType = null;

	/**
	 * user loggin id
	 */
	private String _userLoginId = null;

	/**
	 * The user communication language for the session
	 */
	private Locale _sessionLocale = null;

	private boolean _isValid = true;

	/**
	 * The user communication language for the session
	 */

	/**
	 * The user Hashmap for storing any desired properties
	 */
	private HashMap _sessionPropertyList = new HashMap();

	/**
	 * The user Employee ID from LDAP
	 */
	private String _empID = null;

	/**
	 * The user's entry DN
	 */

	private String _entryDN = null;

	/**
	 * The user's user ID
	 */
	private String _userID = null;

	/**
	 * The user's App Access
	 */
	private String _appAccess = null;

	/**
	 * The user phone Number from LDAP
	 */

	private String _phoneNum = null;

	/**
	 * The user's address from LDAP
	 */

	private String _address = null;

	/**
	 * The user's e-mail ID from LDAP
	 */

	private String _mailID = null;

	/**
	 * The user fax Number from LDAP
	 */

	private String _faxNum = null;

	/**
	 * The user Mobile Number from LDAP
	 */

	private String _mobileNum = null;

	/**
	 * The user Company Name from LDAP
	 */

	private String _CpnyCd = null;

	/**
	 * The user Plant Code from LDAP
	 */

	private String _PlantCd = null;

	/**
	 * The user department name from LDAP
	 */

	private String _deptName = null;

	/**
	 * The user Function from LDAP
	 */

	private String _function = null;

	/**
	 * The user Employee line from LDAP
	 */

	private String _line = null;

	/**
	 * The user Employee Cost Center from LDAP
	 */

	private String _CostCenter = null;

	/**
	 * The user's managers's Employee ID from LDAP
	 */

	private String _manager = null;

	/**
	 * The user assistant manager's Employee ID from LDAP
	 */

	private String _asstMgr = null;

	/**
	 * The user's general manager's Employee ID from LDAP
	 */
	private String _genMgr = null;

	private String _givenName = null; // This is first name

	private String _cn = null; // This is common name

	private String _sn = null; // This is family name

	public MatsUserVO(String userName) throws InfrastructureException {
		if (userName != null) {
			_userName = userName;
		} else {
			throw new InfrastructureException("User Name Can not be null in a session",
					ReturnCode.INPUT_PARAMETER_IS_NULL);
		}

	}

	public MatsUserVO() {

	}

	/**
	 * Construct a UserSessionImpl with an identifier. Default values for the
	 * timeout (10 minutes) and the level (2) are used.
	 *
	 * @param organization
	 *            organization of the user
	 * @param contract
	 *            contract number of the user
	 * @param user
	 *            user login name
	 * @param hostname
	 *            the hostname/IP address of the requesting host.
	 */
	public MatsUserVO(String userName, String system, String userType, String loginId, Locale locale)
			throws InfrastructureException {
		this(userName);
		_lastused = System.currentTimeMillis();
		if (userType != null) {
			_userType = userType;
		} else {
			throw new InfrastructureException("User Type Can not be null in a session",
					ReturnCode.INPUT_PARAMETER_IS_NULL);
		}
		if (loginId != null) {
			_userLoginId = loginId;
		} else {
			throw new InfrastructureException("User Login ID Can not be null in a session",
					ReturnCode.INPUT_PARAMETER_IS_NULL);
		}
		if (locale != null) {
			_sessionLocale = locale;
		} else {
			_sessionLocale = new Locale(Constants.DEFAULT_LANGUAGE, Constants.DEFAULT_COUNTRY);
		}

		_sessionId = generateID();
	}

	/**
	 * The identification of a system session instance is constructed of several
	 * fields which make it unambigous.
	 * 
	 * @returns an unambigous identification of this session instance.
	 */
	public String getId() {
		return _sessionId;
	}

	/**
	 * Sets the server message id generated for the login service request in the
	 * DispatcherBean. This id is used for correlating the login request with
	 * all subsequent requests in the audit log. The audit log will print the
	 * ServerMessageId of the login request which will be the same as the
	 * LoginMsgId which will be printed for all requests issued by the same
	 * user.
	 */
	public void setLoginMessageId(String loginMessageId) {
		_loginMessageId = loginMessageId;
	}

	public String getLoginMessageId() {
		return _loginMessageId;
	}

	/**
	 * Set the timestamp when the last use of the session occurred.
	 */
	public void setLastUse() {
		_lastused = System.currentTimeMillis();
	}

	/**
	 * Get the timestamp since the last access to the systemsession
	 * 
	 * @return timestamp in millisecond
	 */
	public long getLastUse() {
		return _lastused;
	}

	/**
	 * String representation of the SystemSession
	 * 
	 * @return info about the system session
	 */
	public String toString() {
		return _sessionId;
	}

	private String generateID() {
		StringBuffer buf = new StringBuffer();
		buf.append(_userLoginId);
		buf.append(DELIMITER);

		return buf.toString();
	}

	/**
	 * To get the user login id
	 *
	 */
	public String getUserId() {
		return _userLoginId;
	}

	/**
	 * To get the user Name
	 *
	 */
	public String getUserName() {
		return _userName;
	}

	/**
	 * To get the user Type
	 *
	 */
	public String getUserType() {
		return _userType;
	}

	/**
	 * To set the user login id
	 *
	 */
	public void setUserId(String id) {
		_userLoginId = id;
	}

	/**
	 * To set the user name
	 *
	 */
	public void setUserName(String userName) {
		_userName = userName;
	}

	/**
	 * To set the user type
	 *
	 */
	public void setUserType(String userType) {
		_userType = userType;
	}

	/**
	 * Returns the sessionLocale.
	 * 
	 * @return Locale
	 */
	public Locale getSessionLocale() {
		return _sessionLocale;
	}

	/**
	 * Sets the sessionLocale.
	 * 
	 * @param sessionLocale
	 *            The sessionLocale to set
	 */
	public void setSessionLocale(Locale sessionLocale) {
		_sessionLocale = sessionLocale;
	}

	/**
	 * Returns the sessionProperty of a given key , null if the key is not
	 * defined.
	 * 
	 * @return SessionProperty value
	 */
	public Object getSessionProperty(String key) {
		if (_sessionPropertyList.containsKey(key)) {
			return _sessionPropertyList.get(key);
		}
		return null;
	}

	/**
	 * Sets the sessionProperty of a given key , null if the key is not
	 * defined..
	 * 
	 * @param key
	 *            SessionProperty key ,
	 * @param Object
	 *            SessionProperty value
	 */
	public void setSessionProperty(String key, Object value) {
		_sessionPropertyList.put(key, value);
	}

	/**
	 * Sets the user Validity
	 * 
	 * @param isValid
	 */

	public void setValidUser(boolean valid) {
		_isValid = valid;
	}

	/**
	 * Returns the validity of the user
	 * 
	 * @return isValid value
	 */

	public boolean isValidUser() {
		return _isValid;
	}

	/**
	 * Returns the employee ID of the user
	 * 
	 * @return empID value
	 */

	public String getempID() {
		return _empID;
	}

	/**
	 * Returns the entry DN of the user
	 * 
	 * @return entryDN value
	 */

	public String getentryDN() {
		return _entryDN;
	}

	/**
	 * Returns the user ID of the user
	 * 
	 * @return userID value
	 */

	public String getuserID() {
		return _userID;
	}

	/**
	 * Returns the application Accessed
	 * 
	 * @return appAccess value
	 */

	public String getappAccess() {
		return _appAccess;
	}

	/**
	 * Returns the phone number of the user
	 * 
	 * @return isValid value
	 */

	public String getphoneNum() {
		return _phoneNum;
	}

	/**
	 * Returns the address of the user
	 * 
	 * @return address value
	 */

	public String getaddress() {
		return _address;
	}

	/**
	 * Returns the mail ID of the user
	 * 
	 * @return mailID value
	 */

	public String getmailID() {
		return _mailID;
	}

	/**
	 * Returns the fax Number of the user
	 * 
	 * @return faxNum value
	 */

	public String getfaxNum() {
		return _faxNum;
	}

	/**
	 * Returns the mobile Number of the user
	 * 
	 * @return mobileNum value
	 */

	public String getmobileNum() {
		return _mobileNum;
	}

	/**
	 * Returns the company name of the user
	 * 
	 * @return CpnyName value Added elseif for TAW by Ramesh on Saturday,
	 *         November 06, 2004
	 */

	public String getCpnyCd() {
		if (_CpnyCd.equalsIgnoreCase("TMT"))
			return "T";
		else if (_CpnyCd.equalsIgnoreCase("TABT"))
			return "B";
		else if (_CpnyCd.equalsIgnoreCase("TAW"))
			return "W";
		else
			return _CpnyCd;
	}

	/**
	 * Returns the Plant Code of the user
	 * 
	 * @return PlantCd value
	 */

	public String getplantCd() {
		return _PlantCd;
	}

	/**
	 * Returns the department name of the user
	 * 
	 * @return deptName value
	 */

	public String getdeptName() {
		return _deptName;
	}

	/**
	 * Returns the function of the user
	 * 
	 * @return function value
	 */

	public String getfunction() {
		return _function;
	}

	/**
	 * Returns the line of the user
	 * 
	 * @return line value
	 */

	public String getline() {
		return _line;
	}

	/**
	 * Returns the Cost Center of the user
	 * 
	 * @return CostCenter value
	 */

	public String getCostCenter() {
		return _CostCenter;
	}

	/**
	 * Returns the manager employee ID of the user
	 * 
	 * @return manager value
	 */

	public String getmanager() {
		return _manager;
	}

	/**
	 * Returns the assistant manager employee ID of the user
	 * 
	 * @return asstMgr value
	 */

	public String getasstMgr() {
		return _asstMgr;
	}

	/**
	 * Returns the general manager ID of the user
	 * 
	 * @return genMgr value
	 */

	public String getgenMgr() {
		return _genMgr;
	}

	/**
	 * Sets the user employee number
	 * 
	 * @param empID
	 */

	public void setempID(String empID) {
		_empID = empID;
	}

	/**
	 * Sets the user entry DN
	 * 
	 * @param entryDN
	 */

	public void setentryDN(String entryDN) {
		_entryDN = _entryDN;
	}

	/**
	 * Sets the user userID
	 * 
	 * @param userID
	 */

	public void setuserID(String userID) {
		_userID = userID;
	}

	/**
	 * Sets the user Application Accessed
	 * 
	 * @param appAccess
	 */

	public void setappAccess(String appAccess) {
		_appAccess = appAccess;
	}

	/**
	 * Sets the user phone number
	 * 
	 * @param phoneNum
	 */

	public void setphoneNum(String phoneNum) {
		_phoneNum = phoneNum;
	}

	/**
	 * Sets the user address
	 * 
	 * @param address
	 */

	public void setaddress(String address) {
		_address = address;
	}

	/**
	 * Sets the user mailID
	 * 
	 * @param mailID
	 */
	public void setmailID(String mailID) {
		_mailID = mailID;
	}

	/**
	 * Sets the user fax Number
	 * 
	 * @param faxNum
	 */
	public void setfaxNum(String faxNum) {
		_faxNum = faxNum;
	}

	/**
	 * Sets the user mobile number
	 * 
	 * @param mobileNum
	 */
	public void setmobileNum(String mobileNum) {
		_mobileNum = mobileNum;
	}

	/**
	 * Sets the user Company Name
	 * 
	 * @param CpnyName
	 */
	public void setCpnyCd(String CpnyCd) {
		_CpnyCd = CpnyCd;
	}

	/**
	 * Sets the user Plant Code
	 * 
	 * @param PlantCd
	 */
	public void setplantCd(String plantCd) {
		_PlantCd = plantCd;
	}

	/**
	 * Sets the user department name
	 * 
	 * @param deptName
	 */
	public void setdeptName(String deptName) {
		_deptName = deptName;
	}

	/**
	 * Sets the user function
	 * 
	 * @param function
	 */

	public void setfunction(String function) {
		_function = function;
	}

	/**
	 * Sets the user line
	 * 
	 * @param line
	 */

	public void setline(String line) {
		_line = line;
	}

	/**
	 * Sets the user Cost Center
	 * 
	 * @param CostCenter
	 */
	public void setCostCenter(String CostCenter) {
		_CostCenter = CostCenter;
	}

	/**
	 * Sets the user manager employee ID
	 * 
	 * @param manager
	 */
	public void setmanager(String manager) {
		_manager = manager;
	}

	/**
	 * Sets the user's assistant manager
	 * 
	 * @param asstMgr
	 */
	public void setasstMgr(String asstMgr) {
		_asstMgr = asstMgr;
	}

	/**
	 * Sets the user's general manager ID
	 * 
	 * @param genMgr
	 */
	public void setgenMgr(String genMgr) {
		_genMgr = genMgr;
	}

	/**
	 * This Method outputs the root element
	 * 
	 * @return <code> String </code> - name of the root element and it's
	 *         attributes in xml form
	 */
	public String getVOName() {
		return "MatsSessionImpl";
	} // getVOName

	/**
	 * Returns the _cn.
	 * 
	 * @return String
	 */
	public String get_cn() {
		return _cn;
	}

	/**
	 * Returns the _givenName.
	 * 
	 * @return String
	 */
	public String get_givenName() {
		return _givenName;
	}

	/**
	 * Returns the _sn.
	 * 
	 * @return String
	 */
	public String get_sn() {
		return _sn;
	}

	/**
	 * Sets the _cn.
	 * 
	 * @param _cn
	 *            The _cn to set
	 */
	public void set_cn(String _cn) {
		this._cn = _cn;
	}

	/**
	 * Sets the _givenName.
	 * 
	 * @param _givenName
	 *            The _givenName to set
	 */
	public void set_givenName(String _givenName) {
		this._givenName = _givenName;
	}

	/**
	 * Sets the _sn.
	 * 
	 * @param _sn
	 *            The _sn to set
	 */
	public void set_sn(String _sn) {
		this._sn = _sn;
	}

}
