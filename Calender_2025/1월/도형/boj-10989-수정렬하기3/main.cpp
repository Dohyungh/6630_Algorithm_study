#include<iostream>
#include<algorithm>

using namespace std;

int main() {

	int N;
	cin >> N;
	
	int arr[10001];

	fill_n(arr, 10001, 0);

	for (int i = 0; i < N; i++) {
		int idx;
		cin >> idx;
		arr[idx]++;
	}

	for (int i = 1; i <= 10000; i++) {
		for (int j = 0; j < arr[i]; j++) {
			cout << i << '\n';
			
		}
	}


	return 0;
}