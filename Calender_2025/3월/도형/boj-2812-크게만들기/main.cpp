#include<iostream>
#include<queue>
#include<string>
#include<stack>
using namespace std;

int main()
{
	int N, K;
	cin >> N;
	cin >> K;

	string num;
	cin >> num;
	
	stack<int> st;

	int cnt = 0;
	int idx = 0;

	while (idx < N && cnt < K) {

		if (st.empty()) {
			st.push(stoi(string(1, num[idx++])));
			continue;
		}

		int top = st.top();

		if (stoi(string(1, num[idx])) > top) {
			st.pop();
			cnt++;
		}
		else {
			st.push(stoi(string(1, num[idx++])));
		}
	}

	stack<int> st2;
	while (!st.empty()) {
		st2.push(st.top());
		st.pop();
		
	}

	int length = 0;
	while (!st2.empty() && length < N - K) {
		cout << st2.top();
		st2.pop();
		length++;
	}

	for (int i = idx; i < N - (K - cnt); i++) {
		cout << num[i];
	}
}