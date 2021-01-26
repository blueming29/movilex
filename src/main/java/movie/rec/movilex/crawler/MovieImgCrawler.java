package movie.rec.movilex.crawler;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieImgCrawler {

	public ArrayList<String> ImgCrawler() {
		Document html;

		ArrayList<String> movie_imgs = new ArrayList<>();
		try {
				html = Jsoup.connect("https://movie.naver.com/movie/bi/mi/photoView.nhn?code=189457").get();
				Elements imgs = html.select(".rolling_list ._list");
				for (int i = 0; i < 5; i++) {
					String str_img = imgs.get(i).attr("data-json");
					int start_idx = str_img.indexOf("https://");
					int end_idx = str_img.indexOf("?type");
					String img = str_img.substring(start_idx, end_idx);
//					System.out.println(img);
					movie_imgs.add(img);
				}

				
				
				System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return movie_imgs;
	}
}
