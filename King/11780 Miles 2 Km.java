import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
	
	public static void main (String [] args) throws Exception {
		int [] fibs=new int [17];
		fibs[0]=0; fibs[1]=1;
		for (int i=2;i<fibs.length;i++) fibs[i]=fibs[i-2]+fibs[i-1];
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			int N=Integer.parseInt(br.readLine());
			if (N==0) break;
			double realV=N*1.6;
			
			int [] dp=new int [N+1];
			Arrays.fill(dp,100000);
			dp[1]=2;
			
			for (int miles=1;miles<dp.length;miles++) {
				double KM=miles*1.6;
				for (int i=0;i<fibs.length-1 && miles-fibs[i]>=0;i++) {
					int subMile=miles-fibs[i];
					double oldDiff=Math.abs(dp[miles]-KM);
					double newDiff=Math.abs(dp[subMile]+fibs[i+1]-KM);
					if (oldDiff>newDiff) dp[miles]=dp[subMile]+fibs[i+1];
				}
			}
			
			System.out.printf("%.2f\n", Math.abs(dp[N]-realV));
		}
	}

}