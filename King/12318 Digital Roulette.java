import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static int [] Poly;
	
	private static long powMod(long x, long k, long mod) {
		long r=1;
		for (int i=0;i<k;i++) r=(r*x)%mod;
		return (int)r;
	}

	private static int calcP(int n, int mod) {
		long sum=0;
		for (int i=Poly.length-1;i>=0;i--) sum=(sum+(Poly[i]%mod)*powMod(n,i,mod))%mod;
		return (int)sum;
	}
	
	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			if (N==0 && M==0) break;

			N++;
			int K=Integer.parseInt(br.readLine().trim());

			st=new StringTokenizer(br.readLine());
			Poly=new int [K+1];
			for (int i=0;i<Poly.length;i++) Poly[i]=Integer.parseInt(st.nextToken());
			
			boolean [] flag=new boolean[N];
			int ans=0;
			for (int m=0;m<=M;m++) {
				int curr=calcP(m,N);
				if (!flag[curr]) {
					flag[curr]=true;
					ans++;
				}
			}
			System.out.println(ans);
		}
	}

}
