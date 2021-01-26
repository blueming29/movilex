package movie.rec.movilex.dao;

import movie.rec.movilex.dto.MemberDto;

public interface MovieMemberDao {

	// 회원가입
	public void member_insert(String user_name, String user_pw, String email);

	// 로그인
	public int member_check(String email, String user_pw);

	// 중복확인
	public int email_check(String email);

	// 2차암호 체크
	public String get_user_pw(String email);

	public void update_pw(String email, String pw);

	// 회원정보
	public MemberDto get_member_info(String sessionID);

	public void member_update(String user_name, String bcPwd, String sessionID);

	public void user_withdrawal(String sessionID);

}
