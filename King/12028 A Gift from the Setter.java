import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String [] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			long K=Integer.parseInt(st.nextToken());
			long C=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());
			long mod=1000007;

			long [] a=new long [N];
			a[0]=Integer.parseInt(st.nextToken());
			for (int i=1;i<N;i++) a[i]=(K*a[i-1]+C)%mod;
			Arrays.sort(a);
			
			long [] dp=new long [N];
			dp[0]=a[0];
			for (int i=1;i<N;i++) dp[i]=dp[i-1]+a[i];

			long ans=0;
			for (int i=0;i<N;i++) ans+=(dp[N-1]-dp[i])-(a[i]*(N-1-i));

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
	}

}