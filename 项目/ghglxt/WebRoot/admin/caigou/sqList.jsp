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
            function toAddCg(sqid)
            {
             	var url="<%=path %>/caigou?type=toAddCg&sqid="+sqid;
				window.location.href=url;
            }
        </script>		
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="8" background="<%=path %>/images/tbg.gif">&nbsp;物品采购&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">物品名称</td>
					<td width="10%">物品类别</td>
					<td width="10%">数量</td>
					<td width="10%">申请时间</td>
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.sqList}" var="shenqing">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${shenqing.spmc}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${shenqing.splb}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${shenqing.shuliang}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${shenqing.shenqingshijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<c:if test="${shenqing.sfcg==0}">
							<input type="button" value="物品采购" onclick="toAddCg(${shenqing.id})"/>
						</c:if>
						<c:if test="${shenqing.sfcg!=0}">
							<font color="red">已采购</font>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
