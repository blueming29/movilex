$(document).ready(function(){	
	// 페이지 전환 시 세션의 푸터 인덱스를 가져옴
	var footer_idx = sessionStorage.getItem("footer_idx");
	
	// 처음으로 페이지가 열릴 때
	if(footer_idx == null){
		// 홈 버튼에 표시되게 하기위해서 idx를 0으로 변경
		footer_idx = 0;
	}
	// 클릭된 버튼에 색상 변화시키기
	$(".nav_tab_btn a").eq(footer_idx).css('color', '#292a32');
	
	$(".nav_tab_btn a").click(function(){
		// 푸터에서 클릭 한 인덱스를 가져옴
		var idx = $(".nav_tab_btn a").index(this);
		// 인덱스를 세션에 저장
		sessionStorage.setItem("footer_idx", idx );
		// 일단 모든 색상을 지움
		$(".nav_tab_btn a").css('color', '#babac3');

	});
	
	
	$("#footer_login_btn").click(function(){
		$("#footer_login_btn a").css('color', '#292a32');
		$(".headerModal").eq(0).removeClass("hidden");
		
	});

	
});