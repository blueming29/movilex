package movie.rec.movilex.dto;

public class MovieImgDto {
	private int movie_code;
	private String movie_img1;
	private String movie_img2;
	private String movie_img3;
	private String movie_img4;
	private String movie_img5;
	public int getMovie_code() {
		return movie_code;
	}
	public void setMovie_code(int movie_code) {
		this.movie_code = movie_code;
	}
	public String getMovie_img1() {
		return movie_img1;
	}
	public void setMovie_img1(String movie_img1) {
		this.movie_img1 = movie_img1;
	}
	public String getMovie_img2() {
		return movie_img2;
	}
	public void setMovie_img2(String movie_img2) {
		this.movie_img2 = movie_img2;
	}
	public String getMovie_img3() {
		return movie_img3;
	}
	public void setMovie_img3(String movie_img3) {
		this.movie_img3 = movie_img3;
	}
	public String getMovie_img4() {
		return movie_img4;
	}
	public void setMovie_img4(String movie_img4) {
		this.movie_img4 = movie_img4;
	}
	public String getMovie_img5() {
		return movie_img5;
	}
	public void setMovie_img5(String movie_img5) {
		this.movie_img5 = movie_img5;
	}
	
	public MovieImgDto() {
		// TODO Auto-generated constructor stub
	}
	public MovieImgDto(int movie_code, String movie_img1, String movie_img2, String movie_img3, String movie_img4,
			String movie_img5) {
		super();
		this.movie_code = movie_code;
		this.movie_img1 = movie_img1;
		this.movie_img2 = movie_img2;
		this.movie_img3 = movie_img3;
		this.movie_img4 = movie_img4;
		this.movie_img5 = movie_img5;
	}
	
}
