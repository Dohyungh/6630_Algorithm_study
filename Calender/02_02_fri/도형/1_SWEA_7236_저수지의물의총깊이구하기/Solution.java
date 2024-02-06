package SWEA._7236_저수지의물의총깊이구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 아니 이거 // WWW 7나오는데 통과함;;
			// WGW
			// WWW

public class Solution {
	public static void main(String[] args) throws IOException {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(bf.readLine()); // 변의 크기
			int[][] arr = new int[N][N]; // 원배열 생성
			
			for (int n = 0; n<N; n++) {
				StringTokenizer st = new StringTokenizer(bf.readLine()); // 아무것도 안넣으면 그냥 띄어쓰기로 다 나눠줌.
				for (int i = 0; i<N; i++) {
					String token = st.nextToken(); // nextToken이 무조건 String이라 char 가 안됨.
					if (token.equals("G")) { // 따라서 equals를 써야함
						arr[n][i] = 0;
					} else {
						arr[n][i] = 1;
					}
				}
			}
		
			int[][] prefixSum = new int[N+1][N+1]; // padding O
			
			for (int i = 1; i<N+1; i++) { // 생성
				for (int j = 1; j<N+1; j++) {
					prefixSum[i][j] = arr[i-1][j-1] + prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1];
				}
			}
			
			int ans=0;
			int tmp; // tmp 변수 필요함. GGG/GWG/GGG 의 경우 1이나와야해서. 접근해서 얻은 값에서 다짜고짜 -1 하면 안됨.
			for (int i = 1; i<=N-2; i++) {
				for (int j = 1; j<=N-2; j++) { // 접근
					tmp = prefixSum[i+2][j+2] - prefixSum[i-1][j+2] - prefixSum[i+2][j-1] + prefixSum[i-1][j-1];
					if (tmp == 1) { // 1이면 넘어가
						
					} else { //아니면 가운데 w 1빼
						tmp--;
					}
					ans = Math.max(ans, tmp);
				}
			}
			
			System.out.printf("#%d %d%n", tc, ans);
			
			
		
		}
		
		
	}

}
