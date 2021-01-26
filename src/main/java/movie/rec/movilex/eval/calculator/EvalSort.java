package movie.rec.movilex.eval.calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import movie.rec.movilex.dto.CosSimDto;
import movie.rec.movilex.dto.MyEvalListDto;

public class EvalSort {
	public ArrayList<MyEvalListDto> eval_sort(Map<String, Integer> map){
        List<String> keySetList = new ArrayList<>(map.keySet());
        // 내림차순 //
        Collections.sort(keySetList, new Comparator<String>() {
        	public int compare(String o1, String o2) {
        		
        		return map.get(o2).compareTo(map.get(o1));
        	};
        	
		});
        
        
//      System.out.println("------------sort 후 -------------");
        
        Iterator it = keySetList.iterator();
        ArrayList<MyEvalListDto> sorted_list = new ArrayList<MyEvalListDto>();

        while(it.hasNext()) {
        	String key = (String) it.next();
        	Integer value = map.get(key);
        	MyEvalListDto evalListDto = new MyEvalListDto();
        	
        	evalListDto.setKey(key);
        	evalListDto.setValue(value);
        	
        	sorted_list.add(evalListDto);
        	
//        	System.out.println("키 : " + key + ", 값 : " + value);

        }
		return sorted_list;
	}
	
	public ArrayList<Integer> sim_sort(Map<Integer, Double> map){
		List<Integer> keySetList = new ArrayList<>(map.keySet());
		// 내림차순 //
		Collections.sort(keySetList, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				
				return map.get(o2).compareTo(map.get(o1));
			};
			
		});
		
		
//      System.out.println("------------sort 후 -------------");
		
		Iterator<Integer> it = keySetList.iterator();
		ArrayList<Integer> sorted_code = new ArrayList<Integer>();
		// 유사도 높은 순 50개 까지
		for (int i = 0; i < 50; i++) {
			Integer key = it.next();
			Double value = map.get(key);
			if(value != 0) {
				sorted_code.add(key);
				
			}
		}

		return sorted_code;
	}
	
	public ArrayList<MyEvalListDto> input_list(Map<String, Integer> map){
        List<String> keySetList = new ArrayList<>(map.keySet());

        Iterator it = keySetList.iterator();
        ArrayList<MyEvalListDto> entered_list = new ArrayList<MyEvalListDto>();

        while(it.hasNext()) {
        	String key = (String) it.next();
        	Integer value = map.get(key);
        	MyEvalListDto evalListDto = new MyEvalListDto();
        	
        	evalListDto.setKey(key);
        	evalListDto.setValue(value);
        	
        	entered_list.add(evalListDto);
        	
//        	System.out.println("키 : " + key + ", 값 : " + value);

        }
        
//        System.out.println();
		return entered_list;
				
	}

}
