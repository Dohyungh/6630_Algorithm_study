package boj_1504_특정한최단경로;

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int vertex, cost;

		public Node(int vertex, int cost) {
			this.vertex = vertex;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			return Integer.compare(this.cost, other.cost);
		}
	}

	static int N, E;
	static List<List<Node>> graph;
	static final int INF = 200000000;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, c));
			graph.get(b).add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine());

		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());

		int[] distFromStart = dijkstra(1);
		int[] distFromV1 = dijkstra(v1);
		int[] distFromV2 = dijkstra(v2);

		// 경로 1: 1 -> v1 -> v2 -> N
		int path1 = distFromStart[v1] + distFromV1[v2] + distFromV2[N];
		// 경로 2: 1 -> v2 -> v1 -> N
		int path2 = distFromStart[v2] + distFromV2[v1] + distFromV1[N];

		int result = Math.min(path1, path2);
		System.out.println(result >= INF ? -1 : result);
	}

	static int[] dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();
			int u = current.vertex;

			if (current.cost > dist[u])
				continue;

			for (Node neighbor : graph.get(u)) {
				int v = neighbor.vertex;
				int newCost = dist[u] + neighbor.cost;
				if (newCost < dist[v]) {
					dist[v] = newCost;
					pq.add(new Node(v, newCost));
				}
			}
		}
		return dist;
	}
}
