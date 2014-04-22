<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@include file="../common/head.jsp"%>
    <title>나무커뮤니티</title>
</head>
<body>

<!-- Main Navigation ========================================================================================== -->
<%@include file="../common/main_navi.jsp"%>

<!-- Header ========================================================================================== -->
<header>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="jumbotron">
                    <h2>커뮤니티 관리센터</h2>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12 col-lg-12">
                <ol class="breadcrumb">
                    <li><a href="#">관리자 홈</a></li>
                    <li><a href="#">커뮤니티 관리</a></li>
                    <li class="active">회원관리</li>
                </ol>
            </div>
        </div>
    </div>
</header>

<!-- Container ======================================================================================= -->
<div class="container">
    <div class="row">


        <!-- ★★★ Contents -->
        <div class="col-sm-9 col-lg-9">
            <div class="page-header2">
                <h3>개인정보</h3>
            </div>

            <!-- ★★★ Tab Menu -->
            <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                <li class="active"><a href="#all" data-toggle="tab">개인정보 관리</a></li>

            </ul>


            <!-- ★★★ Tab Panel -->
                <!-- ★★★ 회원정보 -->
                <div class="tab-pane fade active in" id="all">

					<div class="table-responsive">
						<form action="${ctx}/user/modify.do" method="post">
                        <table class="table table-bordered table-hover">
                            <tr>
                                <th class="text-center">이메일</th>
                                <td class="text-center"><input name="email" class="text-center" type="text" value="${person.email}" 
								style="border:0px; background:transparent; width:100%; margin:0;" readonly="readonly"></td>
                            </tr>
							<tr>
								<th class="text-center">이름</th>
								<td class="text-center"><input name="name" class="text-center" type="text" value="${person.name}" 
								style="border:1px solid #999999; border-radius:5px; background:transparent; width:100%; margin:0;" required="required"></td>
							</tr>
							<tr>
								<th class="text-center">비밀번호</th>
								<td class="text-center"><input name="password" class="text-center" type="password" value="${person.password}" 
								style="border:1px solid #999999; border-radius:5px; background:transparent; width:100%; margin:0;" required="required"></td>
							</tr>
								
							</table>
							<button type="submit" class="btn btn-sm btn-default" style="float: right; margin-top: -10px; margin-right: 20px;">수정</button>
							</form>
                    </div>
                </div>

				<ul class="nav nav-tabs" style="margin-bottom: 15px;">
                	<li class="active"><a href="#community" data-toggle="tab">커뮤니티</a></li>
					<li><a href="#club" data-toggle="tab">클럽</a></li>
            	</ul>
				<div id="mycomm_club" class="tab-content">
					<div class="tab-pane fade active in" id="community">
					<div class="table-responsive">
					<ul class="list-group">
                    <!-- ★★★ 가입커뮤니티목록 -->
                    

						<c:if test="${my_communities.size()!=0}">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="text-center">이름</th>
                                <th class="text-center">설명</th>
                                <th class="text-center">관리자</th>
                                <th class="text-center">탈퇴/삭제</th>
                            </tr>
                            </thead>
								<tbody>
									<!--<tr><td colspan="5" class="text-center">가입한 커뮤니티가 없습니다.</td></tr>-->
									<c:forEach var="community" items="${my_communities}">
										<tr>
											<td class="text-center">${community.name}</td>
											<td class="text-center">${community.description}</td>
											<td class="text-center">${community.manager.manager.email}</td>
											<td class="text-center">
											<c:choose>
											<c:when  test="${community.manager.manager.email eq loginId}">
												<button class="btn btn-xs btn-default" 
												onclick="location.href='${ctx}/community/remove?community_id=${community.id}&mypage=true'">삭제</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-xs btn-default" 
												onclick="location.href='${ctx}/community/withdrawal?community_id=${community.id}&mypage=true'">탈퇴</button>
											</c:otherwise>
											</c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</c:if>
						</ul>
						</div>
                    </div>
                    
                    <div class="tab-pane fade" id="club">
                    <div class="table-responsive">
					<ul class="list-group">
                    	<c:if test="${my_clubs.size()!=0}">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="text-center">이름</th>
                                <th class="text-center">설명</th>
                                <th class="text-center">대표관리자</th>
                                <th class="text-center">탈퇴/삭제</th>
                            </tr>
                            </thead>
								<tbody>
									<!--<tr><td colspan="5" class="text-center">가입한 클럽이 없습니다.</td></tr>-->
									<c:forEach var="club" items="${my_clubs}">
										<tr>
											<td class="text-center">${club.name}</td>
											<td class="text-center">${club.description}</td>
											<td class="text-center">${club.mainManager.manager.email}</td>
											<td class="text-center">
											<c:choose>
											<c:when  test="${club.mainManager.manager.email eq loginId}">
											<button class="btn btn-xs btn-default" 
											onclick="location.href='${ctx}/club/remove?club_id=${club.id}'">삭제</button>
											</c:when>
											<c:otherwise>
											<button class="btn btn-xs btn-default" 
											onclick="location.href='${ctx}/club/withdrawal?club_id=${club.id}'">탈퇴</button>
											</c:otherwise>
											</c:choose>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</c:if>
						</ul>
                    </div>
                </div>

                    <div class="text-center">

                    </div>
                </div>
            </div>

        </div>
        <!-- Footer ========================================================================================== -->
   		<%@include file="../common/footer.jsp"%>
    </div>
</body>
</html>