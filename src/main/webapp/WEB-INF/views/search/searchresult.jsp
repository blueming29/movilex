<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/search/searchResultStyle.css" />
<!-- <script src="resources/js/cookie/jquery.cookie.js"></script>
 -->
<script>
	$(document).ready(function() {
		$(".prev_btn").click(function() {
			location.href = "searchform.do";
		});

		// 검색에 표시 들어오게
		sessionStorage.setItem("footer_idx", 1);
	});
</script>


</head>
<body>
	<div class="search_wrap">
		<div class="search_result_container">
			<div class="search_grid">
				<div class="round_corner">
					<!-- 헤더 -->
					<section class="search_fixed_header">
					<div class="search_header">
						<div class="header_bar">
							<button class="prev_btn">
								<i class="fas fa-arrow-left"></i>
							</button>
							<span class="search_title">검색결과</span>
						</div>

					</div>
					</section>
					<div class="search_result_movie_section">
						<div class="search_result_row">
							<ul class="search_result_ul">
								<c:forEach var="search_dto" items="${search_list }">
									<li class="search_result_li">
										<a href="contentview.do?movie_code=${search_dto.movie_code }">
											<div class="movie_img_block">
												<div class="movie_img">
													<img src="${search_dto.movie_img }" alt="">
												</div>
											</div>
											<div class="movie_info_title">
												<div class="search_result_title">${search_dto.movie_title }</div>
												<div class="search_result_spec">${search_dto.movie_releasedate } ・ ${search_dto.movie_nation }</div>
											</div>
										</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>