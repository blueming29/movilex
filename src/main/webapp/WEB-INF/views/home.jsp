<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="main.do">메인화면 바로가기</a> <br />
<a href="eval_genre_proc.do">장르별 추천 바로가기</a> <br />
<a href="eval_nation_proc.do">국가별 추천 바로가기</a> <br />
<a href="eval_actor_proc.do">배우별 추천 바로가기</a> <br />
<a href="eval_total_proc.do">전체 추천 바로가기</a> <br />
</body>
</html>
