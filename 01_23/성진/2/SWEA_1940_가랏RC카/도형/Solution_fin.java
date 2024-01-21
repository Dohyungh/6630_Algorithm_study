package SWEA._1940_가랏RC카;

import java.util.Scanner;

public class Solution_fin {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc<=T;tc++) {
			int n = sc.nextInt(); // 명령 수
			int num = 1; // 명령 수 채웠는지 카운트
			int v = 0; // 속도
			int ans = 0; // 답
			// 입력을 한번에 다 받아놓는 게 안됨. 0 때문에
			while (num<=n) {
				int order = sc.nextInt();
				if (order == 0) { //order 가 0일 때
					ans +=v;
				} else if (order == 1) { //order 가 1일 때
					int plus = sc.nextInt();
					v +=plus;
					ans +=v;
				} else if (order == 2) { //order 가 2일 때
					int minus = sc.nextInt();
					if (minus >= v) {
						v = 0;
					} else {
						v -=minus;
						ans+=v;
					} 
//					이게 쥐꼬리만큼 더 깔끔할듯
//						v-=minus;
//						if (v<0) {
//							v=0;
//						}
//						ans+=v;
					
//					그럼 뭐 v = Math.max(0, v-minus); 가 젤 깔끔할 듯
					
				}
				num++; // 한번에 쓰자
			}
			
			System.out.printf("#%d %d%n", tc, ans);
		}	
	}	
}