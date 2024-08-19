package BOJ._2342_DanceDanceRevolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	// 방문체크 겸용 초기화 상수
	static int inf = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 명령의 개수 0 포함
		int N = st.countTokens();
		
		// dp table
		int[][][] dp = new int[N][5][5];
		
		// dp 초기화
		for (int i = 0; i< N; i++) {
			for (int j = 0; j < 5; j++) {
				Arrays.fill(dp[i][j], inf);
			}
		}
		
		
		// input
		int[] input = new int[N];
		
		for (int i = 0; i< N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		// 여기까지 입력
		
		
		// 정답 초기화
		int answer = inf;
		
		// 시작
		dp[0][0][0] = 0;
		
		// 매 input 에 대해
		for (int i = 1; i< N; i++) {
			
			int ipt = input[i-1];
			
			// 왼발이 움직일 때와 오른발이 움직일 때를 계산
			// 결과적으로 input에 발이 닿아 있어야 하기 때문에 마지막 모양 기준으로 dp를 설정함
			// 예를 들어, input 이 4 가 들어왔다면
			// 결국 모양은 
			// 0 4 / 1 4 / 2 4 / 3 4 
			// 혹은      
			// 4 0 / 4 1 / 4 2 / 4 3
			// 가 되어야 함
			// 각 목적지에 대해 다시 for 문을 돌림
			
			// 0 4 가 결국 된다면
			// 0 0 에서, 0 1 에서, 0 2 에서, 0 3 에서, 0 4 에서 가는 것중에 가장 저렴한 비용을 저장함
			
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					
					// 왼발
					if (dp[i-1][k][j] != inf) {
						dp[i][ipt][j] = Math.min(dp[i][ipt][j], dp[i-1][k][j] + getCost(k, ipt));
					}
					
					// 오른발
					if (dp[i-1][j][k] != inf) {
						dp[i][j][ipt] = Math.min(dp[i][j][ipt], dp[i-1][j][k] + getCost(k, ipt));
					}
				}
			}
		}
		
		// 마지막에 최솟값 구하기
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				answer = Math.min(answer, dp[N-1][i][j]);
			}
		}
		System.out.println(answer);
		
	}

	// 위치에 따른 cost
	static int getCost(int from, int to) {
		if (from == 0) return 2;
		if (from == to) return 1;
		if (Math.abs(to - from) == 2) return 4;
		return 3;
	}

}
