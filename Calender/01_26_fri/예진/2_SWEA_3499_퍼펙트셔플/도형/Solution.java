package SWEA._3499_퍼펙트셔플;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for (int tc = 1; tc<=T; tc++) {
			
			Queue<String> queue1 = new LinkedList<>();
			Queue<String> queue2 = new LinkedList<>();
			
			
			int N = sc.nextInt();
			int k;
			
			if (N%2==0) {
				k = N/2-1; //index 기준
				
			} else k = N/2; // index 기준
			
			// q1, q2 입력
			for (int i=0; i<=k; i++) {
				queue1.add(sc.next());
			}
			for (int i=k+1; i<N;i++) {
				queue2.add(sc.next());
			}
			
			
			System.out.printf("#%d ",tc);
			while (!queue1.isEmpty()||!queue2.isEmpty()) { // 둘다 텅 빈게 아니라면
				System.out.printf("%s ",queue1.poll());
				if (!queue2.isEmpty()) {
					System.out.printf("%s ",queue2.poll()); // 무조건 q2 가 더 짧거나 같음.
				}
			}
			System.out.printf("%n");
			
		}
		
	}
}
