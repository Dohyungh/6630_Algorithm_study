/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 음악프로그램_2623
 * Date: 2025.01.16
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

		String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int[] enter = new int[n+1];
        List<Integer>[] adj = new List[n+1];
        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int[] nums = new int[c];
            for (int j = 0; j < c; j++) nums[j] = Integer.parseInt(st.nextToken());

            for (int j = 0; j < c - 1; j++) {
                adj[nums[j]].add(nums[j+1]);
                enter[nums[j+1]]++;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] confirm = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if (enter[i] == 0) queue.add(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {

            int curr = queue.poll();
            confirm[curr] = true;
            sb.append(curr).append("\n");

            for (int next : adj[curr]) {
                if (--enter[next] == 0) queue.add(next);
            }
        }

        boolean pass = true;
        for (int i = 1; i <= n; i++) if (!confirm[i]) pass = false;

        if (pass) bw.write(sb.toString());
        else bw.write("0");
		bw.flush();
		bw.close();
		br.close();
	}
}