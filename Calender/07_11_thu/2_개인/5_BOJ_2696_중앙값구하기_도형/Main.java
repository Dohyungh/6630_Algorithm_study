package BOJ._2696_중앙값구하기;

/*
INPUT:
1
5
-2147483648 -2147483648 -2147483648 -2147483647 2147483647

ANSWER:
3
-2147483648 -2147483648 -2147483648

OUTPUT:
3
-2147483648 -2147483648 -2147483647

그냥 o2-o1 하면, -2147483647 < -2147483648 로 정렬됨

 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			PriorityQueue<Integer> pqASC = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			PriorityQueue<Integer> pqDESC = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return Integer.compare(o2,o1);
				}
			});

			StringBuilder sb = new StringBuilder();

			for (int i = 1; i <= N; i++) {
				int szA = pqASC.size();
				int szD = pqDESC.size();
				int num = sc.nextInt();
				if (szA == szD) {
					if (pqASC.isEmpty() || num >= pqASC.peek()) {
						pqASC.offer(num);
						sb.append(pqASC.peek());
					} else {
						pqDESC.offer(num);
						sb.append(pqDESC.peek());
					}
				} else {
					if (szA > szD) {
						if (num >= pqASC.peek()) {
							pqDESC.offer(pqASC.poll());
							pqASC.offer(num);
						} else {
							pqDESC.offer(num);
						}
					} else {
						if (num <= pqDESC.peek()) {
							pqASC.offer(pqDESC.poll());
							pqDESC.offer(num);
						} else {
							pqASC.offer(num);
						}
					}
				}
				if (i % 2 == 1 && i % 20 != 0) {
					sb.append(" ");
					continue;
				}
				if (i % 20 == 0) {
					sb.append("\n");
				}
			}
			sb.append("\n");
			System.out.println(""+(N/2 + 1));
			System.out.print(sb.toString());

		}
	}

}
