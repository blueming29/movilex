package movie.rec.movilex.dto;

public class MyEvalMovieDto {
	private int eval_point;
	private String movie_genre;
	private String movie_actor;
	private String movie_nation;

	public MyEvalMovieDto() {
		// TODO Auto-generated constructor stub
	}

	public MyEvalMovieDto(int eval_point, String movie_genre, String movie_actor, String movie_nation) {
		super();
		this.eval_point = eval_point;
		this.movie_genre = movie_genre;
		this.movie_actor = movie_actor;
		this.movie_nation = movie_nation;
	}

	public int getEval_point() {
		return eval_point;
	}

	public void setEval_point(int eval_point) {
		this.eval_point = eval_point;
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
