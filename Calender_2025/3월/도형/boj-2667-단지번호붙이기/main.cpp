#include<iostream>
#include<vector>
#include<string>
#include<queue>
#include<algorithm>
using namespace std;

int main() {
	int N;
	cin >> N;

	vector<vector<bool>> isBuild(N, vector<bool>(N));

	for (int i = 0; i < N; i++)
	{
		string str;

		cin >> str;
		
		for (int j = 0; j < N; j++)
		{
			if (str[j] == '0')
			{
				isBuild[i][j] = false;
			}
			else
			{
				isBuild[i][j] = true;
			}
		}

	}

	vector<vector<bool>> visited(N, vector<bool>(N));

	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			visited[i][j] = false;
		}
	}

	int dr[4] = {-1, 1, 0, 0};
	int dc[4] = { 0, 0, -1, 1 };
	vector<int> answers;
	for (int i = 0; i < N; i++)
	{
		for (int j = 0; j < N; j++)
		{
			if (visited[i][j]) continue;

			if (isBuild[i][j])
			{
				queue<pair<int, int>> q;

				q.push({ i,j });
				int cnt = 0;
				visited[i][j] = true;
				while (!q.empty())
				{
					pair<int, int> curr = q.front();
					q.pop();
					cnt++;

					int r = curr.first;
					int c = curr.second;

					
					for (int d = 0; d < 4; d++)
					{
						
						int nr = r + dr[d];
						
						int nc = c + dc[d];


						if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
						if (visited[nr][nc]) continue;
						visited[nr][nc] = true;

						if (isBuild[nr][nc]) {
							q.push({ nr,nc });
						}

					}
				}

				answers.push_back(cnt);
			}

			visited[i][j] = true;
		}
	}

	sort(answers.begin(), answers.end() );


	cout << answers.size() << endl;
	for (int i = 0; i < answers.size(); i++)
	{
		cout << answers[i] << endl;
	}
	return 0;
}