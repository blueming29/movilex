<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- header 링크 -->
<script src="https://kit.fontawesome.com/ab523fec5e.js" crossorigin="anonymous"></script>

<script
  src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
</head>
<!-- main 링크 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<!-- chart.js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>

<!-- footer 링크 -->
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
 -->
 
 <!-- memberInfo 링크 -->
<!-- <script src="//code.jquery.com/jquery-1.11.0.min.js"></script> -->

<style>
#container {
	width: 100%;
	margin: 0px auto;
	text-align: center;
	border: 0px solid #bcbcbc;
}

#header {
	border: 0px solid #bcbcbc;
	background-color: white;
}

#content{
	width: 100%;
	float: left;
	text-align: left;
	border: 0px solid #bcbcbc;
}

#footer{
	clear: both;
	border: 0px solid #bcbcbc;
}
</style>

<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div id="container">
		<div id="header">
			<!-- xml에서 지정한 put-attribute의 이름 -->
			<tiles:insertAttribute name="header" />
		</div>
		<div id="content">
			<tiles:insertAttribute name="body" />
		</div>
		<div id="footer">
			<tiles:insertAttribute name="footer" />
		</div>

	</div>
</body>
</html>