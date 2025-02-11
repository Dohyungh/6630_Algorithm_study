#include<iostream>
#include <unordered_map>
#include <algorithm>

using namespace std;

int main() 
{
	int t;
	cin >> t;

	
	for (int i = 0; i < t; i++) 
	{
		int N;
		cin >> N;

		unordered_map<string, int> dict;
		string* arr = new string[N];
		
		for (int i = 0; i < N; i++) 
		{
			cin >> arr[i];
		}

		sort(arr, arr + N, [](const string &a, const string &b){ return a.size() < b.size(); });

		for (int i = 0; i < N; i++)
		{
			string num = arr[i];

			while (num.size() > 0)
			{
				if (dict.count(num)) 
				{
					cout << "NO" << endl;
					delete[] arr;
					goto label;

				}
				num.pop_back();
			}
			dict[arr[i]] = 1;
		}

		cout << "YES" << endl;
		delete[] arr;
	label:;
	}



	return 0;
}