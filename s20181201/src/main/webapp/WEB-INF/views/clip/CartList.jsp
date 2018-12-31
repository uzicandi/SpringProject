<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/head.jsp"%>
<title>상품 장바구니 목록</title>


<!-- <link
	href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css"> -->
<!-- <script
	src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> -->
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<style>
.table>tbody>tr>td, .table>tfoot>tr>td {
	vertical-align: middle;
}

@media screen and (max-width: 600px) {
	table#cart tbody td .form-control {
		width: 20%;
		display: inline !important;
	}
	.actions .btn {
		width: 36%;
		margin: 1.5em 0;
	}
	.actions .bg-maroon {
		float: left;
	}
	.actions .bg-maroon {
		float: right;
	}
	table#cart thead {
		display: none;
	}
	table#cart tbody td {
		display: block;
		padding: .6rem;
		min-width: 320px;
	}
	table#cart tbody tr td:first-child {
		background: #333;
		color: #fff;
	}
	table#cart tbody td:before {
		content: attr(data-th);
		font-weight: bold;
		display: inline-block;
		width: 8rem;
	}
	table#cart tfoot td {
		display: block;
	}
	table#cart tfoot td .btn {
		display: block;
	}
}
</style>
</head>

<body class="hold-transition skin-red-light sidebar-mini">

	<%@ include file="../partials/header.jsp"%>
	<div class="content-wrapper">
		<section class="content-header">
			<h1>
				<small></small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i>Cart </a></li>
				<li class="active">CartList</li>
			</ol>
		</section>


		<!-- Top content -->

		<div class="row">
			<div class="col-sm-8 col-sm-offset-2 text">

				<h1>
					<i class="fa fa-shopping-cart"></i><strong>장바구니</strong>
				</h1>

			</div>
		</div>
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2 form-box">
				<div class="form-top">
					<div class="form-top-left">
						<h3></h3>
						<!-- 	<p></p> -->
					</div>
					<!-- <div class="form-top-right">
							<i class="fa fa-lock"></i>
						</div> -->
				</div>

				<c:choose>
					<c:when test="${map.count == 0 }">
						<h3>장바구니가 비어있습니다.</h3>
					</c:when>
					<c:otherwise>
						<link
							href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
							rel="stylesheet">
						<form name="form1" id="form1" method="post" action="CartUpdate.do">
							<div class="container">
								<table id="cart" class="table table-hover table-condensed">
									<thead>
										<tr>
											<th style="width: 50%">상품</th>
											<th style="width: 10%">상품금액</th>
											<th style="width: 8%">수량</th>
											<th style="width: 22%" class="text-center">주문금액</th>
											<th style="width: 10%"></th>
										</tr>
									</thead>
									<tbody>
										
										<c:forEach var="row" items="${map.list }" varStatus="i">
											<tr>
												<td data-th="상품명">
													<div class="row">
														<div class="col-sm-2 hidden-xs">
															<img src="${row.filePath }" class="img-responsive" />
														</div>
														<div class="col-sm-10">
															<!-- itemName -->
															<h4 class="nomargin">${row.title}</h4>
														</div>
													</div>
												</td>
												<td data-th="가격"><fmt:formatNumber
														pattern="###,###,###" value="${row.price }" /></td>
												<td data-th="수량"><input type="number"
													class="form-control text-center" name="subNo" value="${row.subNo}" min="1">
													<input type="hidden" name="mainNo" value="${row.mainNo}"> 
												</td>
												
												<td data-th="주문금액" class="text-center"><fmt:formatNumber
														pattern="###,###,###" value="${row.price*row.subNo}" /></td>
														<!-- 수정 -->
												<td class="actions" data-th="">
												<input type="hidden" name="count" value="${map.count }">
													<button class="btn bg-maroon btn-sm" type="submit"
														id="btnUpdate">
														<i class="fa fa-refresh"></i>
													</button> 
														<!-- 삭제 -->
														<a href="CartDelete.do?mainNo=${row.mainNo }" class="btn bg-maroon btn-sm" role="button">
															<i class="fa fa-trash-o"> </i></a>
														
														
												</td>
											</tr>
										</c:forEach>
									
									</tbody>
									
									<tfoot>
										<tr class="visible-xs">
											<td class="text-center"><strong>총 주문금액 : <fmt:formatNumber
														pattern="###,###,###" value="${map.sumMoney }" /></strong></td>
										</tr>
										<tr>
											<td><a href="KISItemList.do" class="btn btn-warning"><i
													class="fa fa-angle-left"></i> Continue Shopping</a></td>
											<td colspan="2" class="hidden-xs"></td>
											<td class="hidden-xs text-center"><strong>총
													주문금액 : <fmt:formatNumber pattern="###,###,###"
														value="${map.sumMoney }" />
											</strong></td>
											<td><a href="OrderSheet.do" class="btn btn-success btn-block">Checkout
													<i class="fa fa-angle-right"></i>
											</a></td>
										</tr>
									</tfoot>
								</table>
							</div>
							</form>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>


	<!-- /.content-wrapper -->

	<%@ include file="../partials/footer.jsp"%>
	<div class="control-sidebar-bg"></div>


</body>
</html>