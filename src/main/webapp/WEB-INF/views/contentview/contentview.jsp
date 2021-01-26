<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>contentview</title>
<link rel="stylesheet" href="resources/css/contentview/contentview.css" />
<script src="resources/js/contentview/contentview.js"></script>
<script>
	$(document).ready(function() {
		// 홈에 표시 들어오게
		sessionStorage.setItem("footer_idx", 0);
	});
</script>

<body style="margin: 0px;">
	<div class="content_view_wrap">
		<div class="content_view_header">

			<!-- 상단포스터 -->
			<section class="content_view_poster">
				<div class="content_view_poster_part1"></div>

				<!-- DB movie_img --> 
				<div class="content_view_poster_part2" >
					<img src="${stillcut_img}" />
				</div>
				<div class="content_view_poster_part3"></div>
			</section>

			<!-- 상단정보 -->	
	<section class="content_view_info">		
			<!-- info의 포스터 -->
			<div class="content_view_info_poster">
				<div id="content_view_info_poster"><img class="content_view_info_movie_img" src="${content_list.movie_img}" /></div>
			</div>

			<!-- info의 내용 -->
			<div class="content_view_info_content">
			
			<h1 class="content_view_title">${content_list.movie_title }</h1> <br />
			<div class="content_view_info2">${content_list.movie_releasedate } · ${content_list.movie_genre } · ${content_list.movie_nation }</div>
			<!-- 자동정렬시 주의_알고리즘에 의한 평점 | ★~ 바로 옆에 있어야 됨 안그러면 줄바뀜 -->
			<div class="content_view_point">평점 ★ ${content_list.movie_point }</div>
	      	<div class="content_view_want">
	      		<div class="content_view_want_button" id="want">
		      		<form action="likeproc.do" onsubmit="return loginCheck()">
		      			<input type="hidden" name="movie_code" value="${content_list.movie_code }" />
				      	<button class="content_view_see" type="submit">
				      	   	보고싶어요
				      		<i class="fas fa-caret-down"></i>
				      	</button>
				      	
		      		</form>
			 	</div>
	      		<div class="content_view_want_button" id="nowant">
		      		<form action="like_delete_proc.do">
		      			<input type="hidden" name="movie_code" value="${content_list.movie_code }" />
				      	<button class="content_view_see" type="submit">
				      	   	취소하기
				      		<i class="fas fa-caret-down"></i>
				      	</button>
		      		</form>
			 	</div>
				<section class="content_view_star"> 
					 <%-- <input type="hidden" class="eval_point" value="${eval_count }" /> --%>
						<!-- 별 -->
						<div class="content_view_star_title">평가하기</div>
					    <div class="star-box">
					        <span class="star content_view_star_left"></span>
					        <span class="star content_view_star_right"></span>
					
					        <span class="star content_view_star_left"></span>
					        <span class="star content_view_star_right"></span>
					
					        <span class="star content_view_star_left"></span>
					        <span class="star content_view_star_right"></span>
					
					        <span class="star content_view_star_left"></span>
					        <span class="star content_view_star_right"></span>
					
					        <span class="star content_view_star_left"></span>
					        <span class="star content_view_star_right"></span>
					    </div>
				</section>
		      	
	      	</div>
		</div>
		
		</div>	
	</section>


		<div class="content_view_body">
			<!-- 코멘트 남기기 -->
			<section class="content_view_comment_write">
				<!-- 문구 : user님의 의견을 적어주세요 -->
				<div class="content_view_comment_write_id">
					<h3 class="comment_id">${memberDto.user_name } 님의 생각을 글로 적어보세요.</h3>
				</div>
				<!-- 버튼 : 코멘트 남기기 -->
				<form action="comment.do" class="comment_write">
					<input type="hidden" name="movie_code" value="${content_list.movie_code }" />
					<input type="hidden" name="eval_point" value="${eval_count }" />
					<input type="hidden" name="movie_title" value="${content_list.movie_title}" />
					<button class="comment_button">코멘트 남기기</button>				
				</form>
			</section>
				
				
			<!-- 내가 작성한 코멘트 -->
	         <section class="content_view_my_comment">
	
	            <!-- 내가 남긴 코멘트 -->
	            <div class="content_view_my_comment_content">
	               <c:forEach items="${comment }" var="cdto">
	                  <c:if test="${sessionID == cdto.user_id}">
	                     <input type="hidden" id="comment_seq"
	                        value="${cdto.comment_seq }" />
	                     ${cdto.comment_content }
	                  </c:if>
	               </c:forEach>
	            </div>
	            <!-- 삭제 | 수정 버튼 -->
	            <div class="content_view_my_comment_button">
	
	               <!-- 코멘트 수정 -->
	               <button class="comment_edit">
	                  <img src="resources/image/contentImg/pencil.png" alt="edit"
	                     id="pencil" /> 수정
	               </button>
	               |
	
	               <!-- 코멘트 삭제 -->
	               <button class="comment_delete">
	                  <img src="resources/image/contentImg/trash.png" alt="delete"
	                     id="trash" /> 삭제
	               </button>
	
	            </div>
	
	         </section>
			<!-- 영화정보 -->
			<section class="content_view_information">

			<div class="content_view_body_title">
				<!-- 기본정보_타이틀 -->
				<h2 class="content_view_information1">기본정보</h2>
			</div>

			<div class="content_view_body_content">
				<!-- 영화제목 -->
				<div class="content_view_information_title">${content_list.movie_title }</div>
				<!-- 영화 개봉일, 국가, 장르 -->
				<div class="content_view_information_info">${content_list.movie_releasedate } · ${content_list.movie_nation } · ${content_list.movie_genre }</div>
				<!-- 영화 상영시간 -->
				<div class="content_view_information_runtime">${content_list.movie_runtime }</div>
				<!-- 영화 줄거리 -->
				<div class="content_view_information_content">${content_list.movie_content }</div> 

			</div>

			</section>

			<!-- 영화제작 -->
			<section class="content_view_production">
				<div class="content_view_body_title">
					<h2 class="content_view_production1">출연 | 제작</h2>
				</div>
				<div class="content_view_body_content">
					<div class="content_view_production_director">${content_list.movie_director }</div>
					<div class="content_view_production_actor">${content_list.movie_actor }</div>
				</div>
			</section>

			<!-- 별점그래프 -->
			<section class="content_view_graph">
				<div class="content_view_body_title">
					<h2>별점 그래프</h2>
				</div>
				<div class="content_view_body_content">
					<canvas id="myChartOne" ></canvas>
				</div>
			</section>

			<!-- 영화코멘트 -->
			<c:if test="${not empty comment}">
				<section class="content_view_comment">
					<div class="content_view_body_title">
						<h2 class="content_view_comment_title">코멘트</h2>
					</div>
		
					<div class="content_view_body_content">
						<!-- 코멘트 -->
						<ul class="content_view_comment00">
							<c:forEach items="${comment }" var="cdto" varStatus="status">
								<!-- 코멘트 창1 -->
								<li class="content_view_comment_comment">
									<!-- 코멘트_이름 -->
									<div class="content_view_comment_id">
									<!-- 이름을 누르면 마이페이지로 이동 -->
									<a id="content_view_comment_id" href="#">${user_name_by_comment[status.index] }</a>
									
		
									<!-- id가 준 별점 -->
									<div class="content_view_comment_point">★ ${cdto.user_point / 2 }</div>
									</div> 
									<!-- 코멘트_내용 -->
									<div class="content_view_comment_content">${cdto.comment_content }</div>
									
									<c:set var="uibc" value="${user_id_by_comment[status.index] }"></c:set>
									
									<c:choose>
										<c:when test="${fn:contains(uibc, sessionID) }">
											<!-- 코멘트_좋아요 수 -->
											<div class="content_view_comment_like_count">
												<i class="fas fa-thumbs-up"></i>
												<span>${cdto.comment_like }</span>
											</div>
											<!-- 코멘트_좋아요 버튼 -->
											<div class="content_view_comment_like">
												<input type="hidden" class="comment_seq" value="${cdto.comment_seq }" />
												<button class="like_btn checked_like">좋아요</button>
											</div>
										
										</c:when>
										
										<c:otherwise>
											<!-- 코멘트_좋아요 수 -->
											<div class="content_view_comment_like_count">
												<i class="far fa-thumbs-up"></i>
												<span>${cdto.comment_like }</span>
											</div>
											<!-- 코멘트_좋아요 버튼 -->
											<div class="content_view_comment_like">
												<input type="hidden" class="comment_seq" value="${cdto.comment_seq }" />
												<button class="like_btn">좋아요</button>
											</div>
										</c:otherwise>
									</c:choose>
								</li>
							</c:forEach>
		
						</ul>
					</div>
				</section>
			</c:if>
		</div>

		<div class="content_view_footer"></div>
	</div>

