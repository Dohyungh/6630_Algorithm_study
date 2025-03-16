#include<iostream>
#include<algorithm>
#include<queue>


using namespace std;

struct Node {
	int idx;
	int cost;

	Node(int idx, int cost) : idx(idx), cost(cost) {}

	bool operator<(const Node& other) const {
		return cost > other.cost;
	}
};

int main() {
	int N, M, start, end;

	cin >> N;
	cin >> M;

	vector<vector<pair<int, int>>> adj(N+1);

	for (int i = 0; i < M; i++)
	{
		int from, to, val;

		cin >> from;
		cin >> to;
		cin >> val;

		adj[from].push_back({ to, val });
	}

	priority_queue<Node> pq;


	int* dist = new int[N+1];

	for (int i = 0; i < N + 1; i++)
	{
		dist[i] = 987654321;
	}

	cin >> start;
	cin >> end;

	pq.push(Node(start, 0));
	dist[start] = 0;
	while (!pq.empty())
	{
		Node curr = pq.top();

		pq.pop();
		if (dist[curr.idx] < curr.cost) continue;


		for (int i = 0; i < adj[curr.idx].size(); i++)
		{
			int to = adj[curr.idx][i].first;
			int val = adj[curr.idx][i].second;

			if (curr.cost + val < dist[to])
			{
				dist[to] = curr.cost + val;
				pq.push(Node(to, dist[to]));
			}
		}
	}

	cout << dist[end];


	return 0;
}