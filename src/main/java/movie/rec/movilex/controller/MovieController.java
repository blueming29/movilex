package movie.rec.movilex.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import movie.rec.movilex.crawler.MovieBoxOfficeCrawler;
import movie.rec.movilex.crawler.MovieImgCrawler;
import movie.rec.movilex.crawler.MovieInfoCrawler;
import movie.rec.movilex.crypt.work.BCrypt;
import movie.rec.movilex.crypt.work.SHA256;
import movie.rec.movilex.dao.BoardDao;
import movie.rec.movilex.dao.MovieDao;
import movie.rec.movilex.dao.MovieMemberDao;
import movie.rec.movilex.dto.EvalMovieDto;
import movie.rec.movilex.dto.LikeMovieDto;
import movie.rec.movilex.dto.MainPageDto;
import movie.rec.movilex.dto.MemberDto;
import movie.rec.movilex.dto.MovieCommentDto;
import movie.rec.movilex.dto.MovieDto;
import movie.rec.movilex.dto.MovieImgDto;
import movie.rec.movilex.dto.MyEvalListDto;
import movie.rec.movilex.dto.MyEvalMovieDto;
import movie.rec.movilex.dto.NonEvalMovieListDto;
import movie.rec.movilex.dto.NoticeBoardDto;
import movie.rec.movilex.dto.PopularSearchDto;
import movie.rec.movilex.dto.QnaBoardDto;
import movie.rec.movilex.email.EmailSendAction;
import movie.rec.movilex.eval.calculator.EvalCalculator;
import movie.rec.movilex.eval.calculator.EvalSort;

@Controller
public class MovieController {
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value= {"/main.do", "/"}, method=RequestMethod.GET)
	public String main(Model model, HttpSession session) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		
		String sessionID = (String) session.getAttribute("sessionID");
//		String sessionID = "BLUEMING29";
		if(sessionID == null || sessionID == "") {
			sessionID = "";
		}
		
		ArrayList<MainPageDto> boxoffice_dtos = new ArrayList<MainPageDto>();
		MovieBoxOfficeCrawler code_crawler = new MovieBoxOfficeCrawler();
		ArrayList<String> movie_code = code_crawler.getMovieCode();
		
		for(int i = 0; i < movie_code.size() ; i++) {
			MainPageDto dto = dao.get_boxoffice(sessionID, Integer.parseInt(movie_code.get(i)));				
			
			if(dto != null) {
				boxoffice_dtos.add(dto);
			}
		}
		// 박스오피스 리스트 전달
		model.addAttribute("boxofficeList", boxoffice_dtos);

		ArrayList<MainPageDto> starPoint_dtos = dao.get_starPoint(sessionID);
		for(int i = 0 ; i < starPoint_dtos.size() ; i++) {
			
		}
		model.addAttribute("starPoint", starPoint_dtos);

		return "main";
	}

	// 인기검색어
	@ResponseBody
	@RequestMapping(value="/popsearch.do", method=RequestMethod.POST)
	public ArrayList<String> popSearch() {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);

		ArrayList<PopularSearchDto> pop_searchs = dao.get_pop_search();
		ArrayList<String> pop_search = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			pop_search.add(pop_searchs.get(i).getPop_title());
		}
		return pop_search;
	}
	
	
	
	// 회원가입
	@ResponseBody
	@RequestMapping(value="/joinproc.do", method=RequestMethod.POST)
	public void join(HttpServletRequest request) throws Exception {
		MovieMemberDao dao = sqlSession.getMapper(MovieMemberDao.class);

		String user_name = request.getParameter("username");
		String email = request.getParameter("email");
		String user_pw = request.getParameter("userpw");
		
		SHA256 sha = SHA256.getInsatnce();
		String shPwd = sha.getSha256(user_pw.getBytes());
		
		// 2차 암호화
		String bcPwd =  BCrypt.hashpw(shPwd, BCrypt.gensalt());

		dao.member_insert(user_name, bcPwd, email);
	}
	
	
	// 중복확인
	@ResponseBody
	@RequestMapping(value="/emailcheckproc.do", method=RequestMethod.POST)
	public int email_check(HttpServletRequest request) {
		MovieMemberDao dao = sqlSession.getMapper(MovieMemberDao.class);
		
		String email = request.getParameter("user_email");

		int email_check = dao.email_check(email) == 0 ? -1 : 1;
		
		return email_check;
		
	}
	
	// 로그인
	@ResponseBody
	@RequestMapping(value="/loginproc.do", method=RequestMethod.POST)
	public int login(HttpServletRequest request, HttpSession session) throws Exception {
		MovieMemberDao dao = sqlSession.getMapper(MovieMemberDao.class);
		
		String email = request.getParameter("email");
		String user_pw = request.getParameter("userpw");
		
		int login_result = 0;
		if(!email.equals("admin")) {
		
			String crypt_user_pw = dao.get_user_pw(email);
				if(crypt_user_pw != null) {
				// id와 pwd 일치 여부 확인1
				SHA256 sha = SHA256.getInsatnce();
				String shaPass = sha.getSha256(user_pw.getBytes());
				
				boolean idpwboolean = false;
					// 2차 암호화된 비밀번호 비교해주는 메소드(2차암호는 매번 달라지므로 이걸 써야됨)
				idpwboolean = BCrypt.checkpw(shaPass, crypt_user_pw);
				
				System.out.println(idpwboolean);
		
				// 일치여부 확인
				if(idpwboolean == true) {
					login_result = 1;
					session.setAttribute("sessionID", email);
				}
			}
		} else {
			login_result = dao.member_check(email, user_pw);
			session.setAttribute("sessionID", email);

		}
		
		return login_result;
	}
	
	// 비밀번호 확인
	@ResponseBody
	@RequestMapping(value="/password_check.do", method=RequestMethod.POST)
	public int password_check(HttpServletRequest request, HttpSession session) throws Exception {
		MovieMemberDao dao = sqlSession.getMapper(MovieMemberDao.class);
		
		String email = (String) session.getAttribute("sessionID");
		String user_pw = request.getParameter("user_pw");
		
		int result = 0;
		
		String crypt_user_pw = dao.get_user_pw(email);
		
		// id와 pwd 일치 여부 확인1
		SHA256 sha = SHA256.getInsatnce();
		String shaPass = sha.getSha256(user_pw.getBytes());
		
		boolean idpwboolean = false;
		// 2차 암호화된 비밀번호 비교해주는 메소드(2차암호는 매번 달라지므로 이걸 써야됨)
		idpwboolean = BCrypt.checkpw(shaPass, crypt_user_pw);
				
		// 일치여부 확인
		if(idpwboolean == true) {
			result = 1;
		} else {
			result = 0;
		}
		return result;
	}
	
	
	// 임시 비밀번호 발급
	@ResponseBody
	@RequestMapping(value="/passwordcheck.do", method=RequestMethod.POST)
	public int passwordcheck(HttpServletRequest request) throws Exception {
		MovieMemberDao dao = sqlSession.getMapper(MovieMemberDao.class);

		String email = request.getParameter("email");
		System.out.println(email);
		EmailSendAction esa = new EmailSendAction();
		
		int result = dao.email_check(email);
		
		if(result == 1) {
			String str_password =
					"a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,"
					+ "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,"
					+ "1,2,3,4,5,6,7,8,9,0";
			String[] arr_pw = str_password.split(",");
			
			String pw = "";
			for (int j = 0; j < 10; j++) {
				pw += arr_pw[(int)(Math.random() * 61)];
			}
			
			SHA256 sha = SHA256.getInsatnce();
			String shPwd = sha.getSha256(pw.getBytes());
			
			// 2차 암호화
			String bcPwd =  BCrypt.hashpw(shPwd, BCrypt.gensalt());
			dao.update_pw(email, bcPwd);
			
			// 메일 전송
			esa.emailSendAction(email, pw);
			
		}
		return result;
		
	}
	
	// 정보수정 화면
	@RequestMapping("/infoedit.do")
	public String infoedit(HttpSession session, Model model) {
		MovieMemberDao dao = sqlSession.getMapper(MovieMemberDao.class);
		String sessionID = (String) session.getAttribute("sessionID");
		
		MemberDto dto = dao.get_member_info(sessionID);
		
		model.addAttribute("info_dto", dto);
		
		return "memberInfo/infoEdit";
	}
	
	
	// 정보수정처리
	@ResponseBody
	@RequestMapping("/info_edit_proc.do")
	public int info_edit_proc(HttpSession session, HttpServletRequest request) throws Exception {
		MovieMemberDao dao = sqlSession.getMapper(MovieMemberDao.class);
		String sessionID = (String) session.getAttribute("sessionID");
		
		String user_name = request.getParameter("user_name");
		String user_pw = request.getParameter("user_pw");
		
		int result = 0;

		if(user_name != null && user_pw != null) {
			SHA256 sha = SHA256.getInsatnce();
			String shPwd = sha.getSha256(user_pw.getBytes());
			
			// 2차 암호화
			String bcPwd =  BCrypt.hashpw(shPwd, BCrypt.gensalt());
	
			dao.member_update(user_name, bcPwd, sessionID);
			result = 1;
		}
		return result;
	}
	
	// 로그아웃
	@RequestMapping("/logoutproc.do")
	public String logoutproc(HttpSession session) {
		session.removeAttribute("sessionID");
		return "redirect:main.do";
	}
	
	// 회원탈퇴
	@RequestMapping("/withdrawalproc.do")
	public String withdrawalproc(HttpSession session) {
		MovieMemberDao dao = sqlSession.getMapper(MovieMemberDao.class);
		String sessionID = (String) session.getAttribute("sessionID");
		
		dao.user_withdrawal(sessionID);
		session.removeAttribute("sessionID");
		return "redirect:main.do";
//		return "thankyou";
	}

	
	
	
	
	// 개인 정보화면
	@RequestMapping("/memberInfo.do")
	public String memberInfo(Model model, HttpSession session) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);

		String sessionID = (String) session.getAttribute("sessionID");
