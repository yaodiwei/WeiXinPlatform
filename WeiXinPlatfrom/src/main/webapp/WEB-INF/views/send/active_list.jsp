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
<div id="breadcrumb">You are here: <a href="<c:url value="/active" />">发送客服消息</a></div>
<div id="main_content">
<div id="search">
<h2><a href="">Search Criteria</a></h2>
<form id="searchForm" action="<c:url value="/send/active/find" />" method="post">
<p><label for="code">OpenId</label> <input type="text" name="openId" id="openId" value="${user.openId}" /></p>
<p><label for="name">Name</label> <input type="text" name="nickName" id="nickName" value="${user.nickName }" /></p>
<p><button class="button-search" type="submit">Search</button> <button class="button button-cancel" type="button">Cancel</button></p>
<input type="hidden" name="pageIndex" id="pageIndex" value="1" />
</form>
</div>


<table class="recordTable">
	<caption>48小时内发送过消息的用户</caption>
	<thead>
		<tr>
			<th></th>
			<th>头像</th>
			<th>昵称</th>
			<th>详细</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user" varStatus="status">
			<input type="hidden" name="openIds" id="openIds" value="${user.openId }" />
			<tr>
				<td>${status.index + 1 }</td>
				<td><img src="${user.headImgUrl }" width="20px" height="20px"/></td>
				<td>${user.nickName }</td>
				<td><a href="<c:url value="/send/active/detail/${user.openId}" />">detail</a></td>
			</tr>
 		</c:forEach>
	</tbody>
	<tfoot>
		<%@ include file="../inc_pagination.jspf"%>
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