// Java version passed in Kattis, but not here. Really?

#include <bits/stdc++.h>
using namespace std;

const int MAX_E=5002;
vector<int> BetterThan[MAX_E];
vector<int> WorseThan[MAX_E];
bool Visited[MAX_E];

int findBetterThan(int curr) {
    int value=1;
    for (int next : BetterThan[curr]) {
        if (!Visited[next]) {
            Visited[next]=true;
            value+=findBetterThan(next);
        }
    }
    return value;
}

int findWorseThan(int curr) {
    int value=1;
    for (int next: WorseThan[curr]) {
        if (!Visited[next]) {
            Visited[next]=true;
            value+=findWorseThan(next);
        }
    }
    return value;
}

int main() {
    int A, B, P, E;
    while (scanf("%d %d %d %d", &A, &B, &E, &P) == 4) {
        for (int e=0;e<E;e++) {
            BetterThan[e].clear();
            WorseThan[e].clear();
        }

        for (int p=0;p<P;p++) {
            int x, y;
            scanf("%d %d", &x, &y);
            BetterThan[x].push_back(y);
            WorseThan[y].push_back(x);
        }

        int ansA=0;
        int ansB=0;
        int ansW=0;
        for (int e=0;e<E;e++) {
            memset(Visited, false, sizeof(Visited));
            Visited[e]=true;
            int betterThan=E-findBetterThan(e)+1;
            if(betterThan<=A) ansA++;
            if(betterThan<=B) ansB++;

            memset(Visited, false, sizeof(Visited));
            Visited[e]=true;
            if(findWorseThan(e)>B) ansW++;
        }

        cout << ansA << "\n" << ansB << "\n" << ansW << "\n";
    }
}
