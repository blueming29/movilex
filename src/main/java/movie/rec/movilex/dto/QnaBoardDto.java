package movie.rec.movilex.dto;

public class QnaBoardDto {
	private int qna_seq;
	private String user_id;
	private String qna_title;
	private String qna_content;
	private String qna_date;
	private int qna_group;
	private int qna_step;
	private int qna_indent;
	
	
	
	public QnaBoardDto() {
		// TODO Auto-generated constructor stub
	}


	

	public QnaBoardDto(int qna_seq, String user_id, String qna_title, String qna_content, String qna_date,
			int qna_group, int qna_step, int qna_indent) {
		super();
		this.qna_seq = qna_seq;
		this.user_id = user_id;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_date = qna_date;
		this.qna_group = qna_group;
		this.qna_step = qna_step;
		this.qna_indent = qna_indent;
	}




	public int getQna_seq() {
		return qna_seq;
	}



	public void setQna_seq(int qna_seq) {
		this.qna_seq = qna_seq;
	}



	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public String getQna_title() {
		return qna_title;
	}



	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}



	public String getQna_content() {
		return qna_content;
	}



	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}



	public String getQna_date() {
		return qna_date;
	}



	public void setQna_date(String qna_date) {
		this.qna_date = qna_date;
	}



	public int getQna_group() {
		return qna_group;
	}



	public void setQna_group(int qna_group) {
		this.qna_group = qna_group;
	}



	public int getqna_step() {
		return qna_step;
	}



	public void setqna_step(int qna_step) {
		this.qna_step = qna_step;
	}



	public int getqna_indent() {
		return qna_indent;
	}



	public void setqna_indent(int qna_indent) {
		this.qna_indent = qna_indent;
	}
	
	
}




