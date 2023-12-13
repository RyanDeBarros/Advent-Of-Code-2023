#include<vector>
#include<cmath>
#include<iostream>
using namespace std;

struct Row
{
	vector<int> arrangements;
	vector<char> springList;
	vector<int> qIndeces;
	int max_combo;

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
		for (int i = 0; i < springList.size(); i++) if ('?' == springList[i]) qIndeces.push_back(i);
		max_combo = 1 << qIndeces.size();
	}

	int combos()
	{
		if (qIndeces.empty()) return 1;
		int combos = 0;
		for (int i = 0; i < max_combo; i++)
		{
			for (int j = 0; j < qIndeces.size(); j++)
			{
				if ((i & (1 << j)) >> j) springList[qIndeces[j]] = '#';
				else springList[qIndeces[j]] = '.';
			}
			if (valid()) 
			{
				combos++;
			}
		}
		return combos;
	}

	bool valid()
	{
		vector<int> arr;
		int i = 0;
		for (char c : springList)
		{
			if ('#' == c) i++;
			else
			{
				if (i != 0) arr.push_back(i);
				i = 0;
			}
		}
		if (i != 0) arr.push_back(i);
		return arr == arrangements;
	}
};

int main()
{
	vector<Row> rows;
	string str;
	while (getline(cin, str) && !str.empty()) rows.push_back(Row(str));

	int sum = 0;
	for (Row &row : rows) 
	{
		sum += row.combos();
	}

	cout << sum << endl;
}

