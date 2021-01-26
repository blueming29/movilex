package movie.rec.movilex.dto;

public class CommentLikeDto {
	private int comment_seq;
	private String user_id;
	private int like_check;
	
	public CommentLikeDto() {
		// TODO Auto-generated constructor stub
	}

	
	public CommentLikeDto(int comment_seq, String user_id, int like_check) {
		super();
		this.comment_seq = comment_seq;
		this.user_id = user_id;
		this.like_check = like_check;
	}


	public int getComment_seq() {
		return comment_seq;
	}

	public void setComment_seq(int comment_seq) {
		this.comment_seq = comment_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getLike_check() {
		return like_check;
	}

	public void setLike_check(int like_check) {
		this.like_check = like_check;
	}
	
	
}
