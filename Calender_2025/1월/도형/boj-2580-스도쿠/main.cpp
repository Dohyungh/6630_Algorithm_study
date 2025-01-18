#include<iostream>

using namespace std;

int arr[9][9];
bool answered = false;

void solve(int idx) {
	if (idx == 81) {
		answered = true;
		return;
	}
	int row = idx / 9;
	int col = idx % 9;

	if (arr[row][col] != 0) {
		solve(idx + 1);
		return;
	}
	if (answered) return;

	bool ok = true;
	for (int i = 1; i <= 9; i++) {
		if (answered) return;

		ok = true;

		for (int j = 0; j < 9; j++) {
			if (j == col) continue;
			if (arr[row][j] == i) {
				ok = false;
				break;
			}
		}

		for (int j = 0; j < 9; j++) {
			if (j == row) continue;
			if (arr[j][col] == i) {
				ok = false;
				break;
			}
		}

		int boxRow = (row / 3) * 3;
		int boxCol = (col / 3) * 3;

		for (int j = boxRow; j < boxRow + 3; j++) {
			for (int k = boxCol; k < boxCol + 3; k++) {
				if (j == row && k == col) continue;
				if (arr[j][k] == i) {
					ok = false;
					break;
				}
			}
		}

		if (ok) {
			arr[row][col] = i;
			solve(idx + 1);
			if (!answered) arr[row][col] = 0;
		}
	}


}

int main() {
	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			cin >> arr[i][j];
		}
	}

	solve(0);

	for (int i = 0; i < 9; i++) {
		for (int j = 0; j < 9; j++) {
			cout << arr[i][j] << ' ';
		}
		cout << '\n';
	}
}