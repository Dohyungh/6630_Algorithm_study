#include<iostream>

using namespace std;

int main() {

	int T;
	cin >> T;

	for (int tc = 0; tc < T; tc++) {

		int N;
		cin >> N;

		int* scores = new int[N];

		int sum = 0;
		for (int i = 0; i < N; i++) {
			cin >> scores[i];
			sum += scores[i];
		}
		float avg = (float)sum / N;

		float cnt = 0.0;
		for (int i = 0; i < N; i++) {
			if (scores[i] > avg) {
				cnt++;
			}
		}

		cout.precision(6);
		cout << cnt * 100 / N << '%' << '\n';

	}


	return 0;
}