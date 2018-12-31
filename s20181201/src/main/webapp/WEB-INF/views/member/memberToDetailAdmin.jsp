<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

		<%@ include file="../partials/header.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					회원정보 수정(관리자) <small>Member Update (Admin)</small>
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
									<div class="col-lg-9 table-responsive" style="width: 50%; text-align: center;">

										<table class="table table-striped">
											<tr>
												<th>아이디</th>
												<td>${member.memberId }</td>
											</tr>
											<tr>
												<th>닉네임</th>
												<td>${member.nickname }</td>
											</tr>
											<tr>
												<th>이름</th>
												<td>${member.name }</td>
											</tr>
											<tr>
												<th>생년월일</th>
												<td>${member.birth }</td>
											</tr>
											<tr>
												<th>성별</th>
												<td><script type="text/javascript">
													sexArray = new Array("남성","여성")
													i = ${member.sex}
													{document.write(sexArray[i]);}
												</script></td>
											</tr>
											<tr>
												<th>피부타입</th>
												<td><script type="text/javascript">
													skintypeArray = new Array("건성", "중성", "지성","복합성")
													i = ${member.skinType}
													{document.write(skintypeArray[i]);}
												</script></td>
											</tr>
											<tr>
												<th>피부고민</th>
												<td><script type="text/javascript">
													skinComplexArray = new Array("해당없음", "아토피","여드름", "민감성")
													i = ${member.skinComplex}
													{document.write(skinComplexArray[i]);}
												</script></td>
											</tr>
											<tr>
												<th>회원등급</th>
												<td><script type="text/javascript">
													gradeArray = new Array("관리자", "판매자","일반인", "승인대기","휴면상태", "탈퇴")
													i = ${member.grade}
													{document.write("["+ i+ "]="+ gradeArray[i]);}
												</script></td>
											</tr>
											<tr>
												<th>이메일</th>
												<td>${member.email }</td>
											</tr>
											<tr>
												<th>메일수신여부</th>
												<td><script type="text/javascript">
													isEmailArray = new Array("수신거부", "수신")
													i = ${member.isEmail}
													{document.write(isEmailArray[i]);}
												</script></td>
											</tr>
											<tr>
												<th>전화번호</th>
												<td>${member.phone }</td>
											</tr>
											<tr>
												<th>주소</th>
												<td>${member.address }</td>
											</tr>
											<tr>
												<th>질문</th>
												<td>${member.question }</td>
											</tr>
											<tr>
												<th>답변</th>
												<td>${member.answer }</td>
											</tr>
											<tr>
												<th>추천인</th>
												<td>${member.recommender }</td>
											</tr>
											<tr>
												<th>가입일자</th>
												<td>${member.joinDate }</td>
											</tr>
											<tr>
												<th>최종접속일</th>
												<td>${member.regDate }</td>
											</tr>
				
											<tr style="background-color: #eee;">
											
												<td colspan="2">
												<input class="btn" type="button" value="목록" onclick="location.replace('memberToListAdmin.do')">
												<input class="btn" type="button" value="수정" onclick="location.replace('memberToUpdateAdminForm.do?memberId=${member.memberId}')">
												</td>
											
											</tr>

										</table>

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

		<%@ include file="../partials/footer.jsp"%>

		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->


</body>

</html>