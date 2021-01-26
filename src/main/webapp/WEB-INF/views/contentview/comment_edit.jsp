<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>comment</title>
<link rel="stylesheet" href="resources/css/contentview/comment.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>

   $(document).ready(function(){
		// textarea 입력시 버튼 색상 변화
		$(".comment_content").on('input', function(){
			if($(this).val() == ""){
				$(".comment_save_button").css('color', '#ccc');
				$(".comment_save_button").css('font-weight', 'normal');
			} else{
				$(".comment_save_button").css('color', '#007bff');
				$(".comment_save_button").css('font-weight', '700');
			}
		});
	    $(".comment_close").click(function(){
	         history.back();
	    });
      
   });
   
</script>
</head>
<body>
<div class="warp">
   <div class="comment_header">
      <!-- 코멘트창을 닫고 contentveiw 창으로 이동 -->
      <button class="comment_close_button">
         <span class="comment_close">&times;</span>
      </button>
      <!-- 영화제목 -->
      <div class="comment_movie_title">
         <h2>${movie_title }</h2>
      </div>
   </div>   
   
   
   <div class="comment_body">
      <form action="comment_edit_proc.do">   
         <input type="hidden" name="movie_code" value="${comment.movie_code }" />   
         
         <input type="hidden" name="comment_seq" value="${comment.comment_seq }" />
         <!-- 코멘트작성 버튼 클릭시 작성후 contenview 창으로 이동 -->
         <div class="save_button">
         <input type="submit" class="comment_save_button" value="코멘트 수정" /> <br />
         </div>
         <div class="comment_content_textarea">
            <!-- 코멘트 작성창 -->         
            <textarea name="comment_content" class="comment_content">${comment.comment_content }</textarea>
         </div>      
      </form>
   </div>
   </div>
</body>
</html>