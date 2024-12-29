package boj_1074_Z;

import java.io.*;
import java.util.*;

public class Main {
    public static long findOrder(int N, int r, int c) {
        // 기저 사례: 1x1 크기일 때
        if (N == 0) {
            return 0;
        }
        
        int halfSize = 1 << (N-1); // 2^(N-1)
        
        // 4등분한 사각형 중 어느 사분면에 속하는지 판단
        // 1사분면 (왼쪽 위)
        if (r < halfSize && c < halfSize) {
            return findOrder(N-1, r, c);
        }
        // 2사분면 (오른쪽 위)
        else if (r < halfSize && c >= halfSize) {
            return halfSize * halfSize + findOrder(N-1, r, c - halfSize);
        }
        // 3사분면 (왼쪽 아래)
        else if (r >= halfSize && c < halfSize) {
            return 2 * halfSize * halfSize + findOrder(N-1, r - halfSize, c);
        }
        // 4사분면 (오른쪽 아래)
        else {
            return 3 * halfSize * halfSize + findOrder(N-1, r - halfSize, c - halfSize);
        }
    }
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        long result = findOrder(N, r, c);
        
        System.out.println(result);
    }
}
