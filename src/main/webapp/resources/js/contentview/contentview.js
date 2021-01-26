$(document).ready(function() {

	
	// 코멘트 슬라이드
    $(".content_view_comment00").bxSlider({
        minSlides: 1,
        maxSlides: 4,
        //moveSlides: 1,
        pager: false,
        touchEnabled : (navigator.maxTouchPoints > 0), 
        slideWidth: 260,
        slideMargin: 15,
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
});
