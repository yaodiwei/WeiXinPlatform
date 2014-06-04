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
   <h2>Objective: Package DataService services as new product to enrich company business lines</h2>
	Content:
	<li>Feed Server Service analysis in product, price, place and promotion prospective</li>
	<li>Operation Workflow</li>
	<li>Users and Stakeholders</li>
	<li>Business Requirement</li>
	<li>Features list</li>
	<li>Road Map (Features & Deliverables)</li>
	<li>Appendix</li>
	</div>
	<%@ include file="./inc_footer.jspf"%>
</body>
</html>