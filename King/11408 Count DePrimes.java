import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final int MAX_N=5000001;
	private static boolean [] NotPrime=new boolean [MAX_N];

	private static void eSieve() {
		NotPrime[0]=true;
		NotPrime[1]=true;
		for (int i=2;i*i<NotPrime.length;i++) if (!NotPrime[i]) {
			for (int i2=i*i;i2<NotPrime.length;i2+=i) NotPrime[i2]=true;
		}
	}
	
	public static void main(String [] args) throws Exception {
		eSieve();

		int [] sumOfPF=new int [MAX_N];
		for (int i=2;i<NotPrime.length;i++) if (!NotPrime[i]) {
			int temp=i;
			while (temp<MAX_N) {
				sumOfPF[temp]+=i;
				temp+=i;
			}
		}
		
		int [] dp=new int [MAX_N];
		for (int i=2;i<dp.length;i++) {
			dp[i]=dp[i-1];
			if (!NotPrime[sumOfPF[i]]) dp[i]++;
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			StringTokenizer st=new StringTokenizer(s);
			int A=Integer.parseInt(st.nextToken())-1;
			int B=Integer.parseInt(st.nextToken());
			System.out.println(dp[B]-dp[A]);
		}
	}

}