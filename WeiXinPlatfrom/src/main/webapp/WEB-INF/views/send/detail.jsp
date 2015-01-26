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
<div id="breadcrumb">You are here: SETTING » <a href="<c:url value="/avtive/detail" />">用户详细对话</a></div>
<div id="main_content">
<div id="search">
<h2><a href="">Search Criteria</a></h2>
<form id="searchForm" action="<c:url value="/avtive/detail" />" method="get">
<p><label for="code">Code</label> <input type="text" name="code" id="code" value="" /></p>
<p><label for="name">Name</label> <input type="text" name="name" id="name" value="" /></p>
<p><button class="button-search" type="submit">Search</button> <button class="button button-cancel" type="button">Cancel</button></p>
<input type="hidden" name="pageIndex" id="pageIndex" value="1" />
</form>
</div>
<table class="recordTable">
	<caption>对话</caption>
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
		<c:forEach items="${texts}" var="text" varStatus="status">
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
			<form action="<c:url value="/send/active/send" />" method="post">
			<td>发送消息区</td>
			<input type="hidden" name="openId" value="${openId }">
			<td><textarea rows="3" cols="140" name="value" id="value"></textarea></td>
			<td colspan="10"><button class="button button-plus" type="submit" />Send</button></td>
			</form>
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