<script>
	var star_point_arr = ${star_points};
	let myChartOne = document.getElementById('myChartOne').getContext('2d');
	let barChartOne = new Chart(myChartOne,{
		type: 'bar',
		data: {
			labels: ['0.5', '1', '1.5', '2', '2.5', '3', '3.5', '4', '4.5', '5', ''],
			datasets: [{
				data: [
					star_point_arr[0],
					star_point_arr[1],
					star_point_arr[2],
					star_point_arr[3],
					star_point_arr[4],
					star_point_arr[5],
					star_point_arr[6],
					star_point_arr[7],
					star_point_arr[8],
					star_point_arr[9],
					0,
					
				],

				backgroundColor: '#28a745',

			}]
		},
		options: {
			legend: {display: false},
			responsive: false,
			scales: {
				yAxes: [{
					ticks: {
						beginAtZero: true,
						fontColor: "transparent",
					},
					gridLines: {
						display: false,
					}
				}],
				xAxes: [{
					barPercentage: 1.1,
					ticks: {
						fontSize : 15,
					},
					gridLines: {
						display: false,
					}
				}],
			}
		}
	
	});
	var sessionID = '${sessionID}';
	
	var like_check = ${like_check};
	

	
	
	
	
	
	if(like_check == 0 ){
		$("#nowant").addClass("hidden");
	} else {
		$("#want").addClass("hidden");
	}
	
	function loginCheck(){
		if(sessionID == "null" || sessionID == ""){
			alert("로그인이 필요합니다.");
			return false;
		} else {
			return true;
		}
		
	}
	
	
	
	// Ajax
	$(".like_btn").on("click", function() {
		var idx = $(".like_btn").index(this);
		var comment_seq = $('.comment_seq').eq(idx).val();	
		var total_like = $(".content_view_comment_like_count span").eq(idx).text();
		
 		$.ajax({
		    type:'get',
		    async:false, //false가 기본값임 - 비동기
		    url:'http://localhost:9005/movilex/comment_like_proc.do',
		    dataType:'text',
		    data:'comment_seq=' + comment_seq + '&sessionID=' + sessionID,
		    success: function(data, textStatus) {
		        console.log('success');
		    	
		    	if(data == 0){
		    		$(".content_view_comment_like_count i").eq(idx).attr('class', 'fas fa-thumbs-up');
 		    		$(".content_view_comment_like_count span").eq(idx).text(Number(total_like) + 1);
		    		$(".like_btn").eq(idx).addClass("checked_like");
		    		
		    	} else {
		    		$(".content_view_comment_like_count i").eq(idx).attr('class', 'far fa-thumbs-up');
 		    		$(".content_view_comment_like_count span").eq(idx).text(Number(total_like) - 1);
 		    		$(".like_btn").eq(idx).removeClass('checked_like');
		    		
		    	}
		    },
		    error:function (data, textStatus) {
		        console.log('error');
		    }
		});    //ajax
	
	});
	
	
	
	
	
	
	
	
	
	// click : 평점 수정 및 삭제 처리 해줘야함
	var star_point = ${eval_count };
	
	
	// sessionID가 있을때
	// star_point = 0(평가 안했을 때) 일 때 : 이 영화에 대한 나의 평점이 없음	
	// - > display: none;
	
	
	
	
	
	var comment_exist = ${comment_exist};
	var ep_count = ${eval_count};	
	
	// ep_count - 내가 평가를 했느냐?
			// 평가를 했다면? ep_count > 0
			// 안했다면?	  ep_count == 0
	
	// star_point > 0 일 때 : 이 영화에 대한 나의 평점이 있는데
	// 코멘트를 달았다면? exist_comment=1 - > COMMENT등록창 안보이게!
			
	if(ep_count != '0' && comment_exist == '0' ){
		// non_comment = 0;
		$(".content_view_comment_write").show();
		
	} else if(ep_count != '0' && comment_exist == '1'){
	   $(".content_view_my_comment").css('display', 'flow-root');
	
	}
	
	/* 내가 남긴 코멘트 삭제 */
	$(".comment_delete").click(function(){
	   var movie_code = ${content_list.movie_code };
	   var comment_seq = $("#comment_seq").val();
	   location.href = "my_comment_delete_proc?comment_seq="+comment_seq+"&movie_code="+movie_code;
	});
	
	/* 내가 남긴 코멘트 수정하기 전에 불러오기 */
	$(".comment_edit").click(function(){
	   var movie_code = ${content_list.movie_code};
	   var movie_title = "${content_list.movie_title}";
	   var comment_seq = $("#comment_seq").val();
	   
	   location.href = "comment_edit?comment_seq="+comment_seq+"&movie_code="+movie_code+"&movie_title="+movie_title;
	});


	
	
	
	// 맨 처음 DB에서 평점을 가져옴
	//var star_point = $(".eval_point").val() ;
	// 만약 평점이 있으면 있는 갯수만큼 별 색깔 채우고
	// 평점 없으면 star_point = 0 이라 아래 for문 안돌아가서 못채움
	for (var i = 0; i < star_point; i++) {
	    $(".star").eq(i).addClass("on");
	}
	
	// hover : 쪽 디자인만 구현 DB랑 관계 X
	$(".star").hover(function() {
	    var idx = $(this).index() + 1;
	    $(".star").removeClass("on");
	    for (var i = 0; i < idx; i++) {
	        $(".star").eq(i).addClass("on");                
	    }
	}, function(){
	    if(star_point == 0 || star_point == null){
	        $(".star").removeClass("on");
	    } else {
	        $(".star").removeClass("on");
	        for (var i = 0; i < star_point; i++) {
	            $(".star").eq(i).addClass("on");
	        }
	    }
	});
	
	$(".star").click(function(){
		if(sessionID == "null" || sessionID == ""){
			alert("로그인이 필요합니다.");
		} else {
	    	var idx = $(this).index() + 1;
		    var movie_code = ${content_list.movie_code };
		    	
		    
		    if(idx == star_point){
		        location.href = "eval_proc.do?idx=" + idx + "&movie_code=" + movie_code + "&update=-1";
		
		    } else {
		    	location.href = "eval_proc.do?idx=" + idx + "&movie_code=" + movie_code + "&update=" + (star_point == 0 ? 0 : 1);
		    }
		}
	});

</script>
</body>




</html>