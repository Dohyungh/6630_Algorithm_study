package day_23.p2;

import java.io.*;
import java.util.*;

public class Main {

    private static final StringBuilder sb = new StringBuilder();

    private static final int[][] map = new int[9][9];
    private static final boolean[][][] visited = new boolean[9][9][1 + 9];
    private static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 0) continue;

                for (int k = 0; k < 9; k++) {
                    visited[k][j][map[i][j]] = true;
                    visited[i][k][map[i][j]] = true;
                    visited[i / 3 * 3 + k / 3][j / 3 * 3 + k % 3][map[i][j]] = true;
                }
            }
        }

        dfs(0, 0);

        bw.write(sb.toString());
        bw.flush();
    }

    private static void dfs(int r, int c) {
        if (flag) return;

        if (r == 9) {
            flag = true;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            return;
        }

        if (map[r][c] != 0) {
            if (c == 8) dfs(r + 1, 0);
            else dfs(r, c + 1);
        } else {
            for (int i = 1; i <= 9; i++) {
                if (visited[r][c][i]) continue;

                map[r][c] = i;
                for (int k = 0; k < 9; k++) {
                    visited[k][c][map[r][c]] = true;
                    visited[r][k][map[r][c]] = true;
                    visited[r / 3 * 3 + k / 3][c / 3 * 3 + k % 3][map[r][c]] = true;
                }

                if (c == 8) dfs(r + 1, 0);
                else dfs(r, c + 1);

                for (int k = 0; k < 9; k++) {
                    visited[k][c][map[r][c]] = false;
                    visited[r][k][map[r][c]] = false;
                    visited[r / 3 * 3 + k / 3][c / 3 * 3 + k % 3][map[r][c]] = false;
                }
                map[r][c] = 0;
            }
        }
    }

}
