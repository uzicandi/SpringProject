<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="home.do" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><strong>BMW</strong></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><strong>B</strong>eauty <strong>M</strong>an&<strong>W</strong>oman</span>
    </a>

	<!-- 여기부터 위에 바 -->
	<c:if test="${sessionId != null}">
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          
          <!-- Messages: style can be found in dropdown.less--> 
          <li class="dropdown messages-menu">
            <!-- 장바구니 button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-opencart"></i>
              <c:if test="${cartMap.get('count') != 0 }">
              	<span class="label label-success">${cartMap.get('count') }</span>
              </c:if>
            </a>
            <ul class="dropdown-menu">
              <li class="header"></li>
              <li>
                <!-- inner menu: contains the messages -->
                <ul class="menu">
                
                <c:forEach var="row" items="${cartMap.get('list') }" varStatus="i">
                  <li><!-- start message -->
                    <a href="KISItemContent.do?itemNo=${row.mainNo }">
                      <div class="pull-left">
                        <!-- 상품이미지 -->
                        <img src="${row.filePath }" class="img-circle" alt="User Image">
                      </div>
                      <!-- 상품정보 -->
                      <h4>${row.title}<small>이동</small></h4>
                    </a>
                  </li>
                  </c:forEach>
                  
                  <!-- end message -->
                </ul>
                <!-- /.menu -->
              </li>
              <li class="footer"><a href="CartList.do">장바구니 상품 구매하기</a></li>
            </ul>
          </li>
          
           <!-- 비교함 button  12/19(화) 재연 -->
		<li class="dropdown messages-menu"><a href="#"
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="fa fa-folder-open-o"></i> <c:if
					test="${foldingList.size() != 0 }">
					<span class="label label-success">${foldingList.size() }</span>
				</c:if>
		</a>
			<ul class="dropdown-menu">
				<li class="header"></li>
				<li>
					<!-- inner menu: contains the messages -->
					<ul class="menu">

						<c:forEach var="item" items="${foldingList }" varStatus="i">
							<li>
								<!-- start message --> <a
								href="KISItemContent.do?itemNo=${item.itemNo }">
									<div class="pull-left">
										<!-- 상품이미지 -->
										<img src="${item.saveFileList[0].filePath }"
											class="img-circle" alt="${item.name }">
									</div> <!-- 상품정보 -->
									<h4>${item.name}<small>이동</small>
									</h4>
							</a>
							</li>
						</c:forEach>

						<!-- end message -->
					</ul> <!-- /.menu -->
				</li>
				<li class="footer"><a data-toggle="modal"
					data-target="#compareModal">상품 비교하기</a></li>
			</ul></li>
          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- 종 버튼 Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- Inner Menu: contains the notifications -->
                <ul class="menu">
                  <li><!-- start notification -->
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <!-- end notification -->
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <c:choose>
              	<c:when test="${ssMap.get('filePath') == '0'}">
              		<img src="/uploadImg/member/noimage.jpg" class="user-image" alt="User Image">
              	</c:when>
              	<c:otherwise>
              		<img src="${ssMap.get('filePath') }" class="user-image" alt="User Image">
              	</c:otherwise>
              </c:choose>
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">${ssMap.get('nickname') }</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
              	<c:choose>
	              	<c:when test="${ssMap.get('filePath') == '0'}">
	              		<img src="/uploadImg/member/noimage.jpg" class="img-circle" alt="User Image">
	              	</c:when>
	              	<c:otherwise>
	              		<img src="${ssMap.get('filePath') }" class="img-circle" alt="User Image">
	              	</c:otherwise>
	              </c:choose>

                <p>${ssMap.get('nickname') }
                  <small>
                  	Info(${ssMap.get('age') }세/
                  	<c:choose>
                  		<c:when test="${ssMap.get('sex') == 0 }">남성</c:when>
                  		<c:otherwise>여성</c:otherwise>
                  	</c:choose>/
                  	<c:choose>
                  		<c:when test="${ssMap.get('skinType') == 0 }">건성</c:when>
                  		<c:when test="${ssMap.get('skinType') == 1 }">중성</c:when>
                  		<c:when test="${ssMap.get('skinType') == 2 }">지성</c:when>
                  		<c:otherwise>복합성</c:otherwise>
                  	</c:choose>)
                  </small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="memberMyPageForm.do">My Page</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="OrderList.do">주문내역</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="Logout.do">Sign out</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
            </ul>
          </li>
          
        </ul>
      </div>
    </nav>
    </c:if>
    
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
<!--       <div class="user-panel">
        <div class="pull-left image">
          <img src="./resources/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          Status
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div> -->

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="Search...">
          <span class="input-group-btn">
              <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
              </button>
            </span>
        </div>
      </form>
      <!-- /.search form -->

     <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">HEADER</li>
        <!-- Optionally, you can add icons to the links -->
        
       	<!-- 고정링크 -->
