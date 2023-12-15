#include<iostream>
#include<vector>
using namespace std;

int main()
{
	vector<string> platform;
	string temp;
	while (getline(cin, temp) && !temp.empty())
	{
		platform.push_back(temp);
	}

	vector<string> cols;
	for (int j = 0; j < platform[0].size(); j++)
	{
		string col;
		for (int i = 0; i < platform.size(); i++)
		{
			col += platform[i].at(j);
		}
		cols.push_back(col);
	}

	int load;
	for (string &col : cols)
	{
		int fallback = 0;
		while (fallback < col.size() && col[fallback] != '.') fallback++;
		for (int i = 0; i < col.size(); i++)
		{
			if (i < fallback)
			{
				if ('O' == col[i]) load += (col.size() - i);
				continue;
			}
			if ('O' == col[i])
			{
				if (fallback < col.size())
				{
					col[fallback] = 'O';
					col[i] = '.';
					load += (col.size() - fallback);
					do fallback++;
					while (fallback < col.size() && col[fallback] != '.');
				}
				else break;
			}
			else if ('#' == col[i])
			{
				fallback = i;
				do fallback++;
				while (fallback < col.size() && col[fallback] != '.');
			}
		}
	}

	cout << load << endl;
}

