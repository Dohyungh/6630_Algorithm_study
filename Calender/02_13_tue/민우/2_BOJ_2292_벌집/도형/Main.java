package BOJ._2292_벌집;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// 층수 출력하라는 거네
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		if (N ==1) {
			System.out.println(1); 
			return;
		}
		
		//1 + 6 + 12 + 18 +....
		
		int i = 1;
		int cnt = 1;
		while (i < N) {
			i = i + cnt*6;
			cnt++;
		}
		
		System.out.println(cnt);
		sc.close();
	}

}
