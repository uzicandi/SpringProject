<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../partials/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Item List</title>
<script type="text/javascript">
	// 검색어 입력안하고 검색버튼 눌렀을 때
	function searchCheck(frm) {
		if (frm.keyword.value == "") {
			alert("검색 단어를 입력하세요.");
			frm.keyword.focus();
			return;
		}
		frm.submit();
	}

	// 검색어 분류 셀렉트박스 선택시
	function searchKind() {
		console.log("선택한 검색어 값 --> " + $("#search").val());
		var optionVal = $("#search").val();
	};

	//입고시 알람 뜨게 하기 나중에
	function alarm() {
		alert("이 부분은 나중에!!")
	}

	function addCart(itemNo, name, price) {

		console.log('itemNo -> ' + itemNo);
		console.log('name -> ' + name);
		console.log('price -> ' + price);
		$(".modal-body #itemNo").val(itemNo);
		$(".modal-body #name").val(name);
		$(".modal-body #price").val(price);

		$(".modal-body #amount").val(
				$("#amount" + itemNo + " option:selected").val());

	}

	$(function() {
		$('#goCart').click(
				function() {

					var itemNo = $('#itemNo').val();
					var name = $('#name').val();
					var price = $('#price').val();
					var amount = $('#amount').val();
					var sendData = "itemNo=" + itemNo + "&name=" + name
							+ "&price=" + price + "&amount=" + amount;

					console.log('sendData -> ' + sendData);

					location.href = "CartInsert.do?" + sendData;
				});
	});

	$(function() {
		$('#goShopping').click(
				function() {

					var itemNo = $('#itemNo').val();
					var name = $('#name').val();
					var price = $('#price').val();
					var amount = $('#amount').val();
					var sendData = "itemNo=" + itemNo + "&name=" + name
							+ "&price=" + price + "&amount=" + amount
							+ "&test=1";

					console.log('sendData -> ' + sendData);

					$.get('CartInsert.do', sendData, function(msg) {

						console.log('msg1 -> ' + msg);

						if (msg != null) {
							// 부모창 리프레시 시켜주기
							window.location.reload();
						}
						;
					});
				});
	});

	function itemSorting(num, name) {
		console.log('cateNum -> ' + num);
		console.log('cateName -> ' + name);

	};
	
	$(function() { 
	    $('#cate_group > ul').show(); 
	    $('#cate_group > ul > li') 
		     .on('mouseover', function() { 
		      $(this).find('div').stop(true, true).slideDown(); 
		     }) 
		     .on('mouseout', function() { 
		      $(this).find('div').stop(true, true).slideUp(); 
	     }); 
	}); 
</script>
<style type="text/css">
	b {
		overflow: hidden;
 		text-overflow: ellipsis;
 		display: -webkit-box;
		-webkit-line-clamp: 2; /* 라인수 */
 		-webkit-box-orient: vertical;
 		word-wrap:break-word; 
 		line-height: 1.2em;
 		height: 3.6em;	
	}
