<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../inc_html_header.jspf"%>
<style>
.ui-autocomplete-input {width:30px;}
</style>
</head>
<body>
<%@ include file="../inc_body_header.jspf"%>
<div id="breadcrumb">You are here: <a href="<c:url value="/send/passive" />">发送被动相应消息</a></div>
<div id="main_content">
<table class="recordTable">
	<caption>User - Edit</caption>
	<thead>
		<tr>
			<th></th>
			<th>Name</th>
			<th>Value</th>
			<th width="70">Enable</th>
			<th width="105">Operation</th>
		</tr>
	</thead>
	<tbody id="tbody">
		<c:forEach items="${passives}" var="passive" varStatus="status">
			<form action="<c:url value="/send/passive/update" />" method="post">
			<tr>
				<td>${status.index + 1 }</td>
				<td>${passive.name }</td><input type="hidden" name="name" value="${passive.name }"/>
				<td><textarea rows="1" cols="140" name="value" id="value">${passive.value }</textarea><br><span id="valueErr"></span></td>
				<td>
					<select name="enable" id="selected">
						<option value="1" ${passive.enable==1?'selected':'' }>开启</option>
						<option value="0" ${passive.enable==0?'selected':'' }>关闭</option>
					</select>
				</td>
				<td><button type="submit" class="button-disk" >Update</button> <a class="confirm-delete" href="<c:url value="/send/passive/delete?name=${passive.name }" />">Del</a></td>
			</tr>
			</form>
		</c:forEach>
	</tbody>
	<tfoot>
		<tr>
			<form action="<c:url value="/send/passive/add" />" method="post">
			<td>Add</td>
			<td><textarea rows="1" cols="20" name="name" id="name"></textarea></td>
			<td><textarea rows="1" cols="140" name="value" id="value"></textarea><br><span id="valueErr"></span></td>
			<td>
				<select name="enable" id="selected">
					<option value="1" ${passive.enable==1?'seleted':'' }>开启</option>
					<option value="0" ${passive.enable==0?'seleted':'' }>关闭</option>
				</select>
			</td>
			<td colspan="10"><button class="button button-plus" type="submit" />Add</button></td>
			</form>
		</tr>
	</tfoot>
</table>

</div>
<%@ include file="../inc_footer.jspf"%>
<script>
</script>
</body>
</html>