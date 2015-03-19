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
            function cgPint()
            {
             	document.getElementById("printHid").style.display="none";
             	window.print();
             	document.getElementById("printHid").style.display="";
            }
        </script>		
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="8" background="<%=path %>/images/tbg.gif">&nbsp;物品采购打印&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">物品名称</td>
					<td width="10%">物品类别</td>
					<td width="10%">供货商</td>
					<td width="10%">采购时间</td>
					<td width="10%">数量</td>
					<td width="10%">采购价</td>
		        </tr>	
				<c:forEach items="${requestScope.cgList}" var="caigou">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${caigou.spmc}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${caigou.splb}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${caigou.ghsmc}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${caigou.caigoushijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${caigou.shuliang}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${caigou.caigoujia}
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 5px;" id="printHid">
			  <tr>
			    <td>
			      <input type="button" value="打印" style="width: 80px;" onclick="cgPint()" />
			    </td>
			  </tr>
		    </table>			
	</body>
</html>
