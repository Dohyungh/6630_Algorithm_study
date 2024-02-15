package BOJ._8320_직사각형을만드는방법;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		//하품이 나온다
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int cnt = 0;
		for (int j = 1; j<=N; j++) { // N개를 다 안써도 됨.
			for (int i = 1; i<=Math.sqrt(j); i++) { // 루트까지만
				if (j%i==0) cnt++; 
			}
		}
		
		System.out.println(cnt);
		
		sc.close(); // 문단속
	}

}
