<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>

<title>BMW</title>
<%@ include file="partials/head.jsp"%>
<style type="text/css">

#nowuser tr:hover {background-color: #ddd;}
#nowuser th {
  padding-top: 12px;
  padding-bottom: 12px;
}

#nowuser td {
  padding-top: 12px;
  padding-bottom: 12px;
}

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

.ellipsis-multi2 {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 1; /* 라인수 */
	-webkit-box-orient: vertical;
	word-wrap: break-word;
	line-height: 2.4em;
	height: 2.2em;
	/* line-height 가 1.2em 이고 3라인을 자르기 때문에 height는 1.2em * 3 = 3.6em */
}

.ellipsis-multi3 {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2; /* 라인수 */
	-webkit-box-orient: vertical;
	word-wrap: break-word;
	line-height: 1.4em;
	height: 2.8em;
	/* line-height 가 1.2em 이고 3라인을 자르기 때문에 height는 1.2em * 3 = 3.6em */
}

.ellipsis-multi4 {
	overflow: hidden;
	text-overflow: ellipsis;
	display: -webkit-box;
	-webkit-line-clamp: 2; /* 라인수 */
	-webkit-box-orient: vertical;
	word-wrap: break-word;
	line-height: 1.3em;
	height: 2.6em;
	/* line-height 가 1.2em 이고 3라인을 자르기 때문에 height는 1.2em * 3 = 3.6em */
}
</style>
</head>

