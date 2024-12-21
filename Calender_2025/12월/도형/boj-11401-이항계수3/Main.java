/*
 * 모르면 못 푸는 문제라 권장하지 않음.
 * 
 * 조합을 계산하는게 문제인데, 정확히는 조합의 값을 직접 구하는게 아니라 나머지를 구해야 한다는게 다른 점이었다.
 * 
 * 나머지를 구하는 목적이 부여되면서 
 * 페르마 소정리 (나눗셈 식의 나머지를 구하는 방법)
 * 라는 개념을 알아야만 풀 수 있는 문제가 되었다.
 * 
 * 그 이후 지수가 굉장히 큰 거듭제곱을 구하는 문제에 부딪히게 되는데,
 * 이때 2로 나누어 가면서 log 스케일 안에서 거듭제곱 값을 구하면 된다.
 * 
 * 이 두개의 트릭을 구현하기만 하면 통과한다.
 * 
 * 이론이 어렵지 코드는 쉽다.
 * 
 */

import java.util.Scanner;

public class Main {
    static int x = 1_000_000_007;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        // N! / (K! * (N-K)!)
        // 페르마 소정리에 의해
        // N! * (K! * (N-K)!)^10-05 를 10-07로 나눈 나머지를 구하면 된다.

        if (N >= x || K >= x) {
            System.out.println(0);
            return;
        }

        long N_factorial = getFactorial(N);

        long K_factorial = getFactorial(K);
        long N_K_factorial = getFactorial(N-K);

        long power = getPower(K_factorial * N_K_factorial % x, x-2);

        System.out.println(N_factorial * power % x);



    }

    public static long getFactorial(int num) {
        long factorial = 1L;
        while(num > 1) {
            factorial *= num;
            factorial %= x;
            num--;
        }
        return factorial;
    }

    public static long getPower(long num, int pow) {
//        System.out.println(pow);
        if (pow == 0) return 1L;
        long temp = getPower(num, pow/2) % x;
        if (pow % 2 == 1) {
            return temp * temp % x * num % x;
        } else {
            return temp * temp % x;
        }

    }
}