//		String sessionID = "BLUEMING29";
		
		String user_name = dao.get_user_name(sessionID);
		
		model.addAttribute("user_name", user_name);
		
		
		// 세션 아이디가 평가한 목록 가져오기
		ArrayList<EvalMovieDto> eval_movie_dtos = dao.get_eval_list(sessionID, "0");
		model.addAttribute("eval_total", eval_movie_dtos.size());
		model.addAttribute("eval_list", eval_movie_dtos);
		
		// 세션 아이디가 좋아요한 목록 가져오기
		ArrayList<LikeMovieDto> like_movie_dtos = dao.get_like_list(sessionID, "0");
		model.addAttribute("like_total", like_movie_dtos.size());
		model.addAttribute("like_list", like_movie_dtos);
		
		// 별점 그래프 그릴 데이터 가져오기
		ArrayList<Integer> star_points = new ArrayList<Integer>();
		
		int eval_check_cnt = 0;
		for(int i = 1 ; i <= 10 ; i++) {
			int star_point = dao.get_star_point_by_sessionID(sessionID, i);
			star_points.add(star_point);
			eval_check_cnt += star_point;
//			System.out.println(star_point);
		}
		
		if (eval_check_cnt !=0) {
			
			model.addAttribute("star_points", star_points);
			
			ArrayList<Integer> total_points = dao.get_total_points(sessionID);
			
			// 제일 큰 평점
			model.addAttribute("max_point", (double)Collections.max(total_points)/2);
			
			int sum = 0;
			for(Integer i : total_points){
				sum += i;
		}
		double avg = sum / total_points.size();

		int point_count = sum / 2;
		
		double point_avg = avg / 2;
		
		// 총 별 갯수
		model.addAttribute("point_count", point_count);
		
		// 별점 평균
		model.addAttribute("point_avg", point_avg);
		
	}
		
		// 평가계산기 객체생성
		EvalCalculator eval_cal = new EvalCalculator();
		
		// DB에서 내가 평가한 리스트 가져옴
		ArrayList<MyEvalMovieDto> eval_lists = dao.get_my_eval_movie(sessionID);
		
		// Map에 장르에 대한 평가 점수로 계산한 값 넣기
		Map<String, Integer> genre_map = eval_cal.eval_genre_cal(eval_lists);

		// Map 내림차순 정렬 시켜줄 객체생성
		EvalSort es = new EvalSort();
		ArrayList<MyEvalListDto> genre_list = es.eval_sort(genre_map);
		model.addAttribute("genre_list", genre_list);
		


		// Map에 국가에 대한 평가 점수로 계산한 값 넣기
		Map<String, Integer> nation_map = eval_cal.eval_nation_cal(eval_lists);
		ArrayList<MyEvalListDto> nation_list = es.eval_sort(nation_map);
		model.addAttribute("nation_list", nation_list);
		
		
		// Map에 배우에 대한 평가 점수로 계산한 값 넣기
		Map<String, Integer> actor_map = eval_cal.eval_actor_cal(eval_lists);
		ArrayList<MyEvalListDto> actor_list = es.eval_sort(actor_map);
		model.addAttribute("actor_list", actor_list);
		
		
		return "memberInfo";
	}
	
	// 개인 평가 화면
	@RequestMapping("/evalform.do")
	public String evalploc(Model model, HttpSession session, HttpServletRequest request) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		String sessionID = (String) session.getAttribute("sessionID");
