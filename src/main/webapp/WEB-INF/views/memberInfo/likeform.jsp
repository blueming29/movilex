<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/informStyle/evalStyle.css">
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<script src="resources/js/informScript/dropdown_like.js"></script>
</head>
<body>
    <div class="like_wrap">
        <div class="like_container">
            <div class="like_grid">
                <div class="round_corner">
                    <!-- 헤더 -->
                    <section class="like_fixed_header">
                        <div class="like_header">
                            <div class="header_bar">
                                <button class="prev_btn"></button>
                                <span class="eval_title">보고싶어요</span>
                            </div>
                        </div>
                    </section>
                    <section class="fixed_selectbox" id="like_form_fixed_selectbox">
                        <div class="selectbox">
					        <dl class="dropdown">
					            <dt><span>담은 순</span></dt>
					            <dd>
					                <ul class="dropdown_list">
					                    <li><span id="sel_idx0">담은 순</span></li>
					                    <li><span id="sel_idx1">평점 순</span></li>
					                    <li><span id="sel_idx2">가나다 순</span></li>
					                    <li><span id="sel_idx3">신작 순</span></li>
					                    <li><span id="sel_idx4">구작 순</span></li>
					                </ul>
					            </dd>
					        </dl>
					    </div>
                    </section>
                    <section class="nontab_contents_container">
                        <!-- 전체탭 이미지 넣기 --><!-- 숨길 부분-->
                        <div class="hide_block_all">
                            <div class="movie_section">
                                <ul class="movie_items_ul">
                                	<c:forEach var="dto" items="${like_list }">
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
	                                                <c:choose>
	                                                	<c:when test="${dto.eval_point != 0}">
			                                                <div class="movie_eval">
			                                                    평가함 ★ ${dto.eval_point / 2 }
			                                                </div>
	                                                	</c:when>
	                                                	<c:otherwise>
			                                                <div class="movie_non_eval">
			                                                    평점 ★ ${dto.movie_point }
			                                                </div>
	                                                	</c:otherwise>
	                                                </c:choose>
	                                            </div>
	                            	        </li>
                                        </a>
                                	</c:forEach>
                                </ul>
                            </div>
						</div>
                    </section>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
