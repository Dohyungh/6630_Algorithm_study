package boj_1647_도시분할계획;

import java.io.*;
import java.util.*;

public class Main {
	static class Node implements Comparable<Node> {
		int A, B, W;

		public Node(int A, int B, int W) {
			this.A = A;
			this.B = B;
			this.W = W;
		}

		@Override
		public int compareTo(Node node) {
			return this.W - node.W;
		}
	}

	static int[] p;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Node[] edges = new Node[M];
		p = new int[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());

			edges[i] = new Node(A, B, W);
		}

		Arrays.sort(edges);

		for (int i = 0; i <= N; i++) {
			p[i] = i;
		}

		int ans = 0;
		int pick = 0;

		for (int i = 0; i < M; i++) {
			if (N <= 2) {
				break;
			}

			int pa = findSet(edges[i].A);
			int pb = findSet(edges[i].B);

			if (pa != pb) {
				union(pa, pb);
				ans += edges[i].W;
				pick++;
			}

			if (pick == (N - 2))
				break;
		}

		System.out.println(ans);

	}

	public static void union(int a, int b) {
		p[findSet(b)] = findSet(a);
	}

	public static int findSet(int a) {
		if (a != p[a]) {
			return p[a] = findSet(p[a]);
		}
		return a;
	}
}
