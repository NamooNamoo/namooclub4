<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="../common/head.jsp"%>
<title>나무커뮤니티</title>

<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
						<h2>관리센터</h2>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<ol class="breadcrumb">
						<li><a href="#">관리자 홈</a></li>
						<li><a href="#">커뮤니티 관리</a></li>
						<li class="active">클럽관리</li>
					</ol>
				</div>
			</div>
		</div>
	</header>

	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">


		<%-- 	<!-- ★★★ Aside Menu -->
        <div style="z-index:1020" class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
            <div class="list-group panel panel-success">
                <div class="panel-heading list-group-item text-center hidden-xs">
                    <h4>클럽 관리</h4>
                </div>
                <div id="cat-navi">
                    <a href="${ctx}/manage/club.do" class="list-group-item hidden-xs">클럽관리</a>
                    <a href="./commMemberList.html" class="list-group-item hidden-xs active">회원관리</a>
                    </div>
                <div class="panel-footer">
                    <div class="row">
                        <div class="col-xs-6 col-sm-12 col-md-6 col-lg-6 text-left">
                            <a class="btn btn-link btn-sm btn-block" href="#">클럽운영자 채팅</a>
                        </div>
                        <div class="col-xs-6 col-sm-12 col-md-6 col-lg-6 text-left">
                            <a class="btn btn-link btn-sm btn-block" href="#">전체 메일발송</a>
                        </div>
                    </div>
                </div>
            </div>
        </div> --%>


			<!-- ★★★ Contents -->
			<div class="col-sm-9 col-lg-9">
				<div class="page-header2">
					<h3>클럽관리</h3>
				</div>

				<!-- ★★★ Tab Menu -->
				<ul class="nav nav-tabs" style="margin-bottom: 15px;">
					<li class="active"><a href="#all" data-toggle="tab">전체클럽</a></li>

				</ul>


				<!-- ★★★ Tab Panel -->
				<div id="communityList" class="tab-content">
					<!-- ★★★ 전체클럽 -->
					<div class="tab-pane fade active in" id="all">

						<!-- ★★★ 검색조건 -->
						<div class="panel panel-default">
							<div class="panel-body">
								<form class="form-search">
									<div class="input-group">
										<input type="text" class="form-control"
											placeholder="클럽명 또는 운영자명"> <span
											class="input-group-btn">
											<button type="submit" class="btn btn-primary">검색</button>
										</span>
									</div>
								</form>
							</div>
						</div>

						<!-- ★★★ 회원목록 -->
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="text-center">번호</th>
										<th class="text-center">카테고리</th>
										<th class="text-center">클럽이름</th>
										<th class="text-center">대표관리자</th>
										<th class="text-center">회원관리</th>
									</tr>
								</thead>
								<tbody>


									<!--<tr><td colspan="5" class="text-center">등록된 클럽이 없습니다.</td></tr>-->
									<c:forEach var="club" items="${club_list}" varStatus="status">
										<tr>
											<td class="text-center">${status.count}</td>
											<td>${club.category}</td>
											<td><a
												href="${ctx}/manage/club_detail?club_id=${club.id}">${club.name}</a></td>
											<td>${club.mainManager.manager.name}</td>
											<td class="text-center"><button class="btn btn-xs btn-default" onclick="location.href='${ctx}/manage/club_mem?club_id=${club.id}'">회원관리</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

		</div>
	<!-- Footer ========================================================================================== -->
	<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>