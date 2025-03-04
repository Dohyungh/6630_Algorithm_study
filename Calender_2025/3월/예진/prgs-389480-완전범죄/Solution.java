import java.lang.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        
        int cnt = info.length;
        
        // 행 인덱스: 물건의 번호, 열 인덱스: B가 남길 수 있는 흔적의 한계
        // value: A가 남길 수 있는 최소의 흔적
        int[][] dp = new int[cnt][m];
        
        // 모든 물건을 A가 훔치는 경우의 값
        int max = 0;
        for (int i = 0; i < cnt; i++) max += info[i][0];
        
        // 0번 인덱스 물건 초기화
        // B가 물건을 훔칠 수 있는 부분부터 value에서 A의 흔적 값을 지워 최소값 갱신
        for (int j = 0;j < m; j++) {
            if (j < info[0][1]) dp[0][j] = max;
            else dp[0][j] = max - info[0][0];
        }
        
        for (int i = 1; i < cnt; i++) {
            for (int j = 0; j < m; j++) {
                if (j < info[i][1]) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-info[i][1]] - info[i][0]);
            }
        }
        
        // 최종 값이 n 이상일 경우, 가능한 경우가 없다는 뜻
        if (dp[cnt - 1][m - 1] >= n) return -1;
        return dp[cnt - 1][m-1];
    }
}