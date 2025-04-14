#include<iostream>
#include<string>
#include<sstream>
#include<vector>

using namespace std;

int main() {

	int N;

	cin >> N;
	int* arr = new int[N];

	cin.ignore();
	string line;
	getline(cin, line);
	istringstream iss(line);

	int num;
	int idx = 0;
	while (iss >> num) {
		arr[idx++] = num;
	}
	
	vector<int> vec;
	vec.push_back(arr[0]);

	for (int i = 0; i < N; i++) {
		int curr = arr[i];

		int right = vec.size() - 1;
		int left = 0;

		if (vec[vec.size() - 1] < curr) {
			vec.push_back(curr);
			continue;
		}

		while (left < right)
		{
			int mid = (left + right - 1) / 2;

			if (vec[mid] >= curr)
			{
				right = mid;

			}
			else
			{
				left = mid + 1;
			}
		}

		vec[right] = curr;
	
	}

	cout << vec.size();
	

	return 0;
}
