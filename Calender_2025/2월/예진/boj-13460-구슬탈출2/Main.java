/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 구슬 탈출 2_13460
 * Date: 2025.02.17
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static BufferedWriter bw;
	static StringTokenizer st;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] board;

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        board = new int[n][m];
        State start = new State();

        // 판 상태 입력 (공 위치 제외, 공 위치는 start 클래스에 별도로 저장), 큐에서 꺼낸 공의 위치로 동일한 판 위에 테스트 할 것임.
        // 벽은 1, 구멍은 -1로 저장
        for (int i = 0; i < n; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (chars[j] == '#') board[i][j] = 1;
                else if (chars[j] == 'O') board[i][j] = -1;
                else if (chars[j] == 'R') {
                    start.redR = i;
                    start.redC = j;
                } else if (chars[j] == 'B') {
                    start.blueR = i;
                    start.blueC = j;
                }
            }
        }

        Queue<State> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[n][m][n][m];      // 두 구슬의 위치로 방문체크
        queue.add(start);                                       // 시작위치 추가
        visited[start.redR][start.redC][start.blueR][start.blueC] = true;       // 시작 위치 방문처리

        int cnt = 0;    // 시작위치를 꺼낼 때는 아직 한번도 이동하지 않은 상태
        int answer = -1;    // 빨간 구슬이 구멍에 빠지는 경우를 찾지 못하면 이 값을 출력하게 될 것
        out: while (!queue.isEmpty() && cnt <= 10) {

            // 같은 회차(cnt)로 구분되는 모든 경우 확인
            int size = queue.size();
            for (int i = 0; i < size; i++) {

                // 현재 두 구슬의 위치
                State curr = queue.poll();

                // 빨간 구슬이 구멍에 빠지는 경우라면 회차 저장 후 종료
                if (board[curr.redR][curr.redC] < 0) {
                    answer = cnt;
                    break out;
                }

                // 현재 구슬 위치를 보드에 저장
                board[curr.redR][curr.redC] = 2;
                board[curr.blueR][curr.blueC] = 3;

                // 네 방향으로 기울임 테스트
                for (int d = 0; d < 4; d++) {
                    // 기울였을 때 두 구슬의 멈추는 지점 구하기
                    int[] nRed = moveNext(curr.redR, curr.redC, d);
                    int[] nBlue = moveNext(curr.blueR, curr.blueC, d);

                    // 두 구슬이 같은 행이나 열에 있는 경우,
                    // 먼저 시도한 구슬이 다른 구슬에 의해 끝까지 이동하지 못함을 고려하여 한번 더 시도
                    nRed = moveNext(nRed[0], nRed[1], d);

                    // 이동하려는 경우가 방문한 적 없고, 파란 구슬이 구멍에 빠지는 경우가 아니라면 큐에 추가
                    if (!visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]] && board[nBlue[0]][nBlue[1]] >= 0) {
                        queue.add(new State(nRed[0], nRed[1], nBlue[0], nBlue[1]));
                        visited[nRed[0]][nRed[1]][nBlue[0]][nBlue[1]] = true;
                    }

                    // 이동한 구슬의 위치가 구멍이 아니라면 원래대로(빈칸) 되돌림
                    // 구멍이라면 칸의 값을 갱신 안했기 때문에 -1일 것
                    if (board[nRed[0]][nRed[1]] > 0) board[nRed[0]][nRed[1]] = 0;
                    if (board[nBlue[0]][nBlue[1]] > 0) board[nBlue[0]][nBlue[1]] = 0;

                    // 이동하기 전 위치로 구슬 다시 초기화
                    board[curr.redR][curr.redC] = 2;
                    board[curr.blueR][curr.blueC] = 3;
                }

                // 현재 경우에서 이동하는 모든 방향을 확인하고 큐에 추가했으니, 판 다시 비우기
                board[curr.redR][curr.redC] = 0;
                board[curr.blueR][curr.blueC] = 0;
            }

            // 회차 증가
            cnt++;
        }

        bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}

    public static int[] moveNext(int r, int c, int d) {
        // 반환값을 현재 위치로 초기화
        int[] next = new int[] {r, c};

        // 현재 위치가 구멍이라면 그대로 반환(이미 구멍에 빠졌는데 위 코드에서 재시도로 인해 다시 메서드가 호출된 경우)
        if (board[r][c] < 0) return next;

        // 이동 가능한 마지막 위치까지 반환값 변경
        // 다음 칸이 벽(1)이거나 다른구슬(2 or 3)이면 중지, 지금 칸이 구멍(-1)이면 중지
        while (board[next[0] + dr[d]][next[1] + dc[d]] <= 0 && board[next[0]][next[1]] >= 0) {
            next[0] += dr[d];
            next[1] += dc[d];
        }

        // 지금 이동하려는 위치가 빈칸이면 구슬 이동 (구멍(-1)이라면 -1값이 바뀌면 안됨)
        if (board[next[0]][next[1]] == 0) {
            board[next[0]][next[1]] = board[r][c];
        }

        // 원래 위치와 이동하려는 위치가 같지 않으면 원래 위치에서 구슬 삭제, 같은데 삭제하면 구슬이 그냥 사라짐
        if (!(r == next[0] && c == next[1])) board[r][c] = 0;

        return next;
    }
}

class State {
    int redR;
    int redC;
    int blueR;
    int blueC;

    State () {}
    State(int redR, int redC, int blueR, int blueC) {
        this.redR = redR;
        this.redC = redC;
        this.blueR = blueR;
        this.blueC = blueC;
    }
}