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
   <h2>一日不看成姐,心里就憋的慌</h2>
	内容:
	<br><img src="images/cheng_sister.jpg" /><h1>呵呵,你他喵是在逗我,你个王八蛋.</h1><br>
	感谢成姐对本人的默默支持!
	
	</div>
	<%@ include file="./inc_footer.jspf"%>
</body>
</html>