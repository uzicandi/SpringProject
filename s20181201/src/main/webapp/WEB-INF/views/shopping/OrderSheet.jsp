<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="../partials/head.jsp"%>
<title>AdminLTE 2 | Starter</title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet"
	href="bower_components/bootstrap/dist/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="bower_components/font-awesome/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="bower_components/Ionicons/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="dist/css/AdminLTE.min.css">

<link rel="stylesheet" href="dist/css/skins/skin-red-light.min.css">

<script type="text/javascript">
	$(function() {
		var keyword = "${keyword}";
		console.log(keyword);
		$("#keyword").val(keyword);

	})
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	function chk() {
		if ($('#req').is(':checked')) {
			if(${curPoint >= sumMoney}){
				if(document.getElementById("receiver").value == ""){			
					alert("받는분을 입력하세요.");	
				}
				else if(document.getElementById("addr2").value == ""){			
					alert("주소를 입력하세요.");	
				}
				else{
					alert("결제가 완료되었습니다.");
					$('#frm').submit();
				}			
			}else{
				alert("보유 포인트가 부족합니다. 충전 후 다시 이용 하십시요.");
			}
		} else {
			alert("구매진행 동의에 체크를 하셔야 결제가 진행됩니다.");
			return false;
		}
	}

	function execPostCode() {
		new daum.Postcode({
			oncomplete : function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

				// 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
				// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
				var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
				var extraRoadAddr = ''; // 도로명 조합형 주소 변수

				// 법정동명이 있을 경우 추가한다. (법정리는 제외)
				// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
				if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
					extraRoadAddr += data.bname;
				}
				// 건물명이 있고, 공동주택일 경우 추가한다.
				if (data.buildingName !== '' && data.apartment === 'Y') {
					extraRoadAddr += (extraRoadAddr !== '' ? ', '
							+ data.buildingName : data.buildingName);
				}
				// 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
				if (extraRoadAddr !== '') {
					extraRoadAddr = ' (' + extraRoadAddr + ')';
				}
				// 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
				if (fullRoadAddr !== '') {
					fullRoadAddr += extraRoadAddr;
				}

				// 우편번호와 주소 정보를 해당 필드에 넣는다.
				console.log(data.zonecode);
				console.log(fullRoadAddr);

				$("[name=addr1]").val(data.zonecode);
				$("[name=addr2]").val(fullRoadAddr);

				/* document.getElementById('signUpUserPostNo').value = data.zonecode; //5자리 새우편번호 사용
				document.getElementById('signUpUserCompanyAddress').value = fullRoadAddr;
				document.getElementById('signUpUserCompanyAddressDetail').value = data.jibunAddress; */

				/* 해당필드에 넣을때
				document.getElementById('addr1').value = data.zonecode; //5자리 새우편번호 사용
				document.getElementById('addr2').value = fullAddr; */
			}
		}).open();
	}
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
					주문 페이지 <small>결제 상품 리스트</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="home.do"><i class="fa fa-dashboard"></i> BMW</a></li>
					<li class="active">주문</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">


				<%--  <%@ include file="member/LoginView.jsp" %> --%>
				<!--------------------------
        | Your Page Content Here |
        -------------------------->

				<div class="container">
					<form action="OrderPayment.do" method="post" id="frm">
						<div>
							<h4>장바구니</h4>
							<p>
							<table id="cart" class="table table-hover table-condensed">
								<thead>
									<tr>
										<th style="width: 52.5%">상품</th>
										<th style="width: 12.5%">상품금액</th>
										<th style="width: 10.5%">수량</th>
										<th style="width: 24.5%" class="text-center">주문금액</th>
									</tr>
								</thead>
								<tbody>

									<c:if test="${list.size() != 0 }">
										<c:forEach var="i" begin="0" end="${list.size()-1 }" step="1">
											<tr>
												<td data-th="상품명">
													<div class="row">
														<div class="col-sm-2 hidden-xs">
															<img src="${list.get(i).getFilePath() }" alt="..."
																class="img-responsive" />
														</div>
														<div class="col-sm-10">
															<!-- itemName -->
															<h4 class="nomargin">${list.get(i).title}</h4>
														</div>
													</div>
												</td>
												<td data-th="가격"><fmt:formatNumber
														pattern="###,###,###" value="${list.get(i).price}" /></td>
												<td data-th="수량">
													<%-- <input type="number"
													class="form-control text-center" name="subNo" value="${list.get(i).subNo}" min="1"> --%>
													${list.get(i).subNo} <input type="hidden" name="mainNo"
													value="${list.get(i).mainNo}">
												</td>

												<td data-th="주문금액" class="text-center"><fmt:formatNumber
														pattern="###,###,###"
														value="${list.get(i).price*list.get(i).subNo}" /></td>

											</tr>
										</c:forEach>
									</c:if>

								</tbody>

								<tfoot>
									<tr class="visible-xs">
										<td class="text-center"><strong>총 주문금액 : <fmt:formatNumber
													pattern="###,###,###" value="${sumMoney }" /></strong></td>
									</tr>
									<tr>
										<td><a href="KISItemList.do" class="btn btn-warning"><i
												class="fa fa-angle-left"></i> Continue Shopping</a></td>
										<td colspan="2" class="hidden-xs"></td>
										<td class="hidden-xs text-center"><strong>총 주문금액
												: <fmt:formatNumber pattern="###,###,###"
													value="${sumMoney }" />
										</strong></td>
										<!-- <td><a href="OrderSheet.do" class="btn btn-success btn-block">Checkout
													<i class="fa fa-angle-right"></i>
											</a></td> -->
									</tr>
								</tfoot>
							</table>
						</div>


						<div class="col-md-7">

							<input type="hidden" name="list" value="${list }"> <input
								type="hidden" name="sumMoney" value="${sumMoney }"> <input
								type="hidden" name="count" value="${count }"> <input
								type="hidden" name="curPoint" value="${curPoint }"> <br>
							<p>
							<h4>배송지 정보</h4>
							<strong title="필수입력">수령인</strong>
							<p>
								<input type="text" maxlength="50" name="receiver" id="receiver"
									style="width: 30%;" value=${ssMap.get('name') }
									required="required" class="form-control">
							<p>
								<strong title="필수입력">연락처</strong>
							<div>
								<input type="text" name="phone0" value="${phone0 }"
									required="required" style="width: 60px; text-align: center">
								ㅡ <input type="text" name="phone1" value="${phone1 }"
									required="required" maxlength="4"
									style="width: 60px; text-align: center"> ㅡ <input
									type="text" name="phone2" value="${phone2 }"
									required="required" maxlength="4"
									style="width: 60px; text-align: center">
								<p>
							</div>
							<p>
								<strong title="필수입력">배송지 주소</strong>
							<p>
							<div class="deliver_option">
								<input style="width: 30%; display: inline;" placeholder="우편번호"
									name="addr1" id="addr1" type="text" readonly="readonly"
									class="form-control">
								<button type="button" class="btn btn-default"
									onclick="execPostCode();">
									<i class="fa fa-search"></i>우편번호 찾기
								</button>
								<input class="form-control" placeholder="도로명 주소" name="addr2"
									id="addr2" type="text" readonly="readonly" required="required"
									style="width: 70%"> <input class="form-control"
									placeholder="상세주소" name="addr3" id="addr3" type="text"
									required="required" style="width: 70%">
							</div>
							<p>
							<div>
								<strong>배송메모</strong>
								<p>
									<input class="form-control" type="text" id="memo" name="memo"
										style="width: 70%">
							</div>

							<p>도서산간 지역의 경우 추후 수령 시 추가 배송비가 과금될 수 있습니다.</p>

						</div>

						<div class="col-md-5">

							<h4>주문자 정보</h4>
							<p>이름 &nbsp; :
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								${ssMap.get('name') }
							<p>
							<div>
								<p>연락처 &nbsp; :
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp; ${phone0 } ㅡ ${phone1 } ㅡ ${phone2 }
								<p>
							</div>
							<p>이메일 &nbsp; :
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp; ${ssMap.get('email') }
							<p>보유 포인트 &nbsp; : &nbsp;&nbsp;&nbsp; ${curPoint }
							<div class="progress xs">
								<div class="progress-bar progress-bar-green" style="width: 90%;"></div>
							</div>

							<div style="color: green">
								주문자 정보로 결제관련 정보가 제공됩니다.
								<p>정확한 정보로 등록되어있는지 확인해주세요.
							</div>
							<p>
						</div>

						<!-- 모달 -->


						<c:if test="${list.size() == 0 }">
							<script type="text/javascript">
							alert("장바구니가 비어있습니다. 장바구니를 채워주세요.");
							location.href="RankingMainView.do";
						</script>
						</c:if>
					</form>

					<div align="center" class="col-md-12">

						<p>
							<input type="checkbox" class="minimal" name="req" id="req">
							위 상품의 구매조건 확인 및 결제진행 동의 <br>
							<button onclick="chk()" class="btn btn-block btn-success btn-lg"
								style="width: 25%; height: 70px">결제하기</button>
					</div>
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
