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
						<h1>${community.name}</h1>
						<p>${community.description}</p>
						<p>
							<c:if test="${param.join ne false}">
								<a href="${ctx}/club/open.do?community_id=${community.id}"
									class="btn btn-warning btn-lg">클럽 개설하기</a>
							</c:if>
						</p>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-sm-12 col-lg-12">
					<ol class="breadcrumb">
						<li><a href="#">Home</a></li>
						<li class="active">${community.name}</li>
					</ol>
				</div>
			</div>
		</div>
	</header>

	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<!-- ★★★ Tab Menu -->
				<c:choose>
					<c:when test="${loginId ne null}">
						<ul class="nav nav-tabs" style="margin-bottom: 15px;">
							<li class="active"><a href="#joined" data-toggle="tab">가입
									클럽</a></li>
							<li class=""><a href="#unjoinded" data-toggle="tab">미가입
									클럽</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="nav nav-tabs" style="margin-bottom: 15px;">
							<li class="active"><a data-toggle="tab">클럽 목록</a></li>
						</ul>
					</c:otherwise>
				</c:choose>

				<!-- ★★★ Tab Panel -->
				<div id="communityList" class="tab-content">
					<c:choose>
						<c:when test="${loginId ne null}">
							<!-- ★★★ 가입 클럽 -->
							<div class="tab-pane fade active in" id="joined">
								<div class="page-header">
									<h2 id="container">가입 클럽</h2>
								</div>

								<ul class="list-group">
									<c:forEach var="myClub" items="${my_club_list}">
										<li class="list-group-item"><span class="badge">${myClub.date}</span>
											<h4>
												<span class="label label-primary">${myClub.category}</span>&nbsp;
												<a
													href="${ctx}/club/main.do?community_id=${param.community_id}&club_id=${myClub.id}">${myClub.name}
													(회원:${myClub.members.size()})</a>
											</h4>
											<p>${myClub.description}</p>
											<c:set var="temp" value="false" />

												<c:forEach var="myClubManager" items="${myClub.manager}">
												<c:choose>
													<%-- <c:when test="${myClubManager.email ne loginId}">
														<button type="button" class="btn btn-default btn-sm"
															onclick="location.href='${ctx}/view/club/withdrawal.xhtml?community_id=${param.community_id}&club_id=${myClub.id}'">멤버탈퇴
															신청하기</button>
													</c:when> --%>
													<c:when test="${myClubManager.manager.email eq loginId}">
														<button type="button" class="btn btn-default btn-sm"
															onclick="location.href='${ctx}/view/club/remove.xhtml?community_id=${param.community_id}&club_id=${myClub.id}'">클럽
															삭제하기</button>
															<c:set var="temp" value="true" />
													</c:when>
												</c:choose>
											</c:forEach>
												<c:if test="${temp==false}">
												<button type="button" class="btn btn-default btn-sm"
															onclick="location.href='${ctx}/view/club/withdrawal.xhtml?community_id=${param.community_id}&club_id=${myClub.id}'">멤버탈퇴
															신청하기</button>
												</c:if></li>
									</c:forEach>
								</ul>

								<button type="button" class="btn btn-default btn-lg btn-block">클럽
									목록 더보기</button>
							</div>

							<!-- ★★★ 미가입 클럽 -->
							<div class="tab-pane fade" id="unjoinded">
								<div class="page-header">
									<h2 id="container">미가입 클럽</h2>
								</div>

								<ul class="list-group">
									<c:forEach var="notMyClub"
										items="${not_my_club_list}">
										<li class="list-group-item"><span class="badge">${notMyClub.date}</span>
											<h4>
												<!-- <span class="label label-info">추천</span>&nbsp; -->
												<span class="label label-primary">${notMyClub.category}</span>&nbsp;
												<a
													href="${ctx}/club/main.do?community_id=${param.community_id}&club_id=${notMyClub.id}">${notMyClub.name}
													(회원:${notMyClub.members.size()})</a>
											</h4>
											<p>${notMyClub.description}</p>
											<button type="button" class="btn btn-default btn-sm"
												onclick="location.href='${ctx}/club/join.do?community_id=${param.community_id}&club_id=${notMyClub.id}'">멤버
												가입하기</button></li>
									</c:forEach>
								</ul>

								<button type="button" class="btn btn-default btn-lg btn-block">클럽
									목록 더보기</button>
							</div>
						</c:when>
						<c:when test="${loginId eq null}">
							<!-- ★★★ 전체 클럽 -->
							<div>
								<div class="page-header">
									<h2 id="container">전체 클럽</h2>
								</div>

								<ul class="list-group">
									<c:forEach var="club" items="${club_list}">
										<li class="list-group-item"><span class="badge">${club.date}</span>
											<h4>
												<span class="label label-primary">${club.category}</span>&nbsp;
												<a
													href="${ctx}/club/main.do?community_id=${param.community_id}&club_id=${club.id}">${club.name}
													(회원:${club.members.size()})</a>
											</h4>
											<p>${club.description}</p>
											<button type="button" class="btn btn-default btn-sm"
												onclick="location.href='${ctx}/club/join.do?community_id=${param.community_id}&club_id=${club.id}'">멤버
												가입하기</button></li>
									</c:forEach>
								</ul>

								<button type="button" class="btn btn-default btn-lg btn-block">클럽
									목록 더보기</button>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>

		<!-- Footer ========================================================================================== -->
		<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>