</style>
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
					제품리스트 <small>제품목록에 대한 설명 적기</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="KISIngtList.do"><i class="fa fa-dashboard"></i>
							제품</a></li>
					<li class="active">제품리스트</li>
					<li class="active">${cateP.name }</li>
				</ol>
			</section>
			
			<!-- Main content -->
			<section class="content container-fluid">
				<div class="box">
					<!-- <div class="box-header with-border">
						<h3 class="box-title">Horizontal Form</h3>
					</div> -->
					
					<div class="box-body">
						<button value="제품 등록" class="btn btn-block bg-maroon" onclick="location.href='KISItemWriteForm.do'" style="width: auto;">제품등록</button>
						
							
						<hr>
						

						<div class="container">
	
						 	<div class="col-sm-12">
							<c:set var="num" value="${pg.total-pg.start+1}"></c:set>

							<div class="container">
							<div class="col-sm-12 btn-group">
								<a href="KISItemList.do?cateSel=0" class="btn bg-maroon">전체</a>&nbsp&nbsp&nbsp
								<c:forEach var="catelist" items="${catelist}">
									<c:if test="${catelist.division == 1 and cateP.parent == catelist.parent}">
											<a href="KISItemList.do?cateSel=${catelist.categoryNo}">
												<c:if test="${cateSel == catelist.categoryNo }">
													<input type="button" class="btn bg-maroon" style="width: auto;" value="${catelist.name}">
												</c:if>
												<c:if test="${cateSel != catelist.categoryNo }">
													<input type="button" class="btn bg-maroon" style="width: auto;" value="${catelist.name}">
												</c:if>
											</a> &nbsp&nbsp&nbsp
									</c:if>
								</c:forEach>
							<br><br><br>	
						</div>
 									
									<br><br>
									<c:if test="${cateSel == 0}"> 
									<div class="col-sm-12">
									<h1 style="text-align: center;"><b>Best 3</b></h1>
									<c:forEach var="best" items="${bestlist}" begin="1" end="3" step="1">
										<div class="col-sm-4" style="text-align: center;">
								      		<div class="panel panel-danger">
								        	<div class="panel-body">
								        		<a href="KISItemContent.do?itemNo=${best.itemNo}">
												<img src="${best.saveFileList[0].filePath}" width="80%" height="250"><br>
								        		<br>
								        		<b>${best.name}</b></a>
								        		<br>
								        		<fmt:formatNumber pattern="###,###,###" value="${best.price}" />원
											</div>
											</div>
										</div>
										</c:forEach>
							   	</div>
									 </c:if>
									<div class="col-sm-12">									
									<span>Total ${itemTotal}개</span>
									<br><br>
									</div>
									
									<c:if test="${itemTotal > 0 }">
									
									<c:forEach var="item" items="${list}" varStatus="status">
										
								    	<div class="col-sm-4" style="text-align: center;">
								      		<div class="panel panel-danger">
								        	<div class="panel-body">
								        		<a href="KISItemContent.do?itemNo=${item.itemNo}">
								        		<img src="${item.saveFileList[0].filePath}" width="80%" height="250"><br>
								        		<br>
								        		<b>${item.name}</b></a>
								        		<br>
								        		<fmt:formatNumber pattern="###,###,###" value="${item.price}" />원
								        	</div>
								        	<div class="panel-footer">
								        		<c:choose>
								        			<c:when test="${item.itemStock > 0}">
								        				수량 : 
														<select name="amount" id="amount${item.itemNo}">
															<c:forEach begin="1" end="10" var="i">
																<option value="${i}">${i}</option>
															</c:forEach>	
														</select>&nbsp; 
								        				<button type="button" class="btn btn-default" data-toggle="modal" data-target="#modal-default" 
								        						onClick="addCart('${item.itemNo}','${item.name}','${item.price}')">
															<i class="fa fa-fw fa-cart-plus" ></i>
 														</button>				
								        			</c:when>
								        			
								        			<c:when test="${item.itemStock == 0}">
								        				<input type="button" class="stockAlarm" value="입고알림" onclick="alarm()" style="height: 28px;">
								        			</c:when>
								        		</c:choose>
								        	</div>
								      		</div>
								    	</div>
								    <c:set var="num" value="${num - 1}" />	    	
									</c:forEach>
									
							</c:if>
								</div>
						
							<c:if test="${itemTotal == 0}">
								데이터가 없네
							</c:if>
						</div>
							
												
						<div style="text-align: center;">
							<c:choose>
								<c:when test="${item.keyword == null}">
									<c:if test="${pg.startPage > pg.pageBlock}">
										<a href="KISItemList.do?currentPage=${pg.startPage - pg.pageBlock}&cateSel=${cateSel }">[이전]</a>
									</c:if>
									<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
										 <a href="KISItemList.do?currentPage=${i}&cateSel=${cateSel }">[${i}]</a>
									</c:forEach>
									<c:if test="${pg.endPage < pg.totalPage}">
										<a href="KISItemList.do?currentPage=${pg.startPage + pg.pageBlock}&cateSel=${cateSel }">[다음]</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:if test="${pg.startPage > pg.pageBlock}">
										<a href="KISItemList.do?currentPage=${pg.startPage - pg.pageBlock}&cateSel=${cateSel }">[이전]</a>
									</c:if>
									<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
										<a href="KISItemList.do?currentPage=${i}&cateSel=${cateSel }">[${i}]</a>
									</c:forEach>
									<c:if test="${pg.endPage < pg.totalPage}">
										<a href="KISItemList.do?currentPage=${pg.startPage + pg.pageBlock}&cateSel=${cateSel }">[다음]</a>
									</c:if>
								
								</c:otherwise>
							</c:choose>
						</div>
						<br><br>
						<!--  권한체크 넣기 -->
							<form name="search" action="KISItemList.do" method="post" style="text-align: center;">
							<input type="hidden" name="cateSel" value="${cateSel }">
							<select id="search" name="search" onchange="searchKind()" style="height: 32px;">
								<option value="제품명">제품명</option>
								<option value="브랜드">브랜드</option>
								<option value="해시태그">해시태그</option>
							</select> 
							<input type="search" name="keyword" style="width: 250px; height: 32px;"> 
							<input class="btn bg-maroon" type="button" value="검색" onclick="searchCheck(form)">
						</form>
						
					</div>
				</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		
		
		<div class="modal fade" id="modal-default">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body" style="text-align: center;">
					<p>장바구니에 추가되었습니다.</p>
					<input type="hidden" id="itemNo"> 
					<input type="hidden" id="name">
					<input type="hidden" id="price">
					<input type="hidden" id="amount">
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default pull-left" id="goShopping" value="계속 쇼핑하기">
					<input type="button" class="btn btn-primary" id="goCart" value="장바구니로 가기">
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
		</div>

		<!-- Main Footer -->
		<%@ include file="../partials/footer.jsp"%>


	</div>
	<!-- ./wrapper -->

</body>
</html>