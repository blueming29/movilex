package movie.rec.movilex.crawler;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieLinkCrawler2 {

	public static void main(String[] args) {
		Document html;

		try {
				html = Jsoup.connect("https://movie.naver.com/movie/running/current.nhn?view=list&tab=normal&order=reserve").get();
				Elements points = html.select(".star .info_star .star_t1 .num");
				System.out.println(points.size());
				
				Elements links = html.select(".star .info_star .star_t1 a");
				System.out.println(links.size());
				
				Elements titles = html.select(".lst_dsc .tit a");
				System.out.println(titles.size());
				for(int i = 0 ; i < titles.size() ; i++) {
					String str_point = points.get(i).text();
					double point = Double.parseDouble(str_point);
					String title = titles.get(i).text();
					String link = links.get(i).attr("href");
					String movieCode = link.substring(link.indexOf("=") + 1, link.indexOf("#"));
					
					if(point < 8.1) {
						System.out.println(point + " : " + title + " : " + movieCode);
						MovieInfoCrawler crawler = new MovieInfoCrawler();
						String movieList = crawler.Crawling(movieCode) + movieCode + "\t" + str_point;
						BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\JEONGIN\\최종프로젝트관련\\movieList2.txt", true));
						bw.write(movieList + "\n");
						bw.close();
					}
				}
				
			/*	for (int j = 0; j < 50; j++) {
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
				}*/

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
