package movie.rec.movilex.dao;

import java.util.ArrayList;

import movie.rec.movilex.dto.NoticeBoardDto;
import movie.rec.movilex.dto.QnaBoardDto;  



public interface BoardDao {
	
	// 공지사항
	public ArrayList<NoticeBoardDto> get_noticeview();
	
	public void write_notice(String board_title, String board_content);

	public NoticeBoardDto modify_get_notice(String board_seq);
	
	public void modify_notice(String board_seq,String board_title, String board_content);

	public void delete_notice(String board_seq);
	
	
	
	// QnA
	public ArrayList<QnaBoardDto> get_qnaview();

	public void write_qna(String sessionID, String qna_title, String qna_content);

	public QnaBoardDto modify_get_qna(String qna_seq);

	public void modify_qna(String qna_seq, String qna_title, String qna_content);

	public void delete_qna(String qna_seq);

	public QnaBoardDto reply_get_qna(String qna_seq);

	public void reply_qna(String sessionID, String qna_title, String qna_content, int qna_group, int qna_step, int qna_indent);

}
