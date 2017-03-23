
package com.infrastructure.conrollerframework.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.infrastructure.exception.InfrastructureException;

/**
 * This class represents a command object as defined in the Controlling Servlet
 * control file. Attributes associated with a command are a name, action class,
 * roles that the commands can execute.
 */
public class Command implements java.io.Serializable {
	public static final String COMMAND_TAG = "command";
	private static final String COMMAND_NAME_ATTR_TAG = "name";
	private static final String ACTION_CLASS_ATTR_TAG = "action-class";
	private static final String DEFAULT_COMMAND_ATTR_TAG = "default-command";
	private static final String DATASOURCE_ATTR_TAG = "data-source";
	private static final String ROLES_LIST_TAG = "accessable-roles";
	private static final String ROLE_TAG = "role";
	private static final String ROLE_VALUE_ATTR = "value";
	/**
	 * Name of the command
	 */
	private String _commandName = null;

	/**
	 * Roles of the users that has access to this command.
	 */
	private Collection _roleList = new ArrayList();

	/**
	 * Action class associated with this command. The action class contains the
	 * business Traceic to be executed for this command.
	 */
	private Class _actionClass = null;

	/**
	 * Specifies if this command is the default command or not.
	 */
	private boolean _isDefaultCommand = false;

	/**
	 * Specifies which data source to use for this command execution.
	 */
	private String _dataSourceName = null;

	/**
	 * Additional Attribute / Properties for the command
	 */
	private HashMap _commandProperties = new HashMap();

