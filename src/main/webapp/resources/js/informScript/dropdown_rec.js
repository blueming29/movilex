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
        if (span_idx == 1){
        	location.href = 'http://localhost:9005/movilex/eval_genre_proc.do?idx='+span_idx;
        } else if(span_idx == 2){
        	location.href = 'http://localhost:9005/movilex/eval_nation_proc.do?idx='+span_idx;
        } else if(span_idx == 3){
        	location.href = 'http://localhost:9005/movilex/eval_actor_proc.do?idx='+span_idx;
        } else {
        	
        	location.href = 'http://localhost:9005/movilex/recommendform.do?idx='+span_idx;
        }
      
        
    });
    
    
    // 외부 클릭시 창 닫힘
    $(document).bind('click', function (e) {
        var $clicked = $(e.target);
        if (!$clicked.parents().hasClass("dropdown"))
            $(".dropdown_list").slideUp(300);
    });


    
});
