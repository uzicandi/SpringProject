<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/head.jsp" %>

<title>비밀번호찾기</title>
<style type="text/css">
	.bg-img {
  /* The image used */
  background-image: url('./resources/images/LoginBack.jpg');
 	opacity: 0.95; 
  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}


.container {
  position: relative;
 /*  right: 0; */
  margin: 20px;
  /* max-width: 300px; */
  padding: 16px;
  background-color: #ffffff;
  background-color: rgba( 255, 255, 255, 0.25 );
  border-radius: 10px;
 
}
</style>

</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
  
  <%@ include file="../partials/header.jsp" %>
  
   <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper bg-img">
    <!-- Content Header (Page header) -->
   <section class="content-header">
      <h1>
       
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>MemberService </a></li>
        <li class="active">FindPw</li>
      </ol>
    </section> 

    <!-- Main content -->
    <section class="content container-fluid"> 

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg" align="center">
			<div class="container" style="margin-top: 150px;">
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 text">
						<h1>
							<strong>비밀번호 찾기</strong>
						</h1>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3></h3>
								<!-- 	<p></p> -->
							</div>
							<!-- <div class="form-top-right">
							<i class="fa fa-lock"></i>
						</div> -->
						</div>
						<div class="form-bottom">
							<form role="form" action="FindPwCheck.do" method="post"
								class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">ID</label> <input
										type="text" name="memberId" placeholder="User Id..."
										class="form-username form-control" id="memberId" required>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-username">E-Mail</label> <input
										type="text" name="email" placeholder="E-Mail..."
										class="form-username form-control" id="email" required>
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-username">Question</label> <select
										class="form-control" id="question" name="question">
										<option value="0" selected="selected">Select Question...</option>
										<option value="1">아버님은 무슨일 하시니?</option>
										<option value="2">연봉이 어떻게 되니?</option>
										<option value="3">차는 있니?</option>
										<option value="4">문제 있는 장기 있니?</option>
									</select>

									<!-- <input
									type="email" name="question" placeholder="Question..."
									class="form-username form-control" id="question"> -->
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-username">Answer</label> <input
										type="text" name="answer" placeholder="Answer..."
										class="form-username form-control" id="answer" required>
								</div>
								<button type="submit" class="btn" id="findPw">Search Password</button>
								<c:if test="${msg == 'success' }">
									<div style="color: blue">입력하신 이메일로 임시비밀번호를 전송하였습니다.</div>
								</c:if>
								<c:if test="${msg == 'failure' }">
									<div style="color: red">입력한 정보가 맞지 않습니다.</div>
								</c:if>
								<c:if test="${msg == 'mailFailure' }">
									<div style="color: red">메일전송에 실패하였습니다..</div>
								</c:if>
							</form>
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


</body>
</html>