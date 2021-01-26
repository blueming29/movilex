package movie.rec.movilex.crawler;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieBoxOfficeCrawler {
	public ArrayList<String> getMovieCode() {
		Document html;
		ArrayList<String> movie_codes = new ArrayList<String>();
		try {
			html = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn?order=reserve").get();

			Elements titleLinks = html.select(".lst_detail_t1 .thumb a");
			
			for(int i = 0 ; i < 30; i++)
			{
				String titleLink = titleLinks.get(i).attr("href");
				String movie_code = titleLink.substring(titleLink.indexOf("=") + 1, titleLink.length());
				movie_codes.add(movie_code);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movie_codes;
	}
}
