/*
 * 1시간 넘게 고민만 하다가 접근조차 못하겠어서 풀이를 봄.
 * 
 * [풀이]
 * 1. scv에 가할 수 있는 공격의 종류는 6가지임. 6가지 공격을 순회하며 모든 scv가 죽을 때까지의 최소 공격횟수를 구한다.
 * 2. 단순히 dfs로 모든 경우를 계산하면 중복계산이 생기기 때문에 메모이제이션을 통한 백트랙킹이 필요.
 * 3. 초기 scv들의 체력부터 dp[61][61][61]에 각 상태에 이르기까지 가해야 하는 공격의 최소 횟수를 갱신하며 계산
 * 		-> 이때, 각 scv의 순서는 상관이 없기에 정렬을 통해 동일한 상태를 비교할 것
 * 			(예: {10, 12, 4}와 {12, 4, 10}은 같은 경우임, 따라서 {4, 10, 12}로 정렬 후 dp에 갱신
 * 4. dp테이블에는 음수 인덱스가 존재하지 않기 때문에, 죽은 scv의 경우 0의 상태를 유지하도록 함.
 * 5. 경우를 계산한 적이 없거나, dp테이블에 기록된 횟수보다 현재 카운팅된 횟수가 적은 경우에만 다시 계산하도록 함.
 * 
 * [다른 풀이 이해안되는 부분]
 *	dp테이블에 공격횟수를 저장하지 않고, 방문 여부만 체크해서 한번 계산했던 경우는 보지 않는 풀이들이 있었다.
 *	정확히 반례가 존재하는지는 모르겠지만 느낌상 같은 상태에 도달하는데에 여러가지 경우의 수가 존재할텐데
 *	한번 계산해봤다고 다시 계산하지 않으면 최소 공격횟수를 갱신하지 못하는 경우가 있지 않을까 싶어서 이해가 안됐다.
 *  예를 들어, 71번째줄에서 dp[hp[0]][hp[1]][hp[2]] != 0 라면 그냥 return을 해버리는 것이다.
 *  심지어 boolean으로 진짜 방문 여부만 체크하는 사람들도 있었다. 하지만 해당 부분에 대해 명쾌한 설명이 적힌 풀이는 없었다.. 
 *  그냥 넘어가지 않고 갱신을 하는 게 맞다고 생각되어 이렇게 풀었다.
 */
package boj_12869_뮤탈리스크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	// 공격 순서에 따른 종류 6가지 고정
	static int[][] attack = {{9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}};
	static int[][][] dp = new int[61][61][61];		// 최대 체력 60
	static int min;		// 최소 공격횟수(답)
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// scv 개수, 입력을 받기 위함, 사실 별상관없음
		int N = Integer.parseInt(br.readLine());
		// scv가 3개 미만일 경우에는 초기 체력 0으로 설정할 것이기 때문에, 배열 크기는 3으로 고정
		int[] scv = new int[3];
		
		// 초기 체력 입력
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(input[i]);
		}
		
		// 답안 초기화, 최댓값
		min = Integer.MAX_VALUE;
		
		// 초기 체력 및 현재 공격횟수 0 인수로 전달
		dfs(scv, 0);
		
		System.out.println(min);
		br.close();
	}
	
	// 현재 체력과 지금까지 가해진 공격 횟수를 전달받아, 모든 scv가 죽을 때까지의 경우의 수를 찾는 함수
	private static void dfs(int[] hp, int cnt) {
		// 배열 정렬(같은 상황을 dp테이블에 일정하게 저장하기 위함)
		Arrays.sort(hp);
		
		// 저장된 최소 공격 횟수보다 현재 공격횟수가 크거나 같으면 더이상 계산해도 의미없음.
		if (min <= cnt) return;
		
		// 모든 scv가 죽었으면, min 갱신
		if (hp[0] == 0 && hp[1] == 0 && hp[2] == 0) {
			min = Math.min(min, cnt);
		}
		
		// 현재 상태를 계산한적이 있는데 카운트가 그때보다 크다면 더이상 계산할 필요가 없음.
		if (dp[hp[0]][hp[1]][hp[2]] != 0 && dp[hp[0]][hp[1]][hp[2]] <= cnt) return;
		
		// 현재 상태를 계산한 적이 없거나, 더 적은 공격으로 현재 상태까지 왔다면 dp테이블 갱신 필요.
		dp[hp[0]][hp[1]][hp[2]] = cnt;
		
		// 6가지 공격을 가해보면서 dfs 실행
		for (int i = 0; i < 6; i++) {
			int[] newHp = new int[3];
			
			// dp테이블에 음수 인덱스는 없으므로, 체력이 최소 0인 상태를 유지
			newHp[0] = Math.max(hp[0]-attack[i][0], 0);
			newHp[1] = Math.max(hp[1]-attack[i][1], 0);
			newHp[2] = Math.max(hp[2]-attack[i][2], 0);
			
			// 각 공격이 가해진 체력과 공격횟수+1 전달
			dfs(newHp, cnt+1);
		}
	}
}
