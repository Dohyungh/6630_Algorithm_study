package soft_6251_업무처리;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int num = (int) Math.pow(2, H + 1);

		List<Integer>[] tasks = new ArrayList[num];

		for (int i = 1; i < num; i++) {
			tasks[i] = new ArrayList<>();
		}

		for (int i = (int) Math.pow(2, H); i < num; i++) {
			st = new StringTokenizer(br.readLine());

			for (int k = 0; k < K; k++) {
				tasks[i].add(Integer.parseInt(st.nextToken()));
			}
		}

		int ans = 0;

		for (int r = 2; r <= R; r++) {
			for (int i = 1; i < (int) Math.pow(2, H); i++) {
				int task = 0;

				if (r % 2 == 1 && !tasks[i * 2].isEmpty()) { // 홀수 번째 날짜에 왼쪽 부하 직원의 업무 삭제
					task = tasks[i * 2].remove(0);
				} else if (r % 2 == 0 && !tasks[i * 2 + 1].isEmpty()) { // 짝수 번째 날짜에 오른쪽 부하 직원의 업무 삭제
					task = tasks[i * 2 + 1].remove(0);
				}

				if (task != 0) {
					if (i == 1) { // 부서장이 처리한 업무는 ans에 더한다.
						ans += task;
					} else {
						tasks[i].add(task);
					}
				}
			}
		}

		System.out.println(ans);
	}
}
