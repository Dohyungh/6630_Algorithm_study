package boj_1916_최소비용구하기;

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<Node>[] adj = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adj[s].add(new Node(v, w));
		}

		st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		boolean[] visited = new boolean[N + 1];

		queue.add(new Node(start, 0));
		dist[start] = 0;
		visited[start] = true;

		while (!queue.isEmpty()) {
			Node curr = queue.poll();

			if (curr.v == end) {
				break;
			}

			if (visited[curr.v]) {
				continue;
			}

//			visited[curr.v] = true;

			for (Node node : adj[curr.v]) {
				if (!visited[node.v] && dist[node.v] > dist[curr.v] + node.w) {
					dist[node.v] = curr.w + node.w;
					queue.add(new Node(node.v, dist[node.v]));
				}
			}
		}

		System.out.println(dist[end]);
	}
}
