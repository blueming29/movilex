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
<script src="https://kit.fontawesome.com/ab523fec5e.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	$(document).ready(function() {
		// textarea 입력시 버튼 색상 변화
		$(".input_content").on('input', function() {
			if ($(this).val() == "") {
				$(".notice_save_button").css('color', '#ccc');
				$(".notice_save_button").css('font-weight', 'normal');
			} else {
				$(".notice_save_button").css('color', '#007bff');
				$(".notice_save_button").css('font-weight', '700');
			}
		});

		$(".write_close").click(function() {
			history.back();
		});
	});
</script>
</head>
<body>
	<div class="wrap">


		<div class="notice_write_header">
			<button class="write_close_button">
				<!-- 목록으로 가기 버튼-->
				<span class="write_close"><i class="fas fa-arrow-left"></i></span>
			</button>
			<div class="writeview_title">
				<h2>문의사항 답변하기</h2>
			</div>
		</div>

		<div class="board_body">
			<!-- form tag delete? -->
				<input type="hidden" name="qna_seq" value="${dto.qna_seq }">


				<div class="board_write_area">
					<div class="board_content_area">
						<div>
							<span>글번호 :</span>
							<span>${dto.qna_seq }</span>
						</div>

						<div>
							<span>제목 :</span>
							<span>${ dto.qna_title}</span>
						</div>
						<div>
							<span>내용 :</span>
							<span>${ dto.qna_content}</span>
						</div>
					</div>
				</div>



			<form action="qnareplyproc.do" method="post">
				<div class="save_button">
					<input type="hidden" name="qna_seq" value="${dto.qna_seq }" />
					<input type="submit" class="notice_save_button" value="저장" />
				</div>

				<div class="board_write_area">
					<div class="board_content_area">
						<div>
							<input type="text" name="qna_title" class="input_title" placeholder="제목을 입력하세요"/>
						</div>
						<textarea name="qna_content" class="input_content" placeholder="내용을 입력하세요"></textarea>
					</div>
				</div>


			</form>

		</div>
	</div>


</body>
</html>