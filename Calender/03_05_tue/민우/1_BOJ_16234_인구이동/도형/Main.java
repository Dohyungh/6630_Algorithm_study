package BOJ._16234_인구이동;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	static boolean[] visited; // 방문 여부
	static boolean[][] adj; // 인접행렬
	static int[][] map; // 인구수 지도
	static int N; // 지도 크기
	static int L; // 국경 오픈 조건의 최솟값
	static int R; // 국경 오픈 조건의 최댓값
	static boolean allDisconnected; //전부 개방 안함
	public static void main(String[] args) {
		
		//BFS
		//가 맞아??
		//더 좋은게 있을 것 같은데
		
		//와우 시간초과
		
		Scanner sc = new Scanner (System.in);
		
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				
			}
		}

		// 여기까지 입력
		
		int day = 0;
		while (true) { // 종료 조건은 딱 하나, 모든 나라가 국경을 닫았을 때 임.
			
			visited = new boolean[N*N]; // 매일 매일 방문 배열 초기화
			adj = new boolean[N*N][N*N]; // 매일 매일 인접 행렬 초기화

			allDisconnected = true; // 모두 국경을 닫았는지 판단할 불린

			for (int i = 0; i < N; i++) { // 모든 점에 대해 사방탐색 돌면서 국경을 열었는지(연결됐는지) 파악
				for (int j = 0; j < N; j++) {	
					updateAdj(i,j);
				}
			}
			
			if(allDisconnected) break; // 다 닫혀있으면 day를 늘리지 말고 break
			
			day++; // day 를 업데이트 하는 순간에 주의
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i*N+j]) {
						BFS(i,j); // 방문안한 나라에 대해 BFS 수행	
						// 방문안한 나라 ??
						// 생성된 연합에서 아무 나라를 시작점으로, BFS를 바닥까지(queue가 빌 때까지) 돌리면 그 연합에 속한 모든 나라를 방문하게 되는데,
						// 생성된 각 연합들 중에 가장 빨리 만나는(행,열번호가 작은) 친구를 그냥 내 맘대로 시작점으로 두겠다는 뜻임.
						
						// 트리의 상위 개념인 그래프에서는 루트가 딱히 없다는 점을 기억해 두자.
					}
				}
			}
			
		}

		System.out.println(day);
		sc.close();
		
	}
	private static void BFS(int i, int j) {
		Queue<Integer> queue = new LinkedList<>();
		
		int adjIndex = i*N+j; // "시작점"의 "인접행렬에서의" index
		queue.add(adjIndex);
		
		visited[adjIndex] = true; // 방문체크
		
		int sum = 0; // 연합에 속한 모든 나라의 국민 수를 합쳐줄 변수 생성
		
		List<int[]> union = new ArrayList<>(); // 연합인 애들 관리
		
		while(!queue.isEmpty()) {
			int nowNode = queue.poll();
			int r = nowNode/N; // 인접행렬에서 원래 배열의 index 로 돌아가기 (행 : 몫, 열 : 나머지)
			int c = nowNode%N;
			visited[nowNode] = true; // 방문체크 (중복) 이라 빼도됨 // 좀 더 보편적인 BFS 에서는 방문체크를 여기서 함 (queue에서 뽑았을 때)
			sum += map[r][c]; // 나라를 돌면서 인구를 다 합해줌
		
			union.add(new int[] {r,c}); // 연합에 속한 좌표들을 저장해야 함 // 나중에 돌면서 다 업데이트 해줘야 하니까.
			
			for (int k = 0; k < adj.length; k++) {
				if (adj[nowNode][k] && !visited[k]) { // 연결되어 있고, 아직 방문하지 않았다면
					queue.add(k); // 큐에 넣어주어라
					visited[k] = true; // 방문 체크는 여기서!!
				}
			}
		}
		
		int cnt = union.size(); // 크기 구하고
		
		int result = sum /cnt; // 균등 배분한 값을
		
		for (int[] country : union) { // 연합인 애들 돌면서 다 넣어준다.
			map[country[0]][country[1]] = result;
		}
		
		
		
		
		
	}
	private static void updateAdj(int i, int j) { // 굉장히 sparse(성긴) 하다 // 나랑 붙어있는 나라랑만 연결되기 때문에 (max: 4개만 true고 나머지 다 false) // 바꿔 말하면 그냥 사방탐색으로 돌면서 만드는 거랑 별 차이없다. 
		
		
		int adjIndex = i * N +j;
		
		int[] dr = {-1,1,0,0};
		int[] dc = {0,0,-1,1};
		
		int cnt = 0;
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			int nAdjIndex = nr*N +nc;
			
			if (nr >=0 && nr <N && nc >=0 && nc <N) {
				int gap = Math.abs(map[nr][nc] - map[i][j]);
				if (gap <=R && gap >=L) { // 차이가 L이상 R 이하일 때만 연결(국경오픈)
					adj[adjIndex][nAdjIndex] = true;
					allDisconnected = false; // 단 한 변이라도 연결되어 있으면 false
					cnt++; // 가지치기를 위한 cnt 변수
				}
				
			}
		}
		if (cnt ==0) { // 시간초과 해결! // 혼자인 곳은 BFS 사전 차단
			visited[adjIndex] = true;
		}
		
		
	}

}
