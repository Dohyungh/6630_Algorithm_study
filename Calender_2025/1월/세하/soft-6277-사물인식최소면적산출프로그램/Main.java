package soft_6277_사물인식최소면적산출프로그램;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K, min;
    static int[][] points, sel;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        points = new int[N][3];
        sel = new int[K][3];
        visited = new boolean[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 1; j < 3; j++) {
                points[i][j] = Integer.parseInt(st.nextToken()); // x, y 좌표 저장 
            }

            points[i][0] = Integer.parseInt(st.nextToken()); // 편의상 색깔을 0번째 열에 저장 
        }

        min = 987654321;
        
        // select 함수 호출
        // 초기 x, y 좌표의 최소/최댓값은 좌표 범위 +- 1 
        select(0, 1, 1001, 1001, -1001, -1001);
        
        System.out.println(min);
    }

    public static void select(int s_idx, int k, int x_min , int y_min, int x_max, int y_max) {
        if(s_idx >= K) {
            min = Math.min(min, (x_max - x_min) * (y_max - y_min));
            return;
        }
        
        for(int i = 0; i < N; i++) {
            if(points[i][0] == k) {
            	// 현재까지 저장된 x, y 좌표의 최소/최댓값과 새로 고르는 점의 x, y 좌표를 비교하여 임시로 갱신 
                int x_min_new = Math.min(x_min, points[i][1]);
                int y_min_new = Math.min(y_min, points[i][2]);
                int x_max_new = Math.max(x_max, points[i][1]);
                int y_max_new = Math.max(y_max, points[i][2]);
                
                // 갱신한 x, y 좌표로 넓이 계산 
                int tmp = (x_max_new - x_min_new) * (y_max_new - y_min_new);
                
                // 계산한 넓이가 저장된 최소 넓이보다 작을 경우에만 색깔 고르기 
                if(tmp < min) {      
                    for(int c = 0; c < 3; c++) {
                        sel[s_idx][c] = points[i][c];
                    }
                    
                    select(s_idx + 1, k + 1, x_min_new, y_min_new, x_max_new, y_max_new);
                }
            }
        }           
    }
}

