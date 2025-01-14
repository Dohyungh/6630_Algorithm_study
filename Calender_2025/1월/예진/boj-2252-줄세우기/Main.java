/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 줄 세우기_2252
 * Date: 2025.01.14
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
		
		st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] enter = new int[n+1];
        List<Integer>[] adj = new List[n+1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int before = Integer.parseInt(st.nextToken());
            int after = Integer.parseInt(st.nextToken());

            enter[after]++;
            adj[before].add(after);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (enter[i] == 0) queue.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {

            int curr = queue.poll();
            sb.append(curr).append(" ");

            for (int next : adj[curr]) {
                if (--enter[next] == 0) queue.offer(next);
            }
        }

        bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}