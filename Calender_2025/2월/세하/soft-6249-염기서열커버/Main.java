package soft_6249_염기서열커버;

import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static String[] sequences;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sequences = new String[N];

		for (int i = 0; i < N; i++) {
			sequences[i] = br.readLine();
		}

		dfs(0, 0, new boolean[N]);
		System.out.println(answer);
	}

	static void dfs(int cnt, int covered, boolean[] visited) {
		if (covered == (1 << N) - 1) {
			answer = Math.min(answer, cnt);
			return;
		}

		if (cnt >= answer)
			return;

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				List<Integer> group = findGroup(i, covered);
				if (!group.isEmpty()) {
					boolean[] newVisited = visited.clone();
					int newCovered = covered;
					for (int idx : group) {
						newVisited[idx] = true;
						newCovered |= (1 << idx);
					}
					dfs(cnt + 1, newCovered, newVisited);
				}
			}
		}
	}

	static List<Integer> findGroup(int start, int covered) {
		List<Integer> group = new ArrayList<>();
		String base = sequences[start];

		for (int i = 0; i < N; i++) {
			if ((covered & (1 << i)) == 0 && isMatch(base, sequences[i])) {
				group.add(i);
			}
		}
		return group;
	}

	static boolean isMatch(String pattern, String target) {
		StringBuilder common = new StringBuilder();
		for (int i = 0; i < M; i++) {
			if (pattern.charAt(i) == '.' || target.charAt(i) == '.') {
				common.append('.');
			} else if (pattern.charAt(i) == target.charAt(i)) {
				common.append(pattern.charAt(i));
			} else {
				return false;
			}
		}
		return true;
	}
}
