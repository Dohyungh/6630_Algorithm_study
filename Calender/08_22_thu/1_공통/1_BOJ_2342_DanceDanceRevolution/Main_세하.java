package boj_2342_DanceDanceRevolution;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] strArr = br.readLine().split(" ");

		int len = strArr.length;

		int[] dirArr = new int[len - 1];
		int[][][] dp = new int[len][5][5];

		for (int i = 0; i < len - 1; i++) {
			dirArr[i] = Integer.parseInt(strArr[i]);
		}

		int INF = 987654321;

		// DP 테이블 INF 값으로 초기화
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < 5; j++) {
				for (int k = 0; k < 5; k++) {
					dp[i][j][k] = INF;
				}
			}
		}

		// 처음 위치에서 값은 0
		dp[0][0][0] = 0;

		for (int i = 0; i < len - 1; i++) {
			int next = dirArr[i]; // 다음 위치
			// 모든 왼발과 오른발 위치에 대해서 dp 실행
			for (int left = 0; left < 5; left++) {
				for (int right = 0; right < 5; right++) {
					int currForce = dp[i][left][right]; // 현재 위치에서 힘의 최솟값

					if (currForce == INF)
						continue;

					// left를 움직여 다음 위치로 이동하는 경우, (기존 dp 값)과 (현재 힘 + left를 다음 위치로 이동할 때 드는 힘)을 비교
					dp[i + 1][next][right] = Math.min(dp[i + 1][next][right], currForce + force(left, next));
					// 마찬가지로 right를 움직여 다음 위치로 이동하는 경우, (기존 dp 값)과 (현재 힘 + right를 다음 위치로 이동할 때 드는
					// 힘)을 비교
					dp[i + 1][left][next] = Math.min(dp[i + 1][left][next], currForce + force(right, next));
				}
			}
		}

		int ans = INF;

		// 최종 위치에서 모든 경우의 수에 대한 최솟값 탐색
		for (int left = 0; left < 5; left++) {
			for (int right = 0; right < 5; right++) {
				ans = Math.min(ans, dp[len - 1][left][right]);
			}
		}
		System.out.println(ans);
	}

	public static int force(int curr, int next) {
		if (curr == next) { // 현재 위치와 다음 위치가 같으면 드는 힘은 1
			return 1;
		} else if (curr == 0) { // 현재 위치가 0이면 항상 드는 힘은 2
			return 2;
		} else if (Math.abs(curr - next) == 2) { // 현재 위치와 다음 위치의 차가 2이면 두 방향은 서로 반대이므로 드는 힘은 4
			return 4;
		} else { // 인접한 경우 드는 힘은 3
			return 3;
		}
	}
}