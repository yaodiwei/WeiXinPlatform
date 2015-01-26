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
<div id="breadcrumb">You are here: <a href="<c:url value="/menu" />">菜单编辑</a></div>
<div id="main_content">
<form action="<c:url value="/menu/update" />" method="post">
<table class="recordTable">
	一级菜单项1
	<select class="filter" name="sel1">
    	<option value="">请选择是否有子菜单</option>
    	<option value="1" ${sel1==1?"selected":""} >是</option>
    	<option value="-1" ${sel1==-1?"selected":""} >否</option>
	</select>
	<div class="block" id="isSub1" style="display:none;">
		名字<input type="text" name="name1" value="${name1}" /><br>
		
		子菜单项1_1&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems11_type">
	    	<option value="click" ${menuItems11_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems11_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems11_name" value="${menuItems11_name}" />
		事件(网址)
		<input type="text" name="menuItems11_key" value="${menuItems11_key}" /><br>
		
		子菜单项1_2&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems12_type">
	    	<option value="click" ${menuItems12_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems12_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems12_name" value="${menuItems12_name}" />
		事件(网址)
		<input type="text" name="menuItems12_key" value="${menuItems12_key}" /><br>
		
		子菜单项1_3&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems13_type">
	    	<option value="click" ${menuItems13_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems13_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems13_name" value="${menuItems13_name}" />
		事件(网址)
		<input type="text" name="menuItems13_key" value="${menuItems13_key}" /><br>
		
		子菜单项1_4&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems14_type">
	    	<option value="click" ${menuItems14_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems14_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems14_name" value="${menuItems14_name}" />
		事件(网址)
		<input type="text" name="menuItems14_key" value="${menuItems14_key}" /><br>
		
		子菜单项1_5&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems15_type">
	    	<option value="click" ${menuItems15_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems15_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems15_name" value="${menuItems15_name}" />
		事件(网址)
		<input type="text" name="menuItems15_key" value="${menuItems15_key}" /><br>
	</div>
	<div class="block" id="noSub1" style="display:none;">
		类型
		<select class="filter" name="menuItems1_type">
	    	<option value="click" ${menuItems1_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems1_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems1_name" value="${menuItems1_name}" />
		事件(网址)
		<input type="text" name="menuItems1_key" value="${menuItems1_key}" />
	</div><br><br>


	一级菜单项2
	<select class="filter" name="sel2">
    	<option value="">请选择是否有子菜单</option>
    	<option value="1" ${sel2==1?"selected":""}>是</option>
    	<option value="-1" ${sel2==-1?"selected":""}>否</option>
	</select>
	<div class="block" id="isSub2" style="display:none;">
		名字<input type="text" name="name2" value="${name2}" /><br>
		
		子菜单项2_1&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems21_type">
	    	<option value="click" ${menuItems21_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems21_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems21_name" value="${menuItems21_name}" />
		事件(网址)
		<input type="text" name="menuItems21_key" value="${menuItems21_key}" /><br>
		
		子菜单项2_2&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems22_type">
	    	<option value="click" ${menuItems22_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems22_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems22_name" value="${menuItems22_name}" />
		事件(网址)
		<input type="text" name="menuItems22_key" value="${menuItems22_key}" /><br>
		
		子菜单项2_3&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems23_type">
	    	<option value="click" ${menuItems23_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems23_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems23_name" value="${menuItems23_name}" />
		事件(网址)
		<input type="text" name="menuItems23_key" value="${menuItems23_key}" /><br>
		
		子菜单项2_4&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems24_type">
	    	<option value="click" ${menuItems24_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems24_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems24_name" value="${menuItems24_name}" />
		事件(网址)
		<input type="text" name="menuItems24_key" value="${menuItems24_key}" /><br>
		
		子菜单项2_5&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems25_type">
	    	<option value="click" ${menuItems25_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems25_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems25_name" value="${menuItems25_name}" />
		事件(网址)
		<input type="text" name="menuItems25_key" value="${menuItems25_key}" /><br>
	</div>
	<div class="block" id="noSub2" style="display:none;">
		类型
		<select class="filter" name="menuItems2_type">
	    	<option value="click" ${menuItems2_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems2_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems2_name" value="${menuItems2_name}" />
		事件(网址)
		<input type="text" name="menuItems2_key" value="${menuItems2_key}" />
	</div><br><br>
	
	一级菜单项3
	<select class="filter" name="sel3">
		<option value="">请选择是否有子菜单</option>
    	<option value="1" ${sel3==1?"selected":""}>是</option>
    	<option value="-1" ${sel3==-1?"selected":""}>否</option>
	</select>
	<div class="block" id="isSub3" style="display:none;">
		名字<input type="text" name="name3" value="${name3}" /><br>
		
		子菜单项3_1&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems31_type">
	    	<option value="click" ${menuItems31_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems31_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems31_name" value="${menuItems31_name}" />
		事件(网址)
		<input type="text" name="menuItems31_key" value="${menuItems31_key}" /><br>
		
		子菜单项3_2&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems32_type">
	    	<option value="click" ${menuItems32_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems32_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems32_name" value="${menuItems32_name}" />
		事件(网址)
		<input type="text" name="menuItems32_key" value="${menuItems32_key}" /><br>
		
		子菜单项3_3&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems33_type">
	    	<option value="click" ${menuItems33_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems33_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems33_name" value="${menuItems33_name}" />
		事件(网址)
		<input type="text" name="menuItems33_key" value="${menuItems33_key}" /><br>
		
		子菜单项3_4&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems34_type">
	    	<option value="click" ${menuItems34_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems34_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems34_name" value="${menuItems34_name}" />
		事件(网址)
		<input type="text" name="menuItems34_key" value="${menuItems34_key}" /><br>
		
		子菜单项3_5&nbsp;&nbsp;&nbsp;
		类型
		<select class="filter" name="menuItems35_type">
	    	<option value="click" ${menuItems35_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems35_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems35_name" value="${menuItems35_name}" />
		事件(网址)
		<input type="text" name="menuItems35_key" value="${menuItems35_key}" /><br>
	</div>
	<div class="block" id="noSub3" style="display:none;">
		类型
		<select class="filter" name="menuItems3_type">
	    	<option value="click" ${menuItems3_type.equals("click")?"selected":""}>click</option>
	    	<option value="view" ${menuItems3_type.equals("view")?"selected":""}>view</option>
		</select>
		名字
		<input type="text" name="menuItems3_name" value="${menuItems3_name}" />
		事件(网址)
		<input type="text" name="menuItems3_key" value="${menuItems3_key}" />
	</div><br><br>
	 
	<br><button type="submit" class="button-disk">Save</button>
</table>
</form>
</div>
<%@ include file="../inc_footer.jspf"%>
<script>
$(function(){
	var selected = $("select[name=sel1]").val();
	if (selected==1) {
		$("#noSub1").css('display','none');
		$("#isSub1").css('display','');
	} else if (selected==-1){
		$("#isSub1").css('display','none');
		$("#noSub1").css('display','');
	} else { 
		$("#noSub1").css('display','none');
		$("#isSub1").css('display','none');
	}
	var selected = $("select[name=sel2]").val();
	if (selected==1) {
		$("#noSub2").css('display','none');
		$("#isSub2").css('display','');
	} else if (selected==-1){
		$("#isSub2").css('display','none');
		$("#noSub2").css('display','');
	} else { 
		$("#noSub2").css('display','none');
		$("#isSub2").css('display','none');
	}
	var selected = $("select[name=sel3]").val();
	if (selected==1) {
		$("#noSub3").css('display','none');
		$("#isSub3").css('display','');
	} else if (selected==-1){
		$("#isSub3").css('display','none');
		$("#noSub3").css('display','');
	} else { 
		$("#noSub3").css('display','none');
		$("#isSub3").css('display','none');
	}
	
	
	$("select[name=sel1]").change(function() {
		var selected = $("select[name=sel1]").val();
		if (selected==1) {
			$("#noSub1").css('display','none');
			$("#isSub1").css('display','');
		} else if (selected==-1){
			$("#isSub1").css('display','none');
			$("#noSub1").css('display','');
		} else { 
			$("#noSub1").css('display','none');
			$("#isSub1").css('display','none');
		}
	});
	
	$("select[name=sel2]").change(function() {
		var selected = $("select[name=sel2]").val();
		if (selected==1) {
			$("#noSub2").css('display','none');
			$("#isSub2").css('display','');
		} else if (selected==-1){
			$("#isSub2").css('display','none');
			$("#noSub2").css('display','');
		} else { 
			$("#noSub2").css('display','none');
			$("#isSub2").css('display','none');
		}
	});
	
	$("select[name=sel3]").change(function() {
		var selected = $("select[name=sel3]").val();
		if (selected==1) {
			$("#noSub3").css('display','none');
			$("#isSub3").css('display','');
		} else if (selected==-1){
			$("#isSub3").css('display','none');
			$("#noSub3").css('display','');
		} else { 
			$("#noSub3").css('display','none');
			$("#isSub3").css('display','none');
		}
	});

});
</script>
</body>
</html>