<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Item Write View</title>
<script type="text/javascript">
	// 등록할 성분 선택하는 팝업 띄움
	function openPop() {
		$.ajax({
			url : "KISPopIngtList.do",
			dataType : "html",
			error : function() {
				alert("통신실패!!");
			},
			success : function(data) {
				$('#poplist').html(data);
			}
		});
	}
	
	function saveBtn() {
		var NoArr = new Array();
		var NameArr = new Array();

		$("input:checkbox[name=myCheck]:checked").each(function() {
			NoArr.push($(this).val());
			var data = $(this).val();
			NameArr.push($("#chk_" + data).val());
		});
		var receiveValues = document.getElementById("receiveValues").value;
		var ingtName = document.getElementById("ingtName").value;

		document.getElementById("receiveValues").value = NoArr;
		document.getElementById("ingtName").value = NameArr;
	}
	
	$(function () {
		// 삭제시키는법 몰라서 한번만 누를수 있게 함
		$(".chktag").one("click", function () {
		$(this).toggleClass("disabled"); // 버튼 클릭시 배경색 지정 토글
		var txt = $(this).val();
		console.log(txt);
		$("#taghere").val($("#taghere").val()+txt);
		})		
	})
</script>
<style type="text/css">
	.modal-title{
		float : left;
	}
	#modal-content {
/* 		position: relative; */
/* 		margin-left: -35%; */
/* 		margin-top : 15%; */
/* 		overflow : auto; */
/* 		width: auto;		 */
	}
	.bg {
		background: #777;
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
					Page Header <small>Optional description</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
					<li class="active">Here</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content container-fluid" style="width:80%;">
	<h2>제품 등록</h2>
	<form action="KISItemWrite.do" name="frm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="memberId" value="${ssMap.get('memberId') }">
		<div class="box">
			<div class="box-body">
				<table class="table">
			<tr>
				<th width="200">카테고리</th>
				<td>
					<select id="parentCate" name="categoryNo" required="required" onChange="changeSel()" style="height:31px; ">
						<c:forEach var="cate" items="${catelist}">
							<option value="${cate.categoryNo}">${cate.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th class="title">브랜드</th>
				<td><input class="form-control" style="width:60%;" type="text" name="brand" required="required"></td>
			</tr>
			<tr>
				<th class="title">제품명</th>
				<td><input class="form-control" style="width:60%;" type="text" name="name" required="required"></td>
			</tr>
			<tr>
				<th class="title">재고</th>
				<td><input class="form-control" style="width:60%;" type="number" name="itemStock" required="required"></td>
			</tr>
			<tr>
				<th class="title">제품정보</th>
				<td><textarea class="form-control" style="width:60%;" rows="10" cols="40" name="info"></textarea></td>
			</tr>
			<tr>
				<th class="title">가격</th>
				<td><input class="form-control" style="width:60%;" type="text" name="price" value="0"></td>
			</tr>
			<tr>
				<th class="title">공개여부</th>
				<td><input class="form-control" style="width:60%;" type="radio" name="isPublic" checked="checked"
					value="1" required="required">공개 <input type="radio"
					name="isPublic" value="0" required="required">비공개</td>
			</tr>
			<!-- 성분등록하기 -->
			<tr>
				<th class="title">성분등록</th>
				<td>
					<input type="hidden" name="receiveValue" id="receiveValues">					
					<div class="container">
					<!-- Trigger the modal with a button -->
					<button type="button" class="btn bg-maroon" data-toggle="modal" data-target="#myModal" onClick="openPop(); return false;">성분 등록</button>
					<!-- Modal -->
				    <div class="modal fade" id="myModal" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div id="modal-content" class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title" >성분 리스트</h4>
					        </div>
					        <div id="poplist" class="modal-body">
					          <p>Some text in the modal.</p>
					        </div>
					        <div class="modal-footer">
					          <button type="button" class="btn btn-default" onclick="saveBtn()">저장</button>
					          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
					        </div>
					      </div>
					      
					    </div>
					  </div>					  
					</div>
										
					<br>
					<textarea class="form-control" style="width:60%;" rows="3" cols="40" name="ingtName" id="ingtName" readonly="readonly"></textarea>
				</td>
			</tr>
			<tr>
				<th class="title">해시태그</th>
				<td>
					<input class="form-control" style="width:60%;" type="text" id="taghere" name="htag">
					<br><input type="button" class="chktag btn bg-maroon" name="chkval" value="#건성">
					<input type="button" class="chktag btn bg-maroon" name="chkval" value="#중성">
					<input type="button" class="chktag btn bg-maroon" name="chkval" value="#지성">
					<input type="button" class="chktag btn bg-maroon" name="chkval" value="#복합성">
					<input type="button" class="chktag btn bg-maroon" name="chkval" value="#10대">
					<input type="button" class="chktag btn bg-maroon" name="chkval" value="#20대">
					<input type="button" class="chktag btn bg-maroon" name="chkval" value="#30대">
					<input type="button" class="chktag btn bg-maroon" name="chkval" value="#40대 이상">
				</td>
			</tr>
			<!-- 제품사진 -->
			<tr>
				<th class="title">사진1</th>
				<td><input type="file" name="saveFiles"></td>
			</tr>
			<tr>
				<th class="title">사진2</th>
				<td><input type="file" name="saveFiles"></td>
			</tr>
			<tr>
				<th class="title">사진3</th>
				<td><input type="file" name="saveFiles"></td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="btn bg-maroon" type="submit" value="확인" onclick="btnSubmit()">
					<input class="btn bg-maroon" type="button" value="취소" onclick="history.go(-1)">
				</td>
			</tr>
		</table>
		</div>
		</div>
	</form>
</section>
			
			<!-- /.content -->
		</div>
	</div>
		<!-- /.content-wrapper -->

		<%@ include file="../partials/footer.jsp"%>

		<div class="control-sidebar-bg"></div>
</body>
</html>