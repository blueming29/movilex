package movie.rec.movilex.dto;

public class MovieDto {
	private String movie_img;
	private int movie_code;
	private String movie_title;
	private double movie_point;
	private String movie_genre;
	private String movie_nation;
	private String movie_runtime;
	private int movie_releasedate;
	private String movie_director;
	private String movie_actor;
	private String movie_content;
	
	public String getMovie_img() {
		return movie_img;
	}
	public void setMovie_img(String movie_img) {
		this.movie_img = movie_img;
	}
	public int getMovie_code() {
		return movie_code;
	}
	public void setMovie_code(int movie_code) {
		this.movie_code = movie_code;
	}
	public String getMovie_title() {
		return movie_title;
	}
	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}
	public double getMovie_point() {
		return movie_point;
	}
	public void setMovie_point(double movie_point) {
		this.movie_point = movie_point;
	}
	public String getMovie_genre() {
		return movie_genre;
	}
	public void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
	}
	public String getMovie_nation() {
		return movie_nation;
	}
	public void setMovie_nation(String movie_nation) {
		this.movie_nation = movie_nation;
	}
	public String getMovie_runtime() {
		return movie_runtime;
	}
	public void setMovie_runtime(String movie_runtime) {
		this.movie_runtime = movie_runtime;
	}
	public int getMovie_releasedate() {
		return movie_releasedate;
	}
	public void setMovie_releasedate(int movie_releasedate) {
		this.movie_releasedate = movie_releasedate;
	}
	public String getMovie_director() {
		return movie_director;
	}
	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}
	public String getMovie_actor() {
		return movie_actor;
	}
	public void setMovie_actor(String movie_actor) {
		this.movie_actor = movie_actor;
	}
	public String getMovie_content() {
		return movie_content;
	}
	public void setMovie_content(String movie_content) {
		this.movie_content = movie_content;
	}
	public MovieDto() {
		// TODO Auto-generated constructor stub
	}
	public MovieDto(String movie_img, int movie_code, String movie_title, double movie_point, String movie_genre,
			String movie_nation, String movie_runtime, int movie_releasedate, String movie_director, String movie_actor,
			String movie_content) {
		super();
		this.movie_img = movie_img;
		this.movie_code = movie_code;
		this.movie_title = movie_title;
		this.movie_point = movie_point;
		this.movie_genre = movie_genre;
		this.movie_nation = movie_nation;
		this.movie_runtime = movie_runtime;
		this.movie_releasedate = movie_releasedate;
		this.movie_director = movie_director;
		this.movie_actor = movie_actor;
		this.movie_content = movie_content;
	}
	

	
}
