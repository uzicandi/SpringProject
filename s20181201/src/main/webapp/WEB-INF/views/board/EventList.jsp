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

	})
</script>
</head>

<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header><%@ include file="../partials/header.jsp"%></header>


		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					이벤트 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="home.do"><i class="fa fa-dashboard"></i> BMW</a></li>
					<li class="active">이벤트</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">


				<%--  <%@ include file="member/LoginView.jsp" %> --%>
				<!--------------------------
        | Your Page Content Here |
        -------------------------->

				<div class="container">
			
					<table>
						<tr>
							<td>
							<c:if test="${list.size() != 0 }">
								<c:forEach var="i" begin = "${currentPage-1 }" end = "${currentPage-1 }">
									<a href="BoardContent.do?boardNo=${list.get(i).getBoardNo() }">
										<img src="/uploadImg/board/${saveFiles.get(i).getSavedFileName()}" alt="" width="800" height="800" />
									</a>
								</c:forEach>
							</c:if>
							<c:if test="${list.size() == 0 }">
								<h1>진행중인 이벤트가 없습니다. ㅠㅠ</h1>
							</c:if>
							</td>
						</tr>
						<tr>
							<td align="center">
								<c:if test="${pg.startPage > pg.pageBlock }">
									<a href="EventList.do?currentPage=${pg.startPage-pg.pageBlock}">[이전]</a>
								</c:if>
								<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
									<a href="EventList.do?currentPage=${i}">[${i}]</a>
								</c:forEach>
								<c:if test="${pg.endPage < pg.totalPage }">
									<a href="EventList.do?currentPage=${pg.startPage+pg.pageBlock}">[다음]</a>
								</c:if>
							</td>
						</tr>
					</table>

				</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer><%@ include file="../partials/footer.jsp"%></footer>

	</div>
	
</body>
</html>
