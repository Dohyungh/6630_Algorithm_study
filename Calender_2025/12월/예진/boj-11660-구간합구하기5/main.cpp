/**
 * Author: yngbao97, Yuk Yejin
 * Problem: 구간 합 구하기 5_11660
 * Date: 2024.12.18
 */

#include <iostream>
#include <algorithm>
#include <sstream>
#include <string>
#include <vector>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, m;
    cin >> n >> m;
    cin.ignore();

    vector<vector<int>> arr(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        string input;
        getline(cin, input);
        stringstream ss(input);
        for (int j = 0; j < n; j++) {
            ss >> arr[i][j];
        }
    }

    vector<vector<int>> prefixSum(n, vector<int>(n));
    prefixSum[0][0] = arr[0][0];
    for (int i = 1; i < n; i++) {
        prefixSum[i][0] = prefixSum[i-1][0] + arr[i][0];
        prefixSum[0][i] = prefixSum[0][i-1] + arr[0][i];
    }

    for (int i = 1; i < n; i++) {
        for (int j = 1; j < n; j++) {
            prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + arr[i][j];
        }
    }

    for (int i = 0; i < m; i++) {

        string input;
        getline(cin, input);
        stringstream ss(input);
        int stX, stY, endX, endY;
        ss >> stX >> stY >> endX >> endY;

        int answer = prefixSum[endX-1][endY-1];
        if (stX > 1) answer -= prefixSum[stX-2][endY-1];
        if (stY > 1) answer -= prefixSum[endX-1][stY-2];
        if (stX > 1 && stY > 1) answer += prefixSum[stX-2][stY-2];

        cout << answer << "\n";
    }

    return 0;
}