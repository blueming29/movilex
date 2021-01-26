<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<!-- 개별 css -->
<link rel="stylesheet" href="resources/css/informStyle/memberStyle.css">


<!-- bxSlider -->
<script src="resources/js/informScript/informScript.js"></script>
<script src="resources/js/all.min.js"></script>


</head>
<body>

<!-- 메인화면 -->
	<div class="info_wrap">
		<div class="info_container">
			<div class="info_grid">
				<div class="round_corner">
					<div class="wall_paper">
						<img src="resources/image/informImg/info_img.jpg" alt="">
						<button class="setting-button" id="popbutton">
							<i class="fas fa-cog"></i>
						</button>
						<div class="logo_img">
							<img src="resources/image/informImg/movie_logo_whitecolor.png"
								alt="">
						</div>
					</div>
					<div class="user_info_header">
						<div class="user_info">
							<div class="round_user_img">
								<img src="resources/image/informImg/user_img.png" alt="">
							</div>
							<div class="user_name">
								<span>${user_name }</span>
							</div>
						</div>
					</div>
					<div class="user_info_body">
						<div class="user_info_list">
							<header class="info_title_header">
								<span class="info_title">평가</span>
								<span class="total_count">${eval_total }</span>
							<div class="info_spread">
								<a href="evalform.do">더보기</a>
							</div>
							</header>
							<div class="slideWrap multipleWrap">
								<ul class="multiple_slider">
									<c:forEach var="eval_dto" items="${eval_list }">
										<a href="contentview.do?movie_code=${eval_dto.movie_code }">
											<li>
												<img src="${eval_dto.movie_img }" alt="">
												<p class="movie_title">${eval_dto.movie_title } </p>
												<p class="movie_eval_star_point">평가함 ★ ${eval_dto.eval_point / 2}</p>
											</li>
										</a>
									</c:forEach>
								</ul>
							</div>
						</div>

						<div class="user_info_list">
							<header class="info_title_header">
								<span class="info_title">보고싶어요</span>
								<span class="total_count">${like_total }</span>
							<div class="info_spread">
								<a href="likeform.do">더보기</a>
							</div>
							</header>
							<div class="slideWrap multipleWrap">
								<ul class="multiple_slider">
									<c:forEach var="like_dto" items="${like_list }">
										<a href="contentview.do?movie_code=${like_dto.movie_code }">
											<li>
												<img src="${like_dto.movie_img }" alt="">
												<p class="movie_title">${like_dto.movie_title }</p>
												<c:choose>
													<c:when test="${like_dto.eval_point != 0}">
														<p class="movie_eval_star_point">평가함 ★ ${like_dto.eval_point / 2 }</p>
													</c:when>
													<c:otherwise>
														<p class="movie_star_point">평점 ★ ${like_dto.movie_point }</p>
													</c:otherwise>
												</c:choose>
											</li>
										</a>
									</c:forEach>
								</ul>
							</div>
						</div>

						<div class="user_info_list">
							<header class="info_title_header"> <span
								class="info_title">취향분석</span>
							<button id="open_more"></button>
							</header>
						</div>
					</div>
				</div>
			</div>

			<div class="info_grid">
				<div class="info_grid_toggle">
					<div class="round_corner">
						<div class="user_info_body">
							<div class="user_info_list">
								<header class="info_title_header"> <span
									class="info_title">별점 분포</span> </header>

								<div class="graph_container">
									<canvas id="myChartOne"></canvas>
								</div>
								<div class="point_distribution">
									<ul class="point_distribution_ul">
										<li class="point_distribution_li"><span
											class="point_distribution_title"> ${point_avg }</span> <span
											class="point_distribution_subtitle">별점 평균</span></li>
										<li class="point_distribution_li"><span
											class="point_distribution_title"> ${point_count }</span> <span
											class="point_distribution_subtitle">별점 개수</span></li>
										<li class="point_distribution_li"><span
											class="point_distribution_title"> ${max_point }</span> <span
											class="point_distribution_subtitle">많이 준 별점</span></li>
									</ul>
								</div>
							</div>

							<!-- 선호장르 -->
							<c:if test="${not empty genre_list }">
								<div class="user_info_list">
									<header class="info_title_header">
										<span class="info_title">영화 선호 장르</span>
									</header>
									<div class="preper_genre">
										<ul class="preper_genre_toprank_ul">
											<c:forEach var="genre_dto" items="${genre_list }" varStatus="status">
												<c:if test="${status.index < 3 }">
													<li class="preper_genre_toprank_li">
														<span class="preper_genre_title">${genre_dto.key } </span>
														<span class="preper_genre_subtitle">${genre_dto.value } 점 </span>
													</li>
												</c:if>
											</c:forEach>
										</ul>
										<ul class="preper_genre_ul">
											<c:forEach var="genre_dto" items="${genre_list }" varStatus="status">
												<c:if test="${status.index >=3 and status.index < 10 }">
													<li class="preper_genre_li">
														${genre_dto.key }
														<span class="preper_genre_subtitle">${genre_dto.value } 점 </span></li>
												</c:if>
											</c:forEach>
										</ul>
									</div>
								</div>
							</c:if>

							<!-- 선호 국가 -->
							<c:if test="${not empty nation_list }">
								<div class="user_info_list">
									<header class="info_title_header">
										<span class="info_title">영화 선호 국가</span>
									</header>
									<div class="preper_nation">
										<ul class="preper_nation_toprank_ul">
											<c:forEach var="nation_dto" items="${nation_list }" varStatus="status">
												<c:if test="${status.index < 3 }">
													<li class="preper_nation_toprank_li">
														<span class="preper_nation_title">${nation_dto.key } </span>
														<span class="preper_nation_subtitle">${nation_dto.value } 점 </span>
													</li>
												</c:if>
											</c:forEach>
										</ul>
										<ul class="preper_nation_ul">
											<c:forEach var="nation_dto" items="${nation_list }" varStatus="status">
												<c:if test="${status.index >=3 and status.index < 10 }">
													<li class="preper_nation_li">
														${nation_dto.key }
														<span class="preper_nation_subtitle">${nation_dto.value } 점 </span></li>
												</c:if>
											</c:forEach>
										</ul>
									</div>
								</div>
							</c:if>

							<!-- 선호 배우 -->
							<c:if test="${not empty actor_list }">
								<div class="user_info_list">
									<header class="info_title_header">
										<span class="info_title">영화 선호 배우</span>
									</header>
									<div class="preper_actor">
										<ul class="preper_actor_toprank_ul">
											<c:forEach var="actor_dto" items="${actor_list }" varStatus="status">
												<c:if test="${status.index < 3 }">
													<li class="preper_actor_toprank_li">
														<span class="preper_actor_title">${actor_dto.key } </span>
														<span class="preper_actor_subtitle">${actor_dto.value } 점 </span>
													</li>
												</c:if>
											</c:forEach>
										</ul>
										<ul class="preper_actor_ul">
											<c:forEach var="actor_dto" items="${actor_list }" varStatus="status">
												<c:if test="${status.index >=3 and status.index < 10 }">
													<li class="preper_actor_li">
														${actor_dto.key }
														<span class="preper_actor_subtitle">${actor_dto.value } 점 </span></li>
												</c:if>
											</c:forEach>
										</ul>
									</div>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- 팝업화면 -->
	<div class="myModal hidden">
		<!-- 배경색 -->
		<div class="myModal_overlay"></div>
		<div class="myModal_content">
			<div class="myModal_header">
				<button type="button" class="myModal_close" >&times;</button>
				<div class="myModal_title">설정</div>
			</div>
			<div class="myModal_body">
				<section class="setting-list-section">
					<div class="setting-list">
						<a href="noticeview.do">공지사항</a>
					</div>
					<div class="setting-list">
						<a href="qnaview.do">문의하기 / QnA</a>
					</div>
					<div class="setting-list">
						<a href="infoedit.do">정보수정</a>
					</div>
					<div class="setting-list">
						<button id="logout_popup">로그아웃</button>
					</div>
					<div class="setting-list">
						<button id="withdrawal_popup">회원탈퇴</button>
					</div>
				</section>
				<c:if test="${sessionID == 'admin' }">
					<section class="setting-list-section">
						<div class="setting-list-title">관리자 메뉴</div>
						<div class="setting-list">
							<a href="">회원관리</a>
						</div>
						<div class="setting-list">
							<a href="dbinsertform.do">DB추가</a>
						</div>
						<div class="setting-list">
							<a href="">통계보기</a>
						</div>
					</section>
				</c:if>
			</div>
			<div class="myModal_footer">
				<button type="button" class="close_btn">Close</button>
			</div>
		</div>
	</div>
	
	<!-- 로그아웃 -->
	<div class="logoutModal hidden">
		<!-- 배경색 -->
		<div class="logoutModal_overlay"></div>
		<div class="logoutModal_content">
			<div class="logoutModal_header">
				<div class="logoutModal_title">로그아웃 하시겠습니까?</div>				
			</div>
			
			<div class="logoutModal_footer">
				<button type="button" class="logout_btn">네</button>
				<button type="button" class="logout_close_btn">아니오</button>
			</div>
		</div>
	</div>
	
	<!-- 회원탈퇴 -->
	<div class="withdrawalModal hidden">
		<!-- 배경색 -->
		<div class="withdrawalModal_overlay"></div>
		<div class="withdrawalModal_content">
			<div class="withdrawalModal_header hidden">
				<div class="withdrawalModal_title">정말 탈퇴 하시겠습니까?</div>				
			</div>
			<div class="withdrawalModal_body">
				<button type="button" class="withdrawalModal_close" >&times;</button>
				<div class="password_input_title">
					비밀번호를 확인해 주세요
				</div>
				<div class="password_input">
					<input id="password_check" type="password" placeholder="비밀번호를 입력하세요" />
				</div>
				<div class="error_msg hidden">
					비밀번호가 일치하지 않습니다
				</div>
			</div>
			<div class="withdrawalModal_footer hidden">
				<button type="button" class="withdrawal_btn">네</button>
				<button type="button" class="withdrawal_close_btn">아니오</button>
			</div>
		</div>
	</div>

</body>

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
			maintainAspectRatio: false,
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
</script>
</html>