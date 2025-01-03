/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 최소 스패닝 트리_1197
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
        int v = Integer.parseInt(input[0]);
        int e = Integer.parseInt(input[1]);

        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(left, right, w);
        }
        Arrays.sort(edges);

        p = new int[v+1];
        for (int i = 1; i <= v; i++) {
            p[i] = i;
        }

        int answer = 0;
        for (Edge edge : edges) {
            int left = findSet(edge.left);
            int right = findSet(edge.right);
            if (left != right) {
                answer += edge.w;
                p[left] = p[right];
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
}

class Edge implements Comparable<Edge> {
    int left;
    int right;
    int w;

    Edge() {}
    Edge(int left, int right, int w) {
        this.left = left;
        this.right = right;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(this.w, o.w);
    }
}