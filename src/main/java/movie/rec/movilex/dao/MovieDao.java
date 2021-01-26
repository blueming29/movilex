package movie.rec.movilex.dao;

import java.util.ArrayList;

import movie.rec.movilex.dto.EvalMovieDto;
import movie.rec.movilex.dto.LikeMovieDto;
import movie.rec.movilex.dto.MainPageDto;
import movie.rec.movilex.dto.MovieCommentDto;
import movie.rec.movilex.dto.MovieDto;
import movie.rec.movilex.dto.MovieImgDto;
import movie.rec.movilex.dto.MyEvalMovieDto;
import movie.rec.movilex.dto.NonEvalMovieListDto;
import movie.rec.movilex.dto.PopularSearchDto;

public interface MovieDao {

	// 박스오피스 리스트
	public MainPageDto get_boxoffice(String sessionID, int movie_code);
	
	// 평점 리스트
	public ArrayList<MainPageDto> get_starPoint(String sessionID);
	
	// contentview
	public MovieDto get_contentview(int movie_code);

	// 평가 리스트
	public ArrayList<EvalMovieDto> get_eval_list(String sessionID, String idx);

	// 좋아요
	public ArrayList<LikeMovieDto> get_like_list(String sessionID, String idx);

	// 평점
//	public Integer get_eval_point(String sessionID, int movie_code);

	// 검색어 1개
	public ArrayList<MovieDto> get_searchs_list(String keyword);
	// 검색어 2개
	public ArrayList<MovieDto> get_searchs_list2(String keyword1, String keyword2);


	// 평점 가져오기
	public Integer get_eval_point_count(String sessionID, String movie_code);

	// 스틸컷 가져오기
	public MovieImgDto get_contentviewImg(int movie_code);


	// 평점 가져오기
	public MovieCommentDto get_point(String user_id);
	// 코멘트 가져오기
	public ArrayList<MovieCommentDto> get_comment(int movie_code);
	// 코멘트 작성하기
	public void write_comment(String moive_code, String sessionID, String eval_point, String comment_content);

	
	
	
	// 평점 삭제
	public void eval_point_delete(String sessionID, String movie_code);
	// 평점 입력
	public void eval_point_insert(String sessionID, String movie_code, String idx);
	// 평점 수정
	public void eval_point_update(String sessionID, String movie_code, String idx);

	
	// 보고싶어요 추가
	public void like_insert(String sessionID, String movie_code);
	// 보고싶어요 삭제
	public void like_delete(String sessionID, String movie_code);
	// 좋아요가 눌러졌는지 확인
	public int get_like_count(String sessionID, String movie_code);

	
	// 내가 평가를 했나 알아보기
	public int get_comment_exist(String sessionID, String movie_code);

	
	
	// 좋아요 부분
	public String get_comment_like_search(String comment_seq, String sessionID);

	public void comment_like_insert(String comment_seq, String sessionID);

	public void comment_like_delete(String comment_seq, String sessionID);

	
	
	
	

	public void comment_like_update(String comment_seq, String up_and_down);

	public ArrayList<String> get_comment_user_id(int comment_seq);

	public int get_star_point_by_movie_code(String movie_code, int point);

	public int get_star_point_by_sessionID(String sessionID, int point);

	public ArrayList<Integer> get_total_points(String sessionID);

	
	// 내가 남긴 코멘트 불러오기
	   public MovieCommentDto my_comment_read(String comment_seq);
	   
	   // 내가 남긴 코멘트 수정
	   public void my_comment_edit(String comment_seq, String movie_code, String comment_content);

	   // 내가 남긴 코멘트 삭제
	   public void my_comment_delete(String comment_seq);
	
	
	
	
	public ArrayList<MyEvalMovieDto> get_my_eval_movie(String sessionID);

	
	
	
	
	
	// 전체 영화 리스트 불러오기
//	public ArrayList<MovieDto> get_movie_list();

	// 평가안한 영화 코드 가져오기
	public ArrayList<NonEvalMovieListDto> non_eval_list(String sessionID);
	
	
	
	
	
	// 각 코드별 영화 리스트 불러오기
	public MovieDto get_movie_info(int movie_code);

	public int get_eval_check(String sessionID, int movie_code);

	public ArrayList<MovieDto> get_similar_movie(ArrayList<Integer> genre_sim_list);

	
	// 영화 이미지 넣기
	public int movie_imgs_insert(String movie_code, String movie_img1, String movie_img2, String movie_img3,
			String movie_img4, String movie_img5);

	// 영화 정보 넣기
	public int movie_info_insert(String movie_img, String movie_code, String movie_title, String movie_point,
			String movie_genre, String movie_nation, String movie_runtime, String movie_releasedate,
			String movie_director, String movie_actor, String movie_content);

	public int exist_movie_check(String movie_code);

	// 인기 검색어
	public ArrayList<PopularSearchDto> get_pop_search();

	public void insert_popsearch(String keyword);

	public String get_user_name(String sessionID);

	public int eval_check_count(String sessionID);

	public String get_comment_user_name(int comment_seq);






}
