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
		int val = value(rows, 100);
		if (val >= 0) return val;
		return value(cols, 1);
	}

	int value(vector<string> &rows, int mult)
	{
		for (int i = 0; i < rows.size() - 1; i++)
		{
			bool valid = true;
			for (int k = 0; k < min(i + 1, static_cast<int>(rows.size()) - i - 1); k++)
			{
				if (rows[i - k] != rows[i + 1 + k])
				{
					valid = false;
					break;
				}
			}
			if (valid) return mult * (i + 1);
		}
		return -1;
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

