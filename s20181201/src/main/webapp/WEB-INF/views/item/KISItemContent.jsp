<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../partials/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Item View</title>
<style type="text/css">
th {
	text-align: center;
}

/* Inline Bar Graph */
.iGraph {
	white-space: nowrap;
	line-height: normal;
	margin-bottom: 15px;
}

.iGraph .gBar {
	display: inline-block;
	width: 250px;
	height: 14px;
	margin: 0 5px 0 0;
	border: 1px solid #ccc;
	background: #e9e9e9;
	font-size: 11px;
}

.iGraph .gAction {
	display: inline-block;
	height: 14px;
	border: 1px solid #8c9bac;
	background: #99a6b6;
	margin: -1px;
}

.iGraph .gPercent {
	font: 16px Arial, Helvetica, sans-serif;
	color: #ccc;
}

.iGraph .gPercent strong {
	font-size: 18px;
	color: #e88b30;
}

div>h1 {
	padding: 0;
	margin: 0;
}

.firstWrap {
	padding-bottom: 15px;
}

.htag {
	clear: both;
	margin-top: 3%;
	padding: 10px 10px 10px 0;
	margin-bottom: 25px;
}

.row {
	margin-bottom: 20px;
}
.modal-title{
		float : left;
}
</style>
<script type="text/javascript">
	$(function() {
		if (oily == 0) {
			var oily = 0;
		} else {
			var oily = $(".oily").length;
		}
		if (dry == 0) {
			var dry = 0;
		} else {
			var dry = $(".dry").length;
		}
		if (sensitivity == 0) {
			var sensitivity = 0;
		} else {
			var sensitivity = $(".sensitivity").length;
		}
		
		var allSize = oily + dry + sensitivity;
		console.log("oily --> " + oily);
		console.log("dry --> " + dry);
		console.log("sensitivity --> " + sensitivity);
		var percent1 = (oily / allSize) * 100;
		var percent2 = (dry / allSize) * 100;
		var percent3 = (sensitivity / allSize) * 100;
		console.log("percent1 --> " + percent1);
		console.log("percent2 --> " + percent2);
		console.log("percent3 --> " + percent3);

		// 20가지 주의성분
		var danger = $(".danger").length;
		console.log("danger --> " + danger);
		$(".gPercent>strong:eq(0)").text(danger + "개");

		// 알레르기 주의성분
		var allergy = $(".allergy").length;
		console.log("allergy --> " + allergy);
		$(".gPercent>strong:eq(1)").text(allergy + "개");

		// 기능성 성분
		var functional1 = $(".functional1").length;
		var functional2 = $(".functional2").length;
		var functional3 = $(".functional3").length;
		$(".gPercent>strong:eq(5)").text(functional1 + "개");
		$(".gPercent>strong:eq(6)").text(functional2 + "개");
		$(".gPercent>strong:eq(7)").text(functional3 + "개");
		
		if (percent1 == 0 || "NaN") {
			$(".gAction:eq(0)").css("width", 0 + "%");
			$(".gPercent>strong:eq(2)").text(0);
		} else {
			$(".gAction:eq(0)").css("width", percent1 + "%");
			$(".gPercent>strong:eq(2)").text(percent1);
		}		
		if (percent2 == 0 || "NaN") {
			$(".gAction:eq(1)").css("width", 0 + "%");
			$(".gPercent>strong:eq(3)").text(0);
		} else {
			$(".gAction:eq(1)").css("width", percent1 + "%");
			$(".gPercent>strong:eq(3)").text(percent2);
		}
		if (percent3 == 0 || "NaN") {
			$(".gAction:eq(2)").css("width", 0 + "%");
			$(".gPercent>strong:eq(4)").text(0);
		} else {
			$(".gAction:eq(2)").css("width", percent1 + "%");
			$(".gPercent>strong:eq(4)").text(percent3);
		}

		$("#hiddenRow").css("display", "none");
		
		var strHtag = "${item.htag}";
		var str = strHtag.split("#");
		console.log(str.length);
		var tag = "";
		for (var i=1; i<str.length; i++) {
				tag += "<button id='j' class='btn bg-maroon btn-sm' style='margin-right:3px'>#"+str[i]+"</button>";
				console.log("#" + str[i]);
		}
				$(".htag").html(tag);		
	})

	// 제품에 함유된 성분 리스트 팝업으로 띄움
	function openPop() {
		$.ajax({
			url : "KISPopIngtListForItemView.do",
			type : "POST",
			dataType : "text",
			data : {
				itemNo : "${item.itemNo}"
			},
			success : function(data) {
				console.log("data-->" + data);
				$('#poplist').html(data);
			},
			error : function() {
				alert("통신실패!!");
			}
		});
	}

	// 제품 비교함 담기
	function foldingItem(itemNo) {
		$.ajax({
			url : "FoldingItem.do",
			type : "POST",
			dataType : "text",
			data : {
				itemNo : itemNo
			},
			success : function(data) {
				console.log("data-->" + data);
				alert(data);
				history.go(0);
			},
			error : function() {
				alert("foldingItem 통신실패!!");
			}
		});
	}

	function likeCnt() {
		if ('${sessionId}' == null) {
			alert('로그인이 필요합니다.');
		} else {
			$.ajax({
				url : 'ItemLikeUpdate.do',
				async : true,
				type : "POST",
				dataType : "json",
				data : {
					itemNo : "${item.itemNo}",
					memberId : "${sessionId}"
				},
				beforeSend : function() {
					console.log("likeCnt 전...");
				},
				complete : function() {
					console.log("likeCnt 완료...!");
				},
				success : function(data) {
					//	 			var data = JSON.parse(data);
					console.log("likeCnt()를 정상적으로 성공!!");
					console.log("data -> " + data.likeCnt);
					console.log("msg -> " + data.msg);
					alert(data.msg);
					$(".likeChk").html(data.likeCnt);
				}
			});
		}
	};
	
	
    function formSubmit()
    {
    document.getElementById("form1").submit();
    }
	

    $(function () {
   		// 모달창 닫을때 부모페이지 새로고침
    	//		$('#myModal').on('hidden.bs.modal', function (e) {
    	//			window.opener.location.reload();
    	//		})
    		$('#myModal').on('hide.bs.modal', function (e) {
    			
    	//			window.opener.location.reload();
    			history.go(0);
    		});	
    	});
    
    function openReviewModal(mainNo, subNo) {
    	console.log('mainNo -> ' + mainNo);
    	console.log('subNo -> ' + subNo);
    	
    	$.ajax({
			url : 'JJYItemReviewContent.do',
			async : true,
			type : "POST",
			dataType : "text",
			data : {
				itemNo : mainNo,
				reviewNo : subNo
			},
			beforeSend : function() {
				console.log("Review Content 전...");
			},
			complete : function() {
				console.log("Review Content 완료...!");
			},
			success : function(data) {
// 	 			var data = JSON.parse(data);
				console.log("Review Content()를 정상적으로 성공!!");
				console.log("data -> " + data);
// 				alert(data.msg);
				$("#reviewContent .modal-content").html(data);
				$('#reviewContent').modal('show');
			}
		});
    }
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
					제품 상세보기<small>Item Page</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>Item</a></li>
					<li class="active">ItemPage</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid" style="width:80%;">

				<div class="row">
					<div class="col-sm-6">
						<div id="carousel-example-generic" class="carousel slide"
							data-ride="carousel">
							<ol class="carousel-indicators">
								<c:forEach var="saveFile" items="${item.saveFileList}"
									varStatus="status">
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
							<div class="carousel-inner" align="center">
								<c:forEach var="saveFile" items="${item.saveFileList}"
									varStatus="status">
									<c:if test="${status.index == 0}">
										<div class="item active" >
											<img src="${saveFile.filePath }"
												alt="${saveFile.originFileName }" style="line-height: 1; max-width: 500px; max-height: 400px;">
										</div>
									</c:if>
									<c:if test="${status.index != 0 }">
										<div class="item">
											<img src="${saveFile.filePath }"
												alt="${saveFile.originFileName }" style="line-height: 1; max-width: 500px; max-height: 400px;">
										</div>
									</c:if>
								</c:forEach>
							</div>
							<a class="left carousel-control" href="#carousel-example-generic"
								data-slide="prev" style="color: #d81b60;"> <span class="fa fa-angle-left"></span>
							</a> <a class="right carousel-control"
								href="#carousel-example-generic" data-slide="next" style="color: #d81b60;"> <span
								class="fa fa-angle-right"></span>
							</a>
						</div>
					</div>

					<div class="col-sm-6">
						<h1>${item.name}</h1>
						<br>
						<h4>[ ${item.brand} ]</h4>
						<br>
						<%-- 				<strong>제품번호</strong> ${item.itemNo}<br> --%>
						<strong>카테고리 : </strong>
						<c:forEach var="cate" items="${catelist}">
							<c:if test="${item.categoryNo == cate.categoryNo}">${cate.name}</c:if>
						</c:forEach>
						<br> <strong>재고 : </strong> ${item.itemStock}<br> <strong>제품정보 : </strong>
						${item.info}<br> <strong>가격 : </strong> ${item.price}<br>
						<strong>평점 : </strong> 		
				<c:choose>
					<c:when
						test="${item.rating > 4.5 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 4 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-half-full" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 3.5 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 3 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-half-full" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 2.5 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 2 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-half-full" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 1.5 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 1 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-half-full" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 0.5 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating > 0 }">
						<i class="fa fa-star" aria-hidden="true"></i>
						<i class="fa fa-star-half-full" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
					<c:when
						test="${item.rating == 0 }">
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
						<i class="fa fa-star-o" aria-hidden="true"></i>
					</c:when>
				</c:choose>	
				<br><div class="htag"></div>
						<h4>상품금액 합계</h4>
						<hr>
						<a class="btn btn-app" onclick="likeCnt()"> <span
							class="badge bg-red likeChk">${item.likeCnt}</span> <i
							class="fa fa-heart-o"></i> likes
						</a> 
						<a class="btn btn-app" onclick="foldingItem(${item.itemNo})">
							<i class="fa fa-inbox"></i> 비교함 담기
						</a>
						<%-- <button type="button" class="btn btn-default"
							onclick="foldingItem(${item.itemNo})">비교함 담기</button> --%>
						<!-- 					<button type="button" class="btn btn-default">구매하기</button> -->
						<!-- 장바구니 이동 부분 -->
						<div style="float: left; padding-right: 3px;">
							<form name="form1" method="post" action="CartInsert.do" id="form1">
								<input type="hidden" name="itemNo" value="${item.itemNo }">
								<input type="hidden" name="name" value="${item.name }">
								<input type="hidden" name="price" value="${item.price }">
								<!-- 							<select name="amount" style="height:31px;"> -->
								<%-- 								<c:forEach begin="1" end="10" var="i"> --%>
								<%-- 									<option value="${i}">${i}</option> --%>
								<%-- 								</c:forEach> --%>
								<!-- 							</select>-->
								<input type="number" name="amount" value="1" min="1" max="10"
									style="width: 50px; height: 35px;">&nbsp;개 
								<!-- <input type="submit" class="btn btn-app">  -->
								<a class="btn btn-app" onclick="formSubmit(); return false;">
									<i class="fa fa-barcode"></i>장바구니 담기
								</a>
								<!-- 로그인 안했으면 로그인페이지// 로그인했으면 submit -->
							</form>
							<!-- 	<input type="button" value="장바구니로" onclick="location.href='CartList.do'"> -->
						</div>

					</div>
				</div>
				
				<!-- Modal Start -->
				<div class="modal fade" id="myModal" role="dialog">
					<div class="modal-dialog">

						<!-- Modal content-->
						<div id="modal-content" class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h4 class="modal-title">성분 리스트</h4>
							</div>
							<div id="poplist" class="modal-body">
								<p>Some text in the modal.</p>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">닫기</button>
							</div>
						</div>

					</div>
				</div>
				<!-- Modal End -->
																
				<div class="box">
					<div class="box-header with-border">
						<div style=" float:left">
							<h4>성분 구성</h4>
						</div>
						<div style="float:right">
							<button class="btn btn-default" data-toggle="modal"
									data-target="#myModal" onClick="openPop(); return false;">모든 성분 한눈에 보기</button>
						</div>
					</div>
			
					<div class="box-body">
						<table class="table table-striped" align="center">
							<tr>
								<td style="text-align:center; ">
									<img src="./resources/images/danger.jpg" width="30px"
									height="30px"><br> <span class="iGraph"> 20가지 주의성분<span
										class="gPercent"><strong></span>
								</td>
								<td style="text-align:center; ">
									<img src="./resources/images/allergy.jpg" width="30px"
									height="30px"><br> <span class="iGraph"> 알레르기 주의성분<span
										class="gPercent"><strong></span>
								</td>
							</tr>
							<tr>
								<td>
									<strong>피부타입별 성분</strong><br>
											<span class="iGraph">지성 피부&nbsp;&nbsp;&nbsp; <span
												class="gBar"><span class="gAction"></span></span> <span
												class="gPercent"><strong></strong>%</span>
										</span>
										<br> <span class="iGraph">건성 피부&nbsp;&nbsp;&nbsp;
											<span class="gBar"><span class="gAction"></span></span> <span
											class="gPercent"><strong></strong>%</span>
									</span> <br> <span class="iGraph">민감성피부 <span
											class="gBar"><span class="gAction"></span></span> <span
											class="gPercent"><strong></strong>%</span>
									</span> 
								</td>
								<td>
									<strong>기능성 성분</strong><br> 
									<!-- 해당 성분없으면 흑백 아이콘 띄워지게 수정할 것  -->
									<img src="./resources/images/uvGuard.png" width="70px"
									height="70px"> <span class="gPercent"><strong></strong></span>
									<img src="./resources/images/whitening.png" width="70px"
									height="70px"> <span class="gPercent"><strong></strong></span>
									<img src="./resources/images/wrinkle.png" width="70px"
									height="70px"> <span class="gPercent"><strong></strong></span>
								</td>
							</tr>
							
							<tr>
							<div id="hiddenRow">
								<strong>성분정보</strong>
								<c:forEach var="mapp" items="${mapplist}">
									<!-- 				피부타입별 성분 갯수 -->
									<c:if test="${mapp.specialyType eq '지성'}">
										<span class="oily">${mapp.specialyType}</span>
									</c:if>
									<c:if test="${mapp.specialyType eq '건성'}">
										<span class="dry">${mapp.specialyType}</span>
									</c:if>
									<c:if test="${mapp.specialyType eq '민감성'}">
										<span class="sensitivity">${mapp.specialyType}</span>
									</c:if>
									<!-- 				20가지 주의성분 갯수 -->
									<c:if test="${mapp.danger20 != null}">
										<span class="danger">${mapp.danger20}</span>
									</c:if>
									<!-- 				알레르기유발성분 갯수 -->
									<c:if test="${mapp.dangerAllergy != null}">
										<span class="allergy">${mapp.dangerAllergy}</span>
									</c:if>
									<!-- 				기능성 성분 표시 -->
									<c:if test="${mapp.functional eq '자외선차단'}">
										<span class="functional1">${mapp.functional}</span>
									</c:if>
									<c:if test="${mapp.functional eq '주름개선'}">
										<span class="functional2">${mapp.functional}</span>
									</c:if>
									<c:if test="${mapp.functional eq '미백효과'}">
										<span class="functional3">${mapp.functional}</span>
									</c:if>
									<!-- 				위험도 등급표시 -->
									<c:if test="${mapp.grade == 1}">
										<img class="grade1" src="./resources/images/grade1.png"
											width="30px" height="35px">
									</c:if>
									<c:if test="${mapp.grade == 2}">
										<img class="grade2" src="./resources/images/grade2.png"
											width="30px" height="35px">
									</c:if>
									<c:if test="${mapp.grade == 3}">
										<img class="grade3" src="./resources/images/grade3.png"
											width="30px" height="35px">
									</c:if>
									<c:if test="${mapp.grade == 4}">
										<img class="grade4" src="./resources/images/grade4.png"
											width="30px" height="35px">
									</c:if>
									<c:if test="${mapp.grade == 5}">
										<img class="grade5" src="./resources/images/grade5.png"
											width="30px" height="35px">
									</c:if>
									<c:if test="${mapp.grade == 6}">
										<img class="grade6" src="./resources/images/grade6.png"
											width="30px" height="35px">
									</c:if>
									<c:if test="${mapp.grade == 7}">
										<img class="grade7" src="./resources/images/grade7.png"
											width="30px" height="35px">
									</c:if>
									<c:if test="${mapp.grade == 8}">
										<img class="grade8" src="./resources/images/grade8.png"
											width="30px" height="35px">
									</c:if>
									<c:if test="${mapp.grade == 9}">
										<img class="grade9" src="./resources/images/grade9.png"
											width="30px" height="35px">
									</c:if>
						${mapp.name}${mapp.grade} /									
				</c:forEach>
							</div>
							</tr>
							
							<tr align="right">
								<td colspan="2">
									<!-- 권한체크 넣기 --> <input class="btn btn-default" type="button" value="수정"
									onclick="location.href='KISItemUpdateForm.do?itemNo=${item.itemNo}'">
									<input class="btn btn-default" type="button" value="삭제"
									onClick="location.href='KISItemDelete.do?itemNo=${item.itemNo}'">
									<input class="btn btn-default" type="button" value="목록가기"
									onclick="location.replace('KISItemList.do')">
								</td>
							</tr>



							
						</table>
						
					</div>
					
						
					
				</div>

				<div class="box">
					<div class="box-header with-border">
						<button type="button" class="btn btn-default" onclick="location.replace('JJYItemReviewWriteForm.do?itemNo=${item.itemNo}&currentPage=${currentPage }&currentPageRv=${pgRv.currentPage}')">리뷰작성</button>
					</div>
					<div class="box-body">
						<c:if test="${reviewTotal > 0 }">
							<c:forEach var="comm" items="${comms}">
								<div class="row" style="text-align: left;" >
									<div class="col-sm-3 text-center">
										<c:choose>
											<c:when test="${comm.memberFilePath == '0'}">
												<img src="/uploadImg/member/noimage.jpg" class="img-circle"
													alt="User-Image" style="width: 80px;">
											</c:when>
											<c:when test="${comm.memberFilePath != '0'}">
												<img src="${comm.memberFilePath }" class="img-circle"
													alt="${comm.memberId}" style="width: 80px;">
											</c:when>
										</c:choose>
										<br>
										${comm.memberId }<br>
										${comm.regDate}<br>
										${comm.commMember.age }세 / 
										<c:choose>
											<c:when test="${comm.commMember.sex == 0 }">남성</c:when>
											<c:otherwise>여성</c:otherwise>
										</c:choose> / 
										<c:choose>
					                  		<c:when test="${comm.commMember.skinType == 0 }">건성</c:when>
					                  		<c:when test="${comm.commMember.skinType == 1 }">중성</c:when>
					                  		<c:when test="${comm.commMember.skinType == 2 }">지성</c:when>
					                  		<c:otherwise>복합성</c:otherwise>
					                  	</c:choose>
									</div>
									<div class="col-sm-9">

										<div class="col-sm-4">
											<c:if test="${comm.saveFileList != null }">
												<a
													href="JJYItemReviewContent.do?itemNo=${comm.mainNo}&reviewNo=${comm.subNo }&currentPage=${currentPage }&currentPageRv=${pgRv.currentPage}">
													<img alt="" src="${comm.saveFileList[0].filePath }"
													style="width: 150px;">
												</a>
											</c:if>
										</div>
										<div class="col-sm-8">
											<div class="col-sm-12">
