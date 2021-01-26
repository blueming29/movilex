package movie.rec.movilex.dto;

public class MovieCommentDto {
	private int comment_seq;
	private int movie_code;
	private String user_id;
	private int user_point;
	private String comment_content;
	private int comment_like;
	public int getComment_seq() {
		return comment_seq;
	}
	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
	}
	public int getMovie_code() {
		return movie_code;
	}
	public void setMovie_code(int movie_code) {
		this.movie_code = movie_code;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getUser_point() {
		return user_point;
	}
	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public int getComment_like() {
		return comment_like;
	}
	public void setComment_like(int comment_like) {
		this.comment_like = comment_like;
	}
	
	public MovieCommentDto() {
		// TODO Auto-generated constructor stub
	}
	public MovieCommentDto(int comment_seq, int movie_code, String user_id, int user_point, String comment_content,
			int comment_like) {
		super();
		this.comment_seq = comment_seq;
		this.movie_code = movie_code;
		this.user_id = user_id;
		this.user_point = user_point;
		this.comment_content = comment_content;
		this.comment_like = comment_like;
	}
	
	
}
