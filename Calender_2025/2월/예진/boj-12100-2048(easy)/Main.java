/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 2048 (Easy)_12100
 * Date: 2025.02.18
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;
	static boolean[] dr = {false, false, true, false};	// 행 역순이어야 하냐
	static boolean[] dc = {false, true, false, false};	// 열 역순이어야 하냐
	static boolean[] rFir = {false, true, false, true};	// 행 우선순회냐
	static int n;
	static int answer;
	static Deque<Integer> deque;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// n, 보드, answer 초기화
		n = Integer.parseInt(br.readLine());
		int[][] board = new int[n][n];
		answer = 0;

		// board 초기 상태 입력 및 최대값 저장
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				answer = Math.max(answer, board[i][j]);
			}
		}

		// 시뮬레이션
		dfs(0, board);

		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

	public static void dfs(int cnt, int[][] board) {

		// 5회까지 이동해봤으면 리턴
		if (cnt >= 5) return;

		// 4가지 방향 확인
		for (int d = 0; d < 4; d++) {

			// 다음 경우의 수로 넘길 보드 판 복제본
			int[][] copy = new int[n][];
			for (int i = 0; i < n; i++) copy[i] = Arrays.copyOf(board[i], n);

			// 보드판에 변경사항이 있는지 체크
			boolean changed = false;

			// 이동 방향에 따라 순회하며 열 또는 행 마다 숫자 갱신
			for (int i = 0; i < n; i++) {
				deque = new ArrayDeque<>();
				for (int j = 0; j < n; j++) {
					// 행 우선순회 방향이면 가로로 하나의 행을 순서대로 큐에 저장(0 제외)
					if (rFir[d] && copy[i][j] != 0) deque.addLast(copy[i][j]);
					// 열 우선순회 방향이면 세로로 하나의 열을 순서대로 큐에 저장(0 제외)
					else if (!rFir[d] && copy[j][i] != 0) deque.addLast(copy[j][i]);
				}

				// 역순회 여부에 따라 갱신된 열/행 반환
				int[] newLine = rFir[d] ? getNew(dc[d]) : getNew(dr[d]);

				// 변화가 있을 때만 copy 배열에 갱신 (하나라도 갱신되면 changed = true)
				for (int j = 0; j < n; j++) {
					// 행 우선 순회라면 가로방향으로 갱신
					if (rFir[d] && copy[i][j] != newLine[j]) {
						copy[i][j] = newLine[j];
						changed = true;
					}
					// 열 우선 순회라면 세로방향으로 갱신
					else if (!rFir[d] && copy[j][i] != newLine[j]) {
						copy[j][i] = newLine[j];
						changed = true;
					}
				}
			}

			// 하나라도 달라진 자리가 있다면 다음 경우의 수 탐색
			if (changed) dfs(cnt + 1, copy);
		}
	}

	public static int[] getNew(boolean deOrder) {
		int[] line = new int[n];
		int idx = deOrder ? n-1 : 0;	// 순회 방향에 따라 line 배열을 채울 idx 시작점 설정
		int move = deOrder ? -1 : 1;	// 순회 방향에 따라 line 배열을 채워갈 idx 변화값 설정

		int before = 0;

		// deque에서 0이 나올 일은 없음.
		while (!deque.isEmpty()) {
			int curr = poll(deOrder);	// 순회 방향에 따라 큐에서 꺼내는 방향 설정

			// 새로 꺼낸 수가 이전의 수와 같으면
			if (before == curr) {
				line[idx] = curr + curr;		// 두배 값을 line[idx]에 추가
				answer = Math.max(answer, line[idx]);	// 최대값 갱신
				before = 0;						// 먼저 합쳐진 수는 다시 합쳐질 수 없으므로 before = 0 으로 초기화
				idx += move;					// idx 갱신

			// 새로 꺼낸 수와 이전의 수가 다르면
			} else {
				// 이전의 수가 0이 아니라면
				if (before != 0) {
					line[idx] = before;		// line[idx]에 이전 값 추가
					idx += move;			// idx 갱신
				}
				before = curr;			// 이전 값을 새로 꺼낸 수로 갱신
			}
		}
		if (before != 0) line[idx] = before;	// 마지막 before 상태가 0이 아니라면(즉, 값이 있다면) line[idx]에 추가

		return line;
	}

	public static int poll(boolean deOrder) {
		if (deOrder) return deque.pollLast();
		return deque.pollFirst();
	}
}