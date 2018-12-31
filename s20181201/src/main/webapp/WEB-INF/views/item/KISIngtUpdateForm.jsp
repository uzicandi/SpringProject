<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ingredient Update View</title>
</head>
<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper">

		<%@ include file="../partials/header.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					성분수정 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>성분</a></li>
					<li class="active">성분수정</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">
	
	<form action="KISIngtUpdate.do" method="post">
	<input type="hidden" name="ingredientNo" value="${ingt.ingredientNo}">
		<div class="box" align="center">
					<div class="box-body" style="width: 50%;">
		<table class="table">
			<tr>
				<th width="200">성분번호</th>
				<td>${ingt.ingredientNo}</td>
			</tr>
			<tr>
				<th>성분명</th>
				<td><input class="form-control" style="width:60%;" type="text" name="name" required="required"
					value="${ingt.name}"></td>
			</tr>
			<tr>
				<th>위험도</th>
				<td><input class="form-control" style="width:60%;" type="text" name="grade" required="required"
					value="${ingt.grade}"></td>
			</tr>
			<tr>
				<th>20가지 주의성분</th>
				<td><input class="form-control" style="width:60%;" type="text" name="danger20" 
					value="${ingt.danger20}"></td>
			</tr>
			<tr>
				<th>알레르기 주의성분</th>
				<td><input class="form-control" style="width:60%;" type="text" name="dangerAllergy"
					value="${ingt.dangerAllergy}"></td>
			</tr>
			<tr>
				<th>피부타입별 특이성분</th>
				<td><input class="form-control" style="width:60%;" type="text" name="specialyType"
					value="${ingt.specialyType}"></td>
			</tr>
			<tr>
				<th>기능성 성분</th>
				<td><input class="form-control" style="width:60%;" type="text" name="functional"
					value="${ingt.functional}"></td>
			</tr>
		</table>
		<div align="center">
			<input class="btn bg-maroon" type="submit" value="수정완료">
			<input class="btn bg-maroon" type="button" value="뒤로가기" onclick="history.go(-1)">				
		</div>
		</div>
		</div>
		
	</form>
			</section>
			
			<!-- /.content -->
		</div>
	</div>
		<!-- /.content-wrapper -->

		<%@ include file="../partials/footer.jsp"%>

		<div class="control-sidebar-bg"></div>
</body>
</html>