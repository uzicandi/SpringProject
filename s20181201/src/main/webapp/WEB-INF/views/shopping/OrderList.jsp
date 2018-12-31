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

			<section class="content-header" style="margin-bottom: 2%">
				<h1>
					주문내역 <small>결제 상품 리스트</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="home.do"><i class="fa fa-dashboard"></i> BMW</a></li>
					<li class="active">주문내역</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">


				<%--  <%@ include file="member/LoginView.jsp" %> --%>
				<!--------------------------
        | Your Page Content Here |
        -------------------------->
				<div class="container">

					<!-- <section class="content"> -->
					<!-- Small boxes (Stat box) -->
					<div class="row">
						<div class="col-lg-4 col-xs-6">
							<!-- small box -->
							<div class="small-box bg-aqua">
								<div class="inner">
									<h3>${curPoint }원</h3>

									<p>보유 포인트</p>
								</div>
								<div class="icon">
									<i class="fa fa-database"></i>
								</div>

							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-4 col-xs-6">
							<!-- small box -->
							<div class="small-box bg-green">
								<div class="inner">
									<h3>${complete }</h3>

									<p>결제확인/완료</p>
								</div>
								<div class="icon">
									<i class="fa fa-inbox"></i>
								</div>

							</div>
						</div>
						<!-- ./col -->
						<div class="col-lg-4 col-xs-6">
							<!-- small box -->
							<div class="small-box bg-yellow">
								<div class="inner">
									<h3>${delivery }</h3>

									<p>배송중/완료</p>
								</div>
								<div class="icon">
									<i class="fa fa-truck"></i>
								</div>

							</div>
						</div>
					</div>

					<!-- 아이템 반복 -->
					<c:forEach var="spList" items="${list }">
						<div class="row">
							<div class="col-md-12">
								<div class="box">
									<div class="box-header with-border"></div>
									<!-- /.box-header -->
									<div class="box-body">
										<div class="row">
											<div class="col-md-4" align="center">

												<div class="chart">
													<!-- Sales Chart Canvas -->
													<a href="KISItemContent.do?itemNo=${spList.itemNo }"> <img
														src="${spList.filePath }" width="165px" height="165px">
													</a>
												</div>
												<!-- /.chart-responsive -->
											</div>
											<!-- /.col -->
											<div class="col-md-8">
												<div class="progress-group">
													<span class="progress-text" style="font-size: 30px">
														<a href="KISItemContent.do?itemNo=${spList.itemNo }">
															${spList.name } <br>
													</a>
													</span>
												</div>
												<!-- /.progress-group -->
												<div class="progress-group" style="font-size: 20px">
													<span class="progress-text"> ${spList.price }원 &nbsp
														&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp
														${spList.regDate } <br>
													</span>

												</div>
												<div>
													<p><b>수량 : ${spList.amount }개</b></p>
												</div>
												<!-- /.progress-group -->
												<div class="progress-group">
													<span class="progress-text"> <c:choose>
															<c:when test="${spList.status == '0'}">
											결제완료 <br>
															</c:when>
															<c:when test="${spList.status == '1'}">
											배송중 <br>
															</c:when>
															<c:when test="${spList.status == '2'}">
											배송완료 <br>
															</c:when>
														</c:choose>
													</span>

													<!-- <div class="progress sm">
														<div class="progress-bar progress-bar-black"
															style="width: 85%"></div>
													</div> -->
												</div>
												<!-- /.progress-group -->
												<div class="progress-group">
													<span class="progress-text"> <c:choose>
															<c:when test="${spList.status == '0' }">
											결제가 완료되었습니다.
										</c:when>
															<c:when test="${spList.status == '1' }">
											주문하신 상품이 현재 배송중입니다.
										</c:when>
															<c:when test="${spList.status == '2' }">
											구매가 완료되었습니다. 이용해주셔서 감사합니다.
										</c:when>
														</c:choose>
													</span>
													<!-- <div class="progress sm">
														<div class="progress-bar progress-bar-black"
															style="width: 85%"></div>
													</div> -->
												</div>
												<div>
													<p><b>메모 : ${spList.memo }</b></p>
												</div>
												<!-- /.progress-group -->
											</div>
											<!-- /.col -->
										</div>
										<!-- /.row -->
									</div>
									<!-- ./box-body -->
									<div class="box-footer"></div>
									<!-- /.box -->
								</div>
								<!-- /.col -->
							</div>
						</div>
					</c:forEach>




					<%-- <table border="1">
						<c:forEach var="spList" items="${list }">
							<tr>
								<td rowspan="4"><a
									href="KISItemContent.do?itemNo=${spList.itemNo }">아이템의 사진
										들어갈 자리</a></td>
								<td colspan="2"><a
									href="KISItemContent.do?itemNo=${spList.itemNo }">${spList.name }</a>
								</td>
							</tr>
							<tr>
								<td>${spList.price }원</td>
								<td>${spList.regDate }</td>
							</tr>
							<tr>
								<td colspan="2"><c:choose>
										<c:when test="${spList.status == '0'}">
											결제완료
										</c:when>
										<c:when test="${spList.status == '1'}">
											배송중
										</c:when>
										<c:when test="${spList.status == '2'}">
											배송완료
										</c:when>
									</c:choose></td>
							</tr>
							<tr>
								<td colspan="2"><c:choose>
										<c:when test="${spList.status == '0' }">
											결제가 완료되었습니다.
										</c:when>
										<c:when test="${spList.status == '1' }">
											주문하신 상품이 현재 배송중입니다.
										</c:when>
										<c:when test="${spList.status == '2' }">
											구매가 완료되었습니다. 이용해주셔서 감사합니다.
										</c:when>
									</c:choose></td>
							</tr>
						</c:forEach>
					</table> --%>
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
