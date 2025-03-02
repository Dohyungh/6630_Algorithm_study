package boj_1967_트리의지름;

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int ed;
		int w;

		public Node(int ed, int w) {
			this.ed = ed;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	static int N;
	static int INF = 987654321;
	static List<Node>[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine());

			int p = Integer.parseInt(st.nextToken());
			int ch = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			tree[p].add(new Node(ch, w));
			tree[ch].add(new Node(p, w));
		}

		Node n1 = dijkstra(1); // 루트 노드에서 가장 거리가 먼 노드 n1 찾기
		Node n2 = dijkstra(n1.ed); // n1에서 가장 거리가 먼 노드 n2 찾기

		System.out.println(n2.w); // n1과 n2 사이의 거리가 정답
	}

	// 어떤 정점에 대해 다익스트라 시행
	public static Node dijkstra(int st) {
		int[] dist = new int[N + 1];
		boolean[] visited = new boolean[N + 1];

		Arrays.fill(dist, INF);
		dist[st] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();

		pq.add(new Node(st, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();

			if (visited[curr.ed])
				continue;

			visited[curr.ed] = true;

			for (Node node : tree[curr.ed]) {
				if (!visited[node.ed] && curr.w + node.w < dist[node.ed]) {
					dist[node.ed] = curr.w + node.w;
					pq.add(new Node(node.ed, dist[node.ed]));
				}
			}
		}

		// 입력 정점과 가장 거리가 먼 정점과 그 거리를 return
		int max = -INF;
		int v = -1;

		for (int i = 1; i <= N; i++) {
			if (dist[i] > max) {
				max = dist[i];
				v = i;
			}
		}

		return new Node(v, max);
	}
}
