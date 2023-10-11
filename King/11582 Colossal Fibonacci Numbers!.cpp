// Java version with BigInteger runs into TLE!
#include <stdio.h>
#include <vector>

using namespace std;

const int MAX_N=1001;
vector<int> cycle[MAX_N];

vector<int> findCycle(int N) {
    int f [N*N+1];
    f[0]=0;
    f[1]=1;
    int len=0;
    for (int i=2;true;i++) {
        f[i]=(f[i-2]+f[i-1])%N;
        if (!f[i-1] && f[i]==1) {
            len=i-1;
            break;
        }
    }
    vector<int> seq;
    for (int i=0;i<len;i++) seq.push_back(f[i]);
    return seq;
}

int modPow(unsigned long long base, unsigned long long pow, int mod) {
    unsigned long long ans=1;
    while (pow) {
        if (pow&1) ans=((ans%mod)*(base%mod))%mod;
        base=((base%mod)*(base%mod))%mod;
        pow>>=1;
    }
    return (int)ans;
}

int main() {
    for (int i=2;i<MAX_N;i++) cycle[i]=findCycle(i);
    int testCaseCount;
    scanf("%d",&testCaseCount);
    unsigned long long A,B;
    int N;
    for (int testCase=0;testCase<testCaseCount;testCase++) {
        scanf("%llu %llu %d",&A,&B,&N);
        if (A==0 || N==1) {
            printf("0\n");
            continue;
        }

        int C=cycle[N].size();
        printf("%d\n", cycle[N][modPow(A,B,C)]);
    }

}