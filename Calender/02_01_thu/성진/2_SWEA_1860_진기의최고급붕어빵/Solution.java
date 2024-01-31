package _1860;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	
	public static void main(String args[]) throws Exception {
		
		System.setIn(new FileInputStream("SWEA/src/_1860/input.txt"));


		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		
		for(int test_case = 1; test_case <= T; test_case++) {
			
			// N: 손님의 수, M: 붕어빵 K개를 만들기 위해 필요한 시간(초), K: M초의 시간을 들였을 때 만들수 있는 붕어빵의 개수
			int N = sc.nextInt();
			int M = sc.nextInt();
			int K = sc.nextInt();
			
			// 손님이 도착하는 시간을 저장할 리스트 생성
			List<Integer> time_list = new ArrayList<>(); 
			
			for (int i=0; i<N; i++) {
				time_list.add(sc.nextInt());
			}
		
			// 손님이 도착할 시간을 오름차순으로 정렬
            Collections.sort(time_list);
			
            // 붕어빵의 초기 재고를 계산(첫번째 손님이 오는 시간을 M으로 나눈 몫 * K)
			int stock = (time_list.get(0) / M) * K;
			
			// answer는 Possible이 default가 되도록 설
			String answer = "Possible";
			
			// 첫 손님에게 붕어빵을 판매
			stock -= 1;
			
			// 두번째 손님이 있을 경우 아래 for문을 실행
			int idx = 1;
			while (idx < N) {
				// 재고가 0보다 작아지는 순간 while문 탈출
				if (stock < 0) {
					break;
				}
				// 현재 손님과 바로 전에 온 손님 사이에 만들 수 있는 붕어빵의 수를 계산해 재고에 더하기
				stock += (((time_list.get(idx) / M) - (time_list.get(idx-1) / M))) * K;
				// 현재 손님에게 붕어빵을 판매
				stock -= 1;
				idx++;
			}
			
			// 재고가 0보다 적었다면 Impossible 출력
			if (stock<0) {
				System.out.printf("#%d Impossible\n", test_case);
			}
			
			// 재고가 0과 같거나 컸다면 Possible 출력
			else {
				System.out.printf("#%d %s\n", test_case, answer);
			}
		}	
		sc.close();	
		
		
	}
}