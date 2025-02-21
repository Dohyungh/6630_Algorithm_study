class Solution {
    public int solution(int n, int[] tops) {
        final int MOD = 10_007;
        
        int[][] dp = new int[2][n+1];
        dp[0][0] = dp[1][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            if (tops[i-1] == 0) {
                dp[0][i] = ((dp[0][i-1] * 2) + dp[1][i-1]) % MOD;
                dp[1][i] = dp[0][i-1] + dp[1][i-1] % MOD;
            }
            else {
                dp[0][i] = ((dp[0][i-1] * 3) + dp[1][i-1]) % MOD;
                dp[1][i] = (dp[0][i-1] * 2) + dp[1][i-1] % MOD;
            }
        }
        
        return dp[0][n];
    }
}