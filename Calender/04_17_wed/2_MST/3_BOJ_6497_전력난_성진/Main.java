package boj_6497_전력난;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static class Edge implements Comparable<Edge> {

		int X;
		int Y;
		int W;

		public Edge(int x, int y, int w) {
			X = x;
			Y = y;
			W = w;
		}

		public int compareTo(Edge e) {
			return this.W - e.W;
		}
	}

	static int[] p;

	// 전체를 밝히면서 최소 비용의 가로등만을 남겨두는 문제, 즉
	// 최소 비용 신장 트리(Minimum Spanning Tree)를 구하는 문제이다.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {

			int m = sc.nextInt();
			int n = sc.nextInt();

			if (m == 0 && n == 0) {
				break;
			}

			// 간선의 배열
			Edge[] edges = new Edge[n];

			int totalCost = 0;

			for (int i = 0; i < n; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				int z = sc.nextInt();
				// 입력받아 edges에 저장
				edges[i] = new Edge(x, y, z);
				totalCost += z;
			}

			// 비용에 따라 정렬
			Arrays.sort(edges);

			// p 배열 초기화
			p = new int[m];
			for (int i = 0; i < p.length; i++) {
				p[i] = i;
			}

			int pick = 0;
			int cost = 0;

			for (int i = 0; i < n; i++) {

				int x = edges[i].X;
				int y = edges[i].Y;

				int px = find(x);
				int py = find(y);

				if (px != py) {
					union(px, py);
					cost += edges[i].W;
					pick++;
				}

				if (pick == m - 1) {
					break;
				}

			}

			System.out.println(totalCost - cost);

		}

	}

	static int find(int x) {
		// path compression
		if (p[x] != x) {
			return p[x] = find(p[x]);
		}
		return p[x];
	}

	static void union(int x, int y) {
		p[find(y)] = find(x);
	}

}
