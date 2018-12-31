<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body {font-family: Arial, Helvetica, sans-serif;}

th {
	background-color: #cce8f4;
	text-align: center;
}

td {
	text-align: center;
}


select { 
	width: 200px;  
	padding: .8em .5em;  
	font-family: inherit; 
	background: url(https://farm1.staticflickr.com/379/19928272501_4ef877c265_t.jpg) no-repeat 95% 50%; 
	border: 1px solid #999; 
	border-radius: 0px; 
	-webkit-appearance: none; 
	-moz-appearance: none; 
	appearance: none; 
}



</style>
<script type="text/javascript">
	$(function(){
		if('${result}') alert("충전 완료!")
	});

	//Get the modal
	var modal = document.getElementById('id01');
	
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
	
	

	

	// 충전금액 직접입력
	function charge_change() {
		// 선택일시
		if (document.frm.charge.options[document.frm.charge.selectedIndex].value == 0) {
			//document.frm.money.disabled = true;
			$('#money').attr("readonly", true)
			document.frm.money.value = "";
		} 
		// 직접입력일시
		if (document.frm.charge.options[document.frm.charge.selectedIndex].value == 9) {
			$('#money').attr("readonly", false)
			document.frm.money.value = "";
			document.frm.money.focus();
		} 
		// 선택했을시
		else { /* (document.frm.charge.options[document.frm.charge.selectedIndex].value != '9'){ */
			//document.frm.money.disabled = true;
			$('#money').attr("readonly", true)
			document.frm.money.value = document.frm.charge.options[document.frm.charge.selectedIndex].value;
			document.frm.charge.focus();
		}
	}
	
	function chk() {
		//document.write(document.frm.money.value);
		if(document.frm.money.value == '' || document.frm.money.value == 0){
            alert("금액을 선택하세요!")
			return false;
        } else {
        	alert("충전되었습니다!")
        }
		return true;
	}

	// 직접입력 누르면 숨어잇다 나오는 인풋박스
	/* $(function(){
	      //직접입력 인풋박스 기존에는 숨어있다가
	$("#selboxDirect").hide();
	$("#selbox").change(function() {
	                //직접입력을 누를 때 나타남
			if($("#selbox").val() == "direct") {
				$("#selboxDirect").show();
			}  else {
				$("#selboxDirect").hide();
			}
		}) 

	}); */
	

</script>
</head>
<%-- <body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
  
  <%@ include file="../partials/header.jsp" %>
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Page Header
        <small>Optional description</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
        <li class="active">Here</li>
      </ol>
    </section>
 --%>
    <!-- Main content -->
    <section class="content container-fluid">
    
    <div class="container">
	<div class="row">
			<div>
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner">
						<h3>${rpoint }원</h3>

						<p>보유 포인트</p>
					</div>
					<div class="icon">
						<i class="fa fa-database"></i>
					</div>

				</div>

			</div>
			<!-- ./col -->					 	
					 	
	</div> 
	</div>


	<div class="row">
	<div class="col-xs-12 table-responsive">
	<table class="table table-striped">
	<thead>
	<tr>
		<th>충전/사용</th>
		<th>금액</th>
		<th>내역 일자</th>
		<th>잔여 포인트</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="Point" items="${list }">
		<tr>
			<td><c:if test="${Point.isIncome == 0}">
					충전
				</c:if>
				<c:if test="${Point.isIncome == 1}">
					제품 구매
				</c:if>&emsp;</td>
			<td><c:if test="${Point.isIncome == 0}">
				+${Point.price }
				</c:if>
				<c:if test="${Point.isIncome == 1}">
				-${Point.price }
				</c:if>&emsp;</td>
			<td>${Point.regDate }&emsp;</td>
			<td>${Point.point }</td>
		</tr>
		<c:set var="num" value="${num - 1 }"></c:set>
	</c:forEach>
	</tbody>
</table>
</div>
</div>
	<div class="row">
<div style="text-align: center;">
<c:if test="${pg.startPage > pg.pageBlock }">
	<input type="button" onclick="pointView(${pg.startPage-pg.pageBlock})" value="이전"/>
	<%-- <a href="pointHistoryForm.do?currentPage=${pg.startPage-pg.pageBlock}">[이전]</a> --%>
</c:if>
<c:forEach var="i" begin="${pg.startPage }" end="${pg.endPage }">
	<input type="button" value="${i}" onclick="pointView(${i})"/>
	<%-- <a onclick="pointView(${i})">[${i}]</a> --%>
	<%-- <a href="pointHistoryForm.do?currentPage=${i}">[${i}]</a> --%>
</c:forEach>
<c:if test="${pg.endPage < pg.totalPage }">
	<input type="button" onclick="pointView(${pg.startPage+pg.pageBlock})" value="다음"/>
	<%-- <a href="pointHistoryForm.do?currentPage=${pg.startPage+pg.pageBlock}">[다음]</a> --%>
</c:if>
</div>
	</div>
	
	
	
	
<div style="text-align: center;">
	<button type="button" class="btn bg-maroon" data-toggle="modal" data-target="#modal-info" onclick="document.getElementById('modal-info').style.display='block'">
		포인트 충전하기</button>
	     <div class="modal modal-info fade" id="modal-info">
          <div class="modal-dialog">
            <div class="modal-content">
          
           
             
              <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                  <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Info Modal</h4>
              </div>
              <form class="modal-content animate" action="pointCharge.do" name="frm" id="frm" method="post" onsubmit="return chk()">   
              <div class="modal-body">
             
               
          <h3>충전할 금액을 선택하고 확인을 눌러주세요</h3>
             <div style="text-align: center;">
             <input type="hidden" name="money" id="money" value="" readonly>
			<select class="form-control" name="charge" onchange="charge_change()" required>
				<option value="0">선택</option>
				<option value="5000">5,000</option>
				<option value="10000">10,000</option>
				<option value="30000">30,000</option>
				<option value="50000">50,000</option>
				<option value="100000">100,000</option>
			</select>
			</div>
			<br>
			
			
              </div>
              <div class="modal-footer">
                <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                <button type="submit" class="btn btn-outline">확인</button>
              </div>
              	</form>
            </div>
            <!-- /.modal-content -->
          </div>
          <!-- /.modal-dialog -->
        </div>
        <!-- /.modal -->

	<!-- <button onclick="document.getElementById('id01').style.display='block'" style="width:auto;"> -->	
	<!-- 여기여기 -->
</div>

    </section>
    
    
    
    
    <!-- /.content -->
<%--   </div>
  <!-- /.content-wrapper -->

  <%@ include file="../partials/footer.jsp" %>
	
  <div class="control-sidebar-bg"></div>
</div> --%>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->


</body>
</html>