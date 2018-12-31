<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../partials/head.jsp"%>

<title>Insert title here</title>

<style type="text/css">
* {
	box-sizing: border-box;
}

table {
	width: 100%;
}

th{
	background-color: #cce8f4;
	text-align: center;
}

td {
	background-color: #d6ecf6;
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
        회원정보 수정(관리자)
        <small>Member Update (Admin)</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Admin</a></li>
        <li class="active">MemberUpdate</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content container-fluid">

				<!-- Top content -->
				<div class="top-content">

					<div class="inner-bg">
						<div class="container">

							<div style="margin-top: 50px; text-align: center;">
								<div class="row" >
									<div class="col-lg-3"></div>
									<div class="col-lg-6 table-responsive" style="text-align: center;">

										
	<form action="memberToUpdateAdmin.do" method="post">
<%-- 		<input type="hidden" name="grade" value="${member.grade }"> --%>
		<input type="hidden" name="memberId" value="${member.memberId }">
		<table class="table table-striped">
		
		
			<tr>
				<th>아이디</th>
				<td>${member.memberId }</td>
			</tr>
		
			<tr><th>닉네임</th><td><input type="text" name="nickname" value="${member.nickname }" required></td></tr>
			<tr><th>이름</th><td><input type="text" name="name" value="${member.name }" required></td></tr>
			<tr><th>생일</th><td><input type="date" name="birth" value="${member.birth }" required></td></tr>
			<tr><th>성별</th><td>
					남성 <input type="radio" name="sex" value="${member.sex }" checked />
					여성 <input type="radio" name="sex" value="${member.sex }" /></td></tr>



			<tr>
				<th>피부타입</th>
				<td><input type="radio" name="skinType" value="0" id="st1" required></input><label for="st1">건성</label>
                              <input type="radio" name="skinType" value="1" id="st2"></input><label for="st2">중성</label>
                              <input type="radio" name="skinType" value="2" id="st3"></input><label for="st3">지성</label>
                              <input type="radio" name="skinType" value="3" id="st4"></input><label for="st4">복합성</label></td>
			</tr>
			<tr><td>피부고민<br></td>
<!-- <span style="color: red">(중복선택 가능)</span> -->
            <td><input type="radio" name="skinComplex" value="0" id="sc1" checked="checked" required></input><label for="sc1">해당없음</label>
                <input type="radio" name="skinComplex" value="1" id="sc2"></input><label for="sc2">아토피</label>
                <input type="radio" name="skinComplex" value="2" id="sc3"></input><label for="sc3">여드름</label>
                <input type="radio" name="skinComplex" value="3" id="sc4"></input><label for="sc4">민감성</label>
                </td></tr>
            <tr>
				<th>회원등급</th>
				<td>
				
					<select id="grade" name="grade">
						<option value="0" >관리자</option>
						<option value="1">판매자</option>
						<option value="2">일반회원</option>
						<option value="3">대기자</option>
						<option value="4">휴면계정</option>
						<option value="5">탈퇴회원</option>
					</select>
				</td>
			</tr>
			
		<tr><th>이메일</th><td><input type="email" name="email" placeholder="abc@abc.com" required></td></tr>
		<tr><th>메일수신여부</th><td>
		<input type="radio" name="isEmail" value="1" id="i1"></input><label for="i1">수신</label>
        <input type="radio" name="isEmail" value="0" id="i2" checked="checked"></input>
        <label for="i2">미수신</label></td></tr>
        
		<tr><th>전화번호</th><td><input type="tel" name="phone"
                              pattern="\d{2,3}-\d{3,4}-\d{4}" placeholder="xxx-xxxx-xxxx"
                              title="2,3자리-3,4자리-4자리"></td></tr>
		<tr><th>주소</th><td><input type="text" name="address" value="${member.address }" ></td></tr>
			<tr><th>질문</th><td>
				<select name="question">
						<option value="0" selected="selected">질문을 선택하세요</option>
						<option value="1">아버님은 무슨일 하시니?</option>
						<option value="2">연봉이 어떻게 되니?</option>
						<option value="3">차는 있니?</option>
						<option value="4">문제 있는 장기 있니?</option>
				</select></td></tr>
				
			<tr><th>답변</th><td><input type="text" name="answer" value="${member.answer }" required></td></tr>
		<tr><th>추천인</th><td><input type="text" name="recommender" value="${member.recommender }" ></td></tr>
		<tr><th>가입일자</th><td>${member.joinDate }</td></tr>
		<tr><th>최종접속일</th><td>${member.regDate }</td></tr>
		
		<tr style="background-color: #eee;">
			<td colspan="2">
			<input class="btn" type="button" value="목록" onclick="location.replace('memberToListAdmin.do')">
			<input class="btn" type="submit" value="확인" >
			<input class="btn" type="button" value="취소" onclick="location.replace('memberToDetailAdmin.do?memberId=${member.memberId}')"> </td></tr>
		
	</table>
	
</form>
									</div>
									<div class="col-lg-3"></div>
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