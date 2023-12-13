#include <iostream>
#include <vector>
using namespace std;

int* next(char c, int &from)
{
	int *change = new int[2];
	change[0] = 0; change[1] = 0;
	switch(c)
	{
		case '|':
			if (from == 1)
			{
				change[0] = 1;
				from = 1;
			}
			else
			{
				change[0] = -1;
				from = 4;
			}
			break;
		case '-':
			if (from == 2)
			{
				change[1] = 1;
				from = 2;
			}
			else
			{
				change[1] = -1;
				from = 3;
			}
			break;
		case 'L':
			if (from == 1)
			{
				change[1] = 1;
				from = 2;
			}
			else
			{
				change[0] = -1;
				from = 4;
			}
			break;
		case 'J':
			if (from == 1)
			{
				change[1] = -1;
				from = 3;
			}
			else
			{
				change[0] = -1;
				from = 4;
			}
			break;
		case '7':
			if (from == 2)
			{
				change[0] = 1;
				from = 1;
			}
			else
			{
				change[1] = -1;
				from = 3;
			}
			break;
		case 'F':
			if (from == 3)
			{
				change[0] = 1;
				from = 1;
			}
			else
			{
				change[1] = 1;
				from = 2;
			}
			break;
	}
	return change;
}

bool seeChange(int &i, int &j, int dirI, int dirJ, int sizeI, int sizeJ, vector<vector<bool>> &mainPath, vector<vector<bool>> &checked)
{
	i += dirI; j += dirJ;
	checked[i][j] = true;
	return i > 0 && i < sizeI - 1 && j > 0 && j < sizeJ - 1 && !mainPath[i][j];
}

void seeLeft(int i, int j, int from, vector<vector<char>> &tiles, vector<vector<bool>> &mainPath, vector<vector<bool>> &checked)
{
	if (i == 0 || i == tiles.size() - 1 || j == 0 || j == tiles[i].size() - 1) return;
	switch (tiles[i][j])
	{
		case '|':
			while (seeChange(i, j, 0, from == 1 ? 1 : -1, tiles.size(), tiles[i].size(), mainPath, checked));
			break;
		case '-':
			while (seeChange(i, j, from == 2 ? -1 : 1, 0, tiles.size(), tiles[i].size(), mainPath, checked));
			break;
		case 'L':
			if (from == 3)
			{
				while (seeChange(i, j, 1, 0, tiles.size(), tiles[i].size(), mainPath, checked));
				while (seeChange(i, j, 0, -1, tiles.size(), tiles[i].size(), mainPath, checked));
			}
			break;
		case 'J':
			if (from == 1)
			{
				while (seeChange(i, j, 1, 0, tiles.size(), tiles[i].size(), mainPath, checked));
				while (seeChange(i, j, 0, 1, tiles.size(), tiles[i].size(), mainPath, checked));
			}
			break;
		case '7':
			if (from == 2)
			{
				while (seeChange(i, j, -1, 0, tiles.size(), tiles[i].size(), mainPath, checked));
				while (seeChange(i, j, 0, 1, tiles.size(), tiles[i].size(), mainPath, checked));
			}
			break;
		case 'F':
			if (from == 4)
			{
				while (seeChange(i, j, -1, 0, tiles.size(), tiles[i].size(), mainPath, checked));
				while (seeChange(i, j, 0, -1, tiles.size(), tiles[i].size(), mainPath, checked));
			}
			break;
	}
}

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

	vector<vector<bool>> mainPath;
	for (vector<char> row : tiles)
	{
		vector<bool> pathRow;
		for (char c : row) pathRow.push_back(false);
		mainPath.push_back(pathRow);
	}
	i = startI;
	j = startJ;
	int from = argv[2][0] - 48;
	/*
	 1
	2 3
	 4
	*/
	do
	{
		mainPath[i][j] = true;
		int *change = next(tiles[i][j], from);
		i += *change;
		j += *(change + 1);
		delete[] change;
	} while(i != startI || j != startJ);
	
	vector<vector<bool>> checked;
	for (int i = 0; i < tiles.size(); i++)
	{
		vector<bool> checkedRow;
		for (int j = 0; j < tiles[i].size(); j++) checkedRow.push_back(mainPath[i][j]);
		checked.push_back(checkedRow);
	}

	i = startI;
	j = startJ;
	from = argv[2][0] - 48;
	do
	{
		seeLeft(i, j, from, tiles, mainPath, checked);
		int *change = next(tiles[i][j], from);
		i += *change;
		j += *(change + 1);
		delete[] change;
	} while(i != startI || j != startJ);

	int sum = 0;
	for (int i = 0; i < tiles.size(); i++) for (int j = 0; j < tiles[i].size(); j++)
		{
			if (mainPath[i][j]) sum--;
			if (checked[i][j]) sum++;
		}
	cout << sum << endl;
	
}

