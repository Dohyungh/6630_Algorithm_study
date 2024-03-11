package boj_1260_DFS와BFS;

// util 디렉토리 안에 있는 모든 클래스를 import
import java.util.*;

public class Main {

	static int N;	// 노드의 수: N
	static int M;	// 간선의 수: M
	static int start;	// 시작 노드
	static int[][] graph;	// 그래프의 연결을 인접 행렬로 표현

	static boolean[] visited;	// 해당 노드를 방문했는가?
	static Queue<Integer> queue;	// bfs를 위한 queue 구현

	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		start = sc.nextInt();

		// 노드 번호 N을 표현하기 위해 행렬의 사이즈는 (N+1) x (N+1)
		graph = new int[N + 1][N + 1];

		
		// 간선의 수만큼 입력 반복
		for (int i = 0; i < M; i++) {
			
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();

			// 시작 노드 - 종료 노드의 연결성을 1로 표현
			// 양방향 그래프이므로 대칭 행렬로 생성
			graph[node1][node2] = 1;
			graph[node2][node1] = 1;

		}


		// visited를 false로 초기화
		visited = new boolean[N + 1];
		dfs(start);
		
		System.out.println();

		// visited를 false로 초기화
		visited = new boolean[N + 1];
		bfs(start);

		sc.close();

	}

	// 깊이 우선 탐색
	static void dfs(int node) {

		// 현재 노드를 출력하고, 방문 체크
		System.out.print(node + " ");
		visited[node] = true; 

		for (int i = 0; i < N + 1; ++i) {
			// 연결된 노드이며, 아직 방문하지 않은 노드라면
			if (graph[node][i] != 0 && !visited[i]) {
				// dfs를 재귀 호출(시스템 스택을 활용)
				dfs(i);
			}
		}
	}

	// 너비 우선 탐색
	static void bfs(int node) {

		queue = new LinkedList<Integer>();

		// 현재 노드를 queue에 더하고, 방문 체크
		queue.add(node);
		visited[node] = true;

		// 큐가 빌 때까지
		while (!queue.isEmpty()) {
			// 노드를 큐에서 꺼내고, 출력
			node = queue.poll();
			System.out.print(node + " ");
			for (int i = 0; i < N + 1; i++) {
				// 연결된 노드이며, 아직 방문하지 않은 노드라면
				if (graph[node][i] != 0 && !visited[i]) {
					// 큐에 더하고, 방문 체크
					queue.add(i);
					visited[i] = true;
				}
			}

		}

	}

}
