<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category</title>
<script type="text/javascript">

	function chk2() {
		console.log('name -> ' + name);
		if(!frm2.name.value) {
			alert("카테고리를 입력하세요");
			frm2.name.focus();
			return false;
		} else {
// 			location.replace("NYSCateConfirmPar.do?name=" + frm2.name.value);
			$.ajax({
				url : "CategoryConfirmParentName.do?name=" + frm2.name.value,
				dataType : "text",
				error : function() {
					alert("다시 해보세요");
				},
				success : function(data) {
					console.log('data -> ' + data);
					$('#compareParent').text(data);
				}
			});
		}
	}
	
 	 $(function(){
	        $('#save').click(function() {
	            //중복 check된 value를 가지고 오고
	            var name = $('#name').val();
	            console.log('name -> ' + name);
	            var parent = $('#name').val();
	            console.log('parent ->' + parent);
	            var isPublic = $('input:radio[name=isPublic]').val();
	            console.log('isPublic -> ' + isPublic);
	            var division = $('input:hidden[name=division]').val();
	            console.log('division -> ' + division);
	            var sendData = "name=" + name + "&parent=" + parent + "&isPublic=" + isPublic + "&division=" + division;
	            console.log('sendData -> ' + sendData);
	            
	            $.post('CategoryParentWrite.do', sendData, function(msg){

	            	console.log('msg1 -> ' + msg);
	            	
	                if(msg == 1) {
			            // 부모창 리프레시 시켜주기
			            window.opener.location.reload();
	                    self.close();
	                };
	            });
	    	});
 		 });
	

</script>
</head>
<body class="skin-red-light sidebar-mini" style="height: auto; min-height: 100%;">
<form name="frm2">
	<h2>카테고리 추가</h2>
		<p>
		카테고리 이름 : 
			<input type="text" name="name" id="name" required="required" value="${name}">
			<input type="button" value="중복체크" onclick="chk2()">
<!-- 			<input type="button" value="중복체크" id="NYSAddParentCate2"> -->
			<br><span style="color: red;" id="compareParent"></span>
			
		</p>
		<p>
			<input type="hidden" name="parent" value="${name}">
		</p>
		<p>
		공개 여부 :
			<input type="radio" name="isPublic" value="1" checked="checked">공개
			<input type="radio" name="isPublic" value="0">비공개
		</p>
		<p>
		분류 :
			<input type="hidden" name="division" value="0">부모카테고리
		</p>
<!-- 			<input type="button" value="확인" id="save"> -->
<!-- 			<input type="button" value="취소" onclick="self.close()"> -->

</form>
</body>
</html>