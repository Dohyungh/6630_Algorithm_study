#include<iostream>
#include<string>
#include<array>


using namespace std;

int main() {


	string input;

	cin >> input;

	array<int,26> countingArr = {};

	for (int i = 0; i < input.length(); i++) {
		int ascii = int(input[i]);

		if (ascii >= 65 && ascii <= 90) {
			countingArr[ascii - 65]++;
		}
		else {
			countingArr[ascii - 97]++;
		}
	}

	int max = -1;
	for (int i = 0; i < countingArr.size(); i++) {
		if (countingArr[i] > max) {
			max = countingArr[i];
		}
	}
	bool flag = false;
	char answer;
	for (int i = 0; i < countingArr.size(); i++) {
		if (flag && countingArr[i] == max) {
			cout << '?';
			return 0;
		}
		if (countingArr[i] == max) {
			flag = true;
			answer = 'A' + i;
		}
	}

	cout << answer;

	return 0;
}