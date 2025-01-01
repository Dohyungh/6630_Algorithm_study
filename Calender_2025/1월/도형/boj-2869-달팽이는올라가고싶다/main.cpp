#include<iostream>

using namespace std;

int main() {

	int A, B, V;

	cin >> A;
	cin >> B;
	cin >> V;
	
	int temp = (V - A) / (A - B);
	int var = (V - A) % (A - B);

	if (var == 0) {
		cout << (V - A) / (A - B) + 1;

	}
	else {
		cout << (V - A) / (A - B) + 2;
	}


	return 0;
}