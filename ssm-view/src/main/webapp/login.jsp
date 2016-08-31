<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title }</title>
</head>
<body>
<form id="loginForm" name="loginForm"  action="${ctx}/menu/menu!layout" method="post">
<div>
  <div >
    <table width="322" border="0" align="" cellpadding="0" cellspacing="0" >
      <tr>
        <td width="65" height="40" class="">用户名：</td>
        <td width="257"><input type="text" id="username"  class="txtbox" value=""   style="height:20px; line-height:20px;width: 180px;" /></td>
      </tr>
      <tr>
        <td height="40" >密　码：</td>
        <td><input type="password"  style="line-height:20px;width: 180px;" /></td>
      </tr>
      <tr>
        <td colspan="2">
          <table width="100%" border="0" cellspacing="0" cellpadding="0" >
            <tr>            
              <td width="40" height="40" align="left">              
               <input type="submit"  id="button"   value="登录" />                           
              </td>           
              
            </tr>
          </table>
          </td>
      </tr>
    </table>
  </div>
</div>
</form>
</body>
</html>


