<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../partials/head.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
 	
 	$(document).ready(function() {
		/* var parent_val = '${cate.parent}';
		console.log(parent_val); */
		$('select[name=parent] option[value=${cate.parent}]').attr('selected', 'selected');
 		$('input:radio[name=isPublic]:input[value=${cate.isPublic}]').attr('checked', true);
		$('input:radio[name=division]:input[value=${cate.division}]').attr('checked', true);
/* 		$('input:radio[name=isPublic]').val('${cate.isPublic}').attr('checked', true); */
	});  
 	
	var nameChk = 0;
	$(function() {
		$("#nameChk").click(function() {
			nameChk = 0;
			var namev = frm2.name.value;
			if(!namev) {
				console.log('이름을 입력하세요');
				frm2.name.focus();
				return false;
			}
			var usename = $("#name").val();
			$.ajax({
				async: true,
				type: 'POST',
				data : usename,
				url : "cateNameCheck.do",
				dataType: "json",
				contentType: "application/json; charset=UTF-8",
				success: function(data) {
					if(data.cnt>0) {
						document.getElementById('namemsg').style.color = "red";
						document.getElementById('namemsg').innerHTML = "존재하는 이름입니다.";
	                    $("#name").focus();
	                    nameChk = 0;
					} else {
						document.getElementById('namemsg').style.color = "blue";
						document.getElementById('namemsg').innerHTML = "<b>사용 가능</b>한 이름입니다.";
						nameChk = 1;
					}
				},
				error : function(error) {
					$("#name").focus();
				}
			});
		});
	});
 	
 	
 	

</script>
</head>
<body class="skin-red-light sidebar-mini">
<div class="wrapper">
<%@ include file="../partials/header.jsp" %>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>Category<small>Update section</small></h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>Home</a></li>
					<li><a href="#">Category</a></li>
					<li class="active">update section</li>
				</ol>
		</section>
	
		<!-- Main content -->
    	<section class="content">
    	
			<form action="CategoryUpdatePro.do" method="post" name="frm2">
 			<input type="hidden" name="categoryNo" value="${cate.categoryNo}">
			<div class="box" align="center">
					<div class="box-body" style="width: 50%;">
		<table class="table">
			<tr>
				<th>카테고리 번호</th>
				<td>${cate.categoryNo}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="name" id="name" required="required" value="${cate.name}">
					<input type="button" value="중복확인" id="nameChk">
					<span id="namemsg"></span>
				</td>
			</tr>
			<tr>
				<th>상위 카테고리</th>
				<td><c:if test="${!empty list}">
						<select name="parent" id="parent">
							<c:forEach var="list" items="${list}">
								<option value="${list.name}">${list.name}</option>
							</c:forEach>
						</select>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>공개 여부</th>
				<td><input type="radio" name="isPublic" value="0">비공개
					<input type="radio" name="isPublic" value="1">공개
				</td>
			</tr>
			<tr>
				<th>분류</th>
				<td><input type="radio" name="division" value="0">상위카테고리
					<input type="radio" name="division" value="1">하위카테고리
				</td>
			</tr>
			<tr>
				<td colspan="2"><input class="btn bg-maroon" type="submit" value="확인"></td>
			</tr>
			</table>	
			
			</div>
			</div>	
			</form>
	
		</section>	
	</div>
</div>
</body>
</html>	