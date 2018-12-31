<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
// 	$(function () {
// 		if('${msg != null}') {
// 			alert('${msg}');
// 		}
// 	});
	
	function updateCancle() {
// 		history.go(-1);		
		location.replace('JJYItemReviewContent.do?itemNo=${comm.mainNo}&reviewNo=${comm.subNo }&currentPage=${comm.currentPage }&currentPageRv=${comm.currentPageRv}');
	}
	
	$.ajaxSetup({
		type : "POST",
		async : true,
		dataType : "JSON",
		error : function(xhr) {
			console.log("error html = " + xhr.statusText);
		}
	});
	
	function deleteFile(tdId, filesNo) {
		$.ajax({
			url : 'uploadFileDelete.do',
			data : {
				mainNo : "${comm.mainNo}", 
				subNo : "${comm.subNo}",
				ref_Table : "REVIEW",
				filesNo : filesNo
			},
			beforeSend : function() {
				console.log("deleteFile 전...");
			},
			complete : function() {
				console.log("deleteFile 완료...!");
			},
			success : function(data) {
				console.log("deleteFile()를 정상적으로 성공!!");
// 				var data = JSON.parse(data);
				
				console.log("data -> " + data.fileName);
				if(data == 0) {
					console.log("삭제중 이상발생 -> " + data.fileName);
				} else {
					$(tdId).html(data.fileName + " >> 삭제완료");		
				}
			}
		});
	}
</script>

</head>
<body class="hold-transition skin-red-light sidebar-mini">
	<div class="wrapper">
		<header><%@ include file="../partials/header.jsp"%></header>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Page Header <small>Optional description</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">
				<div class="box  ">
					<div class="box-header with-border"></div>
					<div class="box-body">
						<h2>리뷰 수정</h2>
						<span style="color: red;"><c:if test="${msg != null }">${msg }</c:if></span>
						<form action="JJYItemReviewUpdatePro.do" method="post"
							enctype="multipart/form-data">
							<input type="hidden" name="isPublic" value="1"> <input
								type="hidden" name="mainNo" value="${comm.mainNo}"> <input
								type="hidden" name="subNo" value="${comm.subNo}"> <input
								type="hidden" name="ref_Table" value="${comm.ref_Table}">
							<input type="hidden" name="currentPage"
								value="${comm.currentPage}"> <input type="hidden"
								name="currentPageRv" value="${comm.currentPageRv}">
							<!-- memverId 받아와야함 -->
							<input type="hidden" name="memberId" value="admin">

							<table class="table">
								<tr>
									<th class="title" width="200">제품정보</th>
									<td>${comm.mainNo }</td>
								</tr>
								<tr>
									<th class="title">평점</th>
									<td colspan="3"><select name="rating"
										style="width: 100px; height: 30px;">
											<option value="1">1점</option>
											<option value="2">2점</option>
											<option value="3" selected>3점</option>
											<option value="4">4점</option>
											<option value="5">5점</option>
									</select></td>
								</tr>
								<tr>
									<th class="title">좋았던 점</th>
									<td><textarea rows="5" cols="60" name="content">${comm.content }</textarea>
									</td>
								</tr>
								<tr>
									<th class="title">아쉬웠던 점</th>
									<td><textarea rows="5" cols="60" name="content2">${comm.content2 }</textarea>
									</td>
								</tr>
								<tr>
									<th class="title">꿀 Tip</th>
									<td><textarea rows="5" cols="60" name="content3">${comm.content3 }</textarea>
									</td>
								</tr>
								<tr>
									<th class="title">사진</th>
									<td>
										<table class="table">
											<tr align="left">
												<c:forEach var="saveFile" items="${saveFileList }">
													<c:set var="startNum" value="${startNum + 1 }"></c:set>
													<td id="picture${startNum }"><img
														style="width: 500px; height: 450px;"
														title="${saveFile.savedFileName }"
														src="${saveFile.filePath }"><br> <input
														type="button" name="deleteFiles${startNum }"
														value="파일삭제${startNum }"
														onclick="deleteFile('#picture${startNum}',${saveFile.filesNo})"></td>
												</c:forEach>
											</tr>
											<!-- 제품사진 -->
											<tr>
												<td><input type="file" name="saveFiles"></td>
												<td><input type="file" name="saveFiles"></td>
												<td><input type="file" name="saveFiles"></td>
											</tr>
										</table>
									</td>
								</tr>

							</table>
							<div align="center">
								<input type="submit" value="수정완료"> <input type="button"
									value="취소" onclick="updateCancle()">
							</div>
						</form>
						<!-- 	</form> -->
					</div>
				</div>
			</section>
		</div>
	</div>


	<footer><%@ include file="../partials/footer.jsp"%></footer>
</body>
</html>