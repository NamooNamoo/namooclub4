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

			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="text-center">ID</th>
						<th class="text-center">레벨</th>
					</tr>
				</thead>
				<tbody>
					<!--<tr><td colspan="5" class="text-center">가입한 회원이 없습니다.</td></tr>-->
					<tr>
						<td>${param.id}</td>
						<td class="text-center">${param.level}</td>
					</tr>
				</tbody>
			</table>
			<c:choose>
				<c:when test="${param.level==1}">
					<th class="text-right"><button class="btn btn-default" value="3" onclick="location.href='${ctx}/manage/club_mem?id=${param.id}&level=3&club_id=${param.club_id}'">대표 관리자</button></th>
					<th class="text-right"><button class="btn btn-default" value="2" onclick="location.href='${ctx}/manage/club_mem?id=${param.id}&level=2&club_id=${param.club_id}'">서브 관리자</button></th>
				</c:when>
				<c:when test="${param.level==2}">
					<th class="text-right"><button class="btn btn-default" value="3" onclick="location.href='${ctx}/manage/club_mem?id=${param.id}&level=3&club_id=${param.club_id}'">대표 관리자</button></th>
					<th class="text-right"><button class="btn btn-default" value="2" onclick="location.href='${ctx}/manage/club_mem?id=${param.id}&level=1&club_id=${param.club_id}'">일반 멤버</button></th>
				</c:when>
			</c:choose>

		</div>
<!-- Footer ========================================================================================== -->
		<%@include file="../common/footer.jsp"%>
	</div>
</body>
</html>