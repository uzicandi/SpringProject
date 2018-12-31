<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Ingredient View</title>

<style type="text/css">
th {
	text-align: center;
}
td {
	text-align: center;
}
</style>

</head>
<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper">

		<%@ include file="../partials/header.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					성분 상세내역<small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="KISIngtList.do"><i class="fa fa-dashboard"></i>성분</a></li>
					<li class="active">성분상세내역</li>
				</ol>
			</section>



			<!-- Main content -->
			<section class="content container-fluid">

				<div class="box" align="center">
					<div class="box-header with-border">
						<h3 class="box-title"></h3>
					</div>
					<div class="box-body" style="width: 50%;">

						<table class="table table-striped" >
							<tr>
								<th width="200">성분 번호</th>
								<td>${ingt.ingredientNo}</td>
							</tr>
							<tr>
								<th>성분명</th>
								<td>${ingt.name}</td>
							</tr>
							<tr>
								<th>위험등급</th>
								<td>${ingt.grade}</td>
							</tr>
							<tr>
								<th>20가지 주의성분</th>
								<td>${ingt.danger20}</td>
							</tr>
							<tr>
								<th>알레르기 주의성분</th>
								<td>${ingt.dangerAllergy}</td>
							</tr>
							<tr>
								<th>피부타입별 특이성분</th>
								<td>${ingt.specialyType}</td>
							</tr>
							<tr>
								<th>기능성 성분 여부</th>
								<td>${ingt.functional}</td>
							</tr>

						</table>

						<div align="center">
							<input type="button" class="btn bg-maroon" value="수정"
								onclick="location.href='KISIngtUpdateForm.do?ingredientNo=${ingt.ingredientNo}'">
							<input type="button" class="btn bg-maroon" value="삭제"
								onClick="location.href='KISIngtDelete.do?ingredientNo=${ingt.ingredientNo}'">
							<input type="button" class="btn bg-maroon" value="목록"
								onclick="location.href='KISIngtList.do'">
						</div>
					</div>
				</div>


			</section>

			<!-- /.content -->
		</div>
	</div>
	<!-- /.content-wrapper -->

	<%@ include file="../partials/footer.jsp"%>

	<div class="control-sidebar-bg"></div>
</body>
</html>