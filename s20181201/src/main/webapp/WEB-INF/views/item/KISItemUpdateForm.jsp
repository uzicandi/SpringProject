<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Item Update View</title>
<script type="text/javascript">	
	// 디비에 저장된 카테고리에 해당하는 카테고리명 selected 시킴
	$(function () {		
		var cateNo = ${item.categoryNo};
		var selectNo = ${cateNoSelect.categoryNo};
		
		var exists = 0 != $('#parentCate option[value='+selectNo+']').length;
		console.log("exists --> " + exists);
		if (exists == true) {
			$('#parentCate option[value='+selectNo+']').attr('selected', 'selected');
		}
		
		console.log("item에 담긴 카테고리번호 --> " + cateNo);
		console.log("selectNo에 담긴 카테고리번호 --> " + selectNo);
		parentCate_val = $("#parentCate").attr;
	});

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
	
	function deleteFile(tdId, filesNo) {
		$.ajax({
			url : 'uploadFileDelete.do',
			type : "POST",
			dataType : "json",
			data : {
				mainNo : "${item.itemNo}", 
				subNo : 0,
				ref_Table : "ITEMINFO",
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
//	 			var data = JSON.parse(data);
					
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
	.form-control {
		width: 50%;
	}
	.title {
		width: 20%;
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
			<section class="content container-fluid" style="width:70%;">
	<h2>제품 수정</h2>
	<form id="frm" name="frm" action="KISItemUpdate.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="memberId" value="${item.memberId }">
		<input type="hidden" name="itemNo" value="${item.itemNo}">
		<div class="box">
			<div class="box-body">
				<table class="table">
					<tr>
						<th class="title">제품번호</th>
						<td>${item.itemNo}</td>
					</tr>
					<tr>
						<th class="title">카테고리</th>
						<td>
							<select id="parentCate" name="categoryNo" required="required" style="height:31px;">
								<c:forEach var="catelist" items="${catelist}">
									<option value="${catelist.categoryNo}">${catelist.name}/${catelist.categoryNo}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th class="title">브랜드</th>
						<td><input class="form-control" type="text" name="brand" value="${item.brand}"
							required="required"></td>
					</tr>
					<tr>
						<th class="title">제품명</th>
						<td><input class="form-control" type="text" name="name" value="${item.name}"
							required="required"></td>
					</tr>
					<tr>
						<th class="title">재고</th>
						<td><input class="form-control" type="number" name="itemStock" value="${item.itemStock}"></td>
					</tr>
					<tr>
						<th class="title">제품정보</th>
						<td><textarea class="form-control" rows="10" cols="40" name="info">${item.info}
									</textarea></td>
					</tr>
					<tr>
						<th class="title">가격</th>
						<td><input class="form-control" type="text" name="price" value="${item.price}"></td>
					</tr>
					<tr>
						<th class="title">공개여부</th>
						<td><input type="radio" name="isPublic" value="1"
							checked="checked" required="required">공개 <input
							type="radio" name="isPublic" value="0" required="required">비공개
						</td>
					</tr>
					<tr>
						<th class="title">등록된 성분</th>
						<td>
							<c:forEach var="mapp" items="${mapplist}">
								${mapp.name} /
							</c:forEach>
						</td>
					</tr>
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
							<textarea class="form-control" rows="3" cols="40" name="ingtName" id="ingtName" readonly="readonly"></textarea>
						</td>
					</tr>
					<tr>
						<th class="title">해시태그</th>
						<td><input class="form-control" type="text" name="htag"></td>
					</tr>
					<!-- 제품사진 -->
					<tr>
						<th class="title">등록된 이미지</th>
						<td id="picture${startNum }">															
							<c:forEach var="saveFile" items="${item.saveFileList }">
								<c:set var="startNum" value="${startNum + 1 }"></c:set>
									<span style=" float:left; margin-right: 20px;">
									<img title="${saveFile.savedFileName }" src="${saveFile.filePath }" width="100px"><br>
									<input class="btn btn-default" type="button" name="deleteFiles${startNum }" value="파일삭제${startNum }"
										onclick="deleteFile('#picture${startNum}',${saveFile.filesNo})"></span>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th class="title">제품사진 첨부</th>
						<td>
							<input class="btn btn-default" type="file" name="saveFiles"><br>
							<input class="btn btn-default" type="file" name="saveFiles"><br>
							<input class="btn btn-default" type="file" name="saveFiles">
						</td>
					</tr>	
			<tr>
				<td colspan="2">
					<input class="btn btn-default" type="submit" value="수정하기" onclick="saveBefore()">
					<input type="button" class="btn1 btn btn-default" value="취소" onclick="history.go(-1)"></td>
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