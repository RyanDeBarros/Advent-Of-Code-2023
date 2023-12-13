#include<iostream>
#include<vector>
#include<cstdlib>
using namespace std;

bool inOpenBiInterval(int a, int b, int x)
{
	return (a < x && x < b) || (b < x && x < a);
}

int main(int argc, char **argv)
{
	vector<vector<char>> table;
	string line = "";
	while (getline(cin, line) && !line.empty())
	{
		vector<char> row;
		for (char c : line) row.push_back(c);
		line.clear();
		table.push_back(row);
	}

	vector<int> expandedRows, expandedColumns;
	for (int i = 0; i < table.size(); ++i)
	{
		bool expand = true;
		for (int j = 0; j < table[i].size(); ++j) if ('#' == table[i][j])
		{
			expand = false;
			break;
		}
		if (expand) expandedRows.push_back(i);
	}
	for (int j = 0; j < table[0].size(); ++j)
	{
		bool expand = true;
		for (int i = 0; i < table.size(); ++i) if ('#' == table[i][j])
		{
			expand = false;
			break;
		}
		if (expand) expandedColumns.push_back(j);
	}

	vector<vector<int>> galaxies;
	for (int i = 0; i < table.size(); ++i) for (int j = 0; j < table[i].size(); ++j)
		if ('#' == table[i][j]) galaxies.push_back({i, j});

	long sum = 0;
	long offsetAmount = argc > 1 && argv[1] ? stol(string(argv[1])) - 1 : 2;
	for (int i = 0; i < galaxies.size() - 1; ++i)
	{
		for (int j = i + 1; j < galaxies.size(); ++j)
		{
			int offset = 0;
			for (int row : expandedRows) if (inOpenBiInterval(galaxies[i][0], galaxies[j][0], row)) offset++;
			for (int column : expandedColumns) if (inOpenBiInterval(galaxies[i][1], galaxies[j][1], column)) offset++;

			sum += abs(galaxies[i][0] - galaxies[j][0]) + abs(galaxies[i][1] - galaxies[j][1]) + offset * offsetAmount;
		}
	}

	cout << sum << endl;
}

