/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 중량제한_1939
 * Date: 2025.02.14
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

        List<Edge> edges = new ArrayList<>();
        for (int i = 0 ; i < m; i++) {
            st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end, weight));
        }
        Collections.sort(edges);

        st = new StringTokenizer(br.readLine(), " ");
        int fac1 = Integer.parseInt(st.nextToken());
        int fac2 = Integer.parseInt(st.nextToken());

        p = new int[n+1];
        for (int i = 1; i <= n; i++) p[i] = i;

        for (int i = 0; i < m; i++) {
            Edge edge = edges.get(i);

            int a = findSet(edge.start);
            int b = findSet(edge.end);

            if (a != b) p[a] = b;

            if (findSet(fac1) == findSet(fac2)) {
                bw.write(String.valueOf(edge.w));
                break;
            }
        }

		bw.flush();
		bw.close();
		br.close();
	}
    public static int findSet(int x) {
        if (p[x] == x) return x;
        return p[x] = findSet(p[x]);
    }
}

class Edge implements Comparable<Edge> {
    int start;
    int end;
    int w;

    Edge (int start, int end, int w) {
        this.start = start;
        this.end = end;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(o.w, this.w);
    }
}