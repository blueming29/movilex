package movie.rec.movilex.dto;

public class PopularSearchDto {
	private String pop_title;
	private int cnt;
	
	public PopularSearchDto() {
		// TODO Auto-generated constructor stub
	}

	public PopularSearchDto(String pop_title, int cnt) {
		super();
		this.pop_title = pop_title;
		this.cnt = cnt;
	}

	public String getPop_title() {
		return pop_title;
	}

	public void setPop_title(String pop_title) {
		this.pop_title = pop_title;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
