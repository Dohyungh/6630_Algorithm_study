#include<iostream>

using namespace std;

int main() {

	int N, M;

	cin >> N;

	cin >> M;

	int** arr = new int* [N];
	int** result = new int* [N];
	
	for (int i = 0; i < N; i++) {
		arr[i] = new int [M];
		result[i] = new int[M];
	}

	for (int i = 0; i < N; i++) {
		string line;
		cin >> line;
		for (int j = 0; j < M; j++) {
			arr[i][j] = line[j] - '0';
		}
	}10

	for (int i = 0; i < N; i++) {
		string line;
		cin >> line;
		for (int j = 0; j < M; j++) {
			result[i][j] = line[j] - '0';
		}
	}

	int cnt = 0;
	for (int i = 0; i < N - 2; i++) {
		for (int j = 0; j < M - 2; j++) {
			if (arr[i][j] != result[i][j]) {
				for (int a = 0; a < 3; a++) {
					for (int b = 0; b < 3; b++) {
						arr[i + a][j + b] = 1 - arr[i+a][j+b];
					}
				}
				cnt++;
			}
		}
	}

	bool found = true;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			if (arr[i][j] != result[i][j]) found = false;
		}
	}
	//cout << cnt << endl;

	if (found) cout << cnt;
	if (!found) cout << -1;


	return 0;
}