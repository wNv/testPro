<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
            function check()
            {
                if(document.formAdd.shuliang.value=="")
                {
                   alert("采购数量不能为空!");
                   return false;
                }
                
                if(document.formAdd.caigoujia.value=="")
                {
                   alert("采购价格不能为空!");
                   return false;
                }
                
                document.formAdd.submit();
            }
            
            function openGong()
            {
				var url="<%=path %>/gong?type=seleGong";
       	   		var ret = window.showModalDialog(url,"供货商信息查看","dialogWidth=500px;dialogHeight=300px;status=yes;help=no;scroll=yes");
            	if(ret != null)
            	{
            		document.getElementById("gongId").value = ret[0];
            		document.getElementById("ghs").value = ret[1];
            	}
            }            
        </script>
	</head>

	<body leftmargin="2" topmargin="9" background='<%=path %>/images/allbg.gif'>
			<form action="<%=path %>/caigou?type=cgAdd" name="formAdd" method="post">
				     <table width="98%" align="center" border="0" cellpadding="4" cellspacing="1" bgcolor="#CBD8AC" style="margin-bottom:8px">
						<tr bgcolor="#EEF4EA">
					        <td colspan="3" background="<%=path %>/images/wbg.gif" class='title'><span>物品采购</span></td>
					    </tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         物品名称：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        ${shenqing.spmc }
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         物品类别：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
   						    	${shenqing.splb }
						    </td>
						</tr>						
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         供应商：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
								<input type="text" id="ghs" readonly="readonly" onclick="openGong()"/>点击选择供货商
								<input type="hidden" id="gongId" name="gongId"/>
						    </td>
						</tr>
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         采购时间：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        ${riqi }
						    </td>
						</tr>						
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         数量：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="shuliang" size="20"/>
						    </td>
						</tr>						
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         采购价：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <input type="text" name="caigoujia" size="20"/>
						    </td>
						</tr>						
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						         备注：
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						        <textarea rows="5" cols="16" name="beizhu"></textarea>
						    </td>
						</tr>						
						<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
						    <td width="25%" bgcolor="#FFFFFF" align="right">
						        &nbsp;
						    </td>
						    <td width="75%" bgcolor="#FFFFFF" align="left">
						       <input type="hidden" name="caigoushijian" value="${riqi}"/> 
						       <input type="hidden" name="goodsId" value="${shenqing.goodsId }"/> 
						       <input type="hidden" name="shenqingId" value="${shenqing.id }"/> 
						      <input type="button" value="提交" onclick="check()"/>&nbsp; 
						       <input type="button" value="返回" onclick="javascript:history.back()"/>&nbsp; 
						    </td>
						</tr>
					 </table>
			</form>
   </body>
</html>