//		String sessionID = "BLUEMING29";

		String idx = request.getParameter("idx");
		if(idx == null) {
			idx = "0";
		}
//		System.out.println(idx);
		
		// 평가 리스트
		ArrayList<EvalMovieDto> eval_movie_dtos = dao.get_eval_list(sessionID, idx);
		model.addAttribute("eval_list", eval_movie_dtos);
		
		// 내가 준 별점 순
		ArrayList<EvalMovieDto> eval_movie_point_dtos = dao.get_eval_list(sessionID, "5");
		model.addAttribute("orderByPoint", eval_movie_point_dtos);
		
		return "evalform";
	}
	
	// 보고싶어요 화면
	@RequestMapping("/likeform.do")
	public String likeform(Model model, HttpSession session, HttpServletRequest request) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		String sessionID = (String) session.getAttribute("sessionID");

		String idx = request.getParameter("idx");
//		System.out.println(idx);
		if(idx == null) {
			idx = "0";
		}
		
		ArrayList<LikeMovieDto> like_movie_dtos = dao.get_like_list(sessionID, idx);
		model.addAttribute("like_list", like_movie_dtos);
		
		return "likeform";
	}
	
	
	// 검색 화면
	@RequestMapping("/searchform.do")
	public String searchform() {
		
		
		return "searchform";
	}
	
	// 검색 결과
	@RequestMapping("/searchresult.do")
	public String searchresult(Model model, HttpSession session, HttpServletRequest request) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		
		String keyword = request.getParameter("search_keyword");
		dao.insert_popsearch(keyword);
		
		
		String[] splited_keyword = keyword.split("\\s");
		
		ArrayList<String> keywords = new ArrayList<String>();
		for (int i = 0; i < splited_keyword.length; i++) {
			if(!splited_keyword[i].equals("")) {
				keywords.add(splited_keyword[i]);
				
			}
		}
		

//		System.out.println("입력한 값3 : " + keywords);
		
		
		session.setAttribute("keyword", keyword);
		
		ArrayList<MovieDto> movie_dtos = null;
		if(keywords != null) {
			
			if(keywords.size() >= 2) {
				movie_dtos = dao.get_searchs_list2(keywords.get(0), keywords.get(1));
				
			} else {
				movie_dtos = dao.get_searchs_list(keywords.get(0));
			}
		}
		
		
		
		if(movie_dtos.isEmpty() || keywords.size() >= 3) {
			// DB 조회 후 내용 없을 때
			return "searchform";
		} else {
			// DB에서 가져올 내용이 있을 때
			model.addAttribute("search_list", movie_dtos);
			return "searchresult";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	//contentview
	@RequestMapping(value = "/contentview.do", method =RequestMethod.GET)
	public String contentview(HttpServletRequest request, HttpSession session, Model model) {
		String sessionID = (String) session.getAttribute("sessionID");

		
		// 로그아웃이면 sessionID = null or "";
		// 로그인이면 sessionID = sessionID;
		
		//contentview_영화소개
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		String movie_code = request.getParameter("movie_code");
		MovieDto dto = dao.get_contentview(Integer.parseInt(movie_code));
		
		
		model.addAttribute("content_list",dto);

		//contentview_상단 스틸컷
		MovieImgDto stillcut_dto = dao.get_contentviewImg(Integer.parseInt(movie_code));
		
		ArrayList<String> img_list = new ArrayList<String>();
		
		try {
			for (Field field : stillcut_dto.getClass().getDeclaredFields()){
				field.setAccessible(true);
				Object value = field.get(stillcut_dto);
				if(!value.equals("") || value != null) {
					img_list.add(value.toString());
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		img_list.remove(0);

		
	    int random_num = (int)(Math.random()*img_list.size());
	    
	    // 스틸컷 이미지
	    model.addAttribute("stillcut_img", img_list.get(random_num));
		
	    //comment_코멘트(최신순으로)
		ArrayList<MovieCommentDto> cdto = dao.get_comment(Integer.parseInt(movie_code));
		
		
		
		
		 ArrayList<ArrayList<String>> user_id_by_comment = new ArrayList<ArrayList<String>>();
		
		for (int i = 0; i < cdto.size(); i++) {
//			System.out.println(cdto.get(i).getComment_seq());
			int comment_seq = cdto.get(i).getComment_seq();
			
			ArrayList<String> user_ids = dao.get_comment_user_id(comment_seq);
			
			user_id_by_comment.add(user_ids);
		}
		
//		System.out.println(user_id_by_comment);
		

		model.addAttribute("user_id_by_comment", user_id_by_comment );
		model.addAttribute("comment",cdto);
	    
		Integer ep_count;
		
		if(sessionID == null || sessionID.isEmpty()) {
			ep_count = 0;
		} else {
			ep_count = dao.get_eval_point_count(sessionID, movie_code);
			if(ep_count == null) {
				ep_count = 0;
			}
		}
		
		model.addAttribute("eval_count", ep_count);

		int like_check_count = 0;
		
		if(sessionID != null) {
			like_check_count = dao.get_like_count(sessionID, movie_code);
			
		}
		model.addAttribute("like_check", like_check_count);
		
		
		
		// exist == 1이면 default
		int comment_exist = 1;
		
		// 별점 평가를 했냐?
		
		
		if(sessionID != null) {
			comment_exist = dao.get_comment_exist(sessionID, movie_code);
		}
		
		
		model.addAttribute("comment_exist", comment_exist);
		
		
		ArrayList<Integer> star_points = new ArrayList<Integer>();
		
		for(int i = 1 ; i <= 10 ; i++) {
			int star_point = dao.get_star_point_by_movie_code(movie_code, i);
			star_points.add(star_point);
//			System.out.println(star_point);
		}
		
		model.addAttribute("star_points", star_points);
		
		
		if(sessionID == null || sessionID.isEmpty() ) {
			
		} else {
			MovieMemberDao memberDao = sqlSession.getMapper(MovieMemberDao.class);
			MemberDto memberDto = memberDao.get_member_info(sessionID);
			model.addAttribute("memberDto", memberDto);
			
		}

		ArrayList<String> user_name_by_comment = new ArrayList<String>();
		
		for (int i = 0; i < cdto.size(); i++) {
			int comment_seq = cdto.get(i).getComment_seq();
			
			String user_names = dao.get_comment_user_name(comment_seq);
			
			user_name_by_comment.add(user_names);
		}
		
		model.addAttribute("user_name_by_comment", user_name_by_comment );
		
		
		return "contentview";
	}
	
	//코멘트
	@RequestMapping("/comment.do")
	public String comment(HttpServletRequest request, Model model) {
		String movie_title = request.getParameter("movie_title");
		String movie_code = request.getParameter("movie_code");
		String eval_point = request.getParameter("eval_point");
		
		model.addAttribute("movie_title", movie_title);
		model.addAttribute("movie_code", movie_code);
		model.addAttribute("eval_point", eval_point);
		return "contentview/comment";
	}
	//코멘트 작성
	@RequestMapping("/comment_write.do")
	public String comment_write(HttpServletRequest request, HttpSession session, Model model) {
//		System.out.println("쓰고 가네");
		MovieDao dao = sqlSession.getMapper(MovieDao.class);

		
		String sessionID = (String) session.getAttribute("sessionID");
		// movie_code
		// user_id(session)
		// point(index)
		// comment_content
		
		String movie_code = request.getParameter("movie_code");
		String eval_point = request.getParameter("eval_point");
		String comment_content = request.getParameter("comment_content");
		
//		System.out.println(sessionID);
//		System.out.println(movie_code);
//		System.out.println(eval_point);
//		System.out.println(comment_content);
		
		dao.write_comment(movie_code, sessionID, eval_point, comment_content);
		return "redirect:contentview.do?movie_code=" + movie_code;
	}
	
	
	//movie_point 입력
	@RequestMapping("/eval_proc.do")
	public String eval_proc(HttpServletRequest request, HttpSession session, Model model) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);

		String sessionID = (String) session.getAttribute("sessionID");
		
		String movie_code = request.getParameter("movie_code");
		
		String update = request.getParameter("update");
		String idx = request.getParameter("idx");
		
//		System.out.println("update가 뭐냐 : " + update);
		
		if(update.equals("-1")) {
			dao.eval_point_delete(sessionID, movie_code);
			
		} else if(update.equals("0")) {
			dao.eval_point_insert(sessionID, movie_code, idx);
			
		} else {
			dao.eval_point_update(sessionID, movie_code, idx);
		}
		
		//movie_code로 contentview01 재전달 받음
		return "redirect:contentview.do?movie_code=" + movie_code;
	}
	
	// 보고싶어요 담기
	@RequestMapping("/likeproc.do")
	public String likeproc(HttpServletRequest request, HttpSession session, Model model) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		
		String movie_code = request.getParameter("movie_code");
		String sessionID = (String) session.getAttribute("sessionID");

		dao.like_insert(sessionID, movie_code);
		
		
		return "redirect:contentview.do?movie_code=" + movie_code;
	}
	
	// 보고싶어요 삭제
	@RequestMapping("/like_delete_proc.do")
	public String like_delete_proc(HttpServletRequest request, HttpSession session, Model model) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		
		String movie_code = request.getParameter("movie_code");
		String sessionID = (String) session.getAttribute("sessionID");
		
		dao.like_delete(sessionID, movie_code);
		
		
		return "redirect:contentview.do?movie_code=" + movie_code;
	}
	
	
	
	// 좋아요 ajax
	@ResponseBody
	@RequestMapping("/comment_like_proc.do")
	public String comment_like_proc(HttpServletRequest request, HttpSession session, Model model) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		
		String comment_seq = request.getParameter("comment_seq");
		String sessionID = (String) session.getAttribute("sessionID");

