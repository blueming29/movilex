package movie.rec.movilex.dto;

public class MovieEvalDto {
	private int eval_seq;
	private String user_id;
	private int movie_code;
	private int eval_point;
	public MovieEvalDto() {
		// TODO Auto-generated constructor stub
	}
	public MovieEvalDto(int eval_seq, String user_id, int movie_code, int eval_point) {
		super();
		this.eval_seq = eval_seq;
		this.user_id = user_id;
		this.movie_code = movie_code;
		this.eval_point = eval_point;
	}
	public int getEval_seq() {
		return eval_seq;
	}
	public void setEval_seq(int eval_seq) {
		this.eval_seq = eval_seq;
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
	public int getEval_point() {
		return eval_point;
	}
	public void setEval_point(int eval_point) {
		this.eval_point = eval_point;
	}
	
	
}
