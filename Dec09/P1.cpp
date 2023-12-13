#include <vector>
#include <iostream>
using namespace std;

struct Sequence
{
	vector<long> seq;
	Sequence(string row)
	{
		string temp;
		int i;
		for (char c : row)
		{
			if (c == ' ')
			{
				if (!temp.empty())
				{
					seq.push_back(stol(temp));
					temp.clear();
				}
			}
			else temp += c;
		}
		if (!temp.empty())
		{
			seq.push_back(stol(temp));
			temp.clear();
		}
	}
};

bool allZero(vector<long> &seq)
{
	for (long l : seq) if (l) return false;
	return true;
}

long nextValue(vector<long> &seq)
{
	if (allZero(seq))
	{
		return 0;
	}
	else
	{
		vector<long> diff;
		for (int i = 1; i < seq.size(); i++) diff.push_back(seq[i] - seq[i - 1]);
		return seq[seq.size() - 1] + nextValue(diff);
	}
}

int main()
{
	vector<Sequence> sequences;
	string row;
	while (getline(cin, row)) sequences.push_back(Sequence(row));
	long sum = 0;
	for (Sequence &sequence : sequences) sum += nextValue(sequence.seq);
	cout << sum << endl;
}