//		System.out.println(comment_seq + " : " + sessionID);
		
		String like_search = dao.get_comment_like_search(comment_seq, sessionID);
		
		if(like_search.equals("0")) {
			dao.comment_like_insert(comment_seq, sessionID);
//			System.out.println("좋아요");
					
		} else {
			dao.comment_like_delete(comment_seq, sessionID);
//			System.out.println("좋아요취소");
		}
		// 좋아요 총 갯수 업데이트
		dao.comment_like_update(comment_seq, like_search);
		
		
		return like_search;
		
	}
	
	//내가 쓴 코멘트 삭제
    @RequestMapping("/my_comment_delete_proc")
    public String my_comment_delete_proc(HttpServletRequest request, HttpSession session) {
       MovieDao dao = sqlSession.getMapper(MovieDao.class);
       
       String comment_seq = request.getParameter("comment_seq");
       String movie_code = request.getParameter("movie_code");
       
       dao.my_comment_delete(comment_seq);
       
       
       return "redirect:contentview.do?movie_code="+movie_code;
    }

    //내가 쓴 코멘트 불러오기
    @RequestMapping("/comment_edit")
    public String my_comment_edit(HttpServletRequest request,Model model,HttpSession session) {
       MovieDao dao = sqlSession.getMapper(MovieDao.class);
       
       String comment_seq = request.getParameter("comment_seq");
       String movie_title = request.getParameter("movie_title");
       
       model.addAttribute("movie_title",movie_title);

       MovieCommentDto cdto = dao.my_comment_read(comment_seq);
       model.addAttribute("comment", cdto);
                   
       return "contentview/comment_edit";
    }
    
    //내가 쓴 코멘트 수정
    @RequestMapping("/comment_edit_proc.do")
    public String my_comment_edit_proc(HttpServletRequest request, HttpSession session) {
       MovieDao dao = sqlSession.getMapper(MovieDao.class);
       
//     String sessionID = (String) session.getAttribute("sessionID");
       String movie_code = request.getParameter("movie_code");      
       String comment_content = request.getParameter("comment_content");
       String comment_seq = request.getParameter("comment_seq");
       
       dao.my_comment_edit(comment_seq, movie_code, comment_content);
       
       return "redirect:contentview.do?movie_code=" + movie_code;
    }
        
	// 공지사항
	@RequestMapping(value = "/noticeview.do", method = RequestMethod.GET)
	public String noticeView(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
//		String board_seq = request.getParameter("board_seq");

		ArrayList<NoticeBoardDto> dto = dao.get_noticeview();

		model.addAttribute("noticeView", dto);

		return "noticeview";
	}

	// 공지작성 view
	@RequestMapping("/noticewrite.do")
	public String NoticeWriteForm(HttpServletRequest request, Model model) {

		return "noticeBoard/noticeWrite";
	}

	// 공지작성proc
	@Transactional
	@RequestMapping("/noticewriteproc.do")
	public String NoticeWrite(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);

		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");

		dao.write_notice(board_title, board_content);

		System.out.println(board_title + " : " + board_content);

		return "redirect:noticeview.do";
	}

	// 공지수정 view
	@RequestMapping("/noticemodify.do")
	public String NoticeModifyForm(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		NoticeBoardDto dto = dao.modify_get_notice(request.getParameter("board_seq"));
		model.addAttribute("dto", dto);
		return "noticeBoard/noticeModify";
	}

	// 공지수정 proc
	@RequestMapping("/noticemodifyproc.do")
	public String NoticeModify(HttpServletRequest request, Model model) {
		/*
		 * BoardDao dao=sqlSession.getMapper(BoardDao.class);
		 * dao.modify_notice(request.getParameter("board_seq"),
		 * request.getParameter("board_title"), request.getParameter("board_content"));
		 */
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String board_seq = request.getParameter("board_seq");

		String board_title = request.getParameter("board_title");
		String board_content = request.getParameter("board_content");

		dao.modify_notice(board_seq, board_title, board_content);
		System.out.println(board_seq + " : " + board_title + " : " + board_content);
		return "redirect:noticeview.do";
	}

	// 공지삭제proc
	@RequestMapping("/noticedeleteproc.do")
	public String NoticeDelete(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String board_seq = request.getParameter("board_seq");
		dao.delete_notice(board_seq);
		return "redirect:noticeview.do";
	}
    
	// QnA view
	@RequestMapping(value = "/qnaview.do", method = RequestMethod.GET)
	public String qnaView(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);

		ArrayList<QnaBoardDto> dto = dao.get_qnaview();
		model.addAttribute("qnaView", dto);

		return "qnaview";
	}

	// QnA 작성 view
	@RequestMapping("/qnawrite.do")
	public String QnaWriteForm(HttpServletRequest request, Model model) {

		return "noticeBoard/qnaWrite";
	}

	// QnA 작성
	@Transactional
	@RequestMapping("/qnawriteproc.do")
	public String QnaWrite(HttpServletRequest request,HttpSession session , Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String sessionID = (String) session.getAttribute("sessionID");

		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");

		dao.write_qna(sessionID, qna_title, qna_content);

		System.out.println(qna_title + " : " + qna_content);

		return "redirect:qnaview.do";
	}

	// QnA 수정 view
	@RequestMapping("/qnamodify.do")
	public String QnaModifyForm(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		QnaBoardDto dto = dao.modify_get_qna(request.getParameter("qna_seq"));
		model.addAttribute("dto", dto);

		return "noticeBoard/qnaModify";

	}

	// QnA 수정
	@RequestMapping("/qnamodifyproc.do")
	public String QnaModify(HttpServletRequest request, Model model) {

		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String qna_seq = request.getParameter("qna_seq");
		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");

		dao.modify_qna(qna_seq, qna_title, qna_content);
		System.out.println(qna_seq + " : " + qna_title + " : " + qna_content);

		return "redirect:qnaview.do";

	}

	// QnA 삭제
	@RequestMapping("/qnadeleteproc.do")
	public String QnaDelete(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String qna_seq = request.getParameter("qna_seq");
		dao.delete_qna(qna_seq);
		return "redirect:qnaview.do";
	}

	// 답변 작성 view
	@RequestMapping("/qnareply.do")
	public String QnaReplyForm(HttpServletRequest request, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		QnaBoardDto dto = dao.reply_get_qna(request.getParameter("qna_seq"));
		model.addAttribute("dto", dto);

		return "noticeBoard/qnaReply";

	}

	// 답변 작성
	@RequestMapping("qnareplyproc.do")
	public String QnaReply(HttpServletRequest request, HttpSession session, Model model) {
		BoardDao dao = sqlSession.getMapper(BoardDao.class);
		String sessionID = (String) session.getAttribute("sessionID");

		String qna_seq = request.getParameter("qna_seq");

		QnaBoardDto qnaDto = dao.reply_get_qna(qna_seq);

		String qna_title = request.getParameter("qna_title");
		String qna_content = request.getParameter("qna_content");

		int qna_group = qnaDto.getQna_group();
		int qna_step = qnaDto.getqna_step();
		int qna_indent = qnaDto.getqna_indent();

		
		
		
		System.out.println(qna_title + " : " + qna_content + " : " + qna_group + " : " + qna_step + " : " + qna_indent);
		dao.reply_qna(sessionID, qna_title, qna_content, qna_group, qna_step, qna_indent);

		return "redirect:qnaview.do";
	}
    
    
    
    
    
    
    
    
    
    
    
    
	// 전체 추천영화 화면
	@RequestMapping("/recommendform.do")
	public String recommendform(HttpSession session, Model model) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		
		String sessionID = (String) session.getAttribute("sessionID");
