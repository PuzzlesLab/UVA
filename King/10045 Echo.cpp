#include <iostream>
#include <map>
#include <string>

using namespace std;

map<pair<int, string>, pair<bool, bool>> Dp;
string s;
int N;

pair<bool, bool> find(pair<int, string> ctx)
{
    if (ctx.first == s.length())
    {
        return make_pair(ctx.second.length() == 0, ctx.second.length() != 0);
    }
    if (Dp.count(ctx))
    {
        return Dp[ctx];
    }

    pair<bool, bool> ans = make_pair(false, false);
    if (ctx.second.length() && s[ctx.first] == ctx.second.front())
    {
        pair<bool, bool> curr = find(make_pair(ctx.first + 1, ctx.second.substr(1)));
        ans = make_pair(ans.first | curr.first, ans.second | curr.second);
        if (ans.first)
        {
            return Dp[ctx] = ans;
        }
    }
    if (ctx.second.length() < 10)
    {
        pair<bool, bool> curr = find(make_pair(ctx.first + 1, ctx.second + s[ctx.first]));
        ans = make_pair(ans.first | curr.first, ans.second | curr.second);
    }
    return Dp[ctx] = ans;
}

int main()
{
    cin >> N;
    for (int n = 0; n < N; n++)
    {
        cin >> s;
        Dp.clear();
        pair<bool, bool> ans = find(make_pair(0, ""));
        if (ans.first)
        {
            cout << "An echo string with buffer size ten\n";
        }
        else if (ans.second)
        {
            cout << "Not an echo string, but still consistent with the theory\n";
        }
        else
        {
            cout << "Not consistent with the theory\n";
        }
    }
}