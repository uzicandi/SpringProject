<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingredient Write View</title>
</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">

		<!-- Main Header -->
		<%@ include file="../partials/header.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					성분 작성 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="KISIngtList.do"><i class="fa fa-dashboard"></i> 성분</a></li>
					<li class="active">성분작성</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">

				<!-- Top content -->
				<div class="top-content">

					<div class="inner-bg">
						<div class="container">
							<div class="row">
								<div class="col-sm-8 col-sm-offset-2 text">

	<form action="KISIngtWrite.do" method="post">
		<table class="table">
			<!-- <tr>
				<th width="200">성분번호</th>
				<td><input type="text" name="ingredientNo" required="required"></td>
			</tr> -->
			<tr>
				<th width="200">성분명</th>
				<td><input class="form-control" style="width:60%;" type="text" name="name" required="required"></td>
			</tr>
			<tr>
				<th>위험도 등급</th>
				<td><input class="form-control" style="width:60%;" type="text" name="grade" required="required"></td>
			</tr>
			<tr>
				<th>20가지 주의성분</th>
				<td><input class="form-control" style="width:60%;" type="text" name="danger20"></td>
			</tr>
			<tr>
				<th>알레르기 주의성분</th>
				<td><input class="form-control" style="width:60%;" type="text" name="dangerAllergy"></td>
			</tr>
			<tr>
				<th>피부타입별 특이성분</th>
				<td><input class="form-control" style="width:60%;" type="text" name="specialyType"></td>
			</tr>
			<tr>
				<th>기능성 성분 여부</th>
				<td><input class="form-control" style="width:60%;" type="text" name="functional"></td>
			</tr>		
			
		</table>
		<div align="center">
				<input class="btn bg-maroon" type="submit" value="확인">
				<input class="btn bg-maroon" type="reset" value="다시작성">
				<input class="btn bg-maroon" type="button" value="목록가기" onclick="location.href='KISIngtList.do'">
			</div>
	</form>
	</div>
	</div>
	</div>
	</div>
							
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@ include file="../partials/footer.jsp"%>


	</div>
</body>
</html>