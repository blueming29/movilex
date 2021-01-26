package movie.rec.movilex.dto;

public class NoticeBoardDto {
	private int board_seq;
	private String user_id;
	private String board_title;
	private String board_content;
	private String board_date;
	
	public NoticeBoardDto() {
		// TODO Auto-generated constructor stub
	}

	
	
	public NoticeBoardDto(int board_seq, String user_id, String board_title, String board_content, String board_date) {
		super();
		this.board_seq = board_seq;
		this.user_id = user_id;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_date = board_date;
	}



	public int getBoard_seq() {
		return board_seq;
	}

	public void setBoard_seq(int board_seq) {
		this.board_seq = board_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	
	
}
