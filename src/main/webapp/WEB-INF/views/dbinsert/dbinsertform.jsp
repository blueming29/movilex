<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dbinsert</title>
<link rel="stylesheet" href="resources/css/dbinsert/dbinsertStyle.css" />
<script src="https://kit.fontawesome.com/ab523fec5e.js" crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<script>
$(document).ready(function(){
	var result = '${result}';
	console.log(result);
	if(result == 'success'){
		alert("DB 추가 성공!");
	} else if(result == 'fail'){
		alert("이미 있는 영화 정보입니다");
	}
	
	$(".search_code input").on('input', function(){
		if($(this).val() == ""){
			$(".search_btn button").css('color', '#ccc');
			$(".search_btn button").css('font-weight', 'normal');
		} else{
			$(".search_btn button").css('color', '#007bff');
			$(".search_btn button").css('font-weight', '700');
		}
	});
	
	
	$(".dbinsert_close").click(function(){
		location.href= "memberInfo.do";
	});
	
	// Ajax
	$(".search_btn").on("click", function() {
		var movie_code = $(".search_code input").val();
		
		console.log(movie_code)
		
	 	$.ajax({
		    type:'get',
		    url: 'http://localhost:9005/movilex/dbsearch.do?movie_code='+movie_code,
		    dataType:'JSON',
		    success: function(data) {
		        console.log('success');
		        // 이미지
		        $("#movie_img1").val(data.movie_img1);
		        $("#movie_img2").val(data.movie_img2);
		        $("#movie_img3").val(data.movie_img3);
		        $("#movie_img4").val(data.movie_img4);
		        $("#movie_img5").val(data.movie_img5);

		        // 영화정보
		        $("#movie_img").val(data.movie_img);
		        $("#movie_code").val(movie_code);
		        $("#movie_title").val(data.movie_title);
		        $("#movie_point").val(data.movie_point);
		        $("#movie_genre").val(data.movie_genre);
		        $("#movie_nation").val(data.movie_nation);
		        $("#movie_runtime").val(data.movie_runtime);
		        $("#movie_releasedate").val(data.movie_releasedate);
		        $("#movie_director").val(data.movie_director);
		        $("#movie_actor").val(data.movie_actor);
		        $("#movie_content").val(data.movie_content);
						     
		        $(".save_button button").show();
		    },
		    error:function (error) {
		        console.log('error');
		    }
		});    //ajax
	
	});
	
});


</script>
</head>
<body>
<div class="wrap">
	<div class="dbinsert_header">
		<button class="dbinsert_close_button">
			<span class="dbinsert_close"><i class="fas fa-arrow-left"></i></span>
		</button>
		<!-- 영화제목 -->
		<div class="dbinsert_movie_title">
			<h2>DB 영화 추가</h2>
		</div>
	</div>
	
	<div class="btn_area">
		<div class="search_code">
			<input type="text" placeholder="영화코드 검색" />
		</div>
		<div class="search_btn">
			<button>조회</button>
		</div>
	</div>
	<form action="dbinsertproc.do">	
		<div class="dbinsert_body">
			<div class="save_button">
				<button type="submit">영화등록</button>
			</div>
			<input type="hidden" id="movie_img" name="movie_img" />
			<input type="hidden" id="movie_img1" name="movie_img1" />
			<input type="hidden" id="movie_img2" name="movie_img2" />
			<input type="hidden" id="movie_img3" name="movie_img3" />
			<input type="hidden" id="movie_img4" name="movie_img4" />
			<input type="hidden" id="movie_img5" name="movie_img5" />
			코드 : <input type="text" id="movie_code" name="movie_code"  />	<br />
			제목 : <input type="text" id="movie_title" name="movie_title"  /> <br />
			평점 : <input type="text" id="movie_point" name="movie_point"  /> <br />
			장르 : <input type="text" id="movie_genre" name="movie_genre"  /> <br />
			국가 : <input type="text" id="movie_nation" name="movie_nation"  /> <br />
			상영시간 : <input type="text" id="movie_runtime" name="movie_runtime"  /> <br />
			개봉일 : <input type="text" id="movie_releasedate" name="movie_releasedate"  /> <br />
			감독 : <input type="text" id="movie_director" name="movie_director"  /> <br />
			배우 : <input type="text" id="movie_actor" name="movie_actor"  /> <br />
			줄거리 <br />
			<div class="dbinsert_content_textarea">
				<textarea name="movie_content" id="movie_content" class="dbinsert_content"></textarea>
			</div>		
		</div>
	</form>
</div>
</body>
</html>