import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

	private static int [] Fac;
	private static int [] Dp;

	private static int count(int num) {
		if (num==0) return 0;
		
		if (Dp[num]==-1) {
			Dp[num]=Integer.MAX_VALUE;
			for (int i=Fac.length-1;i>=1;i--) if (num>=Fac[i]) Dp[num]=Math.min(Dp[num],1+count(num-Fac[i]));
		}

		return Dp[num];
	}

	public static void main(String[] args) throws Exception {
		Fac=new int [10];
		Fac[0]=1;
		for (int i=1;i<Fac.length;i++) Fac[i]=i*Fac[i-1];

		Dp=new int [100001];
		Arrays.fill(Dp,-1);

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while ((s=br.readLine())!=null) {
			// Don't reset Dp here, reusable for all test cases.
			System.out.println(count(Integer.parseInt(s)));
		}
	}

}
