<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <%@include file="../common/head.jsp"%>
    <title>나무커뮤니티</title>
<script type="text/javascript">
function levelCheck(id) {
	document.getElementById("level").value = id.value;
	if (id.value == 3) {
		/* var inputElems = document.getElementById("checked_member");
		var count = 0;
		for (var i = 0; i < inputElems.length; i++) {
			if (inputElems[i].checked == true) {
				alert("ㅇㅇ");
				count++;
			}
		} */

		var numberOfCheckboxesSelected = $('input[type=checkbox]:checked').length;
		if( numberOfCheckboxesSelected != 1 ){
			alert("대표 관리자를 한 명만 선택해주세요");
			return false;
		}
	}
}
</script>
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


<!-- ★★★ Aside Menu -->
        <!-- <div style="z-index:1020" class="col-xs-12 col-sm-3 col-md-3 col-lg-3">
            <div class="list-group panel panel-success">
                <div class="panel-heading list-group-item text-center hidden-xs">
                    <h4>커뮤니티 관리</h4>
                </div>
                <div id="cat-navi">
                    <a href="./commClubList.html" class="list-group-item hidden-xs active">클럽관리</a>
                    <a href="./commMemberList.html" class="list-group-item hidden-xs">회원관리</a>
                    <select class="form-control">
                        <option selected="selected" value="#">클럽관리</option>
                        <option value="#">회원관리</option>
                    </select></div>
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
        </div> -->

        <!-- ★★★ Contents -->
        <div class="col-sm-12 col-lg-12">
            <div class="page-header2">
                <h3>회원관리</h3>
            </div>

            <!-- ★★★ Tab Menu -->
            <ul class="nav nav-tabs" style="margin-bottom: 15px;">
                <li class="active"><a href="#all" data-toggle="tab">전체회원</a></li>

            </ul>


            <!-- ★★★ Tab Panel -->
            <div id="communityList" class="tab-content">
                <!-- ★★★ 전체회원 -->
                <div class="tab-pane fade active in" id="all">




					<div class="table-responsive">
					<c:if test="${club.manager.size() ne 1}">


                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="text-center">번호</th>
                                <th class="text-center">ID</th>
                                <th class="text-center">이름</th>
                                <th class="text-center">레벨</th>
                                <!-- <th class="text-center">권한부여</th> -->
                            </tr>
                            </thead>
								<tbody>
									<!--<tr><td colspan="5" class="text-center">가입한 회원이 없습니다.</td></tr>-->
									<c:set var="count" value="0" />
									<c:forEach var="manager" items="${club.manager}">
										<c:if test="${manager.manager.email ne loginId}">
										<tr>
											<c:set var="count" value="${count+1}" />
											<td class="text-center">${count}</td>
											<td class="text-center"><a href="${ctx}/manage/club_mem_detail.do?id=${manager.manager.email}&level=${manager.level}&club_id=${param.club_id}">${manager.manager.email}</a></td>
											<td class="text-center">${manager.manager.name}</td>
											<td class="text-center">${manager.level}</td>
										</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							</c:if>
                    </div>
                    <!-- ★★★ 회원목록 -->
                    <div class="table-responsive">

                    <form action="${ctx}/manage/club_mem.do" method="post">
                    	<input type="hidden" name="club_id" value="${param.club_id}">
						<input type="hidden" id="level" name="level" value="0">
						<c:if test="${members.size()!=0}">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="text-center">번호</th>
                                <th class="text-center">ID</th>
                                <th class="text-center">이름</th>
                                <th class="text-center">레벨</th>
                            </tr>
                            </thead>
								<tbody>
									<!--<tr><td colspan="5" class="text-center">가입한 회원이 없습니다.</td></tr>-->
									<c:forEach var="member" items="${club.members}" varStatus="managerCount">


										<tr>
											<td class="text-center">${managerCount.count}</td>
											<td class="text-center"><a href="${ctx}/manage/club_mem_detail.do?id=${member.member.email}&level=1&club_id=${param.club_id}">${member.member.email}</a></td>
											<%-- <td><a href="./commViewMember.html">${member.name}</a></td> --%>
											<td class="text-center">${member.member.name}</td>
											<td class="text-center">1</td>
										</tr>

									</c:forEach>
								</tbody>
							</table>

							</c:if>
                    </form>
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