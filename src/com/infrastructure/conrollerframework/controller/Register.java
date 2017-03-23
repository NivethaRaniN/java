package com.infrastructure.conrollerframework.controller;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.infrastructure.conrollerframework.data.VI;
import com.infrastructure.exception.BaseException;

public class Register extends BaseAction{

	@Override
	public VI convertRequest(HttpServletRequest request) throws BaseException {
		System.out.println("convertRequest");
		return null;
	}

	@Override
	public void actionForward(VI response, ServletConfig config,
			HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) throws BaseException {
		try {

			System.out.println(" Action Forward Called");
			servletRequest.setAttribute("RESPONSE_VI_ATTRIBUTE", response);

			config.getServletContext().getRequestDispatcher("/register.jsp")
					.forward(servletRequest, servletResponse);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
