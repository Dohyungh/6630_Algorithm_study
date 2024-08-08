package boj_1043_거짓말;

import java.io.*;
import java.util.*;

public class Main {

	static int[][] people;
	static List<Integer>[] party;
	static boolean[] isTrue;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		people = new int[2][N + 1];
		party = new ArrayList[M];
		isTrue = new boolean[M];

		// make-set
		for (int i = 1; i <= N; i++) {
			people[0][i] = i;
		}

		st = new StringTokenizer(br.readLine());

		int P = Integer.parseInt(st.nextToken());

		// 진실을 아는 사람은 true로 변경
		for (int i = 0; i < P; i++) {
			people[1][Integer.parseInt(st.nextToken())] = 1;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());

			party[i] = new ArrayList<Integer>();
			party[i].add(x);

			for (int j = 0; j < n - 1; j++) {
				int y = Integer.parseInt(st.nextToken());
				party[i].add(y);

				// 같은 파티에 참석하면 union
				union(x, y);

				// 진실을 아는 사람이 있으면 그 파티는 진실을 얘기한다.
				if (people[1][y] == 1 && !isTrue[i]) {
					isTrue[i] = true;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			// 진실을 아는 사람이 속한 집합은 모두 진실을 안다.
			if (people[1][i] == 1) {
				for (int j = 1; j <= N; j++) {
					if (findSet(i) == findSet(j)) {
						people[1][j] = 1;
					}
				}
			}
		}

		int ans = 0;

		label: for (int i = 0; i < M; i++) {
			// 거짓을 이야기 하는 파티 중에서 진실을 아는 사람이 있으면 진실로 바꾼다.
			if (!isTrue[i]) {
				for (int j = 0; j < party[i].size(); j++) {
					if (people[1][party[i].get(j)] == 1) {
						isTrue[i] = true;
						continue label;
					}
				}
				ans++;
			}
		}

		System.out.println(ans);

	}

	public static int findSet(int x) {
		if (x != people[0][x]) {
			return people[0][x] = findSet(people[0][x]);
		}
		return people[0][x];
	}

	public static void union(int x, int y) {
		people[0][findSet(y)] = findSet(x);
	}
}