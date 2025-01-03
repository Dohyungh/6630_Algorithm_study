#include<iostream>
#include<algorithm>

using namespace std;

int euclide(int A, int B) {
	if (A == 0) {
		return B;
	}

	if (B == 0) {
		return A;
	}

	int big, small;

	big = max(A, B);
	small = min(A, B);



	return euclide(B, A % B);

}

int main() {

	int A, B;

	cin >> A;
	cin >> B;

	int GCD = euclide(A, B);
	int LCA = A * B / GCD;

	cout << GCD << '\n' << LCA;


	return 0;
}

