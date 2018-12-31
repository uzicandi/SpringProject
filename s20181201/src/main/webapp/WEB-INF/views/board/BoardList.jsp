<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="../partials/head.jsp"%>
<title>AdminLTE 2 | Starter</title>

<script type="text/javascript">
	$(function() {
		var keyword = "${keyword}";
		console.log(keyword);
		$("#keyword").val(keyword);
		
// 		if(keyword != null)
// 			$("#keyword option").val(keyword).prop("selected", true);

	})
</script>
<style type="text/css">
th {
	text-align: center;
	font-size: 15px;
}
</style>
</head>

<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper" >

		<!-- Main Header -->
		<header><%@ include file="../partials/header.jsp"%></header>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					게시판 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="home.do"><i class="fa fa-dashboard"></i> BMW</a></li>
					<li class="active">게시판</li>
				</ol>
			</section>
			<!-- Main content -->
			<section class="content container-fluid">


				<%--  <%@ include file="member/LoginView.jsp" %> --%>
				<!--------------------------
        | Your Page Content Here |
        -------------------------->
				<div align="center">
					<c:set var="num" value="${pg.total-pg.start+1}"></c:set>

					<div class="box" style="width: 80%;">
						<!-- /.box-header -->
						<div class="box-body">
							<table id="example1" class="table table-bordered table-striped">
								<thead>
									<tr>
										<th style="width: 10%"><b>번호</b></th>
										<th><b>제목</b></th>
										<th style="width: 10%"><b>글쓴이</b></th>
										<th style="width: 7%"><b>조회</b></th>
										<th style="width: 12%"><b>등록일</b></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="SDBBoard" items="${list }">
										<tr>
											<td align="center">${SDBBoard.boardNo }</td>
											<td align="left"><a
												href="BoardContent.do?boardNo=${SDBBoard.boardNo }">${SDBBoard.title}
													<span style="color: red">[${SDBBoard.replyCnt }]</span>
											</a></td>
											<td align="center">${SDBBoard.memberId }</td>
											<td align="center">${SDBBoard.hits }</td>
											<td align="center">${SDBBoard.regDate }</td>
										</tr>
										<c:set var="num" value="${num - 1 }"></c:set>
									</c:forEach>

								</tbody>
								<tfoot>
									<tr>
										<td colspan="8" align="center"><c:if
												test="${pg.startPage > pg.pageBlock }">
												<a
													href="BoardList.do?currentPage=${pg.startPage-pg.pageBlock}">[이전]</a>
											</c:if> <c:forEach var="i" begin="${pg.startPage }"
												end="${pg.endPage }">
												<a href="BoardList.do?currentPage=${i}">[${i}]</a>
											</c:forEach> <c:if test="${pg.endPage < pg.totalPage }">
												<a
													href="BoardList.do?currentPage=${pg.startPage+pg.pageBlock}">[다음]</a>
											</c:if></td>
									</tr>
								</tfoot>
							</table>
							<div class="progress xs">
								<div class="progress-bar progress-bar-blue" style="width: 100%;"
									align="center"></div>
							</div>
							<div align="center" style="width: 100%">
								<form name="frm" action="BoardList.do?">
									<div class="col-md-10" style="padding-left: 13%">
										<select name="keyword" id="keyword" style="height: 30px">
											<option value="title" >제목</option>
											<option value="content">내용</option>
											<option value="memberId">글쓴이</option>
										</select> <input type="hidden" name="sKeyword" value="${keyword }">
										<input type="search" name="search" required="required"
											style="height: 30px; width: 230px"> <input
											type="submit" value="검색" style="height: 30px">
									</div>
									<div class="col-md-2">
										<a href="BoardWriteForm.do"><button type="button"
												class="btn btn-block btn-success btn-lg"
												style="width: 100px">글쓰기</button></a>
									</div>
								</form>
							</div>
						</div>
						<!-- /.box-body -->
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer><%@ include file="../partials/footer.jsp"%></footer>


	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->
	<%--  <%@ include file="include/plugin_js.jsp" %> --%>

	<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. -->
</body>
</html>
