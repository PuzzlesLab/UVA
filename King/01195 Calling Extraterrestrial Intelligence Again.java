import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static boolean [] NotPrime=new boolean [100001];
	private static long [] Primes=new long [9592];
	private static int PrimeCount=0;
	
	private static void eSieve() {
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
		
		NotPrime[0]=true;
		NotPrime[1]=true;
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) Primes[PrimeCount++]=i;
	}

	public static void main(String [] args) throws Exception {
		eSieve();

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0 0 0")) {
			StringTokenizer st=new StringTokenizer(s);
			int M=Integer.parseInt(st.nextToken());
			int A=Integer.parseInt(st.nextToken());
			int B=Integer.parseInt(st.nextToken());

			long solP=-1;
			long solQ=-1;
			long solProd=0;
			for (int qIdx=0;qIdx<Primes.length && Primes[qIdx]<M;qIdx++) {
				long p=Primes[qIdx]*A/B;
				int pMinIdx=Arrays.binarySearch(Primes,p);
				if (pMinIdx<0) pMinIdx=(-(pMinIdx)-1);
				if (pMinIdx==Primes.length) continue;

				for (int pIdx=pMinIdx;pIdx<=qIdx;pIdx++) {
					long prod=Primes[pIdx]*Primes[qIdx];
					if (prod>M) break;
					if (prod>solProd && Primes[pIdx]*B>=Primes[qIdx]*A) {
						solP=Primes[pIdx];
						solQ=Primes[qIdx];
						solProd=prod;
					}
				}
			}
			System.out.printf("%d %d\n",solP,solQ);
		}
	}

}