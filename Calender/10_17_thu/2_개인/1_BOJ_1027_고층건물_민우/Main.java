package day_15.BOJ_G4_1027;

import java.io.*;
import java.util.*;

public class Main {

    // 고층 빌딩의 지붕의 좌표를 나타내기 위한 클래스
    // ccw에서 int형 오버플로우 방지용으로 long 타입으로 만듦
    static class Point {
        long x;
        long y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        // 1. 빌딩 지붕의 좌표를 배열로 저장
        Point[] buildings = new Point[1 + N];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            buildings[i] = new Point(i, Integer.parseInt(st.nextToken()));
        }

        // 2. 해당 빌딩에서 보이는 빌딩의 수를 세는 카운팅 배열
        int[] cntArr = new int[1 + N];

        // 3. 2중 for문을 돌며 볼 수 있는 빌딩인지 계산
        for (int i = 1; i < N; i++) {
            Point curr = new Point(i + 1, 0);

            // 보이는 빌딩이면 기준점을 갱신해주고 카운팅
            for (int j = i + 1; j <= N; j++) {
                if (!isIntersection(buildings[i], curr, buildings[j])) continue;

                curr = buildings[j];
                cntArr[i]++;
                cntArr[j]++;
            }
        }

        // 4. 가장 많이 볼 수 있는 빌딩의 수를 찾아서 출력
        int answer = 0;
        for (int cnt : cntArr) answer = Math.max(answer, cnt);
        System.out.println(answer);
    }

    // 볼 수 있는 빌딩인지 판단하는 메서드로 외적 벡터의 방향이 양의 값이면 보이고, 0이면 접하고, 음의 값이면 가려진 상태
    private static boolean isIntersection(Point p1, Point p2, Point p3) {
        return ccw(p1, p2, p3) > 0;
    }

    // 세 점의 좌표로 ccw 돌려서 외적 값을 반환
    private static long ccw(Point p1, Point p2, Point p3) {
        return (p2.x * p3.y - p3.x * p2.y) + (p3.x * p1.y - p1.x * p3.y) + (p1.x * p2.y - p2.x * p1.y);
    }

}
