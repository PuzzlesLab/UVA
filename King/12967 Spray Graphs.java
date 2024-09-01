import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String [] args) throws Exception {
		long [] dp=new long [31];
		dp[1]=1;
		dp[2]=4;
		// Extension done on every old outside nodes, so every old node connects 2 new outside nodes.
		// There are also 4 new corner outside nodes.
		for (int i=3;i<dp.length;i++) dp[i]=(dp[i-1]<<1)+4;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int TC=Integer.parseInt(br.readLine());
		for (int tc=0;tc<TC;tc++) {
			int N=Integer.parseInt(br.readLine());
			System.out.println(dp[N]);
		}
 	}

}
