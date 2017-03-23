<%@ page import=" com.infrastructure.conrollerframework.session.*,java.net.URLEncoder" %>

<%!
	ServletContext ctx = null;
	String ctxRoot = null;
	boolean done = false;

%>

<%
	if(!done) {

	    System.out.println("step 1");
		//ctxRoot = CommonUtil.getContextFromRequest(request);
		done = true;
	}
%>

<%--
		UserSession userSession = 	(UserSession)session.getAttribute(Constants.USER_SESSION_ATTR_NAME);
		System.out.println("Hello userSession"+userSession);
		if(userSession == null){
		  response.sendRedirect("/" + ctxRoot + "/resources/jsp/logon.jsp");
		}
		java.util.Locale locale = userSession.getSessionLocale();
		String language = locale.getLanguage();
		System.out.println("Hello language"+language);
		//ResourceBundleReader resourceBundle = ResourceBundleReader.getInstance("common",locale);
		String lang="en";
		if(language.equalsIgnoreCase("TH"))
		{
			lang="th";
		}

        String strAppCharset =  "windows-874";
        if(strAppCharset != null)
           response.setContentType( "text/html; charset=" + strAppCharset);
        else
        {
        	strAppCharset="windows-874";
        	response.setContentType( "text/html; charset=" + strAppCharset);
        }


--%>