//		String sessionID = "BLUEMING29";
		
		int eval_check = dao.eval_check_count(sessionID);
		
		if(eval_check != 0) {
				
			
			
			// 평가계산기 객체생성
			EvalCalculator eval_cal = new EvalCalculator();
			// Map 내림차순 정렬 시켜줄 객체생성
			EvalSort es = new EvalSort();
			
			// DB에서 내가 평가한 리스트 가져옴
			ArrayList<MyEvalMovieDto> eval_lists = dao.get_my_eval_movie(sessionID);
			
			// 평가하지 않은 영화 코드, 장르, 국가, 배우 가져오기
			ArrayList<NonEvalMovieListDto> movie_lists = dao.non_eval_list(sessionID);
			
	
			
			// Map에 장르에 대한 나의 평가 점수로 계산한 값 넣기
			Map<String, Integer> my_genre_map = eval_cal.eval_genre_cal(eval_lists);
			Map<String, Integer> my_nation_map = eval_cal.eval_nation_cal(eval_lists);
			Map<String, Integer> my_actor_map = eval_cal.eval_actor_cal(eval_lists);
			
			// 장르 유사도 담아둘 Map
			Map<Integer, Double> total_sim_map = new HashMap<Integer, Double>();
			
			
	
			Iterator<String> genre_keys = my_genre_map.keySet().iterator();
			// 나의 장르 벡터 제곱의 합
			int pow_b = 0;
			while (genre_keys.hasNext()) {
				String key = genre_keys.next();
				Integer value = my_genre_map.get(key);
				
				pow_b += Math.pow(value, 2);
				
	//			System.out.println(key + " : " + value);
			}
			
			Iterator<String> nation_keys = my_nation_map.keySet().iterator();
			// 나의 국가 벡터 제곱의 합
			while (nation_keys.hasNext()) {
				String key = nation_keys.next();
				Integer value = my_nation_map.get(key);
				
				pow_b += Math.pow(value, 2);
				
	//			System.out.println(key + " : " + value);
			}
			
			Iterator<String> actor_keys = my_actor_map.keySet().iterator();
			// 나의 배우 벡터 제곱의 합
			while (actor_keys.hasNext()) {
				String key = actor_keys.next();
				Integer value = my_actor_map.get(key);
				
				pow_b += Math.pow(value, 2);
				
	//			System.out.println(key + " : " + value);
			}
			
	
			for (int i = 0; i < movie_lists.size() ; i++) {
				
				int movie_code = movie_lists.get(i).getMovie_code();
				int a_dot_b = 0;
				int pow_a = 0;
				
				// 장르 제곱합
				if(movie_lists.get(i).getMovie_genre() != null) {
					String[] movie_genres = movie_lists.get(i).getMovie_genre().split(", ");
					pow_a = movie_genres.length;
					for (int j = 0; j < movie_genres.length; j++) {
						if(my_genre_map.containsKey(movie_genres[j])) {
							a_dot_b += my_genre_map.get(movie_genres[j]);
						}
					}
				}
				
				// 국가 제곱합
				if(movie_lists.get(i).getMovie_nation() != null) {
					String[] movie_nations = movie_lists.get(i).getMovie_nation().split(" , ");
					pow_a = movie_nations.length;
					for (int j = 0; j < movie_nations.length; j++) {
						if(my_nation_map.containsKey(movie_nations[j])) {
							a_dot_b += my_nation_map.get(movie_nations[j]);
						}
					}
				}
				
				// 배우 제곱합
				if(movie_lists.get(i).getMovie_actor() != null) {
					String text = movie_lists.get(i).getMovie_actor();
					
					// 괄호 안의 내용 지우기
					Pattern PATTERN_BRACKET = Pattern.compile("\\([^\\(\\)]+\\)");
					Matcher matcher = PATTERN_BRACKET.matcher(text);
					    
					String movie_actor = text;
					String removeTextArea = new String();
					
					while(matcher.find()) {
					    int startIndex = matcher.start();
					    int endIndex = matcher.end();
					    
					    removeTextArea = movie_actor.substring(startIndex, endIndex);
					    movie_actor = movie_actor.replace(removeTextArea, "");
					    matcher = PATTERN_BRACKET.matcher(movie_actor);
					}
	
	
								
					String[] movie_actors = movie_actor.split(", ");
					pow_a = movie_actors.length;
					for (int j = 0; j < movie_actors.length; j++) {
						if(my_actor_map.containsKey(movie_actors[j])) {
							a_dot_b += my_actor_map.get(movie_actors[j]);
						}
					}
				}
				
				if(pow_a != 0 && pow_b != 0) {
					double cos_similaty = a_dot_b / (Math.sqrt(pow_a) * (Math.sqrt(pow_b)) );
					total_sim_map.put(movie_code, cos_similaty);
	//				System.out.println("a_dot_b : " + a_dot_b);
	//				System.out.println("pow_a : " + pow_a);
	//				System.out.println("pow_b : " + pow_b);
					System.out.println(movie_code + " : " + cos_similaty);
				}
			}
	//		
	//		
			// 유사도 높은순으로 내림차순 정렬
			ArrayList<Integer> total_sim_list = es.sim_sort(total_sim_map);
			
	
			ArrayList<MovieDto> similar_movie = dao.get_similar_movie(total_sim_list);
			model.addAttribute("similar_dto", similar_movie);
		}
		return "recommendform";
	}

	
	
