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
						<h1></h1>
						<p></p>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">

				<div class="page-header">
					<h2 id="container">클럽 삭제하기</h2>
				</div>
				<div class="well">
					<form class="form-horizontal" action="${ctx}/club/remove.do"
						method="post">
						<input type="hidden" name="community_id"
							value="${param.community_id}"> <input type="hidden"
							name="club_id" value="${param.club_id}">
						<fieldset>
							<div class="form-group">
								<div class="col-lg-10 col-lg-offset-2">
									<br />
									<button type="submit" class="btn btn-primary">확인</button>
									<button class="btn btn-default"
										onclick="history.back(); return false;">취소</button>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>

		</div>

		<!-- Footer ========================================================================================== -->

		<%@include file="../common/footer.jsp"%>
	</div>

</body>
</html>