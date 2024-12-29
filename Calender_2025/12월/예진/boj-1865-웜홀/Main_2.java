/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 웜홀_1865
 * Date: 2024.12.29
 * Time: 764 ms
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {

			String[] input = br.readLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			int w = Integer.parseInt(input[2]);

			List<Edge> edges = new ArrayList<>();

			for (int i = 0; i < m + w; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());

				if (i >= m) {
					edges.add(new Edge(start, end, -time));
				} else {
					edges.add(new Edge(start, end, time));
					edges.add(new Edge(end, start, time));
				}
			}

			int[] minTime = new int[n+1];
			boolean cycle = false;

			out: for (int i = 0; i <= n; i++) {
				for (Edge e : edges) {
					if (minTime[e.end] > minTime[e.start] + e.time) {
						minTime[e.end] = minTime[e.start] + e.time;

						if (i == n) {
							cycle = true;
							break out;
						}
					}
				}
			}

			if (cycle) bw.write("YES\n");
			else bw.write("NO\n");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}

class Edge {
	int start;
	int end;
	int time;

	Edge() {}
	Edge(int start, int end, int time) {
		this.start = start;
		this.end = end;
		this.time = time;
	}
}