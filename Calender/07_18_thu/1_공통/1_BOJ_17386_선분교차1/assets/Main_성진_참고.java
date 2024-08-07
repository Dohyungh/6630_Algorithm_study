package boj_17386_선분교차1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 첫 번째 선분의 좌표 입력
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();

        // 두 번째 선분의 좌표 입력
        int x3 = sc.nextInt();
        int y3 = sc.nextInt();
        int x4 = sc.nextInt();
        int y4 = sc.nextInt();

        // CCW 알고리즘을 이용하여 두 선분의 교차 여부 판단
        if (isIntersect(x1, y1, x2, y2, x3, y3, x4, y4)) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    // CCW (Counter Clock Wise) 함수
    // 벡터의 외적 관련 내용 블로그 https://m.blog.naver.com/ideugu/221411770100
    public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        double result = (double) (x2 - x1) * (y3 - y1) - (double) (y2 - y1) * (x3 - x1);
        if (result > 0) return 1;  // 반시계 방향
        if (result < 0) return -1; // 시계 방향
        return 0;                  // 일직선 상
    }

    // 두 선분의 교차 여부 확인 함수
    public static boolean isIntersect(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int ccw1 = ccw(x1, y1, x2, y2, x3, y3);
        int ccw2 = ccw(x1, y1, x2, y2, x4, y4);
        int ccw3 = ccw(x3, y3, x4, y4, x1, y1);
        int ccw4 = ccw(x3, y3, x4, y4, x2, y2);

        // ccw1, ccw2가 부호가 서로 다른 경우
        // ccw3, ccw4가 부호가 서로 다른 경우
        if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0) {
            return true;
        }
        return false;
    }
}
