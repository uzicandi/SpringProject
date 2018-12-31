<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../partials/head.jsp"%>
<title>게시판</title>

<script>
	// Get the modal
	var modal = document.getElementById('id01');

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}

	/* $(function() {
	 $("boardComment").load('SDBCommentContent.do?boardNo=${board.boardNo }&kinds=1');
	 }); */

	$(function() {
		$("boardComment")
				.load(
						'JJYCommentContent.do?mainNo=${SDBBoard.boardNo }&subNo=${SDBBoard.boardNo }&kinds=1');
	});

	$.ajaxSetup({
		type : "POST",
		async : true,
		dataType : "json",
		error : function(xhr) {
			console.log("error html = " + xhr.statusText);
		}
	});

	$(function() {
		$("#InsertClip").click(function() {
			var boardNo = $("#boardNo").val();
			$.ajax({
				async : true,
				type : 'POST',
				data : boardNo,
				url : "BoardContentClipInsert.do",
				dataType : "json",
				contentType : "application/json; charset=UTF-8",
				success : function(data) {
					if (data.chkClip != 0) {
						alert("이미 스크랩 된 게시글 입니다.");
					} else {
						alert("게시글이 스크랩 되었습니다.");
					}
				},
				error : function(error) {

				}
			});
		});
	});
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
					게시글 <small></small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="home.do"><i class="fa fa-dashboard"></i> BMW</a></li>
					<li class="active">게시판</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid">

				<!--------------------------
        | Your Page Content Here |
        -------------------------->

				<div class="container" style="width: 80%">

					<h1>${SDBBoard.title }</h1>

					<div class="progress xs">
						<div class="progress-bar progress-bar-blue" style="width: 100%;"
							align="center"></div>
					</div>

					<div style="margin-bottom: 50px">
						<table style="width: 100%">
							<tr>
								<td style="width: 50%">작성자 &nbsp; | &nbsp;
									${SDBBoard.memberId }</td>
								<td style="text-align: right">조회수 &nbsp; | &nbsp;
									${SDBBoard.hits } &nbsp; &nbsp;&nbsp;&nbsp; 등록일 &nbsp; | &nbsp;
									${SDBBoard.regDate }</td>
							</tr>
						</table>
					</div>

					<div style="margin-bottom: 250px">${SDBBoard.content }</div>

					<table style="width: 100%">
						<tr>
							<td><button class="btn btn-block btn-primary btn-lg"
									style="width: 120px" onclick="location.href='BoardList.do'">목록</button></td>
							<td style="width: 10%"><c:if
									test="${ssMap.get('nickname') == SDBBoard.memberId || ssMap.get('grade') == '0'}">
									<button class="btn btn-block btn-primary btn-lg"
										onclick="location.href='BoardUpdateForm.do?boardNo=${SDBBoard.boardNo}'">수정</button>
								</c:if></td>
							<td style="width: 10%"><c:if
									test="${ssMap.get('nickname') == SDBBoard.memberId || ssMap.get('grade') == '0'}">
									<button type="button" class="btn btn-block btn-primary btn-lg"
										data-toggle="modal" data-target="#modal-default">삭제</button>
								</c:if></td>
							<td style="width: 10%" align="right"><button
									class="btn btn-block btn-primary btn-lg" id="InsertClip"
									onclick="InsertClip">스크랩</button></td>
						</tr>

					</table>


					<div style="margin-top: 30px">
						댓글부분
						<boardComment></boardComment>
					</div>


					<!-- 삭제버튼 모달 -->

					<div class="modal fade" id="modal-default">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header" align="center">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" style="color: red">
										<b> </b>
									</h4>
								</div>
								<div class="modal-body" align="center" style="margin-top: 20px">
									<p style="font-size: 20px">삭제 하시겠습니까?</p>
								</div>
								<div class="modal-footer">
									<form class="modal-content animate"
										action="BoardDelete.do?boardNo=${SDBBoard.boardNo }"
										method="post">
										<button type="submit"
											class="btn btn-primary btn-lg pull-center">삭제</button>
										<button type="button"
											class="btn btn-default btn-lg pull-center"
											data-dismiss="modal">취소</button>
									</form>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- modal 끝 -->
					<input type="hidden" id="boardNo" value="${SDBBoard.boardNo }">
				</div>
			</section>

		</div>


		<!-- /.content -->

		<!-- /.content-wrapper -->

		<footer><%@ include file="../partials/footer.jsp"%></footer>
	</div>

	<div class="control-sidebar-bg"></div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->


</body>
</html>