<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
        <script language="javascript">
           function gongSelected()
           {
                 var gongOpts = document.getElementsByName("gongxx");
                 for(var i=0;i<gongOpts.length;i++)
                 {
                 	if(gongOpts[i].checked)
                 	{
                 		var arrGongxx = gongOpts[i].value.split("_");
                 		window.returnValue = arrGongxx;
                 		window.close();
                 	}
                 }
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="8" background="<%=path %>/images/tbg.gif">&nbsp;供货商选择&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">&nbsp;</td>
					<td width="10%">编号</td>
					<td width="10%">名称</td>
					<td width="10%">联系人</td>
					<td width="10%">电话</td>
					<td width="*">email</td>
		        </tr>	
				<c:forEach items="${requestScope.gongList}" var="gong">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						<input type="radio" name="gongxx" value="${gong.id }_${gong.mingcheng}"/>
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gong.bianhao}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gong.mingcheng}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${gong.lianxiren}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${gong.dianhua}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${gong.email}
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;">
			  <tr>
			    <td>
			      <input type="button" value="选择" style="width: 80px;" onclick="gongSelected()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
