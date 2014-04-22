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
						<h1>나무커뮤니티 탈퇴하기</h1>
						<p>정말 나무 커뮤니티를 탈퇴하시겠습니까? 나무 커뮤니티를 탈퇴하시려면 확인버튼을 클릭하세요.</p>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Container ======================================================================================= -->
	<div class="container">
		<div class="row">
			<div class="col-lg-12">

				<div class="well">
					<form class="form-horizontal" action="${ctx}/user/withdrawal.do"
						method="post">
						<input type="hidden" name="community_name"
							value="${param.community_name}">
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