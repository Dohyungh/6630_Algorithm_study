/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 문제집_1766
 * Date: 2025.01.20
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

    @SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] info = br.readLine().split(" ");
        int n = Integer.parseInt(info[0]);
        int m = Integer.parseInt(info[1]);
        int[] enter = new int[n+1];
        List<Integer>[] next = new List[n+1];
        for (int i = 0; i <= n; i++) next[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            enter[after]++;
            next[before].add(after);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) if (enter[i] == 0) pq.add(i);
        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr).append(" ");

            for (int i : next[curr]) if (--enter[i] == 0) pq.add(i);
        }

        bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}