<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@include file="../common/head.jsp"%>
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
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
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
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
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
        <h2 class="info-heading">안내</h2>
    </div>

    <!-- body -->
		<div class="info-body">

			<h3>안내 타이틀</h3>
			<p>${message}</p>

			<form class="form-horizontal" action="${ctx}/${url}" method="post">
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

		<!-- footer -->
    <div class="info-footer">
        <p>© NamooSori 2014.</p>
    </div>
</div>


</body>
</html>