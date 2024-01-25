package SWEA._4047_영준이의카드카운팅;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		System.out.println(Integer.parseInt("01")); // 1
		int T = sc.nextInt();
		for (int tc = 1; tc<=T;tc++) {
			// 각 모양마다 카운터 배열 생성
			int[] S = new int[14];
			int[] D = new int[14];
			int[] H = new int[14];
			int[] C = new int[14];
			
			//입력받기
			String str = sc.next();
			for (int i = 0; i<str.length()/3;i++) {//0,3,6,9 -> 0,1,2,3 
				if (str.substring(i*3, i*3+1).equals("S")) {//다시 0,1,2,3 -> 0,3,6,9 substring은 마지막 인덱스 포함안함!
						int idx = Integer.parseInt(str.substring(i*3+1, i*3+3));
						S[idx]++;
				} else if (str.substring(i*3, i*3+1).equals("D")) {
					int idx = Integer.parseInt(str.substring(i*3+1, i*3+3));
					D[idx]++;
				} else if (str.substring(i*3, i*3+1).equals("H")) {
					int idx = Integer.parseInt(str.substring(i*3+1, i*3+3));
					H[idx]++;
				} else if (str.substring(i*3, i*3+1).equals("C")) {
					int idx = Integer.parseInt(str.substring(i*3+1, i*3+3));
					C[idx]++;
				}
					
			}
			//max 13에서 빼자
			int S_ans = 13;
			int D_ans = 13;
			int H_ans = 13;
			int C_ans = 13;
			
			//마지막 출력에 분기가 있으므로 boolean 하나 생성
			boolean isError = false;
			for (int i = 0; i<14;i++) {
				if (S[i]==2) {
					isError = !isError;
				}
				S_ans -=S[i];
			}
			for (int i = 0; i<14;i++) {
				if (D[i]==2) {
					isError = !isError;
				}
				D_ans -=D[i];
			}
			for (int i = 0; i<14;i++) {
				if (H[i]==2) {
					isError = !isError;
				}
				H_ans -=H[i];
			}
			for (int i = 0; i<14;i++) {
				if (C[i]==2) {
					isError = !isError;
				}
				C_ans -=C[i];
			}

			if (isError) {
				System.out.printf("#%d ERROR%n", tc);
			} else {
				System.out.printf("#%d %d %d %d %d%n",tc,S_ans,D_ans,H_ans,C_ans);
			}
		}
	}
	
}
