<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../partials/head.jsp" %>

<title>Insert title here</title>

<style type="text/css">
* {
	box-sizing: border-box;
}

table {
	width: 100%;
}

body {
	margin: 0;
}

tr .th{
	background-color: #cce8f4;
}

td {
	background-color: #eee;
	text-align: center;
}

/* Style the header */
.header {
	background-color: #f1f1f1;
	padding: 20px;
	text-align: center;
}

</style>




</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
  
  <%@ include file="../partials/header.jsp" %>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        회원관리
        <small>Member List</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>Admin</a></li>
        <li class="active">MemberList</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

  <!-- Top content -->
<div class="top-content">   
    
	<div class="inner-bg">
		<div class="container">
 
 
 
      <!-- <h2>회원 목록</h2> -->


   <div style="margin-top: 50px;">	
	<div class="row">
		<div class="col-xs-12 table-responsive">	
	
<c:set var="num" value="${pg.total-pg.start+1}"></c:set>
	<table class="table table-striped">
		<thead>
			<tr>
				<td class="th">아이디&emsp;&emsp;</td>
				<td class="th">닉네임&emsp;&emsp;</td>
				<td class="th">이름&emsp;&emsp;</td>
				<td class="th">생년월일&emsp;&emsp;</td>
				<td class="th">성별&emsp;&emsp;</td>
				<td class="th">주소</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="member">
				<tr>
					<td><a href="memberToDetailAdmin.do?memberId=${member.memberId}">${member.memberId}&emsp;</a></td>
					<td>${member.nickname}&emsp;</td>
					<td>${member.name}&emsp;</td>
					<td>${member.birth}&emsp;</td>
					<td><script type="text/javascript">
								sexArray = new Array("남성", "여성")
								i = ${member.sex}
								{
									document.write(sexArray[i]);
								}
							</script>&emsp;</td>
					<td>${member.address}</td>
				</tr>
			<c:set var="num" value="${num - 1}"></c:set>
			</c:forEach>
		</tbody>		
	</table>
<div class="row" style="text-align: center;">
	<div>
		<form action="memberToListAdmin.do" method="post">
			<select name="keyword">
				<option value="아이디">아이디</option>
				<option value="이름">이름</option>
				<option value="주소">주소</option>
			</select> 
			<input type="text" name="search" value="${search}"> 
			<input type="submit" value="검색">
		</form>
	</div>		
</div>	

 <div style="margin-top: 20px;">	
<div class="row">	
	<c:choose>
		<c:when test="${search != null }">
			<div style="text-align: center;">
				<c:if test="${pg.startPage > pg.pageBlock}">
				<input class="btn" type="button" value="이전" onclick="location.replace('memberToListAdmin.do?currentPage=${pg.startPage-pg.pageBlock}&search=${search}&keyword=${keyword}')">
					<%-- <a href='memberToListAdmin.do?currentPage=${pg.startPage-pg.pageBlock}&search=${search}&keyword=${keyword}'>[이전]</a> --%>
				</c:if>
				<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
				<input class="btn" type="button" value="${i}" onclick="location.replace('memberToListAdmin.do?currentPage=${i}&search=${search}&keyword=${keyword}')">
				<%-- 	<a href='memberToListAdmin.do?currentPage=${i}&search=${search}&keyword=${keyword}'>[${i}]</a> --%>
				</c:forEach>
				<c:if test="${pg.endPage < pg.totalPage}">
				<input class="btn" type="button" value="다음" onclick="location.replace('memberToListAdmin.do?currentPage=${startPage+blockSize }&search=${search}&keyword=${keyword}')">
				<%-- 	<a href='memberToListAdmin.do?currentPage=${startPage+blockSize }&search=${search}&keyword=${keyword}'>[다음]</a> --%>
				</c:if>
			</div>
		</c:when>
		<c:otherwise>
			<div style="text-align: center;">
				<c:if test="${pg.startPage > pg.pageBlock}">
				<input class="btn" type="button" value="이전" onclick="location.replace('memberToListAdmin.do?currentPage=${pg.startPage-pg.pageBlock}')">
					<%-- <a href="memberToListAdmin.do?currentPage=${pg.startPage-pg.pageBlock}">[이전]</a> --%>
				</c:if>
				<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
				<input class="btn" type="button" value="${i}" onclick="location.replace('memberToListAdmin.do?currentPage=${i}')">
					<%-- <a href="memberToListAdmin.do?currentPage=${i}">[${i}]</a> --%>
				</c:forEach>
				<c:if test="${pg.endPage < pg.totalPage}">
				<input class="btn" type="button" value="다음" onclick="location.replace('memberToListAdmin.do?currentPage=${pg.startPage+pg.pageBlock}')">
					<%-- <a href="memberToListAdmin.do?currentPage=${pg.startPage+pg.pageBlock}">[다음]</a> --%>
				</c:if>
			</div>
		</c:otherwise>
	</c:choose>
</div>
</div>

</div>
</div>
</div>
</div>

</div>
</div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <%@ include file="../partials/footer.jsp" %>
	
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->


</body>
</html>