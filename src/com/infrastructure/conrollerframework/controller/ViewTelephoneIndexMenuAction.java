
package com.vehimgmt.telephone.client.actions;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infrastructure.conrollerframework.data.HttpRequestProxy;
import com.infrastructure.conrollerframework.data.HttpResponseProxy;
import com.infrastructure.conrollerframework.data.VI;
import com.infrastructure.exception.BaseException;
import com.infrastructure.exception.BusinessException;
import com.infrastructure.exception.InfrastructureException;
import com.infrastructure.exception.ReturnCode;
import com.infrastructure.valueobject.*;
import com.vehimgmt.common.server.vo.TelephoneIndexVO;
import com.vehimgmt.common.utils.*;
import com.vehimgmt.telephone.server.service.*;

public class ViewTelephoneIndexMenuAction extends BaseAction {

	String _command = "";

	public ViewTelephoneIndexMenuAction()

	{

	}

	/**
	 * It is the responsibility of this method to conver the extract the request
	 * data from incoming call and transfor the same in to value object. defined
	 * as abstract all sub classes must provide implementations for this method.
	 * 
	 * @param request
	 *            Http request object from the incoming call. See
	 *            HttpServletRequest javax.servlet.http.HttpServletRequest for
	 *            more detailes
	 * @return The converted value object. See @link ImvResponse
	 *         com.infrastructure.common.data.VI for more detailes
	 * @exception InfrastructureException
	 *                in case of infrastructure exceptions. See @link
	 *                InfrastructureException
	 *                com.infrastructure.common.exception.InfrastructureException
	 *                for more detailes
	 * @exception BusinessException
	 *                in case of business exceptions. See @link
	 *                BusinessException
	 *                com.infrastructure.common.exception.BusinessException for
	 *                more detailes
	 */
	public VI convertRequest(HttpServletRequest request) throws BaseException {
		System.out.println("Convert Request Called");
		TelephoneIndexVO tvo = new TelephoneIndexVO();

		System.out.println("======Convert Request Called For ViewTelephoneIndex finished =======");

		return tvo;
	}

	/**
	 * It is the responsibility of this method to call the facade bean and
	 * execute the business logic and create the response object. The controller
	 * will invoke this method. The base class implementation provides general
	 * reflection way of invocation and sub classes are free to override this
	 * method and provide there own version of implementation.
	 * 
	 * @param request
	 *            The incoming request from the client converted to Imv Value
	 *            object .See @link ImvRequest
	 *            com.infrastructure.common.data.ImvRequest for more detailes
	 * @return the response from facade bean. See @link ImvResponse
	 *         com.infrastructure.common.data.ImvResponse for more detailes
	 * @exception InfrastructureException
	 *                in case of infrastructure exceptions. See @link
	 *                InfrastructureException
	 *                com.infrastructure.common.exception.InfrastructureException
	 *                for more detailes
	 * @exception BusinessException
	 *                in case of business exceptions. See @link
	 *                BusinessException
	 *                com.infrastructure.common.exception.BusinessException for
	 *                more detailes
	 */
	public HttpResponseProxy executeRequest(HttpRequestProxy request) throws BaseException {

		HttpResponseProxy imvResponse = null;
		try {
			System.out.println("ViewTelephoneIndex ::executeRequest=======");

			if (request == null) {
				throw new InfrastructureException("Input request is null", ReturnCode.INPUT_PARAMETER_IS_NULL);
			}

			// Lookup the session bean and create the EjbObject

			Telephone telephoneBean = (Telephone) getFacadeInterface(Constant.TELEPHONE_INDEX);
			System.out.println("Halilllllllllllll  &&&&&&&&&&&");
			if (telephoneBean == null) {
				throw new InfrastructureException("Service Bean object not found", ReturnCode.SERVICE_NOT_FOUND);
			}

			imvResponse = telephoneBean.telephoneIndex(request);

		}

		catch (Exception ce) {
			ce.printStackTrace();
			throw new InfrastructureException(ce.toString(), ReturnCode.GENERAL_ERROR);
		}

		return imvResponse;

	}

	/**
	 * It is the responsibility of this method to foreward the response to
	 * appropriate jsp. defined as abstract all sub classes must provide
	 * implementations for this method.
	 * 
	 * @param response
	 *            The response from facade bean
	 * @param servletResponse
	 *            http response object See HttpServletRequest
	 *            javax.servlet.http.HttpServletResponse for more detailes
	 * @exception InfrastructureException
	 *                in case of infrastructure exceptions. See @link
	 *                InfrastructureException
	 *                com.hp.common.exception.InfrastructureException for more
	 *                detailes
	 * @exception BusinessException
	 *                in case of business exceptions. See @link
	 *                BusinessException
	 *                com.hp.common.exception.BusinessException for more
	 *                detailes
	 */

	public void actionForward(VI response, ServletConfig config, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws BaseException {

		try {

			System.out.println(" Action Forward Called");
			servletRequest.setAttribute("RESPONSE_VI_ATTRIBUTE", response);

			config.getServletContext().getRequestDispatcher("/resources/jsp/usedcar/TelephoneIndex.jsp")
					.forward(servletRequest, servletResponse);

		} catch (ServletException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), 100);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), 100);
		}

	}

	/**
	 * It is the responsibility of this method to create the next view in case
	 * of error condition. The controller will invoke this method. The base
	 * class implementation provides general implementation and sub classes are
	 * free to override this method and provide there own version of
	 * implementation.
	 * 
	 * @param exception
	 *            exception object from the business logic
	 * @param servletResponse
	 *            http response object See HttpServletRequest
	 *            javax.servlet.http.HttpServletResponse for more detailes
	 * @exception InfrastructureException
	 *                in case of infrastructure exceptions. See @link
	 *                InfrastructureException
	 *                com.hp.common.exception.InfrastructureException for more
	 *                detailes
	 */
	public void errorForward(BaseException exception, ServletConfig config, HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws InfrastructureException {
		try {

			config.getServletContext().getRequestDispatcher("some.jsp").forward(servletRequest, servletResponse);

		} catch (Exception e) {
			e.printStackTrace();
			throw new InfrastructureException(e.getMessage(), 100);
		}
	}

} // End of Class
