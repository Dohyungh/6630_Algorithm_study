#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <sstream>
#include <climits>
#include <queue>
using namespace std;

class City {
    public:
        int num, dist;

        City () : num(-1), dist(INT_MAX) {}
        City (int a, int b) : num(a), dist(b) {}
};

struct compareDist {
    bool operator() (const City& c1, const City& c2) {
        return c1.dist > c2.dist;
    }
};

int main() {

    int n, m;
    cin >> n >> m;
    cin.ignore();

    vector<vector<City> > adj(n+1);
    
    for (int i = 0; i < m; i++) {
        string input;
        getline(cin, input);
        stringstream ss(input);
        int start, end, dist;
        ss >> start >> end >> dist;
        adj[start].push_back(City(end, dist));
    }

    int start, end;
    cin >> start >> end;

    vector<int> dp(n+1);
    fill(dp.begin(), dp.end(), INT_MAX);
    priority_queue<City, vector<City>, compareDist> pq;
    pq.push(City(start, 0));
    dp[start] = 0;

    while(!pq.empty()) {

        City curr = pq.top();
        pq.pop();

        if (curr.num == end) break;

        for (City city : adj[curr.num]) {
            if (dp[city.num] > curr.dist + city.dist) {
                dp[city.num] = curr.dist + city.dist;
                pq.push(City(city.num, dp[city.num]));
            }
        }
    }

    cout << dp[end];

    return 0;
}