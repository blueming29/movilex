 <%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script> -->
<script src="resources/js/all.min.js"></script>
<script src="resources/js/mainScript.js"></script>

<link rel="stylesheet" href="resources/css/mainStyle.css" />
<script>
	$(document).ready(function() {
		// 홈에 표시 들어오게
		sessionStorage.setItem("footer_idx", 0);
	});
</script>

</head>
<body>
	<div class="mainListContainer" style="margin-top: 70px;">
		<div class="mainListTitleRow">
			<p class="mainListTitle">박스오피스</p>
		</div>
		<div class="slideWrap">
			<ul class="boxoffice_slider">
				<% int i = 1; %>
				<c:forEach var="dto" items="${boxofficeList }" varStatus="status">
					<li>
						<a href="contentview.do?movie_code=${dto.movie_code }">
							<div class="movie_img_container">
								<c:choose>
									<c:when test="${dto.movie_img == null }">
										<img class="movie_img" src="http://placehold.it/250x360"/>
									</c:when>
									<c:otherwise>
										<img class="movie_img" src="${dto.movie_img}" />
									</c:otherwise>
								</c:choose>
								<% if(i <= 10) { %>
									<div class="ranking_badge">
										<%= i %>					
									</div>
								
								<% i++; } %>
								
							</div>
							<div class="movie_detail">
								<p class="main_title">${dto.movie_title }</p>
								<p class="spec">
									<c:if test="${dto.movie_releasedate != 0 }">
										${dto.movie_releasedate }
									</c:if>
									<span class="on_display">
										<c:if test="${dto.movie_releasedate != 0 }">
											/
										</c:if>
										${dto.movie_nation }
									</span>
								</p>
								<c:choose>
 									<c:when test="${dto.eval_point == 0 || dto.eval_point == null }">
										<p class="spec_point"><span class="on_display">평점</span> ★ ${dto.movie_point }</p>										
									</c:when>
									<c:otherwise>
										<p class="spec_eval_point"><span class="on_display">평가함</span> ★ ${dto.eval_point / 2}</p>									
									</c:otherwise>
								</c:choose>
							</div>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	
	<div class="mainListContainer">
		<div class="mainListTitleRow">
			<p class="mainListTitle">네티즌 평점 순</p>
		</div>
		<div class="slideWrap">
			<ul class="point_slider">
				<% i = 1; %>
				<c:forEach var="dto" items="${starPoint }" >
					<li>
						<a href="contentview.do?movie_code=${dto.movie_code }">
							<div class="movie_img_container">
								<c:choose>
									<c:when test="${dto.movie_img == null }">
										<img class="movie_img" src="http://placehold.it/250x360"/>
									</c:when>
									<c:otherwise>
										<img class="movie_img" src="${dto.movie_img}" />
									</c:otherwise>
								</c:choose>
								<% if(i <= 10) { %>
									<div class="ranking_badge">
										<%= i %>					
									</div>
								
								<% i++; } %>
								
							</div>
							<div class="movie_detail">
								<p class="main_title">${dto.movie_title }</p>
								<p class="spec">
									<c:if test="${dto.movie_releasedate != 0 }">
										${dto.movie_releasedate }
									</c:if>
									<span class="on_display">
										<c:if test="${dto.movie_releasedate != 0 }">
											/
										</c:if>
										${dto.movie_nation }
									</span>
								</p>
								<c:choose>
 									<c:when test="${dto.eval_point == 0 || dto.eval_point == null }">
										<p class="spec_point"><span class="on_display">평점</span> ★ ${dto.movie_point }</p>										
									</c:when>
									<c:otherwise>
										<p class="spec_eval_point"><span class="on_display">평가함</span> ★ ${dto.eval_point / 2}</p>									
									</c:otherwise>
								</c:choose>
							</div>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html> 