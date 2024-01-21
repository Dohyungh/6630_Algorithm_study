package SWEA._1940_가랏RC카;

import java.util.Scanner;

public class Solution1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc<=T;tc++) {
			int n = sc.nextInt();
			int num = 1;
			int v = 0;
			int ans = 0;
			while (num<=n) {
				int order = sc.nextInt();
				if (order == 0) {
					ans +=v;
					num++;
				} else if (order == 1) {
					int plus = sc.nextInt();
					v +=plus;
					ans +=v;
					num++;
				} else if (order == 2) {
					int minus = sc.nextInt();
					if (minus >= v) {
						v = 0;
					} else {
						v -=minus;
						ans+=v;
					} num++;
				}
				
			}
			
			System.out.printf("#%d %d%n", tc, ans);
		}	
	}	
}
// 특이 사항 없음