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
						<li class="active">커뮤니티관리</li>
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
					<h3>커뮤니티관리</h3>
				</div>

				<!-- ★★★ Tab Menu -->
				<ul class="nav nav-tabs" style="margin-bottom: 15px;">
					<li class="active"><a href="#all" data-toggle="tab">전체커뮤니티</a></li>

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
											placeholder="커뮤니티명 또는 운영자명"> <span
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
										<th class="text-center">커뮤니티명</th>
										<th class="text-center">관리자</th>

									</tr>
								</thead>
								<tbody>
									<!--<tr><td colspan="5" class="text-center">등록된 클럽이 없습니다.</td></tr>-->

									<c:forEach var="community" items="${community_list}"
										varStatus="status">
										<tr>
											<td class="text-center">${status.count}</td>
											<td><a
												href="${ctx}/manage/community_detail?community_id=${community.id}">${community.name}</a></td>
											<td>${community.manager.manager.name}</td>
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