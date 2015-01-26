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
<div id="breadcrumb">You are here: SETTING » <a href="<c:url value="/operator" />">Text</a></div>
<div id="main_content">
<div id="search">
<h2><a href="">Search Criteria</a></h2>
<form id="searchForm" action="<c:url value="/receive" />" method="get">
<p><label for="code">Code</label> <input type="text" name="code" id="code" value="" /></p>
<p><label for="name">Name</label> <input type="text" name="name" id="name" value="" /></p>
<p><button class="button-search" type="submit">Search</button> <button class="button button-cancel" type="button">Cancel</button></p>
<input type="hidden" name="pageIndex" id="pageIndex" value="1" />
</form>
</div>
<table class="recordTable">
	<caption>Text</caption>
	<thead>
		<tr>
			<th></th>
			<th>msgId</th>
			<th>content</th>
			<th>createTime</th>
			<th>openId</th>
			<th>operatorIds</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagination.resultSet}" var="text" varStatus="status">
			<tr>
				<td>${status.index + 1 }</td>
				<td>${text.msgId }</td>
				<td>${text.content }</td>
				<td>${text.createTimeStr }</td>
				<td>${text.openId }</td>
				<td>${text.operatorId }</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<%@ include file="../inc_pagination.jspf"%>
		<tr>
			<td colspan="100"><a class="button button-plus" href="<c:url value="/operator/add" />">Add</a></td>
		</tr>
	</tfoot>
</table>
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