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
<div id="breadcrumb">You are here: <a href="<c:url value="/user" />">User</a></div>
<div id="main_content">

<form action="<c:url value="/user/getUsersInfo" />" method="post">
<table class="recordTable">
	<caption>User &nbsp;&nbsp;&nbsp;<button  type="submit">Update user to database</button><font color="red">${result }</font></caption>
	<thead>
		<tr>
			<th width="5%"></th>
			<th width="20%">Open Id</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${openIds}" var="openId" varStatus="status">
			<tr>
				<td>${status.index + 1 }</td>
				<td>${openId }</td><input type="hidden" name="openIds" id="openIds" value="${openId }" />
			</tr>
		</c:forEach>
	</tbody>
</table>
</form>
</div>
<%@ include file="../inc_footer.jspf"%>
<script>
	$('a.pageNumber').click(function() {
		$('#pageIndex').val($.trim($(this).html()));
		$('#searchForm').submit();
		return false;
	});
</script>
</body>
</html>