<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="inc_html_header.jspf"%>
<style>
#loginForm {
	float: left;
	padding: 15px 20px;
	border: 2px solid #CCC;
	box-shadow: 4px 4px 4px #CBCBCB;
	margin-right: 60px;
}

#loginForm h2 {
	color: #CB161B;
	font-size: 20px;
	margin-bottom: 20px;
}

#loginForm p {
	margin-top: 8px;
}

#loginForm label {
	font-size: 12px;
	display: block;
	width: 90px;
	float: left;
}

p#error {
	text-align: left;
	color: red;
}
</style>
</head>
<body>
<body>
<div id="logo"><img src="images/logo.png" /></div>
<div id="breadcrumb">You are here: <a href="<c:url value="/" />">LOGIN</a></div>
<div id="main_content" style="height:auto;Overflow:hidden;width:auto;">
<form action="<c:url value="/login" />" id="loginForm" name="loginForm" method="post">
<h2>Login</h2>
<p><label for="userCode">Login Name</label><input class="required" type="text" name="userCode" id="userCode" value="${userCode }" /></p>
<p><label for="password">Password</label><input class="required" type="password" name="password" id="password" /></p>
<p><label></label><button type="submit">Login</button></p>
<p id="error">${error }</p>
<input type="hidden" name="url" value="${url }" />
</form>

<div id="news">
<ul style="">
	<li><a href="#f100">About DataService</a></li>
</ul>

<div id="f100">
<dl>
	Introduction<br>
	Samvo data service offers you our football and basketball statistic feed, which contains extensive statistics for current season and historical seasons on worldwide events, such as fixtures, line-ups, shot on target, bookings, and so on.
</dl>
</div>
</div>

<div class="clear"></div>



</div>
<script>
	$("#loginForm").validate( {
		errorPlacement : function(error, element) {
		},
		invalidHandler : function(form, validator) {
			var errors = validator.numberOfInvalids();
			if (errors) {
				$('#error').html('Login Name and Password are required');
			} else {
				$('#error').html('');
			}
		}
	});
	$('#news').tabs( {
		collapsible : true
	});
</script>
</body>
</html>