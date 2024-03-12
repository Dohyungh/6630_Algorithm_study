package BOJ._16236_아기상어;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static int[][] map;
	static int nowNode; // 현재위치는 스태틱으로 선언하고, // BFS 돌릴 때는 지역변수 path_r,c 로 지나가는 길인걸 따로 저장.
	static boolean[][] adj;
	static int size;
	static int N;
	static int[] prey;
	static boolean[] visited;
	static int answer = 0;
	static int eaten = 0;
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		map = new int[N][N];
		nowNode = 0;
		for (int i = 0 ; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j]==9) {
					nowNode = i*N+j;
					//시작하는 자리만 받고 0으로 바꿔줌!
					map[i][j]=0; // 이거 때문에 자꾸 틀림ㅠㅠ
				}
			}
		}
		// 아기상어 사이즈
		size = 2;
		
		// while 문 들어가야 해서 null 아닌 다른 값으로 초기화
		prey = new int[] {-1,-1};
		
		while(prey != null) {
			
			prey = null;
			
			// BFS 내부에서 prey를 찾고, 못찾았으면 그만! 
			BFS();

		}
		System.out.println(answer);

	}

	private static void BFS() {

		Queue<Integer> queue = new LinkedList<>();
		visited = new boolean[N*N];
		// 시작 위치 add
		queue.add(nowNode);
		
		// queue size
		int qSize = 1; // queue의 사이즈로 depth 판별
		// queue size 와 비교하면서 depth 체크할 개수 변수
		int cnt = 0;
		int depth = 0;
		
		// 시작 노드 visited 체크
		visited[nowNode] = true;
		
		// 먹이들. (같은 거리 일 경우가 있으니
		List<Integer> preys = new ArrayList<>();
		
		while (!queue.isEmpty()) {

			adjUpdate(); // 상어 size 이하로만 갈 수 있게 인접행렬 업데이트
			
			int nextNode = queue.poll();
			cnt++; // 하나 뽑고, cnt도 하나 늘려줌
			int nr = nextNode / N;
			int nc = nextNode % N;			
			
			if (map[nr][nc] < size && map[nr][nc]!=0) {
				// 뽑은 놈이 상어보다 작으면, 먹이들 리스트에 추가!
				preys.add(nextNode);
				
			}
			
			for (int i = 0; i < N*N; i++) {
				if (adj[nextNode][i] && !visited[i]) {
					// 뽑은 놈까지 해서 queue 에 연결된 친구들 추가!
					queue.add(i);
					visited[i] = true;
				}
			}
			
			// 추가가 끝나고 나서,,
			
			// 만약 cnt가 이전에 저장한 qSize 와 같다면, 한 depth를 다 돌았고,
			// 그중에 prey가 있었는지 없었는지,
			// prey가 여러개라면, 정렬해서 제일 위/왼쪽에 있는 친구를 타겟으로 잡고 이동!
			
			if (cnt == qSize) {
				qSize = queue.size();
				cnt = 0;
				
				// 먹이 판단은 한 층을 다 돌았을 때!!
				if (preys.size() != 0) {
					
					// 사이즈가 1이 아닐때만 정렬하면 되긴 한데, 그냥 하자.
					// adjIndex가 왼쪽위로 갈 수록 작은 값이기 때문에 그냥 정렬해서 가장 앞에 있는 거 뽑으면 된다!!
					Collections.sort(preys);
					
					int adjPreyIndex = preys.get(0); //제일 앞에꺼
					
					int pr = adjPreyIndex / N;
					int pc = adjPreyIndex % N;
					
					prey = new int[] {pr,pc}; // 첫번째
					
					map[pr][pc] = 0;
					
					nowNode = adjPreyIndex;
					map[nowNode/N][nowNode%N] = 0;
					eaten++;
					if (eaten == size) {
						size++;
						eaten = 0;
					}
					answer += depth;
					break;
				}
				
				depth++;

//				Collections.sort(queue); List 만 사용 가능. Comparator로 어떻게 잘 하면 되는 거 같은데 공부 필요!!ㅜㅜ
				
			}

		}

	}

	private static void adjUpdate() {
		adj = new boolean[N*N][N*N];
		
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int adjIndex = i*N+j;
				for (int d = 0; d < 4; d++) {
					int nr = i + dr[d];
					int nc = j + dc[d];
					
					if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
						if (map[nr][nc] <= size) {
							adj[adjIndex][nr*N+nc] = true;
						}
					}
				}
				
			}
		}

	}

}
