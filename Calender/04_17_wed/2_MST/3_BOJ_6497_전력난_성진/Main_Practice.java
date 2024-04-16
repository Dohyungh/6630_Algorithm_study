package boj_6497_전력난;

import java.util.Arrays;
import java.util.Scanner;

public class Main_Practice {

	static class Edge implements Comparable<Edge> {

		int x;
		int y;
		int w;

		public Edge(int x, int y, int w) {
			this.x = x;
			this.y = y;
			this.w = w;
		}

		public int compareTo(Edge e) {
			return this.w - e.w;
		}

		@Override
		public String toString() {
			return "Edge [x=" + x + ", y=" + y + ", w=" + w + "]";
		}

	}

	static int[] p;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		while (true) {

			int m = sc.nextInt(); // 집의 수 m
			int n = sc.nextInt(); // 길의 수 n

			// 입력 종료 조건
			if (m == 0 && n == 0) {
				break;
			}

			// Edge 객체를 담을 배열 선언
			Edge[] edges = new Edge[n];

			// 전체 가로등 비용
			int totalCost = 0;

			// x, y, z를 받아 edge 객체 생성 및 배열에 저장
			for (int i = 0; i < n; i++) {
				edges[i] = new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt());
				totalCost += edges[i].w; // 전체 가로등 비용 계산
			}
			// 가중치 기준 오름차순 정렬
			Arrays.sort(edges);

			// p 배열 makeSet
			p = new int[m];
			for (int i = 0; i < m; i++) {
				p[i] = i;
			}

			// 지금까지 킨 가로등 개수
			int light = 0;

			// 가로등 최소 비용
			int minCost = 0;

			
			// edges 배열을 순회하며 간선을 선택할 것임
			for (Edge edge : edges) {

				int px = find(edge.x);
				int py = find(edge.y);

				int cost = edge.w;

				// 사이클이 발생하지 않는 경우, 트리에 포함시키고, 비용을 더한다.
				if (px != py) {
					union(px, py);
					minCost += cost;
					light++;	// 가로등 개수 더해주기
				}
				// 가로등 다 뽑았다면(m-1개) 종료
				if (light == m-1) {
					break;
				}
			}
			
			// 절약한 비용 출력
			System.out.println(totalCost - minCost);
			
		}

	}

	static int find(int x) {
		if (p[x] != x) {
			p[x] = find(p[x]);
		}
		return p[x];
	}

	static void union(int x, int y) {
		p[find(y)] = find(x);
	}

}
