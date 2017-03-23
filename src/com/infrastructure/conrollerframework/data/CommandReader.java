
package com.infrastructure.conrollerframework.data;

import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.infrastructure.exception.InfrastructureException;

/**
 * Support class for loading and parsing the Portal Control file and generating
 * the hashtable of Command objects.
 * 
 * @see Command
 */
public class CommandReader {
	private static final String CONFIG_XML_ROOT_TAG = "application";
	private static final String DEFAULT_DATASOURCE_TAG = "default-datasource";

	/**
	 * URI for Portal Control file holding all the command definitions and
	 * mappings
	 */
	private String _commandFileURI = null;

	/**
	 * Holds the default data source name
	 */
	private String _defaultDataSourceName = null;
	/**
	 * Name of the default command to which the system forwards the page if
	 * something goes wrong
	 */
	private Command _defaultCommand = null;

	/**
	 * The command list holder
	 */
	private HashMap _commandList = null;

	/**
	 * Constructor takes URI to control file and initialises upon instantiation.
	 */
	public CommandReader(String commandFileURI) throws InfrastructureException {
		readCommandfile(commandFileURI);
	}

	/**
	 * Load or reload commands from control file.
	 */
	public void readCommandfile(String commandFileURI) throws InfrastructureException {
		System.out.println("Reading commands from the file: " + commandFileURI);
		_commandFileURI = commandFileURI;
		Document doc = readCommands();
		buildCommandMap(doc);
	}

	/**
	 * Parses the XML document from file.
	 * 
	 * @return document in parsed format.
	 */
	private Document readCommands() throws InfrastructureException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			return db.parse(_commandFileURI);
		} catch (FactoryConfigurationError e) {
			System.out
					.println("JAXP: DocumentBuilderFactory Implementation is not available or cannot be instantiated");
			throw new InfrastructureException(e.toString(), 100);
		} catch (ParserConfigurationException e) {
			System.out.println("JAXP: DocumentBuilder cannot be created which satisfies the configuration requested.");
			throw new InfrastructureException(e.toString(), 100);
		} catch (Exception e) {
			System.out.println("Error while parsing the file " + _commandFileURI);
			e.printStackTrace();
			throw new InfrastructureException(e.toString(), 100);
		}
	}

	/**
	 * Builds a hashtable of command objects from a command XML document.
	 *
	 * @param document
	 *            holds all command definitions and mappings.
	 */
	private void buildCommandMap(Document doc) throws InfrastructureException {
		_commandList = new HashMap();
		if (doc == null) {
			throw new InfrastructureException("Document for building command can not be null", 100);
		}

		Element root = doc.getDocumentElement();

		// Get all the child nodes of the Document object
		if (root.getNodeName().equalsIgnoreCase(CONFIG_XML_ROOT_TAG) == false) {
			throw new InfrastructureException("configuration file foremat error", 100);
		}

		_defaultDataSourceName = root.getAttribute(DEFAULT_DATASOURCE_TAG);
		if (_defaultDataSourceName == null) {
			throw new InfrastructureException("The default data source name must be specified", 100);
		}
		System.out.println("Default Data Source Name : " + _defaultDataSourceName);
		NodeList commandNodeList = root.getElementsByTagName(Command.COMMAND_TAG);

		if (commandNodeList != null) {
			Command command = null;
			for (int index = 0; index < commandNodeList.getLength(); index++) {
				command = new Command(commandNodeList.item(index), _defaultDataSourceName);
				if ((_defaultCommand != null) && (command.isDefaultCommand())) {
					throw new InfrastructureException("can not have more than one default commands", 100);
				} else if ((_defaultCommand == null) && (command.isDefaultCommand())) {
					_defaultCommand = command;
				}
				_commandList.put(command.getCommandName(), command);
			} // for
		}
		if (_defaultCommand == null) {
			throw new InfrastructureException("Any one command must be specified as default command", 100);
		}
	}

	/**
	 * Gets the command names.
	 * 
	 * @return command name list.
	 */
	public Set getCommandNames() throws InfrastructureException {
		if (_commandList == null) {
			throw new InfrastructureException("Get command list can not be requested before reading configuration file",
					100);
		}
		return _commandList.keySet();
	}

	/**
	 * Gets the command List.
	 * 
	 * @return commands Map.
	 */
	public Set getCommandList() throws InfrastructureException {
		if (_commandList == null) {
			throw new InfrastructureException("Get command list can not be requested before reading configuration file",
					100);
		}
		return _commandList.entrySet();
	}

	/**
	 * Gets the command object for a given command.
	 * 
	 * @return requested command.
	 */
	public Command getCommand(String command) throws InfrastructureException {
		return getCommand(command, false);
	}

	/**
	 * Gets the command object for a given command.
	 * 
	 * @return requested command. If the command does not exist and if send
	 *         default is set then it returns teh default command.
	 */
	public Command getCommand(String command, boolean sendDefault) throws InfrastructureException {
		if (_commandList == null) {
			throw new InfrastructureException("Get command list can not be requested before reading configuration file",
					100);
		}
		if (_commandList.containsKey(command) == true) {
			return (Command) _commandList.get(command);
		} else if (sendDefault == true) {
			return _defaultCommand;
		} else {
			throw new InfrastructureException("Invalid command name", 100);
		}
	}

	/**
	 * Get for control file URI member variable.
	 * 
	 * @param controlFile
	 *            URI containing commands and mappings.
	 */
	public String getCommandFileURI() {
		return _commandFileURI;
	}

	/**
	 * Accessor for the name of the default command for the system.
	 *
	 * @return Default Commands Name
	 */
	public Command getDefaultCommand() {
		return _defaultCommand;
	}
} // End of class definition
