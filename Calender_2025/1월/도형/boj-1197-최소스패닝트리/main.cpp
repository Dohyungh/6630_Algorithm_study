#include<iostream>
#include<vector>
#include<queue>
#include<functional>

using namespace std;

int V, E;

struct Edge {
	int next;
	int cost;

	bool operator<(const Edge& other) const {
		return cost > other.cost;
	}
};

int main() {
	cin >> V >> E;

	vector<vector<Edge>> adj(V+1);

	for (int i = 0; i < E; i++)
	{
		int no, de, cost;

		cin >> no >> de >> cost;

		adj[no].push_back({ de, cost });
		adj[de].push_back({ no, cost });
	}

	bool* visited = new bool[V + 1]();

	visited[1] = true;

	priority_queue<Edge> pq;

	for (int i = 0; i < adj[1].size(); i++)
	{
		if (!visited[adj[1].at(i).next])
		{
			pq.push(adj[1].at(i));
		}
	}
	long answer = 0L;
	while (!pq.empty())
	{
		Edge curr = pq.top();
		pq.pop();
		if (!visited[curr.next])
		{
			visited[curr.next] = true;
			answer += curr.cost;

			for (int i = 0; i < adj[curr.next].size(); i++)
			{
				if (!visited[adj[curr.next].at(i).next])
				{
					pq.push(adj[curr.next].at(i));
				}
			}
		}

	}
	cout << answer;

	return 0;

}