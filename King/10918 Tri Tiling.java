import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws Exception {
		long [] dp=new long [32];
		dp[0]=1;
		for (int i=2;i<dp.length-1;i+=2) {
			for (int i2=0;i2<i-2;i2+=2) dp[i]+=dp[i2]<<1;
			dp[i]+=3*dp[i-2];
		}

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			if (N==-1) break;
			System.out.println(((N&1)==0)?dp[N]:0);
		}
	}

}
