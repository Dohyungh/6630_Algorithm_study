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

        double l1;  // 첫 번째 선분의 기울기
        double l2;  // 두 번째 선분의 기울기

        // 첫 번째 선분의 기울기 계산
        if (x1 == x2) {
            l1 = Double.MAX_VALUE;  // 수직선(y축과 평행)의 경우 기울기를 double형의 최댓값으로 설정
        } else {
            l1 = (double) (y2 - y1) / (x2 - x1);
        }

        // 두 번째 선분의 기울기 계산
        if (x3 == x4) {
            l2 = Double.MAX_VALUE;  // 수직선(y축과 평행)의 경우 기울기를 double형의 최댓값으로 설정
        } else {
            l2 = (double) (y4 - y3) / (x4 - x3);
        }

        // 두 선분의 기울기가 같은 경우(평행한 경우)
        if (l1 == l2) {
            System.out.println(0);
            return;
        }

        // 첫 번째 선분의 y절편 계산
        double k1 = y1 - l1 * x1;
        // 두 번째 선분의 y절편 계산
        double k2 = y3 - l2 * x3;


        // y = lx + k라는 직선의 방정식 생성 완료


        double x, y;  // 두 선분의 교차점 좌표

        // 첫 번째 선분이 수직선인 경우
        if (l1 == Double.MAX_VALUE) {
            x = x1;
            y = l2 * x + k2;
        }
        // 두 번째 선분이 수직선인 경우
        else if (l2 == Double.MAX_VALUE) {
            x = x3;
            y = l1 * x + k1;
        }
        // 나머지 경우 두 선분의 교차점 계산
        else {
            x = (k2 - k1) / (l1 - l2);
            y = l1 * x + k1;
        }

        // 첫 번째 선분의 x와 y 범위 계산
        int sx1 = Math.min(x1, x2);
        int lx1 = Math.max(x1, x2);
        int sy1 = Math.min(y1, y2);
        int ly1 = Math.max(y1, y2);

        // 두 번째 선분의 x와 y 범위 계산
        int sx2 = Math.min(x3, x4);
        int lx2 = Math.max(x3, x4);
        int sy2 = Math.min(y3, y4);
        int ly2 = Math.max(y3, y4);

        // 교차점이 두 선분의 범위 내에 있는지 확인
        if (x < sx1 || y < sy1 || x > lx1 || y > ly1 || x < sx2 || y < sy2 || x > lx2 || y > ly2) {
            System.out.println(0);  // 교차점이 범위 내에 없으면 교차하지 않음
        } else {
            System.out.println(1);  // 교차점이 범위 내에 있으면 교차함
        }
    }
}
