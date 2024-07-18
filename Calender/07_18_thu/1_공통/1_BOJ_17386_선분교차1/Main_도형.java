package BOJ._17386_선분교차1;

import java.util.Scanner;

public class Main {
    static long a;
    static long b;
    static long c;
    static long d;

    static long i;
    static long j;
    static long k;
    static long l;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        a = sc.nextLong();
        b = sc.nextLong();
        c = sc.nextLong();
        d = sc.nextLong();

        i = sc.nextLong();
        j = sc.nextLong();
        k = sc.nextLong();
        l = sc.nextLong();


        System.out.println(getAnswer());
    }

    private static int getAnswer(){
        long left = (l-j)*(c-a) - (d-b)*(k-i);
        long alpha_right = (l-j)*(i-a) - (j-b)*(k-i);
        long beta_right = (d-b)*(i-a) - (j-b)*(c-a) ;

        // 부호 동일
        if (Long.signum(left) * Long.signum(alpha_right) < 0) {
            return 0;
        }

        // 절댓값 좌가 더 작으면 0
        if (Math.abs(left) <= Math.abs(alpha_right)) {
            return 0;
        }

        // 부호 동일
        if (Long.signum(left) * Long.signum(beta_right) < 0) {
            return 0;
        }

        // 절댓값 좌가 더 작으면 0
        if (Math.abs(left) <= Math.abs(beta_right)) {
            return 0;
        }
        return 1;


    }

}
