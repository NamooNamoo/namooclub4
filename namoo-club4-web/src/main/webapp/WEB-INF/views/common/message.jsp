<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@include file="../common/head.jsp"%>
<meta http-equiv="Refresh" content="3;URL=${ctx}/user/mypage.do">
<title>나무커뮤니티</title>
<style type="text/css">
body {
	padding-top: 100px;
	padding-bottom: 40px;
	background-color: #ecf0f1;
}

.info-header {
	max-width: 500px;
	padding: 15px 29px 25px;
	margin: 0 auto;
	background-color: #18bc9c;
	color: white;
	text-align: left;
	-webkit-border-radius: 15px 15px 0px 0px;
	-moz-border-radius: 15px 15px 0px 0px;
	border-radius: 15px 15px 0px 0px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.info-footer {
	max-width: 500px;
	margin: 0 auto 20px;
	padding-left: 10px;
}

.info-body {
	max-width: 500px;
	padding: 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	-webkit-border-radius: 0px 0px 15px 15px;
	-moz-border-radius: 0px 0px 15px 15px;
	border-radius: 0px 0px 15px 15px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.info-heading {
	margin-bottom: 15px;
}

.info-btn {
	text-align: center;
	padding-top: 20px;
}
</style>
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">

		<!-- header -->
		<div class="info-header">
			<h2 class="info-heading">회원정보수정</h2>
		</div>

		<!-- body -->
		<div class="info-body">

			<h3>안내</h3>
			<p>회원 정보가 수정되었습니다.<br />
			3초 후 회원정보 페이지로 이동합니다.</p>

			<div class="row info-btn">
				<button class="btn btn-large btn-default"
					onclick="location.href='${ctx}/user/mypage.do'">회원정보
					페이지로 이동</button>
			</div>
		</div>

		<!-- footer -->
		<div class="info-footer">
			<p>© NamooSori 2014.</p>
		</div>
	</div>

</body>
</html>