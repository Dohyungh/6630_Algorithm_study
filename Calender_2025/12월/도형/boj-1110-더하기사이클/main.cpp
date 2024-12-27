#include<iostream>

using namespace std;

int main() {

	int N;

	cin >> N;

	int X = N;
	int cnt = 0;
	while (true)
	{
		int temp = X / 10 + X % 10;
		X = (X % 10) * 10 + temp % 10;
		cnt++;

		if (X == N) {
			break;
		}
	}

	cout << cnt;
	return 0;

}