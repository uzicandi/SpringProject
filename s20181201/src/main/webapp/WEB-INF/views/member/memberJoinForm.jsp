<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../partials/head.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>신규회원가입</title>
<style type="text/css">
	.bg-img {
  /* The image used */
  background-image: url('./resources/images/LoginBack.jpg');
 	opacity: 0.95; 
  /* Center and scale the image nicely */
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}
</style>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="/resources/js/addressapi.js"></script>
<script type="text/javascript">
	//아이디 체크여부 확인 (아이디 중복일 경우 = 0 , 중복이 아닐경우 = 1 )
	var idck = 0;
	var nickck = 0;
	$(function() {
	    //idck 버튼을 클릭했을 때 
	    $("#idck").click(function() {
	    	idck = 0;
			var reg_id = /^[a-zA-Z0-9]{4,15}$/;
			var idv = frm.memberId.value;
			if (!idv) {
				document.getElementById('idmsg').style.color = "red";
				document.getElementById('idmsg').innerHTML = "아이디를 입력하세요.";
				frm.memberId.focus();
				return false;
			}
			if (!reg_id.test(idv)) {
				document.getElementById('idmsg').style.color = "red";
				document.getElementById('idmsg').innerHTML = "아이디는 4~15자리 영문, 숫자만 가능합니다";
				frm.memberId.focus();
				return false;
			}
	        //userid 를 param.
	        var userid =  $("#memberId").val(); 
	        $.ajax({
	            async: true,
	            type : 'POST',
	            data : userid,
	            url : "memberIdCheck.do",
	            dataType : "json",
	            contentType: "application/json; charset=UTF-8",
	            success : function(data) {
	                if (data.cnt > 0) {
	                	document.getElementById('idmsg').style.color = "blue";
						document.getElementById('idmsg').innerHTML = "존재하는 ID입니다.";
	                    $("#memberId").focus();
	                    idck = 0;
	                } else {
	                	document.getElementById('idmsg').style.color = "black";
						document.getElementById('idmsg').innerHTML = "<b>사용 가능</b>한 ID입니다.";
	                    //아이디가 존제할 경우 빨깡으로 , 아니면 파랑으로 처리하는 디자인
	                    //$("#idmsg").addClass("has-success")
	                    //$("#idmsg").removeClass("has-error")
	                    $("#passwd").focus(); 
	                    //아이디가 중복하지 않으면  idck = 1 
	                    idck = 1;
	                    
	                }
	            },
	            error : function(error) {
	            	$("#memberId").focus();
	            }
	        });
	    });
	});
	    
	$(function() {
	  //nickck 버튼을 클릭했을 때 
	    $("#nickck").click(function() {
	    	nickck = 0;
			var nickv = frm.nickname.value;
			if (!nickv) {
				document.getElementById('nickmsg').style.color = "red";
				document.getElementById('nickmsg').innerHTML = "닉네임을 입력하세요.";
				frm.nickname.focus();
				return false;
			}
	        //userid 를 param.
	        var nick =  $("#nickname").val(); 
	        $.ajax({
	            async: true,
	            type : 'POST',
	            data : nick,
	            url : "memberNickCheck.do",
	            dataType : "json",
	            contentType: "application/json; charset=UTF-8",
	            success : function(data) {
	                if (data.cnt > 0) {
	                	nickck = 0;
	                	document.getElementById('nickmsg').style.color = "blue";
						document.getElementById('nickmsg').innerHTML = "존재하는 닉네임입니다.";
	                    $("#nickname").focus();
	                } else {
	                	document.getElementById('nickmsg').style.color = "black";
						document.getElementById('nickmsg').innerHTML = "<b>사용 가능</b>한 닉네임입니다.";
	                    $("#name").focus(); 
	                    nickck = 1;
	                }
	            },
	            error : function(error) {
	            	$("#nickname").focus();
	            }
	        });
	    });
	});

		function checkPwd() { // 비밀번호 확인 AJAX
			var reg_pwd = /^[a-zA-Z0-9]{6,20}$/;
			if (frm.passwd.value != frm.passwd2.value) {
				document.getElementById('pwdmsg').style.color = "red";
				document.getElementById('pwdmsg').innerHTML = "비밀번호가 일치하지 않습니다.";
			} else if (!reg_pwd.test(frm.passwd.value)) {
				document.getElementById('pwdmsg').style.color = "red";
				document.getElementById('pwdmsg').innerHTML = "비밀번호는 6~20자리 영문, 숫자만 가능합니다";
			} else {
				document.getElementById('pwdmsg').style.color = "black";
				document.getElementById('pwdmsg').innerHTML = "비밀번호가 <b>일치</b>합니다.";
			}
		}

	function chk() {
		if(idck==0){
			modalContents.text("아이디 중복체크를 해주세요");
            modal.modal('show');
            //alert('아이디 중복체크를 해주세요');
            frm.memberId.focus();
            return false;
        }
		var reg_pwd = /^[a-zA-Z0-9]{6,20}$/;
		if (frm.passwd.value != frm.passwd2.value) {
			modalContents.text("비밀번호가 일치하지 않습니다!.");
	        modal.modal('show');
			//alert("비밀번호가 일치하지 않습니다!");
			frm.passwd.focus();
			return false;
		} else if (!reg_pwd.test(frm.passwd.value)) {
			modalContents.text("비밀번호는 6~20자리 영문, 숫자만 가능합니다");
	        modal.modal('show');
			//alert("비밀번호는 6~20자리 영문, 숫자만 가능합니다");
			frm.passwd.focus();
			return false;
		}
		if(nickck==0){
			modalContents.text("닉네임 중복체크를 해주세요");
	        modal.modal('show');
            //alert('닉네임 중복체크를 해주세요');
            frm.nickname.focus();
            return false;
        }
		if (frm.year.value == 0 || frm.month.value == 0 || frm.month.value == 0) {
			modalContents.text("생년월일을 입력하세요");
	        modal.modal('show');
			//alert("생년월일을 입력하세요.");
			frm.year.focus();
			return false;
		}
		if (frm.question.value == 0) {
			modalContents.text("질문을 선택하세요");
	        modal.modal('show');
			//alert("질문을 선택하세요")
			frm.question.focus();
			return false;
		}
		return true;
	}
	
	function execPostCode() {
        new daum.Postcode({
            oncomplete: function(data) {
               // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

               // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
               // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
               var extraRoadAddr = ''; // 도로명 조합형 주소 변수

               // 법정동명이 있을 경우 추가한다. (법정리는 제외)
               // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
               if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                   extraRoadAddr += data.bname;
               }
               // 건물명이 있고, 공동주택일 경우 추가한다.
               if(data.buildingName !== '' && data.apartment === 'Y'){
                  extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
               }
               // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
               if(extraRoadAddr !== ''){
                   extraRoadAddr = ' (' + extraRoadAddr + ')';
               }
               // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
               if(fullRoadAddr !== ''){
                   fullRoadAddr += extraRoadAddr;
               }

               // 우편번호와 주소 정보를 해당 필드에 넣는다.
               console.log(data.zonecode);
               console.log(fullRoadAddr);
               
               
               $("[name=addr1]").val(data.zonecode);
               $("[name=addr2]").val(fullRoadAddr);
           }
        }).open();
    }
	
	$(function(){
        //모달을 전역변수로 선언
        var modalContents = $(".modal-contents");
        var modal = $("#defaultModal");   
         
        $(".onlyHangul").keyup(function(event){
            if (!(event.keyCode >=37 && event.keyCode<=40)) {
                var inputVal = $(this).val();
                $(this).val(inputVal.replace(/[a-z0-9]/gi,''));
            }
        });
     
        $(".onlyNumber").keyup(function(event){
            if (!(event.keyCode >=37 && event.keyCode<=40)) {
                var inputVal = $(this).val();
                $(this).val(inputVal.replace(/[^0-9]/gi,''));
            }
        });

    });
	
	function loginGo(){
		location.replace("home.do");
	}

