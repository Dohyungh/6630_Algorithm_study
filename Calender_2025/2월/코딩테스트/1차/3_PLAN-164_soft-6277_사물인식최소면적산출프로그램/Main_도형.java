package SOFT._6277_사물인식최소면적산출프로그램;

// 실패

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int answer = Integer.MAX_VALUE;
		int[][] dots = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int color = Integer.parseInt(st.nextToken());
			dots[i][0] = x;
			dots[i][1] = y;
			dots[i][2] = color - 1;
		}
		Arrays.sort(dots, (o1,o2) -> o1[2] - o2[2]);
		
		for(int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				for (int l = 0; l < N; l++) {
					for (int d = l; d < N; d++) {
						int x1 = dots[i][0];
						int x2 = dots[j][0];
						
						int y1 = dots[l][1];
						int y2 = dots[d][1];
						
						int area = Math.abs(x1 - x2) * Math.abs(y1 - y2);
						int visited = 0;
						visited = visited | (1<<dots[i][2]);
						visited = visited | (1<<dots[j][2]);
						visited = visited | (1<<dots[l][2]);
						visited = visited | (1<<dots[d][2]);
						System.out.println("visited: " + visited + " area: " + area);
						
						for (int k = 0; k < N; k++) {
							if (k == i || k == j || k == l || k == d) continue;
							int x = dots[k][0];
							int y = dots[k][1];
							if (((x - x1) * (x - x2) <= 0) && ((y - y1) * (y - y2) <= 0)) {
								visited = visited | (1<<dots[k][2]);
							}
						}
						
						if (visited + 1 == (1<<K)) {
							answer = Math.min(answer, area);
						}
						
					}
				}
			}
		}
		System.out.println(answer);
		
		
	}

}
