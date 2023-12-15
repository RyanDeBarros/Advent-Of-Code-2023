#include<iostream>
#include<vector>
using namespace std;

const vector<vector<string>> states;

void tiltWest(vector<string> &platform)
{
	for (string &row : platform)
	{
		int fallback = 0;
		while (fallback < row.size() && row[fallback] != '.') fallback++;
		for (size_t j = 0; j < row.size(); j++)
		{
			if (j < fallback) continue;
			if ('O' == row[j])
			{
				if (fallback < row.size())
				{
					row[fallback] = 'O';
					row[j] = '.';
					do fallback++;
					while (fallback < row.size() && row[fallback] != '.');
				}
				else break;
			}
			else if ('#' == row[j])
			{
				fallback = j;
				do fallback++;
				while (fallback < row.size() && row[fallback] != '.');
			}
		}
	}
}

void rotate(vector<string> &platform, const bool &cw)
{
	vector<string> cols;
	for (size_t j = 0; j < platform[0].size(); j++)
	{
		string row;
		for (size_t i = 0; i < platform.size(); i++)
		{
			row += cw
				? platform[platform.size() - i - 1].at(j)
				: platform[i].at(platform[i].size() - j - 1);
		}
		cols.push_back(row);
	}
	platform = cols;
}

bool checkState(const vector<string> &platform)
{
	for (auto &state : states) if (state == platform) return true;
	return false;
}

void tiltCycle(vector<string> &platform, size_t count)
{
	count *= 4;
	for (size_t n = 0; n < count; n++)
	{
		if (checkState(platform))
		{
			platform = states[(count - n) % states.size()];
			return;
		}
		tiltWest(platform);
		rotate(platform, true);
		cout << static_cast<int>(1000000 * n / count) * 0.0001 << endl;
	}
}

int load(const vector<string> &platform)
{
	int load = 0;
	for (const string &str : platform) for (size_t i = 0; i < str.size(); i++) 
		if ('O' == str[i]) load += str.size() - i;
	return load;
}

int main(int argc, char **argv)
{
	vector<string> platform;
	string temp;
	while (getline(cin, temp) && !temp.empty()) platform.push_back(temp);
	rotate(platform, false);

	tiltCycle(platform, argc > 1 ? stol(argv[1]) : 1000000000L);
	cout << load(platform) << endl;
}

