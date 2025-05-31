#include <iostream>
#include <sstream>
#include <string>
using namespace std;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(false);
    int M;
    cin >> M;
    cin.ignore();
    int max = (1 << 21) - 2;

   int bit = 0;
    for (int i = 0; i < M; i++) {
        string line;
        getline(cin, line);

        istringstream iss(line);


        string ord;
        int num;

        iss >> ord;
        iss >> num;



        if (ord == "add") {
            bit |= (1 << num);
        }
        else if (ord == "remove") {
            if ((bit & (1 << num)) > 0)
            {
                bit -= (1 << num);
            }
        }
        else if (ord == "check") {
            if ((bit & (1 << num)) > 0) {
                cout << 1 << endl;
            }
            else {
                cout << 0 << '\n';
            }
        }
        else if (ord == "toggle") {
            bit ^= (1 << num);
        }
        else if (ord == "all") {
            bit = max;
        }
        else if (ord == "empty") {
            bit = 0;
        }

    }

    return 0;
}
