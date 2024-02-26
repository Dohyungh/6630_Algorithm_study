package boj_14503_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        int nr = Integer.parseInt(st.nextToken());
        int nc = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int[][] checkArr = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                int check = Integer.parseInt(st.nextToken());
                checkArr[r][c] = check;
                if (check == 1) {
                	checkArr[r][c] = -1;
                }
                else {
                	checkArr[r][c] = 0;
                }
            }
        }
		
		
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};
		
		int answer = 0;
		
		while (true) {
			if (checkArr[nr][nc] == 0) {
				checkArr[nr][nc] = 1;
				answer++;
			}
			
//			boolean flag = true;
//			
//			for (int i=)
			
			
			
			
			// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
			if (((nr-1 >= 0) && (checkArr[nr-1][nc] != 0)) &&
					((nc-1 >= 0) && (checkArr[nr][nc-1] != 0)) &&
					((nr+1 < N) && (checkArr[nr+1][nc] != 0)) &&
					((nc+1 < M) && (checkArr[nr][nc+1] != 0))) {
				// 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
				if ((nr-dr[d] >= 0) && (nr-dr[d] < N) && (nc-dc[d] >= 0) && (nc-dc[d] < M) && (checkArr[nr-dr[d]][nc-dc[d]] != -1)) {
					nr -= dr[d];
					nc -= dc[d];
				}
				// 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
				else {
					break;
				}
			}
			// 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
			else {
				for (int i=0; i<4; i++) {
					// 반시계 방향으로 90도 회전한다.
					d = (d-1 + 4) % 4;
					if ((nr+dr[d] >= 0) && (nr+dr[d] < N) && (nc+dc[d] >= 0) && (nc+dc[d] < N) && (checkArr[nr+dr[d]][nc+dc[d]] == 0)) {
						// 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
						nr += dr[d];
						nc += dc[d];
						break;
					}
				}
				
			}
			
		}
		

				
		System.out.println(answer); 	
				
		br.close();
	}

}
