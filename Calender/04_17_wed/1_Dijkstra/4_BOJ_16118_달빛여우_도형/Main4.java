package BOJ._16118_달빛여우;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main4 {
	// 늑대 불쌍해
	
	// 다익스트라 돌릴 때 *2 /2 왔다갔다 하라는 거 같은데
	
	static int[][] distWolf;
	static List<int[]>[] adj;
	static int N;
	
	static int[] distFox;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N];
		for (int i = 0; i <N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st2.nextToken())-1;
			int to = Integer.parseInt(st2.nextToken())-1;
			int d = Integer.parseInt(st2.nextToken()) * 2;
			
			adj[from].add(new int[] {to, d});
			adj[to].add(new int[] {from, d});
		}
		
		distWolf = new int[2][N]; // 홀수번 도착 / 짝수번 도착
		for (int i = 0; i < 2; i++) {
			Arrays.fill(distWolf[i], Integer.MAX_VALUE);
		}
		distWolf[0][0] = 0;
		distWolf[1][0] = Integer.MAX_VALUE;
				
		switchingDijkstra();
		
		distFox = new int[N];
		Arrays.fill(distFox, Integer.MAX_VALUE);
		distFox[0] = 0;
		visited = new boolean[N];
		Dijkstra();		
		
		
		int answer = 0;
		for (int i = 0; i < N; i++) {
			if (distFox[i] != Integer.MAX_VALUE && distFox[i] < Math.min(distWolf[0][i], distWolf[1][i])) answer++;
		}
		System.out.println(answer);
	}
	
	// 홀수번만에 왔냐 짝수번만에 왔냐를 구분해야해
	// 이거 생각하는데 왜 이렇게 오래 걸렸냐
	public static void switchingDijkstra() {
		
		// 홀수번만에 온 애 다음 애의 짝수번을 업데이트하고
		// 짝수번만에 온 애 다음 애의 홀수번을 업데이트하는 식으로.
		
		boolean[][] visited = new boolean[2][N];
		
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]> () {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[2] - o2[2];
			}
		});
		
		pq.add(new int[] {0, 0, 0});
		
		while(!pq.isEmpty()) {
			int[] nowNode = pq.poll();
			
			int lookUp = nowNode[0];
			int minIdx = nowNode[1];
			if (visited[lookUp][minIdx]) continue;
			if (distWolf[lookUp][minIdx] < nowNode[2]) continue;
			int update = 1-lookUp;
			
				
//			distWolf[update][nodes[0]] = update == 0 ? Math.min(distWolf[update][nodes[0]], distWolf[lookUp][minIdx] + nodes[1]*2) :Math.min(distWolf[update][nodes[0]], distWolf[lookUp][minIdx] + nodes[1]/2);
//			pq.add(new int[] {update, nodes[0], distWolf[update][nodes[0]]});
				
			if (update == 0) {
				for (int[] nodes : adj[minIdx]) {
					int cost = distWolf[lookUp][minIdx] + nodes[1]*2;
					
					if (!visited[update][nodes[0]] && distWolf[update][nodes[0]] > cost) {
						distWolf[update][nodes[0]] = cost;
						pq.add(new int[] {update, nodes[0], cost});
					}
				}
			}
			if (update == 1) {
				for (int[] nodes : adj[minIdx]) {
					int cost = distWolf[lookUp][minIdx] + nodes[1]/2;
					if (!visited[update][nodes[0]] && distWolf[update][nodes[0]] > cost) {
						distWolf[update][nodes[0]] = cost;
						pq.add(new int[] {update, nodes[0],cost});
					}
				}
			}
		
			visited[lookUp][minIdx] = true;
			
			
		}
		
	}
	
	public static void Dijkstra() {
			
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]> () {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
			
		});
		pq.add(new int[] {0,0});
		while(!pq.isEmpty()) {
			int[] nowNode = pq.poll();
			int minIdx = nowNode[0];
			if (visited[minIdx]) continue;
			if (distFox[nowNode[0]] < nowNode[1]) continue;
			
			for (int[] nodes : adj[minIdx]) {
				int cost = distFox[minIdx] + nodes[1];
				if (!visited[nodes[0]] && cost < distFox[nodes[0]]) {
					distFox[nodes[0]] = cost;
					pq.add(new int[] {nodes[0], cost});
				}
				
			}
			visited[minIdx] = true;
		}
	}
	
}