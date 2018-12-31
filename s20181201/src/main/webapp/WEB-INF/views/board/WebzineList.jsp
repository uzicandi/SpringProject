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
<style type="text/css">
.ellipsis-multi {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2; /* 라인수 */
	-webkit-box-orient: vertical;
	word-wrap: break-word;
	line-height: 1.2em;
	height: 2.4em;
	/* line-height 가 1.2em 이고 3라인을 자르기 때문에 height는 1.2em * 3 = 3.6em */
}
</style>
</head>

<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header><%@ include file="../partials/header.jsp"%></header>

		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					웹진 <small>뷰티 정보</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="home.do"><i class="fa fa-dashboard"></i> BMW</a></li>
					<li class="active">웹진</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">


				<%--  <%@ include file="member/LoginView.jsp" %> --%>
				<!--------------------------
        | Your Page Content Here |
        -------------------------->

				<div class="container">
					<c:if test="${list.size() == 0 }">
						<h1>웹진 페이지 공사중입니다.</h1>
					</c:if>

					<c:if test="${list.size() != 0 }">
						<div class="row">
							<c:forEach var="i" begin="0" end="${list.size()-1 }" step="1">
								<div>
									<div class="col-lg-5 portfolio-item"
										style="margin-bottom: 20px">
										<div class="card h-100">
											<a
												href="BoardContent.do?boardNo=${list.get(i).getBoardNo() }"><img
												class="card-img-top"
												src="/uploadImg/board/${saveFiles.get(i).getSavedFileName()}"
												alt="" width="100%" height="300px"></a>
											<div class="card-body">
												<h4 class="card-title">
													<a
														href="BoardContent.do?boardNo=${list.get(i).getBoardNo() }">
														<p class="ellipsis-multi">${list.get(i).getTitle()}</p>

													</a>
												</h4>
												<h5>Editor : ${list.get(i).getMemberId() }</h5>
											</div>
										</div>
										<div class="progress xs">
											<div class="progress-bar progress-bar-blue"
												style="width: 98.5%;"></div>
										</div>
									</div>
									<div class="col-md-1"></div>
								</div>
							</c:forEach>
						</div>
					</c:if>

				</div>



			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer><%@ include file="../partials/footer.jsp"%></footer>


	</div>
	<!-- ./wrapper -->

</body>
</html>
