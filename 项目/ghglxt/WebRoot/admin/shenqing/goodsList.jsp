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
           function shenqingAdd(goodsId)
           {
                 var url="<%=path %>/shenqing?type=toAddSq&id="+goodsId;
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="8" background="<%=path %>/images/tbg.gif">&nbsp;采购物品申请&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="10%">编号</td>
					<td width="10%">名称</td>
					<td width="10%">类别</td>
					<td width="10%">品牌</td>
					<td width="10%">型号</td>
					<td width="10%">规格</td>
					<td width="*">采购申请</td>
		        </tr>	
				<c:forEach items="${requestScope.goodsList}" var="goods">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${goods.bianhao}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${goods.mingcheng}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${goods.leibie}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${goods.pinpai}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${goods.xinghao}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    ${goods.guige}
					</td>
					<td bgcolor="#FFFFFF" align="center">
					    <input type="button" onclick="shenqingAdd(${goods.id})" value="采购申请"/>
					</td>
				</tr>
				</c:forEach>
			</table>
	</body>
</html>
