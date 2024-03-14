package BOJ._7562_나이트의이동;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static int answer;
	static int N;
	static int[][] timeMap;
	
	// 나이트의 이동 8방 탐색
	static int[] dr = {-1,-2,-2,-1,1,2,2,1};
	static int[] dc = {-2,-1,1,2,-2,-1,1,2};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt(); // SWEA의 향기
		
		for (int tc = 1; tc <= T; tc++) {
			
			N = sc.nextInt();
			int start_r = sc.nextInt(); //출발지 입력
			int start_c = sc.nextInt();
			
			int dest_r = sc.nextInt(); //도착지 입력
			int dest_c = sc.nextInt();
			
			// 뭐로하지
			// BFS는 지겨우니까!
			// DFS
			
			answer = Integer.MAX_VALUE;
			boolean[][] visited = new boolean[N][N];
			
			visited[start_r][start_c] = true;
			
			// 각 지점에 더 빨리 도착했을 때만 DFS 를 호출하도록 하기 위해
			// timeMap 을 만들어서 현재 쓰여져 있는 시간보다 빨리 도달하는 DFS 만 호출.
			timeMap = new int[N][N]; 
			
			// 시간초기화는 최댓값으로
			for (int i = 0; i < N; i++) {
				Arrays.fill(timeMap[i], Integer.MAX_VALUE);
			}
			
			DFS(start_r,start_c,dest_r,dest_c,0,visited);
			
			System.out.println(answer);
			
			
		}
		
		sc.close();
		
	}

	public static void DFS(int r, int c, int dest_r, int dest_c, int depth, boolean[][] visited) {
		if (r == dest_r && c == dest_c) { // 목적지에 도달했으면 정답을 업데이트
			answer = Math.min(answer, depth);
			return;
		}
		
		if (depth < answer) { // 지금 알고 있는 답보다 늦는 순간 DFS 호출 안함. 백트래킹 1
			
			for (int d = 0; d < 8; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				int alpha = -1; // 일단 -1 로 초기화
				//난 쩔어 난 멋있어 난 대단해 나 산공맞아
				if (getDist(r,c,dest_r,dest_c)<=3) alpha = 3; // <=2, alpha = 1 (틀림, 도달을 못함) //<=3, alpha = 1혹은2 (백준은 정답이라는데 내가 틀린 테케를 찾음 (6나오는 그것)) // <=3, alpha = 3 (정답!)
				else alpha = 0; // 1번 전략
				
				if (nr >= 0 && nr < N && nc >= 0 && nc <N  && getDist(r,c,dest_r,dest_c)+alpha>=getDist(nr,nc,dest_r,dest_c)) { // 가깝기 전까지는 1번전략 선택. 백트래킹 2
					// 아직 방문 안했거나, 해당 지점에 쓰여져 있는 것보다 더 빨리 도착했을 때만 DFS 호출. 백트래킹 3
					if (!visited[nr][nc] || timeMap[nr][nc] > depth+1) { // timeMap 을 -1로 해서 방문체크를 visited를 안쓰고 timeMap으로 동시에 할 수도 있음!(by 성진)
						visited[nr][nc] = true;
						timeMap[nr][nc] = depth+1;
						DFS(nr,nc,dest_r,dest_c,depth+1, visited);
					}
				}
			}
		}
	}
	
	// 없으면 시간초과
	public static int getDist(int r, int c, int dest_r, int dest_c) {
		//그냥 절댓값 거리로 했음.
		return Math.abs(r-dest_r) + Math.abs(c-dest_c);
	}

}



//300
//0 0
//123 123
//
// 백트래킹2 안하면 시간초과

//10
//5 5
//6 6
//
//거리가 멀어지면 안되는 전략 1번만 하는 백트래킹 2는 답을 찾을 수가 없음
//바로 도달하기 힘든 곳에 대해서는 잠시 멀리 갔다가 다시 와야할 수가 있음.