import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static final int MAX=2000001;
	private static boolean [] NotPrime=new boolean [MAX];
	private static int [] Prime=new int [148933];
	private static int PrimeCount=0;

	private static class Tuple implements Comparable<Tuple> {
		int n, v;
		
		public int compareTo(Tuple t) {
			if (this.v!=t.v) return this.v-t.v;
			return this.n-t.n;
		}
	}

	private static void initialize() {
		for (int i=2;i*i<MAX;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<MAX;i2+=i) NotPrime[i2]=true;
		}
		for (int i=2;i<MAX;i++) if (!NotPrime[i]) Prime[PrimeCount++]=i;
	}

	private static int sopf(int n) {
		int result=0;
		int temp=n;
		for (int i=0;i<PrimeCount && Prime[i]*Prime[i]<=temp;i++) {
			while (temp%Prime[i]==0) {
				temp/=Prime[i];
				result++;
			}
		}
		if (temp!=1) result++;
		return result;
	}

	public static void main(String[] args) throws Exception {
		initialize();
		
		Tuple [] tuples=new Tuple [MAX-1];
		for (int i=0;i<tuples.length;i++) {
			tuples[i]=new Tuple();
			tuples[i].n=i+1;
			tuples[i].v=sopf(tuples[i].n);
		}
		Arrays.sort(tuples);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=1;
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			
			N--;
			System.out.printf("Case %d: %d\n",tc++,tuples[N].n);
		}
	}

}
