/*
 * [문제풀이]
 * 계단수의 길이를 하나씩 늘려가며 경우의 수를 확인한다고 가정했을 때,
 * (현재 마지막 숫자, 현재 숫자의 순서, 지금까지 사용한 숫자)의 상태를 기준으로
 * 해당 지점부터 파생될 수 있는 경우의 수를 dp에 저장했다. (0~9까지 모두 사용된 경우들만)
 * 숫자의 사용여부 즉, 방문체크는 비트마스킹을 사용했다.
 * 1을 사용했다면 0000000010, 모두 사용했다면 1111111111 일것.
 *
 * 1000000000
 * 1100000000
 * 1110000000
 * 1111000000
 * 1111100000
 * 1111110000
 * 1111111000
 * 1111111100
 * 1111111110
 * 1111111111
 * 0100000000
 * .
 * .
 * .
 * 
 * 10 + 9 + 8 + 7 + 6 + 5 + 4 + 3 + 2 + 1
 * 사실상 1024중 55가지 경우
 * dp[10][1024][101]
 * 
 * 이때 0~9까지 모두 사용된 순간부터 남은 경우의 수도 미리 구해서 그냥 더하기로 했다.
 * (현재 마지막 숫자, 현재 숫자의 순서) 상태를 기준으로 구해뒀다.
 * 해당 표는 방문체크 여부가 필요없다. 단순 순열임
 */
package boj_1562_계단수;

import java.util.Scanner;

public class Main {

	static int N;							// 계단수의 길이
	static final int MOD = 1_000_000_000;   // 문제에서 주어진 출력 형식 %
	static long[][] rest = new long[10][91];	// 0~9가 모두 사용된 순간 남은 모든 경우의 수
	static long[][][] dp = new long[10][1024][101];	 // 해당 상태에서 파생될 수 있는 0~9가 모두 사용된 경우의수
	public static void main(String[] args) {
		
		// rest 테이블 초기화
		for (int j = 0; j <= 90; j++) {
			for (int i = 0; i < 10; i++) {
				if (j == 0) rest[i][j] = 1;
				else if (i == 0) rest[i][j] = rest[1][j-1];
				else if (i == 9) rest[i][j] = rest[8][j-1];
				else rest[i][j] = (rest[i-1][j-1] + rest[i+1][j-1]) % MOD;
			}
		}
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		long answer = 0;
		
		// 계단수의 길이가 10 미만이면 유효한 경우의 수가 없음
		if (N < 10) answer += 0;
		else {
			// 경우의 수를 탐색할 시작 지점 선택, "0부터 시작하는 수는 계단수가 아니다."
			for (int i = 1; i < 10; i++) {
				// 현재 지점, 현재 지점의 순서(위치), 숫자 사용상태(방문상태)
				answer += dfs(i, 1, 1 << i);
			}
		}
		
		System.out.println(answer % 1_000_000_000);
		sc.close();

	}
	
	/**
	 * 현재 상태에서 파생될 수 있는 계단수의 경우의 수를 반환
	 * @param num 현재 숫자
	 * @param idx 현재 숫자의 순서(위치)
	 * @param visited 현재까지 사용된 숫자들의 상태
	 * @return
	 */
	private static long dfs(int num, int idx, int visited) {
		
		// 숫자를 모두 사용한 상태라면(1111111111) rest 행렬에서 남은 경우의 수 반환
		if (visited == ((1 << 10)-1)) return rest[num][N-idx];
		
		// 계단수의 길이를 다 채웠지만 0~9 수가 모두 사용 안됐다면 유효하지 않으므로 0 반환
		if (idx >= N) return 0;
		
		// 해당 상태를 이미 확인한 적이 있다면 저장된 값 반환
		if (dp[num][visited][N-idx] != 0) return dp[num][visited][N-idx];
		
		// 새로 확인하는 경우
		long answer = 0;
		
		// 하나 밑의 숫자와 하나 위의 숫자를 사용할때의 경우로 나뉨
		if (num > 0) answer += dfs(num-1, idx+1, visited | (1<<(num-1)));
		if (num < 9) answer += dfs(num+1, idx+1, visited | (1<<(num+1)));
		
		// 두 경우의 수를 더해 형식에 맞게 dp에 저장하고 반환
		return dp[num][visited][N-idx] = answer % 1_000_000_000;
	}

}
