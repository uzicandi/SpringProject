<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 탈퇴</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
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

/* Style the header */
.header {
	background-color: #f1f1f1;
	padding: 20px;
	text-align: center;
}

/* Style the top navigation bar */
.topnav {
	overflow: hidden;
	background-color: #333;
}

/* Style the topnav links */
.topnav a {
	float: left;
	display: block;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

/* Change color on hover */
.topnav a:hover {
	background-color: #ddd;
	color: black;
}

</style>
<script type="text/javascript">
	$(function () {
		if('${result}'){
			alert("탈퇴실패! 입력한 정보를 확인해주세요")
		}
	});
	
	
	function delok(){
	    result = confirm('작성한 게시물과 댓글은 지워지지 않습니다. 정말로 탈퇴하시겠습니까?');
	    if(result == true) document.getElementById('frm').submit();
	    else return false;
	}
	
	
// 	$(document).ready(function() { 
// 		if(${result} == 0){
// 			alert("탈퇴실패! 입력한 정보를 확인해주세요")
// 		}
// 	});
</script>
</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
  
  <%@ include file="../partials/header.jsp" %>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        
        <small></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> MemberService</a></li>
        <li class="active">Withdrawal</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid" style="margin-top: 50px;">

<div class="top-content">
	<div class="inner-bg">
		<div class="container">
			<div class="row">
				<div class="col-sm-8 col-sm-offset-2 text">
					<h3>  
						<strong><i class="fa fa-user-times"></i>&nbsp;회원탈퇴</strong>
					</h3>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-8 col-sm-offset-2 form-box">
					<div class="form-top" style="margin-bottom: 20px;">
						<div class="form-top-left" >
							<h3>탈퇴 후 모든 개인정보가 삭제됩니다.</h3>
							<p>게다가 포인트랑 쿠폰도 사라진다구욧 (질-척)</p>
						</div>
					</div>
					
 <div class="form-bottom">
	<form action="memberWithdrawalPro.do" id="frm">
		<div class="form-group">
			<label class="sr-only" for="form-username">USER ID</label>
				<input type="text" name="id" placeholder="USER ID..." class="form-username form-control">
		</div>
		<div class="form-group">
			<label class="sr-only" for="form-password">Password</label>
				<input type="password" name="passwd" placeholder="Password..." class="form-password form-control" >
		</div>
		<input type="button" value="탈퇴" onclick="delok()" class="btn">
		
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
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->


</body>
</html>