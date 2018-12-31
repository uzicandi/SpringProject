<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../partials/head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
  <%@ include file="../partials/header.jsp" %>


  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       	관리자페이지
        <small>Admin Page</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>MemberService </a></li>
        <li class="active">Admin</li>
      </ol>
    </section> 
    
    
	    <!-- Main content -->
<section class="content container-fluid">


 <!-- Top content -->
<div class="top-content">   
    
	<div class="inner-bg">
		<div class="container">
			 
    
    
    <div style="margin-top: 150px;">
       <!-- Small boxes (Stat box) -->
      <div class="row">
        <div class="col-lg-6 col-sm-6 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner" style="padding-top: 20px; padding-bottom: 20px;">
				<h3>제품관리</h3>
              <p>Item List</p>
            </div>
            <div class="icon">
              <i class="fa fa-shopping-cart"></i>
            </div>
            <a href="KISItemList.do" class="small-box-footer">
              More info <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-6 col-sm-6 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-green">
            <div class="inner" style="padding-top: 20px; padding-bottom: 20px;">
              <h3>성분관리<sup style="font-size: 20px"></sup></h3>

              <p>Ingredient List</p>
            </div>
            <div class="icon">
              <i class="ion ion-stats-bars"></i>
            </div>
            <a href="KISIngtList.do" class="small-box-footer">
              More info <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
       </div>
       </div>
       <div style="margin-top: 20px;">
       <div class="row"> 
        <!-- ./col -->
        <div class="col-lg-6 col-sm-6 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-yellow">
             <div class="inner" style="padding-top: 20px; padding-bottom: 20px;">
              <h3>회원관리</h3>
              <p>Member List</p>
            </div>
            <div class="icon">
              <i class="ion ion-person-add"></i>
            </div>
            <a href="memberToListAdmin.do" class="small-box-footer">
              More info <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-6 col-sm-6 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-red">
             <div class="inner" style="padding-top: 20px; padding-bottom: 20px;">
              <h3>카테고리관리</h3>

              <p>Category List</p>
            </div>
            <div class="icon">
              <i class="ion ion-pie-graph"></i>
            </div>
            <a href="CategoryListView.do" class="small-box-footer">
              More info <i class="fa fa-arrow-circle-right"></i>
            </a>
          </div>
        </div>
        <!-- ./col -->
      </div>
      <!-- /.row -->
    </div>
    
    
    
    
 </div> 
 </div>
 </div>  

    </section>
    <!-- /.content -->
    
    
  </div>
  <!-- /.content-wrapper -->
  
  
  <%@ include file="../partials/footer.jsp" %>
	
  <div class="control-sidebar-bg"></div>
</div>  
</body>
</html>