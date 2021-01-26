package movie.rec.movilex.dto;

public class NonEvalMovieListDto {
	private int movie_code;
	private String movie_genre;
	private String movie_actor;
	private String movie_nation;
	public NonEvalMovieListDto() {
		// TODO Auto-generated constructor stub
	}
	public NonEvalMovieListDto(int movie_code, String movie_genre, String movie_actor, String movie_nation) {
		super();
		this.movie_code = movie_code;
		this.movie_genre = movie_genre;
		this.movie_actor = movie_actor;
		this.movie_nation = movie_nation;
	}
	public int getMovie_code() {
		return movie_code;
	}
	public void setMovie_code(int movie_code) {
		this.movie_code = movie_code;
	}
	public String getMovie_genre() {
		return movie_genre;
	}
	public void setMovie_genre(String movie_genre) {
		this.movie_genre = movie_genre;
	}
	public String getMovie_actor() {
		return movie_actor;
	}
	public void setMovie_actor(String movie_actor) {
		this.movie_actor = movie_actor;
	}
	public String getMovie_nation() {
		return movie_nation;
	}
	public void setMovie_nation(String movie_nation) {
		this.movie_nation = movie_nation;
	}
	
	
}
