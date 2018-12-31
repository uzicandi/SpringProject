<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/head.jsp" %>

<title>아이디찾기</title>
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

<script>
$(document).ready(function() {
	$("#findId").click(function() {
		// 태그.val() : 태그에 입력된 값
		// 태그.val("값") : 태그의 값을 변경
		/* var email = $("#memberId").val();
		var passwd = $("passwd").val();
		if (memberId == "") {
			alert("아이디를 입력하세요");
			$("#memberId").focus(); //입력 포커스 이동
			return; // 함수 종료
		}
		if (passwd == "") {
			alert("아이디를 입력하세요.");
			$("#passwd").focus();
			return;
		} */
		/* 			// 폼 내부의 데이터를 전송할 주소
		 document.form1.action.value="KJWloginCheck";
		 // 제출
		 document.form1.submit(); */
	});

});	
</script>

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
        <li class="active">FindId</li>
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
						<strong>아이디 찾기</strong>
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
						<form role="form" action="FindIdCheck.do" method="post" class="login-form">
							<div class="form-group">
								<label class="sr-only" for="form-username">E-Mail</label> <input
									type="text" name="email" placeholder="E-Mail..."
									class="form-username form-control" id="email" required>
							</div>
							<div class="form-group">
								<label class="sr-only" for="form-username">Question</label> 
									<select class="form-control" id="question" name="question">
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
							<button type="submit" class="btn" id="findId">Search ID</button>
							<c:if test="${msg == 'success' }">
								<div style="color: red">당신의 아이디는 
									${memberId }입니다.
								</div>
							</c:if>
							<c:if test="${msg == 'failure' }">
								<div style="color: red">입력한 정보와 일치하는 ID가 없습니다.</div>
							</c:if>
							
							<div class="form-bottom-left">
								<p><a href="FindPwView.do">Search Password</a></p>
							</div>
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