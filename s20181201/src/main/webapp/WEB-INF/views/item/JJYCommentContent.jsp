<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">

	// 비동기 통신 Ajax를 setting
	$.ajaxSetup({
		type : "POST",
		async : true,
		dataType : "json",
		error : function(xhr) {
			console.log("error html = " + xhr.statusText);
		}
	});

	$(function() {
		$('#commentWrite').click(function() {
			$.ajax({
				url : 'JJYCommentWrite.do',
				// data: {}에서는 EL을 ""로 감싸줘야 함, 나머지는 그냥 사용
				data : {
					mainNo : "${comm.subNo}",
					content : $('#commentContent').val(),
					ref : "${comm.mainNo}",
					ref_Table : "${ref_Table}",
					isPublic : $('input[name=isPublic]:checked').val(),
					memberId : "${userId}"
				},
				beforeSend : function() {
					console.log("시작 전...");
				},
				complete : function() {
					console.log("완료...!");
				},
				success : function(data) {
					// 기존에 없어도 진행됬으나... 갑자기 안되는 관계로
					// data를 JSON.parse()를 통하여 JSON형태로 변환시켜줌....
// 					var data = JSON.parse(data);
						console.log("Write data --> " + data.result);
					if (data.result == 1) {
						console.log("comment가 정상적으로 입력!!");
						$('#commentContent').val("");
						showHtml(data.comments, 1); // 마지막 인자 : commPageNum
					}
				}
			});
		});
	});

	function showHtml(data, commPageNum) {
		commPageNum = parseInt(commPageNum);
		console.log('showhtml');
		console.log("cnt -> " + `${comm.commentCount}`);
		console.log("**** 로그인 ID --> " + '${sessionId == "apple"}');
		var cnt = ${comm.commentCount};
		let html = "<table class='table table-striped table-bordered' style='margin-top: 10px;'><tbody>";
		$.each(data, function(index, comms) {
					console.log('showhtml...' + (index+1) + '   memberId => ' + comms.memberId);

							html += "<tr align='center' id='commt" + (index + 1) + "'>";
							html += "<td width='70'>" + (index + 1) + "</td>";
							
	
							if(comms.isPublic == '1' || `${grade == 0}` || '${sessionId}' == comms.memberId) {
								html += "<td>" + comms.content + "</td>";	
							} else {
								html += "<td>비밀 댓글 입니다.</td>";	
							}
							
							html += "<td width='150'>" + comms.memberId + "</td>";
// 							let presentDay = decoRegDate(comms.regDate);
							let presentDay = comms.regDate;
							html += "<td width='200'>" + presentDay + "</td>";
 							
							if('${userId}' == comms.memberId) {
								html += "<td width='200'>";
								html += "<input type='button' value='수정' onclick='updateForm(`commt"
									 	+ (index + 1) + "`,"
									+ comms.mainNo + ","
									+ comms.subNo + ","
									+ commPageNum + ")'>";
								html += "<input type='button' value='삭제' onclick='deleteComment("
									+ comms.mainNo + ","
									+ comms.subNo + ","
									+ commPageNum + ")'>";
								html += "</td>";
							} else if (`${grade == 0}`) {
								html += "<td width='200'>";
								html += "<input type='button' value='삭제' onclick='deleteComment("
									+ comms.mainNo + ","
									+ comms.subNo + ","
									+ commPageNum + ")'>";
								html += "</td>";
							}

							html += "</tr>";
		});
		html += "</tbody></table>";
		// commentCount는 동기화되어 값을 받아오기 때문에 댓글 insert에 즉각적으로 처리x
		console.log("cnt... -> " + `${comm.commentCount}`);
		if (`${comm.commentCount}` > commPageNum * 10) {
			var nextPageNum = commPageNum + 1;
			if (commPageNum != 0) {
				html += "<input type='button' class='btn btn-default' onclick='getComment("
						+ nextPageNum + ", event)' value='다음 comment 보기'>";
			}
		}

		$('#showComment').html(html);
		$("#commentContent").val("");
		$("#commentContent").focus();
	};

	function getComment(commPageNum, event) {
		$.ajax({
			url : 'JJYCommentRead.do',
			data : {
				commPageNum : commPageNum * 10,
				mainNo : "${comm.subNo}",
				ref : "${comm.mainNo}",
				ref_Table : "${ref_Table}"
			},
			beforeSend : function() {
				console.log("읽어오기 전...");
			},
			complete : function() {
				console.log("읽어오기 완료...!");
			},
			success : function(data) {
// 				var data = JSON.parse(data);
				console.log("comment를 정상적으로 조회!!");
				console.log("commPageNum --> " + commPageNum);
				console.log("ReadDate --> " + data);
				showHtml(data, commPageNum);

// 				if (commPageNum != 0) {
// 					let position = $("#showComment table tr:last").position();
// 					$('html, body').animate({scrollTop : position.top}, 400); // 두번째 param은 스크롤 이동하는 시간	
// 				}
			}
		});
	};

	function toggleName() {
		if ($('#commentRead').val() == '댓글 보기') {
			getComment(1, event);
			$('#commentRead').val('댓글 숨기기');
		} else if ($('#commentRead').val() == '댓글 숨기기') {
			getComment(0, event);
			$('#commentRead').val('댓글 보기');
		}
	};

	function updateForm(id, mainNo, subNo, commPageNum) {
		$.ajax({
			url : 'JJYCommentUpdateForm.do',
			data : {
				mainNo : mainNo,
				subNo : subNo,
				ref_Table : "${ref_Table}",
				ref : "${comm.mainNo}",
				commPageNum : commPageNum,
				trId : id
			},
			beforeSend : function() {
				console.log("수정 전 데이터 로딩 중...");
			},
			complete : function() {
				console.log("수정 전 데이터 로딩 완료...!");
			},
			success : function(data) {
// 				var data = JSON.parse(data);
				console.log("comment를 정상적으로 로딩!!");
				console.log("updateForm data -> " + data);

				let html = "<td>"+data.comm.memberId+"</td><td colspan='3'><textarea class='form-control' rows='1' id='updateContent' placeholder='" + data.comm.content + "' style='width: 100%;'></textarea></td>";
				html += "<td><input type='button' value='수정' onclick='updateCommentPro(" + data.comm.mainNo + "," + data.comm.subNo + "," + data.comm.commPageNum + ")'></td>";
				var data1 = '#'+data.trId;	// trId
				$(data1).html(html);
			}
		});

	};

	function updateCommentPro(mainNo, subNo, commPageNum) {
		$.ajax({
			url : 'JJYCommentUpdatePro.do',
			data : {
				mainNo : mainNo,
				subNo : subNo,
				ref : "${comm.mainNo}",
				ref_Table : "${ref_Table}",
				commPageNum : commPageNum,
				content : $('#updateContent').val()
			},
			beforeSend : function() {
				console.log("수정 전...");
			},
			complete : function() {
				console.log("수정 완료...!");
			},
			success : function(data) {
				var data = JSON.parse(data);
				console.log("comment를 정상적으로 수정!!");
				getComment(data, event);
			}
		});
	};

	function deleteComment(mainNo, subNo, commPageNum) {
		$.ajax({
			url : 'JJYCommentDelete.do',
			data : {
				mainNo : mainNo,
				subNo : subNo,
				ref : "${comm.mainNo}",
				ref_Table : "${ref_Table}",
				commPageNum : commPageNum
			},
			beforeSend : function() {
				console.log("삭제 전...");
			},
			complete : function() {
				console.log("삭제 완료...!");
			},
			success : function(data) {
// 				var data = JSON.parse(data);
				console.log("comment를 정상적으로 삭제!!");
				getComment(data, event);
			}
		});
	};

</script>

</head>
<body>

<div class="input-group" role="group" aria-label="..." style="margin-top: 10px; width: 100%;">
		<textarea class="form-control" rows="3" id="commentContent" placeholder="댓글을 입력하세요." style="width: 100%;"></textarea>
		<div class="btn-group btn-group-sm" role="group" aria-label="...">
			<c:if test="${userId == null}">
				<input type="button" class="btn btn-default" value="댓글 쓰기" disabled="disabled">
			</c:if>
			<c:if test="${userId != null}">
				<input type="button" class="btn btn-default" value="댓글 쓰기" id="commentWrite">
			</c:if>
			<input type="button" class="btn btn-default" value="댓글 보기" onclick="toggleName()" id="commentRead">
		</div>
		<input type="radio" name="isPublic" value="1" checked="checked">공개
		<input type="radio" name="isPublic" value="0">비공개
	</div>


	<!-- Comment 태그 추가 -->
	<div role="group" aria-label="..." style="margin-top: 10px; width: 100%;">
		<div id="showComment" style="text-align: center;"></div>
	</div>
	
</body>
</html>