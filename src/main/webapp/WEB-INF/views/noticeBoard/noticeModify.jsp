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
<script src="https://kit.fontawesome.com/ab523fec5e.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	 // textarea 입력시 버튼 색상 변화
	$(".input_content").on('input', function(){
		if($(this).val() == ""){
			$(".notice_save_button").css('color', '#ccc');
			$(".notice_save_button").css('font-weight', 'normal');
		} else{
			$(".notice_save_button").css('color', '#007bff');
			$(".notice_save_button").css('font-weight', '700');
		}
	});
	
	$(".write_close").click(function(){
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
				<h2>공지사항 수정</h2>
			</div>
		</div>

		<div class="board_body">
			<form action="noticemodifyproc.do" method="post">
				<input type="hidden" name="board_seq" value="${dto.board_seq }">
				<div class="save_button">
					<!-- CSS ISNT APPLY YET -->
					<input type="submit" class="notice_save_button" value="저장" />
				</div>

				<div class="board_write_area">
					<div class="board_content_area">
						<div>
							<span>글번호 : ${dto.board_seq }</span>
						</div>
						<input name="board_title" class="input_title" type="text" size="50" value="${ dto.board_title}">
						<textarea name="board_content" class="input_content" type="text" cols="30" rows="10" size="330" wrap="physical">${ dto.board_content}</textarea>
					</div>
				</div>


			</form>

		</div>
	</div>


</body>
</html>