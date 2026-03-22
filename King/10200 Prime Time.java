import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int MAX_N=10000;
	private static boolean [] NotPrime=new boolean [MAX_N*MAX_N+MAX_N+41+1];
	private static int [] PrimeCount=new int [MAX_N+1];

	private static void eSieve() {
		NotPrime[0]=true;
		NotPrime[1]=true;
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i])
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		
		PrimeCount[0]=1;
		for (int i=1;i<=MAX_N;i++) {
			PrimeCount[i]=PrimeCount[i-1];
			int n=i*i+i+41;
			if (!NotPrime[n]) PrimeCount[i]++;
		}
	}

	public static void main(String [] args) throws Exception {
		eSieve();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			double ans=PrimeCount[to]-PrimeCount[Math.max(from-1,0)]+0.0;
			if (from==0) ans+=1;
			System.out.printf("%.2f\n",(ans*100)/(to-from+1));
		}
	}

}