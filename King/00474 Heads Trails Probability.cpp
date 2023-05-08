// WA on Java version of same code, wth?
#include <stdio.h>

const int MAX=1000000;
double sig [MAX+1];
int exponent [MAX+1];

int main() {
    double cSig=1.0;
    int cExp=0;
    for (int n=0;n<=MAX;n++) {
        sig[n]=cSig;
        exponent[n]=cExp;
        cSig*=0.5;
        while (cSig<1.0) {
            cSig*=10;
            cExp++;
        }
    }

    int N;
    while (scanf("%d", &N) == 1) {
        printf("2^-%d = %.3fe-%d\n",N,sig[N],exponent[N]);
    }
}