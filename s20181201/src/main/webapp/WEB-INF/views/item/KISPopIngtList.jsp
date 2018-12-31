<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingredient List View</title>
<script>
// 	function searchCheck(frm) {
// 		if (frm.keyWord.value == "") {
// 			alert("검색 단어를 입력하세요.");
// 			frm.keyWord.focus();
// 			return;
// 		}
// 		frm.submit();
// 	}
	
	function checkClick() {
		if ($("input[name=myChecks]:checkbox").prop("checked") == true) {
			$("input[name=myCheck]:checkbox").prop("checked", true);			
		} else if($("input[name=myChecks]:checkbox").prop("checked") == false){
			$("input[name=myCheck]:checkbox").prop("checked", false);
		}
	}
	
	
	
</script>
</head>
<body>
<div class="box-header with-border">
	<table class="table table-striped">
	<!-- <tr>
		<td style="align: left;">
			<a href="KISIngtWriteForm.do"><button>성분 등록</button></a>
		</td>
		<td colspan="6" style="align: right;">
			<form name="search" method="post">
				<input type="text" name="keyWord" style="width:300px;height:30px;"> <input type="button"
					value="검색" onclick="searchCheck(form)">
			</form>
		</td>
	</tr> -->
		<tr>
			<th><input type="checkbox" name="myChecks" onclick="checkClick()"></th>
			<th class="title">번호</th>
			<th class="title">성분명</th>
			<th class="title">위험도 등급</th>
			<th class="title">20가지 주의성분</th>
			<th class="title">알레르기 주의성분</th>
			<th class="title">피부타입별 특이성분</th>
			<th class="title">기능성 성분 여부</th>
		</tr>
		<c:set var="num" value="${pg.total-pg.start+1}"></c:set>
		<c:if test="${total > 0 }">
			<c:forEach var="ingt" items="${list}">
				<tr>
					<td>					
						<input type="checkbox" value="${ingt.ingredientNo}" name="myCheck"
						<c:forEach var="ingtlist" items="${mappList}">
						<c:if test="${ingt.ingredientNo == ingtlist.ingredientNo}">checked="checked"</c:if>
						</c:forEach>>  					 
						<input type="hidden" id="chk_${ingt.ingredientNo}" value="${ingt.name}">
					</td>
					<td>${num}</td>
					<td>${ingt.name}</td>
					<td>${ingt.grade}</td>
					<td>${ingt.danger20}</td>
					<td>${ingt.dangerAllergy}</td>
					<td>${ingt.specialyType}</td>
					<td>${ingt.functional}</td>
				</tr>
				<c:set var="num" value="${num - 1}" />
			</c:forEach>
		</c:if>
		<c:if test="${total == 0}">
			<tr>
				<td colspan=7>데이터가 없네</td>
			</tr>
		</c:if>
	</table>
	</div>
</body>
</html>