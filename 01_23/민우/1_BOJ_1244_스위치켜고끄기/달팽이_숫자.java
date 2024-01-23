package Study_for_2024_01_23.problem1;    // 제출할 때 package 지워야 함

import java.util.Scanner;

public class 달팽이_숫자 {    // 이름 Solution으로 바꾸고 제출 -> Pass
    public static void main(String[] args) {
        /* [IM-] SWEA 1954.달팽이 숫자 [D2] */
        /* 메모리 - 21,676kb */
        /* 실행시간 - 166ms */
        /* 코드길이 - 2,372 */
        /* 3시간 6분 걸림 */

        Scanner sc = new Scanner(System.in);

        // 1. 테스트 케이스와 각 테스트 케이스 입력 받아보자
        int N = sc.nextInt();    // 테스트 케이스의 개수 입력

        int[] iArr = new int[N];    // 달팽이 숫자의 크기 저장 배열
        for(int idx=0 ; idx<N ; idx++){
            iArr[idx] = sc.nextInt();
            while(iArr[idx]<1 || iArr[idx]>10) {
                iArr[idx] = sc.nextInt();
            }
        }

        int[][][] snailArr = new int[N][][];    // 달팽이 숫자 저장 배열[순서][행][열] -> 후 졸라 빡세네
        for(int idx=0 ; idx<N ; idx++){
            snailArr[idx] = new int[iArr[idx]][iArr[idx]];
        }



        // 2. 달팽이 숫자를 2차원 배열에 채워보자 -> 진행 가능한 곳까지 계속 직진
        for(int idx=0 ; idx<N ; idx++){
            int n = 1;
            int r = 0;
            int c = 0;
            snailArr[idx][r][c] = n;
            while(n < iArr[idx]*iArr[idx]){
                while (c != iArr[idx]-1 && snailArr[idx][r][c+1] == 0) {    // 왼쪽 -> 오른쪽 으로 진행이 가능
                    c += 1;
                    n += 1;
                    snailArr[idx][r][c] = n;
                }
                while (r != iArr[idx]-1 && snailArr[idx][r+1][c] == 0) {    // 위쪽 -> 아래쪽 으로 진행이 가능
                    r += 1;
                    n += 1;
                    snailArr[idx][r][c] = n;
                }
                while (c != 0 && snailArr[idx][r][c-1] == 0) {    // 오른쪽 -> 왼쪽 으로 진행이 가능
                    c -= 1;
                    n += 1;
                    snailArr[idx][r][c] = n;
                }
                while (r != 0 && snailArr[idx][r-1][c] == 0) {    // 아래쪽 -> 위쪽 으로 진행이 가능
                    r -= 1;
                    n += 1;
                    snailArr[idx][r][c] = n;
                }
            }
        }



        // 3. 달팽이 숫자를 출력해보자
        for(int idx=0 ; idx<N ; idx++){
            System.out.printf("#%d%n", idx+1);
            for(int r=0 ; r<iArr[idx] ; r++){
                for(int c=0 ; c<iArr[idx] ; c++){
                    System.out.printf("%d ", snailArr[idx][r][c]);
                }
                System.out.println();
            }
        }



    }
}
