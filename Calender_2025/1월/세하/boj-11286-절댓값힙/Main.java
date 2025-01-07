package boj_11286_절댓값힙;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int n = 0;

		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// 절댓값이 같으면 더 작은 값부터 정렬 
				if (o1 == -o2) {
					return o1 - o2;
				}
				return Math.abs(o1) - Math.abs(o2);
			}
		});

		while (n++ < N) {
			int num = Integer.parseInt(br.readLine());

			if (num == 0) {
				if(pq.isEmpty()) {
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
