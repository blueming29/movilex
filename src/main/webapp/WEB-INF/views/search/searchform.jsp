<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/search/searchStyle.css" />
<script>
	$(document).ready(function() {
		$(".prev_btn").click(function() {
			location.href = "searchform.do";
		});

		// 검색에 표시 들어오게
		sessionStorage.setItem("footer_idx", 1);
		
		$.ajax({
    		type: "POST",
    		url: 'popsearch.do',
    		success: function(data){
    			console.log('success');
    			
    			var mob_li_text = "";
    			for(var i = 0 ; i < data.length ; i++){
    				mob_li_text += "<li class='popular_search_word'><span>" + data[i] + "</span></li>";
    			}
    			
    			$(".popular_search_list").html(mob_li_text);
    		}, error: function(error){
    			console.log('error');
    		}
    		
    	});
	});
</script>

</head>
<body>
	<div class="search_form_grid">
		<div class="mobile_search_fixed_header">
			<div class="mobile_search_header">
				<div class="mobile_search_container">
					<form action="searchresult.do">
						<input autocomplete="off" class="mobile_input_box"
							name="search_keyword" type="search"
							placeholder="작품 제목, 배우, 감독을 검색해보세요." value="${param.search_keyword }">
						<button class="mobile_search_btn" type="submit">
							<i class="fas fa-search"></i>
						</button>
					</form>
				</div>
			</div>
			<div class="search_word_list">
				<div class="search_word_inner_grid">
					<div class="search_word_inner_list">
						<c:if test="${cookie.last_search != null}">
							<!-- 최근 검색어 -->
							<div class="last_search_container">
								<div class="last_search">
									<div class="last_search_title">
										<h2>최근 검색어</h2>
									</div>
									<div class="clear_btn">
										<button>모두 삭제</button>
									</div>
								</div>
								<ul class="last_search_list">
								 <!-- 검색레이아웃 -->
								</ul>
							</div>
						</c:if>

						<!-- 인기 검색어 -->
						<div class="popular_search_container">
							<div class="popular_search">
								<div class="popular_search_title">
									<h2>인기 검색어</h2>
								</div>
							</div>
							<ul class="popular_search_list">
								 <!-- 검색레이아웃 -->
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="pc_search_section">
			<div class="no_search_result">
				<img src="resources/image/searchImg/nosearch.png" alt=""> <span
					class="no_result_message"> 검색 결과가 없습니다. 다른 검색어를 입력해 보세요 </span>
			</div>

		</div>
	</div>



</body>
</html>