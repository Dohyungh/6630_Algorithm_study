package BOJ._3985_롤케이크;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int L = sc.nextInt();
		int[] cakeL = new int[L+1]; // cake 할당 배열
		int N = sc.nextInt();
		int[] countN = new int[N+1]; // 몇개 가져갔는지 카운팅 배열
		
		int max = 0;
		int answer1 = 0;
		for (int n = 1; n<N+1; n++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			if (end-start > max) { // 이제 숨쉬듯이 하는 max 값 찾기
				max = end-start;
				answer1 = n;
			}
			for (int i = start; i<=end; i++) { // 아직 할당 안했으면 할당
				if (cakeL[i]==0) cakeL[i] = n;
			}
		}
		
		for (int l = 1; l<cakeL.length; l++) { // 카운팅 배열 생성
			countN[cakeL[l]]++;	
		}
		
		max = 0;
		int answer2 = 0;
		for (int n = 1; n<countN.length; n++) {
			if (countN[n]>max) { // max 값 찾기
				max = countN[n];
				answer2 = n;
			}
			
		}
		System.out.println(answer1);
		System.out.println(answer2);
		
	}

}
