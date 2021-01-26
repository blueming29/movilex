package movie.rec.movilex.crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieLinkCrawler {

	public static void main(String[] args) {
		Document html;

		try {
			for (int i = 1; i <= 40; i++) {
				html = Jsoup.connect("https://movie.naver.com/movie/sdb/rank/rmovie.nhn?sel=pnt&date=20201217&page=" + i)
						.get();
				for (int j = 0; j < 50; j++) {
					Elements titles = html.select(".tit5 a");
					String titleLink = titles.get(j).attr("href");
					String movieCode = titleLink.substring(titleLink.indexOf("=") + 1, titleLink.length());

					System.out.println("링크 : " + titleLink); // 링크
					System.out.println("영화코드 : " + movieCode); // 영화코드

					String title = titles.get(j).text();
					System.out.println("제목 : " + title);
					
					Elements points = html.select(".point");
					String point = points.get(j).text(); 
					System.out.println("평점 : " + point);

					MovieInfoCrawler crawler = new MovieInfoCrawler();
					String movieList = crawler.Crawling(movieCode) + movieCode + "\t" + point;
					
					System.out.println(movieList);
					
					BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\JEONGIN\\최종프로젝트관련\\movieList.txt", true));
					bw.write(movieList + "\n");
					bw.close();
					
					System.out.println();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