//////////////////////////////////////////////////////////////////////////////////////////////
    
	// 장르별 추천영화 화면
	@RequestMapping("/eval_genre_proc.do")
	public String eval_genre_proc(HttpSession session, Model model) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);
		
		String sessionID = (String) session.getAttribute("sessionID");
//		String sessionID = "BLUEMING29";
		
		
		// 평가계산기 객체생성
		EvalCalculator eval_cal = new EvalCalculator();
		// Map 내림차순 정렬 시켜줄 객체생성
		EvalSort es = new EvalSort();
		
		// DB에서 내가 평가한 리스트 가져옴
		ArrayList<MyEvalMovieDto> eval_lists = dao.get_my_eval_movie(sessionID);
		
		// 평가하지 않은 영화 코드, 장르, 국가, 배우 가져오기
		ArrayList<NonEvalMovieListDto> movie_lists = dao.non_eval_list(sessionID);

		// 유사도 담아둘 Map
		Map<Integer, Double> genre_sim_map = new HashMap<Integer, Double>();
		
		// Map에 장르에 대한 나의 평가 점수로 계산한 값 넣기
		Map<String, Integer> my_genre_map = eval_cal.eval_genre_cal(eval_lists);
		
		

		Iterator<String> keys = my_genre_map.keySet().iterator();
		// 나의 벡터 제곱의 합
		int pow_b = 0;
		while (keys.hasNext()) {
			String key = keys.next();
			Integer value = my_genre_map.get(key);
			
			pow_b += Math.pow(value, 2);
			System.out.println(key + " : " + value);
		}
		
		for (int i = 0; i < movie_lists.size() ; i++) {
			
			int movie_code = movie_lists.get(i).getMovie_code();

			int a_dot_b = 0;
			int pow_a = 0;
			String[] movie_genres = movie_lists.get(i).getMovie_genre().split(", ");
			pow_a += movie_genres.length;
			for (int j = 0; j < movie_genres.length; j++) {
				if(my_genre_map.containsKey(movie_genres[j])) {
					a_dot_b += my_genre_map.get(movie_genres[j]);
				}
			}
			
			if(pow_a != 0 && pow_b != 0) {
				double cos_similaty = a_dot_b / (Math.sqrt(pow_a) * (Math.sqrt(pow_b)) + Math.pow(10, -8) );
				genre_sim_map.put(movie_code, cos_similaty);
				System.out.println(movie_code + " 영화와의 유사도 : " + cos_similaty);
			}
		}
	
		// 유사도 높은순으로 내림차순 정렬
		ArrayList<Integer> genre_sim_list = es.sim_sort(genre_sim_map);
		
		for (int i = 0; i < genre_sim_list.size(); i++) {
			System.out.println(genre_sim_list.get(i));
			
		}
		
		ArrayList<MovieDto> similar_movie = dao.get_similar_movie(genre_sim_list);
		model.addAttribute("similar_dto", similar_movie);

		
		
		
		
		