<!--         <li class="active"><a href="#"><i class="fa fa-link"></i> <span>Link</span></a></li> -->
        
        <!-- 일반링크 -->
        <li><a href="KISItemList.do"><i class="fa fa-info-circle"></i> <span>제품</span></a></li>
		
       
       <!-- 제품 - 카테고리 목록 -->
        <li class="treeview">
          <a href="#"><i class="fa fa-align-justify"></i> <span>카테고리별</span>
            <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
              </span>
          </a>
          <ul class="treeview-menu">
          	<c:forEach var="list" items="${headerCateList }">
	            <li><a href="KISItemList.do?cateSel=${list.categoryNo }">${list.name }</a></li>
            </c:forEach>
          </ul>
        </li>
        
        <li><a href="RankingMainView.do"><i class="fa fa-trophy"></i> <span>랭킹</span></a></li>
        
        <!-- 커뮤니티 링크 -->
		<li class="header">커뮤니티</li>
		<li><a href="BoardList.do"><i class="fa fa-circle-o"></i> <span>게시판</span></a></li>
		<li><a href="WebzineList.do"><i class="fa fa-circle-o"></i> <span>웹진</span></a></li>
      </ul>
      
	<!--	마이페이지       -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">마이페이지</li>
        <c:if test="${sessionId == null }">
       		<li><a href="LoginView.do"><i class="fa fa-key"></i> <span>login</span></a></li>
<!--        		<li><a href="memberJoinAgreeForm.do"><i class="fa fa-link"></i> <span>join</span></a></li> -->
       </c:if>
        <c:if test="${sessionId != null }">
        	<li><a href="memberMyPageForm.do"><i class="fa fa-user"></i> <span>My Page</span></a></li>
        	<li><a href="OrderList.do"><i class="fa fa-truck"></i> <span>주문내역</span></a></li>
        </c:if>
      </ul>
      
      <!-- 관리자링크 -->
      <c:if test="${ssMap.get('grade') == 0 }">
		<ul class="sidebar-menu" data-widget="tree">
			<li class="header">관리자</li>
			<li><a href="AdminForm.do"><i class="fa fa-cogs"></i> <span>관리자 페이지</span></a></li>
			<li><a href="KISIngtList.do"><i class="fa fa-flask"></i> <span>성분관리</span></a></li>
			<li><a href="CategoryListView.do"><i class="fa fa-list"></i> <span>카테고리관리</span></a></li>
			<li><a href="memberToListAdmin.do"><i class="fa fa-users"></i> <span>회원관리</span></a></li>
		</ul>
      </c:if>
      
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">없어질거 쓰는곳</li>
        <li><a href="OrderSheet.do"><i class="fa fa-link"></i> <span>payment</span></a></li>
        <li><a href="EventList.do?currentPage=1"><i class="fa fa-circle-o text-aqua"></i> <span>이벤트</span></a></li>
        <li><a href="memberWithdrawalForm.do"><i class="fa fa-link"></i> <span>회원탈퇴</span></a></li>
      </ul>
        
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>
    
  <!-- 이후로는 12/19(목) 재연 추가사항 -->
<script type="text/javascript">

	function divideContent(num) {
		return parseInt(12 / num);
	}
	
	
</script>




