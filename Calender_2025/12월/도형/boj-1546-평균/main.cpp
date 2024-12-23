#include<iostream>

using namespace std;

int main() {
    int N;

    cin >> N;

    float scores[N];

    int max = 0;

    for (int i = 0; i < N; i++)
    {
        cin >> scores[i];
        if (scores[i] > max)
        {
            max = scores[i];
        }
    }

    for (int i = 0; i < N; i++)
    {
        scores[i] = scores[i] * 100 / float(max);
    }

    float sum = 0.0f;
    for (int i = 0; i < N; i++)
    {
        sum += scores[i];
    }

    cout.precision(10);
    cout << sum / N;

    return 0;
}