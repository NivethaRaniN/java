
package com.infrastructure.conrollerframework.controller;

/**
 * Used in conjunction with action classes to indicate to controlling servlet
 * what the next action should be following the execution of a given action. It
 * is the return object from the process() method of any action.
 */
public class ActionForward {
	/**
	 * Contanst to be used by c-tor. Determines that the next command should be
	 * handled in "GET" ways by the framework.
	 */
	static public String GET = "GET";

	/**
	 * Contanst to be used by MPSActionForward c-tor. Determines that the next
	 * command should be handled in "POST" ways by the framework.
	 */
	static public String POST = "POST";

	/**
	 * The name of the command as defined in the Portal Control file of the
	 * controlling servlet
	 */
	private String _commandName = null;

	private String _requestType = GET;

	/**
	 * Constroctor.
	 *
	 * @param commandName
	 *            Name of the next command to which the control is to be
	 *            forwarded.
	 * @param requestType
	 *            Type of the request (GET or POST).
	 */
	public ActionForward(String commandName, String requestType) {
		_commandName = commandName;

		if (requestType.equalsIgnoreCase(ActionForward.GET) || requestType.equalsIgnoreCase(ActionForward.POST)) {
			_requestType = requestType;
		}
	}

	/**
	 * Accessor for _commandName member variable.
	 *
	 * @return _commandName
	 */
	public String getCommand() {
		return _commandName;
	}

	/**
	 * Mutator for the _commandName member variable.
	 */
	public void setCommand(String commandName) {
		_commandName = commandName;
	}

	/**
	 * Accessor for c_requestType member variable.
	 *
	 * @return requestType
	 */
	public String getRequestType() {
		return _requestType;
	}

	/**
	 * Mutator for the _requestType member variable.
	 */
	public void setRequestType(String requestType) {
		if (requestType.equalsIgnoreCase(ActionForward.GET) || requestType.equalsIgnoreCase(ActionForward.POST)) {
			_requestType = requestType;
		}
	}
}
