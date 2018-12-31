<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  	<title>Ranking View</title>
 <%@ include file="../partials/head.jsp" %>
<script type="text/javascript">

	function goType(data) {
		$.ajax({
		    url: 'RankingType.do?groupName=' + data,
		    type: 'GET',
		   	error : function() {
		   		alert("안되 다시 로드해");
		   	},
		   	success : function(data) {
		   		console.log('data -> ' + data);
		   		$('#RankingTypeView').html(data);
		   	}
		});
	};
		
	$(document).ready(function() {		
		$('.well').click(function() {
			$('.sidenav').remove();
		});
		
		$('.cateGroup').click(function() {
			$('.well').toggle();
			$('.cateDivi').css("background-color", "white");
		}); 
	});
	
	$(function(){
		  var x = $("ul > li");    
		  x.find("a").click(function(){   
		   x.removeClass("active");  
		   $(this).parent().addClass("active"); 
		  });
	 });
	
</script>
<style>
 	 ul > li:hover > a,
	 ul > li:focus > a,
	 ul > li:active > a,
	 ul > li.active >a {
	  color: white;
	  background-color: white;
	 } 

 .cateDivi {
 	display: none;
 	background-color: white;
 	margin-bottom: 10px;
 	margin-top: 10px;
 	margin-left: 35%;
 }
 
 h2 {
 	text-align: center;
 }
 
 p {
 	text-align: center;
 }
 .wrapper{
 	height:auto;
 	overflow:hidden;
 }
.content-wrapper{
		width:auto;
	    height:auto;
	    overflow:auto;
}
 .content{
	height:auto;
	overflow:hidden;
	text-align: center;
	margin: 0 auto;
} 


</style>
</head>
<body class="skin-red-light sidebar-mini" >
<div class="wrapper" style="height: 100%; min-height: 100%;">
<%@ include file="../partials/header.jsp" %>
<div class="content-wrapper">
    <section class="content-header" style="height: 20px;">
    	<h1>Ranking<small>Best Product</small></h1>
    		<ol class="breadcrumb">
        		<li><a href="#"><i class="fa fa-dashboard"></i>Ranking</a></li>
        		<li class="active">Main View</li>
      		</ol>
    </section>
    <br>

	<!-- Main content -->
    <section class="content">
    	<div class="col-sm-12">
			<div class="col-sm-4 cateGroup">
				<h4><label>카테고리별</label></h4>
				<div class="well cateDivi" style="width: 135px;">
					<c:forEach var="parList" items="${parList}">
						<ul class="nav nav-pills flex-column">
							<li class="nav-item"><a class="nav-link"
								onclick="goType('${parList.name}')">${parList.name}</a></li>
						</ul>
					</c:forEach>

				</div>
			</div>
			<div class="col-sm-4 cateGroup">
				<h4><label>피부타입별</label></h4>
				<div class="well cateDivi" style="width: 135px;">
					<ul class="nav nav-pills flex-column">
						<li class="nav-item"><a class="nav-link"
							onclick="goType('건성')">건성</a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="goType('중성')">중성</a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="goType('지성')">지성</a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="goType('복합성')">복합성</a></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-4 cateGroup">
				<h4><label>연령별</label></h4>
				<div class="well cateDivi" style="width: 135px;">
					<ul class="nav nav-pills flex-column">
						<li class="nav-item"><a class="nav-link"
							onclick="goType('10대')">10대</a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="goType('20대')">20대</a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="goType('30대')">30대</a></li>
						<li class="nav-item"><a class="nav-link"
							onclick="goType('40대')">40대 이상</a></li>
					</ul>
				</div>
			</div>
		</div>
    	
    	<br><div class="col-sm-12" id="RankingTypeView" style="text-align: center;"></div>
		
		<div class="row content" style="width: 80%">
		<div class="col-md-12 sidenav">
		<!-- <div class="col-sm-12 rankingGroup"> -->
				<p><span style="color:blue;"><b>${ssMap.get("nickname")}</b></span>님의데이터를 분석하여 추천하는 제품입니다</p>
			<div class="box box-info">
				<h2><b>
			<c:choose>
				<c:when test="${ssMap.get('sex') == '0'}">남성의 선호제품</c:when>
				<c:when test="${ssMap.get('sex') == '1'}">여성의 선호제품</c:when>
			</c:choose>
			</b></h2>
	
			<div class="box-body">
				<div class="col-sm-1"></div>
				<c:forEach var="sexBestList" items="${sexBestList}">
					<c:if test="${sexBestList.rankNo <=5}">
						<div class="col-sm-2">
						<div style="margin-bottom: 5%">
						<a href="KISItemContent.do?itemNo=${sexBestList.itemNo}">
							<img src="${sexBestList.saveFileList[0].filePath}" width="100" height="180">
						</a>
						</div>
						${sexBestList.rankNo}위<br>
						${sexBestList.name}
						</div>
	 				</c:if>
				</c:forEach>
				<div class="col-sm-1"></div>
			</div>
			</div>
		
		<div class="box box-info">
		<h2><b>
		<c:choose>
			<c:when test="${ssMap.get('skinType') == '0'}">건성을 위한 제품	</c:when>
			<c:when test="${ssMap.get('skinType') == '1'}">중성을 위한 제품	</c:when>
			<c:when test="${ssMap.get('skinType') == '2'}">지성을 위한 제품</c:when>
			<c:when test="${ssMap.get('skinType') == '3'}">복합성을 위한 제품</c:when>
			</c:choose>
		</b></h2>

		<div class="box-body">
			<div class="col-sm-1"></div>
			<c:forEach var="typeBestList" items="${typeBestList}">
				<c:if test="${typeBestList.rankNo <= 5}">
					<div class="col-sm-2">
					<div style="margin-bottom: 5%">
						<a href="KISItemContent.do?itemNo=${typeBestList.itemNo}">
							<img src="${typeBestList.saveFileList[0].filePath}" width="150" height="200">
 						</a>
 					</div>
 					<div style="margin-bottom: 5%">${typeBestList.rankNo}위</div>
 					<div style="margin-bottom: 5%">${typeBestList.name}</div>	
 					</div>
 				</c:if>
			</c:forEach>
			<div class="col-sm-1"></div>
		</div>
		</div>

		
		<div class="box box-info">
		<h2><b>이런 제품은 어떠세요?</b></h2>
		<div class="box-body">
			<div class="col-sm-1"></div>
			<c:forEach var="likeBestList" items="${likeBestList}" begin="1" end="5" step="1">
				<div class="col-sm-2">			
				<div style="margin-bottom: 5%">
					<a href="KISItemContent.do?itemNo=${likeBestList.itemNo}">
						<img src="${likeBestList.saveFileList[0].filePath}" width="150" height="200">
					</a>
				</div>
				<div style="margin-bottom: 5%">${likeBestList.name}</div>
				<div style="margin-bottom: 5%">${likeBestList.brand}</div>					
 				</div>
 			</c:forEach>
 			<div class="col-sm-1"></div>
		</div>
		</div>
	
	
	</div>
	</div>
    </section>
</div>
<%@ include file="../partials/footer.jsp" %>
</div>
</body>
</html>
