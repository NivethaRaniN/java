<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ page buffer="100kb" %>

<head>
<title>ss</title>

 <script>

function showHelp(helpFileUrl){

  		window.open(helpFileUrl,'x','toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes ,resizable=no, width=800,height=600,top=50,left=50');

    }
  </script>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ page language="java" session="true" contentType="text/html" isErrorPage="true" %>
<%
  System.out.println("Entered loginError.jsp");
  if(session != null)
  {
	  session.invalidate();
  }
  request.setAttribute("index","yes");
  java.util.Locale locale = null;
  String language = null;
  String lang = null;

  try{
  	locale = request.getLocale();
  	if (locale != null){
	  language = locale.getLanguage();
	  // Check for thai language setting
  	  if ((language.equalsIgnoreCase("TH"))||(language.equalsIgnoreCase("th_TH"))){
  		locale = new java.util.Locale("th","TH");
  		language = "th";
  		lang = "th";
  	  }else{
  	    // Anything other than th is english
  	    locale = null;
  	  }//end of inner if-else
  	}//end of outer if

  	//Set locale to null for now as we are supporting only en_US
  	locale = null;

  	if (locale == null){
  		locale = new java.util.Locale("en","US");
  		language = "en";
  		lang = "en";
  	}
  	//resourceBundle = ImvResourceBundle.getBundle(locale);
  }catch(Exception e){
    e.printStackTrace();
    locale = new java.util.Locale("en","US");
    language = "en";
    lang = "en";
  }

  System.out.println("This is the locale in loginerror.jsp" + locale);
  language= locale.getLanguage();
  java.util.ResourceBundle resourceBundle = null;

   lang="en";
	if(language.equalsIgnoreCase("TH"))
	{
		lang="th";
	}

   request.setAttribute("HELP_FILE_NAME","member_login_userg_"+lang+".html");
%>
<form name="frmLogin" method='post' action='/ControllerServlet?cmd=actionname'>
  <table width="1003" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td width="70" valign="bottom"><!--<img src="../images/<%=lang%>/scplogo.gif" width="70" height="81">--></td>
      <td width="933" align="right" valign="top"><!--<img src="/scpAdminWeb/images/<%=lang%>/top-header01.gif" width="934" height="49">--><br>
        <img src="../images/<%=lang%>/top-header02.gif" width="447" height="32"></td>
    </tr>
    <tr>
      <td height="23" colspan="2" valign="bottom" background="../images/<%=lang%>/topmenubg.gif"><img src="/scpAdminWeb/images/<%=lang%>/spacer.gif" width="1" height="23"></td>
    </tr>
    <tr valign="top">
      <td height="24" colspan="2"><table width="1003" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2" background="../images/<%=lang%>/headerbg.gif"><!--<img src="../images/<%=lang%>/header.gif" width="511" height="30">--></td>
            <td width="477" align="right" background="../images/<%=lang%>/headerbg.gif" class="runtext2">&nbsp;</td>
            <td width="15" background="../images/<%=lang%>/headerbg.gif"><img src="../images/<%=lang%>/spacer.gif" width="15" height="1"></td>
          </tr>
          <tr>
            <td width="15" height="433" rowspan="6"><img src="../images/<%=lang%>/spacer.gif" width="15" height="1"></td>
            <td colspan="2"><img src="../images/<%=lang%>/spacer.gif" width="1" height="6"></td>
            <td width="15" rowspan="6"><img src="../images/<%=lang%>/spacer.gif" width="15" height="1"></td>
          </tr>
          <tr>
            <td width="496" class="runtext2"><br> <img src="../images/<%=lang%>/spacer.gif" width="1" height="6"></td>
            <td width="477">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="2" align="left" valign="top" class="runtext2"><table width="973" cellpadding="0" cellspacing="1" bgcolor="#BABABC">
                <tr>
                  <td bgcolor="#FFFFFF"><table width="972" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="25" align="left" bgcolor="#99BDFF"><img src="../images/<%=lang%>/whitearrow.gif" width="22" height="22"></td>
                        <td width="348" align="left" bgcolor="#99BDFF" class="headerwhite"> "MembersLogin" </td>
                        <td width="599" bgcolor="#99BDFF" >&nbsp;</td>
                      </tr>
					  <tr>
                        <td width="348" colspan="2" bgcolor="#FFFFFF"><table width="373" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="15" rowspan="9" >&nbsp;</td>
								<td height="14" width="200" colspan="4"><B><Font color="#FF0000"> loginErrorMessage
								</Font></B></td>
							</tr>
                            <tr>
                              <td height="14" colspan="4"><img src="../images/<%=lang%>/spacer.gif" width="1" height="12"></td>
                            </tr>
                            <tr>
                              <td height="14" colspan="4"><a href="../login.jsp"><B><Font color="#FF0000"> "TryAgain" </font></b></a></td>
                            </tr>
                            <tr>
                              <td colspan="4"><img src="../images/<%=lang%>/spacer.gif" width="1" height="12"></td>
                            </tr>
                            <tr>
                              <td colspan="4"><img src="../images/<%=lang%>/spacer.gif" width="1" height="8"></td>
                            </tr>
                          </table></td>
                        <td width="599" rowspan="2" align="right" bgcolor="#FFFFFF"><img src="../images/<%=lang%>/homeimage.jpg" width="599" height="155"></td>
                      </tr>
                      <tr>
                        <td colspan="2" background="../images/<%=lang%>/boxbg.gif" bgcolor="#FFFFFF"><img src="../images/<%=lang%>/boxbg.gif" width="6" height="15"></td>
                      </tr>
                    </table></td>
                </tr>
              </table></td>
          </tr>
			<tr>
            <td colspan="2" class="runtext2"><img src="../images/<%=lang%>/spacer.gif" width="1" height="8"></td>
          </tr>

          <tr>
            <td colspan="2" class="runtext2"><img src="../images/<%=lang%>/spacer.gif" width="1" height="30"></td>
          </tr>
        </table></td>
    </tr>
    <tr valign="middle">
      <td height="24" colspan="2" bgcolor="#0000A5">
	  <%
	  System.out.println("My Changes for making the footer work");
		if(language.equalsIgnoreCase("TH")) {
		 %>
		<%//@include file="footer_th.jsp" %>
		<%
		} else {
		%>
		<%// @include file="footer_en.jsp" %>
		<%
		 }
		%>
	</td>
    </tr>
  </table>
</form>
</body>
</html>
