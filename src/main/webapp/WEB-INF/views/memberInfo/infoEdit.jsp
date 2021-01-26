<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/informStyle/infoStyle.css">
<script src="https://kit.fontawesome.com/ab523fec5e.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
$(document).ready(function(){
	$("#input_pw").on('input', function(){
		if($(this).val() == ""){
			$(".notice_save_button").addClass('hidden');
		} else{
			$(".notice_save_button").removeClass('hidden');
		}
	});
	
	$(".write_close").click(function(){
		location.href="memberInfo.do";
	});
	
	$("#input_current_pw").on('input', function(){
		var user_pw = $(this).val();
		if(user_pw == "" || user_pw == null){
			$(".error_msg").addClass('hidden');
		} else{
			$.ajax({
				type: "POST",
				url: "password_check.do",
				data: { "user_pw" : user_pw },
				success: function(data){
	    			console.log('success');
	    			if(data == 1){
	    				$(".user_pw").removeClass('hidden');
	    				$(".error_msg").addClass('hidden');
	    			} else{
	    				$(".user_pw").addClass('hidden');
	    				$(".error_msg").removeClass('hidden');
	    			}
				},
				error: function(error){
	    			console.log('error');
	    		}
				
			});
		}
	});
	
	
    // 회원정보 수정 
    $("#info_edit_form").submit(function(e) {
    	
    	e.preventDefault(); 
    	
    	var form = $(this);
    	var url = form.attr('action');
    	
    	$.ajax({
    		type: "POST",
    		url: url,
    		data: form.serialize(), 
    		success: function(data)
    		{
    			console.log('success');
    			if(data == 1){
	    			alert("정보 수정완료!");
    				location.href="memberInfo.do";
    			} else {
    				alert("수정하실 내용을 입력해 주세요");
    			}
    		},
    		error: function(error){
    			console.log('error');
    		}
    		
    	});
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
				<h2>개인정보 수정</h2>
			</div>
		</div>

		<div class="board_body">
			<form action="info_edit_proc.do" method="post" id="info_edit_form">
				<div class="save_button">
					<!-- CSS ISNT APPLY YET -->
					<input type="submit" class="notice_save_button hidden" value="수정" />
				</div>

				<div class="board_write_area">
					<div class="board_content_area">
						<div class="user_name">
							<span>이름 : </span><input type="text" name="user_name" class="input_title" value="${info_dto.user_name }" />
						</div>
						<div class="user_current_pw">
							<span>현재 비밀번호 : </span><input type="password" class="input_title" id="input_current_pw" placeholder="현재 비밀번호를 입력하세요" />
						</div>
						<div class="error_msg hidden">
							비밀번호가 일치하지 않습니다
						</div>
						<div class="user_pw hidden">
							<span>수정할 비밀번호 : </span><input type="password" name="user_pw" class="input_title" id="input_pw" placeholder="변경하실 비밀번호를 입력하세요" />
						</div>
					</div>
				</div>


			</form>

		</div>
	</div>

</body>
</html>