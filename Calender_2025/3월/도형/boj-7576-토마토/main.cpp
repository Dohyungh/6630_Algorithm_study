#include<iostream>
#include<queue>
#include<vector>
using namespace std;

int main() {

	int M, N;

	cin >> M;
	cin >> N;

	vector < vector<int>> map(N, vector<int>(M,0));

	queue<pair<int, int>> q;

	int cnt = 0;

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < M; j++)
		{
			cin >> map[i][j];

			if (map[i][j] == 1)
			{
				q.push({ i,j });
				
				
			}

			if (map[i][j] == 0)
			{
				cnt++;
			}
		}
	}


	int dr[4] = {-1, 1, 0, 0};
	int dc[4] = {0,0,-1,1};

	int changed = 0;
	int answer = -1;
	while (!q.empty())
	{
		int sz = q.size();
		//cout << "sz: " << sz << endl;
		for (int i = 0; i < sz; i++)
		{
			pair<int, int> curr = q.front();
			q.pop();


			for (int d = 0; d < 4; d++)
			{

				int nr = curr.first + dr[d];
				int nc = curr.second + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

				if (map[nr][nc] == 0)
				{
					q.push({ nr,nc });
					map[nr][nc] = 1;
					changed++;
				}

			}

		}
		answer++;

	}

	if (changed == cnt)
	{
		cout << answer;

	}
	else {
		cout << -1;
	}




}