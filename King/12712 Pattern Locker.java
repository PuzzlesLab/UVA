import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

	private static final long P=10000000000007L;

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=1;tc<=TC;tc++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			int L=Integer.parseInt(st.nextToken());
			int M=Integer.parseInt(st.nextToken());
			int N=Integer.parseInt(st.nextToken());

			int L2=L*L;
			long [] Dp=new long [L2+1];
			Dp[0]=L2;
			for (int i=1;i<=L2;i++) Dp[i]=(Dp[i-1]*(L2-i))%P;

			long ans=0;
			for (int i=M-1;i<N;i++) ans=(ans+Dp[i])%P;

			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(tc);
			sb.append(": ");
			sb.append(ans);
			System.out.println(sb);
		}
	}

}
