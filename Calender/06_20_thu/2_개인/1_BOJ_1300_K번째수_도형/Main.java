package BOJ._1300_K번째수;

import java.util.Scanner;

public class Main {
    // 2차원 배열의 한 변의 길이
    static long N;

    // 구해야 하는 수가 몇 번째 수 인지
    static long K;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 입력은 단 2개
        N = sc.nextInt();
        K = sc.nextInt();

        // 최솟값
        long left = 1;

        // 최댓값
        long right = N*N;

        // 이분탐색 - 매개변수 탐색 (Parametric Search)
        // 본 문제에서 매개변수(이분탐색의 대상)는 배열에 들어갈(혹은 들어있는) 원소임
        // 즉, 임의의 수 alpha가 주어졌을 때,
        // 그 수가 몇번째인지(좀 더 정확히는 그 수보다 작은 수가 배열에 몇개 존재하는지)를 구한 다음,
        // 그 값이 주어진 K보다 작거나 큰지를 보고
        // 왼쪽 혹은 오른쪽으로 탐색해나간다.

        long mid = (left + right) / 2;

        while(left < right) {

            long loc = getLoc(mid);

            // lowerbound 이분 탐색
            // 이라는데,, 아직 구분이 쉽지는 않다.
            // 더 공부하면서 이해해봐야 할 듯.
            if (loc < K) {
                left = mid + 1;
            } else {
                right = mid;
            }

            mid = (left + right) / 2;

        }

        System.out.println(right);
    }

    // alpha는 몇 번째 수인가?에 대한 답
    // i: 1, j: 1   ~   i: 1, j: min(alpha/1, N)
    // 2, 1         ~      2, min(alpha/2, N)
    // ...
    // min(alpha, N), 1     ~   1, min(alpha/min(alpha,N), N)

    static long getLoc(long alpha) {
        long answer = 0;
        long limit = Math.min(alpha, N);

        for (long i = 1; i <= limit; i++) {
            answer += Math.min(alpha/i,N);
        }

        return answer;
    }
}
