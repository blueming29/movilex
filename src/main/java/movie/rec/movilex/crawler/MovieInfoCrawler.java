package movie.rec.movilex.crawler;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieInfoCrawler {
	public String Crawling(String movieCode) {
		Document html;
		String movieList = "";

		try {

			html = Jsoup.connect("https://movie.naver.com/movie/bi/mi/basic.nhn?code=" + movieCode).get();

			Elements imgs = html.select(".poster a > img");
			String img = imgs.get(1).attr("src");
//			System.out.println("이미지 url : " + img);					// 이미지
			
			Elements titles = html.select(".h_movie a");
			String title = titles.get(0).text();		// 영화제목
//			System.out.println("영화제목 : " + title);
			
			Elements star_scores = html.select(".score_left em");
			String star_score = "";
			for (int i = 3; i < star_scores.size(); i++) {
				star_score += star_scores.get(i).text();
			}

//			System.out.println("평점 : " + star_score);
			
			Elements specs = html.select(".info_spec dd span");
			ArrayList<String> spec = new ArrayList<String>();
			for (int i = 0; i < specs.size(); i++) {
				spec.add(specs.get(i).text());
			}
		
			String genre = spec.get(0);			// 장르
			String nation = spec.get(1);		// 국적
			String runningTime = spec.get(2);	//러닝타임
			String releaseDate = "";	// 개봉일
			if(spec.size() <= 3) {
				releaseDate = "";
			} else {
				releaseDate = spec.get(3).substring(0, 4);	// 개봉일
			}
			
//			System.out.println("장르 : " + genre);
//			System.out.println("국적 : " + nation);
//			System.out.println("상영시간 : " + runningTime);
//			System.out.println("개봉일 : " + releaseDate);
			
			Elements specs2 = html.select(".info_spec dd p");
			ArrayList<String> spec2 = new ArrayList<String>();

			for (int i = 0; i < specs2.size(); i++) {
				spec2.add(specs2.get(i).text());
			}
			
			String director = spec2.get(1);		// 감독
			String actor = spec2.get(2);		// 배우
//			System.out.println("감독 : " + director);
//			System.out.println("출연배우 : " + actor);

			Elements contents = html.select(".story_area .con_tx");
			String content = contents.text();		// 줄거리
//			System.out.println("줄거리 : " + content);
			
			movieList = img + "\t" + title + "\t" + star_score + "\t" + genre + "\t" + nation + "\t" + runningTime + "\t"
					+ releaseDate + "\t" + director + "\t" + actor + "\t" + content + "\t";
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return movieList;
	}
}
