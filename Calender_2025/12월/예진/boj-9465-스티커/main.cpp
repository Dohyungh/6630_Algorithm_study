/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 스티커_9465
 * Date: 2024.12.17
 */

#include <iostream>
#include <algorithm>

using namespace std;

int main() {

    int t;
    cin >> t;

    for (int tc = 0; tc < t; tc++) {

        int n;
        cin >> n;

        int sticker[2][n];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                cin >> sticker[i][j];
            }
        }

        int dp[2][n];
        dp[0][0] = sticker[0][0];
        dp[1][0] = sticker[1][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[1][i-1] + sticker[0][i];
            dp[1][i] = dp[0][i-1] + sticker[1][i];

            if (i < 2) continue;
            int tmp = max(dp[0][i-2], dp[1][i-2]);
            dp[0][i] = max(dp[0][i], tmp + sticker[0][i]);
            dp[1][i] = max(dp[1][i], tmp + sticker[1][i]);
        }

        int answer = max(dp[0][n-1], dp[1][n-1]);
        cout << answer << "\n";
    }

    return 0;
}