#include <iostream>
#include <cstring>

using namespace std;

string src;
string target;
int back[100005];

bool kmp()
{
    memset(back, 0, sizeof(back));
    back[0] = -1;
    int i = 0;
    int i2 = -1;
    while (i < target.length())
    {
        while (i2 >= 0 && target[i] != target[i2])
            i2 = back[i2];
        back[i++] = i2++;
    }

    i = 0;
    i2 = 0;
    while (i < src.length())
    {
        while (i2 >= 0 && src[i] != target[i2])
            i2 = back[i2];
        i++;
        i2++;
        if (i2 == target.length())
            return true;
    }
    return false;
}

int main()
{
    int TC, Q;
    scanf("%d", &TC);
    for (int tc = 0; tc < TC; tc++)
    {
        cin >> src;
        scanf("%d", &Q);
        for (int q = 0; q < Q; q++)
        {
            cin >> target;
            printf(kmp() ? "y\n" : "n\n");
        }
    }
}