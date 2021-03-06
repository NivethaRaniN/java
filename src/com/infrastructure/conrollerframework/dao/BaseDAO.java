
package com.infrastructure.conrollerframework.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;

import com.infrastructure.conrollerframework.data.VI;
import com.infrastructure.conrollerframework.data.VO;

/**
 * This is the base class for all Data Access Objects. It provides all the base
 * functionality which all the DAOs need.
 */
public class BaseDAO {
	private static final String IMV_DATASOURCE_PROPERTY = "imv.system.datasource";
	private static final String IMV_DATASOURCE_DEFAULT = "imv.system.oraclePool";
	private static final String MAXIMUM_NO_RETURN_RECORDS_PROPERTY = "imv.system.maximum-return-records";

	private String _dataSourceName = null;

	// the number of records feteched by DAO will not exceed this value
	// If this value is null then the check for max records is disabled
	private int _systemLimit = 0;
	private boolean _initRequired = true;
	private InitialContext _context = null;

	/**
	 * The DAO Constructor the constructs the DAO and initializes the Data
	 * source name to be used for connection
	 * 
	 * @param dataSourceName
	 *            the data source name to be used
	 */
	public BaseDAO(String dataSourceName) {
		_dataSourceName = dataSourceName;

	}

	/**
	 * Method to do initialize the data source JNDI name and max-number of
	 * records. It uses the Configur0ationManager to initialize the variables.
	 * <p>
	 * Executes only once in the life of a DAO.
	 */
	protected void init() {
		// Get the Imv property for maximum number of records

		_initRequired = false;

	} // init

	/**
	 * This getConnection method will get the db connection from the pool and
	 * then check if parametrization is required. If so then it parametrizes it.
	 * In case the connection is not parametrized, the instance returned will be
	 * the same as provided by the app.server/DBTechAdapter. In case the
	 * connection is parametrized, then a ParametrizedConnection which holds the
	 * db connection will be returned.
	 * </p>
	 * 
	 * @param nimEnv
	 *            the environment object from the dispatcher.
	 * @return <code> Connection </code> - Database Connection Object
	 * @throws ImvDataSourceException
	 *             when a connection cannot be obtained from the pool.
	 */
	protected Connection getConnection() throws Exception {

		if (_initRequired) {
			init();
		}
		return null;
	}

	/**
	 * Executes the SQL query in the PreparedStatement object and returns the
	 * result set generated by the query. Basically, it calls the executeQuery()
	 * method on the PreparedStatement object.
	 * 
	 * @return a ResultSet that contains the data produced by the query; never
	 *         null
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public static ResultSet executeQuery(PreparedStatement pstmt) throws SQLException {
		return pstmt.executeQuery();
	}

	/**
	 * Executes the SQL query in the CallableStatement object and returns the
	 * result set generated by the query. Basically, it calls the executeQuery()
	 * method on the PreparedStatement object.
	 * 
	 * @return a ResultSet that contains the data produced by the query; never
	 *         null
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public static ResultSet executeQuery(CallableStatement cstmt) throws SQLException {
		return cstmt.executeQuery();
	}

	/**
	 * Executes the SQL INSERT, UPDATE or DELETE statement on the
	 * PreparedStatement object. Basically, it calls the executeUpdate() method
	 * on the PreparedStatement object.
	 * 
	 * @param pstmt
	 *            An object that represents a precompiled SQL statement. A SQL
	 *            statement is pre-compiled and stored in a PreparedStatement
	 *            object.
	 * @return the row count for the INSERT, UPDATE or DELETE statement
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public static int executeUpdate(PreparedStatement pstmt) throws SQLException {
		return pstmt.executeUpdate();
	}

	/**
	 * Executes the SQL INSERT, UPDATE or DELETE statement on the
	 * CallableStatement object. Basically, it calls the executeUpdate() method
	 * on the CallableStatement object.
	 * 
	 * @param pstmt
	 *            An object that represents a precompiled SQL statement. A SQL
	 *            statement is pre-compiled and stored in a PreparedStatement
	 *            object.
	 * @return the row count for the INSERT, UPDATE or DELETE statement
	 * @throws SQLException
	 *             if a database access error occurs
	 */
	public static int executeUpdate(CallableStatement cstmt) throws SQLException {
		return cstmt.executeUpdate();
	}

