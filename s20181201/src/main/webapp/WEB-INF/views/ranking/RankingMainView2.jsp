<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List View</title>
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
		$('.col-sm-4').click(function() {
			$('.col-sm-8').remove();
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
 .fakeimg {
      height: 200px;
      background: #aaa;
  }

	 ul > li:hover > a,
	 ul > li:focus > a,
	 ul > li:active > a,
	 ul > li.active >a {
	  color: yellow;
	  background-color: #D1CFC7;
	 }

</style>
</head>
<body>
<header><%@ include file="../partials/header.jsp" %></header>
${ssMap.get("nickName")}
${ssMap.get("sex")}
${ssMap.get("birth")}
${ssMap.get("memberId")}
${ssMap.get("skinType")}
${ssMap.get("email")}
${ssMap.nickName}
<div class="container" style="margin-top:30px">
  <div class="row">
    <div class="col-sm-4">
      <h4>카테고리별</h4>
      <c:forEach var="parList" items="${parList}">
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link" onclick="goType('${parList.name}')">${parList.name}</a>
        </li>
      </ul>
      </c:forEach>
      <br>
     
      <h4>피부타입별</h4>
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link" onclick="goType('건성')">건성</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" onclick="goType('중성')">중성</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" onclick="goType('지성')">지성</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" onclick="goType('복합성')">복합성</a>
        </li>
      </ul>
      <br>
      
      <h4>연령별</h4>
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link" onclick="goType('10대')">10대</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" onclick="goType('20대')">20대</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" onclick="goType('30대')">30대</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" onclick="goType('40대')">40대 이상</a>
        </li>
      </ul>
      <br>
     
    <hr class="d-sm-none">
    </div> 
    
    
    <br><div id="RankingTypeView"></div>
    
    
    
    <div class="col-sm-8">
      <p>${ssMap.get("nickName")}님의 데이터를 분석하여 추천하는 제품입니다</p>
      
      <h1><c:choose>
      		<c:when test="${ssMap.get('sex') == '0'}">
      			 남성의 선호제품
      		</c:when>
      		<c:when test="${ssMap.get('sex') == '1'}">
      			 여성의 선호제품
      		</c:when>
      </c:choose></h1>  
      
      
      <div class="fakeimg">

		<c:forEach var="sexBestList" items="${sexBestList}">
		<%-- 	<c:if test="${sexBestList.rankNo <=5}">
				${sexBestList.rankNo}
		 		${sexBestList.name}
		 	</c:if> --%>
      	</c:forEach>
	  </div>
      <br>
   
      <h1><c:choose>
      		<c:when test="${ssMap.get('skinType') == '0'}">
     			건성을 위한 제품
      		</c:when>
      		<c:when test="${ssMap.get('skinType') == '1'}">
     			중성을 위한 제품
      		</c:when>
      		<c:when test="${ssMap.get('skinType') == '2'}">
     			지성을 위한 제품
      		</c:when>
      		<c:when test="${ssMap.get('skinType') == '3'}">
     			복합성을 위한 제품
      		</c:when>
      </c:choose>
      </h1>
      
      <div class="fakeimg">

		 <c:forEach var="typeBestList" items="${typeBestList}">
<%--  		 	<c:if test="${typeBestList.rankNo <= 5}">
 		 		${typeBestList.rankNo}
		 		${typeBestList.name}	 		
  		 	</c:if> --%>
      	</c:forEach>
      
      </div>
      <br>
      
      
<!-- 		해쉬태그 분석해서 뿌려주기 -->
      <h1>이런 제품은 어떠세요?</h1>
      <div class="fakeimg"></div>
      <br>
      
    </div>
    
  </div>
</div>


<footer><%@ include file="../partials/footer.jsp" %></footer>
</body>
</html>
