package movie.rec.movilex.eval.calculator;

import java.util.ArrayList;

import movie.rec.movilex.dto.MyEvalListDto;

public class CosineSimilaty {

	public double get_cosSimilaty(ArrayList<MyEvalListDto> list_by_code,
			ArrayList<MyEvalListDto> list_by_id) {

		double a_dot_b = 0;
		double pow_a = 0;
		double pow_b = 0;
		for (int i = 0; i < list_by_id.size(); i++) {
			// A dot B
			a_dot_b += list_by_code.get(i).getValue() * list_by_id.get(i).getValue();
			
			// A벡터 제곱 합
			pow_a += Math.pow(list_by_code.get(i).getValue(), 2);
			
			// B벡터 제곱 합
			pow_b += Math.pow(list_by_id.get(i).getValue(), 2);
			
			
		}
/*		System.out.println();
		System.out.println("a dot b = " + a_dot_b);
		System.out.println("A벡터 제곱 합 = " + pow_a);
		System.out.println("B벡터 제곱 합 = " + pow_b);
*/		
		double cos_similaty = a_dot_b / ((Math.sqrt(pow_a) * (Math.sqrt(pow_b)) + (Math.pow(10, -8))));
		
		return cos_similaty;
	}

}