<!-- Modal -->
<div class="modal fade" id="compareModal" tabindex="-1" role="dialog"
	aria-labelledby="compareModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="compareModalLabel">제품 비교</h4>
			</div>
			<div class="modal-body">
				<c:if test="${foldingList == null }">비교함이 비었습니다.</c:if>
				<c:if test="${foldingList != null }">
					<fmt:parseNumber var="num" value="${12/foldingList.size() }" integerOnly="true"></fmt:parseNumber>
					<table class="table text-center">
						<tr>
							<th style="width: 120px">제품사진</th>
							<c:forEach var="item" items="${foldingList}">
								<div class="col-sm-${num }">
									<td><img alt="${item.name }" src="${item.saveFileList[0].filePath }" style="width: 100%; height: auto"></td>
								</div>
							</c:forEach>
						</tr>
						<tr>
							<th>제품이름</th>
							<c:forEach var="item" items="${foldingList}">
								<div class="col-sm-${num }">
									<td>${item.name }</td>
								</div>
							</c:forEach>
						</tr>
						<tr>
							<th>평점</th>
							<c:forEach var="item" items="${foldingList}">
								<div class="col-sm-${num }">
									<td>${item.rating }</td>
								</div>
							</c:forEach>
						</tr>
						<tr>
							<th>가격</th>
							<c:forEach var="item" items="${foldingList}">
								<div class="col-sm-${num }">
									<td>${item.price }</td>
								</div>
							</c:forEach>
						</tr>
						<tr>
							<th>랭킹</th>
							<c:forEach var="item" items="${foldingList}">
								<div class="col-sm-${num }">
									<td>진행중</td>
								</div>
							</c:forEach>
						</tr>
						<tr>
							<th>20가지<br>주의성분</th>
							<div class="col-sm-${num }">
							<c:forEach var="ingtlist3" items="${compareIngtList}">
								<td>
									<c:set var="ingtCnt" value="0"/>
									<c:forEach var="ingt" items="${ingtlist3}" varStatus="status">
										<c:if test="${ingt.danger20 != null }">
											<c:set var="ingtCnt" value="${ingtCnt + 1 }"/>
										</c:if>
									</c:forEach>
									${ingtCnt } 개
								</td>
							</c:forEach>
							</div>
						</tr>
						<tr>
							<th>알레르기<br>주의성분</th>
							<div class="col-sm-${num }">
								<c:forEach var="ingtlist3" items="${compareIngtList}">
								<td>
									<c:set var="ingtCnt" value="0"/>
									<c:forEach var="ingt" items="${ingtlist3}" varStatus="status">
										<c:if test="${ingt.dangerAllergy != null }">
											<c:set var="ingtCnt" value="${ingtCnt + 1 }"/>
										</c:if>
									</c:forEach>
									${ingtCnt } 개
								</td>
								</c:forEach>
							</div>
						</tr>
						<tr>
							<th>지성</th>
							<div class="col-sm-${num }">
								<c:forEach var="ingtlist3" items="${compareIngtList}">
								<td>
									<c:set var="ingtCnt" value="0"/>
									<c:forEach var="ingt" items="${ingtlist3}" varStatus="status">
										<c:if test="${ingt.specialyType eq '지성' }">
											<c:set var="ingtCnt" value="${ingtCnt + 1 }"/>
										</c:if>
									</c:forEach>
									${ingtCnt } 개
								</td>
								</c:forEach>
							</div>
						</tr>
						<tr>
							<th>건성</th>
							<div class="col-sm-${num }">
									<c:forEach var="ingtlist3" items="${compareIngtList}">
									<td>
										<c:set var="ingtCnt" value="0"/>
										<c:forEach var="ingt" items="${ingtlist3}" varStatus="status">
											<c:if test="${ingt.specialyType eq '건성' }">
												<c:set var="ingtCnt" value="${ingtCnt + 1 }"/>
											</c:if>
										</c:forEach>
										${ingtCnt } 개
									</td>
									</c:forEach>
							</div>
						</tr>
						<tr>
							<th>민감성</th>
							<div class="col-sm-${num }">
								<c:forEach var="ingtlist3" items="${compareIngtList}">
								<td>
									<c:set var="ingtCnt" value="0"/>
									<c:forEach var="ingt" items="${ingtlist3}" varStatus="status">
										<c:if test="${ingt.specialyType eq '민감성' }">
											<c:set var="ingtCnt" value="${ingtCnt + 1 }"/>
										</c:if>
									</c:forEach>
									${ingtCnt } 개
								</td>
								</c:forEach>
							</div>
						</tr>
						<tr>
							<th>기능성</th>
							<div class="col-sm-${num }">
								<c:forEach var="ingtlist3" items="${compareIngtList}">
								<td>
									<c:set var="ingtCnt" value="0"/>
									<c:forEach var="ingt" items="${ingtlist3}" varStatus="status">
										<c:if test="${ingt.functional != null }">
											<c:set var="ingtCnt" value="${ingtCnt + 1 }"/>
										</c:if>
									</c:forEach>
									${ingtCnt } 개
								</td>
								</c:forEach>
							</div>
						</tr>
					</table>
				</c:if>

				<div id="testtest"></div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary">Save changes</button>
			</div>
		</div>
	</div>
</div>
  

  