	public static void close(Connection con, Statement pstmt) {
		closeStatement(pstmt);
		closeConnection(con);
	}

	public static void close(Connection con, Statement pstmt, ResultSet result) {
		closeResultSet(result);
		closeStatement(pstmt);
		closeConnection(con);
	}

	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqle) {

			} // catch
		} // if
	} // closeStatement

	/**
	 * <p>
	 * Close JDBC Connection
	 * </p>
	 * 
	 * @param dbConnection
	 *            <code> Connection </code> connection to be closed.
	 */
	public static void closeConnection(Connection dbConnection) {
		try {
			if (dbConnection != null && !dbConnection.isClosed()) {
				dbConnection.close();
			} // if
		} catch (SQLException sqle) {

		} // catch
	} // closeConnection

	/**
	 * <p>
	 * Close the JDBC Prepared Statement
	 * </p>
	 * 
	 * @param pStmt
	 *            <code> PreparedStatement </code> prepared statement to be
	 *            closed.
	 */
	public static void closePreparedStatement(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqle) {

			} // catch
		} // if
	} // closePreparedStatement

	/**
	 * <p>
	 * Close the JDBC ResultSet Object
	 * </p>
	 * 
	 * @param result
	 *            <code> ResultSet </code> ResultSet to be closed.
	 */
	public static void closeResultSet(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (SQLException sqle) {

			} // catch
		} // if
	} // closeResultset

	/**
	 * Creates a PreparedStatement from a queryString and a database conection
	 * 
	 * @param queryStr
	 *            String containing the SQL statement
	 * @param con
	 *            a connection to the database
	 * @throws SQLException
	 *             SQLException if a database access error occurs
	 * @return PreparedStatement a new or cached PreparedStatement
	 */
	protected PreparedStatement createPreparedStatement(String queryStr, Connection con) throws SQLException {
		PreparedStatement pstmt = null;
		String key = new String(queryStr + con);

		pstmt = con.prepareStatement(queryStr);
		return pstmt;
	}

	/**
	 * This method implements the transfer of database data given in a ResultSet
	 * to a value object.
	 * 
	 * <PRE>
	 * protected VO populateVO(ResultSet rs) throws SQLExceptin {
	 *
	 * 	// create a new concrete VO instance
	 * 	AccountVO accountVO = new AccountVO();
	 *
	 * 	// transfer data from ResultSet to the value object
	 * 	accountVO.setAccountNumber(rs.getBigDecimal(1));
	 * 	accountVO.setAccountBalance(rs.getBigDecimal(2));
	 *
	 * 	return accountVO;
	 * }
	 * </PRE>
	 * 
	 * @param rs
	 *            ResultSet returned from a SQL statement
	 * @throws SQLException
	 *             if a database access error occurs
	 * @return a VO object that contains the data produced by the query; never
	 *         null
	 */
	protected VO populateVO(ResultSet rs) throws SQLException {
		throw new UnsupportedOperationException(
				"populateVO() is not supported in DAO and expected to be over-ritten by sub DAO classes");
	}

	/**
	 * Creates a PreparedStatement object for inserting a new row into the
	 * database. The SQL statement is pre-compiled and must be filled with the
	 * corresponding values from the valueObject.
	 * 
	 * <PRE>
	 * protected PreparedStatement createPreparedStatement(VO valueObject, Connection con) throws SQLException {
	 * // first type cast the primary key to the actual type
	 *    AccountVO accountVO  = (AccountVO)values;
	 *
	 * // define the SQL string
	 *   String queryStr = "INSERT INTO ODS_V_ACCOUNTS_W ( ACCOUNT_NO, ACCOUNT_BALANCE) VALUES (?,?)";
	 *
	 * // create the prepared statement
	 *   PreparedStatement pstmt = createPreparedStatement(queryStr,con);
	 *
	 * // set the paramter to the primary key values
	 *   pstmt.setString(1, accountVO.getAccountNumber());
	 *   pstmt.setBigDecimal(2,accountVO.getAccountBalance()));
	 *
	 * // return the PreparedStatement
	 *   return pstmt;
	 * </PRE>
	 * 
	 * @return a new or cached PreparedStatement object containing the
	 *         pre-compiled statement
	 * @param valueObject
	 *            a VO object that contains the data to be inserted into the row
	 * @param con
	 *            a connection to the database
	 * @throws SQLException
	 *             SQLException if a database access error occurs
	 */
	protected PreparedStatement createPreparedStatement(VO valueObject, Connection con) throws SQLException {
		throw new UnsupportedOperationException(
				"createPreparedStatement() is not supported in DAO and expected to be over-ritten by sub DAO classes");
	}

	/**
	 * Creates a CallableStatement object for inserting a new row into the
	 * database. The SQL statement is pre-compiled and must be filled with the
	 * corresponding values from the valueObject.
	 * 
	 * <PRE>
	 * protected PreparedStatement createCallableStatement(VO valueObject, Connection con) throws SQLException {
	 * // first type cast the primary key to the actual type
	 *    AccountVO accountVO  = (AccountVO)values;
	 *
	 * // define the SQL string
	 *   String queryStr = "INSERT INTO ODS_V_ACCOUNTS_W ( ACCOUNT_NO, ACCOUNT_BALANCE) VALUES (?,?)";
	 *
	 * // create the prepared statement
	 *   CallableStatement pstmt = createCallableStatement(queryStr,con);
	 *
	 * // set the paramter to the primary key values
	 *   pstmt.setString(1, accountVO.getAccountNumber());
	 *   pstmt.setBigDecimal(2,accountVO.getAccountBalance()));
	 *
	 * // return the PreparedStatement
	 *   return pstmt;
	 * </PRE>
	 * 
	 * @return a new or cached CallableStatement object containing the
	 *         pre-compiled statement
	 * @param valueObject
	 *            a VO object that contains the data to be inserted into the row
	 * @param con
	 *            a connection to the database
	 * @throws SQLException
	 *             SQLException if a database access error occurs
	 */
	protected CallableStatement createCallableStatement(VO valueObject, Connection con) throws SQLException {
		throw new UnsupportedOperationException(
				"createCallableStatement() is not supported in DAO and expected to be over-ritten by sub DAO classes");
	}

	/**
	 * Creates a PreparedStatement object for find all rows in a certain view or
	 * table in the database.
	 * 
	 * <PRE>
	 * protected PreparedStatement findAllPreparedStatement(Connection con)
	 * throws SQLException { // build the query string String queryStr = "SELECT
	 * ACCOUNT_NO,ACCOUNT_BALANCE FROM ODS_V_ACCOUNTS_R"; // create the prepared
	 * statement
	 *
	 * PreparedStatement pstmt = createPreparedStatement(queryStr,con); return
	 * pstmt; }
	 *
	 * @param con
	 *            con a connection to the database
	 * @throws SQLException
	 *             SQLException if a database access error occurs
	 * @return a new or cached PreparedStatement object containing the
	 *         pre-compiled statement
	 */
	protected PreparedStatement findAllPreparedStatement(Connection con) throws SQLException {
		throw new UnsupportedOperationException(
				"findAllPreparedStatement() is not supported in DAO and expected to be over-ritten by sub DAO classes");
	}

	// ##################################################################################################
	// F O R S T O R E D P R O C E D U R E S

	/**
	 * Creates a CallableStatement object for accessing a stored procedure in
	 * the database. The statement must be filled with the corresponding values
	 * from the valueObject object.
	 * 
	 * <PRE>
	 * protected CallableStatement createCallableStatement(String prepareString, VO valueObject, Connection con) throws SQLException {
	 * // first type cast the VO to the actual type
	 * LoginSVO lvo  = (LoginSVO)valueObject;
	 *
	 * // the SQL string for the stored procedure call is defined in prepareString, e.g.
	 * // "{call ods_access_control.customer_login(?,?,?,?,?,?,?,?,?,?,?,?)}"
	 *
	 * // create the callable statement
	 * CallableStatement cs = createCallableStatement(prepareString,con);
	 *
	 * // register the out parameters
	 *  cs.registerOutParameter(1, Types.INTEGER); // p_rc
	 *  cs.registerOutParameter(2, Types.INTEGER); // P_orun_bc_no (2 is an INOUT parameter)
	 * 	  cs.registerOutParameter(8, Types.VARCHAR); // p_acs
	 *  ...
	 * // set input values
	 *  cs.setString(4, svo.getLoginName()); // p_user_name
	 * 		cs.setString(5, svo.getPwd()); // p_pwd (plain text)
	 *  ...
	 *
	 * // return the CallableStatement
	 * return cs;
	 * </PRE>
	 * 
	 * @return a new or cached PreparedStatement object containing the
	 *         pre-compiled statement
	 * @param valueObject
	 *            a VO object that contains the data to be inserted into the row
	 * @param con
	 *            a connection to the database
	 * @throws SQLException
	 *             SQLException if a database access error occurs
	 * @param prepareString
	 *            Defines stored procedure call (positional parameter notation)
	 */
	protected CallableStatement createCallableStatement(String prepareString, VO valueObject, Connection con)
			throws SQLException {
		throw new UnsupportedOperationException(
				"createCallableStatement() is not supported in DAO and expected to be overwritten by sub DAO classes");
	}

	/**
	 * Creates a CallableStatement from a prepareString and a database conection
	 * 
	 * @param prepareString
	 *            String containing the SQL statement for the stored procedure
	 *            call
	 * @param con
	 *            a connection to the database
	 * @throws SQLException
	 *             SQLException if a database access error occurs
	 * @return CallableStatement a new CallableStatement
	 */
	protected CallableStatement createCallableStatement(String prepareString, Connection con) throws SQLException {

		// caching can be implemented here..
		return con.prepareCall(prepareString);
	} // createCallableStatement

	/**
	 * Executes the CallableStatement to retrieve data from the database in a VI
	 * (either a VO or CollectionVO can be returned). As in the case of a view
	 * or table access it internally uses populate to transfer the results to a
	 * new VO or CollectionVO instance. Since it depends on the stored procedure
	 * if there is none, one or more resultsets, the subclass has to implement
	 * how the results are extracted from the CallableStatement in the populate
	 * method.
	 * 
	 * @param cs
	 *            a CallableStatement object containing the prepared statement
	 * @throws SQLException
	 *             SQLException if a database access error occurs
	 * @return a VI containing all data of a stored procedure described in the
	 *         CallableStatement.
	 */
	// result sets or update numbers must be fetched in populate VO
	protected VI execute(CallableStatement cs) throws SQLException {

		cs.execute();
		// Since it depends on the stored procedure if there is zero, one or
		// more resultsets,
		// the subclass has to implement how the results are extracted from the
		// CallableStatement.

		return populate(cs);
	}

	/**
	 * This method implements the transfer of database data to a value object or
	 * a CollectionVO. The data may be contained in one or more resultsets or
	 * directly in the CallableStatement.
	 * 
	 * <PRE>
	 * protected VI populate(CallableStatement cs) throws SQLExceptin {
	 *
	 * 	// e.g. create a new concrete VO instance
	 * 	LoginSVO lvo = new LoginSVO();
	 *
	 * 	// e.g. to get resultsets
	 * 	Resultset rs = cs.getResultSet();
	 *
	 * 	// e.g. if the stored procedures performs updates retrieve the update
	 * 	// number by
	 * 	int i = cs.getUpdateCount();
	 *
	 * 	// transfer data from CallableStatement cs to the value object
	 * 	lvo.setAcs(cs.getString(8));
	 * 	lvo.setLogoutTimeout(cs.getInt(9));
	 *
	 * 	return lvo;
	 * }
	 * </PRE>
	 * 
	 * @throws SQLException
	 *             if a database access error occurs
	 * @return VI object that contains the data produced by the query; never
	 *         null
	 * @param cs
	 *            CallableStatement object that has been executed.
	 */
	protected VI populate(CallableStatement cs) throws SQLException {
		throw new UnsupportedOperationException(
				"populate() is not supported in DAO and expected to be overwritten by sub DAO classes");
	}
}