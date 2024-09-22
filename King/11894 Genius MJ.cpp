#include <stdio.h>
#include <iostream>
#include <algorithm>
using namespace std;

// TLE if same way is done in Java. :/

struct Tuple
{
    int x, y;

    Tuple()
    {
        x = y = 0;
    }
    Tuple(int _x, int _y)
        : x(_x),
          y(_y)
    {
    }
    bool operator<(Tuple other) const
    {
        if (x != other.x)
            return x < other.x;
        return y < other.y;
    }
};

Tuple plug[100002];
Tuple socket[1000002];
int N;

bool isTranslation()
{
    int refX = plug[0].x - socket[0].x;
    int refY = plug[0].y - socket[0].y;
    for (int n = 1; n < N; n++)
    {
        int currX = plug[n].x - socket[n].x;
        int currY = plug[n].y - socket[n].y;
        if (refX != currX || refY != currY)
            return false;
    }
    return true;
}

void rotateSocket()
{
    for (int n = 0; n < N; n++)
    {
        int nx = -socket[n].y;
        int ny = socket[n].x;
        socket[n].x = nx;
        socket[n].y = ny;
    }
    sort(socket, socket + N);
}

int main()
{
    int TC;
    scanf("%d", &TC);
    for (int tc = 0; tc < TC; tc++)
    {
        scanf("%d", &N);
        for (int n = 0; n < N; n++)
        {
            scanf("%d %d", &plug[n].x, &plug[n].y);
        }
        sort(plug, plug + N);

        for (int n = 0; n < N; n++)
        {
            scanf("%d %d", &socket[n].x, &socket[n].y);
        }

        bool flag = false;
        for (int i = 0; i < 5 && !flag; i++)
        {
            rotateSocket();
            flag |= isTranslation();
        }
        cout << (flag ? "MATCHED" : "NOT MATCHED") << endl;
    }
}