//		// 나의 장르 벡터 만들기
//		Map<String, Integer> genre_info_by_id = eval_cal.info_map_id(my_genre_map, total_genre_map);
//		ArrayList<MyEvalListDto> genre_list_by_id = es.input_list(genre_info_by_id);
//		
//		
//		
//		for (int i = 0; i < movie_lists.size(); i++) {
//			int movie_code = movie_lists.get(i).getEval_point();
//			
//			int movie_code = movie_codes.get(i);
//			
//			
//			// 전체 장르에 대한 영벡터 생성
//			Map<String, Integer> total_genre_map = eval_cal.total_genre(total_movie_list);
//			
//			// 샘플 영화의 정보 불러오기
//			MovieDto movie_info = dao.get_movie_info(movie_code);
//			
//			// 샘플 영화의 장르 벡터 만들기
//			Map<String, Integer> genre_info_by_code = eval_cal.genre_info_map(movie_info, total_genre_map);
//			ArrayList<MyEvalListDto> genre_list_by_code = es.input_list(genre_info_by_code);
//			
//	
//			// 코사인 유사도 객체 생성
//			CosineSimilaty cs = new CosineSimilaty();
//			
//			double cos_similaty = cs.get_cosSimilaty(genre_list_by_code, genre_list_by_id);
//			
//			System.out.println(i + " - " + movie_code + "와의 유사도 : " + cos_similaty);
//			
//			genre_sim_map.put(movie_code, cos_similaty);
//			System.out.println(i);
//		}	
		
		
//		// 유사도 높은순으로 내림차순 정렬
//		ArrayList<Integer> genre_sim_list = es.sim_sort(genre_sim_map);
//		
//		
//		for (int i = 0; i < genre_sim_list.size(); i++) {
//			System.out.println(genre_sim_list.get(i));
//			
//		}
		
//		ArrayList<MovieDto> similar_movie = dao.get_similar_movie(genre_sim_list);
//		model.addAttribute("similar_dto", similar_movie);
		return "recommendform";
	}
	
	
	
	
	
	
	// 국가별 추천
	@RequestMapping("/eval_nation_proc.do")
	public String eval_nation_proc(HttpSession session, Model model) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);

		String sessionID = (String) session.getAttribute("sessionID");
