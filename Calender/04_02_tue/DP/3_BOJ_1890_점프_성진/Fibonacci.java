package boj_1890_점프;

public class Fibonacci {
	 
    static long[] memo;

    public static long fibo(int n) {
        if (n <= 1) {
            return n;
        }
        else if (memo[n] != 0) {
            return memo[n];
        }
        else
            return memo[n] = fibo(n - 1) + fibo(n - 2);
 
    }
    
    public static void main(String[] args) {
        memo = new long[101];
        System.out.println(fibo(100));
    }
}
