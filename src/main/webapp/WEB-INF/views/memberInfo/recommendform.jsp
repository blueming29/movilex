<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/informStyle/recStyle.css">
<script src="resources/js/informScript/dropdown_rec.js"></script>
<script>
	$(document).ready(function() {
		// 홈에 표시 들어오게
		sessionStorage.setItem("footer_idx", 2);
		$(".prev_btn").click(function(){
			location.href = 'main.do';
		});
	});
</script>
</head>
<body>
	    <div class="rec_wrap">
	        <div class="rec_container">
	            <div class="rec_grid">
	            	<c:if test="${empty similar_dto }">
					    <div class="pc_search_section">
							<div class="no_search_result">
								<img src="resources/image/searchImg/nosearch.png" alt="">
								<span class="no_result_message">추천영화를 보고싶으시다면 영화를 평가해 보세요! </span>
							</div>
						</div>
					</c:if>
				    <c:if test="${not empty similar_dto }">
		                <div class="round_corner">
		                    <!-- 헤더 -->
		                    <section class="rec_fixed_header">
		                        <div class="rec_header">
		                            <div class="header_bar">
		                                <button class="prev_btn"></button>
		                                <span class="rec_title">MOVILEX가 추천하는 영화</span>
		                            </div>
		                        </div>
		                    </section>
		
		                    <section class="fixed_selectbox">
		                        <div class="selectbox">
							        <dl class="dropdown">
							            <dt><span>전체</span></dt>
							            <dd>
							                <ul class="dropdown_list">
							                    <li><span id="sel_idx0">전체</span></li>
							                    <li><span id="sel_idx1">선호 장르</span></li>
							                    <li><span id="sel_idx2">선호 국가</span></li>
							                    <li><span id="sel_idx3">선호 배우</span></li>
							                </ul>
							            </dd>
							        </dl>
							    </div>
		                    </section>
		                    <section class="contents_container">
		                        <!-- 전체탭 이미지 넣기 --><!-- 숨길 부분-->
		                        <div class="hide_block_all">
		                            <div class="movie_section">
		                                <ul class="movie_items_ul">
		                                	<c:forEach var="dto" items="${similar_dto }">
		                                        <a href="contentview.do?movie_code=${dto.movie_code }">
			                                  		<li class="movie_item_li">
			                                            <div class="movie_poster">
			                                                <div class="poster_img_section">
			                                                    <img src="${dto.movie_img }" alt="" class="poster_img">
			                                                </div>
			                                            </div>
			
			                                            <div class="movie_info">
			                                                <div class="movie_title">
			                                                    ${dto.movie_title }
			                                                </div>
			                                                <div class="movie_spec">
			                                                    ${dto.movie_releasedate }
			                                                </div>
			                                                <div class="movie_spec">
			                                                    ${dto.movie_genre }
			                                                </div>
			                                            </div>
			                            	        </li>
		                                        </a>
		                                	</c:forEach>
		                                </ul>
		                            </div>
								</div>
		                    </section>
		                </div>
					</c:if>
	            </div>
	        </div>
	    </div>
	

</body>

</html>
