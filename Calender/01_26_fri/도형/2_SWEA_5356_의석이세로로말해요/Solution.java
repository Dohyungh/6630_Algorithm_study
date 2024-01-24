package SWEA._5356_의석이의세로로말해요;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) { // 예시랑 실제랑 입력 다른 것 주의
	
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc =1; tc<=T;tc++) {
			String q1 = sc.next();
			String q2 = sc.next();
			String q3 = sc.next();
			String q4 = sc.next();
			String q5 = sc.next();
			
			Queue<String> queue1 = new LinkedList<>(Arrays.asList(q1.split(""))); //String 한 글자씩 나누기 " " 공백으로 나누기 "," 쉼표로 나누기
			Queue<String> queue2 = new LinkedList<>(List.of(q2.split("")));
			Queue<String> queue3 = new LinkedList<>(Arrays.asList(q3.split("")));
			Queue<String> queue4 = new LinkedList<>(List.of(q4.split("")));
			Queue<String> queue5 = new LinkedList<>(Arrays.asList(q5.split("")));
			
			// String >> (split) >> String[] >> (Arrays.asList)>> "List와 유사한 객체"로 변환하는데쓰는 메서드 2개 Arrays.asList 와 List.of
			//LinkedList 조회는 느리지만, queue로 쓰면 
			
			
			System.out.printf("#%d ",tc);
			int cnt = 0; // 빈 queue 확인
			while (cnt!=5) { // 반복
				switch (1) {
				case 1:
					if (!queue1.isEmpty()) {
						System.out.printf("%s",queue1.poll());
						if (queue1.isEmpty()) cnt++;
					} 
				case 2:
					if (!queue2.isEmpty()) {
						System.out.printf("%s",queue2.poll());
						if (queue2.isEmpty()) cnt++;
					} 
					
				case 3:
					if (!queue3.isEmpty()) {
						System.out.printf("%s",queue3.poll());
						if (queue3.isEmpty()) cnt++;
					}
					
				case 4:
					if (!queue4.isEmpty()) {
						System.out.printf("%s",queue4.poll());
						if (queue4.isEmpty()) cnt++;
					}
	
				case 5:
					if (!queue5.isEmpty()) {
						System.out.printf("%s",queue5.poll());
						if (queue5.isEmpty()) cnt++;
					}
					
				}
			}
		System.out.println();	
		}
		// 좋은 링크 https://hello-judy-world.tistory.com/180, https://hello-judy-world.tistory.com/178
		
	}
	

	
}
