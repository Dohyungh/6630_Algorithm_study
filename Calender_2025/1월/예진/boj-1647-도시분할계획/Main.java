/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 도시 분할 계획_1647
 * Date: 2025.01.03
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static int[] p;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		p = new int[n+1];
		for (int i = 1; i <= n; i++) p[i] = i;

		Edge[] edges = new Edge[m];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges[i] = new Edge(left, right, cost);
		}
		Arrays.sort(edges);

		int sum = 0;
		int cnt = 0;
		for (Edge e : edges) {
			if (cnt >= n-2) break;
			int left = findSet(e.left);
			int right = findSet(e.right);
			if (left != right) {
				p[left] = right;
				cnt++;
				sum += e.cost;
			}
		}

		bw.write(String.valueOf(sum));
		bw.flush();
		bw.close();
		br.close();
	}

	private static int findSet(int num) {
		if (p[num] == num) return num;
		return p[num] = findSet(p[num]);
	}
}

class Edge implements Comparable<Edge> {
	int left;
	int right;
	int cost;

	Edge() {}
	Edge(int left, int right, int cost) {
		this.left = left;
		this.right = right;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.cost, o.cost);
	}
}