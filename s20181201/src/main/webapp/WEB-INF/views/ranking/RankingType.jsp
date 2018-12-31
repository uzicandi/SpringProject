<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Item View</title>
</head>
<body class="skin-red-light sidebar-mini" style="height: auto; min-height: 100%;">
<!--  <div class="content-wrapper"> -->
 	<div class="row content" style="width: 80%">
 	<div class="box box-info">
 		<h2>${groupName} 제품 List</h2><br><br>
 		<div class="box-body">
			<c:forEach var="list" items="${list}">
	  			<div class="row content">
	  				<div class="col-sm-1"></div>
	   				<div class="col-sm-5 sidenav">
	   					<a href="KISItemContent.do?itemNo=${list.itemNo}">
	    					<img src="${list.saveFileList[0].filePath}" width="200" height="250">
	    				</a>
	    			</div>
	   				<div class="col-sm-5">
						<b>${list.rankNo}위</b> <br>
						${list.brand} <br>
						${list.name} <br>
						<fmt:formatNumber pattern="###,###,###" value="${list.price}" />원
	    			</div>
	    			<div class="col-sm-1"></div>
	  			</div>
	  			<br><br><br>
	   		</c:forEach>
	   	</div>	
   	</div>
   	</div>
<!-- </div> -->
</body>
</html>
