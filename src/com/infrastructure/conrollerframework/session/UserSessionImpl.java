
package com.infrastructure.conrollerframework.session;

import java.util.HashMap;
import java.util.Locale;

import com.infrastructure.conrollerframework.data.VO;

public class UserSessionImpl extends VO implements UserSession {

	/**
	 * String session id delimiter
	 */
	private static final String DELIMITER = ":";

	/**
	 * The session key generator
	 */

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
	 * user logged in system id
	 */
	private String _userSystemId = null;

	/**
	 * user supplier code (Only for supplier code)
	 */
	private String _userSupplierCode = null;

	/**
	 * user supplier code (Only for supplier code)
	 */
	private String _userOrgName = null;

	/**
	 * The user communication language for the session
	 */
	private Locale _sessionLocale = null;

	private boolean _isValid = true;

	/**
	 * The user communication language for the session
	 */
	private HashMap _sessionPropertyList = new HashMap();
	private String _empID = null;
	private String _entryDN = null;
	private String _userID = null;
	private String _appAccess = null;
	private String _phoneNum = null;
	private String _address = null;
	private String _mailID = null;
	private String _faxNum = null;
	private String _mobileNum = null;
	private String _CpnyName = null;
	private String _deptName = null;
	private String _function = null;
	private String _line = null;
	private String _CostCenter = null;
	private String _manager = null;
	private String _asstMgr = null;
	private String _genMgr = null;
	private String _plantCd = null;

	public UserSessionImpl() {

	}

	public UserSessionImpl(String userName) {
		System.out.println(userName);

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
	public UserSessionImpl(String userName, String system, String userType, String loginId, Locale locale) {
		this(userName);
		_lastused = System.currentTimeMillis();
		if (system != null) {
			_userSystemId = system;
		}
		if (userType != null) {
			_userType = userType;
		}
		if (loginId != null) {
			_userLoginId = loginId;
		}
		if (locale != null) {
			_sessionLocale = locale;
		}
		_sessionId = generateID();
	}

	public UserSessionImpl(String userName, String system, String userType, String loginId, String suplCode,
			Locale locale) {
		this(userName, system, userType, loginId, locale);
		_userSupplierCode = suplCode;
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

	private String generateID() {
		StringBuffer buf = new StringBuffer();
		buf.append(_userLoginId);
		if (_userSupplierCode != null) {
			buf.append(DELIMITER);
			buf.append(_userSupplierCode);
		}
		buf.append(DELIMITER);
		buf.append(_userSystemId);
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
	 * To get the user logged in system
	 *
	 */
	public String getUserLoginSystem() {
		return _userSystemId;
	}

	/**
	 * To get the user Name
	 *
	 */
	public String getUserName() {
		return _userName;
	}

	/**
	 * To get the user supplier code (Applicable only for supplier users)
	 *
	 */
	public String getUserSupplierCode() {
		return _userSupplierCode;
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
	 * To set the user logged in system
	 *
	 */
	public void setUserLoginSystem(String system) {
		_userSystemId = system;
	}

	/**
	 * To set the user name
	 *
	 */
	public void setUserName(String userName) {
		_userName = userName;
	}

	public void setPlantCode(String plantCode) {
		_plantCd = plantCode;
	}

	/**
	 * To set the user supplier code (Applicable only for supplier users)
	 *
	 */
	public void setUserSupplierCode(String supplcode) {
		_userSupplierCode = supplcode;
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
	 * Returns the userOrgName.
	 * 
	 * @return String
	 */
	public String getUserOrgName() {
		return _userOrgName;
	}

	/**
	 * Sets the userOrgName.
	 * 
	 * @param userOrgName
	 *            The userOrgName to set
	 */
	public void setUserOrgName(String userOrgName) {
		_userOrgName = userOrgName;
	}

	public void setValidUser(boolean valid) {
		_isValid = valid;
	}

	public boolean isValidUser() {
		return _isValid;
	}

	public String getempID() {
		return _empID;
	}

	public String gePlantCode() {
		return _plantCd;
	}

	public String getentryDN() {
		return _entryDN;
	}

	public String getuserID() {
		return _userID;
	}

	public String getappAccess() {
		return _appAccess;
	}

	public String getphoneNum() {
		return _phoneNum;
	}

	public String getaddress() {
		return _address;
	}

	public String getmailID() {
		return _mailID;
	}

	public String getfaxNum() {
		return _faxNum;
	}

	public String getmobileNum() {
		return _mobileNum;
	}

	public String getCpnyName() {
		return _CpnyName;
	}

	public String getdeptName() {
		return _deptName;
	}

	public String getfunction() {
		return _function;
	}

	public String getline() {
		return _line;
	}

	public String getCostCenter() {
		return _CostCenter;
	}

	public String getmanager() {
		return _manager;
	}

	public String getasstMgr() {
		return _asstMgr;
	}

	public String getgenMgr() {
		return _genMgr;
	}

	public void setempID(String empID) {
		_empID = empID;
	}

	public void setentryDN(String entryDN) {
		_entryDN = _entryDN;
	}

	public void setuserID(String userID) {
		_userID = userID;
	}

	public void setappAccess(String appAccess) {
		_appAccess = appAccess;
	}

	public void setphoneNum(String phoneNum) {
		_phoneNum = phoneNum;
	}

	public void setaddress(String address) {
		_address = address;
	}

	public void setmailID(String mailID) {
		_mailID = mailID;
	}

	public void setfaxNum(String faxNum) {
		_faxNum = faxNum;
	}

	public void setmobileNum(String mobileNum) {
		_mobileNum = mobileNum;
	}

	public void setCpnyName(String CpnyName) {
		_CpnyName = CpnyName;
	}

	public void setdeptName(String deptName) {
		_deptName = deptName;
	}

	public void setfunction(String function) {
		_function = function;
	}

	public void setline(String line) {
		_line = line;
	}

	public void setCostCenter(String CostCenter) {
		_CostCenter = CostCenter;
	}

	public void setmanager(String manager) {
		_manager = manager;
	}

	public void setasstMgr(String asstMgr) {
		_asstMgr = asstMgr;
	}

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
		return "UserSessionImpl";
	} // getVOName

}