#include<iostream>
#include<sstream>
#include<unordered_map>
using namespace std;

int main() {
	string line;
	getline(cin, line);
	istringstream ss(line);

	unordered_map<string, pair<string, string>> map;
	

	string start, end, final;

	ss >> start >> end >> final;
	int startH, startM, endH, endM, finalH, finalM;

	startH = stoi(start.substr(0, 2));
	startM = stoi(start.substr(3, 2));
	endH = stoi(end.substr(0, 2));
	endM = stoi(end.substr(3, 2));
	finalH = stoi(final.substr(0, 2));
	finalM = stoi(final.substr(3, 2));



	int answer = 0;
	string time, id;
	while (getline(cin, line))
	{
		istringstream ss(line);
		ss >> time >> id;

		int timeH = stoi(time.substr(0, 2));
		int timeM = stoi(time.substr(3, 2));

		if (timeH < startH || (timeH == startH && timeM <= startM))
		{
			map[id] = { time, "" };
			continue;
		}

		if (((timeH == endH && timeM >= endM) || timeH > endH) && ((timeH == finalH && timeM <= finalM) || timeH < finalH))
		{
			pair<string, string> val;

			if (map.find(id) != map.end())
			{
				val = map[id];
				if (val.second == "")
				{
					answer++;
					map[id] = { val.first, time };

				}
			}

		}

	}
	cout << answer;

	return 0;



}