package BOJ._3109_빵집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static char[][] map;
	static boolean[][] visited;
	static int R;
	static int C;
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) map[i] = br.readLine().toCharArray();
		
		// 방문체크
		// 성공했으면 성공해서
		// 실패했으면 실패해서 방문체크를 '풀' 필요가 없음
		visited = new boolean[R][C];
		
		// 각 줄마다 스타트
		for (int i = 0 ; i < R; i++) solve(i, 0);
		
		System.out.println(answer);
	}
	
	//dfs
	static boolean solve(int r, int c) {
		
		if (visited[r][c]) return false;
		
		visited[r][c] = true;
		
		// 마지막 열 도착
		if (c == C-1) {
			answer++;
			return true;
		}
		
		// 순서대로 우상, 우 , 우하 탐색
		for (int nr = r-1; nr <= r+1; nr++) {
			
			// 조건 탐색
			if (nr < 0 || nr >= R || map[nr][c+1] == 'x') continue;
			
			// dfs 체크
			if (solve(nr, c+1)) return true;
		}
		
		// 성공 못했으면 false 반환
		return false;
	}

}
