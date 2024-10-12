// Same code in Java will run into TLE, what a shame. :/

#include <stdio.h>
#include <algorithm>

using namespace std;
#define eps 1e-8

struct Point
{
    double x, y;

    Point() {}

    Point(double i, double i2)
    {
        x = i;
        y = i2;
    }
};

struct Line
{
    Point p1, p2;
    double a, b, c;

    Line() {}

    Line(int x1, int y1, int x2, int y2)
    {
        p1 = Point(x1, y1);
        p2 = Point(x2, y2);
        if (p1.x == p2.x)
        {
            a = 1.0;
            b = 0;
            c = -p1.x;
        }
        else
        {
            a = -(p1.y - p2.y) / (p1.x - p2.x);
            b = 1.0;
            c = -(a * p1.x) - p1.y;
        }
    };

    bool isParallel(Line l)
    {
        return a == l.a && b == l.b;
    }

    bool isOnLine(double x, double y)
    {
        if (x < min(p1.x, p2.x) - eps)
            return false;
        if (x > max(p1.x, p2.x) + eps)
            return false;
        if (y < min(p1.y, p2.y) - eps)
            return false;
        if (y > max(p1.y, p2.y) + eps)
            return false;
        return true;
    }

    bool isIntersect(Line l)
    {
        if (isParallel(l))
            return false;
        double x = (l.b * c - b * l.c) / (l.a * b - a * l.b);
        double y = b > 0 ? -(a * x + c) : -(l.a * x + l.c);

        if (!isOnLine(x, y))
            return false;
        if (!l.isOnLine(x, y))
            return false;
        return true;
    }
};

Line sticks[1005];
bool flag[1005];

int main()
{
    int xs, ys, xe, ye;
    int N = 0;
    while (scanf("%d", &N) && N)
    {
        for (int n = 0; n < N; n++)
        {
            scanf("%d %d %d %d", &xs, &ys, &xe, &ye);
            sticks[n] = Line(xs, ys, xe, ye);
            flag[n] = false;
        }
        int ans = 0;
        for (int n = 0; n < N; n++)
        {
            for (int n2 = n + 1; n2 < N; n2++)
            {
                if (sticks[n].isIntersect(sticks[n2]))
                {
                    ans++;
                    flag[n] = true;
                    flag[n2] = true;
                }
            }
        }
        for (int n = 0; n < N; n++)
            if (!flag[n])
                ans += 2;
        printf("%d\n", ans);
    }
    return 0;
}