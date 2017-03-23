<HTML>
<BODY>
<%@ page language="java" session="true" errorPage="error.jsp" contentType="text/html" %>
<%
  if(session != null)
	  session.invalidate();
	  response.sendRedirect("/sample/index.jsp");
%>

</BODY>
<HTML>
