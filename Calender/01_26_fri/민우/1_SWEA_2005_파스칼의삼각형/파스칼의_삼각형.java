package Study_for_2024_01_26.problem1;

import java.util.Scanner;

public class 파스칼의_삼각형 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();    // 테스트 케이스의 수

        for(int i=0 ; i<T ; i++){                 // 테스트 케이스만큼 반복
            int N = sc.nextInt();                 // 파스칼 삼각형의 크기
            int[][] pascalArr = new int[N][N];    // 파스칼 삼각형 담을 배열
            pascalArr[0][0] = 1;                  // 첫 줄은 인덱스땜에 밖으로 빼놨음

            for(int r=1 ; r<N ; r++){         // 두번째 줄부터 순회
                for(int c=1 ; c<=r ; c++){    // c<=r 까지만 확인하는게 포인트!!!
                    pascalArr[r][0] = 1;      // 맨 앞 숫자는 1 고정
                    pascalArr[r][c] = pascalArr[r-1][c-1] + pascalArr[r-1][c];    // 파스칼 삼각형 핵심로직
                }
            }

            System.out.printf("#%d\n", i+1);    // 그냥 출력 -> i를 그냥 1부터 했으면 깔끔했을 듯
            for(int r=0 ; r<N ; r++){
                for(int c=0 ; c<=r ; c++){
                    System.out.printf("%d ", pascalArr[r][c]);
                }
                System.out.println();
            }
        }
    }
}