<%-- 												<a href="JJYItemReviewContent.do?itemNo=${comm.mainNo}&reviewNo=${comm.subNo }&currentPage=${currentPage }&currentPageRv=${pgRv.currentPage}"> --%>
<%-- 													<h4>${comm.content}</h4> --%>
<!-- 												</a> -->
												<a href="javascript:openReviewModal(${comm.mainNo }, ${comm.subNo })">
													<h4>${comm.content}</h4>
												</a>
											</div>
											<div class="col-sm-12" style="font-size: 18px;">
												<i class="fa  fa-trophy"></i>&nbsp&nbsp<span style="font-size: 15px">
														<c:choose>
															<c:when
																test="${comm.rating > 4.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 4 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 3.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 3 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 2.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 2 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 1.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 1 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 0.5 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating > 0 }">
																<i class="fa fa-star" aria-hidden="true"></i>
																<i class="fa fa-star-half-full" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
															<c:when
																test="${comm.rating == 0 }">
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
																<i class="fa fa-star-o" aria-hidden="true"></i>
															</c:when>
														</c:choose>
													</span>&nbsp&nbsp&nbsp&nbsp<br>
												<i class="fa  fa-heart-o"></i>&nbsp&nbsp<span> ${comm.likeCnt}</span>&nbsp&nbsp&nbsp&nbsp
												<i class="fa  fa-comment-o"></i>&nbsp&nbsp<span> ${comm.replyCnt}</span>
												
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
						<c:if test="${reviewTotal == 0}">
							데이터가 없네
						</c:if>
					</div>
					<div style="text-align: center;">
						<ul class="pagination pagination-sm">
							<c:if test="${pgRv.startPage > pgRv.pageBlock}">
								<li><a href="KISItemContent.do?itemNo=${item.itemNo}&currentPage=${currentPage}&currentPageRv=${pgRv.startPage - pgRv.pageBlock}">«</a></li>
							</c:if>
			                <c:forEach var="i" begin="${pgRv.startPage}" end="${pgRv.endPage}">
								<li><a href="KISItemContent.do?itemNo=${item.itemNo}&currentPage=${currentPage}&currentPageRv=${i}">${i}</a></li>	
							</c:forEach>
							<c:if test="${pgRv.endPage < pgRv.totalPage}">
								<li><a href="KISItemContent.do?itemNo=${item.itemNo}&currentPage=${currentPage}&currentPageRv=${pgRv.startPage + pgRv.pageBlock}">»</a></li>
							</c:if>
						</ul>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	
	
	
	<!-- Review Content Modal -->
	<div class="modal fade" id="reviewContent" tabindex="-1" role="dialog" aria-labelledby="reviewContentLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    </div>
	  </div>
	</div>
	


	<%@ include file="../partials/footer.jsp"%>

	<div class="control-sidebar-bg"></div>

	<!-- ./wrapper -->
</body>
</html>