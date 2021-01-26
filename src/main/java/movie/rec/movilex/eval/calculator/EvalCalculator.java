package movie.rec.movilex.eval.calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import movie.rec.movilex.dto.MovieDto;
import movie.rec.movilex.dto.MyEvalMovieDto;

public class EvalCalculator {

	// 평가 장르 계산
	public Map<String, Integer> eval_genre_cal(ArrayList<MyEvalMovieDto> eval_lists){
		Map<String, Integer> genre_map = new HashMap<String, Integer>();
		Map<String, Integer> genre_overlap_count = new HashMap<String, Integer>();
		
		// hashmap DATA 생성
		for(int i = 0; i < eval_lists.size(); i++){
			
			String[] movie_genre = eval_lists.get(i).getMovie_genre().split(", ");
			
			for (int j = 0; j < movie_genre.length; j++) {

				
				if (genre_map.containsKey(movie_genre[j])) {
					int count = genre_overlap_count.get(movie_genre[j]);
					count++;
					int point = genre_map.get(movie_genre[j]) + eval_lists.get(i).getEval_point() * 10;
					genre_map.put(movie_genre[j], point);
					genre_overlap_count.put(movie_genre[j], count);

				} else {
					genre_map.put(movie_genre[j], eval_lists.get(i).getEval_point() * 10);
					genre_overlap_count.put(movie_genre[j], 1);
					
				}
				
			}
		}
	
		// 중복된 횟수를 나눠 평균 값 구하기
        Iterator<String> count_keys = genre_overlap_count.keySet().iterator();
        while( count_keys.hasNext() ){
            String key = count_keys.next();
            Integer value = genre_map.get(key);
            Integer count = genre_overlap_count.get(key);
            
            genre_map.put(key, value/count);
        }
        
        

        
		return genre_map;
	}
	
	
	// 평가 국가 계산
	public Map<String, Integer> eval_nation_cal(ArrayList<MyEvalMovieDto> eval_lists){
		Map<String, Integer> nation_map = new HashMap<String, Integer>();
		Map<String, Integer> nation_overlap_count = new HashMap<String, Integer>();
		
		// hashmap DATA 생성
		for(int i = 0; i < eval_lists.size(); i++){
			
			String[] movie_nation = eval_lists.get(i).getMovie_nation().split(" , ");
			
			for (int j = 0; j < movie_nation.length; j++) {
				
				
				if (nation_map.containsKey(movie_nation[j])) {
					int count = nation_overlap_count.get(movie_nation[j]);
					count++;
					int point = nation_map.get(movie_nation[j]) + eval_lists.get(i).getEval_point() * 10;
					nation_map.put(movie_nation[j], point);
					nation_overlap_count.put(movie_nation[j], count);
					
				} else {
					nation_map.put(movie_nation[j], eval_lists.get(i).getEval_point() * 10);
					nation_overlap_count.put(movie_nation[j], 1);
					
				}
				
			}
		}
		
		// 중복된 횟수를 나눠 평균 값 구하기
		Iterator<String> count_keys = nation_overlap_count.keySet().iterator();
		while( count_keys.hasNext() ){
			String key = count_keys.next();
			Integer value = nation_map.get(key);
			Integer count = nation_overlap_count.get(key);
			
			nation_map.put(key, value/count);
		}
	
		return nation_map;
	}
	
	
	// 평가 배우 계산
	public Map<String, Integer> eval_actor_cal(ArrayList<MyEvalMovieDto> eval_lists){
		Map<String, Integer> actor_map = new HashMap<String, Integer>();
		Map<String, Integer> actor_overlap_count = new HashMap<String, Integer>();
	
		
		// hashmap DATA 생성
		for(int i = 0; i < eval_lists.size(); i++){
			String text = eval_lists.get(i).getMovie_actor();
			
			// 괄호 안의 내용 지우기
			Pattern PATTERN_BRACKET = Pattern.compile("\\([^\\(\\)]+\\)");
			Matcher matcher = PATTERN_BRACKET.matcher(text);
			    
			String movie_actor = text;
			String removeTextArea = new String();
			
			while(matcher.find()) {
			    int startIndex = matcher.start();
			    int endIndex = matcher.end();
			    
			    removeTextArea = movie_actor.substring(startIndex, endIndex);
			    movie_actor = movie_actor.replace(removeTextArea, "");
			    matcher = PATTERN_BRACKET.matcher(movie_actor);
			}


						
			String[] movie_actors = movie_actor.split(", ");
			
			
			for (int j = 0; j < movie_actors.length; j++) {

				if (actor_map.containsKey(movie_actors[j])) {
					int count = actor_overlap_count.get(movie_actors[j]);
					count++;
					int point = actor_map.get(movie_actors[j]) + eval_lists.get(i).getEval_point() * 10;
					actor_map.put(movie_actors[j], point);
					actor_overlap_count.put(movie_actors[j], count);
					
				} else {
					actor_map.put(movie_actors[j], eval_lists.get(i).getEval_point() * 10);
					actor_overlap_count.put(movie_actors[j], 1);
					
				}
				
			}
		}
		
		// 중복된 횟수를 나눠 평균 값 구하기
		Iterator<String> count_keys = actor_overlap_count.keySet().iterator();
		while( count_keys.hasNext() ){
			String key = count_keys.next();
			Integer value = actor_map.get(key);
			Integer count = actor_overlap_count.get(key);
			
			actor_map.put(key, value/count);
		}
		
		
		return actor_map;
	}

////////////////////////////// 장르벡터 //////////////////////////
	// 장르 전체 Map 만들기
	public Map<String, Integer> total_genre(ArrayList<MovieDto> total_movie_list) {
		Map<String, Integer> total_genre_map = new HashMap<String, Integer>();
		
		// hashmap DATA 생성
		for(int i = 0; i < total_movie_list.size(); i++){
			
			if(total_movie_list.get(i).getMovie_genre() != null) {
				String[] movie_genre = total_movie_list.get(i).getMovie_genre().split(", ");
				
				for (int j = 0; j < movie_genre.length; j++) {
					total_genre_map.put(movie_genre[j], 0);
				}
			}
		}
		return total_genre_map;
	}


