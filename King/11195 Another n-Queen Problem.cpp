#include <stdio.h>
#include <string.h>

using namespace std;

const int Deltas[4][2] = {
    {-1, -1},
    {-1, 1},
    {1, -1},
    {1, 1},
};
const int BAD = -1;
int Map[20][20];
int N;
int Ans;

void toggle(int x, int y, int change)
{
    for (int i = 0; i < 4; i++)
    {
        int cx = x + Deltas[i][0];
        int cy = y + Deltas[i][1];
        while (cx >= 0 && cx < N && cy >= 0 && cy < N)
        {
            if (Map[cx][cy] != BAD)
                Map[cx][cy] += change;
            cx += Deltas[i][0];
            cy += Deltas[i][1];
        }
    }
}

void dfs(int x, int xMask, int yMask, int step)
{
    if (x == N)
    {
        Ans++;
        return;
    }
    if ((xMask & (1 << x)) != 0)
        return;

    for (int y = 0; y < N; y++)
        if (Map[x][y] == 0)
        {
            if ((yMask & (1 << y)) != 0)
                continue;

            toggle(x, y, 1);
            dfs(x + 1, xMask | (1 << x), yMask | (1 << y), step + 1);
            toggle(x, y, -1);
        }
}

int main()
{
    int tc = 1;
    char line[20];
    while (scanf("%d", &N) && N != 0)
    {
        memset(Map, 0, sizeof(Map));
        for (int n = 0; n < N; n++)
        {
            scanf("%s", line);
            for (int n2 = 0; n2 < N; n2++)
                if (line[n2] == '*')
                    Map[n][n2] = BAD;
        }

        Ans = 0;
        dfs(0, 0, 0, 0);

        printf("Case %d: %d\n", tc++, Ans);
    }
}