package BOJ._11729_하노이탑이동순서;

import java.util.Scanner;

public class Main {
	static int cnt = 0;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
//		printHanoi(n,1,3);
//		System.out.println(cnt); // 이러면 맨 뒤에 출력되네
		
		
//		StringBuilder를 쓰면 됨
//		근데 점화식을 써보자
		
//		X(n) = 2*X(n-1) + 1
		int x = 0;
		for (int i = 0; i<n; i++) {
			x = 2*x+1;
		}
		
		
		System.out.println(x);
		printHanoi(n,1,3);

	}
	
	public static void printHanoi(int n, int start, int end) {
		if (n == 1) {
			System.out.println(start + " " + end);
			return;
		}
		
		int middle = 6 - start - end;
		
		printHanoi(n-1, start, middle);
		System.out.println(start + " " + end);
		cnt++;
		printHanoi(n-1, middle, end);
		
	}

}

// 시간초과 선넘네 진짜