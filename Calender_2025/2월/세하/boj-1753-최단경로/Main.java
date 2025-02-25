package boj_1753_최단경로;

import java.io.*;
import java.util.*;

public class Main {
	static int INF = 987654321;

	static class Node implements Comparable<Node> {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(br.readLine());

		List<Node>[] graph = new ArrayList[V + 1];

		// graph 초기화
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}

		// 간선, 가중치 입력
		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			graph[u].add(new Node(v, w));
		}

		int[] dist = new int[V + 1];
		boolean[] visited = new boolean[V + 1];

		Arrays.fill(dist, INF); // 다른 정점까지의 거리 INF로 초기화
		dist[K] = 0; // 시작 정점의 거리 0으로 초기화
		
		// Dijkstra
		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.add(new Node(K, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			visited[curr.v] = true;

			for (Node node : graph[curr.v]) {
				if (!visited[node.v] && curr.w + node.w < dist[node.v]) {
					dist[node.v] = curr.w + node.w;
					pq.add(new Node(node.v, dist[node.v]));
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF) {
				bw.write("INF\n");
			} else {
				bw.write(dist[i] + "\n");
			}
		}

		bw.flush();
	}
}
