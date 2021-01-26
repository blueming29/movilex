$(document).ready(function() {
    $(".boxoffice_slider").bxSlider({
        minSlides: 3,
        maxSlides: 5,
        //moveSlides: 1,
        pager: false,
        touchEnabled : (navigator.maxTouchPoints > 0), 
        slideWidth: 244,
        slideMargin: 20,
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
    
    
    $(".point_slider").bxSlider({
        minSlides: 3,
        maxSlides: 5,
        //moveSlides: 1,
        pager: false,
        touchEnabled : (navigator.maxTouchPoints > 0),     
        slideWidth: 244,
        slideMargin: 20,
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
    
    // 웹 접근성
    $('.mainSlider a').focusin(function () {
    	mainSlider.stopAuto();
    });   
});