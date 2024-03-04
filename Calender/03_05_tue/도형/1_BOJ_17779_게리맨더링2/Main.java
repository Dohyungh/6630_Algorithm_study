package BOJ._17779_게리맨더링2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// N^2 이상일 거 같은데 시간이 1초?
		// 무지성 br
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int[][] prefixSum = new int[N][N]; // padding 못생겨써
		
		// 생성
		prefixSum[0][0] = map[0][0];
		for (int i = 1; i <N; i++) {
			prefixSum[0][i] = prefixSum[0][i-1] + map[0][i];
			prefixSum[i][0] = prefixSum[i-1][0] + map[i][0];
			
		}
		
		for (int i = 1; i <N; i++) {
			for (int j = 1; j<N; j++) {
				prefixSum[i][j] = prefixSum[i][j-1] + prefixSum[i-1][j] - prefixSum[i-1][j-1] + map[i][j];
			}
		}
		
		
		
		
		
		
	}
	
	// 빠른 포기
	public static int getSum(int x, int y, int d1, int d2, int[][] prefixSum, int sum) {
//		if () {
//			
//		}
		
	
		return 0;
		
	}

}
