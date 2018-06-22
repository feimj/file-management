<%@ page contentType="text/html;charset=utf-8"%>
<%
	request.getSession().getServletContext().setAttribute("rootPath", request.getContextPath());
%>
<script src="${rootPath }/jquery/jquery-1.7.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${rootPath}/easyui/jquery-easyui-1.3.2/jquery.easyui.min.js"></script>
	<link rel="stylesheet" href="${rootPath}/easyui/jquery-easyui-1.3.2/themes/default/easyui.css" type="text/css"></link>
	<link rel="stylesheet" href="${rootPath}/easyui/jquery-easyui-1.3.2/themes/icon.css" type="text/css"></link>
	<script type="text/javascript" src="${rootPath}/easyui/jquery-easyui-1.3.2/locale/easyui-lang-zh_CN.js"></script>
<script>
var rootPath = '${rootPath}';
</script>
