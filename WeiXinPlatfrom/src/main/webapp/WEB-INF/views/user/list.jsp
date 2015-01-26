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
<div id="breadcrumb">You are here: <a href="<c:url value="/user/list" />">数据库用户列表</a></div>
<div id="main_content">
<div id="search">
<h2><a href="">Search Criteria</a></h2>
<form id="searchForm" action="<c:url value="/user/list" />" method="post">
<p><label for="code">OpenId</label> <input type="text" name="openId" id="openId" value="${user.openId}" /></p>
<p><label for="name">Name</label> <input type="text" name="nickName" id="nickName" value="${user.nickName }" /></p>
<p><button class="button-search" type="submit">Search</button> <button class="button button-cancel" type="button">Cancel</button></p>
<input type="hidden" name="pageIndex" id="pageIndex" value="1" />
</form>
</div>

<form action="<c:url value="/user/getUsersInfo" />" method="post">
<table class="recordTable">
	<caption>User &nbsp;&nbsp;&nbsp;<button  type="submit">Update user to database</button></caption>
	<thead>
		<tr>
			<th></th>
			<th>Open Id</th>
			<th>昵称</th>
			<th>性别</th>
			<th>位置</th>
			<th>头像</th>
			<th>订阅时间</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pagination.resultSet}" var="user" varStatus="status">
			<tr>
				<td>${status.index + 1 }</td>
				<td>${user.openId }</td><input type="hidden" name="openIds" id="openIds" value="${user.openId }" />
				<td>${user.nickName }</a></td>
				<td>
					<c:choose >
						<c:when test="${user.sex==1 }">男</c:when>
						<c:when test="${user.sex==2 }">女</c:when>
						<c:when test="${user.sex==1 }">未知</c:when>
					</c:choose>
				</td>
				<td>${user.country }-${user.province }-${user.city }</td>
				<td><img src="${user.headImgUrl }" width="20px" height="20px"/></td>
				<td>${user.subscribeTime }</td>
			</tr>
		</c:forEach>
	</tbody>
	<tfoot>
		<%@ include file="../inc_pagination.jspf"%>
	</tfoot>
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