/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 숨바꼭질 3_13549
 * Date: 2024.12.20
 */

#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <climits>
#include <queue>
using namespace std;

struct compareCount {
    bool operator() (const pair<int, int>& p1, const pair<int, int>& p2) {
        return p1.second > p2.second;
    }
};

int main() {

    int start, end;
    cin >> start >> end;

    vector<int> dp(150001);
    fill(dp.begin(), dp.end(), INT_MAX);
    priority_queue<pair<int, int>, vector<pair<int, int> >, compareCount> pq;
    pq.push(make_pair(start, 0));
    dp[start] = 0;

    while (!pq.empty()) {

        pair<int, int> curr = pq.top();
        pq.pop();

        if (curr.first == end) break;

        if (curr.first > 0 && dp[curr.first - 1] > curr.second + 1) {
            dp[curr.first - 1] = curr.second + 1;
            pq.push(make_pair(curr.first - 1, dp[curr.first - 1]));
        }

        if (curr.first < 150000 && dp[curr.first + 1] > curr.second + 1) {
            dp[curr.first + 1] = curr.second + 1;
            pq.push(make_pair(curr.first + 1, dp[curr.first + 1]));
        }

        if (curr.first < 75000 && dp[curr.first * 2] > curr.second) {
            dp[curr.first * 2] = curr.second;
            pq.push(make_pair(curr.first * 2, dp[curr.first * 2]));
        }
    }

    cout << dp[end];

    return 0;
}