<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="common/head.jsp"%>
<title>나무커뮤니티</title>
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<!-- Main Navigation ========================================================================================== -->
	<%@include file="common/main_navi.jsp"%>

	<!-- Header ========================================================================================== -->
	<header>
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="jumbotron">
						<h1>나무 커뮤니티와 함께!</h1>
						<p>나무 커뮤니티와 함께 특정 취미와 관심사, 특정 그룹 또는 조직에 관한 대화를 시작하세요.</p>
						<p>
							<a href="${ctx}/view/community/open.xhtml"
								class="btn btn-warning btn-lg">커뮤니티 개설하기</a>
						</p>
					</div>
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
									커뮤니티</a></li>
							<li class=""><a href="#unjoinded" data-toggle="tab">미가입
									커뮤니티</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="nav nav-tabs" style="margin-bottom: 15px;">
							<li class="active"><a data-toggle="tab">커뮤니티 목록</a></li>
						</ul>
					</c:otherwise>
				</c:choose>

				<!-- ★★★ Tab Panel -->
				<div id="communityList" class="tab-content">
					<c:choose>
						<c:when test="${loginId ne null}">
							<!-- ★★★ 가입 커뮤니티 -->
							<div class="tab-pane fade active in" id="joined">
								<div class="page-header">
									<h2 id="container">가입 커뮤니티</h2>
								</div>

								<ul class="list-group">
									<c:forEach var="myCommunity" items="${my_community_list}">
										<li class="list-group-item"><span class="badge">${myCommunity.date}</span>
											<h4>
												<a
													href="${ctx}/community/main.do?community_id=${myCommunity.id}">${myCommunity.name}
													(클럽:${myCommunity.getAllClub().size()})
													(회원:${myCommunity.members.size()})</a>
											</h4>
											<p>${myCommunity.description}</p> <c:choose>
												<c:when test="${myCommunity.manager.manager.email ne loginId}">
													<button type="button" class="btn btn-default btn-sm"
														onclick="location.href='${ctx}/view/community/withdrawal.xhtml?community_id=${myCommunity.id}'">멤버탈퇴
														신청하기</button>
												</c:when>
												<c:when test="${myCommunity.manager.manager.email eq loginId}">
													<button type="button" class="btn btn-default btn-sm"
														onclick="location.href='${ctx}/view/community/remove.xhtml?community_id=${myCommunity.id}'">커뮤니티
														삭제하기</button>
												</c:when>
											</c:choose></li>
									</c:forEach>
								</ul>

								<button type="button" class="btn btn-default btn-lg btn-block">커뮤니티
									목록 더보기</button>
							</div>

							<!-- ★★★ 미가입 커뮤니티 -->
							<div class="tab-pane fade" id="unjoinded">
								<div class="page-header">
									<h2 id="container">미가입 커뮤니티</h2>
								</div>

								<ul class="list-group">
									<c:forEach var="notMyCommunity"
										items="${not_my_community_list}">
										<li class="list-group-item"><span class="badge">${notMyCommunity.date}</span>
											<h4>
												<!-- <span class="label label-info">추천</span>&nbsp; -->
												<a
													href="${ctx}/community/main.do?community_id=${notMyCommunity.id}&join=false">${notMyCommunity.name}
													(클럽:${notMyCommunity.getAllClub().size()})
													(회원:${notMyCommunity.members.size()})</a>
											</h4>
											<p>${notMyCommunity.description}</p>
											<button type="button" class="btn btn-default btn-sm"
												onclick="location.href='${ctx}/view/community/join.xhtml?community_id=${notMyCommunity.id}'">멤버
												가입하기</button></li>
									</c:forEach>
								</ul>

								<button type="button" class="btn btn-default btn-lg btn-block">커뮤니티
									목록 더보기</button>
							</div>
						</c:when>
						<c:when test="${loginId eq null}">
							<!-- ★★★ 전체 커뮤니티 -->
							<div>
								<div class="page-header">
									<h2 id="container">전체 커뮤니티</h2>
								</div>

								<ul class="list-group">
									<c:forEach var="community" items="${community_list}">
										<li class="list-group-item"><span class="badge">${community.date}</span>
											<h4>
												<a
													href="${ctx}/community/main.do?community_id=${community.id}">${community.name}
													(클럽:${community.getAllClub().size()})
													(회원:${community.members.size()})</a>
											</h4>
											<p>${community.description}</p>
											<button type="button" class="btn btn-default btn-sm"
												onclick="location.href='${ctx}/view/community/join.xhtml?community_id=${community.id}'">멤버
												가입하기</button></li>
									</c:forEach>
								</ul>

								<button type="button" class="btn btn-default btn-lg btn-block">커뮤니티
									목록 더보기</button>
							</div>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>

		<!-- Footer ========================================================================================== -->
		<%@include file="common/footer.jsp"%>
	</div>


</body>
</html>
