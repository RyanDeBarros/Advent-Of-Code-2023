#include <iostream>
#include <vector>
using namespace std;

int main(int argc, char **argv)
{
	string row;
	int i = 0, j = 0;
	int startI, startJ;
	vector<vector<char>> tiles;
	while (getline(cin, row) && !row.empty())
	{
		vector<char> tileRow;
		for (char c : row)
		{
			if ('S' == c)
			{
				startI = i;
				startJ = j;
				tileRow.push_back(argv[1][0]);
			} else tileRow.push_back(c);
			j++;
		}
		j = 0;
		i++;
		tiles.push_back(tileRow);
	}

	i = startI;
	j = startJ;
	int from = argv[2][0] - 48, count = 0;
	/*
	 1
	2 3
	 4
	*/
	do
	{
		count++;
		switch(tiles[i][j])
		{
			case '|':
				if (from == 1)
				{
					i++;
					from = 1;
				}
				else
				{
					i--;
					from = 4;
				}
				break;
			case '-':
				if (from == 2)
				{
					j++;
					from = 2;
				}
				else
				{
					j--;
					from = 3;
				}
				break;
			case 'L':
				if (from == 1)
				{
					j++;
					from = 2;
				}
				else
				{
					i--;
					from = 4;
				}
				break;
			case 'J':
				if (from == 1)
				{
					j--;
					from = 3;
				}
				else
				{
					i--;
					from = 4;
				}
				break;
			case '7':
				if (from == 2)
				{
					i++;
					from = 1;
				}
				else
				{
					j--;
					from = 3;
				}
				break;
			case 'F':
				if (from == 3)
				{
					i++;
					from = 1;
				}
				else
				{
					j++;
					from = 2;
				}
				break;
		}
	} while(i != startI || j != startJ);
	cout << 0.5 * count << endl;
}
