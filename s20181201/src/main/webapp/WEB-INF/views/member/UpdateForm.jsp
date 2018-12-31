 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 
<%-- <!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/head.jsp" %>
<title>회원정보수정</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<%@ include file="../partials/header.jsp" %>  --%>
<!-- <div class="wrapper"> -->
  
<%--   <%@ include file="../partials/head.jsp" %> --%>
  
  
  
  
   <!-- Content Wrapper. Contains page content -->
<!--   <div class="content-wrapper"> -->
    <!-- Content Header (Page header) -->
<!--    <section class="content-header">
      <h1>
       
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>MemberService </a></li>
        <li class="active">UpdateInfo</li>
      </ol>
    </section>  -->

    <!-- Main content -->
    <section class="content container-fluid"> 

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
							<div>
							<!-- small box -->
							<div class="small-box bg-yellow">
								<div class="inner">
									<h3>회원정보 수정</h3>

									<p>update your information</p>
								</div>
								<div class="icon">
									<i class="ion ion-person-add"></i>
								</div>

							</div>
					
		</div>
					<!-- <div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>회원정보 수정</strong> 
						</h1>
					</div> -->
				</div>
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 form-box">
						 <div class="form-top">
							<div class="form-top-left">
								<h3></h3>
									<p></p>
							</div>
							
						</div> 
						<div class="form-bottom">
							<form role="form" action="UpdateMember.do" method="post"
								class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">memberId</label>아이디
									<input name="memberId" value="${member.memberId }" readonly="readonly" 
									class="form-username form-control" width="80%">
								</div>
								
								<div class="form-group">
									<label class="sr-only" for="form-username">Password</label>패스워드
									<input type="password" name="passwd" value="${member.passwd }" class="form-username form-control" required>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-username">Name</label>이름
									<input name="name" value="${member.name }" class="form-username form-control" required>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-username">Address</label>주소
									<input name="address" value="${member.address }" class="form-username form-control" required>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-username">Phone</label>전화번호
									<input name="phone" value="${member.phone }" class="form-username form-control" required>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-username">Email</label>이메일
									<input name="email" value="${member.email }" class="form-username form-control" required>
								</div>
								
								<button type="submit" class="btn" id="update">수정하기</button>
								<%-- <c:if test="${msg == 'updateOK' }">
								<div style="color: blue">회원정보가 수정되었습니다.
								</div>
								</c:if> --%>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div> 
   </section>
    <!-- /.content -->
<!--   </div> -->
  <!-- /.content-wrapper -->

<%--   <%@ include file="../partials/footer.jsp" %>
	
  <div class="control-sidebar-bg"></div>
 </div> 	 --%>

</body>
</html>