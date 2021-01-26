$(document).ready(function(){
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    if (results==null){
	       return 0;
	    }
	    else{
	       return results[1] || 0;
	    }
	}
	
	// 한글 url parameter 디코딩 시켜서 받기
	var keyword = decodeURIComponent($.urlParam('search_keyword'));
	if(keyword == 0 || keyword == null){
//		alert(keyword);
	}else{
		addCookie(keyword);
			
	}

	
	// 쿠기 셋팅
	function setCookie(cookie_name, value, days) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + days);
		// 설정 일수만큼 현재시간에 만료값으로 지정
		
		var cookie_value = escape(value) + ((days == null) ? '' : '; expires=' + exdate.toUTCString());
		document.cookie = cookie_name + '=' + cookie_value;
	}
		
	// 쿠키 가져오기
	function getCookie(cookie_name) {
		var x, y;
		var val = document.cookie.split(';');
		
		for (var i = 0; i < val.length; i++) {
			x = val[i].substr(0, val[i].indexOf('='));
			y = val[i].substr(val[i].indexOf('=') + 1);
			x = x.replace(/^\s+|\s+$/g, ''); // 앞과 뒤의 공백 제거하기
			if (x == cookie_name) {
			  return unescape(y); // unescape로 디코딩 후 값 리턴
			}
		}
	}

	// 쿠키 추가
	function addCookie(id) {
		var items = getCookie('last_search'); // 이미 저장된 값을 쿠키에서 가져오기
		var maxItemNum = 3; // 최대 저장 가능한 아이템개수
		var expire = 1; // 쿠키값을 저장할 기간
		if (items) {
			var itemArray = items.split(',');
			if (itemArray.indexOf(id) != -1) {
			  // 이미 존재하는 경우 종료
			  console.log('Already exists.');
			}
			else {
			  // 새로운 값 저장 및 최대 개수 유지하기
			  itemArray.unshift(id);
			  if (itemArray.length > maxItemNum ) itemArray.length = maxItemNum;
			  items = itemArray.join(',');
			  setCookie('last_search', items, expire);
			}
		}
		else {
			// 신규 id값 저장하기
			setCookie('last_search', id, expire);
		}
	}
			
	
	// 쿠키삭제
	var deleteCookie = function(name) {
		document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
	}
	

	
	
	var last_search = getCookie('last_search').split(",");
	
	console.log(last_search);
	
	
	
	
	var pc_li_text = "";
	var mob_li_text = "";
	for(var i = 0 ; i < last_search.length ; i++){
		pc_li_text += "<li class='last_search_label_word'><span>" + last_search[i].replace('+', ' ') + "</span></li>";
		mob_li_text += "<li class='last_search_word'><span>" + last_search[i].replace('+', ' ') + "</span></li>"; 
	}
	
	$(".last_search_label_list").html(pc_li_text);
	$(".last_search_list").html(mob_li_text);
	
	
	
	$(".clear_btn").click(function(){
		deleteCookie("last_search");
		$(".last_search_label_container").hide();
		$(".last_search_container").hide();
	});

});