package movie.rec.movilex.dto;

public class MainPageDto {
	public int movie_code;
	public String movie_img;
	public String movie_title;
	public int movie_releasedate;
	public String movie_nation;
	public double movie_point;
	public int eval_point;
	
	

	public int getMovie_code() {
		return movie_code;
	}

	public void setMovie_code(int movie_code) {
		this.movie_code = movie_code;
	}

	public String getMovie_img() {
		return movie_img;
	}

	public void setMovie_img(String movie_img) {
		this.movie_img = movie_img;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public int getMovie_releasedate() {
		return movie_releasedate;
	}

	public void setMovie_releasedate(int movie_releasedate) {
		this.movie_releasedate = movie_releasedate;
	}

	public String getMovie_nation() {
		return movie_nation;
	}

	public void setMovie_nation(String movie_nation) {
		this.movie_nation = movie_nation;
	}

	public double getMovie_point() {
		return movie_point;
	}

	public void setMovie_point(double movie_point) {
		this.movie_point = movie_point;
	}

	public int getEval_point() {
		return eval_point;
	}

	public void setEval_point(int eval_point) {
		this.eval_point = eval_point;
	}

	public MainPageDto() {
		// TODO Auto-generated constructor stub
	}

	public MainPageDto(int movie_code, String movie_img, String movie_title, int movie_releasedate, String movie_nation,
			double movie_point, int eval_point) {
		super();
		this.movie_code = movie_code;
		this.movie_img = movie_img;
		this.movie_title = movie_title;
		this.movie_releasedate = movie_releasedate;
		this.movie_nation = movie_nation;
		this.movie_point = movie_point;
		this.eval_point = eval_point;
	}

	
}
