<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category List View</title>
<script type="text/javascript">

	 $(function(){
	        $('#delete').click(function() {
	            
	        	var categoryNo = $('#cateNum').val();
	        	var sendData = "categoryNo=" + categoryNo;

	            console.log('sendData -> ' + sendData);
	            
	            $.post('CategoryDelete.do', sendData, function(msg){

	            	console.log('msg1 -> ' + msg);
	            	
	                if(msg == 1) {
			            // 부모창 리프레시 시켜주기
			            window.location.reload();
	                };
	            });
	    	});
 		 });

	 function del(cateNum) {
		console.log('test -> ' + cateNum);
		$(".modal-body #cateNum").val(cateNum);
	 }
	 
	 function nameSearch(frm) {
		 if(frm.keyword.value == "") {
			 alert("검색 단어를 입력하세요.");
			 frm.keyword.focus();
			 return;
		 }
		 frm.submit();
	 }
	 
</script>
<style>
	
	.wrapper {
		background-color: #ecf0f5;
	}
	.box-box-solid {
		background-color: #ecf0f5;
	}
	
	table {
		width: 80%;
		margin: 0 auto;
	}	
	
	th, td {
    border-bottom: 1px solid #444444;
    padding: 10px;
    text-align: center;
  }
	
</style>
</head>
<body class="skin-red-light sidebar-mini" style="height: auto; min-height: 100%;">
<div class="wrapper" style="height: auto; min-height: 100%;">
<%@ include file="../partials/header.jsp" %>
<div class="content-wrapper">
	<section class="content-header">
		<h1>Category<small>List View</small></h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i>Home</a></li>
				<li><a href="#">Category</a></li>
				<li class="active">List View</li>
			</ol>
	</section>

	<!-- Main content -->
    <section class="content">

    <!-- Default box -->
    <div class="box">
        <div class="box-header with-border"  style="text-align: center;">
          
          <div class="box-tools pull-right">
          	<button type="button" class="btn btn-box-tool" data-widget="collapse" data-toggle="tooltip"
                    title="Collapse">
            <i class="fa fa-minus"></i></button>
            <button type="button" class="btn btn-box-tool" data-widget="remove" data-toggle="tooltip" title="Remove">
            <i class="fa fa-times"></i></button>
          </div>
        </div>
        <div class="box-body" style="height: 50px;">
        	<a href="CategoryWriteForm.do"><button class="btn bg-maroon" style="position: absolute; right: 20px;">카테고리 등록</button></a>
        	<form name="search" action="CategoryListView.do" method="post">
							<div class="input-group">
								<input class="form-control" type="search"
									name="keyword" placeholder="카테고리명을 입력하세요"
									style="width: 300px; position:absolute; left: 140px;">
								<span class="input-group-btn"> <input
									class="btn bg-maroon" type="button" value="검색"
									onclick="nameSearch(form)"
									style="position: absolute; left: 450px;">
								</span>

							</div>
						</form>
        </div>
      </div>
      <!-- /.box -->

	<div>
		<div class="row docs-premium-template">
			<div class="box-solid">
				<table class="table table-bordered">
				<tr>
					<th class="title1">카테고리 번호</th>
					<th class="title2">카테고리 이름</th>
					<th class="title3">상위 카테고리</th>
					<th class="title4">공개여부</th>
					<th class="title5">분류</th>
					<th class="title6">수정/삭제</th>
				</tr>
				<c:set var="num" value="${pg.total-pg.start+1}"></c:set>
				<c:if test="${total > 0 }">
				<c:forEach var="cate" items="${list}">
				<tr>
					<td>${cate.categoryNo}</td>
					<td><c:if test="${cate.division == 1}"> ─ </c:if>${cate.name}</td>
					<td>${cate.parent}</td>
					<td><c:if test="${cate.isPublic == 0}">비공개</c:if>
						<c:if test="${cate.isPublic == 1}">공개</c:if>
					</td>
					<td><c:if test="${cate.division == 0}">상위카테고리</c:if>
						<c:if test="${cate.division == 1}">하위카테고리</c:if>
					</td>
					<td>
						<input type="button" value="수정" class="btn bg-maroon" onclick="location.href='CategoryUpdateForm.do?categoryNo=${cate.categoryNo}'">
						<!-- <div class="container"> -->
						<!-- Trigger the modal with a button -->
						<button type="button" class="btn bg-maroon"
							data-toggle="modal" data-target="#myModal" onclick="del(${cate.categoryNo})">삭제</button>
						<!-- Modal -->
						<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog">

						<!-- Modal content -->
						<div class="modal-content">
							<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">카테고리 삭제</h4>
							</div>
										
							<div class="modal-body">
							<h2>정말 삭제하시겠습니까?</h2>
							<input type="hidden" id="cateNum">
							</div>
									
							<div class="modal-footer">
							<input type="button" class="btn btn-default"
								data-dismiss="modal" id="delete" value="삭제">											
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>
							</div>
						</div>
						</div>
						</div>
						<!-- </div> -->

					</td>
				</tr>
				<c:set var="num" value="${num-1}"></c:set>	
			</c:forEach>
		</c:if>
		
		<c:if test="${total == 0 }">
		<tr>
			<td colspan=7>데이터가 없음</td>
		</tr>
		</c:if>
		</table>
		<br><br>

	<div style="text-align: center;">
	<c:choose>
		<c:when test="${cate.keyword == null}">
			<c:if test="${pg.startPage > pg.pageBlock}">
				<a href="CategoryListView.do?currentPage=${pg.startPage - pg.pageBlock}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
				<a href="CategoryListView.do?currentPage=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${pg.endPage < pg.totalPage}">
				<a href="CategoryListView.do?currentPage=${pg.startPage + pg.pageBlock}">[다음]</a>
			</c:if>
		</c:when>
		<c:otherwise>
			<c:if test="${pg.startPage > pg.pageBlock}">
				<a href="CategoryListView.do?currentPage=${pg.startPage - pg.pageBlock}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${pg.startPage}" end="${pg.endPage}">
				<a href="CategoryListView.do?currentPage=${i}">[${i}]</a>
			</c:forEach>
			<c:if test="${pg.endPage < pg.totalPage}">
				<a href="CategoryListView.do?currentPage=${pg.startPage + pg.pageBlock}">[다음]</a>
			</c:if>			
		</c:otherwise>
	</c:choose>	
	</div>
	</div>
</div>
</div>

    </section>
    <!-- /.content -->    
</div>
</div>
		
<%@ include file="../partials/footer.jsp" %>

</body>
</html>