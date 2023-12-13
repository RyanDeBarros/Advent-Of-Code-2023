#include<vector>
#include<iostream>
using namespace std;

struct Pattern
{
	vector<string> rows, cols;
	
	Pattern(vector<string> ptn)
	{
		rows.insert(rows.begin(), ptn.begin(), ptn.end());
		for (int j = 0; j < rows[0].size(); j++)
		{
			string str = "";
			for (int i = 0; i < rows.size(); i++)
			{
				str += rows[i].at(j);
			}
			cols.push_back(str);
		}
	}

	int value()
	{
		int val = 100 * value(rows);
		if (val >= 0) return val;
		return value(cols);
	}

	int value(vector<string> &rows)
	{
		for (int i = 0; i < rows.size() - 1; i++)
		{
			int totalDiff = 0;
			for (int k = 0; k < min(i + 1, static_cast<int>(rows.size()) - i - 1); k++)
			{
				totalDiff += diff(rows[i - k], rows[i + 1 + k]);
				if (totalDiff > 1) break;
			}
			if (totalDiff == 1) return i + 1;
		}
		return -1;
	}

	int diff(string s1, string s2)
	{
		if (s1 == s2) return 0;
		int diff = 0;
		for (int i = 0; i < s1.size(); i++)
		{
			if (s1.at(i) != s2.at(i)) if (++diff > 1) return 2;
		}
		return diff;
	}
};

int main()
{
	string temp;
	vector<string> ptn;
	vector<Pattern> patterns;
	while (getline(cin, temp))
	{
		if (temp.empty())
		{
			if (!ptn.empty())
			{
				patterns.push_back(Pattern(ptn));
				ptn.clear();
			}
		}
		else
		{
			ptn.push_back(temp);
		}
	}

	int sum = 0;
	for (Pattern &p : patterns) sum += p.value();
	cout << sum << endl;
}

