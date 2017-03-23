<%@ page language="java" import="java.security.Principal,java.util.Date,com.infrastructure.conrollerframework.session.*,com.infrastructure.conrollerframework.data.*,java.net.URLEncoder" %>

<%@ page  import="java.security.Principal,java.util.Date, java.net.URLEncoder" session="true"  contentType="text/html" %>
<%@include file="/header.jsp" %>


 <%-- <%

try {
String url="";
 
UserSessionImpl uSession = null;
 System.out.println("2");
 if(session.getAttribute(Constants.USER_SESSION_ATTR_NAME)!=null)
 {
 
 
 System.out.println("welcome.jsp"+uSession);
 System.out.println("url is  "+url);
url=URLEncoder.encode(url);

 System.out.println("Enoded url is  "+url);
boolean showNewSales = true;
    Principal mylocal = null;
   // mylocal = request.getUserPrincipal();
     System.out.println("mylocal  "+mylocal);
    if(mylocal!=null)
    {
    System.out.println("Debug 3: principal isn't null");
    String txt = null;
    txt = mylocal.getName();
    System.out.println("Debug 4: name from principal is " + txt);

    System.out.println("(String)session.getAttribute(loclanguage)" + (String)session.getAttribute("loclanguage"));
    System.out.println(request.toString());
    }
    else
    System.out.println("Debug 3: principal is null");

    if(uSession == null) {
        uSession = new com.infrastructure.conrollerframework.session.UserSessionImpl("XX");
    }



	System.out.println("The application name " + uSession.getappAccess());
	String strTmp = uSession.getappAccess();
	strTmp = strTmp == null? "" : strTmp.trim();
	if (!strTmp.equalsIgnoreCase("SALE")) {
	showNewSales = false;
	}
 
	}

%>
 
 --%>
 <html>
<head>
<SCRIPT language=JavaScript >
function fun()
{

	document.welcome.method = "post";
	document.welcome.action = "ControllerServlet?command=checklogon";
	document.welcome.submit();
}

function fun1()
{

	document.welcome.method = "post";
	document.welcome.action = "ControllerServlet?command=checkregister";
	document.welcome.submit();
}
</SCRIPT>
 
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META http-equiv=Content-Type content="text/html; charset=windows-874">
 
  
</head>

<body   class=body id=page    leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
<%

System.out.println("Redirecting to TESTSESSION" +session.getAttribute("TESTSESSION"));
%>
<form name="welcome">
 <table  width="1000" cellpadding="0" cellspacing="0" border="0">
  <!--header tag starts-->
  <tr>
    <td height="55">
      <table id="layout" cellspacing="0" width="100%" height="80"  border="0" >
        <tr>
          <td width="100%" height="78" rowspan="1">
            <table width="99%" cellpadding="0" cellspacing="0" border="0">
              <tr>
                <td  height="3" >
                  <table width="1000" cellpadding="0" cellspacing="10" border="0"  class="featurenavwhite" height="0">
                    <tr valign="bottom">
                      <td  height="1" >
                       <%= new java.util.Date() %>
                        </td>
                      </tr>
                    <tr><td align="center">WELCOME TO EXPENDITURE PLANNER</td></tr>
                  </table>
                
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
 
<tr><td align="center" height="5"><input type="submit" value="Register here" onclick="fun1()"/></td>
<td align="center" height="5"><input type="submit" value="Login here" onclick="fun()"/></td>
</tr>
  </table>
</form>
 </body>

</html>


<%-- <%
    }catch(Throwable th) {
        th.printStackTrace();
    }

%> --%>