/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 서강그라운드_14938
 * Date: 2024.12.31
 */

import java.util.*;
import java.lang.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);		// 지역읙 개수
		int m = Integer.parseInt(input[1]);		// 수색범위
		int r = Integer.parseInt(input[2]);		// 길의 개수

		int[] item = new int[n+1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
		}

		List<Node>[] adj = new List[n+1];
		for (int i = 0; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());

			adj[a].add(new Node(b, dist));
			adj[b].add(new Node(a, dist));
		}

		int max = 0;

		for (int i = 1; i <= n; i++) {

			int[] memo = new int[n+1];
			boolean[] visited = new boolean[n+1];
			Arrays.fill(memo, 123456789);
			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(i, 0));
			int sum = 0;

			while(!pq.isEmpty()) {

				Node curr = pq.poll();

				if (!visited[curr.num]) sum += item[curr.num];
				visited[curr.num] = true;

				for (Node next : adj[curr.num]) {
					int d = curr.dist + next.dist;
					if (memo[next.num] > d && d <= m) {
						pq.offer(new Node(next.num, d));
						memo[next.num] = d;
					}
				}
			}

			max = Math.max(max, sum);
		}

		bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
		br.close();
	}
}

class Node implements Comparable<Node> {
	int num;
	int dist;

	Node() {}
	Node(int num, int dist) {
		this.num = num;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.dist, o.dist);
	}
}