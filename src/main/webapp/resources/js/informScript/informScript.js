$(document).ready(function(){
    // 멀티플 슬라이드
    $(".multiple_slider").bxSlider({
        minSlides: 3,
        maxSlides: 6,
        pager: false,
        touchEnabled : (navigator.maxTouchPoints > 0), 
        slideWidth: 144,
        slideMargin: 10,
        shrinkItems: true,
        infiniteLoop: false,
        hideControlOnEnd: true,
        
        onSliderLoad: function(){
    		$(".bx-clone").find("a").prop("tabIndex","-1");
    	},

    	onSlideAfter: function(){
    		$(".mainSlider").children("li").each(function(){
    			if($(this).attr("aria-hidden") == "false"){
    				$(this).find("a").attr("tabIndex","0");
    			}else{
    				$(this).find("a").attr("tabIndex","-1");
    			}
    		});
    	}
    });

    
    $("#open_more").click(function(){
        $('.info_grid_toggle').toggle();
    });
    
    // 모달창 열기
	$("#popbutton").click(function() {
		$(".myModal").removeClass("hidden");
		$("body").css('overflow', 'hidden');
		
	});
	
	
	// 모달창 닫기
	$(".myModal button").click(function(){
		$(".myModal").addClass("hidden");
		$("body").css('overflow', 'scroll');

	});
	
	$(".myModal_overlay").click(function(){
		$(".myModal").addClass("hidden");
		$("body").css('overflow', 'scroll');
	});
	
	// 로그아웃 팝업 열기
	$("#logout_popup").click(function(){
		$(".myModal").removeClass("hidden");
		$(".logoutModal").removeClass("hidden");
		$("body").css('overflow', 'hidden');
	});
	
	// 로그아웃 팝업 닫기
	$(".logout_close_btn").click(function(){
		$(".logoutModal").addClass("hidden");
	});
	
	// 로그아웃 처리
	$(".logout_btn").click(function(){
		location.href='logoutproc.do';
	});
	
	// 회원탈퇴 팝업 열기
	$("#withdrawal_popup").click(function(){
		$(".myModal").removeClass("hidden");
		$(".withdrawalModal").removeClass("hidden");
		$("body").css('overflow', 'hidden');
	});
	
	// 회원탈퇴 팝업 닫기
	$(".withdrawalModal_close").click(function(){
		$(".withdrawalModal").addClass("hidden");
	});
	
	$(".withdrawalModal_overlay").click(function(){
		$(".withdrawalModal").addClass("hidden");
	});
	
	$(".withdrawal_close_btn").click(function(){
		$(".withdrawalModal").addClass("hidden");
		$(".withdrawalModal_body").removeClass('hidden');
		$(".withdrawalModal_header").addClass('hidden');
		$(".withdrawalModal_footer").addClass('hidden');
		$("#password_check").val("");
	});
	
	$(".withdrawal_btn").click(function(){
		location.href = 'withdrawalproc.do';
	});
	
	
	
	$("#password_check").on('input', function(){
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
	    				$(".error_msg").addClass('hidden');
	    				$(".withdrawalModal_body").addClass('hidden');
	    				$(".withdrawalModal_header").removeClass('hidden');
	    				$(".withdrawalModal_footer").removeClass('hidden');
	    				
	    			} else{
	    				$(".error_msg").removeClass('hidden');
	    			}
				},
				error: function(error){
	    			console.log('error');
	    		}
				
			});
		}
	});
	
	
	

});

