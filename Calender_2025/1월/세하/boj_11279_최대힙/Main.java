package boj_11279_최대힙;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int n = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

		while (n++ < N) {
			int num = Integer.parseInt(br.readLine());

			if (num == 0) {
				if (pq.isEmpty()) {
					sb.append("0\n");
				} else {
					sb.append(pq.poll() + "\n");
				}
			} else {
				pq.add(num);
			}
		}

		System.out.println(sb);
	}
}