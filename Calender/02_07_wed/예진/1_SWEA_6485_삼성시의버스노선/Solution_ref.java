package 삼성시의버스노선_6485_240205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution_ref {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        for (int t = 1; t <= T; ++t) {
        	
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];
            int[] B = new int[N];
            
            // 각 버스노선의 범위(처음과끝)를 배열로 저장
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                A[i] = Integer.parseInt(st.nextToken());
                B[i] = Integer.parseInt(st.nextToken());
            }
            
            int P = Integer.parseInt(br.readLine());		// 버스정류장 개수
            int[] C = new int[P];							// 확인하려는 버스정류장의 번호
            int[] ans = new int[P];							// 확인하려는 버스정류장의 노선 개수 누적
            for (int i = 0; i < P; ++i) {
                C[i] = Integer.parseInt(br.readLine());
                for(int j = 0; j < N; ++j) {				// 제시된 버스노선만큼
                    if(A[j] <= C[i] && C[i] <= B[j]) {		// 버스정류장의 번호와 노선의 범위를 비교하여
                        ans[i]++;							// 해당하는 버스정류장의 노선 개수를 카운트
                    }
                }
            }
            sb.append("#").append(t).append(" ");
            for (int i = 0; i < P; ++i) {
                sb.append(ans[i]).append(" ");				// 제시된 버스정류장의 각 노선 개수를 순서대로 출력
            }
            System.out.println(sb);
        }
    }
}