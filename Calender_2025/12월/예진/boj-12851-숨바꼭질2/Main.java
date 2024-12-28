/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 숨바꼭질 2_12851
 * Date: 2024.12.28
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

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);

        int[] time = new int[150_000];
        int[] cnt = new int[150_000];
        Arrays.fill(time, 123456789);

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        time[n] = 0;
        cnt[n] = 1;

        while(!queue.isEmpty()) {

            int curr = queue.poll();

            if (curr == k) break;

            int[] step = new int[] {curr - 1, curr + 1, curr * 2};

            for (int next : step) {
                if (next >= 0 && next < 150_000 && time[next] >= time[curr] + 1) {
                    if (time[next] == time[curr] + 1) cnt[next] += cnt[curr];
                    else {
                        time[next] = time[curr] + 1;
                        cnt[next] = cnt[curr];
                        queue.add(next);
                    }
                }
            }
        }

        bw.write(String.valueOf(time[k]) + "\n");
        bw.write(String.valueOf(cnt[k]));
		bw.flush();
		bw.close();
		br.close();
	}
}