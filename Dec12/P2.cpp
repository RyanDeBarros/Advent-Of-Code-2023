#include<vector>
#include<cmath>
#include<iostream>
using namespace std;

struct Row
{
	vector<int> arrangements;
	vector<char> springList;

	Row(string str)
	{
		bool onSprings = true;
		string temp = "";
		for (char c : str)
		{
			if (onSprings)
			{
				if (' ' == c)
				{
					onSprings = false;
					/* string copy(springList.begin(), springList.end());
					for (int i = 0; i < 4; i++)
					{
						springList.push_back('?');
						springList.insert(springList.end(), copy.begin(), copy.end());
					} */
					continue;
				}
				else springList.push_back(c);
			}
			else
			{
				if (',' == c)
				{
					if (!temp.empty())
					{
						arrangements.push_back(stoi(temp));
						temp.clear();
					}
				}
				else temp += c;
			}
		}
		if (!temp.empty())
		{
			arrangements.push_back(stoi(temp));
			temp.clear();
		}
		/* vector<int> preArr = arrangements;
		for (int i = 0; i < 4; i++) arrangements.insert(arrangements.end(), preArr.begin(), preArr.end()); */
	}

	long combos()
	{
		long combo = 0;
		combos(0, 0, 0, combo);
		return combo;
	}

	void combos(int num, int i, int n, long &count)
	{
		for (int k = n; k < springList.size(); k++)
		{
			switch (springList[k])
			{
				case '#':
					if (++num > arrangements[i]) return;
					break;
				case '.':
					if (num > 0) i++;
					if (i >= arrangements.size()) return;
					num = 0;
					break;
				case '?':
					if (num == arrangements[i])
					{
						i++;
						if (i >= arrangements.size()) return;
					} else
					{
						combos(num + 1, i, k + 1, count);
					}
					num = 0;
					break;
			}
		}
		count++;
		return;
	}
};

int main()
{
	vector<Row> rows;
	string str;
	while (getline(cin, str) && !str.empty()) rows.push_back(Row(str));

	long sum = 0;
	for (Row &row : rows) 
	{
		long c = row.combos();
		cout << c << endl;
		sum += c;
	}

	cout << sum << endl;
}

