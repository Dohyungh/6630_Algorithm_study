#include<iostream>

using namespace std;

int N, M, K;
long long* arr;

struct Node
{
	int start;
	int end;
	long long val;

	Node(){}

	Node(int start, int end, long long val) : start(start), end(end), val(val) {}
};
Node* tree;

long long build(int idx, int start, int end)
{
	if (start == end)
	{
		tree[idx] = Node(start, end, arr[start]);
		return tree[idx].val;
	}
	
	long long L = build(idx * 2, start, (start + end) / 2);
	long long R = build(idx * 2 + 1, ((start + end) / 2) + 1, end);

	tree[idx] = Node(start, end, L + R);

	return tree[idx].val;


}

void update(int idx, int arrIdx, long long val)
{
	Node curr = tree[idx];

	long long gap = arr[arrIdx] - val;

	tree[idx].val -= gap;

	if (curr.start == curr.end) return;

	if (arrIdx >= curr.start && arrIdx <= (curr.start + curr.end) / 2)
	{
		update(idx * 2, arrIdx, val);
	}
	else
	{
		update(idx * 2 + 1, arrIdx, val);
	}

}

long long getSum(int idx, int from, int to)
{
	Node curr = tree[idx];

	if (curr.start >= from && curr.end <= to)
	{
		return curr.val;
	}

	if (curr.start > to || curr.end < from)
	{
		return 0;
	}

	long long left = getSum(2 * idx, from, to);
	long long right = getSum(2 * idx + 1, from, to);

	return left + right;
}


int main()
{
	cin >> N;
	cin >> M;
	cin >> K;

	arr = new long long[N+1];

	tree = new Node[8 * N]();

	for (int i = 1; i <= N; i++)
	{
		cin >> arr[i];
	}
	
	build(1, 1, N);



	for (int i = 0; i < M + K; i++)
	{
		int a, b;
		long long c;

		cin >> a;
		cin >> b;
		cin >> c;

		if (a == 1)
		{
			update(1, b, c);
			arr[b] = c;

		}
		else
		{
			cout << getSum(1, b, c) << endl;
		}
	}



}

