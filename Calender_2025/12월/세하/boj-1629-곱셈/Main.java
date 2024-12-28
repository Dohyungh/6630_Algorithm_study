package boj_1629_곱셈;

import java.io.*;
import java.util.*;

public class Main {
	public static long power(long A, long B, long C) {
        // B가 0이면 1 반환
        if (B == 0) {
            return 1;
        }
        
        // B가 1이면 A를 C로 나눈 나머지 반환
        if (B == 1) {
            return A % C;
        }
        
        // B를 반으로 나누어 계산
        long half = power(A, B / 2, C);
        
        // B가 짝수인 경우
        if (B % 2 == 0) {
            return (half * half) % C;
        }
        // B가 홀수인 경우
        else {
            return (((half * half) % C) * (A % C)) % C;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long A = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        long C = Integer.parseInt(st.nextToken());
        
        // 결과 계산 및 출력
        long result = power(A, B, C);
        System.out.println(result);
        
    }
}
