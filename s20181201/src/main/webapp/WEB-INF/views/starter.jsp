<!DOCTYPE html>
<html>
<head>
  	<title>aaaaaa</title>
  	<%@ include file="partials/head.jsp" %>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-red-light                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">
  
  <%@ include file="partials/header.jsp" %>
  
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

    <!-- Main content -->
    <section class="content container-fluid">


		<div class="row">
			<div class="col-xs-6" style="background-color: red;">TEST</div>
			<div class="col-xs-6" style="background-color: yellow;">TEST</div>
		</div>
		
		
		<script type="text/javascript">
			$(function() { 
			    $('#cate_id2 > ul').show(); 
			    $('#cate_id2 > ul > li') 
				     .on('mouseover', function() { 
				      $(this).find('div').stop(true, true).slideDown(); 
				     }) 
				     .on('mouseout', function() { 
				      $(this).find('div').stop(true, true).slideUp(); 
			     }); 
			}); 
			
		</script>
		
		
		<div class="col-sm-3">
			<li id="cate_id2"> 
      			<a href="CateProducts.aspx">Laptops &amp; Notebooks</a> 
      			<ul style="display: block;"> 
       				<li id="subcate_id3"> 
        				<a href="SubCateProducts.aspx"> MACS</a> 
       				</li> 
       				<li id="subcate_id4"> 
        				<a href="SubCateProducts.aspx"> Windows</a> 
       					<div style="border: 1px solid black; display: none;"> 
        					<ul style="border: 1px solid black; display: block;"> 
	         					<li><a href="SubSubCateProducts.aspx">notebook</a></li> 
	         					<li><a href="SubSubCateProducts.aspx">probook</a></li> 
        					</ul> 
       					</div> 
       				</li> 
      			</ul> 
      		</li> 
		</div>

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <%@ include file="partials/footer.jsp" %>
	
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<!-- REQUIRED JS SCRIPTS -->


</body>
</html>