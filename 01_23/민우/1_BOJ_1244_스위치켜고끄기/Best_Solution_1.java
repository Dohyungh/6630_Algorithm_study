package Study_for_2024_01_23.problem1;

import java.util.Scanner;

public class Best_Solution_1 {
    static int[][] snail;                   // static 변수 왜 한거지......? 그냥 메인 안에서 새로 만들어도 되지 않나

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();               // Test case 수 입력 받은 듯
        for(int tc=1; tc<=T; tc++) {        // Test case 수 만큼 for문 돌린 듯
            int N = sc.nextInt();           // 각 Test case의 크기 받은 듯
            snail = new int[N][N];          // 달팽이 배열 크기 설정

            int dir = 1, num = 1;           // dir은 진행 방향 용 변수 / num은 첫 칸이 1이라 1로 한 듯
            int x = 0, y = -1;              // x는 행, y는 열 인덱스인데 첫 칸에 바로 넣으려고 y = -1로 한 듯
            while(N>0) {                    // 크기가 N이면 N바퀴 돌고 끝나는거 노렸네
                for(int i=0; i<N; i++) {    // dir이 +1이면 우로 쭉 직진하면서 채우고, -1이면 좌로 쭉 직진하면서 채우네
                    y += dir;
                    snail[x][y] = num++;
                }
                N--;                        // 상하로 채울 때 딱 한 칸 덜가는거 이때 빼는거 지리네
                for(int i=0; i<N; i++) {    // dir이 +1이면 아래로 쭉 직진하면서 채우고, -1이면 위로 쭉 직진하면서 채우네
                    x += dir;
                    snail[x][y] = num++;
                }
                dir *= -1;                  // 미뗬다
            }
            System.out.println("#"+tc);     // 근데 이거는 엔터 치는 순간 바로 출력되는데 왜 통과임? 진짜모름
            for(int[] aa:snail) {
                for(int a:aa) System.out.print(a+" ");
                System.out.println();
            }
        }
    }
}