$(document).ready(function () {
	// idx 파라미터 값 가져오기
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    if (results==null){
	       return 0;
	    }
	    else{
	       return results[1] || 0;
	    }
	}

	// alert($.urlParam('idx'));
	// 받아온 인덱스를 id형식으로 바꿔주기
	var selected_id = "#sel_idx" + $.urlParam('idx');
	// 각 id에 해당하는 text값을 text변수에 저장
	var text = $(selected_id).html();
	// selectbox를 위의 text로 변경
	$(".dropdown dt span").html(text);
	
    // selectBox
    $(".dropdown dt span").on('click', function(){
        $(".dropdown_list").toggle(300);
    });

    $(".dropdown_list span").on('click', function(){
        $(".dropdown_list").slideUp(300);
        var span_idx = $(".dropdown_list span").index(this);
        location.href = "evalform.do?idx="+span_idx;
        
    });
    
    
    // 외부 클릭시 창 닫힘
    $(document).bind('click', function (e) {
        var $clicked = $(e.target);
        if (!$clicked.parents().hasClass("dropdown"))
            $(".dropdown_list").slideUp(300);
    });

    // movie_img fold
    $(".title_area").click(function(){
        
        var btn_idx = $(".title_area").index(this);
        var toggle_id = "#num" + btn_idx;
        
        $(toggle_id).toggle(300);
    });
    
    // 탭 전환(전체)
    $(".tabs_li_all").click(function(){
        $(".tabs_li_point").removeClass('selected');
        $(this).addClass('selected');
        $(".hide_block_point").hide(300);
        $(".hide_block_all").show(300);
        $(".selectbox").show(300);
        $(".border_slide.all").width('100%');
        $(".border_slide.point").width('0');
    });
    
    // 탭 전환(별점 순)
    $(".tabs_li_point").click(function(){
        $(".tabs_li_all").removeClass('selected');
        $(this).addClass('selected');
        $(".hide_block_all").hide(300);
        $(".selectbox").hide(300);
        $(".hide_block_point").show(300);
        $(".border_slide.point").width('100%');
        $(".border_slide.all").width('0');
    });
    
    // 별점 갯수 넣기
    for(var i = 0 ; i < 10 ; i++){
    	var li_id = "#num" + i + " ul li";
    	var li_len = $(li_id).length;
    	
    	var cnt_id = "#cnt" + i;
    	$(cnt_id).text(li_len);
    	
    }
    
    // 이전페이지로 이동
	$(".prev_btn").on('click', function(){
		location.href = "memberInfo.do";
//		history.back();
	});
    
});
