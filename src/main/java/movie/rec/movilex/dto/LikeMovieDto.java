package movie.rec.movilex.dto;

public class LikeMovieDto {
	// 영화 리스트와 JOIN
	private int like_seq;
	private String user_id;
	private int movie_code;
	private String movie_img;
	private String movie_title;
	private double movie_point;
	private int eval_point;
	

	public int getLike_seq() {
		return like_seq;
	}

	public void setLike_seq(int like_seq) {
		this.like_seq = like_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getMovie_code() {
		return movie_code;
	}

	public void setMovie_code(int movie_code) {
		this.movie_code = movie_code;
	}

	public String getMovie_img() {
		return movie_img;
	}

	public void setMovie_img(String movie_img) {
		this.movie_img = movie_img;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	
	public double getMovie_point() {
		return movie_point;
	}

	public void setMovie_point(double movie_point) {
		this.movie_point = movie_point;
	}
	
	public int getEval_point() {
		return eval_point;
	}

	public void setEval_point(int eval_point) {
		this.eval_point = eval_point;
	}

	public LikeMovieDto() {
		// TODO Auto-generated constructor stub
	}

	public LikeMovieDto(int like_seq, String user_id, int movie_code, String movie_img,
			String movie_title, double movie_point, int eval_point) {
		super();
		this.like_seq = like_seq;
		this.user_id = user_id;
		this.movie_code = movie_code;
		this.movie_img = movie_img;
		this.movie_title = movie_title;
		this.movie_point = movie_point;
		this.eval_point = eval_point;
	}
	
	
}
