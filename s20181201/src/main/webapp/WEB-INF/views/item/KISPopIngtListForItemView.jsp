<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingredient List View</title>
<script>
	$(function () {
		$("ul.tabs li").click(function() {
			var tab_id = $(this).attr("data-tab");
		
		$("ul.tabs li").removeClass("current");
		$("ul.tabs li").removeClass("active");
		$(".tab-content").removeClass("current");
		
		$(this).addClass("current");
		$(this).addClass("active");
		$("#" + tab_id).addClass("current");
		})
	})
	
</script>

<style type="text/css">
	ul.tabs {
	    margin: 0px;
		padding: 0px;
		list-style: none;
		height: 40px;
		border-bottom: 1px solid #ddd;
	    border-left: 1px solid #ddd;
	}
	ul.tabs li {        
        float: left;
	    text-align:center;
	    cursor: pointer;
	    width:100px;
 	    height: 40px;
	    line-height: 40px;
	    border: 1px solid #ddd;
	    border-left: none;
	    font-weight: bold;
	    background: #fafafa;
	    overflow: hidden;
	    position: relative;
	}
	ul.tabs li.current {
		color: #d81b60;
		background: #FFFFFF;
    	border-bottom: 1px solid #FFFFFF;
	}
	.tab-content {
		display: none;
		padding: 15px;
	}
	.tab-content.current {
		display: inherit;
	}
	
</style>
</head>
<body>
		<ul class="tabs">
			<li class="tab-link current" data-tab="tab-1">성분구성</li>
			<li class="tab-link" data-tab="tab-2">피부타입별</li>
			<li class="tab-link" data-tab="tab-3">기능성 성분</li>
		</ul>
		<div id="tab-1" class="tab-content current">
			<table class="table table-striped">
			<tr>
				<th class="title">성분명</th>
				<th class="title">위험도 등급</th>
			</tr>
			<c:set var="num" value="${pg.total-pg.start+1}"></c:set>
				<c:forEach var="ingt" items="${ingtlist}">
				<tr>
					<td>${ingt.name}</td>
					<td>
						<c:choose>
							<c:when test="${ingt.grade == 1}">
								<img src="./resources/images/grade1.png" width="30px" height="35">
							</c:when>
							<c:when test="${ingt.grade == 2}">
								<img src="./resources/images/grade2.png" width="30px" height="35">
							</c:when>
							<c:when test="${ingt.grade == 3}">
								<img src="./resources/images/grade3.png" width="30px" height="35">
							</c:when>
							<c:when test="${ingt.grade == 4}">
								<img src="./resources/images/grade4.png" width="30px" height="35">
							</c:when>
							<c:when test="${ingt.grade == 5}">
								<img src="./resources/images/grade5.png" width="30px" height="35">
							</c:when>
							<c:when test="${ingt.grade == 6}">
								<img src="./resources/images/grade6.png" width="30px" height="35">
							</c:when>
							<c:when test="${ingt.grade == 7}">
								<img src="./resources/images/grade7.png" width="30px" height="35">
							</c:when>
							<c:when test="${ingt.grade == 8}">
								<img src="./resources/images/grade8.png" width="30px" height="35">
							</c:when>
							<c:when test="${ingt.grade == 9}">
								<img src="./resources/images/grade9.png" width="30px" height="35">
							</c:when>
						</c:choose>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div id="tab-2" class="tab-content">
			<table class="table table-striped">
			<tr>
				<th class="title">성분명</th>
				<th class="title">피부타입별 특이성분</th>
			</tr>
				<c:forEach var="ingt" items="${ingtlist}">
				<tr>
					<c:if test="${ingt.specialyType != null }">
						<td>${ingt.name}</td>
						<td>${ingt.specialyType}</td>
					</c:if>
				</tr>
				</c:forEach>
			</table>
		</div>
		<div id="tab-3" class="tab-content">
			<table class="table table-striped">
			<tr>
				<th class="title">성분명</th>
				<th class="title">기능성 성분</th>
			</tr>
				<c:forEach var="ingt" items="${ingtlist}">
				<tr>
					<c:if test="${ingt.functional != null }">
						<td>${ingt.name}</td>
						<td>${ingt.functional}</td>
					</c:if>
				</tr>
				</c:forEach>
			</table>
		</div>
</body>
</html>