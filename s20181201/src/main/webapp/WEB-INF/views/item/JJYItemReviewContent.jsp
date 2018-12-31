<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

<script type="text/javascript">
	$(function() {
		$("reviewComment")
				.load(
						'JJYCommentContent.do?mainNo=${comm.mainNo}&subNo=${comm.subNo }&kinds=0');
	});

	function reviewUpdate() {
		location
				.replace('JJYItemReviewUpdateForm.do?itemNo=${comm.mainNo}&reviewNo=${comm.subNo }&currentPage=${currentPage }&currentPageRv=${currentPageRv}');
	}

	function del_chk() {
		if (confirm("리뷰를 삭제 하시겠습니까?") == true) {
			location
					.replace("JJYItemReviewDeletePro.do?itemNo=${comm.mainNo}&reviewNo=${comm.subNo}&currentPage=${currentPage }&currentPageRv=${currentPageRv}");
		} else {
			return;
		}
	}

	function listView() {
		location
				.replace('KISItemContent.do?itemNo=${comm.mainNo}&currentPage=${currentPage }&currentPageRv=${currentPageRv}');
	}

	$.ajaxSetup({
		type : "POST",
		async : true,
		dataType : "JSON",
		error : function(xhr) {
			console.log("error html = " + xhr.statusText);
		}
	});

		function likeCnt() {
			console.log('좋아요!!!');
			$.ajax({
				url : 'ItemReviewLikeUpdate.do',
				data : {
					mainNo : "${comm.mainNo}",
					subNo : "${comm.subNo}",
					ref_Table : "${comm.ref_Table}",
					ref : "${comm.ref}",
					memberId : "${sessionId}"
				},
				beforeSend : function() {
					console.log("likeCnt 전...");
				},
				complete : function() {
					console.log("likeCnt 완료...!");
				},
				success : function(data) {
	// 				var data = JSON.parse(data);
					console.log("likeCnt()를 정상적으로 성공!!");
					console.log("data -> " + data.likeCnt);
					alert(data.msg);
					$(".likeCount").html(data.likeCnt);

				}
			});
		};

	$(function() {
		$("#InsertClip").click(function() {
			$.ajax({
// 				async : true,
// 				type : 'POST',
				data : {
					mainNo : $("#mainNo").val(),
					subNo : $("#subNo").val()
				},
				url : "reviewContentClipInsert.do",
// 				dataType : "json",
				success : function(data) {
					if (data.chkClip != 0) {
						alert("이미 스크랩 된 리뷰 입니다.");
					} else {
						alert("리뷰가 스크랩 되었습니다.");
					}
				},
				error : function(error) {

				}
			});
		});
	});
</script>


</head>
<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper">

		<%@ include file="../partials/header.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Page Header <small>Optional description</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">

				<div class="box  ">
					<div class="box-header with-border">
<!-- 						<h3 class="box-title">리뷰상세내용</h3> -->
						<div style="margin-top: 15px">
							<table style="width: 100%" class="table">
								<tr>
									<td style="width: 50%">작성자 &nbsp; | &nbsp; ${comm.memberId }</td>
									<td style="text-align: right">
										추천수 &nbsp; | &nbsp; <span class="likeCount">${comm.likeCnt }</span> &nbsp;&nbsp;&nbsp;
										조회수 &nbsp; | &nbsp; ${comm.hits } &nbsp; &nbsp;&nbsp;&nbsp; 
										등록일 &nbsp; | &nbsp; ${comm.regDate }
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="box-body">
						
						<table class="table table-striped">
							<tr>
								<th class="title" width="100px">제품 정보</th>
								<td>${comm.mainNo }</td>
							</tr>
							<tr>
								<td colspan="2">
									<div class="box box-solid" style="width: 50% auto;">
										<div class="box-header with-border">
											<!-- 											<h3 class="box-title">Carousel</h3> -->
										</div>
										<!-- /.box-header -->
										<div class="box-body">
											<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
												<ol class="carousel-indicators">
													<c:forEach var="saveFile" items="${saveFileList }" varStatus="status">
														<c:if test="${status.index == 0}">
															<li data-target="#carousel-example-generic"
																data-slide-to="${status.index }" class="active"></li>
														</c:if>
														<c:if test="${status.index != 0 }">
															<li data-target="#carousel-example-generic"
																data-slide-to="${status.index }" class=""></li>
														</c:if>
													</c:forEach>
												</ol>
												<div class="carousel-inner">
													<c:forEach var="saveFile" items="${saveFileList }"
														varStatus="status">
														<c:if test="${status.index == 0}">
															<div class="item active">
																<img src="${saveFile.filePath }"
																	alt="${saveFile.originFileName }">
															</div>
														</c:if>
														<c:if test="${status.index != 0 }">
															<div class="item">
																<img src="${saveFile.filePath }"
																	alt="${saveFile.originFileName }">
															</div>
														</c:if>
													</c:forEach>
												</div>
												<a class="left carousel-control" href="#carousel-example-generic" data-slide="prev"> 
													<span class="fa fa-angle-left"></span>
												</a> 
												<a class="right carousel-control" href="#carousel-example-generic" data-slide="next"> 
													<span class="fa fa-angle-right"></span>
												</a>
											</div>
										</div>
										<!-- /.box-body -->
									</div> <!-- /.box -->
								</td>
							</tr>
							<tr>
								<th class="title">평점</th>
								<td>${comm.rating }</td>
							</tr>
							
							<tr>
								<th class="title">좋았던 점</th>
								<td>${comm.content }</td>
							</tr>
							<tr>
								<th class="title">아쉬웠던 점</th>
								<td>${comm.content2 }</td>
							</tr>
							<tr>
								<th class="title">꿀 Tip</th>
								<td>${comm.content3 }</td>
							</tr>
							
							<c:if test="${grade == 0 || grade == 1}">
								<tr>
									<td colspan="2"><input type="button" value="Like"
										onclick="likeCnt()"></td>
								</tr>
							</c:if>
							<tr>
								<td colspan="2" align="right" style="background-color: #f7f7f7;">
									
										<a class="btn btn-app" onclick="likeCnt()">
							                <span class="badge bg-red likeCount">${comm.likeCnt }</span>
							                <i class="fa fa-heart-o"></i> Likes
						                </a>
					                <div class="btn-group">
										<input type="button" class="btn btn-default" value="수정" onclick="reviewUpdate()"> 
										<input type="button" class="btn btn-default" value="삭제" onClick="del_chk()"> 
										<c:if test="${currentPage != null }">
											<input type="button" class="btn btn-default" value="목록" onclick="listView()">
										</c:if>
										<div style="float: right;">
											<input type="hidden" id="mainNo" name="mainNo" value="${comm.mainNo }"> 
											<input type="hidden" id="subNo" name="subNo" value="${comm.subNo }">
											<input type="button" class="btn btn-default" id="InsertClip" value="스크랩하기">
										</div>
									</div>
								</td>
							</tr>
						</table>
						<hr>
						<h3>댓글</h3>
						<reviewComment></reviewComment>
					</div>
				</div>
			</section>
		</div>
	</div>


	<footer><%@ include file="../partials/footer.jsp"%></footer>
</body>
</html>