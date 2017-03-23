<html>
<head>
<SCRIPT language=JavaScript >

function fun2()
{

	document.reg_Form.method = "post";
	document.reg_Form.action = "ControllerServlet?command=checkcancel";
	document.reg_Form.submit();
}

</SCRIPT>
<title>Register</title>
</head>
<body  class="body" id="page"  topmargin='0' bottommargin='0'
leftmargin='0' rightmargin='0' marginheight='0' marginwidth='0'>


<form name='reg_Form' action="" method="post">

<table id="layoutwhite"  height='100%' width='100%' align='center' cellspacing='0' cellpadding='0' border='0'>
<tr><td valign='middle'>
<table align='center' cellspacing='0' cellpadding='0' border='0'>
<tr ><td>
<table id="layoutblue" align='center' cellspacing='1' cellpadding='0' border='0' height="198">
<tr class='contentBackground'><td>
<table width='500' align='center' cellspacing='0' cellpadding='0' border='0' height="248">
<tr  align='left'  bgcolor="#5a7394">
<td valign='middle' align='center' width="100%">&nbsp;Registration</td>
</tr>

<tr class='contentBorder' align='center' bgcolor="#5a7394">
<td height='18' valign='middle'><span title=''><img name='Login_Image_1' width=1 height=1 border=0 alt='' src='../images/space.gif'></span></td>
</tr>
<tr><td height='18%'><span title=''><img name='Login_Image_2' width=1 height=1 border=0 alt='' src='../images/space.gif'></span></td></tr>
<tr><td height="100%">
<table align='center' cellspacing='10' cellpadding='0' border='0' height="90">

<tr>
<td>
<p align="right" span class="runtext2table">Name</td><td></td>
<td>
<input type=text name="j_name" size='40'  ></td>
</tr>

<tr>
<td>
<p align="right" span class="runtext2table">Email</td><td></td>
<td>
<input type=text name="j_email" size='40'  ></td>
</tr>

<tr>
<td scope="row" align='right'>
<p align="left"><span class="runtext2table">Enter Password</span></td><td>&nbsp;&nbsp;</td>
<td>
<input type=password name="j_pwd"  class=defaultPasswordStyle size='40'  >
</td>
</tr>

<tr>
<td>
<p align="right" span class="runtext2table">Family Name</td><td></td>
<td>
<input type=text name="j_gid" size='40'  ></td>
</tr>

<tr>
<td>
<p align="right" span class="runtext2table">DOB</td><td></td>
<td>
<input type=text name="j_dob" size='40'  ></td>
</tr>


<tr>
<td>
<p align="right" span class="runtext2table">Gender</td><td></td>
<td>
<input type=text name="j_sex" size='40'  ></td>
</tr>


</table>
</td></tr>
<tr><td height='18%'><span title=''><img name='Login_Image_4' width=1 height=1 border=0 alt='' src='../images/space.gif'></span></td></tr>
<tr><td height="24">

<table height='100%' align='right' border='0' cellpadding='2' cellspacing='0'>

<tr>
<td>
<table border=0 cellspacing=0 cellpadding=0 name='Login_Button_0'>
<tr height=16>
<td height="20" width="4"  class="buttonNonUnderline" valign="middle" align="middle" nowrap  title="ss" href='#'>
<input type=submit name="Register" value="Register"  onClick="">
</a></td>
</tr>
</table>
</td>
<td>
</td>
<td>
<table border=0 cellspacing=0 cellpadding=0 name='Login_Button_0'>

<tr height=16>

<td><input type="submit" value="Cancel" onclick="fun2()"/></td>
<%-- <a  class="buttonNonUnderline"  href="index.jsp">
Cancel</a></td> --%>


</tr>
</table>
</td>
<td>&nbsp;&nbsp;&nbsp</td>
</tr>
</td></tr>
</table>
</td></tr>
</table>

</form>


</body>
</html>