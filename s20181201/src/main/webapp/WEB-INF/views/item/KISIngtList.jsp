<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>Ingredient List View</title>
<script>
	function searchCheck(frm) {
		if (frm.keyword.value == "") {
			alert("검색 단어를 입력하세요.");
			frm.keyword.focus();
			return;
		}
		frm.submit();
	}
</script>
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
					성분리스트 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="KISIngtList.do"><i class="fa fa-dashboard"></i> 성분</a></li>
					<li class="active">성분리스트</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">


				<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title"></h3>
					</div>
					<div class="box-body">

							<table class="table table-striped">
								<tr>
									<td style="align: left;"><a href="KISIngtWriteForm.do"><button class="btn bg-maroon">성분
												등록</button></a></td>
									<td colspan="6">
										<form name="search" method="post">
											<input type="search" name="keyword"> <input
												type="button" class="btn bg-maroon" value="검색" onclick="searchCheck(form)">
										</form>
									</td>
								</tr>
								<tr>
									<th class="title">번호</th>
									<th class="title">성분명</th>
									<th class="title">위험등급</th>
									<th class="title">20가지 주의성분</th>
									<th class="title">알레르기 주의성분</th>
									<th class="title">피부타입별 특이성분</th>
									<th class="title">기능성 성분 여부</th>
								</tr>
								<c:set var="num" value="${pg.total-pg.start+1}"></c:set>
								<c:if test="${total > 0 }">
									<c:forEach var="ingt" items="${list}">
										<tr>
											<td>${num}</td>
											<td><a
												href="KISIngtContent.do?ingredientNo=${ingt.ingredientNo}">${ingt.name}</a>
											</td>
											<td>
												<!-- 				위험도 등급에 따른 이미지 -->
												<c:choose>
												<c:when test="${ingt.grade == 1}">
													<img src="./resources/images/grade1.png" width="30px" height="35">
												</c:when>
												<c:when test="${ingt.grade == 2}">
													<img src="./resources/images/grade2.png" width="30px" height="35">
												</c:when>
												<c:when test="${ingt.grade == 3}">
													<img src="./resources/images/grade3.png" width="30px" height="35">
												</c:when>
												<c:when test="${ingt.grade == 4}">
													<img src="./resources/images/grade4.png" width="30px" height="35">
												</c:when>
												<c:when test="${ingt.grade == 5}">
													<img src="./resources/images/grade5.png" width="30px" height="35">
												</c:when>
												<c:when test="${ingt.grade == 6}">
													<img src="./resources/images/grade6.png" width="30px" height="35">
												</c:when>
												<c:when test="${ingt.grade == 7}">
													<img src="./resources/images/grade7.png" width="30px" height="35">
												</c:when>
												<c:when test="${ingt.grade == 8}">
													<img src="./resources/images/grade8.png" width="30px" height="35">
												</c:when>
												<c:when test="${ingt.grade == 9}">
													<img src="./resources/images/grade9.png" width="30px" height="35">
												</c:when>
											</c:choose>
											</td>
											<td>${ingt.danger20}</td>
											<td>${ingt.dangerAllergy}</td>
											<td>${ingt.specialyType}</td>
											<td>${ingt.functional}</td>
										</tr>
										<c:set var="num" value="${num - 1}" />
									</c:forEach>
								</c:if>
								<c:if test="${total == 0}">
									<tr>
										<td colspan=7>데이터가 없네</td>
									</tr>
								</c:if>
							</table>
							<div style="text-align: center;">
								<c:choose>
									<c:when test="${ingt.keyword == null}">
										<c:if test="${pg.startPage > pg.pageBlock}">
											<a
												href="KISIngtList.do?currentPage=${pg.startPage - pg.pageBlock}">[이전]</a>
										</c:if>
										<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
											<a href="KISIngtList.do?currentPage=${i}">[${i}]</a>
										</c:forEach>
										<c:if test="${pg.endPage < pg.totalPage}">
											<a
												href="KISIngtList.do?currentPage=${pg.startPage + pg.pageBlock}">[다음]</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<c:if test="${pg.startPage > pg.pageBlock}">
											<a
												href="KISIngtList.do?currentPage=${pg.startPage - pg.pageBlock}">[이전]</a>
										</c:if>
										<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
											<a href="KISIngtList.do?currentPage=${i}">[${i}]</a>
										</c:forEach>
										<c:if test="${pg.endPage < pg.totalPage}">
											<a
												href="KISIngtList.do?currentPage=${pg.startPage + pg.pageBlock}">[다음]</a>
										</c:if>
									</c:otherwise>
								</c:choose>
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
	<!-- ./wrapper -->

</body>
</html>