package boj_15686_치킨배달;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M, ans;
	static int[][] arr;
	static ArrayList<int[]> homes, chickens, selChickens;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[N][N];

		homes = new ArrayList<int[]>();
		chickens = new ArrayList<int[]>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());

			for (int c = 0; c < N; c++) {
				arr[r][c] = Integer.parseInt(st.nextToken());

				if (arr[r][c] == 1) {
					homes.add(new int[] { r, c }); // 집들의 위치 저장
				} else if (arr[r][c] == 2) {
					chickens.add(new int[] { r, c }); // 치킨집들의 위치 저장
				}
			}
		}

		if (M == chickens.size()) { // 폐업해야할 가게가 없으면 바로 치킨 거리 구하기
			ans = searchDist(chickens);
		} else {
			selChickens = new ArrayList<int[]>();
			
			// selChickens List 초기화 
			for(int i=0; i<M; i++) {
				selChickens.add(new int[] {0,0});
			}
			ans = Integer.MAX_VALUE;
			
			// 조합을 통해 치킨집 고르기 
			Combination(0, 0);
		}

		System.out.println(ans);
	}

	public static void Combination(int idx, int s_idx) {
		if (s_idx >= M) {
			// 고른 후에 최소 치킨 거리 찾기 
			ans = Math.min(ans, searchDist(selChickens));
			return;
		}

		for (int i = idx; i <= chickens.size() - M + s_idx; i++) {
			selChickens.set(s_idx, chickens.get(i));
			Combination(i + 1, s_idx + 1);
		}
	}

	public static int searchDist(ArrayList<int[]> chickens) {
		int ans = 0;
		
		// homes를 순회하며 치킨 거리(고른 chickens 중 가장 짧은 거리) 찾기 
		for (int[] home : homes) {
			int min = Integer.MAX_VALUE;

			for (int[] chicken : chickens) {
				min = Math.min(min, Math.abs(home[0] - chicken[0]) + Math.abs(home[1] - chicken[1]));
			}
			ans += min;
		}

		return ans;
	}
}
