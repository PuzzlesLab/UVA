#include <stdio.h>

int main() {
    int T, A, B;
    double N, M;
    scanf("%d", &T);
    for (int t=1;t<=T;t++) {
        scanf("%lf %lf %d %d\n", &N, &M, &A, &B);
        double currTotal = A*N;
        double expTotal = (A+B)*M;
        double diff=(expTotal-currTotal)/B;

        if (diff<0 || diff>10) printf("Case #%d: Impossible\n", t);
        else printf("Case #%d: %.2f\n", t, diff);
    }
}