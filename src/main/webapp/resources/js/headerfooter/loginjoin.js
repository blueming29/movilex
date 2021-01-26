$(document).ready(function(){
	// 로그인 모달창 열기
	$(".login_btn").click(function() {
		$("#myModal1").removeClass("hidden");
		$("body").css('overflow', 'hidden');
	});
	
	// 회원가입 모달창 열기
	$(".join_btn").click(function() {
		$("#myModal2").removeClass("hidden");
		$("body").css('overflow', 'hidden');
	});

	// 모달창 닫기	
	$(".headerModal_overlay").click(function(){
		$(".headerModal").addClass("hidden");
		$("body").css('overflow', 'scroll');
	});
	
	// 회원가입 후 로그인
	$("#goLoginBtn").click(function(){
		$(".headerModal").eq(1).addClass("hidden");
		$(".headerModal").eq(0).removeClass("hidden");
	});

	// 로그인 후 회원가입
	$("#goJoinBtn").click(function(){
		$(".headerModal").eq(0).addClass("hidden");
		$(".headerModal").eq(1).removeClass("hidden");
	});
	
	// 비밀번호 찾기
	$("#forget_btn").click(function(){
		$(".headerModal").eq(0).addClass("hidden");
		$(".headerModal").eq(2).removeClass("hidden");
	});
	

	// 에러 메세지 지우기
	$(".userPwLabel").on('input', function(){
		$(".error_msg").addClass("hidden");
	});
	
	$(".userEmailLabel").on('input', function(){
		$(".error_msg").addClass("hidden");
	});
	
	// 아이디 중복 확인
	$("#user_email").on('keyup', function(){
		var user_email = $(this).val();
		var exptext = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

		if(exptext.test(user_email) == true){
			$.ajax({
				type: "POST",
				url: 'emailcheckproc.do',
				data: {"user_email" : user_email},
				success: function(data){
					console.log('success');
					console.log(data);
					if(data == -1){
						$(".join_error_msg").css('color', '#007bff');
						$(".join_error_msg").text("사용가능한 이메일 입니다");
					} else if(data == 1){
						$(".join_error_msg").css('color', '#f50000');
						$(".join_error_msg").text("중복된 이메일 입니다");
					}
				},
				error: function(error){
					console.log('error');
				}
			});
		} else {
			$(".join_error_msg").text("");
		}
		
	});
    	  
    
    // 회원가입
    $("#joinForm").submit(function(e) {

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
        	   	alert("회원가입 성공! 로그인 페이지로 이동합니다!");
				$(".headerModal").eq(1).addClass("hidden");
				$(".headerModal").eq(0).removeClass("hidden");
               
            },
            error: function(error){
        	    console.log('error');
        	    $(".userEmailLabel").focus();

            }
    	
         });
    });
    
    // 로그인
    $("#loginForm").submit(function(e) {
    	
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
    			if(data == 0){
    				$(".login_error_msg").removeClass("hidden");
    				
    			} else {
    				location.reload();
    			}
    		},
    		error: function(error){
    			console.log('error');
    		}
    		
    	});
    });
    
    // 비밀번호 찾기
    $("#pwcheckForm").submit(function(e) {
    	
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
    				alert("입력하신 메일로 임시 비밀번호를 전송 했습니다");
    				location.reload();
    			} else {
    				alert("등록되지 않은 이메일 입니다");
    			}
    		},
    		error: function(error){
    			console.log('error');
    		}
    		
    	});
    });

    
    
		

	
	
	
	
});