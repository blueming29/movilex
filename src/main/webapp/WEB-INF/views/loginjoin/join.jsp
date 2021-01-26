 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="resources/css/login.css">
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
    <title>Document</title>
</head>

<script>
    $(document).ready(function() {   
 	   $(goDetail).show()
    });
</script>  
<body>

 <!--    <button onClick="goDetail();">회원가입</button>
 -->

    <!-- 팝업뜰때 배경 -->
    <div id="mask"></div>

    <!--Popup Start -->
            <div id="layerbox" class="layerpop" style="width: 375px; height: 619px;">
        
                <a href="javascript:popupClose();" class="layerpop_close" id="layerbox_close"></a> <br>
        
                <div class="logo">logo</div>
                <div class="title">회원가입</div>
                <article class="layerpop_area">
                    <div class="content">
        
                        <label value="false" class="userNameLabel">
                            <div class="css-1smbjja"><input autocomplete="off" placeholder="이름" type="text" name="name" class="userName" value=""></div>
                            <div value="false" class="css-lcodwd"><span aria-label="clear" role="button" class="css-qe0tnm"></span></div><span value="false" class="css-gem1s0"></span>
                        </label>
                        <br>
                        <label value="false" class="userEmailLabel">
                            <div class="css-1smbjja"><input autocomplete="off" placeholder="이메일" type="email" name="email" class="userEmail" value=""></div>
                            <div value="false" class="css-lcodwd"><span aria-label="clear" role="button" class="css-qe0tnm"></span></div><span value="false" class="css-gem1s0"></span>
                        </label>
                        <br>
                        <label value="false" class="userPwLabel">
                            <div class="css-1smbjja"><input autocomplete="off" placeholder="패스워드" type="password" name="password" class="userPw" value=""></div>
                            <div value="false" class="css-lcodwd"><span aria-label="clear" role="button" class="css-qe0tnm"></span></div><span value="false" class="css-gem1s0"></span>
                        </label>
                    </div>
                    <button type="submit" class="btnBox"><b>회원가입</b></button>
        
        
        
        
                    <div class="checkLogin1">이미 가입하셨나요? <button class="checkLogin2">로그인</button></div>
        
                    <br>
        
                    <div>
                        <div class="social">
                            <a href="" class="kakao_connect"><i class="fa fa-comment" aria-hidden="true"></i>&nbsp;&nbsp;카카오로 가입하기</a>
                            <a href="" class="naver_connect"><b>N</b>&nbsp; 네이버로 가입하기</a>
                            <a href="" class="facebook_connect"><i class="fa fa-facebook" aria-hidden="true"></i> 페이스북으로 가입하기</a>
                            <a href="" class="google_connect"><i class="fa fa-google fa-fw" aria-hidden="true"></i>&nbsp;구글로 가입하기</a>
                        </div>
                    </div>
        
        
                </article>
            </div>
<!--Popup End -->
<script src="resources/js/login.js"></script>
</body></html>