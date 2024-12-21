/**
 * Author: yngbao97, Yuk Yejin
 * Problem: LCS_9251
 * Date: 2024.12.20
 */

#include <iostream>
#include <string>
#include <algorithm>
#include <vector>
using namespace std;

int main() {

    string a, b;
    cin >> a >> b;

    vector<char> first(a.begin(), a.end());
    vector<char> second(b.begin(), b.end());

    vector<vector<int> > dp(first.size() + 1, vector<int>(second.size() + 1));

    for (int i = 1; i <= first.size(); i++) {
        for (int j = 1; j <= second.size(); j++) {
            if (first[i-1] == second[j-1]) dp[i][j] = dp[i-1][j-1] + 1;
            else dp[i][j] = max(dp[i-1][j], dp[i][j-1]);
        }
    }

    cout << dp[first.size()][second.size()] << "\n";

    return 0;
}