package BOJ._2910_빈도정렬;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// 몇개인지
		int N = sc.nextInt();
		
		// 최대 수가 뭔지 // 안써버림
		int C = sc.nextInt();
		
		// key는 어떤 수가
		// value는 배열인데, { 몇번, 최초 등장 시점 }
		Map<Integer, int[]> map = new HashMap<Integer,int[]>();
		
		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			
			// map 생성
			if (map.containsKey(num)) {
				map.put(num, new int[] {map.get(num)[0]+1, map.get(num)[1]});
			} else {
				map.put(num, new int[] {1, i});
			}
		}
		
		// 키만 리스트로 따로 빼서
		List<Integer> keySet = new ArrayList<>(map.keySet());
		
		// value의 특성에 따라 정렬
		keySet.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer int1, Integer int2) {
				
				// 등장시점 오름차순
				if (map.get(int1)[0] == map.get(int2)[0]) {
					return map.get(int1)[1] - map.get(int2)[1];
				};
				
				// 개수 내림차순
				return map.get(int2)[0] - map.get(int1)[0];
			}
		});
		
		// 정렬된 keySet에서 key를 조건에 맞게 출력해주면 끝
		for (int key : keySet) {
			for (int i = 0; i <map.get(key)[0]; i++) {
				System.out.print(key + " ");
			}
		}
		
	}

}
