/*
 * 시간초과로 실패한 풀이입니다.
 * 계속 해결을 시도하다가 포기했답니다,,,원인과 정답을 아시는 분은 도와주셔요
 */

import java.io.*;
import java.util.*;

public class Main {

    static int[][] garage;
    static int n;
    static int answer;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        // 전체 차고 상태 입력: 인덱스 통일을 위해 좌우만 패딩 넣어서 입력
        garage = new int[3*n][n+2];
        for (int i = 0; i < garage.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                garage[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 메서드 매개변수용 상태 초기화 : 게임용 차고에 1라운드 상태 초기화
        // idx는 차고에서 빈칸이 생기면 다음 라운드를 위해 채울 차의 인덱스 : 이것도 매개변수로 넘기면서 다음 차량을 가리킴
        int[][] tmp = new int[n+2][n+2];
        int[] idx = new int[n+2];
        for (int i = 1; i <= n; i++) {
            idx[i] = (2*n-1);
            for (int j = 1; j <= n; j++) {
                tmp[i][j] = garage[i+idx[i]][j];
            }
        }

        answer = 0;
        simul(tmp, idx, 0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }

    private static void simul(int[][] map, int[] idx, int round, int sum) {

        // 3라운드 다 했으면 점수 갱신 후 리턴
        if (round >= 3) {
            answer = Math.max(answer, sum);
            return;
        }

        // 앞으로 모든 라운드에서 최대값을 얻어도 현재 answer보다 작으면 리턴
        if (sum + ((3-round) * 2*(n*n)) <= answer) return;

        // 시작점을 고르고 bfs 후 재귀
        boolean[][] visited = new boolean[n+2][n+2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                // 이미 다른 시작점을 통해 방문했으면 패스
                if (visited[i][j]) continue;

                // 다음 재귀로 넘기기 위한 매개변수 복사
                // 차고
                int[][] tmp = new int[n+2][];
                for (int k = 0; k < n+2; k++) tmp[k] = Arrays.copyOf(map[k], map[k].length);
                // 다음 채워질 차량 인덱스 상태
                int[] charge = Arrays.copyOf(idx, idx.length);

                // bfs
                int color = tmp[i][j];
                Queue<int[]> queue = new ArrayDeque<>();
                queue.add(new int[] {i, j});
                visited[i][j] = true;

                // 삭제된 차량의 개수를 세기 위한 변수
                int cnt = 0;

                // 직사각형 넓이를 구하기 위한 변수
                int[] max = new int[2];
                int[] min = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};

                while (!queue.isEmpty()) {

                    int[] curr = queue.poll();
                    cnt++;
                    tmp[curr[0]][curr[1]] = 0;
                    max[0] = Math.max(max[0], curr[0]);
                    min[0] = Math.min(min[0], curr[0]);
                    max[1] = Math.max(max[1], curr[1]);
                    min[1] = Math.min(min[1], curr[1]);

                    for (int d = 0; d < 4; d++) {
                        int nr = curr[0] + dr[d];
                        int nc = curr[1] + dc[d];

                        if (!visited[nr][nc] && tmp[nr][nc] == color) {
                            queue.add(new int[]{nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }

                // 다음 라운드가 남았다면 차량 재배치
                if (round < 2) {
                    for (int c = 1; c <= n; c++) {
                        for (int r = n; r > 0; r--) {
                            if (tmp[r][c] == 0) {
                                int next = r;
                                while (tmp[next][c] == 0 && next > 0) next--;
                                if (tmp[next][c] != 0) {
                                    tmp[r][c] = tmp[next][c];
                                    tmp[next][c] = 0;
                                } else {
                                    tmp[r][c] = garage[charge[c]--][c];
                                }
                            }
                        }
                    } 
                }

                // 재배치된 차고 상태, 다음 채울 차량의 인덱스 상태, 라운드, 현재 점수를 넣고 재귀
                int score = sum + (max[0] - min[0] + 1) * (max[1] - min[1] + 1) + cnt;
                simul(tmp, charge, round + 1, score);
            }
        }
    }
}
