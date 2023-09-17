import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		long [] dp=new long [1000001];
		for (int i=4;i<dp.length;i++) dp[i]=dp[i-1]+((i-1L)*(i-2L)/2-(i-1L)/2)/2;

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N<3) break;
			System.out.println(dp[N]);
		}
	}

}