	// 각 영화 정보에 대한 장르 벡터 만들기
	public Map<String, Integer> genre_info_map(MovieDto movie_info, Map<String, Integer> total_genre_map) {

		if(movie_info.getMovie_genre() != null) {
			
			String[] movie_genre = movie_info.getMovie_genre().split(", ");
			
			for (int i = 0; i < movie_genre.length; i++) {
				if (total_genre_map.containsKey(movie_genre[i])) {
					total_genre_map.put(movie_genre[i], 1);
	
				}
			}
		}
		
		return total_genre_map;
	}


	// 나의 평가 벡터 만들기
	public Map<String, Integer> info_map_id(Map<String, Integer> map,
			Map<String, Integer> info_by_id) {

		info_by_id.putAll(map);
		
		
		return info_by_id;
	}

	
	
////////////////////////////// 국가벡터 //////////////////////////
	// 국가 전체 Map 만들기
	public Map<String, Integer> total_nation(ArrayList<MovieDto> total_movie_list) {
		Map<String, Integer> total_nation_map = new HashMap<String, Integer>();
		
		// hashmap DATA 생성
		for(int i = 0; i < total_movie_list.size(); i++){
			
			if(total_movie_list.get(i).getMovie_nation() != null) {
				String[] movie_nation = total_movie_list.get(i).getMovie_nation().split(" , ");
				
				for (int j = 0; j < movie_nation.length; j++) {
					total_nation_map.put(movie_nation[j], 0);
				}
			}
		}
		return total_nation_map;
	}


	// 각 영화 정보에 대한 국가 벡터 만들기
	public Map<String, Integer> nation_info_map(MovieDto movie_info, Map<String, Integer> total_nation_map) {

		if(movie_info.getMovie_nation() != null) {
			
			String[] movie_nation = movie_info.getMovie_nation().split(" , ");
			
			for (int i = 0; i < movie_nation.length; i++) {
				if (total_nation_map.containsKey(movie_nation[i])) {
					total_nation_map.put(movie_nation[i], 1);
	
				}
			}
		}
		
		return total_nation_map;
	}
	
	
////////////////////////////// 배우벡터 //////////////////////////
	// 배우 전체 Map 만들기
	public Map<String, Integer> total_actor(ArrayList<MovieDto> total_movie_list) {
		Map<String, Integer> total_actor_map = new HashMap<String, Integer>();
		
		// hashmap DATA 생성
		for(int i = 0; i < total_movie_list.size(); i++){
			if(total_movie_list.get(i).getMovie_actor() != null) {

				String text = total_movie_list.get(i).getMovie_actor();
			
				// 괄호 안의 내용 지우기
				Pattern PATTERN_BRACKET = Pattern.compile("\\([^\\(\\)]+\\)");
				Matcher matcher = PATTERN_BRACKET.matcher(text);
				    
				String movie_actor = text;
				String removeTextArea = new String();
				
				while(matcher.find()) {
				    int startIndex = matcher.start();
				    int endIndex = matcher.end();
				    
				    removeTextArea = movie_actor.substring(startIndex, endIndex);
				    movie_actor = movie_actor.replace(removeTextArea, "");
				    matcher = PATTERN_BRACKET.matcher(movie_actor);
				}
	
				String[] movie_actors = movie_actor.split(", ");
				
				for (int j = 0; j < movie_actors.length; j++) {
					total_actor_map.put(movie_actors[j], 0);
				}
			}
		}
		return total_actor_map;
	}
	
	
	// 각 영화 정보에 대한 배우 벡터 만들기
	public Map<String, Integer> actor_info_map(MovieDto movie_info, Map<String, Integer> total_actor_map) {
		
		if(movie_info.getMovie_actor() != null) {
			String text = movie_info.getMovie_actor();
			
			// 괄호 안의 내용 지우기
			Pattern PATTERN_BRACKET = Pattern.compile("\\([^\\(\\)]+\\)");
			Matcher matcher = PATTERN_BRACKET.matcher(text);
			    
			String movie_actor = text;
			String removeTextArea = new String();
			
			while(matcher.find()) {
			    int startIndex = matcher.start();
			    int endIndex = matcher.end();
			    
			    removeTextArea = movie_actor.substring(startIndex, endIndex);
			    movie_actor = movie_actor.replace(removeTextArea, "");
			    matcher = PATTERN_BRACKET.matcher(movie_actor);
			}
			
			String[] movie_actors = movie_actor.split(", ");
			
			for (int i = 0; i < movie_actors.length; i++) {
				if (total_actor_map.containsKey(movie_actors[i])) {
					total_actor_map.put(movie_actors[i], 1);
				}
			}
		}
		
		return total_actor_map;
	}


}
