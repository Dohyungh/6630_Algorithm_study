package BOJ._16118_달빛여우;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_fin {
	// 늑대 불쌍해
	
	// 아니 내가 더불쌍해
	
	static class Node {
		// 인덱스
		int idx;

		// 거리
		// 인접리스트에 들어갈 때는 노드 사이의 거리 (입력받은 것)
		// 우선순위 큐에 들어갈 때는 dist 배열 업데이트한 결과 값
		int dist;

		// 늑대일 때만 해당
		// 0 이면 짝
		// 1 이면 홀
		int lookUp;

		//lookUp 을 보고 update 를 갱신할 건데.
		// 그 둘 사이 변환은 1 - x 로.
		
		// 인접리스트 및 여우 다익스트라에서 사용
		Node(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}
		
		// 늑대 다익스트라에서 사용
		Node(int idx,  int lookUp,int dist) {
			this.idx = idx;
			this.dist = dist;
			this.lookUp = lookUp;
		}
	}

	static List<Node>[] adj; // 인접리스트
	static int N; // 노드 개수
	
	
	static int[][] distWolf; // 늑대 dist 배열
	// 홀/ 짝 구분해서 2 행으로 만들어줄 거임

	static int[] distFox;
	// 우리가 아는 다익스트라의 dist
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N];
		for (int i = 0; i <N; i++) {
			adj[i] = new ArrayList<>();
		}
		StringTokenizer st2 = null;
		for (int i = 0; i < M; i++) {
			st2 = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st2.nextToken())-1;
			int to = Integer.parseInt(st2.nextToken())-1;
			int d = Integer.parseInt(st2.nextToken()) * 2; // double 쓰기 싫어서
			
			adj[from].add(new Node(to, d));
			adj[to].add(new Node(from, d));
		}
		
		distWolf = new int[2][N]; // 짝수번 도착 / 홀수번 도착
		for (int i = 0; i < 2; i++) {
			Arrays.fill(distWolf[i], Integer.MAX_VALUE);
		}
		distWolf[0][0] = 0; // 최초 시작이 홀 (/2) 로 시작하므로, 짝을 0으로 놓고 ㄱㄱ
		distWolf[1][0] = Integer.MAX_VALUE; 
		// 홀수 번 이동해서 1번 노드로 오는 거는 이제부터 살펴 봐야 아는 것.
				
		switchingDijkstra(); // 늑대 다익스트라
		
		distFox = new int[N];
		Arrays.fill(distFox, Integer.MAX_VALUE);
		distFox[0] = 0;
		
		Dijkstra();		
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (distFox[i] != Integer.MAX_VALUE && distFox[i] < Math.min(distWolf[0][i], distWolf[1][i])) answer++;
		}
		// 여우가 갈 수 있는 노드 중에서 홀/짝중 최소보다도 여우가 작으면 (빠르면) 이길 수 있는 노드
		System.out.println(answer);
	}
	
	// 홀수번만에 왔냐 짝수번만에 왔냐를 구분해야해
	public static void switchingDijkstra() {
		
		// 홀수번만에 온 애 다음 애의 짝수번을 업데이트하고
		// 짝수번만에 온 애 다음 애의 홀수번을 업데이트하는 식으로.
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node> () {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.dist - o2.dist;
			}
		});
		
		pq.add(new Node (0, 0, 0));
		
		while(!pq.isEmpty()) {
			Node nowNode = pq.poll();
			
			// 현재 홀인지 짝인지
			int lookUp = nowNode.lookUp;
			// 어느 노드의 인접을 보고 업데이트를 할 건지
			int minIdx = nowNode.idx;

			// 이미 구한 값보다 더 크면 
			// 우선순위 큐에서 순위에 밀려 있는 동안 더 작은 값으로 업데이트 되어 버렸다면.
			// 그게 큐에 더 늦게 들어와도 먼저 나갔겠지.
			// visited 와 같은 효과를 냄
			if (distWolf[lookUp][minIdx] < nowNode.dist) continue;

			// 이해하기 쉽게 따로 변수 설정
			int update = 1-lookUp;
			
				
			for (Node node : adj[minIdx]) { // 인접리스트 순회
				int nextIdx = node.idx;
				int nextDist = node.dist;
				// 킹항 연산자로 최대한 시간 줄이기
				int cost = update == 0 ? distWolf[lookUp][minIdx] + nextDist*2 : distWolf[lookUp][minIdx] + nextDist/2;
				if (distWolf[update][nextIdx] > cost) {
					distWolf[update][nextIdx] = cost;
					pq.add(new Node(nextIdx, update, cost));
				}
			}
		}
		
	}
	
	public static void Dijkstra() {
			
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node> () {
			
			@Override
			public int compare(Node o1, Node o2) {
				return o1.dist - o2.dist;
			}
			
		});
		pq.add(new Node(0,0));
		while(!pq.isEmpty()) {
			Node nowNode = pq.poll();
			int minIdx = nowNode.idx;
			if (distFox[minIdx] < nowNode.dist) continue;
			
			for (Node node : adj[minIdx]) {
				int nextDist = node.dist;
				int nextIdx = node.idx;
				int cost = distFox[minIdx] + nextDist;
				if (cost < distFox[nextIdx]) {
					distFox[nextIdx] = cost;
					pq.add(new Node(nextIdx, cost));
				}
				
			}
		}
	}
	
}