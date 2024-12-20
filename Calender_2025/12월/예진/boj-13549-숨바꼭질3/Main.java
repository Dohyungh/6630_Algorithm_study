/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 숨바꼭질 3_13549
 * Date: 2024.12.20
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
		
		int[] dp = new int[150_001];
        Arrays.fill(dp, Integer.MAX_VALUE);
        String[] input = br.readLine().split(" ");
        int start = Integer.parseInt(input[0]);
        int end = Integer.parseInt(input[1]);

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0));
        dp[start] = 0;

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            if (curr.num == end) break;

            if (curr.num > 0 && dp[curr.num - 1] > curr.count + 1) {
                dp[curr.num - 1] = curr.count + 1;
                queue.add(new Node(curr.num - 1, dp[curr.num - 1]));
            }
            if (curr.num < 150_000 && dp[curr.num + 1] > curr.count + 1) {
                dp[curr.num + 1] = curr.count + 1;
                queue.add(new Node(curr.num + 1, dp[curr.num + 1]));
            }
            if (curr.num < 75_000 && dp[curr.num * 2] > curr.count) {
                dp[curr.num * 2] = curr.count;
                queue.add(new Node(curr.num * 2, dp[curr.num * 2]));
            }
        }

        bw.write(String.valueOf(dp[end]));
		bw.flush();
		bw.close();
		br.close();
	}
}

class Node implements Comparable<Node> {
    int num;
    int count;

    Node(int num, int count) {
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo (Node o) {
        return Integer.compare(this.count, o.count);
    }
}