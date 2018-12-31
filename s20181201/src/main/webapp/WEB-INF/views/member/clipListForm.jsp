<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나의 스크랩</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">
* {
	box-sizing: border-box;
}

table {
	width: 100%;
}

body {
	margin: 0;
}

th {
	background-color: #cce8f4;
	text-align: center;
}

th.title {
	text-align: center;
	display: table-cell;
	vertical-align: middle;
}

td {
	background-color: #eee;
	text-align: center;
}

/* Style the header */
.header {
	background-color: #f1f1f1;
	padding: 20px;
	text-align: center;
}

</style>


</head>
<section class="content container-fluid">

	<div id="page-wrapper">

		<!-- Main -->
		<section id="main">
			<div class="container">
				<div class="row">
					<div>
						<!-- small box -->
						<div class="small-box bg-green">
							<div class="inner">
								<h3>스크랩보기</h3>

								<p>check your scrap</p>
							</div>
							<div class="icon">
								<i class="fa fa-database"></i>
							</div>

						</div>

					</div>



					<%-- 값 : view=${view }, delview=${delview } --%>
					<!-- Blog -->
					<c:if test="${view == 1 }">
						<c:if test="${delview == 1 }">
							<form action="clipDelete.do" method="post" id="frm" onsubmit="_submit(this);">
								<input type="hidden" name="delview" value="1">
								<button type="reset" class="btn btn-info" onclick="delok()">
									<i class="fa fa-times"></i> 삭제
								</button>
								<input type="button" class="btn btn-default" value="돌아가기" onclick="scrapView(${pg.currentPage})">

								<!-- Table row -->
								<div class="row">
									<div class="col-xs-12 table-responsive">
										<table class="table table-striped">
											<thead>
												<tr>
													<th>삭제 항목</th>
													<th>제품명</th>
													<th>리뷰 내용</th>
													<th>스크랩 일시</th>
												</tr>
											</thead>

											<tbody>
												<c:forEach var="clip" items="${list }">
													<tr>
														<td><input type="checkbox" name="mainNo"
															value="${clip.mainNo}" /> <input type="hidden"
															name="subNo" value="${clip.subNo}" /></td>
														<td>${clip.name}</td>
														<td>${clip.title}</td>
														<td>${clip.regDate}</td>
													</tr>
													<c:set var="num" value="${num - 1 }" />
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</form>

							<div style="text-align: center;">
								<c:if test="${pg.startPage > pg.pageBlock }">
									<input type="button" onclick="scrapDelView(${pg.startPage-pg.pageBlock})" value="[이전]"/>
								</c:if>
								<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
									<input type="button" onclick="scrapDelView(${i})" value="${i}"/>
								</c:forEach>
								<c:if test="${pg.endPage < pg.totalPage }">
									<input type="button" onclick="scrapDelView(${pg.startPage+pg.pageBlock})" value="[다음]"/>
								</c:if>
							</div>
						</c:if>
						<c:if test="${delview == 0 }">
							<tr>
								<c:if test="${totCnt > 0 }">
	
									<button value="편집" class="btn btn-info"
										onclick="scrapDelView(${pg.currentPage})">
										<i class="fa fa-edit">편집</i>
									</button>
									<input type="button" class="btn btn-default" value="게시글 보기" onclick="scrapFirstView2()">
						
								</c:if>
							</tr>

							<!-- Table row -->
							<div class="row">
								<div class="col-xs-12 table-responsive">
									<table class="table table-striped">
										<thead>

											<tr>
												<th>제품명</th>
												<th>리뷰내용</th>
												<th>스크랩 일시</th>
											</tr>
										</thead>
										<tbody>

											<c:if test="${totCnt > 0 }">
												<c:forEach var="Clip" items="${list }">
													<tr>
														<td>${Clip.name }</td>
														<td><a href='JJYItemReviewContent.do?itemNo=${Clip.mainNo}&reviewNo=${Clip.subNo }'>${Clip.title}</a></td>
														<td>${Clip.regDate}</td>
													</tr>
													<c:set var="num" value="${num - 1 }" />
												</c:forEach>
											</c:if>


										</tbody>
										<c:if test="${totCnt == 0 }">
											<tr>
												<td colspan=7>스크랩한 리뷰가 없습니다</td>
											</tr>
										</c:if>
									</table>
								</div>
								<!-- /.col -->
							</div>
							<!-- /.row -->



							<div style="text-align: center;">
								<c:if test="${pg.startPage > pg.pageBlock }">
									<input type="button" onclick="scrapView(${pg.startPage-pg.pageBlock})" value="[이전]"/>
								</c:if>
								<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
									<input type="button" onclick="scrapView(${i})" value="${i}">
								</c:forEach>
								<c:if test="${pg.endPage < pg.totalPage }">
									<input type="button" onclick="scrapView(${pg.startPage+pg.pageBlock})" value="[다음]"/>
								</c:if>
							</div>
						</c:if>
					</c:if>


					<c:if test="${view == 2 }">
						<h2>스크랩한 게시글</h2>
						<c:if test="${delview == 2 }">
							<form action="clipDelete.do" id="frm2" method="post" onsubmit="_submit(this);">
								<input type="hidden" name="delview" value="2">
								<button type="reset" class="btn btn-info" onclick="delok2()">
									<i class="fa fa-times"></i> 삭제
								</button>
								<input type="button" value="돌아가기" class="btn btn-default" onclick="scrapView2(${pg.currentPage})">

								<!-- Table row -->
								<div class="row">
									<div class="col-xs-12 table-responsive">
										<table class="table table-striped">
											<thead>
												<tr>
													<th>삭제 항목</th>
													<th>제목</th>
													<th>작성자</th>
													<th>스크랩 일시</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="clip" items="${list }">
													<tr>
														<td><input type="checkbox" name="mainNo"
															value="${clip.mainNo}" />
														<td class="left" width=200>${clip.title}</td>
														<td>${clip.memberId}</td>
														<td>${clip.regDate}</td>
													</tr>
													<c:set var="num" value="${num - 1 }" />
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</form>

							<div style="text-align: center;">
								<c:if test="${pg.startPage > pg.pageBlock }">
									<input type="button" onclick="scrapDelView2(${pg.startPage-pg.pageBlock})" value="[이전]"/>
								</c:if>
								<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
									<input type="button" onclick="scrapDelView2(${i})" value="${i}"/>
								</c:forEach>
								<c:if test="${pg.endPage < pg.totalPage }">
									<input type="button" onclick="scrapDelView2(${pg.startPage+pg.pageBlock})" value="[다음]"/>
								</c:if>
							</div>
						</c:if>
						<c:if test="${delview == 0 }">
							<tr>
								<c:if test="${totCnt > 0 }">
									<td>
										<button value="편집" class="btn btn-info" onclick="scrapDelView2(${pg.currentPage})">
											<i class="fa fa-edit">편집</i>
										</button>
										<input type="button" value="리뷰 보기" class="btn btn-default" onclick="scrapFirstView()">
									</td>
								</c:if>
							</tr>

							<div class="row">
								<div class="col-xs-12 table-responsive">

									<table class="table table-striped">
										<thead>
											<tr>
												<th>게시글 번호</th>
												<th>제목</th>
												<th>작성자</th>
												<th>스크랩 일시</th>
											</tr>
										<thead>
										<tbody>
											<c:if test="${totCnt > 0 }">
												<c:forEach var="Clip" items="${list }">
													<tr>
														<td>${Clip.mainNo }</td>
														<td class="left" width=200><a
															href="BoardContent.do?boardNo=${Clip.mainNo }">${Clip.title}</a>
														</td>
														<td>${Clip.memberId}</td>
														<td>${Clip.regDate}</td>
													</tr>
													<c:set var="num" value="${num - 1 }" />
												</c:forEach>
											</c:if>
										</tbody>
										<c:if test="${totCnt == 0 }">
											<tr>
												<td colspan=7>스크랩한 게시글이 없습니다</td>
											</tr>
										</c:if>
									</table>
								</div>
							</div>


							<div style="text-align: center;">
								<c:if test="${pg.startPage > pg.pageBlock }">
									<input type="button" onclick="scrapView2(${pg.startPage-pg.pageBlock})" value="[이전]"/>
								</c:if>
								<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
									<input type="button" onclick="scrapView2(${i})" value="${i}"/>
								</c:forEach>
								<c:if test="${pg.endPage < pg.totalPage }">
									<input type="button" onclick="scrapView2(${pg.startPage+pg.pageBlock})" value="[다음]"/>
								</c:if>
							</div>
						</c:if>
					</c:if>
					<!-- 	</form> -->
				</div>
			</div>
		</section>
	</div>

</section>
</body>
</html>