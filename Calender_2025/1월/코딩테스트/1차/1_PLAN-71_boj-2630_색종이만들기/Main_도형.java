package BOJ._2630_색종이만들기;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int wCnt = 0;
	static int bCnt=  0;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		int[][] arr = new int[N][N];
		int[][] pSum = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		pSum[0][0] = arr[0][0];
		
		for (int i = 1; i < N; i++) {
			pSum[0][i] = pSum[0][i-1] + arr[0][i];
			pSum[i][0] = pSum[i-1][0] + arr[i][0];
		}
		
		for (int i = 1; i < N; i++) {
			for (int j = 1; j < N; j++) {
				pSum[i][j] = pSum[i-1][j] + pSum[i][j-1] - pSum[i-1][j-1] + arr[i][j];
			}
		}
		getCounts(pSum, arr, 0, 0, N);
		
		System.out.println(wCnt);
		System.out.println(bCnt);
		
		
	}
	
	static void getCounts (int[][] pSum, int[][] arr,  int sRow, int sCol, int len) {
		if (len == 1) {
			if (arr[sRow][sCol] == 0) wCnt++;
			else bCnt++;
			return;
		}
		
		int eRow = sRow + len -1;
		int eCol = sCol + len -1;
		
		int up = sRow -1 == -1? 0 : pSum[sRow -1][eCol];
		int left= sCol -1 == -1? 0 : pSum[eRow][sCol-1];
		int last= sRow -1 == -1 || sCol-1 == -1 ? 0 : pSum[sRow-1][sCol-1];
		
		int sum = pSum[eRow][eCol] - up - left + last;
		
		if (sum == len*len) {
			bCnt++;
			return;
		}
		else if (sum == 0) {
			wCnt++;
			return;
		}
		else {
			getCounts(pSum, arr, sRow, sCol, len/2);
			getCounts(pSum, arr, sRow+len/2, sCol, len/2);
			getCounts(pSum, arr, sRow, sCol+len/2, len/2);
			getCounts(pSum, arr, sRow+len/2, sCol+len/2, len/2);
		}
	}

}
