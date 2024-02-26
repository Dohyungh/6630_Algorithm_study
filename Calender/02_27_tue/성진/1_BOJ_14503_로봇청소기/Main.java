package boj_14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // br로 한 줄 입력받고, st에 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        // st로 N, M 입력받기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // br 한 줄 입력받고, st에 저장
        st = new StringTokenizer(br.readLine());
        
        // (r, c)와 방향 d 입력받기
        int nr = Integer.parseInt(st.nextToken());
        int nc = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

         
        int[][] checkArr = new int[N][M];

        // 전체 방의 정보 입력받기
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                int check = Integer.parseInt(st.nextToken());
                // 벽이라면 -1을 저장
                if (check == 1) {
                	checkArr[r][c] = -1;
                }
                // 빈 공간이라면 0을 저장
                else {
                	checkArr[r][c] = 0;
                }
            }
        }
		
		
        // 북, 동, 남, 서
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        // 출력할 정답
        int answer = 0;

        while (true) {
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소
            if (checkArr[nr][nc] == 0) { 
                checkArr[nr][nc] = 1;
                answer++;
            }
            
            boolean flag = false;
            
            // 현재 방향 저장
            int originalDirection = d;

            // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우를 먼저 확인.
            for (int i = 0; i < 4; i++) {
                // 반시계 방향으로 90도 회전한다.
                d = (d + 3) % 4;
                int nextR = nr + dr[d];
                int nextC = nc + dc[d];

                // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < M && checkArr[nextR][nextC] == 0) {
                    nr = nextR;
                    nc = nextC;
                    flag = true;
                    break;
                }
            }

            // 2.현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
            if (!flag) {
                int backR = nr - dr[originalDirection];
                int backC = nc - dc[originalDirection];

                // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                if (backR >= 0 && backR < N && backC >= 0 && backC < M && checkArr[backR][backC] != -1) {
                    nr = backR;
                    nc = backC;
                }
                // 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                else { 
                    break;
                }
            }
        }

        System.out.println(answer);
        br.close();
    }
}
