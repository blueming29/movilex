<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/notice/noticeStyle.css">

<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://kit.fontawesome.com/ab523fec5e.js"
	crossorigin="anonymous"></script> -->
<script>
	$(document).ready(function() {
		$(".open_more").click(function() {
			var btn_idx = $(".open_more").index(this);
			var toggle_id = "#num" + btn_idx;
			$(toggle_id).toggle(300);
		});
		$(".prev_btn").click(function() {
			location.href = 'memberInfo.do';
		});
	});
</script>

</head>


<body>
	<div class="notice_wrap">
		<div class="notice_container">
			<div class="notice_grid">
				<div class="round_corner">
					
					<!-- 헤더 -->
					<section class="notice_fixed_header">
					<div class="notice_header">
						<div class="header_bar">
							<button class="prev_btn">
								<i class="fas fa-arrow-left"></i>
							</button>
							<span class="notice_title">공지사항</span>
							<!-- sessionID가 ADMIN일 경우 글쓰기 버튼 등장 -->
							<c:if test="${sessionID=='admin' }">
								<div class=admin_add>
									<button class="submit_btn" onclick="location.href ='/movilex/noticewrite.do'">글쓰기</button>
								</div>
							</c:if>
						</div>

					</div>
					</section>
					<c:forEach items="${noticeView }" var="boardDto" varStatus="status">
						<div class="notice_section">
							<section>
								<div class="title_area">
									<!-- 공지사항 제목 -->
									<span class="section_title">${boardDto.board_title}</span> 
									<!-- 공지사항 날짜 -->
									<span class="section_title_counter">${boardDto.board_date}</span>
									<!-- 공지사항 펼치기 -->
									<button class="open_more">
										<i class="fas fa-caret-down"></i>
									</button>
								</div>
								<div class="content_section">
									<div class="content_section_toggle" id="num${status.index }">
										<div>${boardDto.board_content}</div>
									</div>
								</div>
								<c:if test="${sessionID =='admin'}">
									<div class=admin_update>
										<!-- 수정버튼 -->
										<button class="modify_btn" onclick="location.href='/movilex/noticemodify.do?board_seq=${boardDto.board_seq}'">수정</button>
										<!-- 삭제버튼 -->
										<button class="delete_btn" onclick="location.href ='/movilex/noticedeleteproc.do?board_seq=${boardDto.board_seq}'">삭제</button>
									</div>
								</c:if> 
							</section>
						</div>
					</c:forEach>
	
				</div>
			</div>
		</div>
	</div>
</body>

</html>