//		String sessionID = "BLUEMING29";
		
		// 평가계산기 객체생성
		EvalCalculator eval_cal = new EvalCalculator();
		// Map 내림차순 정렬 시켜줄 객체생성
		EvalSort es = new EvalSort();
		
		// DB에서 내가 평가한 리스트 가져옴
		ArrayList<MyEvalMovieDto> eval_lists = dao.get_my_eval_movie(sessionID);
		
		// 평가하지 않은 영화 코드, 장르, 국가, 배우 가져오기
		ArrayList<NonEvalMovieListDto> movie_lists = dao.non_eval_list(sessionID);

		// 유사도 담아둘 Map
		Map<Integer, Double> nation_sim_map = new HashMap<Integer, Double>();
		
		// Map에 국가에 대한 나의 평가 점수로 계산한 값 넣기
		Map<String, Integer> my_nation_map = eval_cal.eval_nation_cal(eval_lists);
		
		

		Iterator<String> keys = my_nation_map.keySet().iterator();
		// 나의 벡터 제곱의 합
		int pow_b = 0;
		while (keys.hasNext()) {
			String key = keys.next();
			Integer value = my_nation_map.get(key);
			
			pow_b += Math.pow(value, 2);
			System.out.println(key + " : " + value);
		}
		
		for (int i = 0; i < movie_lists.size() ; i++) {
			
			int movie_code = movie_lists.get(i).getMovie_code();

			int a_dot_b = 0;
			int pow_a = 0;
			String[] movie_nations = movie_lists.get(i).getMovie_nation().split(" , ");
			pow_a = movie_nations.length;
			for (int j = 0; j < movie_nations.length; j++) {
				if(my_nation_map.containsKey(movie_nations[j])) {
					a_dot_b += my_nation_map.get(movie_nations[j]);
				}
			}
			
			if(pow_a != 0 && pow_b != 0) {
				double cos_similaty = a_dot_b / (Math.sqrt(pow_a) * (Math.sqrt(pow_b)) );
				nation_sim_map.put(movie_code, cos_similaty);
//				System.out.println("a_dot_b : " + a_dot_b);
//				System.out.println("pow_a : " + pow_a);
//				System.out.println("pow_b : " + pow_b);
				System.out.println(movie_code + " : " + cos_similaty);
			}
		}
		
		
		// 유사도 높은순으로 내림차순 정렬
		ArrayList<Integer> nation_sim_list = es.sim_sort(nation_sim_map);
		
		for (int i = 0; i < nation_sim_list.size(); i++) {
			System.out.println(nation_sim_list.get(i));
			
		}
		
		ArrayList<MovieDto> similar_movie = dao.get_similar_movie(nation_sim_list);
		model.addAttribute("similar_dto", similar_movie);
		
		return "recommendform";
	}
	
	
	
	
	// 배우별 추천
	@RequestMapping("/eval_actor_proc.do")
	public String eval_actor_proc(HttpSession session, Model model) {
		MovieDao dao = sqlSession.getMapper(MovieDao.class);

		String sessionID = (String) session.getAttribute("sessionID");
//		String sessionID = "BLUEMING29";
		
		// 평가계산기 객체생성
		EvalCalculator eval_cal = new EvalCalculator();
		// Map 내림차순 정렬 시켜줄 객체생성
		EvalSort es = new EvalSort();
		
		// DB에서 내가 평가한 리스트 가져옴
		ArrayList<MyEvalMovieDto> eval_lists = dao.get_my_eval_movie(sessionID);
		
		// 평가하지 않은 영화 코드, 장르, 국가, 배우 가져오기
		ArrayList<NonEvalMovieListDto> movie_lists = dao.non_eval_list(sessionID);

		// 유사도 담아둘 Map
		Map<Integer, Double> actor_sim_map = new HashMap<Integer, Double>();
		
		// Map에 장르에 대한 나의 평가 점수로 계산한 값 넣기
		Map<String, Integer> my_actor_map = eval_cal.eval_actor_cal(eval_lists);
		
		

		Iterator<String> keys = my_actor_map.keySet().iterator();
		// 나의 벡터 제곱의 합
		int pow_b = 0;
		while (keys.hasNext()) {
			String key = keys.next();
			Integer value = my_actor_map.get(key);
			
			pow_b += Math.pow(value, 2);
			System.out.println(key + " : " + value);
		}
		
		for (int i = 0; i < movie_lists.size() ; i++) {
			
			int movie_code = movie_lists.get(i).getMovie_code();

			int a_dot_b = 0;
			int pow_a = 0;
			
			if(movie_lists.get(i).getMovie_actor() != null) {
				String text = movie_lists.get(i).getMovie_actor();
				
				// 괄호 안의 내용 지우기
				Pattern PATTERN_BRACKET = Pattern.compile("\\([^\\(\\)]+\\)");
				Matcher matcher = PATTERN_BRACKET.matcher(text);
				    
				String movie_actor = text;
				String removeTextArea = new String();
				
				while(matcher.find()) {
				    int startIndex = matcher.start();
				    int endIndex = matcher.end();
				    
				    removeTextArea = movie_actor.substring(startIndex, endIndex);
				    movie_actor = movie_actor.replace(removeTextArea, "");
				    matcher = PATTERN_BRACKET.matcher(movie_actor);
				}


							
				String[] movie_actors = movie_actor.split(", ");
				pow_a = movie_actors.length;
				for (int j = 0; j < movie_actors.length; j++) {
					if(my_actor_map.containsKey(movie_actors[j])) {
						a_dot_b += my_actor_map.get(movie_actors[j]);
					}
				}
			
				if(pow_a != 0 && pow_b != 0) {
					double cos_similaty = a_dot_b / (Math.sqrt(pow_a) * (Math.sqrt(pow_b)) );
					actor_sim_map.put(movie_code, cos_similaty);
//					System.out.println("a_dot_b : " + a_dot_b);
//					System.out.println("pow_a : " + pow_a);
//					System.out.println("pow_b : " + pow_b);
					System.out.println(movie_code + " : " + cos_similaty);
				}
			}
		}
//		
//		
		// 유사도 높은순으로 내림차순 정렬
		ArrayList<Integer> actor_sim_list = es.sim_sort(actor_sim_map);
		
		for (int j = 0; j < actor_sim_list.size(); j++) {
			System.out.println(actor_sim_list.get(j));
			
		}
		
		ArrayList<MovieDto> similar_movie = dao.get_similar_movie(actor_sim_list);
		model.addAttribute("similar_dto", similar_movie);
	
		return "recommendform";
	}

	
	@RequestMapping("/dbinsertform.do")
	public String dbinsertform(HttpServletRequest request, Model model) {	
		String result = request.getParameter("result");

		if(result != null) {
			model.addAttribute("result", result);
		}
		
	
		return "dbinsert/dbinsertform";
	}
	
	@ResponseBody
	@RequestMapping(value = "/dbsearch.do", method=RequestMethod.GET)
	public Map<String, Object> dbsearch(HttpServletRequest request, Model model) {	
		String movie_code = request.getParameter("movie_code");
//		System.out.println(movie_code);
		
		Map<String, Object> movie_list = new HashMap<>();
		
		// 정보 crawler
		MovieInfoCrawler info_crl = new MovieInfoCrawler();
		String searched_list = info_crl.Crawling(movie_code);
		String[] splited_list = searched_list.split("\t");

		movie_list.put("movie_img", splited_list[0]);
		movie_list.put("movie_title", splited_list[1]);
		movie_list.put("movie_point", splited_list[2]);
		movie_list.put("movie_genre", splited_list[3]);
		movie_list.put("movie_nation", splited_list[4]);
		movie_list.put("movie_runtime", splited_list[5]);
		movie_list.put("movie_releasedate", splited_list[6]);
		movie_list.put("movie_director", splited_list[7]);
		movie_list.put("movie_actor", splited_list[8]);
		movie_list.put("movie_content", splited_list[9]);
		
		
		
		// 스틸컷 crawler
		MovieImgCrawler img_crl = new MovieImgCrawler();
		ArrayList<String> movie_imgs = img_crl.ImgCrawler();
		movie_list.put("movie_img1", movie_imgs.get(0));
		movie_list.put("movie_img2", movie_imgs.get(1));
		movie_list.put("movie_img3", movie_imgs.get(2));
		movie_list.put("movie_img4", movie_imgs.get(3));
		movie_list.put("movie_img5", movie_imgs.get(4));
		
		return movie_list;
	}
	
	@RequestMapping("/dbinsertproc.do")
	public String dbinsertproc(Model model, HttpServletRequest request) {	
		MovieDao dao = sqlSession.getMapper(MovieDao.class);

		String movie_img1 = request.getParameter("movie_img1");
		String movie_img2 = request.getParameter("movie_img2");
		String movie_img3 = request.getParameter("movie_img3");
		String movie_img4 = request.getParameter("movie_img4");
		String movie_img5 = request.getParameter("movie_img5");
		
		String movie_img = request.getParameter("movie_img");
		String movie_code = request.getParameter("movie_code");
		String movie_title = request.getParameter("movie_title");
		String movie_point = request.getParameter("movie_point");
		String movie_genre = request.getParameter("movie_genre");
		String movie_nation = request.getParameter("movie_nation");
		String movie_runtime = request.getParameter("movie_runtime");
		String movie_releasedate = request.getParameter("movie_releasedate");
		String movie_director = request.getParameter("movie_director");
		String movie_actor = request.getParameter("movie_actor");
		String movie_content = request.getParameter("movie_content");
		
		
		int check = dao.exist_movie_check(movie_code);
		String result;
		if(check == 0) {
			dao.movie_imgs_insert(movie_code, movie_img1, movie_img2, movie_img3, movie_img4, movie_img5);
			dao.movie_info_insert(movie_img, movie_code, movie_title, movie_point, movie_genre,
					movie_nation, movie_runtime, movie_releasedate, movie_director, movie_actor, movie_content);
			result = "success";
		} else {
			result = "fail";
		}
		
		
		

				
		return "redirect:dbinsertform.do?result=" + result;
	}

	
	
	@RequestMapping("/thankyou")
	public String thankyou() {	
	
		return "thankyou";
	}
}