	/**
	 * Constructor takes an XML node to initialise the instance.
	 */
	public Command(Node node, String defaultDataSource) throws InfrastructureException {

		// Command name
		if ((node.getNodeName() == null) || (node.getNodeName().equalsIgnoreCase(COMMAND_TAG) == false)) {
			throw new InfrastructureException("Invalid node for command creation", 100);
		}

		NamedNodeMap attributes = node.getAttributes();
		Node cmdNameNode = attributes.getNamedItem(COMMAND_NAME_ATTR_TAG);

		if ((cmdNameNode == null) || ((_commandName = cmdNameNode.getNodeValue()) == null)) {
			throw new InfrastructureException("Command name for a command can not be null", 100);
		}

		Node actionClassNode = attributes.getNamedItem(ACTION_CLASS_ATTR_TAG);
		String actionClassName = null;
		if ((actionClassNode == null) || ((actionClassName = actionClassNode.getNodeValue()) == null)) {
			throw new InfrastructureException("Action Class name for a command can not be null", 100);
		}
		try {
			_actionClass = Class.forName(actionClassName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InfrastructureException(e.toString(), 100);
		}
		// Role for accessing command
		NodeList rolesNodeList = ((Element) node).getElementsByTagName(ROLES_LIST_TAG);
		Element roleElement = null;
		String roleValue = null;
		if (rolesNodeList != null && rolesNodeList.getLength() == 1) {
			NodeList roles = ((Element) rolesNodeList.item(0)).getElementsByTagName(ROLE_TAG);
			for (int index = 0; index < roles.getLength(); index++) {
				roleElement = (Element) roles.item(index);
				if ((roleElement != null)) {// && (roleElement.getNodeValue() !=
											// null)){
					roleValue = roleElement.getNodeValue();
					// If not value then get it from attribute
					if ((roleValue == null) && (roleElement.hasAttribute(ROLE_VALUE_ATTR))) {
						roleValue = roleElement.getAttribute(ROLE_VALUE_ATTR);
					}
					if (roleValue != null) {
						_roleList.add(roleValue);
					}
				}
			}
		}
		if (_roleList.size() == 0) {
			_roleList.add(Constants.PUBLIC);
		}
		// Default Command setting
		Node defaultCommandNode = attributes.getNamedItem(DEFAULT_COMMAND_ATTR_TAG);
		if (defaultCommandNode != null) {
			String defaultCommandValue = defaultCommandNode.getNodeValue();
			if ((defaultCommandValue != null) && defaultCommandValue.equalsIgnoreCase(Constants.BOOLEAN_TRUE) == true) {
				_isDefaultCommand = true;
			} else {
				_isDefaultCommand = false;
			}
		}

		Node dataSourceNode = attributes.getNamedItem(DATASOURCE_ATTR_TAG);
		if ((dataSourceNode != null) && (dataSourceNode.getNodeValue() != null)) {
			_dataSourceName = dataSourceNode.getNodeValue();
		} else {
			_dataSourceName = defaultDataSource;
		}

		// Read all additional command properties
		try {
			// Read all attributes
			NamedNodeMap attrList = node.getAttributes();
			Node attribute = null;
			for (int index = 0; index < attrList.getLength(); index++) {
				attribute = attrList.item(index);
				if (attribute != null) {
					_commandProperties.put(attribute.getNodeName(), attribute.getNodeValue());
				}
			}
			// Read all sub elements
			NodeList nodeList = node.getChildNodes();
			Node attrElement = null;
			for (int index = 0; index < nodeList.getLength(); index++) {
				attrElement = (Node) nodeList.item(index);
				if ((attrElement != null) && (attrElement.hasChildNodes() == false)) {
					_commandProperties.put(attrElement.getNodeName(), attrElement.getNodeValue());
				}
			}
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}

	/**
	 * Accessor for command.
	 *
	 * @return name of the command.
	 */
	public String getCommandName() {
		return _commandName;
	}

	/**
	 * Mutator for command name.
	 * 
	 * @param name
	 *            command name.
	 */
	public void setCommandName(String name) {
		_commandName = name;
	}

	/**
	 * Accessor for roles. User must be member of this role to access command.
	 * 
	 * @return roles for accessing this command.
	 */
	public Collection getRoles() {
		return _roleList;
	}

	/**
	 * Mutator for role.
	 * 
	 * @param role
	 *            for access to this command.
	 */
	public void addRole(String role) {
		if ((role != null) && (role.length() > 0)) {
			_roleList.add(role);
		}
	}

	/**
	 * Mutator for roles.
	 * 
	 * @param roles
	 *            collection of roles that can access this command.
	 */
	public void setRoles(Collection roles) {
		if ((roles != null) && (roles.size() > 0)) {
			_roleList = roles;
		} else if (roles != null) {
			_roleList = new ArrayList();
			_roleList.add(Constants.PUBLIC);
		}
	}

	/**
	 * Accessor for action class.
	 * 
	 * @return actionClass associated with this command.
	 */
	public Class getActionClass() {
		return _actionClass;
	}

	/**
	 * Mutator for action class.
	 * 
	 * @param actionClass
	 *            associated class with this command.
	 */
	public void setActionClass(Class actionClass) {
		if (actionClass != null) {
			_actionClass = actionClass;
		}
	}

	/**
	 * Returns true if the command is the default command for the application
	 * 
	 * @return true if the command is a default command.
	 */
	public boolean isDefaultCommand() {
		return _isDefaultCommand;
	}

	/**
	 * sets / unset s the command as the default command
	 * 
	 * @param isdefault
	 *            boolean to sets / unset s the command as the default command
	 */
	public void setDefaultCommand(boolean isdefault) {
		_isDefaultCommand = isdefault;
	}

	/**
	 * Returns command in an XML string format
	 */
	public String toString() {
		StringBuffer commandString = new StringBuffer("");
		commandString.append("<");
		commandString.append(COMMAND_TAG);
		commandString.append(" ");
		commandString.append(COMMAND_NAME_ATTR_TAG);
		commandString.append("=\"");
		commandString.append(_commandName);
		commandString.append("\" ");

		commandString.append(DEFAULT_COMMAND_ATTR_TAG);
		commandString.append("=\"");
		commandString.append((_isDefaultCommand ? Constants.BOOLEAN_TRUE : Constants.BOOLEAN_FALSE));
		commandString.append("\" ");

		commandString.append(ACTION_CLASS_ATTR_TAG);
		commandString.append("=\"");
		commandString.append(_actionClass.getName());
		commandString.append("\">");
		commandString.append("<");
		commandString.append(ROLES_LIST_TAG);
		commandString.append(">");
		for (Iterator itr = _roleList.iterator(); itr.hasNext();) {
			commandString.append("<");
			commandString.append(ROLE_TAG);
			commandString.append(">");
			commandString.append((String) itr.next());
			commandString.append("</");
			commandString.append(ROLE_TAG);
			commandString.append(">");
		}
		commandString.append("</");
		commandString.append(ROLES_LIST_TAG);
		commandString.append(">");
		commandString.append("</");
		commandString.append(COMMAND_TAG);
		commandString.append(">");
		return commandString.toString();
	}

	/**
	 * Returns the dataSourceName.
	 * 
	 * @return String
	 */
	public String getDataSourceName() {
		return _dataSourceName;
	}

	/**
	 * Sets the dataSourceName.
	 * 
	 * @param dataSourceName
	 *            The dataSourceName to set
	 */
	public void setDataSourceName(String dataSourceName) {
		_dataSourceName = dataSourceName;
	}

	/**
	 * Getter method to get a command property value
	 */
	public String getCommandProperty(String propertyName, String defaultValue) {
		try {
			if ((propertyName != null) || (_commandProperties.containsKey(propertyName))) {
				return (String) _commandProperties.get(propertyName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return defaultValue;
	}
} // End of class definition
