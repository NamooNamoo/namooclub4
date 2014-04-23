<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-responsive-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${ctx}/main">나무커뮤니티</a>
		</div>
		<div class="navbar-collapse collapse navbar-responsive-collapse">
			<ul class="nav navbar-nav">
				<li><a href="${ctx}/main">커뮤니티 홈</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${loginId eq null}">
						<li><a href="${ctx}/user/login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">관리 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="${ctx}/manage/community">커뮤니티 관리센터</a></li>
								<li><a href="${ctx}/manage/club">클럽 관리센터</a></li>
							</ul></li>
						<li><a href="${ctx}/user/mypage">개인정보</a></li>
						<li><a>로그인 중 : ${loginId}</a></li>
						<li><a href="${ctx}/user/logout.do">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>