<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<%@ include file="partials/header.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<!-- Main content -->
			<section class="content container-fluid">


				<div class="row content" style="width: 80%">
					<!-- <div class="col-md-12" align="center" style="margin-bottom: 5%; margin-top: 3%">
						<img alt="" src="/uploadImg/externalFile.jpg" width="80%" height="100px">
					</div> -->
					<!-- 랭킹 carousel -->
					<c:if test="${SellRankList.size() > 0 }">
						<div class="col-md-12 sidenav">

							<div align="center" style="margin-top: 5%">
								<h3 class="box-title" style="font-size: 30px">실시간 판매 랭킹</h3>
							</div>
							<div class="box">
								<!-- <div class="box-header with-border" align="center">
								
							</div> -->
								<!-- /.box-header -->
								<div class="box-body">
									<div id="carousel-example-generic" class="carousel slide"
										data-ride="carousel">
										<ol class="carousel-indicators" style="color: red">
											<li data-target="#carousel-example-generic" data-slide-to="0"
												class="active" style="color: red"></li>
											<li data-target="#carousel-example-generic" data-slide-to="1"
												class=""></li>
										</ol>
										<div class="carousel-inner">
											<div class="item active">
												<div class="col-sm-1"></div>
												<c:if test="${SellRankList.size() >= 5 }">
													<c:forEach var="i" begin="0" end="4" step="1">
														<div class="col-sm-2">
															<div style="margin-bottom: 5%; font-size: 30px"
																align="center">
																<b>${SellRankList.get(i).getRankNo() }위</b>
															</div>
															<div style="margin-bottom: 5%">
																<a
																	href="KISItemContent.do?itemNo=${SellRankList.get(i).getItemNo() }"><img
																	class="card-img-top"
																	src="${SellRankList.get(i).getFilePath()}" alt=""
																	style="max-width: 165px; min-width: 100px"
																	height="165px"></a>
															</div>
															<div style="margin-bottom: 55%; font-size: 20px"
																align="center" class="ellipsis-multi4">
																<b><a
																	href="KISItemContent.do?itemNo=${SellRankList.get(i).getItemNo() }">${SellRankList.get(i).getName() }</a></b>
															</div>
														</div>
													</c:forEach>
												</c:if>

												<c:if test="${SellRankList.size() < 5 }">
													<c:forEach var="i" begin="0"
														end="${SellRankList.size() - 1 }" step="1">
														<div class="col-sm-2">
															<div style="margin-bottom: 5%; font-size: 30px"
																align="center">
																<b>${SellRankList.get(i).getRankNo() }위</b>
															</div>
															<div style="margin-bottom: 5%">
																<a
																	href="KISItemContent.do?itemNo=${SellRankList.get(i).getItemNo() }"><img
																	class="card-img-top"
																	src="${SellRankList.get(i).getFilePath()}" alt=""
																	style="max-width: 165px; min-width: 100px"
																	height="165px"></a>
															</div>

															<div style="margin-bottom: 55%; font-size: 20px"
																align="center" class="ellipsis-multi4">
																<b><a
																	href="KISItemContent.do?itemNo=${SellRankList.get(i).getItemNo() }">${SellRankList.get(i).getName() }</a></b>
															</div>
														</div>
													</c:forEach>
												</c:if>
												<div class="col-sm-1"></div>
											</div>

											<c:if test="${SellRankList.size() > 5 }">
												<div class="item">
													<div class="col-sm-1"></div>
													<c:if test="${SellRankList.size() <= 9 }">
														<c:forEach var="i" begin="5"
															end="${SellRankList.size() - 1 }" step="1">
															<div class="col-sm-2">
																<div style="margin-bottom: 5%; font-size: 30px"
																	align="center">
																	<b>${SellRankList.get(i).getRankNo() }위</b>
																</div>
																<div style="margin-bottom: 5%">
																	<a
																		href="KISItemContent.do?itemNo=${SellRankList.get(i).getItemNo() }"><img
																		class="card-img-top"
																		src="${SellRankList.get(i).getFilePath()}" alt=""
																		style="max-width: 165px; min-width: 100px"
																		height="165px"></a>
																</div>
																<div style="margin-bottom: 55%; font-size: 20px"
																	align="center" class="ellipsis-multi4">
																	<b><a
																		href="KISItemContent.do?itemNo=${SellRankList.get(i).getItemNo() }">${SellRankList.get(i).getName() }</a></b>
																</div>
															</div>
														</c:forEach>
													</c:if>

													<div class="col-sm-1"></div>
												</div>
											</c:if>
										</div>
										<a class="left carousel-control"
											href="#carousel-example-generic" data-slide="prev"
											style="color: #d81b60; padding-right: 8%"> <span
											class="fa fa-angle-left"></span>
										</a> <a class="right carousel-control"
											href="#carousel-example-generic" data-slide="next"
											style="color: #d81b60; padding-left: 8%"> <span
											class="fa fa-angle-right"></span>
										</a>
									</div>
								</div>
								<!-- /.box-body -->
							</div>
							<!-- /.box -->
						</div>
					</c:if>

					<!-- 많이 본 리뷰 -->
					<c:if test="${HitsItemReviewRank.size() >= 2 }">
						<div class="col-md-12">
							<div style="font-size: 30px" align="center">
								<p>많이 찾는 제품</p>
							</div>
							<div class="box">
								<div class="box-body">
									<c:forEach var="i" begin="0" end="1" step="1">
										<div class="col-md-6">
											<div class="col-md-5">
												<a
													href="KISItemContent.do?itemNo=${HitsItemReviewRank.get(i).getItemNo() }"><img
													class="card-img-top"
													src="${HitsItemReviewRank.get(i).getFilePath()}" alt=""
													width="200px" height="200px"></a>
											</div>

											<div class="col-md-1"></div>

											<div class="col-md-6">
												<div class="col-md-12">
													<p style="font-size: 20px">${HitsItemReviewRank.get(i).getBrand() }</p>
												</div>
												<div class="ellipsis-multi3 col-md-12"
													style="margin-bottom: 10%">
													<p style="font-size: 20px">
														<a
															href="KISItemContent.do?itemNo=${HitsItemReviewRank.get(i).getItemNo() }">${HitsItemReviewRank.get(i).getName() }</a>
													</p>
												</div>
												<div class="col-md-12">
													<p style="font-size: 20px">
														<fmt:formatNumber pattern="###,###,###"
															value="${HitsItemReviewRank.get(i).getPrice() }" />
														원
													</p>
													<p style="font-size: 20px">
														<c:choose>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 4.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 4 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 3.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 3 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 2.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 2 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 1.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 1 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 0.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() > 0 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${HitsItemReviewRank.get(i).getRating() == 0 }">
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
														</c:choose>
													</p>
												</div>
												<!-- 	<div class="col-md-6"></div>
										<div class="col-md-12"></div> -->
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</c:if>


					<!-- Beauty Plus -->
					<div class="col-sm-6 sidenav">

						<div style="font-size: 30px">
							<p>Beauty Plus</p>
						</div>

						<c:if test="${WebzineList.size() == 0 }">
							<h1>웹진 페이지 공사중입니다.</h1>
						</c:if>

						<c:if test="${WebzineList.size() != 0 }">
							<c:forEach var="i" begin="0"
								end="${WebzineList.size() - (WebzineList.size()-1) }" step="1">
								<div class="box">
									<div class="box-header">
										<a
											href="BoardContent.do?boardNo=${WebzineList.get(i).getBoardNo() }"><img
											class="card-img-top"
											src="/uploadImg/board/${WebzineSaveFilesList.get(i).getSavedFileName()}"
											alt="" width="100%" height="350px"></a>
									</div>
									<div class="box-body">
										<!-- Color Picker -->
										<div class="ellipsis-multi" style="font-size: 15px">
											<a
												href="BoardContent.do?boardNo=${WebzineList.get(i).getBoardNo() }">
												<b>${WebzineList.get(i).getTitle()}</b>
											</a>
										</div>
										<!-- /.form group -->

										<!-- Color Picker -->
										<div class="form-group">
											<h5>Editor : ${WebzineList.get(i).getMemberId() }</h5>
										</div>
										<!-- /.form group -->
									</div>
									<!-- /.box-body -->
								</div>
							</c:forEach>
						</c:if>
					</div>

					<!-- 지금 BMW 유저들은? -->
					<div class="col-sm-6">
						<div style="font-size: 30px">
							<p>지금 BMW 유저들은?</p>
						</div>
						<div class="box">

							<%-- <table style="width: 100%; font-size: 25px">
								<thead>
									<tr>
										<th style="width: 20%; text-align: center">번호</th>
										<th>제목</th>
										<th style="width: 20%; text-align: center">댓글</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="i" begin="0" end="${InfoTotal - 1 }" step="1">
										<tr>
											<td align="center">${InfoList.get(i).getBoardNo() }</td>
											<td class="ellipsis-multi2"><a
												href="BoardContent.do?boardNo=${InfoList.get(i).getBoardNo() }">${InfoList.get(i).getTitle() }</a></td>
											<td align="center">${InfoList.get(i).getReplyCnt() }</td>
										</tr>

									</c:forEach>
								</tbody>
							</table> --%>
							
								<table id="nowuser" style="width: 100%; font-size: 25px">
								<thead>
									<tr>
										<th style="width: 20%; text-align: center">번호</th>
										<th>제목</th>
										<th style="width: 20%; text-align: center">댓글</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="i" begin="0" end="${InfoTotal - 1 }" step="1">
										<tr>
											<td align="center">${InfoList.get(i).getBoardNo() }</td>
											<!-- <td class="ellipsis-multi2"> -->
											<td class="ellipsis-multi2" style="padding-bottom: 20%">
											<a
												href="BoardContent.do?boardNo=${InfoList.get(i).getBoardNo() }">${InfoList.get(i).getTitle() }</a></td>
											<td align="center">${InfoList.get(i).getReplyCnt() }</td>
										</tr>

									</c:forEach>
								</tbody>
							</table>


						</div>
					</div>
				</div>





			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<%@ include file="partials/footer.jsp"%>


	</div>
	<!-- ./wrapper -->


</body>
</html>
