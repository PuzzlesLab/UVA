import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static int [] dp=new int [50000];

	public static int getSurvivor(int N) {
		 // Special case for k=2 => Move first significant bit to last bit (CP4.1 pg 135)
		int pow=(int)(Math.log(N)/Math.log(2));
		N=N%(int)Math.pow(2, pow);
		return (N<<1)+1;
	}
	
	public static int calc(int N) {
		if (N==0) return 0;
		if (dp[N]==-1) {
			int survivor=getSurvivor(N);
			if (N==survivor) dp[N]=N*2;
			else dp[N]=(N-survivor) + calc(survivor);
		}
		return dp[N];
	}

	public static void main (String [] args) throws Exception {
		Arrays.fill(dp, -1);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			int N=Integer.parseInt(s);
			int ans=calc(N);
			System.out.println(ans);
		}
	}

}
