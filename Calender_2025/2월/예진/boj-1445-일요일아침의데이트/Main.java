/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 일요일 아침의 데이트_1445
 * Date: 2025.02.24
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

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        char[][] forest = new char[n][m];

        int[][][] dp = new int[n][m][];
        int[] start = new int[2];
        for (int i = 0; i < n; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                forest[i][j] = tmp[j];
                if (forest[i][j] == 'S') start = new int[] {i, j};
                dp[i][j] = new int[] {2500, 2500};
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (forest[i][j] != 'g') continue;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || forest[nr][nc] != '.') continue;
                    forest[nr][nc] = 's';
                }
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start[0], start[1], 0, 0 ));

        out : while(!pq.isEmpty()) {

            Node curr = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];



                if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;

                Node next = new Node(nr, nc, curr.gCnt, curr.sCnt);

                if (forest[nr][nc] == 'g') next.gCnt++;
                else if (forest[nr][nc] == 's') next.sCnt++;
                else if (forest[nr][nc] == 'F') {
                    bw.write(String.valueOf(next.gCnt) + " " + String.valueOf(next.sCnt));
                    break out;
                }

                if (dp[nr][nc][0] < next.gCnt
                        || (dp[nr][nc][0] == next.gCnt && dp[nr][nc][1] <= next.sCnt)) continue;

                dp[nr][nc][0] = next.gCnt;
                dp[nr][nc][1] = next.sCnt;
                pq.add(next);
            }
        }

		bw.flush();
		bw.close();
		br.close();
	}
}

class Node implements Comparable<Node> {
    int r;
    int c;
    int gCnt;
    int sCnt;

    Node (int r, int c) {
        this.r = r;
        this.c = c;
        this.gCnt = 2500;
        this.sCnt = 2500;
    }

    Node(int r, int c, int gCnt, int sCnt) {
        this.r = r;
        this.c = c;
        this.gCnt = gCnt;
        this.sCnt = sCnt;
    }

    @Override
    public int compareTo(Node o) {
        if (this.gCnt == o.gCnt) return this.sCnt - o.sCnt;
        return this.gCnt - o.gCnt;
    }
}