</script>
</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
	<%@ include file="../partials/header.jsp" %>

	<div class="content-wrapper bg-img">
		<!-- Content Header (Page header) -->
	    <section class="content-header">
	      <ol class="breadcrumb">
	        <li><a href="home.do"><i class="fa fa-dashboard"></i> BMW</a></li>
	        <li class="active">약관동의</li>
	        <li class="active">회원가입</li>
	      </ol>
	    </section>
	<div class="container"><!-- 좌우측의 공간 확보 -->
            <!-- 모달창 -->
            <div class="modal fade" id="defaultModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title">알림</h4>
                        </div>
                        <div class="modal-body">
                            <p class="modal-contents"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <!--// 모달창 -->
            <hr/>
        
        <!-- Horizontal Form -->
          <div class="box  ">
            <div class="box-header with-border">
              <h3 class="box-title">회원가입</h3>
              <small style="float: right; color: red;">* 표시 항목은 필수 입력 항목입니다&emsp;</small>
            </div>
            <!-- /.box-header -->
            <!-- form start -->
            <form class="form-horizontal" action="memberInsert.do" method="post" name="frm" onsubmit="return chk()">
              <div class="box-body">
              
            <div class="form-group">
                <label for="memberId" class="col-sm-2 control-label">아이디<span style="color: red">*</span></label>
                <div class="col-lg-7">
                    <input type="text" style="width:85%; height:35" name="memberId" id="memberId" placeholder="&emsp;4~15자리 영문, 숫자" maxlength="15" required>&ensp;
                    <input type="button" class="btn btn" value="중복체크" id="idck">
                    <div id="idmsg"></div>
                </div>
            </div>
            <div class="form-group">
                <label for="passwd" class="col-sm-2 control-label">패스워드<span style="color: red">*</span></label>
                <div class="col-lg-7">
                    <input type="password" class="form-control" name="passwd" id="passwd" placeholder="6~20자리 영문, 숫자" maxlength="20" required>
                </div>
            </div>
            <div class="form-group">
                <label for="passwd2" class="col-sm-2 control-label">패스워드 확인<span style="color: red">*</span></label>
                <div class="col-lg-7">
                    <input type="password" class="form-control" name="passwd2" id="passwd2" placeholder="6~20자리 영문, 숫자" maxlength="20" onkeyup="checkPwd()" required><br>
                    <div id="pwdmsg">비밀번호를 입력하십시오</div>
                </div>
            </div>
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">이름<span style="color: red">*</span></label>
                <div class="col-lg-7">
                    <input type="text" class="form-control onlyHangul" name="name" id="name" maxlength="11" required>
                </div>
            </div>
             
            <div class="form-group">
                <label for="nickname" class="col-sm-2 control-label">닉네임<span style="color: red">*</span></label>
                <div class="col-lg-7">
                    <input type="text" style="width:85%; height:35" name="nickname" id="nickname" placeholder="&emsp;닉네임으로 활동하게 됩니다" maxlength="15" required>&ensp;
                    <input type="button" class="btn btn"  value="중복체크" id="nickck"><div id="nickmsg"></div>
                </div>
            </div>
            
            <div class="form-group">
                <label for="birth" class="col-sm-2 control-label">생년월일<span style="color: red">*</span></label>
                <div class="col-lg-10">
                	<input type="date" name="birth" id="birth" required>
                </div>
            </div>
            
            <div class="form-group">
                <label for="sex" class="col-sm-2 control-label">성별<span style="color: red">*</span></label>
                <div class="col-lg-10">
                    <label class="radio-inline">
                        <input type="radio" id="sex" name="sex" value="0" required> 남성
                    </label>
                    <label class="radio-inline">
                        <input type="radio" id="sex" name="sex" value="1"> 여성
                    </label>
                </div>
            </div>
            
            <div class="form-group">
                <label for="skinType" class="col-sm-2 control-label">피부타입<span style="color: red">*</span></label>
                <div class="col-lg-10">
                	<label class="radio-inline">
                        <input type="radio" name="skinType" id="skinType" value="0" required> 건성
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="skinType" id="skinType" value="1"> 중성
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="skinType" id="skinType" value="2"> 지성
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="skinType" id="skinType" value="3"> 복합성
                    </label>
                </div>
            </div>
            
            <div class="form-group">
                <label for="skinComplex" class="col-sm-2 control-label">피부고민<span style="color: red">*</span></label>
                <div class="col-lg-10">
                	<label class="radio-inline">
                        <input type="radio" name="skinComplex" id="skinComplex" value="0" checked required> 해당없음
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="skinComplex" id="skinComplex" value="1"> 아토피
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="skinComplex" id="skinComplex" value="2"> 여드름
                    </label>
                    <label class="radio-inline">
                        <input type="radio" name="skinComplex" id="skinComplex" value="3"> 민감성
                    </label>
                </div>
            </div>
            <hr/>
            <small style="float: right; color: red;">아이디/패스워드 찾기 시 질문, 답변, 이메일 참고 &emsp;</small>	
            <div class="form-group">
                <label for="question" class="col-sm-2 control-label">질문<span style="color: red">*</span></label>
                <div class="col-lg-7">
                    <select class="form-control" name="question" id="question">
                        <option value="0" selected>질문을 선택하세요</option>
						<option value="1">아버님은 무슨일 하시니?</option>
						<option value="2">연봉이 어떻게 되니?</option>
						<option value="3">차는 있니?</option>
						<option value="4">문제 있는 장기 있니?</option>
                    </select>
                </div>
            </div>
            
            <div class="form-group">
                <label for="answer" class="col-sm-2 control-label">답변<span style="color: red">*</span></label>
                <div class="col-lg-7">
                    <input type="text" class="form-control" name="answer" id="answer" maxlength="15" required>
                </div>
            </div>
            
            <div class="form-group">
                <label for="email" class="col-sm-2 control-label">이메일<span style="color: red">*</span></label>
                <div class="col-lg-7">
                    <input type="email" class="form-control" id="email" name="email" placeholder="가입승인 메일이 발송됩니다. 정확히 입력해 주세요" maxlength="40" required>
                </div>
            </div>
            
            <div class="form-group">
                <label for="phone" class="col-sm-2 control-label">휴대폰 번호</label>
                <div class="col-lg-7">
                	<input type="tel" name="phone" id="phone" pattern="\d{2,3}-\d{3,4}-\d{4}" placeholder="xxx-xxxx-xxxx" maxlength="13">
                </div>
            </div>
            
            <div class="form-group">
                <label for="inputPhoneNumber" class="col-sm-2 control-label">주소</label>
                <div class="col-lg-7">
                	<input class="form-control" style="width: 40%; display: inline;" placeholder="우편번호" name="addr1" id="addr1" type="text" readonly="readonly">
					<button type="button" class="btn btn-default" onclick="execPostCode();"><i class="fa fa-search"></i>우편번호 찾기</button>
					<input class="form-control" placeholder="도로명 주소" name="addr2" id="addr2" type="text" readonly="readonly">
					<input class="form-control" placeholder="상세주소" name="addr3" id="addr3" type="text">
                </div>
            </div>
            
            <div class="form-group">
                <label for="recommender" class="col-sm-2 control-label">추천인ID</label>
                <div class="col-lg-7">
                	<input type="text" class="form-control" name="recommender" id="recommender" maxlength="15">
                </div>
            </div>
            
            <div class="form-group">
                <label for="inputEmailReceiveYn" class="col-sm-2 control-label">이메일 수신여부</label>
                <div class="col-lg-10">
                    <label class="radio-inline">
                        <input type="radio" id="emailReceiveYn" name="isEmail" value="1"> 동의합니다.
                    </label>
                    <label class="radio-inline">
                        <input type="radio" id="emailReceiveYn" name="isEmail" value="0" checked> 동의하지 않습니다.
                    </label>
                </div>
            </div>
          </div>
        <!-- /.box-body -->
              <div class="box-footer">
                <button type="button" class="btn btn-default" onclick="loginGo()">Cancel</button>
                <button type="submit" class="btn bg-maroon pull-right">Sign in</button>
              </div>
              <!-- /.box-footer -->
            </form>
          </div>
          <!-- /.box -->
        </div>
        </div>
        <%@ include file="../partials/footer.jsp" %>
        <div class="control-sidebar-bg"></div>
        </div>
</body>
</html>