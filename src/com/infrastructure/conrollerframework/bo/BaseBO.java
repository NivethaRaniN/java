
package com.infrastructure.conrollerframework.bo;

import com.infrastructure.conrollerframework.data.HttpRequestProxy;
import com.infrastructure.conrollerframework.data.VI;
import com.infrastructure.exception.InfrastructureException;

/**
 * The base class for all business Objects. This class provides get***Factory
 * methods for BO, DAO and Helper Factory. The current vertion of get***Factory
 * method always gets the Facotry for the specified module from the
 * ObjectFactoryManager may be in later versions a local caching will be
 * introduced if needed.
 */

public class BaseBO {

	/**
	 * The convenient method to get the value object out of the request
	 * 
	 * @param request
	 *            The Request Object
	 * @return VI The request value object
	 * @exception in
	 *                case if the request or request value object is null.
	 */
	protected VI getRequestValueObject(HttpRequestProxy request) throws InfrastructureException {

		VI requestVO = null;
		if (request == null) {
			throw new InfrastructureException("Null request parameter passed for searchTMTUser()", 100);
		}

		requestVO = request.getRequest();

		// TO BE CHANGED
		if (requestVO == null) {
			throw new InfrastructureException("Null value object parameter passed for searchTMTUser()", 100);
		}

		return requestVO;
	}
}
