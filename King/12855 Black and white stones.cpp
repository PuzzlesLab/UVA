#include <iostream>
#include <cstring>
using namespace std;

const long UNSET = 1l << 62;
const long MAX = (1l << 62) - 1;
long Pair, Adj;
char Game[5002];
long Dp[5002][5002];

long find(int i, int black)
{
    if (black == 0)
        return 0;

    if (Dp[i][black] == UNSET)
    {
        long ans = MAX;
        if (Game[i] == 'B')
            ans = find(i + 1, black - 1);
        else
            ans = min(Pair + find(i + 1, black - 1), (Pair - Adj) * black + find(i + 1, black));
        Dp[i][black] = ans;
    }

    return Dp[i][black];
}

int main()
{
    while (scanf("%ld%ld", &Pair, &Adj) == 2)
    {
        scanf("%s", Game);
        int len = strlen(Game);
        int black = 0;
        for (int i = 0; i < len; i++)
            if (Game[i] == 'B')
                black++;
        for (int i = 0; i < len; i++)
            for (int i2 = 0; i2 <= black; i2++)
                Dp[i][i2] = UNSET;
        printf("%ld\n", find(0, black));
    }
}