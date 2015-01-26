<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../inc_html_header.jspf"%>
</head>
<body>
<%@ include file="../inc_body_header.jspf"%>
<div id="breadcrumb">You are here: <a href="<c:url value="/user/list" />">基本信息设置</a></div>
<div id="main_content">
<form action="<c:url value="/setting/update" />" method="post">
<table class="recordTable">
	AppId &nbsp&nbsp:&nbsp&nbsp ${operator.appId } <input type="text" name="appId" id="appId" /><br><br>
	OperatorId &nbsp&nbsp:&nbsp&nbsp ${operator.operatorId } <input type="text" name="operatorId" id="operatorId" /><br><br>
	AppSecret &nbsp&nbsp:&nbsp&nbsp ${operator.appSecret } <input type="text" name="appSecret" id="appSecret" /><br><br>
	<button type="submit" class="button-disk">Save</button>
</table>
</form>
</div>
<%@ include file="../inc_footer.jspf"%>
<script>
</script>
</body>
</html>