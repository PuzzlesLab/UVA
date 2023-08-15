import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static long gcd(long a, long b) {
		return (b==0) ? a : gcd(b,a%b);
	}

	private static long lcm(long a, long b) {
		return a/gcd(a,b)*b;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			int N=Integer.parseInt(br.readLine());

			StringTokenizer st=new StringTokenizer(br.readLine());
			long [] coins=new long [N];
			for (int n=0;n<N;n++) coins[n]=Integer.parseInt(st.nextToken());

			long ans=coins[0];
			for (int n=1;n<N;n++) ans=lcm(ans,coins[n]);
			ans*=35;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
	}

}