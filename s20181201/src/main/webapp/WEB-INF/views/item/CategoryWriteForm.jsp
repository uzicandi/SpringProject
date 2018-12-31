<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript">
	function chk() {
		if (!frm.name.value) {
			alert("이름을 입력한 후에 확인하세요");
			frm.name.focus();
			return false;
		} else {
			location.replace("CategoryNameConfirm.do?name=" + frm.name.value);
		}
	}

	$(document).ready(function() {
		$.ajax({
			url : "CategoryAddParent.do",
			dataType : "html",
			error : function() {
				alert("안되 다시햇");
			},
			success : function(data) {
				$('#CategoryAddParent').html(data);
			}
		});
	});
</script>
<style>
.content {
	margin: auto;
	text-align: center;
}

th{
	width: 150px;
}
</style>
</head>
<body class="skin-red-light sidebar-mini">
	<div class="wrapper">
		<%@ include file="../partials/header.jsp"%>
		<div class="content-wrapper">
			<section class="content-header">
				<h1>
					Category<small>add section</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>Home</a></li>
					<li><a href="#">Category</a></li>
					<li class="active">add section</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<form action="CategoryWritePro.do" method="post" name="frm">
					<div class="box" align="center">


					</div>
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="box-body"  align="center">
							<table class="table">
								<tr>
									<th>이름</th>
									<td>
										<div class="input-group">
											<input class="form-control" type="text"
												name="name" required="required" value="${name}"> <span
												class="input-group-btn"> <input class="btn bg-maroon"
												type="button" value="중복확인" onclick="chk()">
											</span>
										</div>
									</td>
									<c:if test="${msg != null}">${msg}</c:if>

								</tr>
								<tr>
									<th>상위 카테고리</th>
									<td><c:if test="${!empty list}">
											<select name="parent" value="${parent}">
												<option value="" selected="selected" disabled="disabled">선택</option>
												<c:forEach var="list" items="${list}">
													<option value="${list.name}">${list.name}</option>
												</c:forEach>
											</select>
										</c:if></td>
								</tr>
								<tr>
									<th>공개 여부</th>
									<td><input type="radio" name="isPublic" value="0">비공개
										<input type="radio" name="isPublic" value="1"
										required="required">공개</td>
								</tr>
								<tr>
									<th>분류</th>
									<td>
										<!-- 					<input type="hidden" name="division" value="0">상위카테고리 -->
										<input type="hidden" name="division" value="1">하위카테고리
									</td>
								</tr>
								<tr>
									<td colspan="2">
									<div class="input-group">
									<input class="btn bg-maroon" type="submit" value="확인">
															<!-- Trigger the modal with a button -->
									<span class="input-group-btn"><button type="button" class="btn bg-maroon"
							data-toggle="modal" data-target="#myModal">부모 카테고리 생성</button>
									</span>
						<!-- Modal -->
						<div class="modal fade" id="myModal" role="dialog">
							<div class="modal-dialog">

								<!-- Modal content -->
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">&times;</button>
										<h4 class="modal-title">부모 카테고리 생성</h4>
									</div>
									<div class="modal-body" id="CategoryAddParent"></div>
									<div class="modal-footer">
										<button type="button" class="btn bg-maroon"
											data-dismiss="modal" id="save">저장</button>
										<button type="button" class="btn bg-maroon"
											data-dismiss="modal">닫기</button>
									</div>
								</div>
							</div>
						</div>
						</div>
									</td>
										
								</tr>
							</table>
						</div>
					</div>
					<div class="col-md-3"></div>


				</form>
			</section>

		</div>
	</div>
</body>
</html>

