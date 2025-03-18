// Same code in Java version runs at borderline 1 second (Best ever known is 0.8s, mostly TLE) :/
#include <cstdio>
#include <string>
using namespace std;

class Point
{
public:
    int x, y;
    Point() {};
    Point(double px, double py) : x(px), y(py) {}

    int distSq(Point p)
    {
        int dx = this->x - p.x;
        int dy = this->y - p.y;
        return dx * dx + dy * dy;
    }
};

class Vector
{
public:
    int x, y;
    Vector() {};
    Vector(Point p1, Point p2)
    {
        this->x = p2.x - p1.x;
        this->y = p2.y - p1.y;
    }
    int dot(Vector v)
    {
        return this->x * v.x + this->y * v.y;
    }
    int cross(Vector v)
    {
        return this->x * v.y - this->y * v.x;
    }
};

const int UNKNOWN = 0;
const int SIMPLE_POLYGON = 1;
const int TRAPEZIUM = 2;
const int PARALLELOGRAM = 3;
const int RECTANGLE = 4;
const int RHOMBUS = 5;
const int SQUARE = 6;
const string NAMES[7] = {"Unknown", "Ordinary Quadrilateral", "Trapezium", "Parallelogram", "Rectangle", "Rhombus", "Square"};
Point Points[4];
Point TestPoints[4];
int DistSq[4];
int Solution;

bool isPerpendicular(Point a, Point o, Point b)
{
    Vector oa = Vector(o, a);
    Vector ob = Vector(o, b);
    return oa.dot(ob) == 0;
}

bool isParallel(Point p1, Point p2, Point p3, Point p4)
{
    Vector v1 = Vector(p1, p2);
    Vector v2 = Vector(p3, p4);
    return v1.cross(v2) == 0;
}

int calc90Deg()
{
    int result = 0;
    for (int i = 0; i < 4; i++)
    {
        int prev = i - 1;
        if (prev < 0)
            prev += 4;
        int next = (i + 1) % 4;
        if (isPerpendicular(TestPoints[prev], TestPoints[i], TestPoints[next]))
            result++;
    }
    return result;
}

void check(int i1, int i2, int i3, int i4)
{
    TestPoints[0] = Points[i1];
    TestPoints[1] = Points[i2];
    TestPoints[2] = Points[i3];
    TestPoints[3] = Points[i4];

    for (int i = 0; i < 4; i++)
        DistSq[i] = TestPoints[i].distSq(TestPoints[(i + 1) % 4]);

    int deg90Count = calc90Deg();
    if (DistSq[0] == DistSq[1] && DistSq[1] == DistSq[2] && DistSq[2] == DistSq[3])
    {
        if (deg90Count == 4)
            Solution = max(Solution, SQUARE);
        else if (deg90Count == 0)
            Solution = max(Solution, RHOMBUS);
    }
    else if (DistSq[0] == DistSq[2] && DistSq[1] == DistSq[3] && deg90Count == 4)
    {
        Solution = max(Solution, RECTANGLE);
    }
    else
    {
        bool para1 = isParallel(TestPoints[0], TestPoints[1], TestPoints[3], TestPoints[2]);
        bool para2 = isParallel(TestPoints[0], TestPoints[3], TestPoints[1], TestPoints[2]);
        if (DistSq[0] == DistSq[2] && DistSq[1] == DistSq[3] && deg90Count == 0 && para1 && para2)
        {
            Solution = max(Solution, PARALLELOGRAM);
        }
        else if (para1 ^ para2)
        {
            Solution = max(Solution, TRAPEZIUM);
        }
        else
        {
            Solution = max(Solution, SIMPLE_POLYGON);
        }
    }
}

int main()
{
    int T, x, y;
    scanf("%d", &T);
    for (int t = 1; t <= T; t++)
    {
        for (int i = 0; i < 4; i++)
        {
            scanf("%d %d", &Points[i].x, &Points[i].y);
        }

        Solution = UNKNOWN;
        check(0, 1, 2, 3);
        check(0, 1, 3, 2);
        check(0, 2, 1, 3);
        check(0, 2, 3, 1);
        check(0, 3, 1, 2);
        check(0, 3, 2, 1);

        printf("Case %d: %s\n", t, NAMES[Solution].c_str());
    }
}