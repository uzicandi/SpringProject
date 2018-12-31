<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<%@ include file="../partials/head.jsp"%>

<title>AdminLTE 2 | Starter</title>

<script type="text/javascript">
	$(function() {
		var keyword = "${keyword}";
		console.log(keyword);
		$("#keyword").val(keyword);

	})
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	CKEDITOR.config.width = '1000px';
	CKEDITOR.config.height = '600px';
	$(function() {
		CKEDITOR
				.replace(
						'content',
						{
							filebrowserUploadUrl : '${pageContext.request.contextPath }/adm/fileupload.do'
						});
	});
</script>
</head>

<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header><%@ include file="../partials/header.jsp"%></header>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					게시판 <small>게시글 수정</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="home.do"><i class="fa fa-dashboard"></i> BMW</a></li>
					<li class="active">게시판</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">


				<%--  <%@ include file="member/LoginView.jsp" %> --%>
				<!--------------------------
        | Your Page Content Here |
        -------------------------->

				<div class="container">
					<form action="BoardUpdate.do" method="post" name="frm">
						<input type="hidden" name="boardNo" value="${SDBBoard.boardNo }">

						<table style="border-spacing: 1px">
							<tr>
								<th style="width: 10%">카테고리</th>
								<td style="width: 90%"><select name="boardCategory">
										<option value="자유">자유</option>
										<option value="정보">정보</option>
										<option value="QnA">QnA</option>
								</select>
									<p></td>
							</tr>
							<tr>
								<th><p>제목</th>
								<td><input class="form-control" type="text" name="title"
									required="required" style="width: 60%"
									value="${SDBBoard.title }">
									<p></td>
							</tr>
							<tr>
								<td colspan="2"><p>
										<textarea rows="30" cols="30" name="content">${SDBBoard.content }</textarea>
									<p></td>
							</tr>
							<tr>
								<td>
									<button type="submit" class="btn btn-block btn-primary btn-lg"
										style="width: 100px">수정</button>
								</td>
								<td style="padding-left: 2%">
									<button type="button" class="btn btn-block bg-maroon btn-lg"
										onclick="history.go(-1)" style="width: 100px">취소</button>
								</td>
							</tr>
						</table>
					</form>
				</div>

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer><%@ include file="../partials/footer.jsp"%></footer>

	</div>
	
</body>
</html>
