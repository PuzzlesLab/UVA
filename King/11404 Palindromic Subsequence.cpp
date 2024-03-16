#include <iostream>
#include <string>

using namespace std;

const int MAX_LEN = 1001;
string s;
pair<string, int> dp[MAX_LEN][MAX_LEN];

void clearDp(int size)
{
    for (int i = 0; i < size; i++)
    {
        for (int i2 = 0; i2 < size; i2++)
        {
            dp[i][i2].second = -1;
        }
    }
}

pair<string, int> maxPalin(pair<string, int> left, pair<string, int> right)
{
    if (left.second > right.second)
        return left;
    if (right.second > left.second)
        return right;
    return left > right ? right : left;
}

pair<string, int> find(int i, int i2)
{
    if (i > i2)
        return make_pair("", 0);
    if (dp[i][i2].second == -1)
    {
        pair<string, int> ans = make_pair("", 0);
        if (i == i2)
        {
            ans.first = s[i];
            ans.second = 1;
        }
        else
        {
            if (s[i] == s[i2])
            {
                pair<string, int> next = find(i + 1, i2 - 1);
                ans.first = s[i] + next.first + s[i2];
                ans.second = 2 + next.second;
            }
            else
                ans = maxPalin(find(i + 1, i2), find(i, i2 - 1));
        }
        dp[i][i2] = ans;
    }
    return dp[i][i2];
}

int main()
{
    while (cin >> s)
    {
        clearDp(s.length());
        cout << find(0, s.length() - 1).first << endl;
    }
}