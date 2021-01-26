<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="resources/css/headerandfooter/headerStyle.css">
<link rel="stylesheet" href="resources/css/search/searchLabelStyle.css" />
<script src="resources/js/headerfooter/header.js"></script>
<script src="resources/js/search/search.js"></script>

<!-- 팝업창 -->
<link rel="stylesheet"
	href="resources/css/headerandfooter/loginjoin.css" />
<script src="resources/js/headerfooter/loginjoin.js"></script>
<script>
	function check_input() {
		inputForm = eval("document.search_form");
		//정규식 구문
		var RegExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\\"\\\(\=]/gi;
		var obj = inputForm.search_keyword;

		// 공란 판별
		if (obj.value == null || obj.value == "") {
			alert("검색어를 입력하세요!");
			return false;

			// 특수문자 판별	
		} else if (RegExp.test(obj.value)) {
			alert("특수문자는 입력하실 수 없습니다.");
			// 입력란 지워버리기
			obj.value = obj.value.substring(0, 0);
			return false;
		}

		else {
			return true;
		}
	}
</script>
<%-- 
<%
	session.setAttribute("sessionID", "BLUEMING29");
%>
 --%>
</head>
<body>
	<div class="fixed_header">
		<nav>
		<div class="header_container">
			<ul class="nav_ul_header">
				<li class="nav_logo"><a href="main.do"> <img
						src="resources/image/movie_logo.png" alt="">
				</a></li>
				<li class="nav_search_box">
					<div class="search_container">
						<form action="searchresult.do" name="search_form"
							onsubmit="return check_input()">
							<input autocomplete="off" class="input_box" name="search_keyword"
								type="search" placeholder="작품 제목, 배우, 감독을 검색해보세요."
								value="${param.search_keyword }">
							<button class="search_btn" type="submit">
								<i class="fas fa-search"></i>
							</button>
						</form>
					</div> <!-- 검색레이아웃 -->
					<div class="search_label_form_grid">
						<div class="search_label_word_list">
							<div class="search_label_word_inner_list">
								<c:if test="${ not empty cookie.last_search}">
									<!-- 최근 검색어 -->
									<div class="last_search_label_container">
										<div class="last_search_label">
											<div class="last_search_label_title">
												<h2>최근 검색어</h2>
											</div>
											<div class="clear_btn">
												<button>모두 삭제</button>
											</div>
										</div>
										<ul class="last_search_label_list">
											<!-- 리스트 들어올 자리 -->
										</ul>
									</div>
								</c:if>


								<!-- 인기 검색어 -->
								<div class="popular_search_label_container">
									<div class="popular_search_label">
										<div class="popular_search_label_title">
											<h2>인기 검색어</h2>
										</div>
									</div>

									<ul class="popular_search_label_list">
										<!-- 리스트 들어올 자리 -->
									</ul>
								</div>
							</div>
						</div>
					</div>
				</li>
				<c:choose>
					<c:when test="${sessionID != null || member != null}">
						<!-- 로그인 상태 -->
						<li class="nav_btn">
							<button class="rec_btn">추천영화</button>
						</li>
						<li class="nav_btn">
							<button class="user_btn">
								<i class="fas fa-user-circle"></i>
							</button>
						</li>
					</c:when>
					<c:otherwise>
						<!-- 로그아웃 상태 -->
						<li class="nav_btn">
							<button class="login_btn">로그인</button>
						</li>
						<li class="nav_btn">
							<button class="join_btn">회원가입</button>
						</li>
					</c:otherwise>
				</c:choose>

			</ul>
		</div>
		</nav>
	</div>


	<!-- 팝업창 -->
	<div class="headerModal_container">

		<!-- 로그인 -->
		<div class="headerModal hidden" id="myModal1">
			<!-- 배경색 -->
			<div class="headerModal_overlay"></div>
			<!-- Modal content-->
			<div class="headerModal_content">
				<div class="headerModal_header">
					<div class="logo">
						<img src="resources/image/movie_logo.png" />
					</div>
					<div class="title">로그인</div>
				</div>
				<div class="headerModal_body">
					<form action="loginproc.do" method="post" name="loginForm" id="loginForm">
						<div class="form-group">
							<label value="false" class="userEmailLabel"> <input
								autocomplete="off" type="text" class="userEmail" name="email"
								placeholder="이메일" required></label>
						</div>


						<div class="form-group">
							<label value="false" class="userPwLabel"> <input
								autocomplete="off" type="password" class="userPw" name="userpw"
								placeholder="패스워드" required></label>
						</div>
						<span class="login_error_msg hidden">아이디 혹은 비밀번호가 일치하지 않습니다</span>
						<button type="submit" class="btnBox">
							<b>로그인</b>
						</button>
					</form>
				</div>
				<div class="headerModal_footer">
					<button class="pwforget" id="forget_btn">비밀번호를 잊어버리셨나요?</button>
					<p>
						<b style="color: #8c8c8c;">계정이 없으신가요?</b>
						<button id="goJoinBtn" class="move_btn">&nbsp; 회원가입</button>
					</p>
					<hr>
					<a href="#" class="naver_connect"><b>N</b>&nbsp; 네이버로 가입하기</a>
				</div>

			</div>
		</div>


		<!-- 회원가입 -->
		<div class="headerModal hidden" id="myModal2">
			<!-- 배경색 -->
			<div class="headerModal_overlay"></div>
			<!-- Modal content-->
			<div class="headerModal_content">
				<div class="headerModal_header">
					<div class="logo">
						<img src="resources/image/movie_logo.png" />
					</div>
					<div class="title">회원가입</div>
				</div>
				<div class="headerModal_body">
					<form action="joinproc.do" method="post" name="joinForm" id="joinForm">
						<div class="form-group">
							<label value="false" class="userNameLabel"> <input
								autocomplete="off" type="text" class="userName" name="username"
								placeholder="이름" 
								onKeyup="this.value=this.value.replace(/[^a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g,'');"
								required></label>
						</div>
						<div class="form-group">
							<label value="false" class="userEmailLabel" > <input id="user_email"
								autocomplete="off" type="email" class="userEmail" name="email"
								placeholder="이메일" required></label>
						</div>
						<span class="join_error_msg"></span>
						
						<div class="form-group">
							<label value="false" class="userPwLabel"> <input
								autocomplete="off" type="password" class="userPw" name="userpw"
								placeholder="패스워드" required ></label>
						</div>
						<button type="submit" class="btnBox">
							<b>회원가입</b>
						</button>
					</form>
					<div class="headerModal_footer">
						<p>
							<b style="color: #8c8c8c;">이미 가입하셨나요?</b>
								<button id="goLoginBtn" class="move_btn">&nbsp; 로그인</button>
						</p>
						<hr>
						<a href="#" class="naver_connect"><b>N</b>&nbsp; 네이버로 가입하기</a>
					</div>
				</div>
			</div>
		</div>



		<!-- 비밀번호 찾기 -->
		<div class="headerModal hidden" id="myModal3">
			<!-- 배경색 -->
			<div class="headerModal_overlay"></div>
			<!-- Modal content-->
			<div class="headerModal_content">
				<div class="headerModal_header">
					<div class="logo">
						<img src="resources/image/movie_logo.png" />
					</div>
					<div class="title">비밀번호 재설정</div>
				</div>
				<div class="headerModal_body">
					<form action="passwordcheck.do" method="post" name="pwcheckForm" id="pwcheckForm">
						<h4>
							<b>비밀번호 잊어버리셨나요?</b>
						</h4>
						<p style="color: #8c8c8c;">가입했던 이메일을 적어주세요.</p>
						<p style="color: #8c8c8c;">입력하셨던 이메일 주소로 비밀번호 변경</p>
						<p style="color: #8c8c8c;">이메일을 보내드립니다.</p>
						<div class="form-group">
							<label value="false" class="userEmailLabel"> <input
								autocomplete="off" type="email" class="userEmail" name="email"
								placeholder="이메일" required></label>
						</div>
						<button type="submit" class="btnBox">비밀번호 변경 이메일 보내기</button>
						<br> <br> <br> <br> <br> <br>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>