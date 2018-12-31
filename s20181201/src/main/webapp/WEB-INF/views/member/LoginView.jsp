<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/head.jsp" %>
<title>로그인페이지</title>
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
		$("#btnLogin").click(function() {
			// 태그.val() : 태그에 입력된 값
			// 태그.val("값") : 태그의 값을 변경
			var memberId = $("#memberId").val();
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
			}
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
        <li class="active">Login</li>
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
						<strong>BMW</strong>에 오신 것을 환영합니다! 
					</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-6 col-sm-offset-3 form-box">
					<div class="form-top">
						<div class="form-top-left">
							<h3>Login to our site</h3>
							<p>BMW에서 다양한 혜택을 받아보세요!</p>
						</div>
						
					</div>
					<div class="form-bottom">
						<form role="form" action="LoginCheck.do" method="post"
							class="login-form">
							<div class="form-group">
								<label class="sr-only" for="form-username">USER ID</label> <input
									type="text" name="memberId" placeholder="USER ID..."
									class="form-username form-control" id="memberId">
							</div>
							<div class="form-group">
								<label class="sr-only" for="form-password">Password</label> <input
									type="password" name="passwd" placeholder="Password..."
									class="form-password form-control" id="passwd">
							</div>
							<div class="col-sm-12">
								<button type="submit" class="btn bg-maroon" id="btnlogin">Sign in!</button>
								<c:if test="${msg == 'failure' }">
									<div style="color: red">아이디 또는 비밀번호가 일치하지 않습니다.</div>
								</c:if>
								<c:if test="${msg == 'success' }">
									<script>location.href="index.jsp";</script>
								</c:if>
	 						</div><p>
							<div class="col-sm-3 col-sm-offset-3">
								<p>
									<a href="FindIdView.do">ID/PW 찾기</a>
								</p>
							</div>
							<div class="col-sm-3">
								<p>
									<a href="memberJoinAgreeForm.do">회원가입</a>
								</p>
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