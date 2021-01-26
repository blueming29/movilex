$(document).ready(function(){ 
	// 헤더 인풋박스 나타나기
    $(".input_box").focus(function(){
    	$.ajax({
    		type: "POST",
    		url: 'popsearch.do',
    		success: function(data){
    			console.log('success');
    			
    			var pc_li_text = "";
    			for(var i = 0 ; i < data.length ; i++){
    				pc_li_text += "<li class='popular_search_label_word'><span>" + data[i] + "</span></li>";
    			}
    			
    			$(".popular_search_label_list").html(pc_li_text);
    		}, error: function(error){
    			console.log('error');
    		}
    		
    	});
    	
    	$(".search_label_form_grid").slideDown(200);
    }); 
    
    // 외부 클릭 시 사라짐
    $(document).bind('click', function (e) {
        var $clicked = $(e.target);
        if (!$clicked.parents().hasClass("nav_search_box"))
            $(".search_label_form_grid").slideUp(200);
    });
    
    $(".user_btn").click(function(){
    	// 세션에 footer index 저장
    	sessionStorage.setItem("footer_idx", 3 );
    	location.href = "memberInfo.do";
    });
    
    $(".rec_btn").click(function(){
    	// 세션에 footer index 저장
    	sessionStorage.setItem("footer_idx", 2 );
    	location.href = 'recommendform.do';
    });
    
    
    
    
});
