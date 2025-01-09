package 코테_boj_1647_도시분할계획;

/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 코테_boj_1647_도시분할계획
 * Date: 2025.01.09
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
		
		Edge[] edges = new Edge[m];
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			edges[i] = new Edge(a, b, cost);
		}
		
		p = new int[n+1];
		for (int i = 0; i <=n; i++) p[i] = i;
		
		Arrays.sort(edges);
		int count = 0;
		int answer = 0;
		
		for (int i = 0; i < m && count < n - 2; i++) {
			Edge e = edges[i];
			int a = findSet(e.a);
			int b = findSet(e.b);
			if (a != b) {
				unionSet(a, b);
				count++;
				answer += e.cost;
			}
		}
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	private static int findSet(int num) {
		if (p[num] == num) return num;
		return p[num] = findSet(p[num]);
	}
	
	private static void unionSet(int a, int b) {
		p[a] = b;
	}
}

class Edge implements Comparable<Edge>{
	int a;
	int b;
	int cost;
	
	Edge() {}
	Edge(int a, int b, int cost) {
		this.a = a;
		this.b = b;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.cost, o.cost);
	}
}