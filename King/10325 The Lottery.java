import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	private static long N;
	private static long [] Factors;

	private static long gcd(long a, long b) {
		if (b==0) return a;
		return gcd(b,a%b);
	}

	private static long lcm(long a, long b) {
		return (a*b)/gcd(a,b);
	}

	private static long dfs(int fI, long n) {
		if (fI==Factors.length) return N/n;
		return dfs(fI+1,n)-dfs(fI+1,lcm(n,Factors[fI])); // I.e. We count no of multiples of 6, then subtract no of multiples of 2/3 when possible.
	}

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			StringTokenizer st=new StringTokenizer(s);
			N=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());

			Factors=new long [M];
			st=new StringTokenizer(br.readLine());
			for (int m=0;m<M;m++) Factors[m]=Integer.parseInt(st.nextToken());
			Arrays.sort(Factors);

			long ans=dfs(0,1);
			System.out.println(ans);
		}
	}

}
