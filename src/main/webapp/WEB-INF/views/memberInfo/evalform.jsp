<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/informStyle/evalStyle.css">
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
 -->
<script src="resources/js/informScript/dropdown.js"></script>

</head>

<body>
    <div class="eval_wrap">
        <div class="eval_container">
            <div class="eval_grid">
                <div class="round_corner">
                    <!-- 헤더 -->
                    <section class="eval_fixed_header">
                        <div class="eval_header">
                            <div class="header_bar">
                                <button class="prev_btn"></button>
                                <span class="eval_title">평가한 작품들</span>
                            </div>
                            <ul class="tabs_ul">
                                <li class="tabs_li_all selected">
                                    전체
                                    <div class="border_slide all"></div>
                                </li>
                                <li class="tabs_li_point">
                                    별점 순
                                    <div class="border_slide point"></div>    
                                </li>
                            </ul>
                        </div>
                    </section>
                    <section class="fixed_selectbox">
                        <div class="selectbox">
					        <dl class="dropdown">
					            <dt><span>담은 순</span></dt>
					            <dd>
					                <ul class="dropdown_list">
					                    <li><span id="sel_idx0">담은 순</span></li>
					                    <li><span id="sel_idx1">평점 순</span></li>
					                    <li><span id="sel_idx2">가나다 순</span></li>
					                    <li><span id="sel_idx3">개봉일 순</span></li>
					                </ul>
					            </dd>
					        </dl>
					    </div>
                    </section>
                    <section class="tab_contents_container" >
                    
                    
                    
                        <!-- 전체탭 이미지 넣기 --><!-- 숨길 부분-->
                        <div class="hide_block_all">
                        	<div class="hide_block_by_select">
	                            <div class="movie_section">
	                                <ul class="movie_items_ul">
	                                
	                                	<c:forEach var="dto" items="${eval_list }">
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
		                                                <div class="movie_eval">
		                                                    평가함 ★ ${dto.eval_point/2 }
		                                                </div>
		                                            </div>
			                                    </li>
	                                		</a>
	                                	</c:forEach>
	                                </ul>
                                </div>
                            </div>
                      	</div>
                      
                      
                      
                        <!-- 별점순 이미지 넣기--><!-- 숨길 부분-->
                        <div class="hide_block_point">
                            <div class="point_movie_section">
	                            <c:forEach var="i" begin="1" end="10">
	                                <section>
	                                    <div class="title_area">
	                                        <span class="section_title">${(10 - (i - 1)) / 2 }점 준 영화</span>
	                                        <span class="section_title_counter" id="cnt${i - 1 }"></span>
	                                        <button class="open_more" ></button>
	                                    </div>
	                                    
	                                    <div class="movie_section">
	                                       <div class="movie_section_toggle" id="num${i - 1 }">
	                                            <ul class="movie_items_ul">
	                                            	<c:forEach var="obp_dto" items="${orderByPoint }" >
	                                            		<c:if test="${10 - (i - 1) == obp_dto.eval_point}">
			                                                <a href="contentview.do?movie_code=${obp_dto.movie_code }">
				                                                <li class="movie_item_li">
			                                                        <div class="movie_poster">
			                                                            <div class="poster_img_section">
			                                                                <img src="${obp_dto.movie_img }" alt="" class="poster_img">
			                                                            </div>
			                                                        </div>
			                                                        <div class="movie_info">
			                                                            <div class="movie_title">
																			${obp_dto.movie_title }
			                                                            </div>
			                                                        </div>
				                                                </li>
		                                                    </a>
	                                            		</c:if>
	                                                </c:forEach>
	                                            </ul>
	                                        </div>
	                                    </div>  
	                                </section>
	                            </c:forEach>
                            </div>
                        </div>
                    </section>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
