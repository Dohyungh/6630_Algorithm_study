/*
 * [문제풀이]
 * 경우의 수 10가지와 밟아야 하는 각 지점을 기준으로 
 * 해당 지점부터 마지막 지점까지 들일 수 있는 최소의 힘을 저장해서 
 * 다른 상태에서 시작해 같은 상태에 도달 했을때의 중복탐색을 막았다.
 * 
 * 사실 왼발/오른발 구분이 의미가 없어서 더 효율적으로 탐색할 수 있지 않을까 싶다.
 */
package boj_2342_DDR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] power = {{0, 2, 2, 2, 2}, {0, 1, 3, 4, 3}, {0, 3, 1, 3, 4}, {0, 4, 3, 1, 3}, {0, 3, 4, 3, 1}};
	static int[] order;
	static int[][][] dp;
	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		// 마지막 0 빼고 배열에 옮겨담기
		order = new int[input.length-1];
		for (int i = 0; i < order.length; i++) {
			order[i] = Integer.parseInt(input[i]);
		}
		
		// [왼발 경우의 수][오른발 경우의 수][밟은 지점의 순서]
		dp = new int[5][5][order.length];
		
		int answer = dfs(0, 0, 0);
		
		System.out.println(answer);
		br.close();
	}
	
	/**
	 * 해당 지점부터 마지막 지점까지 사용할 최소의 힘을 반환
	 * @param left 현재 왼발의 위치
	 * @param right 현재 오른발의 위치
	 * @param idx 다음에 이동할 지점의 순서
	 * @return
	 */
	private static int dfs(int left, int right, int idx) {
		
		// 더이상 이동할 지점이 없다면 0리턴
		if (idx >= order.length) return 0;
		
		// 이미 확인한 경로라면 해당 값 반환
		if (dp[left][right][idx] != 0) return dp[left][right][idx];
		
		// 다음 지점의 실제 번호
		int next = order[idx];
		
		// 왼발을 움직이는 경우와 오른발을 움직이는 경우 + 각 들어가는 힘을 비교, 더 작은값 min에 저장
		int min = Math.min(dfs(next, right, idx+1) + power[left][next], 
							dfs(left, next, idx+1) + power[right][next]);
		
		// 해당 지점의 최소 힘이 구해졌으므로, dp테이블에 저장하고 반환
		return dp[left][right][idx] = min;
	}
}
