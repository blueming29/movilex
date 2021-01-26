package movie.rec.movilex.dto;

import java.sql.Date;

public class MemberDto {
	private String user_id;
	private String user_pw;
	private String user_name;
	private Date reg_date;
	public MemberDto() {
		// TODO Auto-generated constructor stub
	}
	public MemberDto(String user_id, String user_pw, String user_name, Date reg_date) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.reg_date = reg_date;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	
}
