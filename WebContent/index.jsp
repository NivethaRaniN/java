
<%@ page language="java" import="java.security.Principal,java.util.Date,com.infrastructure.conrollerframework.session.*,com.infrastructure.conrollerframework.data.*,java.net.URLEncoder" %>

<%@ page  import="java.security.Principal,java.util.Date, java.net.URLEncoder" session="true"  contentType="text/html" %>
<%@include file="/header.jsp" %>

<%
 
System.out.println(" TESTSESSION 2" +session.getAttribute("TESTSESSION"));


session.setAttribute(Constants.USER_SESSION_ATTR_NAME,request.getSession());

config.getServletContext().getRequestDispatcher("/welcome.jsp").forward(request, response);
System.out.println("TESTSESSION 3");

%>
