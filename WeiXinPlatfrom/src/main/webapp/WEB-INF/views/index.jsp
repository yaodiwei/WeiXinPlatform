<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="inc_html_header.jspf"%>
</head>
<body>
	<%@ include file="./inc_body_header.jspf"%>
	<div id="breadcrumb">You are here: <a href="<c:url value="/" />">主页</a></div>
	<div id="main_content">
	<%-- <br><img src="images/weixin_test.jpg" /><br> --%>
	</div>
	<%@ include file="./inc_footer.jspf"%>
</body>
</html>
