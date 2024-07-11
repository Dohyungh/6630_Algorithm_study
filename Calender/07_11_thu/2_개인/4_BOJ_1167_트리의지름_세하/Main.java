package boj_1167_트리의지름;

import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int u;
		int w;

		public Node(int u, int w) {
			this.u = u;
			this.w = w;
		}
	}

	static int V, max, max_node;
	static List<Node>[] adj;
	static boolean[] visited;
	static boolean[][] visited_2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		V = Integer.parseInt(br.readLine());

		adj = new ArrayList[V + 1];

		for (int i = 0; i < V + 1; i++) {
			adj[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());

			while (true) {
				int u = Integer.parseInt(st.nextToken());

				// -1이 입력될 때 까지 반복
				if (u == -1)
					break;

				int w = Integer.parseInt(st.nextToken());

				adj[v].add(new Node(u, w));
			}
		}

		// max_node 초기화
		max_node = -1;
		// 임의의 점(1)에서 bfs 실행하여 max_node를 구한다.
		bfs(1);
		// max_node에서 다시 bfs 실행하여 거리의 max값을 구한다.
		bfs(max_node);

		System.out.println(max);

	}

	public static void bfs(int v) {
		// bfs 실행할 때 마다 visited와 max 초기화
		visited = new boolean[V + 1];
		max = Integer.MIN_VALUE;

		Queue<Node> queue = new LinkedList<>();

		queue.add(new Node(v, 0));

		visited[v] = true;

		while (!queue.isEmpty()) {
			Node curr = queue.poll();
			max = Math.max(max, curr.w);

			// 거리가 max이면 max_node 값을 현재 노드 값으로 갱신
			if (max == curr.w)
				max_node = curr.u;

			for (Node node : adj[curr.u]) {
				if (!visited[node.u]) {
					visited[node.u] = true;
					// v ~ 현재 지점까지의 거리 + 현재 지점에서 새로운 지점까지의 거리로 갱신하여 Queue에 추가 
					queue.add(new Node(node.u, node.w + curr.w));
				}
			}
		}
	}
}
