package SWEA._모의_보호필름;

import java.util.Arrays;
import java.util.Scanner;

public class Solution2 {
	
	static int answer;
	
	static int[][] map;
	
	static int K; // 문제 요구 조건 (같은 숫자(0 또는 1)가 K 개 이상 연속될 것.)
	static int D; // 층 수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			D = sc.nextInt();
			int W = sc.nextInt(); // 너비
			K = sc.nextInt();
			answer = K;
			
			map = new int[D][W];
			
			for (int i = 0; i < D; i++) {
				for (int j = 0; j < W; j++) {
					map[i][j] = sc.nextInt();
				}
			} // 여기 까지 입력.




			int[] state = new int[W]; // 바로 이전의 행을 그대로 따서 저장
			int[] cnt = new int[W]; // 연속된 숫자의 개수 (스트릭)
			
			Arrays.fill(cnt, 0); // 시작은 모두 0으로 시작. 첫 행 들어가면 다 1로 바꿔줄 것임.
			Arrays.fill(state, -1); // 이전행은 -1로 초기화
			
			boolean[] satisfied = new boolean[W]; // 만족여부는 모두 false로 초기화
			
			DP(0,-1,state,cnt,satisfied,0); // 0행을 아무것도 안채움
			DP(0,0,state,cnt,satisfied,1); // 0행을 0으로 채우고, 조작 1회 가함.
			DP(0,1,state,cnt,satisfied,1); // 0행을 1로 채우고, 조작 1회 가함.
			System.out.printf("#%d %d%n",tc,answer);
			
		}
	}

	private static void DP(int depth, int fillWith, int[] oriState, int[] oriCnt, boolean[] oriSatisfied, int ans) {
		// 모든 맵을 처음부터 복사해야함!
		// 이거 모르고 복사를 중간에서 했었는데 그래서 디버깅 30분 추가.
		int[] state = Arrays.copyOf(oriState, oriState.length); 
		boolean[] satisfied = Arrays.copyOf(oriSatisfied, oriSatisfied.length);
		int[] cnt = Arrays.copyOf(oriCnt, oriCnt.length);
		
		// 조작횟수가 지금까지 update 한 answer 보다 커지는 순간 그 이후로는 노쓸모니까 return
		if (answer <= ans) return;
		// 사실 이런 종류의 백트래킹은 DFS 에서 많이 함.
		// 한번이라도 빠르게 바닥을 찍어보면 
		// 전역변수 정답이 UPDATE 되면서 앞으로 그 이상의 DEPTH는 안들어가도 되게 해주는 방식.



		// 마지막 층 도달하면 return
		if (depth >= D) return;
		
		if (fillWith == -1) { // 아무것도 안한다는 선택을 했다면
			for (int i = 0; i < state.length; i++) {
				if (!satisfied[i]) {	
					if (map[depth][i] == state[i]) cnt[i]++; // 원본 맵을 보면서 CNT를 업데이트
					else cnt[i] = 1; // 다르면 1로 리셋. 응 다시 시작해~
				}
			}
			
			state = map[depth];
		}
		else {
			// 채웠을 때 검사 와 cnt 배열 업데이트
			for (int i = 0; i < state.length; i++) {
				if (!satisfied[i]) {				
					if (fillWith == state[i]) cnt[i]++;
					else cnt[i] = 1;
				}
			}
			Arrays.fill(state, fillWith);
		}
		// 각 열마다 만족했는지 여부 업데이트
		for (int i = 0; i < cnt.length; i++) {
			if (cnt[i] >= K && !satisfied[i]) satisfied[i] = true;
		}
		
		// 모든 열이 만족했는지 확인후 answer 업데이트
		// 여기 좀 헷갈리게 써놨는데
		for (int i = 0; i < satisfied.length; i++) {
			if (!satisfied[i]) break; // 하나라도 불만족이면 돌아가
			if(i == satisfied.length-1) { // 모두 만족해서 마지막 INDEX에 도달했다면
				answer = Math.min(answer, ans); // 비교할 수 있는 기회를 줄게. 
				//(는 사실 위에서 백트래킹 해놔서 위에서 안걸렸으면 무조건 정답보다 작음.)
				return;
			}
		}

		DP(depth+1, -1, state, cnt, satisfied, ans); // 아무고토 안해
		DP(depth+1, 0, state, cnt, satisfied, ans+1); // 0으로 채우고 1회 조작 추가
		DP(depth+1, 1, state, cnt, satisfied, ans+1); // 1로 채우고 1회 조작 추가
		
		
		
	}

}
