import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	private static boolean [] notPrime=new boolean [500001];
	
	private static void sieveOfEratothenes() {
		for (int i=2;i*i<notPrime.length;i++) if (!notPrime[i]) {
			for (int i2=i*i;i2<notPrime.length;i2+=i) notPrime[i2]=true;
		}
	}

	public static void main (String [] args) throws Exception {
		sieveOfEratothenes();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int testCaseCount=Integer.parseInt(br.readLine());
		StringBuilder sb=new StringBuilder();
		for (int testCase=0;testCase<testCaseCount;testCase++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int K=Integer.parseInt(st.nextToken());
			int sum=0;
			if (!notPrime[N]) {
				if (N%K!=0) sum+=N;
				if (N!=1 && 1%K!=0) sum+=1;
			} else {
				if (K!=1) {
					for (int n=1;n*n<=N;n++) if (N%n==0) {
						if (n%K!=0) sum+=n;
						int f=N/n;
						if (n!=f && f%K!=0) sum+=f;
					}
				}
			}
			sb.append(sum);
			sb.append('\n');
		}
                // Printing in loop will cause TLE!
		System.out.print(sb.toString());
	}
}
