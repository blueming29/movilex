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
							<span class="notice_title">QnA</span>
							<div class=admin_add>
								<button class="submit_btn"
									onclick="location.href ='qnawrite.do'">문의하기</button>
							</div>
						</div>
					</div>
					</section>
					<section>
						<!-- 제목 -->
						<div class="notice_section">
							<ul>
								<c:forEach items="${qnaView }" var="boardDto" varStatus="status">
									<li>
										<c:choose>
											<c:when test="${sessionID == boardDto.user_id && sessionID != 'admin' }">
												<div class="title_area highlight">
											
											</c:when>
											<c:otherwise>
												<div class="title_area">
											</c:otherwise>
										</c:choose>
											<!-- indent값 -->
											<span class="section_title">
												<c:forEach begin="1" end="${boardDto.qna_indent }" varStatus="cnt">
													<!-- <i class="fab fa-replyd"></i> -->
													<img class="reply" src="resources/image/reply.png" alt="reply" />
												</c:forEach> ${boardDto.qna_title}
											</span>
											<span class="section_title_counter">${boardDto.qna_date}</span>
											<span class="section_title_counter">작성자:${boardDto.user_id}</span>
											<button class="open_more">
												<i class="fas fa-caret-down"></i>
											</button>
										</div>
										
										<!-- 내용 -->
										<div class="content_section">
											<div class="content_section_toggle" id="num${status.index }">
												<div>${boardDto.qna_content}</div>
											</div>
										</div>
										
										<!-- 답변수정삭제 -->
										<div class=admin_update>
											<c:if test="${sessionID=='admin' }">
												<button class="modify_btn"
													onclick="location.href='qnareply.do?qna_seq=${boardDto.qna_seq}'">답변</button>
												<button class="delete_btn"
													onclick="location.href ='qnadeleteproc.do?qna_seq=${boardDto.qna_seq}'">삭제</button>
											</c:if>
			
											<c:if test="${sessionID==boardDto.user_id }">
												<button class="modify_btn"
													onclick="location.href='qnamodify.do?qna_seq=${boardDto.qna_seq}'">수정</button>
												<c:if test="${sessionID != 'admin' }">
													<button class="delete_btn"
														onclick="location.href ='qnadeleteproc.do?qna_seq=${boardDto.qna_seq}'">삭제</button>
												</c:if>
											</c:if>
										</div>
									</li>
								</c:forEach>
							</ul>
						</div>
					</section>
				</div>
			</div>
		</div>
	</div>
</body>

</html>