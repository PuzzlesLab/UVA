import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {

	private static final int MAX_N=1000002;

	public static void main(String [] args) throws Exception {
		int [] status=new int [MAX_N];
		/*
		 * 0 = Unset
		 * 1 = 4N+1 * 4N+1  (No more multiplication allowed)
		 * 2 = Base 4N+1
		 * 3 = Semi Prime
		 */
		for (long h=5;h<MAX_N;h+=4) if (status[(int)h]==0) {
			for (long h2=h+4;h*h2<MAX_N;h2+=4) status[(int)(h*h2)]=1;
			status[(int)h]=2;
			if (h*h<MAX_N) status[(int)(h*h)]=3;
		}

		for (long h=5;h<MAX_N;h+=4) if (status[(int)h]==2) {
			for (long h2=h+4;h*h2<MAX_N;h2+=4) if (status[(int)h2]==2) {
				status[(int)(h*h2)]=3;
			}
		}

		int [] dp=new int [MAX_N];
		for (int i=1;i<MAX_N;i++) dp[i]=dp[i-1]+(status[i]==3?1:0);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		while (!(s=br.readLine()).equals("0")) {
			int h=Integer.parseInt(s);
			System.out.printf("%d %d\n",h,dp[h]);
		}
	}

}