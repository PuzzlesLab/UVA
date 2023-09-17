import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		long [] dp=new long [1000011];
		dp[6]=1;
		for (int i=1;i<=3;i++) for (int i2=6;i2<dp.length-i;i2++) dp[i+i2]+=dp[i2];

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		int TC=1;
		while (!(s=br.readLine()).equals("0")) {
			int N=Integer.parseInt(s);
			StringBuilder sb=new StringBuilder();
			sb.append("Case ");
			sb.append(TC++);
			sb.append(": ");
			sb.append(dp[N]);
			System.out.println(sb);
		}
	}

}
