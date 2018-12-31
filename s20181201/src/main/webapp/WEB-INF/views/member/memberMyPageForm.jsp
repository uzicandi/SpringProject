<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<style type="text/css">
* {
	box-sizing: border-box;
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

.photo2 { /* 직사각형 사진에 적용 */
	width: 100px;
	height: 100px;
	object-fit: cover;
	object-position: top;
	border-radius: 50%;
}

.photo3 { /* 정사각형 사진에 적용 */
	width: 100px;
	height: 100px;
	object-fit: cover;
	border-radius: 50%;
	text-align: center;
}
</style>
<script type="text/javascript">

	$(function() {
		if ('${result}') {
			alert("수정 완료!");
		}
	});
	
	function _submit(f) {
		if($("input:checkbox[name=mainNo]:checked").length == 0) {
			alert("삭제항목을 체크해주세요");
			return false;
		}
	    result = confirm('삭제 하시겠습니까');
	    if(result == true){
	    	if (typeof(f.elements['mainNo'].length) == 'undefined') {	
		        if (f.elements['mainNo'].checked==false) f.elements['subNo'].disabled=true;
		    } 
		    else { //다중
		        for (i=0; i<f.elements['mainNo'].length; i++){
		        	if (f.elements['mainNo'][i].checked==false)  {
		        		f.elements['subNo'][i].disabled=true;
		        	}
		        }
		    }
		    return true;
	    }else return false;
	}
	
	function delok(){
		if($("input:checkbox[name=mainNo]:checked").length == 0) {
			alert("삭제항목을 체크해주세요");
			return false;
		}
	    result = confirm('삭제 하시겠습니까');
	    if(result == true){
	    	document.getElementById('frm').submit();
	    }else  return false;
	}
	
	function scrapFirstView() {
       	$.ajax({
       		url: 'clipListForm.do?view=1&delview=0',
			type: 'GET',
			error : function() {
				alert("실패해쪄염 뿌");
			},
			success : function(data) {
				$('#showJsp').html(data);
			}
		});
 	};
 	
 	function scrapFirstView2() {
       	$.ajax({
       		url: 'clipListForm.do?view=2&delview=0',
			type: 'GET',
			error : function() {
				alert("실패해쪄염 뿌");
			},
			success : function(data) {
				$('#showJsp').html(data);
			}
		});
 	};
	
 	function scrapView(page) {
       	$.ajax({
       		url: 'clipListForm.do?currentPage='+page+'&view=1&delview=0',
			type: 'GET',
			error : function() {
				alert("실패해쪄염 뿌");
			},
			success : function(data) {
				$('#showJsp').html(data);
			}
		});
 	};
 			
 	function scrapView2(page) {
       	$.ajax({
       		url: 'clipListForm.do?currentPage='+page+'&view=2&delview=0',
			type: 'GET',
			error : function() {
				alert("실패해쪄염 뿌");
			},
			success : function(data) {
				$('#showJsp').html(data);
			}
		});
 	};
 	
 	function scrapDelView(page) {
       	$.ajax({
			url: 'clipListForm.do?currentPage='+page+'&view=1&delview=1',
			type: 'GET',
			error : function() {
				alert("실패해쪄염 뿌");
			},
			success : function(data) {
				$('#showJsp').html(data);
			}
		});
 	};  
 	
 	function scrapDelView2(page) {
       	$.ajax({
			url: 'clipListForm.do?currentPage='+page+'&view=2&delview=2',
			type: 'GET',
			error : function() {
				alert("실패해쪄염 뿌");
			},
			success : function(data) {
				$('#showJsp').html(data);
			}
		});
 	}; 
 	
 	function pointView(i) {
       	$.ajax({
       		url: 'pointHistoryForm.do?currentPage='+i,
			type: 'GET',
			error : function() {
				alert("실패해쪄염 mypage");
			},
			success : function(data) {
				$('#pointView').html(data);
			}
		});
 	};
 	 		
</script>




</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
	<!-- <div class="wrapper"> -->
	<%@ include file="../partials/header.jsp"%>

	<!-- Content Wrapper. Contains page content -->
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>My Page</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="#">MemberService</a></li>
				<!-- <li class="active">myPage</li> -->
				<li>myPage</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">

			<div class="row">
				<div class="col-md-3">

					<!-- Profile Image -->
					<div class="box">
						<div class="box-body box-profile" style="text-align: center;">
							<!--  <img class="profile-user-img img-responsive img-circle" src="../../dist/img/user4-128x128.jpg" alt="User profile picture"> -->

							<c:choose>
								<c:when test="${ssMap.get('filePath') == '0'}">
									<img src="/uploadImg/member/noimage.jpg" class=photo3>
								</c:when>
								<c:otherwise>
									<img src="${ssMap.get('filePath') }" class="photo3">
								</c:otherwise>
							</c:choose>

							<!--프로필사진-->

							<form method="POST" action="memberPictureUpload.do"
								enctype="multipart/form-data">

								<input style="text-align: center;" type="file" name="saveFiles"
									required />
								<!--  <button type="file" class="btn btn-app" name="saveFiles">
	<i class="fa fa-inbox"></i>사진불러오기 </button>-->


								<button type="submit" class="btn btn-app">
									<i class="fa fa-save"></i>사진등록
								</button>
								<!-- <input type="submit" class="btn btn-primary btn-block" value="사진 등록" /> -->
								<c:if test="${ssMap.get('filePath') != '0' }">
									<button class="btn btn-app"
										onclick="location.replace('memberPictureDelete.do')">
										<i class="fa fa-times"></i>사진삭제
									</button>
								</c:if>
							</form>
							<!--  <a href="#" class="btn btn-primary btn-block"><b>사진등록</b></a> -->
						</div>
						<!-- /.box-body -->

					</div>
					<!-- /.box -->

					<h3 class="profile-username text-center">${member.name }</h3>

					<p class="text-muted text-center">${member.nickname }</p>

					<ul class="list-group list-group-unbordered">
						<li class="list-group-item"><b>E-Mail</b> <a
							class="pull-right">${member.email }</a></li>
						<li class="list-group-item"><b>주소</b> <a class="pull-right">${member.address }</a>
						</li>
						<li class="list-group-item"><b>전화번호</b> <a class="pull-right">${member.phone }</a></li>
					</ul>




					<!-- About Me Box -->
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">About Me</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body">
							<strong><i class="fa fa-book margin-r-5"></i>성별</strong>

							<p class="text-muted">
								<script>
              		if(${member.sex} == 0) {
              			document.write('<p>남성</p>');
              		} else if (${member.sex} == 1) {
              			documnet.write('<p>여성</p>');
              		}
              	</script>
							</p>

							

							<strong><i class="fa fa-map-marker margin-r-5"></i>피부 타입</strong>

							<p class="text-muted">
								<script>
              		if(${member.skinType} == 0) {
              			document.write('<p>건성</p>');
              		} else if (${member.skinType} == 1) {
              			documnet.write('<p>중성</p>');
              		} else if (${member.skinType} == 2) {
              			documnet.write('<p>지성</p>');
              		} else if (${member.skinType} == 3) {
              			documnet.write('<p>복합성</p>');
              		}
              	</script>
							</p>

							

							<strong><i class="fa fa-pencil margin-r-5"></i>피부 컴플렉스 </strong>

							<p>
								<script>
              		if(${member.skinComplex} == 0) {
              			document.write('<p>없음</p>');
              		} else if (${member.skinComplex} == 1) {
              			documnet.write('<p>아토피</p>');
              		} else if (${member.skinComplex} == 2) {
              			documnet.write('<p>여드름</p>');
              		} else if (${member.skinComplex} == 3) {
              			documnet.write('<p>민감성</p>');
              		}
              	</script>
							</p>

							

							<strong><i class="fa fa-file-text-o margin-r-5"></i>가입일자</strong>

							<p>${member.joinDate }</p>
							
							<hr>
							
							<strong><i class="fa fa-file-text-o margin-r-5"></i>
							<a href="memberWithdrawalForm.do">탈퇴하기</a></strong>
							
							
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>

<!-- /.col -->
				<div class="col-md-9">
					<div class="nav-tabs-custom">
						<ul class="nav nav-tabs">
							<li class="active"><a href="#settings" data-toggle="tab">회원정보 수정</a></li>
							<li><a href="#activity" data-toggle="tab" onclick="scrapFirstView()">나의 스크랩 목록</a></li>
							<li><a href="#timeline" data-toggle="tab" onclick="pointView(1)">포인트 내역</a></li>
						</ul>

						<div class="tab-content">

							<div class="active tab-pane" id="settings">
								<%@ include file="UpdateForm.jsp"%>
								<c:if test="${msg == 'updateOK' }">
									<div style="color: blue">회원정보가 수정되었습니다.</div>
								</c:if>
							</div>
							<!-- /.tab-pane -->

							<div class="tab-pane" id="activity">
								 <div id="showJsp"></div> 
							</div>
							<!-- /.tab-pane -->

							<div class="tab-pane" id="timeline">
								  <div id="pointView"></div>  
							</div>
							<!-- /.tab-pane -->


						</div>
						<!-- /.tab-content -->
					</div>
					<!-- /.nav-tabs-custom -->
				</div>
				<!-- /.col 여기까진 맞음  -->




			</div>
			<!-- /.row -->

		</section>
		<!-- /.content -->


	</div>
	<!-- /.content-wrapper -->

	<%@ include file="../partials/footer.jsp"%>

	<div class="control-sidebar-bg"></div>

	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

</div>
</body>
</html>