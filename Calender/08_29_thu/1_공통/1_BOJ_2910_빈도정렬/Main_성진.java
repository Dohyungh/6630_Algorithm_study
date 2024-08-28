package boj_2910_빈도정렬;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int C = sc.nextInt();

		// 숫자의 등장 횟수를 저장하는 맵 cntMap
		Map<Integer, Integer> cntMap = new HashMap<Integer, Integer>();
		// 입력된 숫자들의 리스트 numList
		List<Integer> numList = new ArrayList<Integer>();

		// 초기 입력된 순서를 기억하기 위한 리스트 tempList 
		List<Integer> tempList = new ArrayList<Integer>();

		for (int i = 0; i < N; i++) {
			int num = sc.nextInt();
			numList.add(num);
			tempList.add(num);

			// 해당 숫자가 처음 등장하는 경우 맵에 추가하고, 
			if (!cntMap.containsKey(num)) {
				cntMap.put(num, 1);
			}
			// 이미 존재하는 경우에는 등장 횟수를 증가시킴
			else {
				int cnt = cntMap.get(num);
				cntMap.put(num, cnt + 1);
			}

		}

		// numList를 등장 횟수에 따라 정렬
		Collections.sort(numList, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// 등장 횟수가 같다면 초기 입력 순서(tempList)에 따라 정렬
				if (cntMap.get(o2) == cntMap.get(o1)) {
					return tempList.indexOf(o2) - tempList.indexOf(o1);
				}
				// 다른 경우 내림차순으로 정렬
				return Integer.compare(cntMap.get(o2), cntMap.get(o1));
			}
		});

		for (int i = 0; i < numList.size(); i++) {
			System.out.print(numList.get(i) + " ");
		}

	}

}
