#include<iostream>
#include<queue>

using namespace std;

struct cmp {
	bool operator()(pair<int, int> a, pair<int, int> b) {
		if (a.second == b.second)
		{
			return a.first > b.first;
		}
		return a.second > b.second;
	}
};

int main() {
	int N;

	cin >> N;

	priority_queue<pair<int, int>, vector<pair<int,int>>, cmp> pq;
	for (int i = 0; i < N; i++) {
		int start, end;

		cin >> start;
		cin >> end;

		pq.push({ start, end });

	}

	int cnt = 1;
	int time = pq.top().second;
	pq.pop();
	while (!pq.empty())
	{
		if (pq.top().first >= time) {
			cnt++;
			time = pq.top().second;
		}
		pq.pop();
	}

	cout << cnt;
	return 0;
	
}