import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int[][] map;
	static int M;
	static int N;
	
	static ArrayList[] adj; // 연결 리스트. 쭉 늘어뜨린 인덱스를 가짐 (i * N + j)
	// ?연결리스트 이렇게 만드는 거 맞아?
	// 노란줄 뜨는데

	// 죽은 자리(사방이 내리막이라 출발점에서 도달할 수 없는 자리)인지 확인 
	//(죽은 자리는 사방이 다 내리막이라 뻗어 나가는 경우의 수가 많음)
	static boolean[][] reachable; 
	
	// 각 자리에서 시작했을 때 도착지에 도달할 수 있는 가지 수를 저장할 일종의 DpTable
	static int[][] pathMap;
	
	// BFS, DFS 에서 모두 사용할 visited
	// 쭉 늘어뜨린 인덱스를 가짐 (i * N + j)
	static boolean[] visited;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt(); // 행 사이즈
		
		N = sc.nextInt(); // 열 사이즈
		
		map = new int[M][N]; // 지도
		pathMap = new int[M][N]; // 경로의 수 DP table
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// 입력
		
		// 인접리스트 업데이트
		adj = new ArrayList[M*N];
		adjUpdate();
		
		
		// 30% 짜리 백트래킹
		reachable = new boolean[M][N];
		visited = new boolean[M*N];
		reachableUpdate(); // BFS 로 업데이트
		
		// 다시 DFS 에 쓸 Visited 초기화
		visited = new boolean[M*N];
		
		// DFS + DP 출발
		for (int i = M-1; i>=0; i--) {
			for (int j = N-1; j>=0; j--) {
				
				// 도착지는 0으로 그대로 둬
				if (i == M-1 && j == N-1) continue;
				// 앞 두 인덱스는 시작점, 뒤에 두개는 지나가는 점.
				// 시작점의 경로의 수를 구해줄 것이기 때문에 어디서 시작했는지 알아야 함!
				DFS(i,j,i,j);
			}
		}
		
		System.out.println(pathMap[0][0]);
	}
	
	
	// 30% 짜리 백트래킹
	private static void reachableUpdate() {
		
		// 갈 수 있는지 여부를 판단할 때는 재귀를 도는 DFS 보다는
		// 안정적인 BFS를 선호하는 편임.

		// BFS 로 맵을 쭉 훑어서 
		Queue<Integer> queue = new LinkedList<>();
		
		queue.add(0);
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			reachable[node/N][node%N] = true;
			
			for (int a = 0; a <adj[node].size(); a++) {
				int n = (int) adj[node].get(a);
				if (!visited[n]) {
					queue.add(n);
					visited[n] = true;
					// 그냥 여기서 한번에 체크해도 될 듯!
				}
			}
		}
	}

	
	private static void DFS(int start_r, int start_c, int i, int j) {
		if (!reachable[i][j]) return;
		
		// 그냥 도착했으면 경로의 수 1 올려줘
		if (i == M-1 && j == N-1) {
			pathMap[start_r][start_c]++;
			return;
		}
		
		
//		if (pathMap[i][j] != 0) { // 원래 코드
		// 이미 체크한 자리의 경로의 수가 0일 수도 있다는 걸 간과함.
		// 그래서 pathMap에 0이라고 써있으니까 여기 안와봤나? 하고 계속 다시 검사하다가 시간초과 남
		
		if (i*N+j > start_r*N+start_c) { // 더 정확한 조건문 (이미 검사한 곳인가요?) 
			//쭉 늘어뜨린 인덱스값이 크다는 건 행 우선순회를 했을 떄 더 나중이라는 뜻
			pathMap[start_r][start_c] += pathMap[i][j]; // 6%짜리 백트래킹
			return;
		}
		
		int adjIdx = i * N + j;
		for (int a = 0; a <adj[adjIdx].size(); a++) {
			int n = (int) adj[adjIdx].get(a);
			if (!visited[n]) {
				visited[n] = true;
				DFS(start_r,start_c,n/N,n%N);
				visited[n] = false;
			}
		}
	}
	
	private static void adjUpdate() {
		int[] dr = {-1,1,0,0};
		int[] dc=  {0,0,-1,1};
		
		for (int i = 0; i< M; i++) {
			for (int j = 0; j < N; j++) {
				int adjIdx = i*N+j;
				adj[adjIdx] = new ArrayList<Integer>();
				
				
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					// 맵 안에 있고, 자기보다 낮은 애들만 연결시켜줌
					if (nr>=0 && nr < M && nc >= 0 && nc < N && map[i][j] > map[nr][nc]) {
						adj[adjIdx].add(nr*N+nc);
					}
				}
			}
		}
		
	}

}
