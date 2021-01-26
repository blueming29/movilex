<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/headerandfooter/footerStyle.css">
<script src="resources/js/headerfooter/footer.js"></script>

</head>
<body>
	<div class="pcweb_ver_footer">
		<div class="footer_container">
			<div class="footer_grid">
				<ul class="footer_ul">
					<li class="policy_list">서비스 이용 약관</li>
					<li class="policy_list">개인정보 처리방침</li>
					<a href="#">회사 안내</a>
				</ul>
				<ul class="footer_ul" style="margin-top: 12px;">
					<li class="policy_list">고객 센터</li>
					<a href="#">blueming29@movilex.com, 02-568-5412</a>
				</ul>
				<ul class="footer_ul">
					<li class="policy_list">제휴 및 대외 협력</li>
					<a href="#">contact@no1class.com, 070-7554-9696</a>
				</ul>
				
				<ul class="footer_ul" style="margin-top: 12px;">
					<li class="policy_list">(주)No.1 Class</li>
					<li class="policy_list">팀장 이정인</li>
					<li class="policy_list_end">서울특별시 강동구 천호대로 1095 현대코아3층</li>
				</ul>
				
				<ul class="footer_ul" style="margin-top: 5px; display: inline-block;">
					<img src="resources/image/informImg/movie_logo_whitecolor.png" alt="" />
					<li class="policy_list_end">© 2021 MOVILEX. Inc</li>
				</ul>
				
				<div class="link_img">
					<i class="fab fa-facebook-square"></i>
					<i style="margin-left: 25px;" class="fab fa-twitter-square"></i>
				</div>
			</div>
		</div>
	</div>

   <div class="mobileweb_ver_footer">
        <nav>
            <ul class="nav_ul_footer">
                <li class="nav_tab_btn">
                    <a href="main.do">
                        <i class="fas fa-home"></i>
                        <div class="tab_btn_name">홈</div>
                    </a>
                    
                </li>
                <li class="nav_tab_btn">
                    <a href="searchform.do">
                        <i class="fas fa-search"></i>
                        <div class="tab_btn_name">검색</div>
                    </a>
                    
                </li>
                
                <c:choose>
					<c:when test="${sessionID != null || member != null}">
		                <!-- 로그인 상태 -->
		                <li class="nav_tab_btn">
		                    <a href="recommendform.do">
		                        <i class="fas fa-star"></i>
		                        <div class="tab_btn_name">추천영화</div>
		                    </a>
		                </li>
		                <li class="nav_tab_btn">
		                    <a href="memberInfo.do">
		                        <i class="fas fa-user"></i>
		                        <div class="tab_btn_name">내 정보</div>
		                    </a>
		                </li> 
					</c:when>
					<c:otherwise>
		                <!-- 로그아웃 상태 -->
		                <li class="nav_tab_btn" id="footer_login_btn">
		                    <a>
		                        <i class="fas fa-user"></i>
		                        <div class="tab_btn_name">로그인</div>
		                    </a>
		                </li> 
					</c:otherwise>
				</c:choose>
            </ul>
        </nav>
    </div>
</body>
</html>